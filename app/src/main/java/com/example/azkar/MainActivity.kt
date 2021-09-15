package com.example.azkar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.azkar.adapter.PagerAdapter
import com.example.azkar.db.DatabaseAccess
import com.example.azkar.fragments.CounterFragment
import com.example.azkar.fragments.NextFragment
import com.example.azkar.fragments.TabFragment
import com.example.azkar.model.Tab
import com.example.azkar.model.TabContent
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*



class MainActivity : AppCompatActivity() {


    val adapter = PagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(appbar)
        setupTabs()
        MobileAds.initialize(this) {
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }
    }

    private fun setupTabs() {
        var databaseAccess = DatabaseAccess.getInstance(this)
        databaseAccess!!.open()
        var list: ArrayList<TabContent>
        var praysList: ArrayList<TabContent>
        list = databaseAccess.getAllContentsTabs()
        praysList = databaseAccess.getAllPrays()
        var tab1 = Tab("أدعية ", TabFragment.newInstance("Tab2", praysList, adapter.listener))
        var tab2 = Tab("أذكار", TabFragment.newInstance("Tab1", list, adapter.listener))
        var tab3 = Tab("المسبحة الالكترونية", CounterFragment.newInstance("Tab3"))
        adapter.addTab(tab2)
        adapter.addTab(tab1)
        adapter.addTab(tab3)
        main_pager.adapter = adapter
        tabLayout.setupWithViewPager(main_pager)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (main_pager.getCurrentItem() == 0) {
            if (adapter.getItem(0) is NextFragment) {
                (adapter.getItem(0) as NextFragment).backPressed()
            } else if (adapter.getItem(0) is TabFragment) {
                finish()
            }
        } else if (main_pager.getCurrentItem() == 1) {

            if (adapter.getItem(1) is NextFragment) {
                (adapter.getItem(1) as NextFragment).backPressed()
            } else if (adapter.getItem(1) is TabFragment) {
                finish()
            }

        }
    }
}
