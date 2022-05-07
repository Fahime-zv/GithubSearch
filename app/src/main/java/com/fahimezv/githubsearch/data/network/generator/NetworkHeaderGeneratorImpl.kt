package com.fahimezv.githubsearch.data.network.generator

import java.util.ArrayList

class NetworkHeaderGeneratorImpl(
) : NetworkHeaderGenerator {
    override fun getHeaders(): List<NetworkRequestHeader> {
        // Have been Used the array list  because if need to add another header we can add easily
        val arrayList = ArrayList<NetworkRequestHeader>()
        arrayList.addAll(
            listOf(
                NetworkRequestHeader("application", "vnd.github.v3+json")
            )
        )

        return arrayList

    }
}

