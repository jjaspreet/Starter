package com.example.starterCode.presenter.rickyandmorty

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.starterCode.common.toToast
import com.example.starterCode.databinding.ActivityHomeBinding
import com.example.starterCode.domain.model.RickyAndMorty
import com.example.starterCode.presenter.rickyandmorty.adapter.RickyAndMortyAdapter
import com.example.starterCode.presenter.rickyandmorty.adapter.RickyAndMortyLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), RickyAndMortyAdapter.OnItemClickListener {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: RickyAndMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = RickyAndMortyAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = RickyAndMortyLoadStateAdapter { adapter.retry() },
                footer = RickyAndMortyLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }
        }

        lifecycleScope.launch  {
            viewModel.getMovies().collectLatest { movies ->
                adapter.submitData(movies)
            }
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
    }

    override fun onItemClick(item: RickyAndMorty) {
        item.name.toToast(this@HomeActivity).show()
    }
}