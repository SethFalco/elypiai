# Utopiai
## Importing to your project
### Download
You can download the this by getting the [Utopiai.jar file](https://github.com/SethX3/Utopiai/raw/master/Utopiai.jar) in the Git and importing it as an External JAR in your IDE.
### Mavens/Gradle (or Other)
[Jitpack](https://jitpack.io/#SethX3/Utopiai/master) can compile and provide this project a dependency however this does not come with sources or javadocs.

## About
During my time programming I've used many APIs, primiarly in [Alexis](https://github.com/SethX3/Alexis), my Discord chatbot. All APIs I've used have been wrapped into a single seperate project for easy importing to any other project they may be needed in.

### Wrapped APIs / Utilities

#### APIs
[Amazon Product API](https://affiliate-program.amazon.com/gp/advertising/api/detail/main.html) - Search Amazon for products and earn commision on purchases. <br>
[Cleverbot](https://www.cleverbot.com/api/) - Query Cleverbot and hold a conversation with it. You can have up to 5,000 calls/month for free. <br>
[Bing Cognative Services](https://www.microsoft.com/cognitive-services) - Search the internet for websites or images, spell check text or get text analytics. <br>
[YouTube Data](https://console.developers.google.com/) - Search YouTube for videos of playlists. <br>
[Google LinkShortener](https://console.developers.google.com/) - Shorten a URL using Google's link shortening service. <br>
[SightEngine](https://sightengine.com/) - Nudity Detection, recieve a score on how safe an image is. <br>
[MyAnimeList](https://myanimelist.net/modules.php?go=api) - Search MyAnimeList and get information on an anime or anime movie. <br>
[Urban Dictionary](http://api.urbandictionary.com/v0/define?term=api) - Search Urban Dictionary and return the top voted or random definition. <br>
[Twitch](https://dev.twitch.tv/docs) - Get information on Twitch Streamers, has premade Notifier via event listeners. <br>
[Steam](https://steamcommunity.com/dev) - Steam API get user information and user game libraries. <br>
[League of Legends](https://developer.riotgames.com/) - Currently only provides current Free Champion Rotation. <br>
[osu!](https://osu.ppy.sh/p/api) - Get info on players for all modes and beatmaps. Also contains pre-made notifier via event listeners. <br>
[RuneScape](http://runescape.wikia.com/wiki/Application_programming_interface) - Get information on users, plus a notifier for significant events such as 200m XP or Level 99. <br>
[Yu-Gi-Oh! Prices](http://docs.yugiohprices.apiary.io/) - Get information on Yu-Gi-Oh! cards and download card images. <br>
[StackExchange](https://api.stackexchange.com/) - Query StackOverflow with a question and tags and return the top result. <br>

###### Many of these require you sign up for an API key.

#### Utilities
[Bi-Directional Draconic Translator](http://draconic.twilightrealm.com/) - Translate from English to Draconic and vice versa. <br>
[Pun Generator](http://www.sugartoast.com/game/terrible-pun-generator) - Generates a random pun. <br>
SUtils - Basic Utility class. <br>
HttpRequest Helper - Helper Class for making Http Requests. <br>
MySQL Wrapper - Make creating requests to a MySQL database relativly easier. <br>
Table Builder - Grab a list of any object and generate a table.

###### If applicable - linked to website used to function.

##### Please visit the Wiki for documentation for Utopiai or the website as linked for info.

### Required Dependencies
[org.json](https://mvnrepository.com/artifact/org.json/json/20160810) - For parsing JSON <br>
[JSoup](https://mvnrepository.com/artifact/org.jsoup/jsoup/1.10.2) - For webscrapping and parsing XML <br>
[Apache Commons Lang](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.5) <br>
[Apache Commons Collections](https://mvnrepository.com/artifact/org.apache.commons/commons-collections4/4.1) <br>
[Apache Commons HttpClient](https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.5.3)
##### Please note that the provided .jar does _NOT_ come with dependencies.