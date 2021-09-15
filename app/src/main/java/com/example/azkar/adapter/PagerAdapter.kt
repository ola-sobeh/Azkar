package com.example.azkar.adapter

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.azkar.R
import com.example.azkar.fragments.NextFragment
import com.example.azkar.fragments.TabFragment
import com.example.azkar.model.Tab
import java.io.Serializable


class PagerAdapter(var fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var TabsList = ArrayList<Tab>()
    private val context: Context? = null
    var listener = FirstPageListener()
    var id: Int = 0

    companion object {
        var mFragmentAtPos0: Fragment? = null
        var mFragmentAtPos02: Fragment? = null
    }

    override fun getCount(): Int {
        return TabsList.size
    }

    override fun getItem(position: Int): Fragment {
        Log.d("TAG", "we in getItem method")

        when (position) {

            0 -> {
                var boolean = false
                if (mFragmentAtPos0 == null) {
                    mFragmentAtPos0 = TabsList[position].fragment
                }

                return mFragmentAtPos0!!

            }
            1 -> {

                if (mFragmentAtPos02 == null) {
                    mFragmentAtPos02 = TabsList[position].fragment
                }
                return mFragmentAtPos02!!
            }
            2 -> {
                 return TabsList[position].fragment
            }
        }
        return mFragmentAtPos0!!
    }


    override fun getPageTitle(position: Int): CharSequence? {
        Log.d("TAG", "we in getPageTitle method ")
        return TabsList[position].title
    }

    override fun getItemPosition(`object`: Any): Int {
        Log.d("TAG", "we in getItemPosition method ")

        if (`object` is TabFragment &&
            mFragmentAtPos0 is NextFragment || `object` is TabFragment && mFragmentAtPos02 is NextFragment
        ) {
            Log.d("TAG", "before 1 getItemPosition method ")

            return POSITION_NONE
        } else if (`object` is NextFragment &&
            mFragmentAtPos0 is TabFragment || `object` is NextFragment && mFragmentAtPos02 is TabFragment
        ) {
            Log.d("TAG", "before 2 getItemPosition method ")
            return POSITION_NONE
        } else return POSITION_UNCHANGED
    }

    fun addTab(tab: Tab) {
        TabsList.add(tab)
    }


    inner class FirstPageListener :
        FirstPageFragmentListener, Serializable {
        override fun onSwitchToNextFragment(TabTitle: String) {
            if (TabTitle.equals("Tab1")) {
                var v: Boolean? = null
                v = mFragmentAtPos0!! is TabFragment
                Log.d("TAG", "$v")
                fm.beginTransaction().replace(R.id.main_pager, mFragmentAtPos0!!)
                    .addToBackStack(null)
                    .commit()
                //notifyDataSetChanged()
                if (mFragmentAtPos0 is TabFragment) {
                    mFragmentAtPos0 = NextFragment.newInstance(id, listener, 0, "Tab1")
                } else {
                    Log.d("TAG", "oolalaaaaaaaaaaaaaaa")
                    var tabFragment = TabsList[0].fragment as TabFragment
                    mFragmentAtPos0 = TabFragment.newInstance("Tab1", tabFragment.list, listener)
                }
                notifyDataSetChanged()
                Log.d("tag", " after  notifyDataSetChanged()")
            } else {
                var v: Boolean? = null
                v = mFragmentAtPos02!! is TabFragment
                Log.d("TAG", "$v")
                fm.beginTransaction().replace(R.id.main_pager, mFragmentAtPos02!!)
                    .addToBackStack(null)
                    .commit()
                if (mFragmentAtPos02 is TabFragment) {
                    mFragmentAtPos02 = NextFragment.newInstance(id, listener, 1, "Tab2")

                } else {
                    var tabFragment = TabsList[1].fragment as TabFragment
                    mFragmentAtPos02 = TabFragment.newInstance("Tab2", tabFragment.list, listener)
                }
                notifyDataSetChanged()
                Log.d("tag", " after  notifyDataSetChanged()")
            }
        }

        fun setviewid(mmm: Int) {
            id = mmm
        }
    }

    interface FirstPageFragmentListener {
        fun onSwitchToNextFragment(TabTitle: String)
    }

}

