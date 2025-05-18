package com.project.sakeshop

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.sakeshop.data.local.model.SakeShopDTO
import com.project.sakeshop.domain.model.SakeShop
import com.project.sakeshop.domain.repository.SakeShopRepository
import com.project.sakeshop.domain.useCase.GetAllSakeShopsUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class GetAllSakeShopsUseCaseSimpleTest {

    private lateinit var useCase: GetAllSakeShopsUseCase
    private lateinit var repository: SakeShopRepository

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetAllSakeShopsUseCase(repository)
    }

    @Test
    fun whenRepositoryReturnsSuccessThenUseCaseReturnsSuccess(): Unit = runTest {
        val type = object : TypeToken<List<SakeShop>>() {}
        val shops: List<SakeShop> = readJsonResource("sakeshop.json", type)

        coEvery { repository.getAllSakeShops() } returns flowOf(Resource.Success(shops))

        val result = useCase().first()

        assertTrue(result is Resource.Success)
        assertEquals(10, (result as Resource.Success).data.size)
        assertEquals("信州スシサカバ 寿しなの", result.data[0].name)
    }

    @Test
    fun whenRepositoryReturnsErrorThenUseCaseReturnsError(): Unit = runTest {
        val errorMessage = "Failed to load shops"
        coEvery { repository.getAllSakeShops() } returns flowOf(Resource.Error(errorMessage))

        val result = useCase().first()

        assertTrue(result is Resource.Error)
        assertEquals(errorMessage, (result as Resource.Error).message)
    }

    @Test
    fun whenRepositoryReturnsEmptyListThenUseCaseReturnsEmptyList(): Unit = runTest {
        coEvery { repository.getAllSakeShops() } returns flowOf(Resource.Success(emptyList()))

        val result = useCase().first()
        assertTrue(result is Resource.Success)
        assertTrue((result as Resource.Success).data.isEmpty())
    }
}

fun <T> readJsonResource(fileName: String, typeToken: TypeToken<T>): T {
    val inputStream = Thread.currentThread().contextClassLoader
        ?.getResourceAsStream(fileName)
        ?: error("Resource not found: $fileName")

    return inputStream.reader().use { reader ->
        Gson().fromJson(reader, typeToken.type)
    }
}