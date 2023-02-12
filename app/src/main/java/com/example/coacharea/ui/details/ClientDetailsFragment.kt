package com.example.coacharea.ui.details

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.coacharea.R
import com.example.coacharea.databinding.FragmentClientDetailsBinding


class ClientDetailsFragment : DialogFragment() {

    private var _binding: FragmentClientDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel : DetailsViewModel by activityViewModels()

    private val args: ClientDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientDetailsBinding.inflate(inflater, container, false)

        val radius = resources.getDimensionPixelSize(R.dimen.dialog_corner_radius)

        val drawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.WHITE)
            cornerRadius = radius.toFloat()
        }

        binding.root.background = drawable

        binding.client = args.client

        binding.button.setOnClickListener {
            viewModel.deleteClient(binding.client!!)
            findNavController().popBackStack()
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

}