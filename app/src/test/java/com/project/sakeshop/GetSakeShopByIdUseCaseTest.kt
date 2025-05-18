package com.project.sakeshop

import com.project.sakeshop.domain.model.SakeShop
import com.project.sakeshop.domain.repository.SakeShopRepository
import com.project.sakeshop.domain.useCase.GetSakeShopByIdUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetSakeShopByIdUseCaseTest {
    private lateinit var useCase: GetSakeShopByIdUseCase
    private lateinit var repository: SakeShopRepository

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetSakeShopByIdUseCase(repository)
    }

    @Test
    fun whenRepositoryReturnsSuccessThenUseCaseReturnsSuccess(): Unit = runTest {
        val sakeShop = SakeShop(
            name = "Test Shop",
            description = "Test Description",
            picture = "test.jpg",
            rating = 4.5,
            address = "Test Address",
            coordinates = listOf(35.6762, 139.6503),
            googleMapsLink = "https://maps.google.com",
            website = "https://test.com"
        )

        coEvery { repository.getSakeShopById(any()) } returns flowOf(Resource.Success(sakeShop))

        val result = useCase(1).first()

        assertTrue(result is Resource.Success)
        assertEquals(sakeShop, (result as Resource.Success).data)
    }

    @Test
    fun whenRepositoryReturnsErrorThenUseCaseReturnsError(): Unit = runTest {
        coEvery { repository.getSakeShopById(any()) } returns flowOf(
            Resource.Error("Error")
        )

        val result = useCase(1).first()
        assertTrue(result is Resource.Error)
        assertEquals("Error", (result as Resource.Error).message)
    }
}