package dev.achmad.trivium.notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import dev.achmad.core.CHANNEL_ID
import dev.achmad.core.NOTIFICATION_ID
import dev.achmad.core.TRIVIUM_ACTION
import dev.achmad.trivium.R
import dev.achmad.trivium.base.MainActivity

object NotificationHelper {

    fun Context.createNotificationChannel() {
        val name = "General"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun Context.buildNotification(
        title: String,
        text: String,
        intent: PendingIntent,
        autoCancel: Boolean = true
    ): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(text)
            .setContentIntent(intent)
            .setAutoCancel(autoCancel)
            .build()
    }

    private fun hasNotificationPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    @Composable
    fun ShowNotification(
        notification: Notification,
        onPermissionResult: (Boolean) -> Unit = {}
    ) {
        val context = LocalContext.current
        var permissionGranted by remember { mutableStateOf(hasNotificationPermission(context)) }

        // If not granted, request it.
        if (!permissionGranted) {
            RequestNotificationPermission { granted ->
                permissionGranted = granted
                onPermissionResult(granted)
            }
        }

        // When permission is granted, show the notification.
        LaunchedEffect(permissionGranted) {
            if (permissionGranted) {
                context.showNotification(notification)
            }
        }
    }


    @Composable
    private fun RequestNotificationPermission(
        onPermissionResult: (Boolean) -> Unit
    ) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            LaunchedEffect(Unit) { onPermissionResult(true) }
            return
        }

        // Create a launcher for requesting the POST_NOTIFICATIONS permission.
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            onPermissionResult(isGranted)
        }

        LaunchedEffect(Unit) { permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS) }
    }

    @SuppressLint("MissingPermission")
    private fun Context.showNotification(notification: Notification) {
        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, notification)
        }
    }

    private fun Context.postNotification(
        title: String,
        content: String,
        action: String,
        extras: Bundle,
    ) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(TRIVIUM_ACTION,action)
            putExtras(extras)
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = buildNotification(title, content, pendingIntent)
        showNotification(notification)
    }

}