package com.elypia.elypiai.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class JsoupUtils {

    public static <T> String optTextByTag(Element element, String tag, T opt) {
        String text = getTextByTag(element, tag);

        return text != null ? text : opt.toString();
    }

    public static String getTextByTag(Element element, String tag) {
        Element ele = getElementByTag(element, tag);

        return ele != null ? ele.text() : null;
    }

    public static Element getElementByTag(Element element, String tag) {
        Elements elements = element.getElementsByTag(tag);

        if (elements.size() == 0)
            return null;

        return elements.first();
    }
}
