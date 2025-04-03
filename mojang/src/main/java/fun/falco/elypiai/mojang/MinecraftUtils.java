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

package fun.falco.elypiai.mojang;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

import fun.falco.elypiai.mojang.models.DefaultSkin;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public final class MinecraftUtils {

    /** Pattern to match if a string is a valid non-hyphenated UUID. */
    private static final Pattern TRIMMED_UUID_PATTERN = Pattern.compile("[a-f\\d]{32}");

    /**
     * Pattern to verify if a Minecraft username is valid.
     *
     * @implNote
     *     Page states that it's no longer possible to create usernames with
     *     less than three characters. This suggests that previously accounts
     *     were created with under 3, so these are allowed despite the fact that
     *     new accounts can't do this.
     * @see <a href="https://help.minecraft.net/hc/en-us/articles/360034636712-Minecraft-Usernames">Minecraft Usernames</a>
     */
    private static final Pattern USERNAME_PATTERN = Pattern.compile("[A-Za-z\\d_]{0,16}");

    /**
     * Don't construct this class. You should only use it through the public
     * static methods.
     */
    private MinecraftUtils() {
        // Do nothing
    }

    /**
     * It's preferred to use {@link #getDefaultSkin(UUID)} if possible. This is
     * just for convenience if you have a String and know for certain it's a
     * valid UUID.
     *
     * @param uuidString UUID to check.
     * @return Default skin a player with this UUID would have.
     * @throws IllegalArgumentException
     *     If uuidString does not confirm to a UUID.
     */
    public static DefaultSkin getDefaultSkin(final String uuidString) {
        final String uuidStringToCheck = (uuidString.contains("-")) ? uuidString : hyphenateUuid(uuidString);
        final UUID uuid = UUID.fromString(uuidStringToCheck);
        return getDefaultSkin(uuid);
    }

    /**
     * @param uuid UUID to check.
     * @return Default skin a player with this UUID would have.
     */
    public static DefaultSkin getDefaultSkin(final UUID uuid) {
        final boolean isAlex = (uuid.hashCode() & 1) != 0;
        return isAlex ? DefaultSkin.ALEX : DefaultSkin.STEVE;
    }

    /**
     * Convert a non-hyphenated UUID string to a UUID string with hyphens. If
     * the input already contains hyphens, nothing happens.
     *
     * @param trimmedUuid UUID without hyphens.
     * @return Same UUID, but with hyphens inserted.
     * @throws IllegalArgumentException
     *     If the trimmedUuid doesn't look like a valid UUID that can have
     *     hyphens inserted.
     */
    public static String hyphenateUuid(final String trimmedUuid) {
        if (trimmedUuid.contains("-")) {
            return trimmedUuid;
        }

        if (!TRIMMED_UUID_PATTERN.matcher(trimmedUuid).matches()) {
            throw new IllegalArgumentException("trimmedUuid isn't a valid trimmed UUID string");
        }

        List<String> sections = List.of(
            trimmedUuid.substring(0, 8),
            trimmedUuid.substring(8, 12),
            trimmedUuid.substring(12, 16),
            trimmedUuid.substring(16, 20),
            trimmedUuid.substring(20, 32)
        );

        return String.join("-", sections);
    }

    /**
     * @param trimmedUuid UUID without hyphans.
     * @return UUID parsed as a {@link UUID} object.
     * @throws IllegalArgumentException
     *     If the trimmedUuid doesn't look like a valid UUID that can have
     *     hyphens inserted.
     */
    public static UUID trimmedUuidToUuid(final String trimmedUuid) {
        return UUID.fromString(hyphenateUuid(trimmedUuid));
    }

    /**
     * @param uuid Valid UUID, can not be null.
     * @return String version of this UUID, with all hyphens removed.
     */
    public static String trimUuid(final UUID uuid) {
        Objects.requireNonNull(uuid);
        return uuid.toString().replace("-", "");
    }

    /**
     * @param username Username which may or may not be of a Minecraft user.
     * @return If the username appears to be a valid Minecraft username.
     */
    public static boolean isUsernameValid(final String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }
}
