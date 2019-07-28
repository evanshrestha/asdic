package com.evanshrestha.asdic

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.evanshrestha.asdic.R.id.seriesInfoFanartImage
import com.squareup.picasso.Picasso

class SeriesInfoActivity : AppCompatActivity() {

    var SERIES : Series? = null
    var seriesInfoTitleTextView : TextView? = null
    var seriesInfoYearTextView : TextView? = null
    var seriesInfoCertificationTextView : TextView? = null
    var seriesInfoFanartImageView : ImageView? = null
    var seriesInfoPosterImageView : ImageView? = null

    var seriesInfoDescriptionView : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_info)
        SERIES = intent.getParcelableExtra("SERIES")
        seriesInfoTitleTextView = findViewById<TextView>(R.id.seriesInfoTitleText)
        seriesInfoYearTextView = findViewById<TextView>(R.id.seriesInfoYearText)
        seriesInfoCertificationTextView = findViewById<TextView>(R.id.seriesInfoCertificationText)
        seriesInfoFanartImageView = findViewById<ImageView>(R.id.seriesInfoFanartImage)
        seriesInfoPosterImageView = findViewById<ImageView>(R.id.seriesInfoPosterImage)
        seriesInfoDescriptionView = findViewById<TextView>(R.id.seriesDescriptionText)

        seriesInfoTitleTextView?.isSelected = true

        Picasso.get().load(SERIES?.imagePosterURL).into(seriesInfoPosterImageView)
        Picasso.get().load(SERIES?.imageFanartURL).into(seriesInfoFanartImageView)
        seriesInfoTitleTextView?.text = SERIES?.title
        seriesInfoYearTextView?.text = SERIES?.year
        seriesInfoCertificationTextView?.text = SERIES?.certification
        seriesInfoDescriptionView?.text = SERIES?.description
    }
}
