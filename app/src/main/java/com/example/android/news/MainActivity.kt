package com.example.android.news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.indview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            var r =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(" https://newsapi.org/").build()//baseUrl
            //NewsAPI we need to add sub URL
            var api = r.create(NewsApi::class.java)//implementation for NewsApi(interface) using Retrofit
            var call: Call<Articles> = api.getNews()//calling abstract fun from implementation object
            //restApi Webservice call
            call.enqueue(object : Callback<Articles> {
                override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                    var artcls: Articles? = response.body()
                    lview.adapter = object : BaseAdapter() {

                        override fun getCount(): Int {
                            return artcls?.articles!!.size
                        }

                        override fun getItem(position: Int): Any {
                            return 0
                        }

                        override fun getItemId(position: Int): Long {
                            return 0
                        }

                        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                            val inflater = LayoutInflater.from(this@MainActivity)//layout inflater object
                            val v = inflater.inflate(R.layout.indview, null)//xml to view object

                            /*id*/v.title1.text = artcls?.articles!!.get(position).t
                            v.description1.text =
                                    artcls.articles.get(position).desc

                            //   Glide.with(this@MainActivity).load(artcls?.articles!!.get(position).).into(v.image)
                            return v
                        }
                    }
                    lview.setOnItemClickListener{
                        parent: AdapterView<*>?, view: View?, position: Int, id: Long ->  
                    }
                }

                override fun onFailure(call: Call<Articles>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed to get info..", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
