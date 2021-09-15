package com.example.azkar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azkar.R
import com.example.azkar.adapter.NextRecyclerViewAdapter
import com.example.azkar.adapter.PagerAdapter
import com.example.azkar.adapter.PagerAdapter.FirstPageListener
import com.example.azkar.adapter.RecyclerViewAdapter
import com.example.azkar.db.DatabaseAccess
import com.example.azkar.model.TabContent
import kotlinx.android.synthetic.main.fragment_next.*
import kotlinx.android.synthetic.main.fragment_tab.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"




class NextFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id_categoery: Int = 0
    private  var position:Int=0
    private var listener: FirstPageListener? = null
    private var TabTiltle:String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id_categoery = it.getInt(ARG_PARAM1)
            listener = it.getSerializable(ARG_PARAM2) as PagerAdapter.FirstPageListener?
            position =it.getInt(ARG_PARAM3)
            TabTiltle =it.getString(ARG_PARAM4)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_next, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNextFragment.layoutManager = LinearLayoutManager(activity)
        var list= getDataFromDataBase(position)
        val rvAdapter = NextRecyclerViewAdapter(activity, list)
        rvNextFragment.adapter = rvAdapter
    }
    fun getDataFromDataBase(position: Int):ArrayList<TabContent>{
        var databaseAccess = DatabaseAccess.getInstance(activity!!.applicationContext)
        databaseAccess!!.open()
        if (position==0){
            return databaseAccess.getAzkae(id_categoery)
        }
        return databaseAccess.getAPrays(id_categoery)
    }

    fun backPressed() {
        listener!!.onSwitchToNextFragment(TabTiltle!!);
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(id_categoery: Int, listener: FirstPageListener, position:Int,TabTiltle:String) =
                NextFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, id_categoery)
                        putSerializable(ARG_PARAM2, listener)
                        putInt(ARG_PARAM3,position)
                        putString(ARG_PARAM4,TabTiltle)
                        Log.d("tag", "we in next fragment")


                    }
                }
    }
}