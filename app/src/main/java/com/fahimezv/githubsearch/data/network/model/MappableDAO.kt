package com.fahimezv.githubsearch.data.network.model

import com.fahimezv.githubsearch.core.entity.NetworkReceivedModel


interface MappableDAO<M : NetworkReceivedModel> {
    fun map(): M
}