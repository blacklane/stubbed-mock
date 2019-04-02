package stubbedmock.filters.types

import java.lang.reflect.Field

private const val COMPANION_FILTER = "Companion"

class CompanionFilter : StubbedFilter {

  override fun isFiltered(field: Field) =
      field.type.simpleName == COMPANION_FILTER
}