package com.example.translateapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ParagraphActivity : AppCompatActivity() {
    private lateinit var btn_feedback: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paragraph)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn_feedback = findViewById(R.id.Main_Btn_feedback)

        btn_feedback.setOnClickListener {
            ratingCustomDialog()

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun ratingCustomDialog() {

        val view = layoutInflater.inflate(R.layout.dialog_rating_layout, null)
        val ratingBar = view.findViewById<RatingBar>(R.id.DialogRatingBar)
        val btn_save = view.findViewById<Button>(R.id.DialogSaveButton)

        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val customDialog = builder.create()
        customDialog.show()
//        customDialog.setCancelable(false)


        btn_save.setOnClickListener {
            customDialog.dismiss()
            pushNotification(ratingBar.rating)

        }


        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            Toast.makeText(this, "Rating: $rating", Toast.LENGTH_SHORT).show()
        }


    }


    //////////push Notification includes the feedback

    @RequiresApi(Build.VERSION_CODES.O)
    private fun pushNotification(rating: Float) {
        val channel = NotificationChannel(
            "idChannel_one",
            "nameChannel_one",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            "chanel one"
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

        notificationManager.createNotificationChannel(channel)


        val intent = Intent(this, ParagraphActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            20, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val sendNotification = NotificationCompat
            .Builder(this, "idChannel_one")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(resources.getString(R.string.your_feedback))
            //////////////////////////
            .setContentText(rating.toString())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        if (
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS), 100
            )

        } else {
            NotificationManagerCompat
                .from(this)
                .notify(1, sendNotification.build())

        }

    }
}