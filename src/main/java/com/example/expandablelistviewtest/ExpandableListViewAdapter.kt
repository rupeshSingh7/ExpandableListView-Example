package com.example.expandablelistviewtest

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView


/**
 * Created By Rupesh Kumar Singh
 */
class ExpandableListViewAdapter(
    mContext: Context,
    headerList: ArrayList<Contect>,
    childList: HashMap<Contect, ArrayList<Contect>>
): BaseExpandableListAdapter() {
    private var mheaderList = ArrayList<Contect>()
    private var mchildList = HashMap<Contect, ArrayList<Contect>>()
    private val context: Context
    init {
        this.mheaderList = headerList
        this.mchildList = childList
        this.context = mContext
    }

    override fun getGroupCount(): Int {
       return mheaderList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
       return mchildList[mheaderList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return mheaderList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return mchildList[mheaderList[groupPosition]]!![childPosition];
    }

    override fun getGroupId(groupPosition: Int): Long {
        return mheaderList[groupPosition].id
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return mchildList[mheaderList[groupPosition]]!![childPosition].id
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var rootView: View
        if (convertView == null) {
            rootView = LayoutInflater.from(context).inflate(
                com.example.expandablelistviewtest.R.layout.list_group,
                parent,
                false)

        }else {
            rootView = convertView
        }
        val list = mheaderList[groupPosition]
        val headerlevel = rootView.findViewById<TextView>(com.example.expandablelistviewtest.R.id.header_titleOne)
        val headerlevel2 = rootView.findViewById<TextView>(com.example.expandablelistviewtest.R.id.header_titleTwo)
        headerlevel.text = list.name
        headerlevel2.text = list.age.toString()
        return rootView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var rootView: View
        if (convertView == null) {
            rootView = LayoutInflater.from(context).inflate(
                com.example.expandablelistviewtest.R.layout.list_item,
                parent,
                false)

        }else {
            rootView = convertView
        }
        val list = mchildList[mheaderList[groupPosition]]!![childPosition]
        val headerlevel = rootView.findViewById<TextView>(com.example.expandablelistviewtest.R.id.list_titleOne)
        val headerlevel2 = rootView.findViewById<TextView>(com.example.expandablelistviewtest.R.id.list_titleTwo)
        val headerlevel3 = rootView.findViewById<TextView>(com.example.expandablelistviewtest.R.id.list_titleThree)
        headerlevel.text = list.name
        headerlevel2.text = list.age.toString()
        headerlevel3.text = list.multiAddress
        return rootView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
      return true
    }

}