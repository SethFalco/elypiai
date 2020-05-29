# Contributing to Elypiai
## About
This will cover how to contribute to Elypiai and what guidelines to follow
when creating additional wrappers.

First you'll need to have a basic understanding of the following libraries, you can do
independent research on the below, or just go through the Elypiai wraps and use them as
referable examples:

* [Retrofit] and [OkHttp] - These are the core HTTP libraries for making requests.
* [Retropia] - We made a small extension for Retrofit to provide utilities and a subjectively cleaner interface.
* [Gson] - Retrofit uses this for deserializing RESTful responses.
* JAXB - Retrofit uses this for deseralizing XML responses.

## Getting Started
For the most part you can just use [Retrofit] as usual, the only difference is that
when we make an `ApiWrapper` object which uses the service class we make with
Retrofit and make the methods return the `Call` wrapped in an `RestInterface` implementation.

The two main types of `RestInterface` are the `RestAction`, and `OptionalRestAction`, depending
on if the consumer of the API should be concerned over if a response exists or not.
The response might not exist in cases where the API returns an a 
success status code, but the response isn't the resource we were
after, and no further information is available for why.

The following is taken from the [Urban Dictionary] API:
```java
public RestAction<DefineResult> define(String term) {
	Call<DefineResult> call = service.define(term);
	
	// This is the important part.
	return new RestAction<>(call);
}
```

From here the rest is up to Retropia, which allows users to call `queue(success, failure)`, 
or `complete()` depending on if they want asynchronous or synchronous requests, or can
use the `RestLatch` or `RestPaginator` for convinent access to common operations. 

#### Things to note:
* The wrapper should always take a first parameter of `URL` with a hard-coded
`baseUrl` property. This is because for testing we want to be able to replace
this `URL` with a custom one.
* The wrapper should avoid using the `Call` objects returned by the service where
possible, and instead opt to create a `RestAction` which is an abstraction of the
`Call` object to either perform the request async or non-async, or execute pipes.

[Retrofit]: https://github.com/square/retrofit "Retrofit on GitHub"
[Retropia]: https://gitlab.com/Elypia/retropia "Retropia on GitLab"
[OkHttp]: https://github.com/square/okhttp "OkHttp on GitHub"
[Gson]: https://github.com/google/gson "Gson on GitHub"
[Urban Dictionary]: https://www.urbandictionary.com "Urban Dictionary"
