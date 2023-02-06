package com.example.coacharea.ui.add

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.coacharea.databinding.FragmentAddBinding
import com.example.coacharea.ui.home.HomeViewModel
import com.example.domain.entity.Client
import java.net.URI

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddViewModel by activityViewModels()

    private lateinit var client: Client

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        client = Client(0, "", 0, "", 0.0, 0.0, 0.0, 0.0, "", "")

        binding.client = client
        with(binding) {
            btnSave.setOnClickListener {
                viewModel.insertClient(client!!)
                findNavController().popBackStack()
            }

            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }

            binding.ivClient.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    // Registers a photo picker activity launcher in single-select mode.
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                client.imageUri = uri.toString()
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