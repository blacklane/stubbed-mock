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
import org.junit.Before
import org.junit.Test
import java.util.Date

class StubbedTypeFactoryTest {

    lateinit var stubbedTypeFactory: StubbedTypeFactory

    @Before
    fun setup() {
        stubbedTypeFactory = StubbedTypeFactory()
    }

    @Test
    fun testGetValueStringType() {
        val clazz = stubbedTypeFactory.getValue(String::class.java, "foo")::class
        Assert.assertEquals(clazz, StringType::class)
    }

    @Test
    fun testGetValuePrimitiveType() {
        val clazz = stubbedTypeFactory.getValue(Int::class.java, "foo")::class
        Assert.assertEquals(clazz, PrimitiveType::class)
    }

    @Test
    fun testGetValueAutoBoxedType() {
        val clazz = stubbedTypeFactory.getValue(Integer::class.java, "foo")::class
        Assert.assertEquals(clazz, AutoBoxedType::class)
    }

    @Test
    fun testGetValueCollectionType() {
        val clazz = stubbedTypeFactory.getValue(Collection::class.java, "foo")::class
        Assert.assertEquals(clazz, CollectionType::class)
    }

    @Test
    fun testGetValueMapType() {
        val clazz = stubbedTypeFactory.getValue(Map::class.java, "foo")::class
        Assert.assertEquals(clazz, MapType::class)
    }

    @Test
    fun testGetValueEnumType() {
        val clazz = stubbedTypeFactory.getValue(Status::class.java, "foo")::class
        Assert.assertEquals(clazz, EnumType::class)
    }

    @Test
    fun testGetValueDateType() {
        val clazz = stubbedTypeFactory.getValue(Date::class.java, "foo")::class
        Assert.assertEquals(clazz, DateType::class)
    }

}