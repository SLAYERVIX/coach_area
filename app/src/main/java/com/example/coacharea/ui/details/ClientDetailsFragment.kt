package com.example.coacharea.ui.details

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.coacharea.R
import com.example.coacharea.databinding.FragmentClientDetailsBinding


class ClientDetailsFragment : Fragment(), MenuProvider {

    private var _binding: FragmentClientDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel : DetailsViewModel by activityViewModels()

    private val args: ClientDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientDetailsBinding.inflate(inflater, container, false)

        binding.client = args.client

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        return menuInflater.inflate(R.menu.meno_details, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_edit -> {
                findNavController().navigate(
                    ClientDetailsFragmentDirections.actionClientDetailsFragmentToEditFragment(
                        args.client
                    )
                )
            }
            R.id.action_delete -> {
                viewModel.deleteClient(args.client)
                findNavController().popBackStack()
            }
        }

        return true
    }
}