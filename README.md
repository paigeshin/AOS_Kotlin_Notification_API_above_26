- API above 26

# Basic Example

- with functions
    - create channel
    - trigger notification

```kotlin
class MainActivity : AppCompatActivity() {

    //It doesn't have to be package name
    private val channelID = "com.paigesoftware.notificationdemo_component"
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "DemoChannel", "This is a demo")

        button.setOnClickListener {
            displayNotification()
        }

    }

    private fun displayNotification() {
        val notificationId: Int = 45
        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
                .setContentTitle("Demo Title")
                .setContentText("This is a demo notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH //Different Levels exist
            val channel = NotificationChannel(id, name, importance).apply {
                description
            }
            //channel.description =
            notificationManager?.createNotificationChannel(channel)

        }
    }

}
```

- read line by line example

```kotlin
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

        //Show Notification
        val notificationId: Int = 45
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification from activity2")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        button2.setOnClickListener {
            notificationManager.notify(notificationId, notification)
        }

    }
}
```

# Tap Action

- Code Example

```kotlin
val notificationId: Int = 45

/** Tap Action **/
val tapResultIntent = Intent(this, DestinationActivity::class.java)
val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
startActivity(tapResultIntent)

val notification = NotificationCompat.Builder(this@MainActivity, channelID)
        .setContentTitle("Demo Title")
        .setContentText("This is a demo notification")
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent) /** Tap Action **/
        .build()
notificationManager?.notify(notificationId, notification)
```

- with functions
    - Create Channel
    - Show Notification

```kotlin
class MainActivity : AppCompatActivity() {

    //It doesn't have to be package name
    private val channelID = "com.paigesoftware.notificationdemo_component"
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "DemoChannel", "This is a demo")

        button.setOnClickListener {
            displayNotification()
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }

    }

    private fun displayNotification() {
        val notificationId: Int = 45

        /** Tap Action **/
        val tapResultIntent = Intent(this, DestinationActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        startActivity(tapResultIntent)

        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
                .setContentTitle("Demo Title")
                .setContentText("This is a demo notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent) /** Tap Action **/
                .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH //Different Levels exist
            val channel = NotificationChannel(id, name, importance).apply {
                description
            }
            //channel.description =
            notificationManager?.createNotificationChannel(channel)

        }
    }

}
```

- read line by line example

```kotlin
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

        //Adding tap
        val tapResultIntent = Intent(this, DestinationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        startActivity(tapResultIntent)

        //Show Notification
        val notificationId: Int = 45
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification from activity2")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent) //Adding tap action
            .build()

        button2.setOnClickListener {
            notificationManager.notify(notificationId, notification)
        }

    }
}
```

# Add Action Buttons to Notification

### Create Action

```kotlin
/** Create Action **/
val settingsActionResultIntent = Intent(this, SettingsActivity::class.java)
val pendingSettingsActionIntent = PendingIntent.getActivity(this, 0, settingsActionResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
// You can set custom icon from drawable.xml
val settingAction: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Details", pendingSettingsActionIntent).build()

val notification = NotificationCompat.Builder(this@MainActivity, channelID)
                .setContentTitle("Demo Title")
                .setContentText("This is a demo notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent) /** Tap Action **/
                .addAction(detailAction) /** Action **/
                .addAction(settingAction) /** Action **/
                .build()
        notificationManager?.notify(notificationId, notification)
```

### Code example with function

```kotlin
class MainActivity : AppCompatActivity() {

    //It doesn't have to be package name
    private val channelID = "com.paigesoftware.notificationdemo_component"
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "DemoChannel", "This is a demo")

        button.setOnClickListener {
            displayNotification()
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }

    }

    private fun displayNotification() {
        val notificationId: Int = 45

        /** Tap Action **/
        val tapResultIntent = Intent(this, DestinationActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
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

        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
                .setContentTitle("Demo Title")
                .setContentText("This is a demo notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent) /** Tap Action **/
                .addAction(detailAction) /** Action **/
                .addAction(settingAction) /** Action **/
                .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH //Different Levels exist
            val channel = NotificationChannel(id, name, importance).apply {
                description
            }
            //channel.description =
            notificationManager?.createNotificationChannel(channel)

        }
    }

}
```

### Read line by line

```kotlin
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
            .setContentIntent(pendingIntent) /** Tap Action **/
            .addAction(detailAction) /** Action **/
            .addAction(settingAction) /** Action **/
            .build()

        button2.setOnClickListener {
            notificationManager.notify(notificationId, notification)
        }

    }
}
```

# Direct Reply Action

### Create ReplyAction

- RemoteInput

```kotlin
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

//Show Notification
val notificationId: Int = 45
val notification = NotificationCompat.Builder(this@SecondActivity, channelID)
    .setContentTitle("Demo Title")
    .setContentText("This is a demo notification")
    .setSmallIcon(android.R.drawable.ic_dialog_info)
    .setAutoCancel(true)
    .setPriority(NotificationCompat.PRIORITY_HIGH)
//    .setContentIntent(pendingIntent) /** Tap Action **/
//    .addAction(detailAction) /** Action **/
//    .addAction(settingAction) /** Action **/
    .addAction(replyAction) /** Direct Reply Action **/
    .build()

button2.setOnClickListener {
    notificationManager.notify(notificationId, notification)
}
```

### Receive reply action

- channel id
- reply key

```kotlin
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
```

### Code Example

- Function Version

```kotlin
class MainActivity : AppCompatActivity() {

    //It doesn't have to be package name
    private val channelID = "com.paigesoftware.notificationdemo_component"
    private var notificationManager: NotificationManager? = null

    private val KEY_REPLY = "key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "DemoChannel", "This is a demo")

        button.setOnClickListener {
            displayNotification()
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }

    }

    private fun displayNotification() {
        val notificationId: Int = 45

        /** Direct Reply Action **/
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

        /** Tap Action **/
        val tapResultIntent = Intent(this, DestinationActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
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

        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
                .setContentTitle("Demo Title")
                .setContentText("This is a demo notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setContentIntent(pendingIntent) /** Tap Action **/
                .addAction(detailAction) /** Action **/
                .addAction(settingAction) /** Action **/
                .addAction(replyAction) /** Direct Reply Action **/
                .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH //Different Levels exist
            val channel = NotificationChannel(id, name, importance).apply {
                description
            }
            //channel.description =
            notificationManager?.createNotificationChannel(channel)

        }
    }

}
```

- line by line version

```kotlin
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
```

- receiving data example

```kotlin
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
```