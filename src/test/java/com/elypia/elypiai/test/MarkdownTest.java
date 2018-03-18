package com.elypia.elypiai.test;

import org.junit.jupiter.api.Test;

import static com.elypia.elypiai.utils.Markdown.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkdownTest {

    @Test
    public void test() {
        String actual = h1("My Markdown Class") + "\n" +
                        strong("Doesn't this seem somewhat useful?") + br() +
                        em("No? Figures... Tbh I just made it cause it seems easy.") +
                        separator() +
                        checkbox("Hey I'm ticked!", true) + br() +
                        checkbox("But I'm not!") +
                        separator() +
                        img("My useless image.", "https://useless.com/image.png");

        String expected = "# My Markdown Class\n**" +
                          "Doesn't this seem somewhat useful?**  \n" +
                          "_No? Figures... Tbh I just made it cause it seems easy._\n" +
                          "\n" +
                          "---\n" +
                          "* [x] Hey I'm ticked!  \n" +
                          "* [ ] But I'm not!\n" +
                          "\n" +
                          "---\n" +
                          "![My useless image.](https://useless.com/image.png)";

        assertEquals(expected, actual);
    }
}
