package stubbedmock.filters

import stubbedmock.filters.types.StubbedFilter
import stubbedmock.filters.types.CompanionFilter
import stubbedmock.filters.types.SyntheticFilter
import java.lang.reflect.Field

internal class MockFilters(filters: List<StubbedFilter>) {
  private val filterList = mutableListOf<StubbedFilter>()

  init {
    //pre-defined filters
    filterList.add(CompanionFilter())
    filterList.add(SyntheticFilter())
    filterList.addAll(filters)
  }

  fun isFiltered(field: Field): Boolean =
      filterList.any { it.isFiltered(field) }
}