package com.project.sakeshop.presentation.screens
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.sakeshop.Resource
import com.project.sakeshop.domain.useCase.GetSakeShopByIdUseCase
import com.project.sakeshop.domain.model.SakeShop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SakeShopDetailsViewModel @Inject constructor(
    private val getSakeShopByIdUseCase: GetSakeShopByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SakeShopDetailState())
    val state: StateFlow<SakeShopDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("shopId")?.let { shopId ->
            getSakeShop(shopId)
        }
    }

    private fun getSakeShop(id: Int) {
        getSakeShopByIdUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SakeShopDetailState(shop = result.data)
                }
                is Resource.Error -> {
                    _state.value = SakeShopDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SakeShopDetailState(isLoading = true)
                }
                is Resource.Empty -> {
                    _state.value = SakeShopDetailState(error = "No shop found")
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class SakeShopDetailState(
    val isLoading: Boolean = false,
    val shop: SakeShop? = null,
    val error: String = ""
)