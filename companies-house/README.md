<div align="center">

# Elypiai - Companies House
[![Matrix]][matrix-community] [![Discord]][discord-guild] [![Maven Central]][maven-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
</div>

## About
This is the [Companies House] module for Elypiai.
Companies House are the governing body around companies in the United Kingdom.
They provide an official API to allow programatic access to public information
for all companies.

The [Gradle]/[Maven] import strings can be found at the maven-central badge above!

## Getting Started
First you'll have to go to the Companies House API site and make an account if you haven't already.
You can then make an application and receive an API key which will be used to authorize requests,
you may need to go to your application and whitelist your IP for making API requests.

Once you're ready, you can depend on the Companies House module of Elypiai.

**Gradle**
```gradle
ext {
    elypiaiVersion = "x.y.z"
}

dependencies {
    implementation "org.elypia.elypiai:companies-house:${elypiaiVersion}"
}
```

**Maven**
```xml
<dependency>
    <groupId>org.elypia.elypiai</groupId>
    <artifactId>companies-house</artifactId>
    <version>x.y.z</version>
</dependency>
```

To use the wrapper, just instantiate the `CompaniesHouse` instance with your API key
that you'll have obtained from the Companies House website.

```java
import org.elypia.elypiai.companieshouse.CompaniesHouse;

class Main {

    public static void main(String[] args) {
        // Construct the wrapper object.
        CompaniesHouse ch = new CompaniesHouse("{API_KEY}");

        // Queue the request we want to make with a callback.
        ch.getRegisteredOfficeAddress("12203025").subscribe((response) -> {
           System.out.println(response.getAddressLine1());
           System.out.println(response.getAddressLine2());
        });
    }
}
```

For more information, please see [Elypiai].

## Contributing
As the API returns data that's on public record, it contains a lot of personal information;
we don't to embed that in the respository. As a result the test responses for
unit testing should only ever contain responses for [Elypia CIC (12203025)], or
of the contributor or contributor's company.

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
[Companies House]: https://developer.companieshouse.gov.uk/api/docs/
[Elypiai]: https://gitlab.com/Elypia/elypiai "Elypiai Repository"
[Elypia CIC (12203025)]: https://beta.companieshouse.gov.uk/company/12203025

[Matrix]: https://img.shields.io/matrix/elypia:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Maven Central]: https://img.shields.io/maven-central/v/org.elypia.elypiai/cleverbot "Download Shield"
[Docs]: https://img.shields.io/badge/docs-elypiai-blue.svg "Documentation Shield"
[Build]: https://gitlab.com/Elypia/elypiai/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/elypiai/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/donate-elypia-blueviolet "Donate Shield"
