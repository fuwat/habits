package com.fuat.habits

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.fuat.habits.databinding.ActivityCreateBinding
import java.util.*

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    lateinit var sharedPreferences : SharedPreferences
    var dayNumber : Int? = null
    var dayName : Int? = null
    var dayCount : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dayNumber = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        dayName = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        dayCount = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)
        sharedPreferences = this.getSharedPreferences("com.fuat.habits", MODE_PRIVATE)
    }

    fun confirm (view: View) {
        if( binding.textHabit1.text.toString() != "" && binding.textHabit5.text.toString() != ""&& binding.textHabit2.text.toString() != "" && binding.textHabit3.text.toString() != "" && binding.textHabit4.text.toString() != "" && binding.textHabit6.text.toString() != "" ){
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Confirm new habits?")
            alert.setMessage("This will forever erase your current list of habits.")
            alert.setPositiveButton("Yes") { dialog, which ->
                sharedPreferences.edit().clear().apply()
                sharedPreferences.edit().putInt("dayNumber", dayNumber!!).apply()
                sharedPreferences.edit().putInt("dayName", dayName!!).apply()
                sharedPreferences.edit().putInt("dayCount", dayCount!!).apply()
                sharedPreferences.edit().putString("habit1", binding.textHabit1.text.toString())
                    .apply()
                sharedPreferences.edit().putString("habit2", binding.textHabit2.text.toString())
                    .apply()
                sharedPreferences.edit().putString("habit3", binding.textHabit3.text.toString())
                    .apply()
                sharedPreferences.edit().putString("habit4", binding.textHabit4.text.toString())
                    .apply()
                sharedPreferences.edit().putString("habit5", binding.textHabit5.text.toString())
                    .apply()
                sharedPreferences.edit().putString("habit6", binding.textHabit6.text.toString())
                    .apply()
                database()
                finish()
            }
            alert.setNegativeButton("No") { dialog, which ->
                Toast.makeText(applicationContext, "Cancelled", Toast.LENGTH_LONG).show()
            }
            alert.show()
        } else {
            Toast.makeText(applicationContext, "Please correctly enter 6 habits", Toast.LENGTH_LONG).show()
        }
    }

    fun database () {
        try {
            val db = this.openOrCreateDatabase("days", Context.MODE_PRIVATE, null)
            db.execSQL("CREATE TABLE IF NOT EXISTS days (id INTEGER PRIMARY KEY, h1 INTEGER, h2 INTEGER, h3 INTEGER, h4 INTEGER, h5 INTEGER, h6 INTEGER)")
            db.delete("days",null,null)
            var x = 0
            while (x < 21) {
                db.execSQL("INSERT INTO days (h1, h2, h3, h4, h5, h6) VALUES (0, 0, 0, 0, 0, 0)")
                x++
            }

        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
}