package com.project.shortenapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.shortenapplication.R
import com.project.shortenapplication.databinding.ActivityMainBinding
import com.project.shortenapplication.presentation.AliasViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding get() = _binding!!
    private var _binding: ActivityMainBinding? = null
    private val aliasViewModel: AliasViewModel by viewModels()

    private val TAG = "INTERVIEW"

    @Inject
    lateinit var aliasAdapter: LinksItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setupRecycleView()
        Log.i(TAG, "qtd items inserted: ${aliasViewModel.allAlias.value}")
    }

    private fun setupRecycleView() {
        aliasViewModel.allAlias.observe(this) {
            aliasAdapter.addData(it.reversed())
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = aliasAdapter

            aliasAdapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private fun initViews() {
        binding.button.setOnClickListener {
            aliasViewModel.doShortenURL(binding.edtInputURL.text.toString())
            binding.edtInputURL.text.clear()
            var layoutManager: LinearLayoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
            layoutManager.scrollToPositionWithOffset(0, 0);
        }
    }
}