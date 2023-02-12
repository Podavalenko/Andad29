package ru.netology.andad29.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.andad29.databinding.FragmentEditPostBinding
import ru.netology.andad29.util.AndroidUtils
import ru.netology.andad29.util.StringArg
import ru.netology.andad29.viewmodel.PostViewModel

@AndroidEntryPoint
class EditPostFragment : Fragment() {
    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditPostBinding.inflate(inflater, container, false)

        binding.editEdit.requestFocus()
        arguments?.textArg?.let(binding.editEdit::setText)
        binding.okEdit.setOnClickListener {
            viewModel.changeContent(binding.editEdit.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }
        return binding.root
    }

}