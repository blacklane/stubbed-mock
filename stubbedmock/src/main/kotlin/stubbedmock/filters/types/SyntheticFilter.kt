package stubbedmock.filters.types

import java.lang.reflect.Field

class SyntheticFilter : StubFilter {
  override fun isFiltered(field: Field) = field.isSynthetic
}