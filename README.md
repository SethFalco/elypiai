# Elypiai [![Matrix]][matrix-community] [![Discord]][discord-guild] [![Maven Central]][maven-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
The [Gradle]/[Maven] import strings can be found at the maven-central badge above!

## About
Elypiai is a small and easy way, especially for new developers, to add ample functionality 
or integrations to any project.  

## Supported APIs
* [Cleverbot][cleverbot]
* [Orna Guide][orna]
* [osu!][osu]
* [Path of Exile][path-of-exile]
* [RuneScape][runescape]
* [Steam][steam]
* [UrbanDictionary][urbandictionary]
* [Yu-Gi-Oh! Prices][yugioh-prices]

## Getting Started
In this example we'll just use the osu! API to get things going.

First you need to construct the wrapper object for the API you want to access, for example.
Following this you can use the methods as you'd expect, each API method will have a few ways to make
the request:

* `complete()` - This will do a synchronous or blocking request and return an optional object.
* `queue(success, failure)` - This will do an asynchronous request, both the sucess, and failure consumers are optional.

```java
class Main {
 
    public static void main(String[] args) {
        // Construct the wrapper object.
        Osu osu = new Osu("{{api key}}");
        
        // It's strongly recommend to use #queue, #complete should only be used if neccasary.
        osu.getPlayer("SethX3").queue((player) -> {
            if (player.isEmpty()) {
                return; // If the player doesn't exist
            }
            
            Player player = player.get();
            // If the player was found
        });
        
        // There are also additional request type objects to manage requests better.
        RestLatch<Player> latch = new RestLatch<>();

        // Let's collect all the RestActions we want to perform in a RestLatch
        String[] usernames = {"SethX3", "Rheannon"};
                
        for (String username : usernames)
            latch.add(osu.getPlayer(username));
        
        // Perform all queued requests and perform the callback when all are finished.
        latch.queue((results) -> {
            for (Optional<Player> optPlayer : results) {
                if (player.isEmpty()) {
                    return; // If the player doesn't exist
                }
                
                Player player = player.get();
                // If the player was found
            }
        });
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
[cleverbot]: https://www.cleverbot.com/api/
[orna]: https://orna.guide/gameplay?show=16
[osu]: https://github.com/ppy/osu-api/wiki
[path-of-exile]: https://www.pathofexile.com/developer/docs/api-resources
[runescape]: http://runescape.wikia.com/wiki/Application_programming_interface
[steam]: https://steamcommunity.com/dev
[urbandictionary]: http://api.urbandictionary.com/v0/define?term=api
[yugioh-prices]: http://docs.yugiohprices.apiary.io/

[Matrix]: https://img.shields.io/matrix/elypia-general:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Maven Central]: https://img.shields.io/maven-central/v/org.elypia.elypiai/osu "Download Shield"
[Docs]: https://img.shields.io/badge/docs-elypiai-blue.svg "Documentation Shield"
[Build]: https://gitlab.com/Elypia/elypiai/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/elypiai/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/donate-elypia-blueviolet "Donate Shield"
