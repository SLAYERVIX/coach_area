package com.example.coacharea.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.coacharea.R
import com.example.coacharea.databinding.FragmentHomeBinding
import com.example.coacharea.ui.utils.adapters.ClientAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val adapter = ClientAdapter()
        binding.rvClient.adapter = adapter

        val hideViewThreshold = 10
        with(binding) {
            rvClient.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > hideViewThreshold && floatingActionButton.visibility == View.VISIBLE) {
                        floatingActionButton.hide()
                    } else if (dy < -hideViewThreshold && floatingActionButton.visibility != View.VISIBLE) {
                        floatingActionButton.show()
                    }
                }
            })

            floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addFragment)
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.clients.collect {
                adapter.submitList(it)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}