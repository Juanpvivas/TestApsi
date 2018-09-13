package com.juanvivas.testapsi.presentation.implementation

import android.content.Context
import android.support.constraint.R.id.parent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.juanvivas.testapsi.R
import com.juanvivas.testapsi.model.App
import com.juanvivas.testapsi.model.Search
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_detail.view.*
import org.jetbrains.anko.startActivity

class DetailSearchAdapter(val requests: ArrayList<Search>) : RecyclerView.Adapter<DetailSearchAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return requests.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setRequest(requests[position])
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name_card_activity)
        val imageView = view.findViewById<ImageView>(R.id.tv_image_card_activity)
        val tvLocation = view.findViewById<TextView>(R.id.tv_location_card_activity)
        val tvPrice = view.findViewById<TextView>(R.id.tv_price_card_activity)


        fun setRequest(request: Search) {
            tvName.text = request.title
            Picasso.with(App.mContext).load(request.image)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .noPlaceholder().centerCrop().fit().into((imageView))
            tvLocation.text = request.path
            tvPrice.text = request.price
        }


    }


}