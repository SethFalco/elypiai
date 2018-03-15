package com.elypia.elypiai.utils;

public final class Markdown {

    /**
     * @param body Text to wrap.
     * @return The text wrapped in bold markdown syntax.
     */

    public static String strong(String body) {
        return "**" + body + "**";
    }

    /**
     * @param body Text to wrap.
     * @return The text wrapped in italics markdown syntax.
     */

    public static String em(String body) {
        return "_" + body + "_";
    }

    /**
     * @param body Text to wrap.
     * @return The text wrapped in strikethrough markdown syntax.
     */

    public static String s(String body) {
        return "~~" + body + "~~";
    }

    /**
     * @param body Text to wrap.
     * @return The text wrapped as a large markdown / HTML header.
     */

    public static String h1(String body) {
        return "# " + body;
    }

    /**
     * @param body Text to wrap.
     * @return The text wrapped as an h2 markdown / HTML header.
     */

    public static String h2(String body) {
        return "## " + body;
    }

    /**
     * @param body Text to wrap.
     * @return The text wrapped as an h3 markdown / HTML header.
     */

    public static String h3(String body) {
        return "### " + body;
    }

    /**
     * @param body Text to wrap.
     * @return The text wrapped as an h4 markdown / HTML header.
     */


    public static String h4(String body) {
        return "#### " + body;
    }

    /**
     * @param body Text to wrap.
     * @return The text wrapped as an h5 markdown / HTML header.
     */


    public static String h5(String body) {
        return "##### " + body;
    }

    /**
     * @param body Text to wrap.
     * @return The text wrapped as an h6 markdown / HTML header.
     */


    public static String h6(String body) {
        return "###### " + body;
    }

    /**
     * @param body The display text for the URL.
     * @param url Location the URL redirects the user.
     * @return The text wrapped as a markdown URL.
     */

    public static String a(String body, String url) {
        return "[" + body + "](" + url + ")";
    }

    /**
     * @param body The alt text for the image, displayed on hover.
     * @param url Location the image.
     * @return The text wrapped as a markdown image.
     */

    public static String img(String body, String url) {
        return "!" + a(body, url);
    }

    /**
     * @param body The text to wrap.
     * @return Text wrapped with markdown code syntax. (`)
     */

    public static String code(String body) {
        return "`" + body + "`";
    }

    /**
     * See {@link #codeblock(String, String)} for flavour / color.
     *
     * @param body The text to wrap.
     * @return Text wrapped with markdown codeblock syntax syntax. (```)
     */

    public static String codeblock(String body) {
        return codeblock(body, "");
    }

    /**
     * @param body Text to wrap.
     * @param flavour The color flavour to display the text, eg <code>java</code>.
     * @return Text wrapped with markdown codeblock syntax. (```)
     */

    public static String codeblock(String body, String flavour) {
        return "```" + flavour + "\n" + body + "\n```";
    }

    /**
     * @param body The text to wrap.
     * @return Text wrapped with markdown quote syntax. (&gt;)
     */

    public static String quote(String body) {
        return "> " + body;
    }

    /**
     * This defaults the checkbox to unchecked. To specify
     * checked or unchecked, see {@link #checkbox(String, boolean)}.
     *
     * @param body The text to wrap.
     * @return Text wrapped with markdown checkbox syntax.
     */

    public static String checkbox(String body) {
        return checkbox(body, false);
    }

    /**
     * @param body The text to wrap.
     * @return Text wrapped with markdown checkbox syntax.
     */

    public static String checkbox(String body, boolean checked) {
        return "* [" + (checked ? "x" : " ") + "] " + body;
    }

    /**
     * Also linebreaks before and after.
     *
     * @return Markdown syntax for a line seperator.
     */

    public static String separator() {
        return "\n\n---\n";
    }

    /**
     * In markdown a line break is performed by putting
     * two spaces, then an extra linebreak \n for clarity.
     *
     * @return Markdown syntax for a linebreak.
     */

    public static String br() {
        return "  \n";
    }
}
