package ru.mixail_akulov.a37_jetpacknavcom2

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

typealias ResultsListener<T> = (T) -> Unit

/**
 * Отправить некоторые результаты в предыдущий фрагмент.
 */
fun <T> Fragment.publishResults(key: String, result: T) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}

/**
 * Прослушайте результаты экрана. Результаты автоматически очищаются, когда слушатель их получает.
 */
fun <T> Fragment.listenResults(key: String, listener: ResultsListener<T>) {
    val liveData = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
    liveData?.observe(viewLifecycleOwner) { result ->
        if (result != null) {
            listener(result)
            liveData.value = null
        }
    }
}