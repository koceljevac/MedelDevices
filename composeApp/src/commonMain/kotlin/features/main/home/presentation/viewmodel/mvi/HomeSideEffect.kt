package features.main.home.presentation.viewmodel.mvi

import core.utils.ViewSideEffect


sealed class HomeSideEffect: ViewSideEffect {
    data object ShowError : HomeSideEffect()

    data class ShowMessage(val message: String) : HomeSideEffect()
}