package com.fahimezv.githubsearch.data.network.generator

interface NetworkHeaderGenerator {
    fun getHeaders(): List<NetworkRequestHeader>

}