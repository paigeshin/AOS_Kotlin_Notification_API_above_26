package com.paigesoftware.notificationdemo_component.action

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.paigesoftware.notificationdemo_component.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        receiveInput()
    }

    private fun receiveInput() {
        /*** Receive Data ***/
        val KEY_REPLY = "key_reply"
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if(remoteInput != null) {
            val inputString = remoteInput.getCharSequence(KEY_REPLY)
            mytextview.text = inputString

            //Send back using same `channelId` and `notificationId`
            val channelId = "com.paigesoftware.notificationdemo_component"
            val notificationId = 45

            val repliedNotification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Your reply received")
                .build()

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotification)

        }
    }

}