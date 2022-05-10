package com.fahimezv.githubsearch.data.network.model.dao

import com.fahimezv.githubsearch.core.entity.Search
import com.fahimezv.githubsearch.data.network.model.MappableDAO
import com.google.gson.annotations.SerializedName

class SearchDAO(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<UserDAO>
) : MappableDAO<Search> {

    override fun map(): Search {
        return Search(totalCount, incompleteResults, items = items.map { it.map() })
    }

    data class UserDAO(
        @SerializedName("login") val login: String,
        @SerializedName("id") val id: Int,
        @SerializedName("node_url") val nodeUrl: String?,
        @SerializedName("node_id") val nodeID: String?,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("gravatar_id") val gravatarId: String,
        @SerializedName("url") val url: String,
        @SerializedName("html_url") val htmLUrl: String,
        @SerializedName("followers_url") val followersUrl: String,
        @SerializedName("following_url") val followingUrl: String,
        @SerializedName("gists_url") val gistsUrl: String,
        @SerializedName("starred_url") val starredUrl: String,
        @SerializedName("subscriptions_url") val subscriptionsUrl: String,
        @SerializedName("organizations_url") val organizationsUrl: String,
        @SerializedName("repos_url") val reposUrl: String,
        @SerializedName("events_url") val eventsUrl: String,
        @SerializedName("received_events_url") val receivedEventsUrl: String,
        @SerializedName("type") val type: String,
        @SerializedName("site_admin") val siteAdmin: Boolean,
        @SerializedName("score") val score: Float?,
        @SerializedName("name") val name: String?,
        @SerializedName("company") val company: String?,
        @SerializedName("blog") val blog: String?,
        @SerializedName("location") val location: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("hireable") val hireable: String?,
        @SerializedName("bio") val bio: String?,
        @SerializedName("twitter_username") val twitterUsername: String?,
        @SerializedName("public_repos") val publicRepos: String?,
        @SerializedName("public_gists") val publicGists: String?,
        @SerializedName("followers") val followers: String?,
        @SerializedName("following") val following: String?,
        @SerializedName("created_at") val created_at: String?,
        @SerializedName("updated_at") val updated_at: String?
    ) : MappableDAO<Search.User> {
        override fun map(): Search.User {
            return Search.User(
                login = login,
                id = id,
                nodeUrl = nodeUrl,
                nodeId= nodeID,
                avatarUrl = avatarUrl,
                gravatarId = gravatarId,
                url = url,
                htmLUrl = htmLUrl,
                followersUrl = followersUrl,
                followingUrl = followingUrl,
                gistsUrl = gistsUrl,
                starredUrl = starredUrl,
                subscriptionsUrl = subscriptionsUrl,
                organizationsUrl = organizationsUrl,
                reposUrl = reposUrl,
                eventsUrl = eventsUrl,
                receivedEventsUrl = receivedEventsUrl,
                type = type,
                siteAdmin = siteAdmin,
                score = score,
                name = name,
                company = company,
                blog = blog,
                location = location,
                email = email,
                hireable = hireable,
                bio = bio,
                twitterUsername = twitterUsername,
                publicRepos = publicRepos,
                publicGists = publicGists,
                followers = followers,
                following = following,
                created_at = created_at,
                updated_at = updated_at
            )
        }
    }


}
