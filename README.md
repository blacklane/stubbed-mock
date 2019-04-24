# Stubbed Mock

Stubbed Mock is an open-source library written in Kotlin for JVM that allows
users to stub data models in a Mockito-like fashion during unit tests.

Stubbed Mock supports many language features like plain Java and Kotlin
classes, constructor and field injection of stubbed values, `lazy`, Kotlin's
backing field properties and more.

Important to note: This library isn't meant to replace Mockito and doesn't
mock class (or function) behavior. Its primary use is for data model classes.

### Table of Contents

* [Getting started](#getting-started)
* [Including Stubbed Mock in your projects](#including-stubbed-mock-in-your-projects)
* [Tech used](#tech-used)
* [Reporting issues and how to contribute](#reporting-issues-and-how-to-contribute)
* [License](#license)

## Getting started

To get an idea of how Stubbed Mock works, let's look at an example. Imagine this
 is a data model that you use in many places throughout your project:

```kotlin
// Kotlin
data class Person(
  val name: String,
  val nickname: String? = null,
  val age: Int = 18
)
```

```java
// Java
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

   // More Java boilerplate
}
```

It'd be repetitive to fill in these three data values for every test you have to
write. And, in reality, your data models will actually have 10 or 20 fields -
all of which need to be filled with data before you can use the instance in
your tests.

Here are some of the most representative examples of what this library does. The
examples are  in Kotlin but you can use the same approach from Java too.  For
other examples, take a look at the [`demo` directory](https://github.com/blacklane/stubbed-mock/tree/master/demo).

### Stubbing a data model

```kotlin
@StubbedMock lateinit var person: Person
```

This produces the instance: ```Person(name="name", nickname="nickname", age=0)```.
All class member variables are set to their type's default value. String
properties are set to the property name (even for nullable strings).

**Stubbing a data model with custom values***

```kotlin
@StubbedMock(
  Stub("name", /* stringValue = */ "Marcus"),
  Stub("nickname", /* stringValue = */ "Mark"),
  Stub("age", intValue = 50)
)
lateinit var person: Person
```

This produces the instance: ```Person(name="Marcus", nickname="Mark", age=50)```.
Notice that we manually set values for each of the properties using the `Stub`
 child annotation. `@Stub` supports all basic JVM types and you need to
  reference the property name you want to inject the value to.

### Initializing the mocks

As you might have suspected, there needs to be an entry point. Similar to
Mockito, you need to invoke our stubbing function before you use the properties
annotated with `@StubbedMock`. You can do this in your setup function:

```kotlin
@Before fun setup() {
  initStubbedMocks(this)
}
```

Or even shorter if this is your only line in `setup`:

```kotlin
@Before fun setup() = initStubbedMocks(this)
```

### How stubbing works

Our stubber is a recursive instance generator. This means that it also supports more
complex data models, like this one:

```kotlin
data class Family(
  val members: List<Person>,
  val head: Person,
  val name: String
)
```

In this case, the stubber will go through all constructor arguments and try to
stub in default values for them. If it encounters a complex data type (such as
  `Person`), it recursively does the same stubbing process for this data type.

For lists, it only stubs in an empty list of the proposed data type - but we're
working on improving this in the future.

### Filtering properties

If you don't want to stub special properties or pre-filled data types that
class will modify later, you can use the advanced stubbing feature called
`Filtering`. It allows you to skip that property and keep its value as-is.

A common use case is the `lazy` property. To add the `lazy` filter to your
initializer function, you can pass it in as an argument:

```kotlin
@Before fun setup() {
  val filters = listOf(LazyFilter())
  initStubbedMocks(this, filters)
}
```

When your property will be filled by the class (or even externally) later on,
you might not want to touch it until that moment. In this case, you can filter
it out. For example, if we wanted to skip the list stubbing in our `Family`
model from earlier, we can do that like so:

```kotlin
@Before fun setup() {
  val filters = listOf(ListFilter())
  initStubbedMocks(this, filters)
}
```

Because list filtering isn't supported out of the box, you need to provide a
custom filter for this data type. You can do this by replacing the `List` with
 another type or you can use a more sophisticated check if necessary. Here's
 one way to do it:

```kotlin
class ListFilter : StubFilter {
  override fun isFiltered(field: Field) = field.type.simpleName == "List"
}
```

## Including Stubbed Mock in your projects

To include this library in your project, you need to clone this repository into
a separate directory inside of your project. Then you should reference the
[`stubbedmock` folder](stubbedmock) as a Gradle module. If you aren't using
Gradle, you have to include the [`stubbedmock/src/main/kotlin`](src/main/kotlin)
as a source directory. Keep in mind that the primary use for this library is in
unit tests (not production code).

As soon as we have continuous integration up and running, we'll update this section.

## Tech used

- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Kotlin](https://kotlinlang.org/)
- [JUnit](https://junit.org/junit4/)
- [Reactive streams](http://reactivex.io/) (for demo purposes only)

## Reporting issues and how to contribute

Our [contributing guide](CONTRIBUTING.md) outlines how you can report issues
and get involved with Stubbed Mock. There's also information on the project
structure and other guidelines for developing new features, fixing bugs or
other changes related to the codebase.

## License

[MIT License](LICENSE)
