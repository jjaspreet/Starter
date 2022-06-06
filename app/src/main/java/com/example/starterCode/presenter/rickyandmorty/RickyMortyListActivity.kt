package com.example.starterCode.presenter.rickyandmorty

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.starterCode.databinding.ActivityRickyMortyListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RickyMortyListActivity : AppCompatActivity() {

    private lateinit var rickyAndMortyAdapter: RickyAndMortyAdapter
    private lateinit var binding: ActivityRickyMortyListBinding

    private val viewModel: RickyAndMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRickyMortyListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
            rickyAndMortyAdapter = RickyAndMortyAdapter(applicationContext)
            recyclerView.adapter = rickyAndMortyAdapter
        }


    }
}