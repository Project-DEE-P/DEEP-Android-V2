package com.dragonest.deep_v2.feature.main

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.ActivityMainBinding
import com.dragonest.deep_v2.util.DeepNfcService
import com.dragonest.deep_v2.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    private val mimeType = "application/com.dragonest.deep_v2" // 임의의 MIME 타입
    private lateinit var serviceIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentNfc: Intent? = intent
        val action: String? = intentNfc?.action
        val data: Uri? = intentNfc?.data

        if (action != null && action == NfcAdapter.ACTION_NDEF_DISCOVERED && data != null) {
            val scheme: String? = data.scheme
            val host: String? = data.host
            val path: String? = data.path

            // 특정 URL과 일치하는지 확인하여 액티비티 이동
            if (scheme != null && scheme == "http" && host != null && host == "yourdomain.com"
                && path != null && path.startsWith("/path")) {
                // 특정 URL에 접촉했을 때 실행될 코드를 여기에 구현합니다.
                // 예를 들어, 다른 액티비티로 이동하거나 원하는 동작을 수행할 수 있습니다.
            }
        }

        serviceIntent = Intent(this, DeepNfcService::class.java)

        // Service 시작
        startService(serviceIntent)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        val intent = Intent(this, javaClass).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val navController = binding.navHostFragmentActivityMain.getFragment<NavHostFragment>().navController

        navView.setupWithNavController(navController)
    }

    override fun onResume() {

        val intentFilters = arrayOf<IntentFilter>()
        val techLists = arrayOf<Array<String>>()

        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, intentFilters, techLists)

        super.onResume()
    }

    override fun onPause() {

        nfcAdapter?.disableForegroundDispatch(this)

        super.onPause()
    }

    override fun onNewIntent(intent: Intent) {

        if(NfcAdapter.ACTION_TAG_DISCOVERED == intent.action || NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action){
            if (mainViewModel.selectedCardId != null && mainViewModel.isPutNfcClicked) {

                val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
                writeUrlToTag(
                    tag!!,
                    "https://web.ddeep.store/showCard/image/${mainViewModel.selectedCardId}"
                ) // 여기서 URL을 씁니다.
            }else{
//                findNavController().navigate()
            }
        }

        super.onNewIntent(intent)
    }

    private fun writeUrlToTag(tag: Tag, url: String) {
        val message = createUriMessage(url)
        val ndef = Ndef.get(tag)

        var isFailed = false

        try {
            isFailed = false
            ndef?.connect()
            ndef?.writeNdefMessage(message)
        } catch (e: Exception) {
            Log.d(TAG, "writeUrlToTag: ${e}")
            isFailed = true
        } finally {

            if(!isFailed){
                Log.d(TAG,"MainActivity - writeUrlToTag() - finish")
                mainViewModel.updateSuccessPutNfc(!mainViewModel.successPutNfc.value)
            }
            ndef?.close()
        }
    }

    private fun createUriMessage(url: String): NdefMessage {
        val record = NdefRecord.createUri(url)
        return NdefMessage(arrayOf(record))
    }

}