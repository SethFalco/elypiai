/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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
public class Component {

    /**
     * @see #getName()
     */
    @SerializedName("name")
    private String name;

    /**
     * @see #getSlug()
     */
    @SerializedName("slug")
    private String slug;

    /**
     * @see #getId()
     */
    @SerializedName("id")
    private int id;

    /**
     * @see #getProject()
     */
    @SerializedName("project")
    private Project project;

    /**
     * @see #getVcs()
     */
    @SerializedName("vcs")
    private String vcs;

    /**
     * @see #getVcsUrl()
     */
    @SerializedName("repo")
    private String vcsUrl;

    /**
     * @see #getGitExportUrl()
     */
    @SerializedName("git_export")
    private String gitExportUrl;

    /**
     * @see #getBranch()
     */
    @SerializedName("branch")
    private String branch;

    /**
     * @see #getPushBranch()
     */
    @SerializedName("push_branch")
    private String pushBranch;

    /**
     * @see #getFilemask()
     */
    @SerializedName("filemask")
    private String filemask;

    /**
     * @see #getTemplate()
     */
    @SerializedName("template")
    private String template;

    /**
     * @see #isCanEditTemplate()
     */
    @SerializedName("edit_template")
    private boolean canEditTemplate;

    /**
     * @see #getIntermediate()
     */
    @SerializedName("intermediate")
    private String intermediate;

    /**
     * @see #getNewBase()
     */
    @SerializedName("new_base")
    private String newBase;

    /**
     * @see #getFileFormat()
     */
    @SerializedName("file_format")
    private String fileFormat;

    /**
     * @see #getLicense()
     */
    @SerializedName("license")
    private String license;

    /**
     * @see #getLicenseUrl()
     */
    @SerializedName("license_url")
    private String licenseUrl;

    /**
     * @see #getAgreement()
     */
    @SerializedName("agreement")
    private String agreement;

    /**
     * @see #getWebUrl()
     */
    @SerializedName("web_url")
    private String webUrl;

    /**
     * @see #getNewLang()
     */
    @SerializedName("new_lang")
    private String newLang;

    /**
     * @see #getLanguageCodeStyle()
     */
    @SerializedName("language_code_style")
    private String languageCodeStyle;

    /**
     * @see #getVcsUrl()
     */
    @SerializedName("push")
    private String vcsPushUrl;

    /**
     * @see #getCheckFlags()
     */
    @SerializedName("check_flags")
    private String checkFlags;

    /**
     * @see #getPriority()
     */
    @SerializedName("priority")
    private int priority;

    /**
     * @see #getEnforcedChecks()
     */
    @SerializedName("enforced_checks")
    private String enforcedChecks;

    /**
     * @see #isRestricted()
     */
    @SerializedName("restricted")
    private boolean isRestricted;

    /**
     * @see #getRepoWeb()
     */
    @SerializedName("repoweb")
    private String repoWeb;

    /**
     * @see #getBugReportContact()
     */
    @SerializedName("report_source_bugs")
    private String bugReportContact;

    /**
     * @see #getMergeStyle()
     */
    @SerializedName("merge_style")
    private String mergeStyle;

    /**
     * @see #getCommitMessage()
     */
    @SerializedName("commit_message")
    private String commitMessage;

    /**
     * @see #getAddMessage()
     */
    @SerializedName("add_message")
    private String addMessage;

    /**
     * @see #getDeleteMessage()
     */
    @SerializedName("delete_message")
    private String deleteMessage;

    /**
     * @see #getMergeMessage()
     */
    @SerializedName("merge_message")
    private String mergeMessage;

    /**
     * @see #getAddonMessage()
     */
    @SerializedName("addon_message")
    private String addonMessage;

    /**
     * @see #isAllowtranslationPropagation()
     */
    @SerializedName("allow_translation_propagation")
    private boolean allowtranslationPropagation;

    /**
     * @see #isEnableSuggestions()
     */
    @SerializedName("enable_suggestions")
    private boolean enableSuggestions;

    /**
     * @see #isSuggestionVoting()
     */
    @SerializedName("suggestion_voting")
    private boolean suggestionVoting;

    /**
     * @see #isSuggestionAutoAccept()
     */
    @SerializedName("suggestion_autoaccept")
    private boolean suggestionAutoAccept;

    /**
     * @see #isPushOnCommit()
     */
    @SerializedName("push_on_commit")
    private boolean pushOnCommit;

    /**
     * @see #getCommitPendingAge()
     */
    @SerializedName("commit_pending_age")
    private int commitPendingAge;

    /**
     * @see #isAutoLockError()
     */
    @SerializedName("auto_lock_error")
    private boolean autoLockError;

    /**
     * @see #getLanguageRegex()
     */
    @SerializedName("language_regex")
    private String languageRegex;

    /**
     * @see #getVariantRegex()
     */
    @SerializedName("variant_regex")
    private String variantRegex;

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public int getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public String getVcs() {
        return vcs;
    }

    public String getVcsUrl() {
        return vcsUrl;
    }

    public String getGitExportUrl() {
        return gitExportUrl;
    }

    public String getBranch() {
        return branch;
    }

    public String getPushBranch() {
        return pushBranch;
    }

    public String getFilemask() {
        return filemask;
    }

    public String getTemplate() {
        return template;
    }

    public boolean isCanEditTemplate() {
        return canEditTemplate;
    }

    public String getIntermediate() {
        return intermediate;
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

    public String getAgreement() {
        return agreement;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getNewLang() {
        return newLang;
    }

    public String getLanguageCodeStyle() {
        return languageCodeStyle;
    }

    public String getVcsPushUrl() {
        return vcsPushUrl;
    }

    public String getCheckFlags() {
        return checkFlags;
    }

    public int getPriority() {
        return priority;
    }

    public String getEnforcedChecks() {
        return enforcedChecks;
    }

    public boolean isRestricted() {
        return isRestricted;
    }

    public String getRepoWeb() {
        return repoWeb;
    }

    public String getBugReportContact() {
        return bugReportContact;
    }

    public String getMergeStyle() {
        return mergeStyle;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public String getAddMessage() {
        return addMessage;
    }

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public String getMergeMessage() {
        return mergeMessage;
    }

    public String getAddonMessage() {
        return addonMessage;
    }

    public boolean isAllowtranslationPropagation() {
        return allowtranslationPropagation;
    }

    public boolean isEnableSuggestions() {
        return enableSuggestions;
    }

    public boolean isSuggestionVoting() {
        return suggestionVoting;
    }

    public boolean isSuggestionAutoAccept() {
        return suggestionAutoAccept;
    }

    public boolean isPushOnCommit() {
        return pushOnCommit;
    }

    public int getCommitPendingAge() {
        return commitPendingAge;
    }

    public boolean isAutoLockError() {
        return autoLockError;
    }

    public String getLanguageRegex() {
        return languageRegex;
    }

    public String getVariantRegex() {
        return variantRegex;
    }
}
