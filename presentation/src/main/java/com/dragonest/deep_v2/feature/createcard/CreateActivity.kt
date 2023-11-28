package com.dragonest.deep_v2.feature.createcard

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.GlideExperiments
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.base.UiState
import com.dragonest.deep_v2.databinding.ActivityCreateBinding
import com.dragonest.deep_v2.databinding.ActivityMainBinding
import com.dragonest.deep_v2.feature.main.MainViewModel
import com.dragonest.deep_v2.util.TAG
import com.dragonest.domain.model.ClovaOcrRequestModel
import com.dragonest.domain.model.ImageModel
import com.zynksoftware.documentscanner.ScanActivity
import com.zynksoftware.documentscanner.model.DocumentScannerErrorModel
import com.zynksoftware.documentscanner.model.ScannerResults
import com.zynksoftware.documentscanner.ui.DocumentScanner
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class CreateActivity() : ScanActivity() {

    private val viewModel : CreateCardViewModel by viewModels()
    private val clovaOcrRecyclerViewAdapter : ClovaOcrRecyclerViewAdapter by lazy { ClovaOcrRecyclerViewAdapter() }

    private lateinit var binding : ActivityCreateBinding
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CreateActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onClose() {
        Log.d(TAG, "onClose: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create)

        binding.recyclerviewInfo.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            adapter = clovaOcrRecyclerViewAdapter
        }

        Log.d(TAG, "Activity ViewModel hashCode: ${viewModel.hashCode()}")

        binding.btnCamera.setOnClickListener {
            addFragmentContentLayout()
        }

        binding.btnCreateCard.setOnClickListener {
            if(viewModel.postImageMultiPart != null && viewModel.clovaOcrResponseModel != null){
                viewModel.postImageCard(
                    image = viewModel.postImageMultiPart!!,
                    request = viewModel.clovaOcrResponseModel!!
                )
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.postClovaOcrState.collect{
                withContext(Dispatchers.Main){
                    when (it) {
                        UiState.Failure -> {

                        }
                        UiState.Loading -> {}
                        is UiState.Success -> {
                            clovaOcrRecyclerViewAdapter.submitList(it.data)
                        }
                    }
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.postImageCardState.collect{
                withContext(Dispatchers.Main){
                    when (it) {
                        UiState.Failure -> {
                            Toast.makeText(
                                applicationContext,"명함 등록에 실패했습니다. 다시 시도해주세요",Toast.LENGTH_SHORT
                            ).show()
                        }
                        UiState.Loading -> {}
                        is UiState.Success -> {
                            finish()
                        }
                    }
                }
            }
        }

    }

    override fun onError(error: DocumentScannerErrorModel) {

        Log.d(TAG, "onError: ${error}")
    }

    override fun onSuccess(scannerResults: ScannerResults) {

        Glide.with(this).load(scannerResults.croppedImageFile).into(binding.imgCreateCard)

        val filePath = scannerResults.croppedImageFile?.path
        viewModel.croppedImageBitmap = BitmapFactory.decodeFile(filePath)

        Log.d(TAG, "onSuccess: ${scannerResults}")

        if(viewModel.croppedImageBitmap != null){
            viewModel.clovaOcrMultiPart = viewModel.croppedImageBitmap!!.bitmapToMultipart("file")
            viewModel.postImageMultiPart = viewModel.croppedImageBitmap!!.bitmapToMultipart("image")

            viewModel.postOcrClova(
                message = ClovaOcrRequestModel(ImageModel(
                    format = "JPEG", name = "image"
                )),
                file = viewModel.clovaOcrMultiPart!!
            )
        }



    }
}

private fun Bitmap.bitmapToMultipart(name : String): MultipartBody.Part {
    var byteArrayOutputStream = ByteArrayOutputStream()

    val MAX_IMAGE_SIZE = 142500
    var compressQuality = 100

    var streamLength = MAX_IMAGE_SIZE

    while(streamLength >= MAX_IMAGE_SIZE){

        byteArrayOutputStream = ByteArrayOutputStream()

        Log.d(TAG,"compressQuality - ${compressQuality}")
        this.compress(Bitmap.CompressFormat.JPEG, compressQuality , byteArrayOutputStream)
        streamLength = byteArrayOutputStream.size()
        Log.d(TAG,"streamLength - ${streamLength}")
        compressQuality -= 5

        if(compressQuality <= 0) break
    }

    this.compress(Bitmap.CompressFormat.JPEG, compressQuality , byteArrayOutputStream)

    val requestFile =
        RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArrayOutputStream.toByteArray())


    return MultipartBody.Part.createFormData(name, "image.jpeg", requestFile)
}
