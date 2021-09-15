package com.example.azkar.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azkar.R
import com.example.azkar.adapter.PagerAdapter
import com.example.azkar.adapter.RecyclerViewAdapter
import com.example.azkar.adapter.RecyclerViewAdapter.OnItemClickListener
import com.example.azkar.db.DatabaseAccess
import com.example.azkar.model.TabContent
import kotlinx.android.synthetic.main.fragment_tab.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

class TabFragment() : Fragment() {

    private var param1: String? = null
     var list = ArrayList<TabContent>()
    private var listener: PagerAdapter.FirstPageListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            list = it.getParcelableArrayList<TabContent>(ARG_PARAM2) as ArrayList<TabContent>
            listener = it.getSerializable(ARG_PARAM3) as PagerAdapter.FirstPageListener?
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.layoutManager = LinearLayoutManager(activity)

        val rvAdapter = RecyclerViewAdapter(activity, list, object : OnItemClickListener {
            override fun onItemClick(item: TabContent) {

                if (listener != null) {
                    //Toast.makeText(activity!!.applicationContext, "helllo2", Toast.LENGTH_LONG).show()
                    listener!!.setviewid(item.id)
                    listener!!.onSwitchToNextFragment(param1!!)
                } else {
                    Toast.makeText(activity!!.applicationContext, "error", Toast.LENGTH_LONG).show()
                }
            }
        })
        rv.adapter = rvAdapter
    }

    companion object {

        @JvmStatic

        fun newInstance(param1: String, list: ArrayList<TabContent>, listener: PagerAdapter.FirstPageListener) =
                TabFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putSerializable(ARG_PARAM3, listener)
                        putParcelableArrayList(ARG_PARAM2, list)
                    }

                }
    }

}