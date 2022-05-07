package com.fahimezv.githubsearch.data.network.generator


class NetworkEnvironmentImpl: NetworkEnvironment {
    override val baseUrl: String
        get() = K.baseUrl
}