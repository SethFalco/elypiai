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

package org.elypia.elypiai.weblate.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.2
 */
public class Project {

    @SerializedName("name")
    private String name;

    @SerializedName("slug")
    private String slug;

    @SerializedName("id")
    private int id;

    @SerializedName("vcs")
    private String vcs;

    @SerializedName("repo")
    private String repoUrl;

    @SerializedName("git_export")
    private String gitExportUrl;

    @SerializedName("branch")
    private String repoBranch;

    @SerializedName("file_mask")
    private String fileMask;

    @SerializedName("template")
    private String template;

    @SerializedName("edit_template")
    private boolean canEditTemplate;

    @SerializedName("new_base")
    private String newBase;

    @SerializedName("file_format")
    private String fileFormat;

    @SerializedName("license")
    private String license;

    @SerializedName("license_url")
    private String licenseUrl;

    @SerializedName("web_url")
    private String resourceUrl;

    @SerializedName("new_lang")
    private String newLang;

    @SerializedName("push")
    private String push;

    @SerializedName("check_flags")
    private String checkFlags;

    @SerializedName("enforced_checks")
    private String enforcedChecked;

    @SerializedName("restricted")
    private boolean isRestricted;

    @SerializedName("project")
    private Project project;

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public int getId() {
        return id;
    }

    public String getVcs() {
        return vcs;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public String getGitExportUrl() {
        return gitExportUrl;
    }

    public String getRepoBranch() {
        return repoBranch;
    }

    public String getFileMask() {
        return fileMask;
    }

    public String getTemplate() {
        return template;
    }

    public boolean isCanEditTemplate() {
        return canEditTemplate;
    }

    public String getNewBase() {
        return newBase;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public String getLicense() {
        return license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getNewLang() {
        return newLang;
    }

    public String getPush() {
        return push;
    }

    public String getCheckFlags() {
        return checkFlags;
    }

    public String getEnforcedChecked() {
        return enforcedChecked;
    }

    public boolean isRestricted() {
        return isRestricted;
    }

    public Project getProject() {
        return project;
    }
}
