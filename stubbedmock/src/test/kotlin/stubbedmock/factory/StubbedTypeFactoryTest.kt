package stubbedmock.factory

import stubbedmock.models.Status
import stubbedmock.types.StringType
import stubbedmock.types.PrimitiveType
import stubbedmock.types.AutoBoxedType
import stubbedmock.types.CollectionType
import stubbedmock.types.DateType
import stubbedmock.types.EnumType
import stubbedmock.types.MapType
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Date

class StubbedTypeFactoryTest {

  lateinit var stubbedTypeFactory: StubbedTypeFactory

  @Before fun setup() {
    stubbedTypeFactory = StubbedTypeFactory()
  }

  @Test fun getValueStringType() {
    val clazz = stubbedTypeFactory.getValue(String::class.java, "foo")::class
    assertEquals(clazz, StringType::class)
  }

  @Test fun getValuePrimitiveType() {
    val clazz = stubbedTypeFactory.getValue(Int::class.java, "foo")::class
    assertEquals(clazz, PrimitiveType::class)
  }

  @Test fun getValueAutoBoxedType() {
    val clazz = stubbedTypeFactory.getValue(Integer::class.java, "foo")::class
    assertEquals(clazz, AutoBoxedType::class)
  }

  @Test fun getValueCollectionType() {
    val clazz = stubbedTypeFactory.getValue(Collection::class.java, "foo")::class
    assertEquals(clazz, CollectionType::class)
  }

  @Test fun getValueMapType() {
    val clazz = stubbedTypeFactory.getValue(Map::class.java, "foo")::class
    assertEquals(clazz, MapType::class)
  }

  @Test fun getValueEnumType() {
    val clazz = stubbedTypeFactory.getValue(Status::class.java, "foo")::class
    assertEquals(clazz, EnumType::class)
  }

  @Test fun getValueDateType() {
    val clazz = stubbedTypeFactory.getValue(Date::class.java, "foo")::class
    assertEquals(clazz, DateType::class)
  }

}