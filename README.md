# Elypiai [![Matrix]][matrix-community] [![Discord]][discord-guild] [![Bintray]][bintray-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
The [Gradle][gradle]/[Maven][maven] import string can be found at the Download badge above!

## About
Elypiai is a small and easy way, especially for new developers, to add ample functionality 
or integrations to any project.  
This is small library made of primarily 3 types of modules which come together to make a small, common, and 
clean interface when creating wrappers.

### Common
Common modules are shared resources that all the wrappers can pool from such as the HTTP client, or
custom deserializers and adapters. They make it easier to reuse code between wraps as well as
improve performance by managing singleton instances of special objects.

### Extension
Extensions are a way to add additional functionality to wrappers generically, so once
a extension is made it can be used by any wrapper. The best example of this is the RedisExtension
which takes a time-to-live and caches responses for the specified time. This is great for cross-application
caching and can massively save bandwidth and reduce API calls.

### Wrappers
Wrappers are the actual wrapper around a service, this is what users should be interfacing with to
call the web APIs. Some wraps include utility methods as appropriate for the respective
service, such as mathematical functions or web scrapping methods to get data the API may not.

## Supported APIs
* [Cleverbot][cleverbot]
* [Orna Guide][orna]
* [osu!][osu]
* [Path of Exile][path-of-exile]
* [RuneScape][runescape]
* [Steam][steam]
* [UrbanDictionary][urbandictionary]
* [Yu-Gi-Oh! Prices][yugioh-prices]

> Elypiai is pretty easy to work with so we can always add more wrappers, or you could contribute
> yourself. If you want to can also keep your wrap to yourself just depend by just depending
> on the `common` and `extension` modules appropriate for your applications with all of the benfits
> including: sharing resources, a common interface, and lots of reusable components.  
>
> You can see more in the contribution guide which _very_ breifly runs through creating a wrapper.

## Support
Should any problems occur, come visit us over on Discord! We're always around and there are
ample developers that would be willing to help; if it's a problem with the library itself then we'll
make sure to get it sorted.

[matrix-community]: https://matrix.to/#/+elypia:matrix.org "Matrix Invite"
[discord-guild]: https://discord.gg/hprGMaM "Discord Invite"
[bintray-page]: https://bintray.com/elypia/elypiai "Bintray Latest Version"
[documentation]: https://elypiai.elypia.org/ "Commandler Documentation"
[gitlab]: https://gitlab.com/Elypia/elypiai/commits/master "Repository on GitLab"
[elypia-donate]: https://elypia.org/donate "Donate to Elypia"
[gradle]: https://gradle.org/ "Depend via Gradle"
[maven]: https://maven.apache.org/ "Depend via Maven"
[cleverbot]: https://www.cleverbot.com/api/
[orna]: https://orna.guide/gameplay?show=16
[osu]: https://github.com/ppy/osu-api/wiki
[path-of-exile]: https://www.pathofexile.com/developer/docs/api-resources
[runescape]: http://runescape.wikia.com/wiki/Application_programming_interface
[steam]: https://steamcommunity.com/dev
[urbandictionary]: http://api.urbandictionary.com/v0/define?term=api
[yugioh-prices]: http://docs.yugiohprices.apiary.io/

[Matrix]: https://img.shields.io/matrix/elypia-general:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discordapp.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Bintray]: https://api.bintray.com/packages/elypia/elypiai/common-core/images/download.svg "Bintray Download Shield"
[Docs]: https://img.shields.io/badge/Docs-Elypiai-blue.svg "Commandler Documentation Shield"
[Builds]: https://gitlab.com/Elypia/elypiai/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/elypiai/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/Elypia-Donate-blueviolet "Donate Shield"
