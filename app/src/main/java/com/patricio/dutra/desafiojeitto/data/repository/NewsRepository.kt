package com.patricio.dutra.desafiojeitto.data.repository

import com.patricio.dutra.desafiojeitto.data.datasource.NewsDataSource
import com.patricio.dutra.desafiojeitto.model.News
import com.patricio.dutra.desafiojeitto.utils.NewsResponse


class NewsRepository(var dataSource: NewsDataSource) {

        val listOfNews = ArrayList<News>()

        fun getNews(NewsResponse: NewsResponse<ArrayList<News>>) {

                dataSource.getNews(object : NewsResponse<ArrayList<News>> {
                        override fun success(result: ArrayList<News>) {
                                listOfNews.addAll(result)
                                NewsResponse.success(listOfNews)
                        }

                        override fun erro(result: String) {
                                NewsResponse.erro(result)
                                if(!listOfNews.isNullOrEmpty())
                                        NewsResponse.success(listOfNews)
                        }
                })
        }

}