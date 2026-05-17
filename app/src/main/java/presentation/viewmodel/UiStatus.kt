package presentation.viewmodel

sealed class UiStatus {

    object Init : UiStatus()

    object Loading : UiStatus()

    object Success : UiStatus()

    data class Error(
        val message: String
    ) : UiStatus()
}