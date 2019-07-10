# Contributing to Elypiai
## About
This will cover the base of what you'd need to know to use Elypiai which should help
contribute to this repository or reuse the `common` and `extension` modules to create your own
wraps personally which will have a common interface, share resources, and allow for extensions similarly
to any wrap found in this repository.

First you'll need to have a basic understanding of the following libraries, you can do
independent research on the below, or just go through the Elypiai wraps and use them as
refernceable examples:

* [Retrofit][retrofit] and [OkHttp][okhttp]
* [GSON][gson] for JSON APIs
* JAXB for XML APIs

## Getting Started
In this guide we'll be wrapping [UrbanDictionary][ud] becasue it's small, only a single endpoint, and already
exists within this repository so it's easy to link to example code rather than mock and maintain seperate code.

### Creating Entities
The first step is to create a class for any entities we may expect this API to return.
In this case due to there being no official documentation, we'll just do a GET request and review
the response. Just use any HTTP Client or your your browser and make a request [here][ud-get].  
As of this tutorial, and what we'll continue working with, the response is as below.

```json
{
    "list": [
        {
            "definition": "One of the kindest people you will ever meet. Usually a girl [Azba] is a cute, smart, independent and non aggressive girl who will steal your heart with her amazing personality and her charming smile. She will be on of your best friends guaranteed no matter what. She is a good [fortnite player] and [a goofy], hot masterpiece and you would be lucky to have her in your life. Overall Azba is one of the most caring people ever existing. You would definitely want an Azba in your life.",
            "permalink": "http://azba.urbanup.com/13366073",
            "thumbs_up": 1,
            "sound_urls": [],
            "author": "Adam Zuhairy",
            "word": "azba",
            "defid": 13366073,
            "current_vote": "",
            "written_on": "2018-11-17T00:00:00.000Z",
            "example": "Omg I want [Azba] [in my life] [please] god",
            "thumbs_down": 0
        }
    ]
}
```

So from this we can see we'll be getting a list of results, so we can either use a deseralizer and convert this into
just a list, or make a wrapper object which goes over the list and may return the list or have utility methods to
perform predefined functions or sorting on the list. We'll go for the latter as it's more helpful for this case.

[A single definition to put in our list.][define-result]  
[List of results the wrapper will return.][define-result-set]

You can see we used a custom deseralizer in this object. They're helpful when APIs can return data in ways
that are not ideal for Java developers, for example numbers as strings, or an empty string instead of null like 
the case above. Several deseralizers have been predefined in the common library for GSON and JAXB including 
the `NullEmptyDeseralizer` which will deseralize empty Strings as `null` instead.

### Creating the Service
Once all entities have been created, we have to make a service class, the service usually isn't exposed
to the user, this is just so Retrofit can build requests and map responses for us.

Usually you'd expect to see one method for each endpoint, don't create overloads in the service class as that belongs
in the wrapper class which will be made later; all service methods should return a `Call<?>` object.

[Example UrbanDictionary service with a single endpoint.][ud-service]

### Creating the Wrapper
Now that we have both the entities and the service class, we can create the wrapper. The wrapper is the class
users will be interfacing with and will expose the methods in the service, and provide RestActions to calling
classes so that they can either `#queue` (async) or `#complete` (non-async) the request.

#### Things to note:
* The wrapper should always take a first parameter of `URL` with a hard-coded `BASE_URL` property. This is
because for testing we want to be able to replace this `URL` with our own one.
* The wrapper should avoid using the `Call<?>` objects returned by the service where possible, and instead opt
to create a `RestAction<?>` which is an abstraction of the `Call<?>` object to either perform the request async or
non-async, as well as execute pipes.

[The wrapper that users interface with.][ud-wrapper]

This demonstrates a minimal usage of Elypiai for a small API, there's little magic added above what other libraries
already provide, in fact most of these instructions until the last step was just basic Retrofit and Gson usage.  
The important part is was using the Elypiai classes/interfaces which gives users the control to add pipes,
extensions, and share resources between wrappers.

[retrofit]: https://github.com/square/retrofit "Retrofit on GitHub"
[okhttp]: https://github.com/square/okhttp "OkHttp on GitHub"
[gson]: https://github.com/google/gson "GSON on GitHub"
[ud]: https://www.urbandictionary.com "UrbanDictionary"
[ud-get]: http://api.urbandictionary.com/v0/define?term=azba "GET /define?term=azba"
[define-result]: https://gitlab.com/Elypia/elypiai/blob/master/urbandictionary/src/main/java/com/elypia/elypiai/urbandictionary/Definition.java
[define-result-set]: https://gitlab.com/Elypia/elypiai/blob/master/urbandictionary/src/main/java/com/elypia/elypiai/urbandictionary/DefineResult.java
[ud-service]: https://gitlab.com/Elypia/elypiai/blob/master/urbandictionary/src/main/java/com/elypia/elypiai/urbandictionary/UrbanDictionaryService.java
[ud-wrapper]: https://gitlab.com/Elypia/elypiai/blob/master/urbandictionary/src/main/java/com/elypia/elypiai/urbandictionary/UrbanDictionary.java
