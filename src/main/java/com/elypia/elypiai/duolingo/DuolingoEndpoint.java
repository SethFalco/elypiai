package com.elypia.elypiai.duolingo;

/**
 * This is not an official wrap of the API, nor does
 * it use official / public endpoints, these endpoints are
 * as found and as available on the website. <br>
 * <br>
 * This list does <em>NOT</em> include anything that would
 * otherwise have to bre webscrapped.
 * This only used JSON data. <br>
 * <br>
 * Any of these endpoints may stop working without notice.
 */

public enum DuolingoEndpoint {

    /**
     * Login must be called to access various endpoints of
     * the API. <br>
     * <br>
     * Parameters: <br>
     * <strong>login</strong> = {username / email} <br>
     * <strong>password</strong> = {password}
     */

    LOGIN("https://duolingo.com/login"),

    /**
     * Route Parameters: <br>
     * <strong>0</strong> = {username}
     */

    USER("https://duolingo.com/users/%s"),

    /**
     * Requires {@link #LOGIN} be called succesfully. <br>
     * <br>
     * Parameters: <br>
     * <strong>unit</strong> = {week / month}
     */

    FRIENDS_LEADERBOARD("https://www.duolingo.com/friendships/leaderboard_activity"),

    /**
     * Requires {@link #LOGIN} be called succesfully. <br>
     * <br>
     * Formdata: <br>
     * <strong>learning_language</strong> = {language code}
     */

    CHANGE_LANGUAGE("https://www.duolingo.com/switch_language"),

    /**
     * Route Parameters: <br>
     * <strong>source</strong> = {source language} <br>
     * <strong>target</strong> = {target language} <br>
     * <br>
     * Parameters: <br>
     * <strong>tokens</strong> = {words / phrases to translate}
     */

    TRANSLATE_TOKENS("https://d2.duolingo.com/api/1/dictionary/hints/%s/%s"),

    /**
     * Requires {@link #LOGIN} be called succesfully. <br>
     */

    USERS_VOCABULARY("https://www.duolingo.com/vocabulary/overview");

    private String endpoint;

    DuolingoEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
