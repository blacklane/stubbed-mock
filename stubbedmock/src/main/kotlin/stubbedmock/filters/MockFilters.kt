package stubbedmock.filters

import stubbedmock.filters.types.StubFilter
import stubbedmock.filters.types.CompanionFilter
import stubbedmock.filters.types.SyntheticFilter
import java.lang.reflect.Field

internal class MockFilters(filters: List<StubFilter>) {
  private val filterList = mutableListOf<StubFilter>()

  init {
    // pre-defined filters
    filterList.add(CompanionFilter())
    filterList.add(SyntheticFilter())
    filterList.addAll(filters)
  }

  fun isFiltered(field: Field) = filterList.any { it.isFiltered(field) }
}