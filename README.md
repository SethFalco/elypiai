## Elypiai
[![Bintray Download](https://api.bintray.com/packages/elypia/Elypiai/Elypiai/images/download.svg)](https://bintray.com/elypia/Elypiai/Elypiai/_latestVersion)
[![GitLab Pipeline Status](https://gitlab.com/Elypia/Elypiai/badges/master/pipeline.svg)](https://gitlab.com/Elypia/Elypiai/commits/master)
[![GitLab Coverage Report](https://gitlab.com/Elypia/Elypiai/badges/master/coverage.svg)](https://gitlab.com/Elypia/Elypiai/commits/master)
[![Discord](https://discordapp.com/api/guilds/184657525990359041/widget.png)](https://discord.gg/hprGMaM)

### Importing
#### [Gradle](https://gradle.org/)
```gradle
implementation 'com.elypia:elypiai:{VERSION}'
```

#### [Maven](https://maven.apache.org/)
```xml
<dependency>
  <groupId>com.elypia</groupId>
  <artifactId>elypiai</artifactId>
  <version>{VERSION}</version>
</dependency>
```

### About
Elypiai is a all-in-one utility library which provides an interface to communicate with many online services such as YouTube, Twitch, and Steam, or even games like RuneScape, or osu!. We've made sure to wrap API's beyond just getting the data, with notifiers for significant events or static utility functions. It also inclues miscellaneous local functionality like a Brainfuck intepreter, an `enum` of ready made regular expressions for common matches, and other basic utilities that may come in handy for anyone, especially new developers.

Elypiai is the dependency away from adding ample functionality to any project.

#### APIs and Utilities
Module | About | Authentication
------ | ----- | --------------
[Amazon Product API](https://affiliate-program.amazon.com/gp/advertising/api/detail/main.html) | Generate links and monetize with the [Amazon Affiliate Program](https://affiliate-program.amazon.com/). | ✔
[Bing Cognative Services](https://www.microsoft.com/cognitive-services) | Search for sites and images, spell check, and text analytics. | ✔
[Brainfuck Interpretter](https://en.wikipedia.org/wiki/Brainfuck) | Interpret brainfuck to a `byte[]` or `String`, compatible with input. | ✖
[Cleverbot](https://www.cleverbot.com/api/) | Chat with cleverbot! | ✔
[Elypian Script Notation](https://gitlab.com/Elypia/Elyversity/wikis/Elypian-Script-Notation) | Create dynamic text following a set of rules dictated in a single `String`. | ✖
[Google LinkShortener](https://console.developers.google.com/) | Shorten a URL and query information on existing ones. | ✔
[MyAnimeList](https://myanimelist.net/modules.php?go=api) | Get information on mangas and animes as they appear on MyAnimeList. | ✔
[NaNoWriMo](https://nanowrimo.org/wordcount_api) | Get scores or create applications or plugins to push word counts for users! | ✖
[osu!](https://osu.ppy.sh/p/api) | Call user and play data, with notifier for significant events. | ✔
[Path of Exile](https://www.pathofexile.com/developer/docs/api-resources) | User data, stashes (items), league scores and ladders. | ✖
[Regex](https://regex101.com/) | `Enum` of regexs ready to validate or parse any common data such as email addresses or API keys. | ✖
[RuneScape](http://runescape.wikia.com/wiki/Application_programming_interface) | Get information on users, plus a notifier for significant events. | ✖
[SightEngine](https://sightengine.com/) | Image detection, check for celebrities, nudity or otherwise inappropriate images. | ✔
[StackExchange](https://api.stackexchange.com/) | Query StackOverflow with a question and optional tags. | ✔
[Steam](https://steamcommunity.com/dev) | Steam API get user information and user game libraries. | ✔
[Twitch](https://dev.twitch.tv/docs) | Get information on Twitch users and streams, with notifier. | ✔
[Urban Dictionary](http://api.urbandictionary.com/v0/define?term=api) | Search Urban Dictionary and return the top voted or random definition. | ✖
[Yu-Gi-Oh! Prices](http://docs.yugiohprices.apiary.io/) | Get information on Yu-Gi-Oh! cards and download card images. | ✖
[YouTube Data](https://console.developers.google.com/) | Search YouTube for videos, playlists or channels. | ✔

### Support
Should any problems occur, come visit us over on [Discord](https://discord.gg/hprGMaM)! We're always around and there are ample developers that would be willing to help; if it's a problem with the library itself then we'll make sure to get it sorted.

This project is _heavily_ relied on by [Alexis, the Discord bot](https://discordapp.com/oauth2/authorize?client_id=230716794212581376&scope=bot). Feel free to check her out or join our guild so you can see Elypiai in action.