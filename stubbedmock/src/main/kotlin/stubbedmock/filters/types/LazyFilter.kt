package stubbedmock.filters.types

import java.lang.reflect.Field

private const val K_PROPERTY_FILTER = "KProperty[]"
private const val LAZY_FILTER = "Lazy"

class LazyFilter : StubbedFilter {

  override fun isFiltered(field: Field) =
      field.type.simpleName == K_PROPERTY_FILTER || field.type.simpleName == LAZY_FILTER
}