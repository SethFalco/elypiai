# Contributing to Elypiai
## About
This will cover how to contribute to Elypiai and what guidelines to follow
when creating additional wrappers.

First you'll need to have a basic understanding of the following libraries, you can do
independent research on the below, or just go through the Elypiai wraps and use them as
referable examples:

* [Retrofit] and [OkHttp] - These are the core HTTP libraries for making requests.
* [RxJava] - We have all API methods respond with RxJava classes for it's fluent API.
* [Retropia] - We made a small extension for Retrofit to for some utilities and standardised classes.
* [WebServer TestBed] - We made a small library for cleaner testing when using [MockWebServer].
* [Gson] - Retrofit uses this for deserializing RESTful responses.

## Guide Lines
We would like to be as consistent as possible between all wraps that appear in this
repository, this means we'll be trying to pull logic into [Retropia] and [WebServer TestBed]
where possible, and then adhere to a set of guide lines for how the interface in this
repository should look to consumers of the libraries.

### Utilize RxJava
For the most part you can just use [Retrofit] as usual, but as we're using RxJava,
it's important to ensure or service methods don't return a `Call` but instead an RxJava
type like, `Maybe`, `Single`, or `Obervable`. This is becasue the RxJava API is very fluent
and provides a lot of control both for us to handle special cases with API objects and
for consumers of the library.

### Overridable Base URL
The wrapper should always take a first parameter of `URL` with a hard-coded
`baseUrl` property. This is because for testing we want to be able to replace
this `URL` with a custom one. There are also cases, especially with self-hostable services,
where we want to be able to override the server address.

[Retrofit]: https://github.com/square/retrofit "Retrofit on GitHub"
[OkHttp]: https://github.com/square/okhttp "OkHttp on GitHub"
[RxJava]: https://github.com/ReactiveX/RxJava "RxJava on GitHub"
[Retropia]: https://gitlab.com/Elypia/retropia "Retropia on GitLab"
[WebServer TestBed]: https://gitlab.com/Elypia/webserver-testbed "WebServer TestBed on GitLab"
[MockWebServer]: https://github.com/square/okhttp/tree/master/mockwebserver "MockWebServer on GitHub"
[Gson]: https://github.com/google/gson "Gson on GitHub"
[Urban Dictionary]: https://www.urbandictionary.com "Urban Dictionary"
