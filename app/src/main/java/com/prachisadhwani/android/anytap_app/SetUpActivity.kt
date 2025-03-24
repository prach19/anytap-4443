package com.prachisadhwani.android.anytap_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prachisadhwani.android.anytap_app.databinding.ActivityMainBinding

class SetUpActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_set_up)
        val tasksSpinner: Spinner = findViewById(R.id.tasksSpinner)
        val partiCode: EditText = findViewById(R.id.participantCode)
        val orientationSpinner: Spinner = findViewById(R.id.orientationSpinner)

        val tasksAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.tasks_array,
            android.R.layout.simple_spinner_item
        )
        tasksSpinner.adapter = tasksAdapter

        val orientationAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.orientation_array,
            android.R.layout.simple_spinner_item
        )
        orientationSpinner.adapter = orientationAdapter
    }

    fun clickOK(view: View?) {
        // get participant/researcher input
        val tasksSpinner: Spinner = findViewById(R.id.tasksSpinner)
        val partiCode: EditText = findViewById(R.id.participantCode)
        val orientationSpinner: Spinner = findViewById(R.id.orientationSpinner)
        val task = tasksSpinner.selectedItem.toString()
        val orientation = orientationSpinner.selectedItem.toString()
        val code = partiCode.text.toString()

        if(task.isEmpty()){
            Toast.makeText(this, "Please enter participant code before continuing.", Toast.LENGTH_SHORT).show()
        } else {
            // bundle up parameters to pass on to activity
            val b = Bundle()
            b.putString("code", code)
            b.putString("task", task)
            b.putString("orientation", orientation)

            // start experiment activity
            val i = Intent(
                applicationContext,
                MainActivity::class.java
            )
            i.putExtras(b)
            startActivity(i)
        }
    }
}