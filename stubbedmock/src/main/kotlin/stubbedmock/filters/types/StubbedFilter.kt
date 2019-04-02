package stubbedmock.filters.types

import java.lang.reflect.Field

interface StubbedFilter {
  fun isFiltered(field: Field): Boolean
}