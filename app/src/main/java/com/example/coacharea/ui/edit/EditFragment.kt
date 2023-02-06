package com.example.coacharea.ui.edit

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.coacharea.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val args: EditFragmentArgs by navArgs()

    private val viewModel : EditViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)

        binding.client = args.client
        with(binding) {
            btnSave.setOnClickListener {
                viewModel.updateClient(args.client)
                findNavController().popBackStack()
            }

            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }

            binding.ivClient.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }

        if (args.client.imageUri.isNotEmpty()) {
            Glide.with(this)
                .load(args.client.imageUri)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .circleCrop()
                .into(binding.ivClient)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Registers a photo picker activity launcher in single-select mode.
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                args.client.imageUri = uri.toString()
                loadImage(uri)

                Log.d("PhotoPicker", "Selected URI: $uri")
            }
            else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    private fun loadImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.ivClient)
    }
}