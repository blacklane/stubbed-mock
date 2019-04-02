package stubbedmock.filters.types

import java.lang.reflect.Field

class TypeFilter(private val typeSimpleName: String) : StubbedFilter {

  override fun isFiltered(field: Field) =
      field.type.simpleName == typeSimpleName
}