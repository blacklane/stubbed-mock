# Contributing Guide

First of all, thank you for being here! And don't be afraid of contributing.
Every contribution has value, and we appreciate the help. If you're unsure about
anything, feel free to reach out to a maintainer. We'll be there to help every
step of the way.

Let's do this together :muscle:

### Table of contents

- [Code of Conduct](#code-of-conduct)
- [Issues, requests and questions](#issues-requests-and-questions)
    + [Creating issues](#creating-issues)
    + [Branching strategy](#branching-strategy)
- [Setting up your environment](#setting-up-your-environment)
    + [Importing the project](#importing-the-project)
    + [Configuring the SDK](#configuring-the-sdk)
    + [Gradle wrapper](#gradle-wrapper)
    + [Delegating the build to Gradle (optional)](#delegating-the-build-to-gradle-optional)
    + [Code style and guidelines](#code-style-and-guidelines)
- [How to edit, run and test](#how-to-edit-run-and-test)
    + [Clean code](#clean-code)
    + [Modules and build configuration](#modules-and-build-configuration)
    + [Project versioning](#project-versioning)
    + [Compiling and testing](#compiling-and-testing)

## Code of Conduct

By participating in this project, you're agreeing to uphold our Code of Conduct.
If you need to report a problem, please see the
[CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) located in this repository.

## Issues, requests and questions

### Creating issues

To report a bug, request a feature, ask a question or anything else, you can use
the issues tab or [follow one of our templates][1].

Any feedback is welcome, but please check if something similar already exists
before submitting your own. After you've created the issue, one of our
maintainers will take a look and try to respond in a timely manner.

If you're interested in working on an issue, make sure you tag one of the
project maintainers in the comments. From there, we'll reach out to you and
create a plan together.

### Branching strategy

Our main/default branch is master. It's push-protected, so you can't push any
changes to `master` without a review. In most cases, static checks, tests and
peer reviews will only happen once everything is ready. Anything
work-in-progress should be marked as `[WIP]` or you can also use GitHub's
_Draft_ mode.

Most contributors won't have `write` access to this repository at first. So the
recommended workflow would look like this:

1. [Fork this repository][2]
1. Apply any changes to your repository's master branch. Each commit should
group your changes logically. Your commit messages should be clear,
understandable and under 80 characters
1. You can open a PR (pull request) from your repository's *Pull Requests* tab.
Point your `master` branch to the this repository's `master` branch. Every PR
should be linked directly to a project issue, so please reference the issue
you're working on in your PR's description using the `#` notation. If your PR
isn't ready for review, make sure to put the `[WIP]` prefix in the PR title.
After your PR is open, static checks and tests will be executed
1. Once you're done working and you have no more changes, remove the `[WIP]`
prefix from the PR title and tag a maintainer in the comments section. We'll
then assign a reviewer (or two) to discuss merging your PR. Make sure you have
rebased the PR to the latest original `master` branch before asking for a review
1. If changes are requested, your new commits should start with `[Review]`
1. After merging to `master`, the checks will run again. If those are successful,
the new binaries will automatically be deployed from this upstream to the
relevant cloud servers
1. You can now safely delete your private downstream repository

If you have `write` access to this repository, you're probably already familiar
with our workflow. The process is pretty similar:

1. Create a new (logically named) branch based on `master`
1. Apply your changes to the new branch
1. Create a new PR for your changes that points to `master` and references the
issue. After your PR is open, static checks and tests will be executed
1. When you're done working, remove the `[WIP]` PR prefix and tag a maintainer
in the comments. Make sure you have rebased to `master` before asking for a review
1. If changes are requested, your new commits should start with `[Review]`
1. After merging to `master` and automatic checks verify the merge, new binaries
are deployed to the relevant cloud servers
1. You can now safely delete your branch

## Setting up your environment

To develop this project, we used [IntelliJ IDEA][3] from JetBrains. It's a
fully-featured, stand-alone desktop IDE that supports most modern languages and
build script configurations out of the box. Plus it has a free community edition
which we especially appreciate.

Our main build tool for this project is [Gradle][4].

### Importing the project

The first step towards contributing is to make sure you can edit the source code
using IntelliJ IDEA. To do this, you can either:

- Use the built-in "new project from git" feature in the `File` menu of your IDE
- Clone this repository manually then open it in the IDE from the `File` menu

After opening the project, your IDE might ask you to import the Gradle project
and all its modules - look out for the alert box on the bottom right of the IDE.
Keep in mind that opening isn't the same as importing. Opening shows all the
files available in the project. Importing allows for build script integration
with the IDE. It also enables other tooling and development features. So for
this project, you're going to want to import.

Once you have Gradle imported, you'll get a dialog asking for configuration
options. Choose to run the project using the provided Gradle wrapper. This
ensures that everyone developing Stubbed Mock uses the same version of Gradle
for assembling and packaging. You can also tick the "auto-import" checkbox to
automatically import the dependencies and any sub-modules.

### Configuring the SDK

Now that you've imported everything, your IDE should change the interface to
reflect that you're working on a Gradle project. Including a Gradle panel should
become available in one of the sidebars.

You might get a warning that you're missing the SDK configuration. Because we
don't enforce a specific _compile_ version for Kotlin or Java, you can choose
your own SDK version in the project/module settings. We do, however, force the
output bytecode version to **1.6** to maintain backward compatibility with
external projects.

The project/module settings screen should looks like this:

![SDK Settings](docs/project_sdk.png)

For this change to take effect, you might need to restart your IDE.

### Gradle wrapper

In rare cases, due to the IDE configuration or import strategy, your local
Gradle wrapper may be missing. To fix this problem, you should create a local
wrapper by running the `wrapper` task from the Gradle side panel. You can do
this by double-clicking the task:

![Gradle wrapper task](docs/wrapper_task.png)

### Delegating the build to Gradle (optional)

IntelliJ IDEA uses its own build system and doesn't run any of the pre-defined
Gradle tasks sometimes (but rarely) you'll have missing build outputs - or
inconsistencies between the source and the output. If this happens, we've
verified that you can safely delegate all build tasks to Gradle by changing the
Project Preferences, like this:

![Gradle delegation](docs/delegate_to_gradle.png)

Assuming that your import ran smoothly, you can now enjoy coding with us! 😊 And
if not, you can always contact a maintainer or open an issue.

### Code style and guidelines

We follow our own [JVM Source Guidelines][5].

## How to edit, run and test

### Clean code

At Blacklane, we care deeply about quality in our core service (professional ground transportation) - and we apply similar standards to our code. Before submitting your changes for review, please be sure your changes follow the rules of clean coding, like [Clean Code][6] or [SOLID principles][7] from Robert C. Martin.

### Modules and build configuration

This project has two named Gradle modules: `stubbedmock-demo` and
`stubbedmock-lib`. They live in the `demo` and `stubbedmock` directories,
respectively. The library source is in `stubbedmock-lib` and the most common
usage examples are in `stubbedmock-demo`.

Each of the modules has its own Gradle configuration file (`build.gradle`).
There's also another configuration file in the root directory that is applied to
both modules. In that root `build.gradle`, we've specified all the variable
dependencies that we use across both modules using a top-level `ext` block.

This project uses the standard [Gradle directory structure][8].

### Project versioning

If your changes require an increase in the project version, this will be
outlined in the issue's acceptance criteria. When updating the project version,
this should happen in two places: The root `build.gradle`
(in `ext.projectVersion`) and in the `README.md` file.

We use [semantic versioning][9] to come up with new version numbers. For general
new versions, we increase the `minor` number. For a breaking change, we increase
the `major` number. And for an emergency quick-fix, we increase the `patch`
number. If you aren't sure, ask one of the maintainers.

### Compiling and testing

**StubbedMock** is a library that's only used in unit tests. So there isn't any
code to "run" or "execute" in your terminal and there's no user interface.

You can check if your code is working by writing unit tests for the library. To
run tests, you can use your IDE by right-clicking on the test directory and
selecting the "Run all tests" option. If you'd rather run the tests from the
command line, you can use the standard `test` task (note that we use the
Gradle wrapper, `gradlew`):

```shell
$ ./gradlew test
```

If you want to build the project from the command line, you can use the standard
`assemble` task, like so:

```shell
$ ./gradlew assemble
```

When dealing with cached tasks using Gradle, sometimes you'll want to clear the
caches and re-build everything. For this, you can use the standard `clean`
task, like so:

```shell
$ ./gradlew clean
```

You can also combine tasks together, e.g. clean, build and test the whole project:

```shell
$ ./gradlew clean assemble test
```

[1]: https://github.com/blacklane/stubbed-mock/issues/new/choose
[2]: https://github.com/blacklane/stubbed-mock/fork
[3]: https://www.jetbrains.com/idea/
[4]: https://gradle.org/
[5]: https://github.com/blacklane/jvm-source-guidelines
[6]: https://www.google.com/search?q=robert+martin+clean+code
[7]: https://en.wikipedia.org/wiki/SOLID
[8]: https://docs.gradle.org/current/userguide/organizing_gradle_projects.html
[9]: https://semver.org
