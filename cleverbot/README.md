# Elypiai: Cleverbot

[![](https://img.shields.io/maven-central/v/fun.falco.elypiai/cleverbot)](https://search.maven.org/artifact/fun.falco.elypiai/cleverbot) [![](https://gitlab.com/SethFalco/elypiai/badges/main/pipeline.svg)](https://gitlab.com/SethFalco/elypiai)

## About

The [Cleverbot](https://www.cleverbot.com/api/) module of Elypiai. Cleverbot is an AI-powered chatbot that users can talk to.

## Getting Started

### Register for Cleverbot

First you must go to the Cleverbot API site and create an account. Following this, you'll have to purchase one of their top-up packages.

### Import

Visit [Elypiai on Maven Central](https://search.maven.org/artifact/fun.falco.elypiai/cleverbot), and follow the instructions for your build system of choice to add the Cleverbot wrapper to your project.


### Fetching Data

Read the general documentation for [Elypiai](../README.md), for an overview of how every supported service can be consumed.

```java
public class Main {

    public static void main(String[] args) {
        Cleverbot cb = new Cleverbot("{API_KEY}");
        chat(cb);
    }

    public void chat(Cleverbot cb) {
        cb.say("Hello").subscribe(
          (result) -> {
              // Response from Cleverbot
              String output = result.getOutput();

              // Cleverbot State, can be given as an argument in the next
              // invocation of Cleverbot#say to continue the conversation.
              String cs = result.getCs();
          },
          Throwable::printStackTrace;
        );
    }
}
```

### Read the Javadocs

Read the Javadocs to learn what functions are available. If you're using a modern code editor or IDE, the code completion will display them as you access the service objects.
