package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyAdapter.Listener {
    private var myAdapter: MyAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var commentList: ArrayList<comments>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        loadData()
    }
    //Initialise the RecyclerView//

    private fun initRecyclerView() {

//Use a layout manager to position your items to look like a standard ListView//

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        comments_list.layoutManager = layoutManager

    }
    var BASE_URL : String = "https://jsonplaceholder.typicode.com/"
    private fun loadData() {

//Build a Retrofit object//

        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

//Get a usable Retrofit object by calling .build()//

            .build().create(getData::class.java)
        //Add all RxJava disposables to a CompositeDisposable//

        //Add all RxJava disposables to a CompositeDisposable//

        myCompositeDisposable?.add(requestInterface.getData()

//Send the Observable’s notifications to the main UI thread//

            .observeOn(AndroidSchedulers.mainThread())

//Subscribe to the Observer away from the main UI thread//

            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }
    private fun handleResponse(cryptoList: List<comments>) {

        commentList = ArrayList(cryptoList)
        myAdapter = MyAdapter(commentList!!, this)

//Set the adapter//

        comments_list.adapter = myAdapter

    }
    override fun onItemClick(comment: comments) {

//If the user clicks on an item, then display a Toast//

        Toast.makeText(this, "You clicked: ${comment.body}", Toast.LENGTH_LONG).show()

    }

    override fun onDestroy() {
        super.onDestroy()

//Clear all your disposables//

        myCompositeDisposable?.clear()

    }
}