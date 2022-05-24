package com.example.starterCode.presenter.rickyandmorty

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.starterCode.common.toToast
import com.example.starterCode.databinding.ActivityHomeBinding
import com.example.starterCode.domain.model.RickyAndMorty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var rickyAndMortyAdapter: RickyAndMortyAdapter
    private lateinit var binding: ActivityHomeBinding

    private val viewModel: RickyAndMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
            rickyAndMortyAdapter = RickyAndMortyAdapter(applicationContext)
            recyclerView.adapter = rickyAndMortyAdapter
        }

        lifecycleScope.launchWhenStarted {
            viewModel.rickyMortyResponse.collect {
                when(it){
                    is RickyAndMortyUIState.Loading ->{
                       binding.progressLayout.visibility = VISIBLE
                    }

                    is RickyAndMortyUIState.Success ->{
                        rickyAndMortyAdapter.setDataList(it.data)
                        rickyAndMortyAdapter.notifyDataSetChanged()
                        binding.progressLayout.visibility = GONE
                    }

                    is RickyAndMortyUIState.Error ->{
                       it.message.toToast(this@HomeActivity).show()
                        binding.progressLayout.visibility = GONE
                    }
                    else -> Unit
                }
            }
        }
    }
}