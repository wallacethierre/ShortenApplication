package com.project.shortenapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.project.shortenapplication.databinding.ActivityMainBinding
import com.project.shortenapplication.presentation.AliasViewModel
import com.project.shortenapplication.ui.adapter.AliasListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AliasListActivity : AppCompatActivity() {

    private val binding get() = _binding!!
    private var _binding: ActivityMainBinding? = null
    private val aliasViewModel: AliasViewModel by viewModels()

    @Inject
    lateinit var aliasAdapter: AliasListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setupRecycleView()
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
            val inputURLContent = binding.edtInputURL.text.toString()
            if (inputURLContent.isNotEmpty()) {
                aliasViewModel.doShortenURL(inputURLContent)
                binding.edtInputURL.text.clear()
                binding.recyclerView.scrollToPosition(0)
            } else {
                var alias = aliasViewModel.allAlias.value?.get(0)?.alias
                if (alias != null) {
                    binding.edtInputURL.error = "Field is empty, please insert url"
                    Snackbar.make(
                        binding.root,
                        aliasViewModel.getOriginalUrlByAlias(alias),
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                }

//                binding.edtInputURL.error = "Field is empty, please insert url"
//                Snackbar.make(
//                    binding.root,
//                    "Please enter the link to shorten",
//                    Snackbar.LENGTH_LONG
//                )
//                    .show()
            }
        }
    }
}