/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fun.falco.elypiai.mojang.models;

import com.google.gson.annotations.SerializedName;

/**
 * List of security questions used by Mojang.
 *
 * <p>If an unknown question is returned, this will return {@link #UNKNOWN}.</p>
 *
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public enum SecurityQuestion {

    UNKNOWN(-1, null),

    @SerializedName("What is your favorite pet's name?")
    WHAT_IS_YOUR_FAVORITE_PETS_NAME(1, "What is your favorite pet's name?"),

    @SerializedName("What is your favorite movie?")
    WHAT_IS_YOUR_FAVORITE_MOVIE(2, "What is your favorite movie?"),

    @SerializedName("What is your favorite author's last name?")
    WHAT_IS_YOUR_FAVORITE_AUTHORS_LAST_NAME(3, "What is your favorite author's last name?"),

    @SerializedName("What is your favorite artist's last name?")
    WHAT_IS_YOUR_FAVORITE_ARTISTS_LAST_NAME(4, "What is your favorite artist's last name?"),

    @SerializedName("What is your favorite actor's last name?")
    WHAT_IS_YOUR_FAVORITE_ACTORS_LAST_NAME(5, "What is your favorite actor's last name?"),

    @SerializedName("What is your favorite activity?")
    WHAT_IS_YOUR_FAVORITE_ACTIVITY(6, "What is your favorite activity?"),

    @SerializedName("What is your favorite restaurant?")
    WHAT_ISYOUR_FAVORITE_RESTAURANT(7, "What is your favorite restaurant?"),

    @SerializedName("What is the name of your favorite cartoon?")
    WHAT_IS_THE_NAME_OF_YOUR_FAVORITE_CARTOON(8, "What is the name of your favorite cartoon?"),

    @SerializedName("What is the name of the first school you attended?")
    WHAT_IS_THE_NAME_OF_THE_FIRST_SCHOOL_YOU_ATTENDED(9, "What is the name of the first school you attended?"),

    @SerializedName("What is the last name of your favorite teacher?")
    WHAT_IS_THE_LAST_NAME_OF_YOUR_FAVORITE_TEACHER(10, "What is the last name of your favorite teacher?"),

    @SerializedName("What is your best friend's first name?")
    WHAT_IS_YOUR_BEST_FRIENDS_FIRST_NAME(11, "What is your best friend's first name?"),

    @SerializedName("What is your favorite cousin's name?")
    WHAT_IS_YOUR_FAVORITE_COUSINS_NAME(12, "What is your favorite cousin's name?"),

    @SerializedName("What was the first name of your first girl/boyfriend?")
    WHAT_WAS_THE_FIRST_NAME_OF_YOUR_FIRST_GIRL_BOYFRIEND(13, "What was the first name of your first girl/boyfriend?"),

    @SerializedName("What was the name of your first stuffed animal?")
    WHAT_WAS_THE_NAME_OF_YOUR_FIRST_STUFFED_ANIMAL(14, "What was the name of your first stuffed animal?"),

    @SerializedName("What is your mother's middle name?")
    WHAT_IS_YOUR_MOTHERS_MIDDLE_NAME(15, "What is your mother's middle name?"),

    @SerializedName("What is your father's middle name?")
    WHAT_IS_YOUR_FATHERS_MIDDLE_NAME(16, "What is your father's middle name?"),

    @SerializedName("What is your oldest sibling's middle name?")
    WHAT_IS_YOUR_OLDEST_SIBLINGS_MIDDLE_NAME(17, "What is your oldest sibling's middle name?"),

    @SerializedName("In what city did your parents meet?")
    IN_WHAT_CITY_DID_YOUR_PARENTS_MEET(18, "In what city did your parents meet?"),

    @SerializedName("In what hospital were you born?")
    IN_WHAT_HOSPITAL_WERE_YOU_BORN(19, "In what hospital were you born?"),

    @SerializedName("What is your favorite team?")
    WHAT_IS_YOUR_FAVORITE_TEAM(20, "What is your favorite team?"),

    @SerializedName("How old were you when you got your first computer?")
    HOW_OLD_WERE_YOU_WHEN_YOU_GOT_YOUR_FIRST_COMPUTER(21, "How old were you when you got your first computer?"),

    @SerializedName("How old were you when you got your first gaming console?")
    HOW_OLD_WERE_YOU_WHEN_YOU_FOT_YOUR_FIRST_GAMING_CONSOLE(22, "How old were you when you got your first gaming console?"),

    @SerializedName("What was your first video game?")
    WHAT_WAS_YOUR_FIRST_VIDEO_GAME(23, "What was your first video game?"),

    @SerializedName("What is your favorite card game?")
    WHAT_IS_YOUR_FAVORITE_CARD_GAME(24, "What is your favorite card game?"),

    @SerializedName("What is your favorite board game?")
    WHAT_IS_YOUR_FAVORITE_BOARD_GAME(25, "What is your favorite board game?"),

    @SerializedName("What was your first gaming console?")
    WHAT_WAS_YOUR_FIRST_GAMING_CONSOLE(26, "What was your first gaming console?"),

    @SerializedName("What was the first book you ever read?")
    WHAT_WAS_THE_FIRST_BOOK_YOU_EVER_READ(27, "What was the first book you ever read?"),

    @SerializedName("Where did you go on your first holiday?")
    WHERE_DID_YOU_GO_ON_YOUR_FIRST_HOLIDAY(28, "Where did you go on your first holiday?"),

    @SerializedName("In what city does your grandmother live?")
    IN_WHAT_CITY_DOES_YOUR_GRANDMOTHER_LIVE(29, "In what city does your grandmother live?"),

    @SerializedName("In what city does your grandfather live?")
    IN_WHAT_CITY_DOES_YOUR_GRANDFATHER_LIVE(30, "In what city does your grandfather live?"),

    @SerializedName("What is your grandmother's first name?")
    WHAT_IS_YOUR_GRANDMOTHERS_FIRST_NAME(31, "What is your grandmother's first name?"),

    @SerializedName("What is your grandfather's first name?")
    WHAT_IS_YOUR_GRANDFATHERS_FIRST_NAME(32, "What is your grandfather's first name?"),

    @SerializedName("What is your least favorite food?")
    WHAT_IS_YOUR_LEAST_FAVORITE_FOOD(33, "What is your least favorite food?"),

    @SerializedName("What is your favorite ice cream flavor?")
    WHAT_IS_YOUR_FAVORITE_ICE_CREAM_FLAVOR(34, "What is your favorite ice cream flavor?"),

    @SerializedName("What is your favorite ice cream flavor?")
    WHAT_IS_YOUR_FAVORITE_ICE_CREAM_FLAVOR_2(35, "What is your favorite ice cream flavor?"),

    @SerializedName("What is your favorite place to visit?")
    WHAT_IS_YOUR_FAVORITE_PLACE_TO_VISIT(36, "What is your favorite place to visit?"),

    @SerializedName("What is your dream job?")
    WHAT_IS_YOUR_DREAM_JOB(37, "What is your dream job?"),

    @SerializedName("What color was your first pet?")
    WHAT_COLOR_WAS_YOUR_FIRST_PET(38, "What color was your first pet?"),

    @SerializedName("What is your lucky number?")
    WHAT_IS_YOUR_LUCKY_NUMBER(39, "What is your lucky number?");

    private final int questionId;
    private final String questionText;

    SecurityQuestion(final int questionId, final String questionText) {
        this.questionId = questionId;
        this.questionText = questionText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }
}
