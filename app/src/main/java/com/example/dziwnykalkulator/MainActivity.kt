package com.example.dziwnykalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.timeUnitSpinner)
        val button = findViewById<Button>(R.id.calculateButton)
        var datePicker = findViewById<DatePicker>(R.id.datePicker)

        val calendar = Calendar.getInstance()
        datePicker.maxDate = calendar.timeInMillis

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val currUnit = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "Wybrałes: $currUnit", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "Nic nie wybrales 123", Toast.LENGTH_SHORT).show()
            }

        }

        button.setOnClickListener{
            val selectedUnit = spinner.selectedItem.toString()
            calculateTimeSpan(selectedUnit)
        }

    }

    private fun calculateTimeSpan(selectedUnit: String){
        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val textView4 = findViewById<TextView>(R.id.textView4)
        val resultsTextView = findViewById<TextView>(R.id.Results)

        val year = datePicker.year
        val month = datePicker.month+1
        val day = datePicker.dayOfMonth

        val calendarSelected = Calendar.getInstance()
        calendarSelected.set(year, month-1, day)

        val calendarToday = Calendar.getInstance()

        val differenceInMillis = calendarToday.timeInMillis - calendarSelected.timeInMillis

        val millisecondsInSelectedUnit = when (selectedUnit) {
            "Sekundy" -> 1000L
            "Minuty" -> 60 * 1000L
            "Godziny" -> 60 * 60 * 1000L
            else -> 0L
        }

        val timeUnit = when (selectedUnit) {
            "Sekundy" -> "sekund"
            "Minuty" -> "minut"
            "Godziny" -> "godzin"
            else -> ""
        }

        val calculatedTime = differenceInMillis / millisecondsInSelectedUnit

        resultsTextView.text = "Od $day/$month/$year minęło $calculatedTime $timeUnit"

        textView4.visibility = View.VISIBLE
        resultsTextView.visibility = View.VISIBLE
    }

}