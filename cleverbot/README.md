# Elypiai Cleverbot Module [![Matrix]][matrix-community] [![Discord]][discord-guild] [![Bintray]][bintray-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
The [Gradle]/[Maven] import string can be found at the bintray badge above!

## About
This is the [Cleverbot] module for Elypiai.
Cleverbot is an AI powered chatbot that users can talk to on the Cleverbot [website]
or via the Cleverbot [API][Cleverbot].

## Getting Started
**Heads Up: The Cleverbot API is a paid service.**

First you'll have to go to the Cleverbot API site and make an account if you haven't already.  
Following this, you may also have to purchase a [topup package][Cleverbot].  

Once you're ready, you can depend on the Cleverbot module of Elypiai.

**Gradle**
```gradle
ext {
    elypiaiVersion = "x.y.z"
}

dependencies {
    implementation "org.elypia.elypiai:cleverbot:${elypiaiVersion}"
}
```

**Maven**
```xml
<dependency>
    <groupId>org.elypia.elypiai</groupId>
    <artifactId>cleverbot</artifactId>
    <version>x.y.z</version>
</dependency>
```

To use the wrapper, just instantiate the `Cleverbot` instance with your API key
that you'll have obtained from the Cleverbot API website.


```java
class Main {
 
    public static void main(String[] args) {
        // Construct the wrapper object.
        Cleverbot cb = new Cleverbot("{API_KEY}");
        
        // Queue the request we want to make with a callback.
        cb.say("Hello").queue((response) -> {
            System.out.println(response.getOutput()); // Response to "Hello"
            System.out.println(response.getCs()); // Cleverbot state, to continue the conversation
        });
    }
}
```

When you call `Cleverbot#say()` you'll get a `RestAction<CleverResponse>`; you can call:
* `complete()` - This will do a synchronous or blocking request and return an optional object.
* `completeGet()` - This will do a synchronous or blocking request, and do get for you.
* `queue(success, failure)` - This will do a asynchronous request, both the sucess, and failure consumers are optional.

For more information, please see [Elypiai].

## Support
Should any problems occur, come visit us over on Discord! We're always around and there are
ample developers that would be willing to help; if it's a problem with the library itself then we'll
make sure to get it sorted.

[matrix-community]: https://matrix.to/#/+elypia:matrix.org "Matrix Invite"
[discord-guild]: https://discord.gg/hprGMaM "Discord Invite"
[bintray-page]: https://bintray.com/elypia/elypiai "Bintray Latest Version"
[documentation]: https://elypia.gitlab.io/elypiai/com/elypia/elypiai/cleverbot/package-summary.html "Elypiai - Cleverbot Documentation"
[gitlab]: https://gitlab.com/Elypia/elypiai/commits/master "Repository on GitLab"
[elypia-donate]: https://elypia.org/donate "Donate to Elypia"
[Gradle]: https://gradle.org/ "Depend via Gradle"
[Maven]: https://maven.apache.org/ "Depend via Maven"
[Cleverbot]: https://www.cleverbot.com/api/ "Cleverbot API"
[website]: https://www.cleverbot.com/ "Cleverbot Website"
[Elypiai]: https://gitlab.com/Elypia/elypiai "Elypiai Repository"

[Matrix]: https://img.shields.io/matrix/elypia-general:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Bintray]: https://img.shields.io/bintray/v/elypia/elypiai/cleverbot "Bintray Download Shield"
[Docs]: https://img.shields.io/badge/Docs-Elypiai-blue.svg "Elypiai Documentation Shield"
[Build]: https://gitlab.com/Elypia/elypiai/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/elypiai/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/Elypia-Donate-blueviolet "Donate Shield"
