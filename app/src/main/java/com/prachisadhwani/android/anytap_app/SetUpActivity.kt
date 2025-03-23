package com.prachisadhwani.android.anytap_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SetUpActivity : AppCompatActivity() {
    val tasksSpinner: Spinner = findViewById(R.id.tasksSpinner)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val partiCode: TextView = findViewById<TextView>(R.id.participantCode)
        val code: String = partiCode.text.toString()


        val tasksAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.tasks_array,
            android.R.layout.simple_spinner_item
        )
        tasksSpinner.adapter = tasksAdapter
        val orientationSpinner: Spinner = findViewById(R.id.orientationSpinner)
        val orientationAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.orientation_array,
            android.R.layout.simple_spinner_item
        )
        orientationSpinner.adapter = orientationAdapter
    }

    fun clickOK(view: View?) {
        // get user's choices...
        val task = tasksSpinner.getSelectedItem().toString()

        // bundle up parameters to pass on to activity
        val b = Bundle()
        b.putString("task", task)

        // start experiment activity
        val i = Intent(
            applicationContext,
             MainActivity::class.java
        )
        i.putExtras(b)
        startActivity(i)

        // comment out (return to setup after clicking BACK in main activity
        //finish();
    }
}