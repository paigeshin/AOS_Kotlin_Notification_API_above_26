package com.paigesoftware.notificationdemo_component

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.paigesoftware.notificationdemo_component.action.DetailsActivity
import com.paigesoftware.notificationdemo_component.action.SettingsActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelID = "com.paigesoftware.notificationdemo_component"

        //Create Notification Channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH //Different Levels exist
            val channel = NotificationChannel(channelID, "Demo", importance).apply {
                description
            }
            //channel.description =
            notificationManager.createNotificationChannel(channel)

        }

        /** Direct Reply Action **/
        val KEY_REPLY = "key_reply" //destination activity  will use this key
        //you should import `androidx.core.app.RemoteInput`
        val remoteInput: RemoteInput = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Insert your name here")
                .build()
        }//.build()
        val replyResultIntent = Intent(this, DetailsActivity::class.java)
        val replyPendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            replyResultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //You can set custom drawable icon here in first argument
        val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(
            0,
            "REPLY",
            replyPendingIntent
        ).addRemoteInput(remoteInput)
            .build()

        //Adding tap
        val tapResultIntent = Intent(this, DestinationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        startActivity(tapResultIntent)

        /** Create Action **/
        val detailActionResultIntent = Intent(this, DetailsActivity::class.java)
        val pendingDetailActionActionIntent = PendingIntent.getActivity(this, 0, detailActionResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        // You can set custom icon from drawable.xml
        val detailAction: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Details", pendingDetailActionActionIntent).build()

        /** Create Action **/
        val settingsActionResultIntent = Intent(this, SettingsActivity::class.java)
        val pendingSettingsActionIntent = PendingIntent.getActivity(this, 0, settingsActionResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        // You can set custom icon from drawable.xml
        val settingAction: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Settings", pendingSettingsActionIntent).build()

        //Show Notification
        val notificationId: Int = 45
        val notification = NotificationCompat.Builder(this@SecondActivity, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent) /** Tap Action **/
            .addAction(detailAction) /** Action **/
            .addAction(settingAction) /** Action **/
            .addAction(replyAction) /** Direct Reply Action **/
            .build()


        button2.setOnClickListener {
            notificationManager.notify(notificationId, notification)
        }

    }
}