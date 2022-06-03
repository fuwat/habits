package com.fuat.habits

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.fuat.habits.databinding.ActivityDaysBinding
import com.fuat.habits.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList

class DaysActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDaysBinding
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaysBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = this.getSharedPreferences("com.fuat.habits", MODE_PRIVATE)
        val startBox = sharedPreferences.getInt("dayName", 0)
        val dayNumber = sharedPreferences.getInt("dayNumber", 0)
        val daysInMonth = sharedPreferences.getInt("dayCount", 0)
        var x = 0
        var z : Int

        val boxesArray = ArrayList<TextView>()
        boxesArray.add(binding.box1)
        boxesArray.add(binding.box2)
        boxesArray.add(binding.box3)
        boxesArray.add(binding.box4)
        boxesArray.add(binding.box5)
        boxesArray.add(binding.box6)
        boxesArray.add(binding.box7)
        boxesArray.add(binding.box8)
        boxesArray.add(binding.box9)
        boxesArray.add(binding.box10)
        boxesArray.add(binding.box11)
        boxesArray.add(binding.box12)
        boxesArray.add(binding.box13)
        boxesArray.add(binding.box14)
        boxesArray.add(binding.box15)
        boxesArray.add(binding.box16)
        boxesArray.add(binding.box17)
        boxesArray.add(binding.box18)
        boxesArray.add(binding.box19)
        boxesArray.add(binding.box20)
        boxesArray.add(binding.box21)
        boxesArray.add(binding.box22)
        boxesArray.add(binding.box23)
        boxesArray.add(binding.box24)
        boxesArray.add(binding.box25)
        boxesArray.add(binding.box26)
        boxesArray.add(binding.box27)
        boxesArray.add(binding.box28)

        while (x < 28) {
            if  (x + 1 < startBox) {
                boxesArray[x].visibility = View.INVISIBLE
            } else if (x - startBox > 19) {
                boxesArray[x].visibility = View.INVISIBLE
            } else {
                z = dayNumber + x - startBox + 1
                if (z <= daysInMonth ) {
                    boxesArray[x].text = (z).toString()
                } else {
                    boxesArray[x].text = (z - daysInMonth).toString()
                }
                boxesArray[x].setOnClickListener {
                    goToEdit(it)
                }
                var sqlFind = 0
                val columns = arrayOf<String>("h1", "h2", "h3", "h4", "h5", "h6")
                var t = 0
                var habitCounter = 0
                try {

                    while ( t < 6 ) {
                        val db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
                        val cursor = db.rawQuery("SELECT * FROM days WHERE id = ${ z - dayNumber + 1 }", null)
                        val idIx = cursor.getColumnIndex( columns[t] )
                        while (cursor.moveToNext()) {
                            sqlFind = cursor.getInt(idIx)
                        }
                        if ( sqlFind == 1 ) {
                            habitCounter++
                            println(habitCounter)
                        }
                        t++
                        cursor.close()
                    }

                    if ( habitCounter > 3 ) {
                        boxesArray[x].setBackgroundColor(Color.GREEN)
                    } else if ( habitCounter > 0) {
                        boxesArray[x].setBackgroundColor(Color.YELLOW)
                    } else {
                        boxesArray[x].setBackgroundColor(Color.TRANSPARENT)
                    }

                } catch (e : Exception) {
                    e.printStackTrace()
                }
                if ( Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toInt() == boxesArray[x].text.toString().toInt() ){
                    boxesArray[x].setBackgroundColor(Color.CYAN)
                }
            }
            x++
        }
    }

    fun goToEdit(view : View) {
        val intent = Intent(this, EditActivity::class.java)
        val day = findViewById<TextView>(view.id).text.toString()
        intent.putExtra("day", day)
        startActivity(intent)
    }

}