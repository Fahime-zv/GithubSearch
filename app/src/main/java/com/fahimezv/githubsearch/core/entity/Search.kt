package com.fahimezv.githubsearch.core.entity

import com.google.gson.annotations.SerializedName

data class Search(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<User>
) : NetworkReceivedModel {
    data class User(
        val login: String,
        val id: Int,
        val nodeUrl: String?,
        val nodeId: String?,
        val avatarUrl: String,
        val gravatarId: String,
        val url: String,
        val htmLUrl: String,
        val followersUrl: String,
        val followingUrl: String,
        val gistsUrl: String,
        val starredUrl: String,
        val subscriptionsUrl: String,
        val organizationsUrl: String,
        val reposUrl: String,
        val eventsUrl: String,
        val receivedEventsUrl: String,
        val type: String,
        val siteAdmin: Boolean,
        val score: Float?, val name: String?,
        val company: String?,
        val blog: String?,
        val location: String?,
        val email: String?,
        val hireable: String?,
        val bio: String?,
        val twitterUsername: String?,
        val publicRepos: String?,
        val publicGists: String?,
        val followers: String?,
        val following: String?,
        val created_at: String?,
        val updated_at: String?
    ) : NetworkReceivedModel
}
