# Elypiai [![Discord][discord-members]][discord] [![Download][bintray-download]][bintray] [![Documentation][docs-shield]][docs] [![GitLab Pipeline Status][gitlab-build]][gitlab] [![Coverage][gitlab-coverage]][gitlab] 
The [Gradle][gradle]/[Maven][maven] import string can be found at the Download badge above!

## About
Elypiai is a small and easy way, especially for new developers, to add ample functionality 
or integrations to any project.
This is small library made of primarly 3 types of modules which come together to make a small and clean

### Common
Common modules are shared resources that all the wrappers can pool from such as HttpClient, or
custom deserializers and adapters. They make it easier to reuse code between wraps as well as
improve performance by managing singleton instances of special objects.

### Extension
Extensions are a way to add additional functionality into of wrappers generically, so once
a extension is made it can be used by any wrapper ideally. The best example of this is the RedisExtension
which takes a time-to-live and caches responses for the specified time. This is great for cross-application
caching and can massively save bandwidth and reduce API calls.

### Wrappers
Wrappers are the actual wrapper around a particular service, this is what users should be interfacing to
call the web APIs. Some wraps to include minimal utility methods as well as appropriate for the respective
service, such as mathematical functions or web scrapping methods to get data the API may not.

## Supported APIs
* [Cleverbot][cleverbot]
* [NaNoWriMo][nanowrimo]
* [osu!][osu]
* [Path of Exile][path-of-exile]
* [RuneScape][runescape]
* [Steam][steam]
* [Twitch][twitch]
* [UrbanDictionary][urbandictionary]
* [Yu-Gi-Oh! Prices][yugioh-prices]

> Elypiai is pretty easy to work with so we can always add more wrappers, or you could contibute
> yourself! Or better yet, or if you want to keep your wrap to yourself just depend on the `common` 
> and extension modules and you'll be good to go, sharing resources, a similar interface, lots of reusable components.

## Support
Should any problems occur, come visit us over on [Discord][discord]! We're always around and there are
ample developers that would be willing to help; if it's a problem with the library itself then we'll
make sure to get it sorted.

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
