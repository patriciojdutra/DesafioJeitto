package com.patricio.dutra.desafiojeitto

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.patricio.dutra.desafiojeitto.model.News
import com.patricio.dutra.desafiojeitto.utils.Constants
import com.patricio.dutra.desafiojeitto.utils.Endpoint
import com.patricio.dutra.desafiojeitto.utils.RecyclerAdapter
import com.patricio.dutra.desafiojeitto.utils.Retrofit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_list_news.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    var lista = arrayListOf<News>()
    var act: Activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                var listaAuxiliar = arrayListOf<News>()
                listaAuxiliar.addAll(lista)

                for (item in lista){
                    if(!item.title.contains(newText)){
                        listaAuxiliar.remove(item)
                    }
                }

                loadList(listaAuxiliar)

                return false
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    getNews()
                }
            }
        })
    }


    fun getNews(){

        if(lista.isEmpty())
            progressBar.visibility = View.VISIBLE
        else
            progressBar2.visibility = View.VISIBLE

        val retrofitClient = Retrofit.getRetrofitInstance(Constants.URLSERVER)
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getNews(Constants.VALUEMAX,Constants.VALUESTART)

        callback.enqueue(object : Callback<List<News>> {

            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                response?.let {
                    it.body()?.let { resultList ->

                        if(lista.isEmpty()) {

                            lista.addAll(resultList as ArrayList<News>)
                            Constants.setCurrentValue()
                            loadList(lista)

                            progressBar.visibility = View.GONE

                        }else{

                            lista.addAll(resultList as ArrayList<News>)
                            Constants.setCurrentValue()
                            updateList(lista)

                            progressBar2.visibility = View.GONE

                        }

                    }

                    it.errorBody()?.let{ result ->

                        var json:JSONObject = JSONObject(result.string())
                        failDialog(json.getString("message"))

                    }
                }
            }

            override fun onFailure(result: Call<List<News>>, t: Throwable) {

                progressBar.visibility = View.GONE
                progressBar2.visibility = View.GONE
                failDialog(t.message.toString())

            }
        })
    }

    fun loadList(lista: List<News>){
        linearLayoutManager = LinearLayoutManager ( this )
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(lista,this)
        recyclerView.adapter = adapter
    }

    fun updateList(lista: List<News>){
        var insertIndex = lista.size
        adapter.notifyItemRangeInserted(insertIndex,lista.size)
    }

    fun failDialog(msg:String){

        lateinit var dialog: Dialog

        val builder = AlertDialog.Builder(act)
        builder.setTitle(Constants.TITLEDIALOG)
        builder.setMessage(msg)
        builder.setCancelable(false)
        builder.setPositiveButton(Constants.OK, DialogInterface.OnClickListener { dialog, which ->
            finish()
        })
        dialog = builder.create()
        dialog.show()

    }
}