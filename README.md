# Stubbed Mock

Stubbed Mock is an open-source library written in Kotlin for JVM that allows
users to easily stub data models in a Mockito-like fashion during Unit tests.

Stubbed Mock supports plain Java and Kotlin classes, constructor and field
injection of stubbed values, `lazy` and Kotlin's backing field properties,
and many other complex language features, all of which are available for both
languages.

Important to note: this library is not meant to replace Mockito, nor does it
mock class (or function) behavior, its primary use is with data model classes.

### How to use

Let's take a look at one simple example: this is a data model you apparently
use in many places throughout your project:

```kotlin
// our default: Kotlin
data class Person(
  val name: String,
  val nickname: String? = null,
  val age: Int = 18
)
```

```java
// the same thing in Java
public final class Person {

  @NotNull private final String name;
  @Nullable private final String nickname;
  private final int age;

  @NotNull public final String getName() { return name; }
  @Nullable public final String getNickname() { return nickname; }
  public final int getAge() { return age; }

  public Person(@NotNull String name, @Nullable String nickname, int age) {
    this.name = name;
    this.nickname = nickname;
    this.age = age;
  }

   // ... (more Java boilerplate)
}
```

You can imagine that it might be repetitive to fill in these 3 data values, in
every test everywhere. But this is a simplified example, in reality, your data
models will have 10 or 20 fields, all of which need to be filled with data before
you can use the instance in your tests.

Here are some of the most representitive examples of what this library does; all of
them are in Kotlin but you can use the same approach from Java too. For other examples,
take a look at the `demo` directory.

**Stubbing a data model**

```kotlin
@StubbedMock lateinit var person: Person
```

The instance that this produces is ```Person(name="name", nickname="nickname", age=0)```,
i.e. all class member variables are set to their type's default value, and string properties
are set to the property name (even for nullable strings).

**Stubbing a data model with custom values***

```kotlin
@StubbedMock(
  Stub("name", /* stringValue = */ "Marcus"),
  Stub("nickname", /* stringValue = */ "Mark"),
  Stub("age", intValue = 50)
)
lateinit var person: Person
```

The instance that this produces is ```Person(name="Marcus", nickname="Mark", age=50)```.
Notice that here we set values for each of the properties manually, using the `Stub`
child annotation. `@Stub` supports all basic JVM types, and you need to reference the
property name you want to inject the value to.

**Initializing the mocks**

As you might have suspected, there needs to be an entry point. Similarly to what
Mockito does, you need to invoke our stubbing function before you use the properties
annotated with `@StubbedMock`. The simplest way to do so is in your setup function:

```kotlin
@Before fun setup() {
  initStubbedMocks(this)
}
```

Or even shorter if this is your only line in `setup`:

```kotlin
@Before fun setup() = initStubbedMocks(this)
```

**How stubbing works**

Our stubber is a recursive instance generator. This means that it also supports more
complex data models, such as this one:

```kotlin
data class Family(
  val members: List<Person>,
  val head: Person,
  val name: String
)
```

In this case, the stubber will go through all constructor arguments, and try to stub
in default values for them. In case it encounters a complex data type (such as `Person`),
it recursively does the same stubbing process for this data type.

In case of lists, at the moment it stubs in an empty list of the proposed data type, but
we're working on improving this in the future.

**Filtering properties**

In case you don't want to stub special properties (like `lazy` delegated ones) or some
pre-filled data types that class will modify later on, you can use the advanced stubbing
feature called `Filtering`. It allows you to skip the problematic property and keep its
value as-is. A common use-case is a `lazy` property, and to add the `lazy` filter to your
initializer function, you can easily pass it in as an argument:

```kotlin
@Before fun setup() {
  val filters = listOf(LazyFilter())
  initStubbedMocks(this, filters)
}
```

In case your property is filled later on by the class, or even externally, you might not
want to touch it until that later moment. So, in our latest example with the `Family` model,
you can (for example) just skip the list stubbing, like so:

```kotlin
@Before fun setup() {
  val filters = listOf(ListFilter())
  initStubbedMocks(this, filters)
}
```

Since list filtering is not supported out of the box, you need to provide a custom filter
for this data type - it is pretty easy to do. Another type can replace the `List`, or you
can do a more sophisticated check if need be. Here's the simplest `List` example:

```kotlin
class ListFilter : StubFilter {
  override fun isFiltered(field: Field) = field.type.simpleName == "List"
}
```

### How to include this in your projects

To include this library in your project, you need to clone the repository
first to a separate directory inside of your project, then reference the
`stubbedmock` folder as a Gradle module. If you are not using Gradle, you
have to include the `stubbedmock/src/main/kotlin` as source directory. Keep
in mind that this project is primarily meant to be used in Unit tests (not
in production code).

As soon as we have continuous integration up and running, we will update
this section.

### Tech used

- [IntelliJ IDEA](https://www.jetbrains.com/idea/) - our favorite IDE
- [Kotlin](https://kotlinlang.org/) - our favorite language
- [JUnit](https://junit.org/junit4/) - our favorite test framework
- [Reactive streams](http://reactivex.io/) (for demo purposes only)

### Reporting issues and how to contribute

Please see our [contribution guide](CONTRIBUTING.md) on how to report issues
and include your features or bug fixes in the codebase. This document also
contains information about the project structure and other guidelines required
to participate in development of Stubbed Mock.

### License

Check out the license for this project [in this document](LICENSE).