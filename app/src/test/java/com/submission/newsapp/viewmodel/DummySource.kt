package com.submission.newsapp.viewmodel

import com.submission.newsapp.model.BaseSource
import com.submission.newsapp.model.Source

object DummySource {
    fun generateSource(): BaseSource{
        val listSource = ArrayList<Source>()
        for (i in 0 .. 15){
            val source = Source(
                "bussines",
                "en",
                "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com",
                "abc-news",
                "eng",
                "ABC News",
                "https://abcnews.go.com"
            )
            listSource.add(source)
        }

        val baseSource = BaseSource(listSource, "ok")
        return baseSource
    }
}