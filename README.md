# Elypiai

[![](https://img.shields.io/maven-central/v/org.elypia.elypiai/osu)](https://search.maven.org/search?q=g:org.elypia.elypiai) [![](https://gitlab.com/SethFalco/elypiai/badges/main/pipeline.svg)](https://gitlab.com/SethFalco/elypiai)

## About

Elypiai is a collection of unofficial wrappers for web-based APIs. Each wrapper uses the same libraries and structure, so the interface is very consistent between them.

### Supported Services

* [Cleverbot](https://www.cleverbot.com/api/)
* [Companies House](https://developer.companieshouse.gov.uk/api/docs/)
* [Mojang](https://wiki.vg/Mojang_API)
* [Orna Guide](https://orna.guide/gameplay?show=16)
* [osu!](https://osu.ppy.sh/docs/index.html)
* [Path of Exile](https://www.pathofexile.com/developer/docs/api-resources)
* [RuneScape](http://runescape.wikia.com/wiki/Application_programming_interface)
* [Steam](https://steamcommunity.com/dev)
* [Urban Dictionary](https://api.urbandictionary.com/v0/define?term=api)
* [Yu-Gi-Oh! Prices](https://yugiohprices.docs.apiary.io/)
* [Weblate](https://docs.weblate.org/en/latest/api.html)

### Why a monorepo?

Rather than having separate repositories for Cleverbot4J, Orna4J, osu!4J, and so on, this creates a submodule as each wrapper has an almost identical structure. In the instance we want to do major alterations in design, it usually applies to all wrappers.

## Getting Started

### Import

Visit [Elypiai on Maven Central](https://search.maven.org/search?q=g:org.elypia.elypiai), and follow the instructions for your build system of choice to add the respective service wrapper to your project.

### Fetching Data

In this example we'll use the Urban Dictionary API, but every module in Elypiai works more or less the same.

```java
public class Main {

    public static void main(String[] args) {
        UrbanDictionary ud = new UrbanDictionary();
        fetchDefinitions(ud);
        fetchDefinitionsBatched(ud);
    }

    /**
     * Invoke a REST action through the service class. You can follow-up with
     * one of the following methods:
     * 
     * #subscribe — Asynchronous request, both the success, and failure
     *              consumers are optional.
     * #blockingGet — Synchronous (blocking) request that returns the result.
     */
    public void fetchDefinitions(UrbanDictionary ud) {
        ud.getDefinitions("foobar").subscribe(
            (result) -> System.out.println(result),
            Throwable::printStackTrace
        );

        DefineResult result = ud.getDefinitions("foobar").blockingGet();
    }

    /**
     * You can batch requests too.
     */
    public static void fetchDefinitionsBatched(UrbanDictionary ud) {
        var requests = Stream.of("foobar", "xkcd")
            .map(ud::getDefinitions)
            .map(Single::toObservable)
            .collect(Collectors.toList());

        var batch = Observable.zip(requests, (objects) ->
            Stream.of(objects).map((o) -> (DefineResult) o).collect(Collectors.toList())
        );

        batch.subscribe(
            (results) -> results.forEach(System.out::println)
        );
    }
}
```

### Read the Javadocs

All the wrappers include Javadocs, read them to learn what functions are available. If you're using a modern code editor or IDE, the code completion will display them as you access the service objects.
