package com.fahimezv.githubsearch.data.network.generator


import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkHeadersGeneratorImplTest {

    private lateinit var networkHeadersGeneratorImpl: NetworkHeaderGeneratorImpl

    @Before
    fun setup() {
        networkHeadersGeneratorImpl = NetworkHeaderGeneratorImpl()
    }

    @Test
    fun `getHeaders must be contains application `() {
        val expected = listOf(NetworkRequestHeader("application", "vnd.github.v3+json"))
        networkHeadersGeneratorImpl.getHeaders()
        val actual = networkHeadersGeneratorImpl.getHeaders()
        Assert.assertEquals(expected, actual)
    }

//    @Test
//    fun `getHeaders return empty`() {
//        val expected = emptyList<NetworkRequestHeader>()
//        val actual = networkHeadersGeneratorImpl.getHeaders()
//        Assert.assertEquals(expected, actual)
//    }
}