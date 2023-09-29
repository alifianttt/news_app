package com.submission.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.newsapp.adapter.NewsAdapter
import com.submission.newsapp.data.source.remote.network.Status
import com.submission.gameapp.databinding.ActivityMainBinding
import com.submission.newsapp.ext.ConnectionLiveData
import com.submission.newsapp.ext.setVisible
import com.submission.newsapp.model.Source
import com.submission.newsapp.ui.home.SourceActivity
import com.submission.newsapp.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val newsViewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this)[NewsViewModel::class.java]
    }

    private val conneectionStatus: ConnectionLiveData by lazy {
        ConnectionLiveData(this)
    }

    private var listNews : ArrayList<Source> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private val listener  = object : NewsAdapter.NewsListener{
        override fun onClick(source: Source) {
            startActivity(SourceActivity.newIntent(this@MainActivity, source.category))
        }

    }
    private fun initView(){
        lifecycleScope.launch {
            newsViewModel.fetchNewsSource()
        }

        binding.swrList.isEnabled = false
        conneectionStatus.observe(this){ isConnect ->
            binding.layoutNetwork.root.setVisible(!isConnect)
        }

        binding.layoutNetwork.imgClose.setOnClickListener {
            binding.layoutNetwork.root.setVisible(false)
        }
        newsViewModel.getNewsSource().observe(this){
            when(it.status){
                Status.LOADING -> binding.swrList.isRefreshing = true
                Status.ERROR -> binding.swrList.isRefreshing = false
                Status.SUCCES -> {
                    binding.swrList.isRefreshing = false
                    listNews.clear()
                    it.data?.let { listSource -> listNews.addAll(listSource.sources.distinctBy { dist -> dist.category }) }
                    binding.rvNews.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = NewsAdapter(listNews, listener)
                    }

                }
            }
        }
    }
}