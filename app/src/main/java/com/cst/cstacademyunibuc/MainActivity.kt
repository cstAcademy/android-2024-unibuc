package com.cst.cstacademyunibuc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.cst.cstacademyunibuc.helpers.extensions.logErrorMessage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickOnButton(findViewById(R.id.btn_press)) {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        "onCreate".logErrorMessage()
    }

    override fun onStart() {
        super.onStart()

        "onStart".logErrorMessage()
    }

    override fun onResume() {
        super.onResume()

        "onResume".logErrorMessage()
    }

    override fun onPause() {
        super.onPause()

        "onPause".logErrorMessage()
    }

    override fun onStop() {
        super.onStop()

        "onStop".logErrorMessage()
    }

    override fun onDestroy() {
        super.onDestroy()

        "onDestroy".logErrorMessage()
    }

    private fun clickOnButton(button: Button, lambda: (() -> Unit)) {
//        val button = findViewById<Button>(R.id.btn_press)
        button.setOnClickListener {
            lambda.invoke()
        }
    }
}