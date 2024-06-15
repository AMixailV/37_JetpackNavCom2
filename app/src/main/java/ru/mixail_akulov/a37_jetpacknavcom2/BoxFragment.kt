package ru.mixail_akulov.a37_jetpacknavcom2

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import ru.mixail_akulov.a37_jetpacknavcom2.BoxFragment.Companion.EXTRA_RANDOM_NUMBER
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.mixail_akulov.a37_jetpacknavcom2.databinding.FragmentBoxBinding
import kotlin.random.Random

/**
 * Второй фрагмент запускается из [RootFragment].
 * Аргументы: цвет (int) и colorName (строка).
 *
 * Может возвращать результат с ключом [EXTRA_RANDOM_NUMBER] (int).
 */

class BoxFragment : Fragment(R.layout.fragment_box){

    private lateinit var binding: FragmentBoxBinding

    private val args: BoxFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

        val color = args.color

        binding.root.setBackgroundColor(color)

        binding.goBackButton.setOnClickListener {
            // просто вернитесь к предыдущему экрану, также можно использовать navigationUp()
            findNavController().popBackStack()
        }
        binding.openSecretButton.setOnClickListener {
            // запустить следующий экран без каких-либо аргументов
            findNavController().navigate(BoxFragmentDirections.actionBoxFragmentToSecretFragment())
        }
        binding.generateNumberButton.setOnClickListener {
            val number = Random.nextInt(100)
            // отправить результат
            publishResults(EXTRA_RANDOM_NUMBER, number)
            // вернуться к предыдущему экрану, также можно использовать navigationUp()
            findNavController().popBackStack()
        }
    }

    companion object {
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"
    }
}