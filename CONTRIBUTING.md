# Contributing

## Reporting Bugs

If you've found a bug with Elypiai, [create an issue](https://gitlab.com/SethFalco/elypiai/-/issues) on GitLab.

If you haven't found a bug, but need help using Elypiai in your project, consider asking on [Stack Overflow](https://stackoverflow.com/questions/tagged/svgo), you may get help faster there. You can still create an issue if the confusion stemmed from a lack of documentation.

## Development

### Requirements

* [Git](https://git-scm.com)
* Java 17
* [Gradle 7](https://gradle.org)

### Getting Started

Clone the repository with Git.

```sh
git clone https://gitlab.com/SethFalco/elypiai.git
```

This is a Java project and uses Gradle for package management. Run the `check` task ensure tests are passing before you start development, this will also install all package dependencies.

```sh
./gradlew check
```

## New Service Wrappers

Service wrappers must always have a constructor that accepts a `URL`, with another constructor that uses a hard-coded URL. This is for testing, and to be able to replace the base URL with another service that has a compatible API, especially relevant for self-hostable applications or Open Source frontends.

## Writing Tests

In this project, tests for each module are structured in one of two ways.

* We mock the API, by downloading a response manually and putting it in the test classpath. Then overriding the base URL of the service class use a mock webserver.
* We query the real API. This means "unit tests" that do real IO!

Given the nature of this project, the latter is preferred, so we know as soon as possible if upstream changes break our wrapper. This will not happen often as fortunately most APIs are very stable and avoid breaking changes, but sometimes things break in unexpected ways.

Allowing IO means that we have to be considerate when writing tests to avoid spamming services, and to be aware that tests could fail spontaneously in the case of a service outage. These are worthwhile trade offs to avoid a false sense of security in testing against archived API responses that are updated infrequently, instead of live API responses.

## Funding

Sponsoring the project or any of the maintainers/contributors helps keep the project sustainable.

* [Seth Falco on GitHub Sponsors](https://github.com/SethFalco)
