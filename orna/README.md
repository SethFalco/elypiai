<div align="center">

# Elypiai - Orna
[![Matrix]][matrix-community] [![Discord]][discord-guild] [![Maven Central]][maven-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
</div>

## About
This is the [Orna] module for Elypiai.  
Orna is a RPG styled GPS/map game for mobile that can be played with everyone around
the world. This uses the API exposed by [Orna.Guide] to provide public information from
the community wiki.

The [Gradle]/[Maven] import strings can be found at the maven-central badge above!

## Getting Started
This is an unauthenticated API which only obtains publicaly available static data
available on the [Orna.Guide] such as data on skills, monsters, bosses, raids, etc.

**Gradle**
```gradle
ext {
    elypiaiVersion = "x.y.z"
}

dependencies {
    implementation "org.elypia.elypiai:orna:${elypiaiVersion}"
}
```

**Maven**
```xml
<dependency>
    <groupId>org.elypia.elypiai</groupId>
    <artifactId>orna</artifactId>
    <version>x.y.z</version>
</dependency>
```

To use the wrapper, just instantiate a `Orna` instance.

```java
class Main {

    public static void main(String[] args) {
        // Construct the wrapper object.
        Orna orna = new Orna();

        // Queue the request we want to make with a callback.
        orna.getMonsters().subscribe((response) -> {
            System.out.println(response.toString());
        });
    }
}
```

When you call `Orna#getMonsters()` you'll get a `Single<List<Monster>>`; you can call:
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
[Orna]: https://playorna.com/
[Orna.Guide]: https://orna.guide/
[Elypiai]: https://gitlab.com/Elypia/elypiai "Elypiai Repository"

[Matrix]: https://img.shields.io/matrix/elypia:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Maven Central]: https://img.shields.io/maven-central/v/org.elypia.elypiai/cleverbot "Download Shield"
[Docs]: https://img.shields.io/badge/docs-elypiai-blue.svg "Documentation Shield"
[Build]: https://gitlab.com/Elypia/elypiai/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/elypiai/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/donate-elypia-blueviolet "Donate Shield"
