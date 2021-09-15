package com.example.azkar.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.Toast
import com.example.azkar.model.Tab
import com.example.azkar.model.TabContent

class DatabaseAccess {
    private var openHelper: DatabaseOpenHelper? = null
    private var db: SQLiteDatabase? = null

    companion object {
        private var instance: DatabaseAccess? = null
        fun getInstance(context: Context): DatabaseAccess? {
            if (instance == null) {
                instance = DatabaseAccess(context)
            }
            return instance
        }
    }

    constructor(context: Context) {
        openHelper = DatabaseOpenHelper(context)
    }

    fun open() {
        this.db = openHelper!!.writableDatabase
    }

    fun close() {
        this.db!!.close()
    }

    fun getAllContentsTabs(): ArrayList<TabContent> {
        var list = ArrayList<TabContent>()
        var c: Cursor
        c = db!!.rawQuery("SELECT * FROM AzkarCategories", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val tabContent = TabContent(c.getInt(0), c.getString(1))
            c.moveToNext()
            list.add(tabContent)
        }
        c.close()
        return list
    }

    fun getAzkae(id: Int): ArrayList<TabContent> {
        var list = ArrayList<TabContent>()
        var c: Cursor
        c = db!!.rawQuery("SELECT * FROM azkar ", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            var tabContent = TabContent(c.getInt(1), c.getString(2))
            if (tabContent.id == id) {
                list.add(tabContent)
            }
            c.moveToNext()
        }
        c.close()
        return list

    }

    fun getAllPrays(): ArrayList<TabContent> {
        Log.d("TAG", "we innnnn  getAllPrays")
        val list = ArrayList<TabContent>()
        val c: Cursor
        c = db!!.rawQuery("SELECT * FROM PrayersCategories", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val tabContent = TabContent(c.getInt(0), c.getString(1))
            c.moveToNext()
            list.add(tabContent)
        }
        c.close()
        Log.d("TAG", "${list.size}")
        return list
    }

    fun getAPrays(id: Int): ArrayList<TabContent> {
        var count = 0
        var list = ArrayList<TabContent>()
        var c: Cursor
        c = db!!.rawQuery("SELECT * FROM prayers ", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            var tabContent = TabContent(c.getInt(1), c.getString(2))
            if (tabContent.id == id) {
                list.add(tabContent)
                count++
            }
            c.moveToNext()
        }
        c.close()
        return list

    }


}
