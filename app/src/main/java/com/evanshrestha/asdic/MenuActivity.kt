package com.evanshrestha.asdic

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.json.JSONObject
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import java.net.URL

class MenuActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var IP_ADDRESS : String? = null
    private var PORT : String? = null
    private var API_KEY : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        IP_ADDRESS = intent.getStringExtra("IP")
        PORT = intent.getStringExtra("PORT")
        API_KEY = intent.getStringExtra("API_KEY")

        val seriesItems : ArrayList<Series> = ArrayList()

        // Pull series info
        doAsync {
            val result = URL("http://" + IP_ADDRESS + ":" + PORT + "/api/series?apikey=" + API_KEY).readText()
            try {
                val seriesArray = JSONArray(result)
                for (seriesIndex in 0 until seriesArray.length()) {
                    val seriesItem : JSONObject = seriesArray.getJSONObject(seriesIndex)
                    val seriesImages : JSONArray = seriesItem.optJSONArray("images")

                    var seriesImagePosterURL : String? = null
                    var seriesImageFanartURL : String? = null

                    for (seriesImageIndex in 0 until seriesImages.length()) {
                        val seriesImageItem = seriesImages.optJSONObject(seriesImageIndex)
                        if (seriesImageItem.optString("coverType").equals("poster")) {
                            seriesImagePosterURL = "http://" + IP_ADDRESS + ":" + PORT + "/api" + seriesImageItem.optString("url") + "&apikey=" + API_KEY
                        } else if (seriesImageItem.optString("coverType").equals("fanart")) {
                            seriesImageFanartURL = "http://" + IP_ADDRESS + ":" + PORT + "/api" + seriesImageItem.optString("url") + "&apikey=" + API_KEY
                        }
                    }

                    val newSeries : Series = Series()
                    newSeries.title = seriesItem.optString("title")
                    newSeries.imageFanartURL = seriesImageFanartURL
                    newSeries.imagePosterURL = seriesImagePosterURL
                    newSeries.certification = seriesItem.optString("certification")
                    newSeries.year = seriesItem.optString("year")
                    newSeries.description = seriesItem.optString("overview")
                    seriesItems.add(newSeries)

                }
            } catch (e: Exception) {
                uiThread {
                    toast("Whoopsie")
                }
                e.printStackTrace()
            }
        }

        linearLayoutManager = LinearLayoutManager(this)

        viewAdapter = RecyclerSeriesAdapter(seriesItems)
        var recycler = findViewById<RecyclerView>(R.id.recycle_shows)
        recycler.apply {
            layoutManager = linearLayoutManager
            adapter = viewAdapter
        }
    }
}
