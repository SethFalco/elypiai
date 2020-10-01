<div align="center">

# Elypiai - osu!
[![Matrix]][matrix-community] [![Discord]][discord-guild] [![Maven Central]][maven-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
</div>

## About
This is the [osu!] module for Elypiai.  
[osu!] is a free and open-source and cross-platform rhthym game,
[repository here][osu-code].
It has a public API available to access data available on players, beatmaps, 
and multiplayer lobbies.

The [Gradle]/[Maven] import strings can be found at the maven-central badge above!

## Getting Started
Before you can use the API, you must head over to the osu! website; create
an account if you don't have one already, then create an application
[here][osu-application].

**Gradle**
```gradle
ext {
    elypiaiVersion = "x.y.z"
}

dependencies {
    implementation "org.elypia.elypiai:osu:${elypiaiVersion}"
}
```

**Maven**
```xml
<dependency>
    <groupId>org.elypia.elypiai</groupId>
    <artifactId>osu</artifactId>
    <version>x.y.z</version>
</dependency>
```

To use the wrapper, just instantiate a `Osu` instance.

```java
class Main {

    public static void main(String[] args) {
        // Construct the wrapper object.
        Osu osu = new Osu();

        // Queue the request we want to make with a callback.
        osu.getPlayer(4185808).subscribe((response) -> {
            System.out.println(response.getUsername());
        });
    }
}
```

When you call `Osu#getPlayer()` you'll get a `Maybe<Player>`; you can call:
* `blockingGet()` - This will do a synchronous or blocking request.
* `subscribe(success, failure)` - This will do an asynchronous request, both the success, and failure consumers are optional.

For more information, please see [Elypiai].

## Support
If you have any questions or need support, come visit us on Matrix! We're always around and there are
ample developers that would be willing to help; if it's a problem with the library itself then we'll
make sure to get it sorted.

[matrix-community]: https://matrix.to/#/+elypia:matrix.org "Matrix Invite"
[discord-guild]: https://discord.gg/hprGMaM "Discord Invite"
[maven-page]: https://search.maven.org/artifact/org.elypia.elypiai/cleverbot "Maven Central"
[documentation]: https://elypia.gitlab.io/elypiai/com/elypia/elypiai/cleverbot/package-summary.html "Documentation"
[gitlab]: https://gitlab.com/Elypia/elypiai/commits/master "Repository on GitLab"
[elypia-donate]: https://elypia.org/donate "Donate to Elypia"
[Gradle]: https://gradle.org/ "Depend via Gradle"
[Maven]: https://maven.apache.org/ "Depend via Maven"
[osu!]: https://osu.ppy.sh/home
[osu-code]: https://github.com/ppy/osu
[osu-api]: https://osu.ppy.sh/docs/index.html
[osu-application]: https://osu.ppy.sh/home/account/edit#oauth
[Elypiai]: https://gitlab.com/Elypia/elypiai "Elypiai Repository"

[Matrix]: https://img.shields.io/matrix/elypia:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Maven Central]: https://img.shields.io/maven-central/v/org.elypia.elypiai/cleverbot "Download Shield"
[Docs]: https://img.shields.io/badge/docs-elypiai-blue.svg "Documentation Shield"
[Build]: https://gitlab.com/Elypia/elypiai/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/elypiai/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/donate-elypia-blueviolet "Donate Shield"
