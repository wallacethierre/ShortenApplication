package com.project.shortenapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.project.shortenapplication.databinding.ActivityMainBinding
import com.project.shortenapplication.presentation.AliasViewModel
import com.project.shortenapplication.ui.adapter.LinksItemsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding get() = _binding!!
    private var _binding: ActivityMainBinding? = null
    private val aliasViewModel: AliasViewModel by viewModels()

    @Inject
    lateinit var aliasAdapter: LinksItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setupRecycleView()
        //TODO: Remove this
        insertITens()
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
                var layoutManager: LinearLayoutManager =
                    binding.recyclerView.layoutManager as LinearLayoutManager
                layoutManager.scrollToPositionWithOffset(0, 0)
            } else {
                binding.edtInputURL.error = "Field is empty, please insert url"
                Snackbar.make(
                    binding.root,
                    "Please enter the link to shorten",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    //TODO: Remove this
    fun insertITens() {
        aliasViewModel.doShortenURL("https://support.google.com/legal/answer/3463239?hl=en")

//        Thread.sleep(2000)
//        aliasViewModel.doShortenURL("https://www.google.com/url?sa=i&url=https%3A%2F%2Fgeekverso.com%2Fdemon-slayer-kimetsu-no-yaiba-the-movie-mugen-train-ganha-novo-visual-e-video-promocional%2F&psig=AOvVaw1L1p4iWhP1r0C9H_r0yykd&ust=1669234222325000&source=images&cd=vfe&ved=0CBIQ3YkBahcKEwjY9fWYzML7AhUAAAAAHQAAAAAQBA")
//        aliasViewModel.doShortenURL("https://www.crunchyroll.com/pt-br/anime-news/2021/06/19/vendas-de-demon-slayer-kimetsu-no-yaiba-the-movie-mugen-train-passam-de-1-milho-de-cpias-em-apenas-3-dias-no-japo")
//        aliasViewModel.doShortenURL("https://www.google.com/url?sa=i&url=https%3A%2F%2Fmubi.com%2Fpt%2Ffilms%2Fdemon-slayer-kimetsu-no-yaiba-the-movie-mugen-train&psig=AOvVaw1L1p4iWhP1r0C9H_r0yykd&ust=1669234222325000&source=images&cd=vfe&ved=0CBIQ3YkBahcKEwjY9fWYzML7AhUAAAAAHQAAAAAQDg")
//        aliasViewModel.doShortenURL("https://mubi.com/pt/films/demon-slayer-kimetsu-no-yaiba-the-movie-mugen-train")
//        aliasViewModel.doShortenURL("https://www.crunchyroll.com/pt-br/anime-news/2020/08/02/demon-slayer-kimetsu-no-yaiba-the-movie-mugen-train-ganha-novo-vdeo-promocional-e-prvia-da-msica-tema-por-lisa")
//        aliasViewModel.doShortenURL("https://criticalhits.com.br/tag/demon-slayer-kimetsu-no-yaiba-the-movie-mugen-train/")
//        aliasViewModel.doShortenURL("https://www.animeunited.com.br/noticias/animes/kimetsu-no-yaiba-the-movie-mugen-train-trailer/")
//        aliasViewModel.doShortenURL("https://www.animesxis.com.br/2020/12/28/recorde-kimetsu-no-yaiba-the-movie-mugen-train-filme-anime-se-torna-a-maior-bilheteria-da-historia-japonesa/")
//        aliasViewModel.doShortenURL("https://www.animesxis.com.br/2020/12/21/kimetsu-no-yaiba-the-movie-mugen-train-filme-anime-esta-a-%C2%A5-520-milhoes-para-ultrapassar-a-viagem-de-chihiro/")
//        aliasViewModel.doShortenURL("https://www.google.com/search?q=kimetsu+no+yaiba+the+movie+mugen+train&rlz=1C5CHFA_enBR972BR972&sxsrf=ALiCzsZgIRx2Cn0E3Cb3JSnTTcEaSZPuFw:1669147817768&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjMt4WWzML7AhXSQ7gEHXocCTIQ_AUoA3oECAMQBQ&biw=1440&bih=732&dpr=2#imgrc=MaUIWZTVltE6-M")
//        aliasViewModel.doShortenURL("https://voi.id/en/lifestyle/24464/demon-slayer-mugen-train-jadi-film-terlaris-sepanjang-masa-di-jepang")
//        aliasViewModel.doShortenURL("https://www.republika.co.id/berita/quugbd463/demon-slayer-mugen-train-hadir-di-catchplay")
//        aliasViewModel.doShortenURL("https://www.ign.com/articles/demon-slayer-the-movie-mugen-train-review")
//        aliasViewModel.doShortenURL("https://cinepop.com.br/demon-slayer-mugen-train-o-filme-293006/")
//        aliasViewModel.doShortenURL("https://www.google.com/imgres?imgurl=https%3A%2F%2Fi.pinimg.com%2Foriginals%2F99%2Fbf%2Fc1%2F99bfc1b969f13b71fd65e69409bfc5e6.jpg&imgrefurl=https%3A%2F%2Fbr.pinterest.com%2Fpin%2F591871576005298547%2F&tbnid=yv26tn5c0_Dc5M&vet=12ahUKEwi3iMzCq7v7AhXMN7kGHQVHBjUQMygDegUIARDsAQ..i&docid=7KMDytoGL2sbBM&w=1080&h=1920&q=sport%20recife&ved=2ahUKEwi3iMzCq7v7AhXMN7kGHQVHBjUQMygDegUIARDsAQ")
    }
}