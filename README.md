# Elypiai [![Matrix]][matrix-community] [![Discord]][discord-guild] [![Maven Central]][maven-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
The [Gradle]/[Maven] import strings can be found at the maven-central badge above!

## About
Elypiai is a small and easy way, especially for new developers, to add ample functionality 
or integrations to any project.  

We wrap web APIs that don't provide an official binding for Java and make a module
for each one with a consistent interface between each.

## Supported APIs
* [Cleverbot] - Complete
* [Companies House] - WIP
* [Orna Guide] - WIP
* [osu!] - WIP
* [Path of Exile] - Complete
* [RuneScape] - WIP
* [Steam] - WIP
* [Urban Dictionary] - Complete
* [Yu-Gi-Oh! Prices] - WIP

You can check the issues to see pending wrappers.

### Why are these wraps in a single repository?
Rather than making a Cleverbot4J, Orna4J, osu!4J, etc project we've opted for a submodule
approach. The reason for this is because all submodules in this repository has an almost
identical structure to them, and when we want to make major alterations to how we want
to make requests, we often want to change it for all of our wrappers. 
Please visit the appropriate README of the module you're interested in.

## Getting Started
In this example we'll just use the osu! API to get things going.

First you need to construct the wrapper object for the API you want to access, for example.
Following this you can use the methods as you'd expect, each API method will have a few ways to make
the request:

* `blockingGet()` - This will do a synchronous or blocking request and return an optional object.
* `subscribe(success, failure)` - This will do an asynchronous request, both the success, and failure consumers are optional.

```java
public class Main {
 
    private final Osu osu;
    
    /**
    * Start by instantiating the wrapper to use, then call
    * a request method. Wrappers will accept whatever they need
    * in their constructor and can have their OkHttpClient instance
    * updated to accommodate custom interceptors.
    */
    public static void main(String[] args) {
        osu = new Osu("{{api key}}");
    }
    
    /**
    * RxJava is pretty flexible; you can call #subscribe
    * with callbacks to handle the result asynchronously, 
    * all the parameters are optional, or call #blockingGet 
    * to handle it synchronously.
    */
    public static void simpleExample() {        
        osu.getPlayer("SethX3").subscribe(
            (player) -> System.out.println(player),
            (ex) -> ex.printStacktrace(),
            () -> System.out.println("That player didn't exist.")
        );
        
        Player player = osu.getPlayer("SethX3").blockingGet();
    }
    
    /**
    * It's even possible to perform batch requests!
    */
    public static void batchExample() {
        List<Observable<Player>> requests = Stream.of("SethX3", "Rheannon")
            .map(osu::getPlayer)
            .map(Maybe::toObservable)
            .collect(Collectors.toList());
                                                   
        Observable<List<Player>> singlePlayers = Observable.zip(requests, (objects) ->
            Stream.of(objects).map((o) -> (Player)o).collect(Collectors.toList())
        );
                
        singlePlayers.subscribe((players) -> players.forEach(System.out::println));
    }
}
```

## Support
Should any problems occur, come visit us over on Discord! We're always around and there are
ample developers that would be willing to help; if it's a problem with the library itself then we'll
make sure to get it sorted.

[matrix-community]: https://matrix.to/#/+elypia:matrix.org "Matrix Invite"
[discord-guild]: https://discord.gg/hprGMaM "Discord Invite"
[maven-page]: https://search.maven.org/search?q=g:org.elypia.elypiai "Maven Central"
[documentation]: https://elypia.gitlab.io/elypiai "Documentation"
[gitlab]: https://gitlab.com/Elypia/elypiai/commits/master "Repository on GitLab"
[elypia-donate]: https://elypia.org/donate "Donate to Elypia"
[Gradle]: https://gradle.org/ "Depend via Gradle"
[Maven]: https://maven.apache.org/ "Depend via Maven"
[Cleverbot]: https://www.cleverbot.com/api/
[Companies House]: https://developer.companieshouse.gov.uk/api/docs/
[Orna Guide]: https://orna.guide/gameplay?show=16
[osu!]: https://github.com/ppy/osu-api/wiki
[Path of Exile]: https://www.pathofexile.com/developer/docs/api-resources
[RuneScape]: http://runescape.wikia.com/wiki/Application_programming_interface
[Steam]: https://steamcommunity.com/dev
[Urban Dictionary]: http://api.urbandictionary.com/v0/define?term=api
[Yu-Gi-Oh! Prices]: http://docs.yugiohprices.apiary.io/

[Matrix]: https://img.shields.io/matrix/elypia:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Maven Central]: https://img.shields.io/maven-central/v/org.elypia.elypiai/osu "Download Shield"
[Docs]: https://img.shields.io/badge/docs-elypiai-blue.svg "Documentation Shield"
[Build]: https://gitlab.com/Elypia/elypiai/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/elypiai/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/donate-elypia-blueviolet "Donate Shield"
