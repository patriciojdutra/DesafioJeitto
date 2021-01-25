package com.patricio.dutra.desafiojeitto.view

import android.animation.ObjectAnimator
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patricio.dutra.desafiojeitto.R
import com.patricio.dutra.desafiojeitto.model.News
import com.patricio.dutra.desafiojeitto.utils.Constants
import com.patricio.dutra.desafiojeitto.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_list_news.*


class MainActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null)
            viewModel.getNews()
        else{
            loadList(viewModel.listNews.value!!)
            progressBar.visibility = View.GONE
        }

        viewModel.listNews.observe(this, Observer {
            if(it.size <= Constants.VALUEMAX) loadList(it) else updateList()
        })

        viewModel.loading.observe(this, Observer {
            if(it)
                if(viewModel.listNews.value.isNullOrEmpty()) progressBar.visibility = View.VISIBLE else progressBar2.visibility = View.VISIBLE
            else
                if(viewModel.listNews.value.isNullOrEmpty()) progressBar.visibility = View.GONE else progressBar2.visibility = View.GONE
        })

        viewModel.error.observe(this, Observer {
            failDialog(it)
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                if(newText.isEmpty()) {
                    loadList(viewModel.listNews.value!!)
                    return false
                }

                var supportList = ArrayList<News>()
                supportList.addAll(viewModel.listNews.value!!)

                for (item in viewModel.listNews.value!!){
                    if(!item.title?.contains(newText)!!){
                        supportList.remove(item)
                    }
                }

                loadList(supportList)
                return false
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE && searchView.query.isEmpty()) {
                    viewModel.getNews()
                }
            }
        })
    }

    fun loadList(list: List<News>){

        ObjectAnimator.ofFloat(cardView, "translationX", 1000f, 0f).apply {
            duration = 2000
            start()
        }

        val linearLayoutManager = LinearLayoutManager ( this )
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(list, this)
        recyclerView.adapter = adapter
    }

    fun updateList(){
        adapter.notifyItemRangeInserted(Constants.VALUESTART, Constants.VALUEMAX)
    }

    fun failDialog(msg:String){

        lateinit var dialog: Dialog

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.title_dialog)
        builder.setMessage(msg)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, which ->
            finish()
        })
        dialog = builder.create()
        dialog.show()

    }
}