/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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

package org.elypia.elypiai.weblate.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.3
 */
public class WeblateRepository {

    /**
     * @see #doesNeedCommit()
     */
    @SerializedName("needs_commit")
    private boolean doesNeedCommit;

    /**
     * @see #doesNeedMerge()
     */
    @SerializedName("needs_merge")
    private boolean doesNeedMerge;

    /**
     * @see #doesNeedPush()
     */
    @SerializedName("needs_push")
    private boolean doesNeedPush;

    /**
     * @return Whether there are any pending changes to commit.
     */
    public boolean doesNeedCommit() {
        return doesNeedCommit;
    }

    /**
     * @return Whether there are any upstream changes to merge.
     */
    public boolean doesNeedMerge() {
        return doesNeedMerge;
    }

    /**
     * @return Whether there are any local changes to push.
     */
    public boolean doesNeedPush() {
        return doesNeedPush;
    }
}
