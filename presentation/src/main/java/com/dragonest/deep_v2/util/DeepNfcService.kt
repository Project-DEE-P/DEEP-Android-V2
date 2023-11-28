package com.dragonest.deep_v2.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.nfc.NfcAdapter
import android.nfc.NfcAdapter.ACTION_NDEF_DISCOVERED
import android.nfc.tech.NfcF
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import com.dragonest.deep_v2.R

class DeepNfcService : Service() {

    private var nfcAdapter: NfcAdapter? = null
    private var isNfcEnabled = false
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handleNfcTag(intent)
        return START_STICKY
    }

    private fun handleNfcTag(intent: Intent?) {
        val action: String? = intent?.action
        val data: Uri? = intent?.data

        if (action != null && action == NfcAdapter.ACTION_NDEF_DISCOVERED && data != null) {
            val scheme: String? = data.scheme
            val host: String? = data.host
            val path: String? = data.path

            Log.d(TAG,"DeepNfcService - host - ${host}")
            Log.d(TAG,"DeepNfcService - scheme - ${scheme}")
            Log.d(TAG,"DeepNfcService - path - ${path}")
//            // 특정 URL과 일치하는지 확인하여 액티비티 이동
//            if (scheme != null && scheme == "http" && host != null && host == "yourdomain.com"
//                && path != null && path.startsWith("/path")) {
//                // 특정 URL에 접촉했을 때 실행될 코드를 여기에 구현합니다.
//                // 예를 들어, 다른 액티비티로 이동하거나 원하는 동작을 수행할 수 있습니다.
//            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        nfcAdapter = null
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        isNfcEnabled = false

        // Foreground 서비스 실행
        startForegroundService()
    }

    private fun startForegroundService() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "nfc_tag_service_channel"
            val channelName = "NFC Tag Service"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            notificationManager.createNotificationChannel(channel)

            val notification: Notification = Notification.Builder(this, channelId)
                .setContentTitle("NFC Tag Service")
                .setContentText("백그라운드에서 NFC 태그를 감지 중입니다.")
                .setSmallIcon(R.drawable.ic_tag_nfc)
                .build()
            startForeground(1, notification)
        } else {
            val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
                .setContentTitle("NFC Tag Service")
                .setContentText("백그라운드에서 NFC 태그를 감지 중입니다.")
                .setSmallIcon(R.drawable.ic_tag_nfc)

            val notification: Notification = builder.build()
            startForeground(1, notification)
        }
    }

}