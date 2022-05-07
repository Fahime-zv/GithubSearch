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
        @SerializedName("node_id") val nodeUrl: String,
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
        @SerializedName("score") val score: Float
    ) : MappableDAO<Search.User> {
        override fun map(): Search.User {
            return Search.User(
                login = login,
                id = id,
                nodeUrl = nodeUrl,
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
                score = score
            )
        }
    }


}
