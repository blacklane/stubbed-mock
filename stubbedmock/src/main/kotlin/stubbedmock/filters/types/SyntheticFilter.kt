package stubbedmock.filters.types

import java.lang.reflect.Field

class SyntheticFilter : StubbedFilter {

  override fun isFiltered(field: Field) =
      field.isSynthetic
}