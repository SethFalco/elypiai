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

package fun.falco.elypiai.mojang.forms;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class AuthenticateForm {

    /**
     * @see #getAgent()
     */
    @SerializedName("agent")
    private AgentForm agent;

    /**
     * @see #getUsername()
     */
    @SerializedName("username")
    private String username;

    /**
     * @see #getPassword()
     */
    @SerializedName("password")
    private String password;

    /**
     * @see #getClientToken()
     */
    @SerializedName("clientToken")
    private String clientToken;

    /**
     * @see #isRequestUser()
     */
    @SerializedName("requestUser")
    private boolean requestUser;

    public AgentForm getAgent() {
        return agent;
    }

    public void setAgent(AgentForm agent) {
        this.agent = agent;
    }

    public String getUsername() {
        return username;
    }

    /**
     * @param username Username or email address of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * <strong>Do not store the password, once finished
     * you should store the access token.</strong>
     *
     * @return Password used to authenticate this user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * <strong>Do not store the password, once finished
     * you should store the access token.</strong>
     *
     * @param password Password of the Mojang account.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     *     Optional client token, this should be specified and identical for
     *     every request.
     */
    public String getClientToken() {
        return clientToken;
    }

    /**
     * Sets the client token or client identifier of the request. Should be
     * identical to the client token given to any other request by your
     * application.
     *
     * @param clientToken
     *     Client token or identifier to give for this request. This should be
     *     the same for all requests by the same application.
     */
    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    /**
     * @return Adds the user object to the response.
     */
    public boolean isRequestUser() {
        return requestUser;
    }

    /**
     * @param requestUser If this request should also get the user.
     */
    public void setRequestUser(boolean requestUser) {
        this.requestUser = requestUser;
    }
}
