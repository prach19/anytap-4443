package com.prachisadhwani.android.anytap_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class FeedbackActivity : AppCompatActivity() {
    lateinit var dataEntry: String
    lateinit var satisfiedSelectString: String
    lateinit var comfortRatingString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_feedback)

        //display the last taken image for the participant
        var imagePreviewURI = intent.getStringExtra("image_uri")
        val imagePreview: ImageView = findViewById(R.id.imagePreview)
        if (imagePreviewURI != null) {
            imagePreview.setImageURI(Uri.parse(imagePreviewURI))
        }
    }

    fun onSubmit(v: View) {
        var participantCode = intent.getStringExtra("code")
        var task = intent.getStringExtra("task")
        var orientation = intent.getStringExtra("orientation")
        var tapLocationX = intent.getStringExtra("tapLocationX")
        var tapLocationY = intent.getStringExtra("tapLocationY")


        //get the answers from the feedback form
        val satisfiedAns: RadioGroup = findViewById(R.id.satisfiedAns)
        var satisfiedSelect = satisfiedAns.checkedRadioButtonId
        val comfortAns: RadioGroup = findViewById(R.id.comfortRating)
        var comfortSelect = comfortAns.checkedRadioButtonId

        if (satisfiedSelect == -1 || comfortSelect == -1) {
            Toast.makeText(
                baseContext,
                "Please fill in the feedback form before submitting",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            satisfiedSelectString = findViewById<RadioButton>(satisfiedSelect).text.toString()
            comfortRatingString = findViewById<RadioButton>(comfortSelect).text.toString()

            //format the data as one string to enter into the csv file
            dataEntry = String.format(
                Locale.CANADA,
                "%s, %s, %s, %s, %s, %s, %s",
                participantCode,
                task,
                orientation,
                satisfiedSelectString,
                comfortRatingString,
                tapLocationX,
                tapLocationY
            )
            Log.d("MYDEBUG", dataEntry)
            val i = Intent(
                applicationContext,
                SetUpActivity::class.java
            )
            startActivity(i)
            finish()
        }


    }
}