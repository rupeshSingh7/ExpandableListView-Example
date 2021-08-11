package com.example.expandablelistviewtest

import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.ExpandableListView.OnGroupExpandListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created By Rupesh Kumar
 */

class MainActivity : AppCompatActivity() {
    var expendableListView: ExpandableListView? = null
    var expendableListViewAdapter: ExpandableListViewAdapter? = null
    private var mheaderList = ArrayList<Contect>()
    private var mchildList = HashMap<Contect, ArrayList<Contect>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        expendableListView = findViewById(R.id.expendableList)
        populateData()
        expendableListViewAdapter = ExpandableListViewAdapter(this, mheaderList, mchildList)
        expendableListView!!.setAdapter(expendableListViewAdapter)

        // Listview on child click listener
        expendableListView!!.setOnChildClickListener(OnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val data = "parent Address:" + mheaderList[groupPosition].multiAddress + "\n child: " +
                    mchildList[mheaderList[groupPosition]]!![childPosition].multiAddress
            showToast(data)
            false
        })

        // Listview Group expanded listener
        expendableListView!!.setOnGroupExpandListener(OnGroupExpandListener { groupPosition ->
          val s = mheaderList.get(groupPosition).toString()
            showToast(s)
        })
    }
    fun populateData(){
        for (i in 2..10 step 2){
        mheaderList.add(
            Contect(
                1,
                "Rupesh $i",
                i * i * 5,
                "delhi $i, mumbai $i, patna $i, ranchi $i"
            )
        )
        }
         mheaderList.forEach {
             val  data =  ArrayList<Contect>()
             val addresses = it.multiAddress.split(",")
             for (address in addresses){
                 var c = it.copy()
                 c.multiAddress = address
                 c.name = c.name +", child"
                 data.add(c)
             }
             mchildList[it] = data
        }
    }
    fun showToast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}