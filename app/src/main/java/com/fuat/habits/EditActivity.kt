package com.fuat.habits

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.fuat.habits.databinding.ActivityEditBinding
import java.lang.Exception

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    lateinit var sharedPreferences : SharedPreferences
    lateinit var db : SQLiteDatabase
    lateinit var cursor : Cursor
    var id : Int? = null
    var input : Int? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = this.getSharedPreferences("com.fuat.habits", MODE_PRIVATE)

        val intent = intent
        val day = intent.getStringExtra("day")
        val dayInt = day?.toInt()

        val today : Int = sharedPreferences.getInt("dayNumber", 0)
        id = dayInt!! - today + 1

        binding.textView.text = "Day " + id.toString()


        binding.habit1.text = sharedPreferences.getString("habit1", "")
        binding.habit2.text = sharedPreferences.getString("habit2", "")
        binding.habit3.text = sharedPreferences.getString("habit3", "")
        binding.habit4.text = sharedPreferences.getString("habit4", "")
        binding.habit5.text = sharedPreferences.getString("habit5", "")
        binding.habit6.text = sharedPreferences.getString("habit6", "")

        binding.habit1.setOnClickListener { clickHabit1() }
        binding.habit2.setOnClickListener { clickHabit2() }
        binding.habit3.setOnClickListener { clickHabit3() }
        binding.habit4.setOnClickListener { clickHabit4() }
        binding.habit5.setOnClickListener { clickHabit5() }
        binding.habit6.setOnClickListener { clickHabit6() }

        val h = arrayOf<TextView>(binding.habit1, binding.habit2, binding.habit3, binding.habit4, binding.habit5, binding.habit6)
        val hs = arrayOf<String>("h1", "h2", "h3", "h4", "h5", "h6")
        try {
            db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
            var x = 0
            while ( x < 6 ) {
                cursor = db.rawQuery("SELECT * FROM days WHERE id = $id", null)
                val hIndex = cursor.getColumnIndex( hs[x] )
                while (cursor.moveToNext()) {
                   input = cursor.getInt(hIndex)
                }
                if ( input!!.toInt() == 1) {
                    changeBG(h[x])
                    println(hs[x] + " is green")
                }
                if ( input!!.toInt() == 0) {
                    changeBGback(h[x])
                }

                x++
            }
            cursor.close()

        } catch (e : Exception) {
            e.printStackTrace()
        }

    }

    fun changeBG (tv : TextView) {
        tv.setBackgroundColor(Color.GREEN)
    }

    fun changeBGback (tv : TextView) {
        tv.setBackgroundColor(Color.TRANSPARENT)
    }

    fun clickHabit1() {
        try {
            db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
            cursor = db.rawQuery("SELECT * FROM days WHERE id = $id", null)

            val idIx = cursor.getColumnIndex("h1")
            while (cursor.moveToNext()) {
                input = cursor.getInt(idIx)
            }
            if( input == 0 ){
                binding.habit1.setBackgroundColor(Color.GREEN)
                db.execSQL("UPDATE days SET h1 = 1 WHERE id = $id")
            } else {
                binding.habit1.setBackgroundColor(Color.TRANSPARENT)
                db.execSQL("UPDATE days SET h1 = 0 WHERE id = $id")
            }
            cursor.close()

        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
    fun clickHabit2() {
        try {
            db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
            cursor = db.rawQuery("SELECT * FROM days WHERE id = $id", null)

            val idIx = cursor.getColumnIndex("h2")
            while (cursor.moveToNext()) {
                input = cursor.getInt(idIx)
            }
            if( input == 0 ){
                binding.habit2.setBackgroundColor(Color.GREEN)
                db.execSQL("UPDATE days SET h2 = 1 WHERE id = $id")
            } else {
                binding.habit2.setBackgroundColor(Color.TRANSPARENT)
                db.execSQL("UPDATE days SET h2 = 0 WHERE id = $id")
            }
            cursor.close()

        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
    fun clickHabit3 () {
        try {
            db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
            cursor = db.rawQuery("SELECT * FROM days WHERE id = $id", null)

            val idIx = cursor.getColumnIndex("h3")
            while (cursor.moveToNext()) {
                input = cursor.getInt(idIx)
            }
            if (input == 0) {
                binding.habit3.setBackgroundColor(Color.GREEN)
                db.execSQL("UPDATE days SET h3 = 1 WHERE id = $id")
            } else {
                binding.habit3.setBackgroundColor(Color.TRANSPARENT)
                db.execSQL("UPDATE days SET h3 = 0 WHERE id = $id")
            }
            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun clickHabit4 () {
        try {
            db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
            cursor = db.rawQuery("SELECT * FROM days WHERE id = $id", null)

            val idIx = cursor.getColumnIndex("h4")
            while (cursor.moveToNext()) {
                input = cursor.getInt(idIx)
            }
            if (input == 0) {
                binding.habit4.setBackgroundColor(Color.GREEN)
                db.execSQL("UPDATE days SET h4 = 1 WHERE id = $id")
            } else {
                binding.habit4.setBackgroundColor(Color.TRANSPARENT)
                db.execSQL("UPDATE days SET h4 = 0 WHERE id = $id")
            }
            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun clickHabit5 () {
        try {
            db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
            cursor = db.rawQuery("SELECT * FROM days WHERE id = $id", null)

            val idIx = cursor.getColumnIndex("h5")
            while (cursor.moveToNext()) {
                input = cursor.getInt(idIx)
            }
            if (input == 0) {
                binding.habit5.setBackgroundColor(Color.GREEN)
                db.execSQL("UPDATE days SET h5 = 1 WHERE id = $id")
            } else {
                binding.habit5.setBackgroundColor(Color.TRANSPARENT)
                db.execSQL("UPDATE days SET h5 = 0 WHERE id = $id")
            }
            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun clickHabit6 () {
        try {
            db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
            cursor = db.rawQuery("SELECT * FROM days WHERE id = $id", null)

            val idIx = cursor.getColumnIndex("h6")
            while (cursor.moveToNext()) {
                input = cursor.getInt(idIx)
            }
            if (input == 0) {
                binding.habit6.setBackgroundColor(Color.GREEN)
                db.execSQL("UPDATE days SET h6 = 1 WHERE id = $id")
            } else {
                binding.habit6.setBackgroundColor(Color.TRANSPARENT)
                db.execSQL("UPDATE days SET h6 = 0 WHERE id = $id")
            }
            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}