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
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class Status {

    /**
     * @see #getServerStatus()
     */
    @SerializedName("Status")
    private ServerStatus serverStatus;

    /**
     * @see #getRuntimeMode()
     */
    @SerializedName("Runtime-Mode")
    private String runtimeMode;

    /**
     * @see #getApplicationAuthor()
     */
    @SerializedName("Application-Author")
    private String applicationAuthor;

    /**
     * @see #getApplicationDescription()
     */
    @SerializedName("Application-Description")
    private String applicationDescription;

    /**
     * @see #getSpecificationVersion()
     */
    @SerializedName("Specification-Version")
    private String specificationVersion;

    /**
     * @see #getApplicatioName()
     */
    @SerializedName("Application-Name")
    private String applicatioName;

    /**
     * @see #getImplementationVersion()
     */
    @SerializedName("Implementation-Version")
    private String implementationVersion;

    /**
     * @see #getApplicationOwner()
     */
    @SerializedName("Application-Owner")
    private String applicationOwner;

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public String getRuntimeMode() {
        return runtimeMode;
    }

    public String getApplicationAuthor() {
        return applicationAuthor;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public String getSpecificationVersion() {
        return specificationVersion;
    }

    public String getApplicatioName() {
        return applicatioName;
    }

    public String getImplementationVersion() {
        return implementationVersion;
    }

    public String getApplicationOwner() {
        return applicationOwner;
    }
}
