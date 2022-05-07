package com.fahimezv.githubsearch.core.entity

data class Search(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<User>
) : NetworkReceivedModel {
    data class User(
        val login: String,
        val id: Int,
        val nodeUrl: String,
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
        val score: Float
    ) : NetworkReceivedModel
}
