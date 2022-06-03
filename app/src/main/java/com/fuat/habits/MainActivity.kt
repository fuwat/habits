package com.fuat.habits

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.fuat.habits.databinding.ActivityMainBinding
import java.util.*
import java.util.Calendar.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var daysList: ArrayList<Day>
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        daysList = ArrayList<Day>()
        val today = Calendar.getInstance().get(DAY_OF_MONTH)
        val dayOfWeek = Calendar.getInstance().get(DAY_OF_WEEK)
        val thisMonth = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
        println(today)
        println(dayOfWeek)
        println(thisMonth)



        var x = today
        var y = 0
        while (y < 21){
            daysList.add(Day(x.toString(), thisMonth))
            println(daysList[y].day + " " + daysList[y].month)
            y++
            x++
        }
        /*
        var sqlin : Int = 0
        try {
            val db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
            val cursor = db.rawQuery("SELECT * FROM days WHERE id = 1", null)
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()) {
                sqlin = cursor.getInt(idIx)
            }

            cursor.close()

        } catch (e : Exception) {
            e.printStackTrace()
        }

        println(sqlin)
        binding.sqlTest.text = sqlin.toString()*/




    }

    fun goToDays(view: View) {
        sharedPreferences = this.getSharedPreferences("com.fuat.habits", MODE_PRIVATE)
        if (sharedPreferences.getString("habit1", "") != "") {
            val intent = Intent(this, DaysActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "First create new habits", Toast.LENGTH_LONG).show()
        }
    }

    fun goToCreate(view: View) {
        val intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }

    fun goToInfo(view : View) {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }


}