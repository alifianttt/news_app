package com.submission.newsapp.ui.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import com.submission.gameapp.databinding.ActivityDetailNewsBinding

class DetailNews : AppCompatActivity() {
    private val binding: ActivityDetailNewsBinding by lazy {
        ActivityDetailNewsBinding.inflate(layoutInflater)
    }
    companion object{
        const val URL_KEY = "url"
        fun newIntent(context: Context, url: String) = Intent(context, DetailNews::class.java).apply {
            putExtra(URL_KEY, url)
        }
    }
    private var urlNews = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        urlNews = intent.getStringExtra(URL_KEY) ?: ""
        binding.webArticle.settings.javaScriptEnabled = true
        binding.webArticle.webChromeClient = WebChromeClient()
        binding.webArticle.loadUrl(urlNews)
    }
}