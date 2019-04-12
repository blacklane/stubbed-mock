package stubbedmock.mock

import stubbedmock.exception.StubbedMockException
import stubbedmock.factory.StubbedTypeFactory
import stubbedmock.filters.MockFilters
import stubbedmock.filters.types.StubFilter
import stubbedmock.types.ClassType
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Parameter

object Stubber {

  private val pendingMockedClasses = mutableListOf<String>()

  fun displayClassFields(clazz: Class<*>, objectInstance: Any) {
    for (field in clazz.declaredFields) {
      field.isAccessible = true
      println("FieldName : ${field.name} - FieldValue : ${field.get(objectInstance)}")
    }
  }

  private fun addToPendingClasses(clazz: Any) {
    val className = (clazz as Class<*>).simpleName
    if (pendingMockedClasses.contains(className)) {
      pendingMockedClasses.clear()
      throw StubbedMockException("$className has Circular Dependency !!")
    } else {
      pendingMockedClasses.add(className)
    }
  }

  private fun removeFromPendingClasses(clazz: Any) {
    pendingMockedClasses.remove(clazz::class.java.simpleName)
  }

  fun <T : Any> run(
    clazz: Class<T>,
    classFields: Map<String, Any> = hashMapOf(),
    stubbedFieldsMap: MutableMap<String, Any> = mutableMapOf(),
    filters: List<StubFilter> = emptyList()
  ): T {

    val classConstructors = clazz.declaredConstructors
    classConstructors.sortBy { it.parameterTypes.size }
    val objectInstance = createObjectInstance(0, clazz, classConstructors)

    addToPendingClasses(clazz)

    for (field in clazz.declaredFields) {
      field.isAccessible = true
      if (classFields.containsKey(field.name)) {
        field.set(objectInstance, classFields[field.name])
      } else if (stubbedFieldsMap.containsKey(field.name)) {
        field.set(objectInstance, stubbedFieldsMap[field.name])
      } else {
        mockClassField(objectInstance, field, MockFilters(filters))
      }
    }

    removeFromPendingClasses(objectInstance)

    return objectInstance
  }

  private fun <T> createObjectInstance(
    constructorStartIndex: Int,
    clazz: Class<T>,
    classConstructors: Array<out Constructor<*>>
  ): T {
    var constructorIndex = constructorStartIndex
    val targetConstructor: Constructor<*> = classConstructors[constructorIndex]
    val constructor = clazz.getConstructor(*targetConstructor.parameterTypes)

    return try {
      val mockedConstructorValues = mockConstructorParameters(targetConstructor.parameters)
      constructor.newInstance(*mockedConstructorValues.toTypedArray())
    } catch (exception: Exception) {
      constructorIndex++
      if (constructorIndex < classConstructors.size) {
        createObjectInstance(constructorIndex, clazz, classConstructors)
      } else {
        throw StubbedMockException("${clazz.simpleName} could not be initialized !!", exception)
      }
    }
  }

  private fun mockConstructorParameters(parameters: Array<Parameter>): MutableList<Any> {
    val mockedValues: MutableList<Any> = arrayListOf()
    parameters.forEach {
      mockedValues.add(mockWithDefault(it.type, it.name))
    }
    return mockedValues
  }

  private fun mockClassField(
    objectInstance: Any,
    field: Field,
    mockFilters: MockFilters
  ) {
    if (mockFilters.isFiltered(field)) return
    val mockedValue = mockWithDefault(field.type, field.name)
    field.set(objectInstance, mockedValue)
  }

  private fun mockWithDefault(clazz: Class<*>, varName: String): Any {
    val mockedValue = StubbedTypeFactory().getValue(clazz, varName)
    if (mockedValue !is ClassType) {
      return mockedValue.getStubbedValue(clazz)
    }
    try {
      return run(clazz)
    } catch (exception: StubbedMockException) {
      throw exception
    } catch (exception: Exception) {
      throw StubbedMockException("Type ${clazz.simpleName} is not supported, add a filter to skip it", exception)
    }
  }
}