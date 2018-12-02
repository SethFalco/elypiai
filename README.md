## Elypiai [![Discord](https://discordapp.com/api/guilds/184657525990359041/widget.png)](https://discord.gg/hprGMaM) [![Bintray Download](https://api.bintray.com/packages/elypia/Elypiai/Elypiai/images/download.svg)](https://bintray.com/elypia/Elypiai/Elypiai/_latestVersion) [![Documentation](https://img.shields.io/badge/Docs-Elypiai-blue.svg)](https://elypiai.elypia.com/) [![GitLab Pipeline Status](https://gitlab.com/Elypia/Elypiai/badges/master/pipeline.svg)](https://gitlab.com/Elypia/Elypiai/commits/master) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/51814ca2e04c46809f97634601595741)](https://www.codacy.com/app/Elypia/Elypiai?utm_source=gitlab.com&amp;utm_medium=referral&amp;utm_content=Elypia/Elypiai&amp;utm_campaign=Badge_Grade) [![Codacy Badge](https://api.codacy.com/project/badge/Coverage/51814ca2e04c46809f97634601595741)](https://www.codacy.com/app/Elypia/Elypiai?utm_source=gitlab.com&utm_medium=referral&utm_content=Elypia/Elypiai&utm_campaign=Badge_Coverage)

### Importing
#### [Gradle](https://gradle.org/)
```gradle
implementation 'com.elypia.elypiai:{ARTIFACT}:{VERSION}'
```

#### [Maven](https://maven.apache.org/)
```xml
<dependency>
  <groupId>com.elypia.elypiai</groupId>
  <artifactId>{ARTIFACT}</artifactId>
  <version>{VERSION}</version>
</dependency>
```

### **Artifacts**
| Artifact                                                                         | Description                                                                                                                 |
|----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| `common`                                                                         | Code shared between all sup-projects, an abstraction of [Retrofit](https://square.github.io/retrofit/) for making requests. |
| [`amazon-product-api`](https://affiliate-program.amazon.com/)                    | Generate links and monetize with the Amazon Affiliate Program.                                                              |
| [`cleverbot`](https://www.cleverbot.com/api/)                                    | Interact with Cleverbot, the AI that learns from people.                                                                    |
| [`nanowrimo`](https://nanowrimo.org/wordcount_api)                               | Query data or create a application or plugin to push word counts!                                                           |
| [`osu`](https://github.com/ppy/osu-api/wiki)                                     | Query data on users and plays with a ready made notifier.                                                                   |
| [`path-of-exile`](https://www.pathofexile.com/developer/docs/api-resources)      | Query user data, stashes, league scores and ladders.                                                                        |
| [`runescape`](http://runescape.wikia.com/wiki/Application_programming_interface) | Get information on users, plus a notifier for significant events.                                                           |
| [`steam`](https://steamcommunity.com/dev)                                        | Query user information and player's libraries.                                                                              |
| [`twitch`](https://dev.twitch.tv/docs)                                           | Get information on Twitch users and streams, with notifier.                                                                 |
| [`urbandictionary`](http://api.urbandictionary.com/v0/define?term=api)           | Search Urban Dictionary and return the top voted or random definition.                                                      |
| [`yugioh-prices`](http://docs.yugiohprices.apiary.io/)                           | Get information on Yu-Gi-Oh! cards and download or display high quality images.                                             |

### About
Elypiai is a centralised repository with libraries for various different web APIs online. Wraps are done beyond obtaining data and mapping it to objects to ensure you get the most of the library out of the box so as a developer you can stick to integrating. This includes making utility methods and notifiers where applicable. Simple examples of this could be the in the RuneScape module which includes methods to convert XP to a level and vice versa, or the osu! API which includes a means to webscrap a users profile picture.

This is an easy way, especially for new developers, to add ample functionality or integrations to any project.

### Example
This project is heavily used by Alexis, an open-source Discord bot also. Please check out her modules to see examples of each wrap.

### Support
Should any problems occur, come visit us over on [Discord](https://discord.gg/hprGMaM)! We're always around and there are ample developers that would be willing to help; if it's a problem with the library itself then we'll make sure to get it sorted.

This project is _heavily_ relied on by [Alexis, the Discord bot](https://discordapp.com/oauth2/authorize?client_id=230716794212581376&scope=bot), and is infact why it exists!
