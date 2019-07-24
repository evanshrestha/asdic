package com.evanshrestha.asdic

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.toast

class RecyclerSeriesAdapter(val items: ArrayList<Series>) : RecyclerView.Adapter<RecyclerSeriesAdapter.ViewHolder>() {


    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_series_listitem, parent, false)) {
        private var seriesTitleTextView : TextView? = null
        private var seriesImageView : ImageView? = null
        private var seriesCertificationTextView : TextView? = null
        private var seriesYearTextView : TextView? = null

        init {
            seriesTitleTextView = itemView.findViewById(R.id.seriesTitleText)
            seriesCertificationTextView = itemView.findViewById(R.id.seriesCertificationText)
            seriesYearTextView = itemView.findViewById(R.id.seriesYearText)
            seriesImageView = itemView.findViewById(R.id.seriesImageView)
            itemView.setOnClickListener {
                Log.d("Asdic", adapterPosition.toString())
            }
        }

        fun bind(series : Series) {
            seriesTitleTextView?.text = series.title
            seriesCertificationTextView?.text = series.certification
            seriesYearTextView?.text = series.year
            Picasso.get().load(series.imageURL).into(seriesImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series : Series = items[position]
        holder.bind(series)

    }

    override fun getItemCount() = items.size


}