# Elypiai [![Discord][discord-members]][discord] [![Download][bintray-download]][bintray] [![Documentation][docs-shield]][docs] [![GitLab Pipeline Status][gitlab-build]][gitlab] [![Coverage][gitlab-coverage]][gitlab] 
The [Gradle][gradle]/[Maven][maven] import string can be found at the Download badge above!

## Artifacts
| Artifact                             | Description                                                                     |
|--------------------------------------|---------------------------------------------------------------------------------|
| [`cleverbot`][cleverbot]             | Interact with Cleverbot, the AI that learns from people.                        |
| [`nanowrimo`][nanowrimo]             | Query data or create a application or plugin to push word counts!               |
| [`osu`][osu]                         | Query data on users and plays with a ready made notifier.                       |
| [`path-of-exile`][path-of-exile]     | Query user data, stashes, league scores and ladders.                            |
| [`runescape`][runescape]             | Get information on users, plus a notifier for significant events.               |
| [`steam`][steam]                     | Query user information and player's libraries.                                  |
| [`twitch`][twitch]                   | Get information on Twitch users and streams, with notifier.                     |
| [`urbandictionary`][urbandictionary] | Search Urban Dictionary and return the top voted or random definition.          |
| [`yugioh-prices`][yugioh-prices]     | Get information on Yu-Gi-Oh! cards and download or display high quality images. |

## About
Elypiai is a centralised repository with libraries for various different web APIs online. Wraps are done beyond obtaining data and mapping it to objects to ensure you get the most of the library out of the box so as a developer you can stick to integrating. This includes making utility methods and notifiers where applicable. Simple examples of this could be the in the RuneScape module which includes methods to convert XP to a level and vice versa, or the osu! API which includes a means to webscrap a users profile picture.

This is an easy way, especially for new developers, to add ample functionality or integrations to any project.

## Support
Should any problems occur, come visit us over on [Discord][discord]! We're always around and there are ample developers that would be willing to help; if it's a problem with the library itself then we'll make sure to get it sorted.

This project is _heavily_ relied on by [Alexis, the Discord bot][alexis], and is infact why it exists!

[discord]: https://discord.gg/hprGMaM "Discord Invite"
[discord-members]: https://discordapp.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[bintray]: https://bintray.com/elypia/Elypiai "Bintray Latest Version"
[bintray-download]: https://api.bintray.com/packages/elypia/Elypiai/common/images/download.svg "Bintray Download Shield"
[docs]: https://elypiai.elypia.com/ "Commandler Documentation"
[docs-shield]: https://img.shields.io/badge/Docs-Elypiai-blue.svg "Commandler Documentation Shield"
[gitlab]: https://gitlab.com/Elypia/elypiai/commits/master "Repository on GitLab"
[gitlab-build]: https://gitlab.com/Elypia/elypiai/badges/master/pipeline.svg "GitLab Build Shield"
[gitlab-coverage]: https://gitlab.com/Elypia/elypiai/badges/master/coverage.svg "GitLab Coverage Shield"

[gradle]: https://gradle.org/ "Depend via Gradle"
[maven]: https://maven.apache.org/ "Depend via Maven"

[cleverbot]: https://www.cleverbot.com/api/
[nanowrimo]: https://nanowrimo.org/wordcount_api
[osu]: https://github.com/ppy/osu-api/wiki
[path-of-exile]: https://www.pathofexile.com/developer/docs/api-resources
[runescape]: http://runescape.wikia.com/wiki/Application_programming_interface
[steam]: https://steamcommunity.com/dev
[twitch]: https://dev.twitch.tv/docs
[urbandictionary]: http://api.urbandictionary.com/v0/define?term=api
[yugioh-prices]: http://docs.yugiohprices.apiary.io/

[alexis]: https://discordapp.com/oauth2/authorize?client_id=230716794212581376&scope=bot "Invite Alexis"
