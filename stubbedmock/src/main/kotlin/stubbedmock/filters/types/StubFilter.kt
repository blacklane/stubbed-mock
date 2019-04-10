package stubbedmock.filters.types

import java.lang.reflect.Field

interface StubFilter {
  fun isFiltered(field: Field): Boolean
}