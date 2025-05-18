package com.project.sakeshop.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.sakeshop.Resource
import com.project.sakeshop.domain.useCase.GetAllSakeShopsUseCase
import com.project.sakeshop.domain.model.SakeShop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SakeShopListViewModel @Inject constructor(
    private val getAllSakeShopsUseCase: GetAllSakeShopsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SakeShopListState())
    val state: StateFlow<SakeShopListState> = _state.asStateFlow()

    init {
        getSakeShops()
    }

    fun getSakeShops() {
        getAllSakeShopsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SakeShopListState(
                        shops = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = SakeShopListState(
                        error = result.message ?: "An unexpected error occurred",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = SakeShopListState(isLoading = true)
                }
                is Resource.Empty -> {
                    _state.value = SakeShopListState(
                        shops = emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class SakeShopListState(
    val isLoading: Boolean = false,
    val shops: List<SakeShop> = emptyList(),
    val error: String = ""
)