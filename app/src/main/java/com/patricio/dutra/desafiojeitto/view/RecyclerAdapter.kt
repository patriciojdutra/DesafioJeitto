package com.patricio.dutra.desafiojeitto.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.patricio.dutra.desafiojeitto.R
import com.patricio.dutra.desafiojeitto.model.News
import com.squareup.picasso.Picasso


class RecyclerAdapter (private val newsList: List<News>, var act: Activity): RecyclerView.Adapter<RecyclerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_news,parent,false) as View
        return Holder(v)

    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.txtQuant.setText((position+1).toString())
        holder.txtHeadNews.setText(newsList[position].newsSite)
        holder.txtBodyNews.setText(newsList[position].title)
        Picasso.get().load(newsList[position].imageUrl)
            .resize(150, 150)
            .centerCrop()
            .into(holder.imageView);

        holder.layout.setOnClickListener {

            lateinit var dialog: Dialog

            var view = View.inflate(act, R.layout.dialog_layout_news,null)
            view.findViewById<TextView>(R.id.txtViewTitle).setText(newsList[position].title)
            view.findViewById<TextView>(R.id.txtViewBody).setText(newsList[position].summary)
            view.findViewById<ImageView>(R.id.imageViewPhoto).setImageDrawable(holder.imageView.drawable)

            view.findViewById<Button>(R.id.btnRead).setOnClickListener {
                val uris = Uri.parse(newsList[position].url)
                act.startActivity(Intent(Intent.ACTION_VIEW, uris))
            }

            view.findViewById<Button>(R.id.btnBack).setOnClickListener {
                dialog.dismiss()
            }

            val builder = AlertDialog.Builder(act)
            builder.setView(view)
            dialog = builder.create()
            dialog.show()

        }

    }

    class Holder(v: View) : RecyclerView.ViewHolder(v) {

        val txtBodyNews = v.findViewById<TextView>(R.id.txtBodyNews)
        val txtHeadNews = v.findViewById<TextView>(R.id.txtHeadNews)
        val imageView = v.findViewById<ImageView>(R.id.imageView3)
        val txtQuant = v.findViewById<TextView>(R.id.txtQuant)
        val layout = v.findViewById<ConstraintLayout>(R.id.layout)
    }

}



