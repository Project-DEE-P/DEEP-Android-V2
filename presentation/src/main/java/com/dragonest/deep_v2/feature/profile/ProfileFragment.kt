package com.dragonest.deep_v2.feature.profile

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dragonest.deep_v2.BR
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.base.UiState
import com.dragonest.deep_v2.databinding.FragmentProfileBinding
import com.dragonest.deep_v2.feature.createcard.CreateActivity
import com.dragonest.deep_v2.feature.main.MainViewModel
import com.dragonest.deep_v2.util.TAG
import com.dragonest.domain.model.CardResponseModel
import com.ggd.zendee.base.BaseFragment
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    override val viewModel: ProfileViewModel by viewModels()
    private val mainViewModel : MainViewModel by activityViewModels()

    private val profileCardAdapter : ProfileCardAdapter by lazy { ProfileCardAdapter() }

    override fun start() {

        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.layoutProfile.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
        binding.layoutProfile.isTouchEnabled = false

        profileCardAdapter.setItemClickListener(object : ProfileCardAdapter.OnItemClickListener{
            override fun onClick(data: CardResponseModel, isSelected : Boolean) {

                Log.d(TAG,"ProfileFragment - onClick - data - ${data}")
                Log.d(TAG,"ProfileFragment - onClick - isSelected - ${isSelected}")

                viewModel.isSelected.set(isSelected)

                if(isSelected){
                    viewModel.selectedCard = data
                    mainViewModel.selectedCardId = data.id

                }
                else {
                    viewModel.selectedCard = null
                    mainViewModel.selectedCardId = null
                }

            }
        })

        binding.layoutProfile.setOnClickListener {
            binding.layoutProfile.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
        }

        binding.recyclerviewCardList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = profileCardAdapter
        }

        binding.txtPutNfc.setOnClickListener{
            onPutNfcClick()
        }
        binding.btnPutNfc.setOnClickListener {
            onPutNfcClick()
        }

        binding.btnCreate.setOnClickListener {
            CreateActivity.start(requireContext())
//            findNavController().navigate(R.id.action_navigation_profile_to_createCardFragment)
        }

        binding.btnSetting.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.toSettingFragment())
        }

        CoroutineScope(Dispatchers.Main).launch{
            setCollector()
        }


        getSuccess()

        viewModel.getCardList()

    }

    fun onPutNfcClick(){

        mainViewModel.isPutNfcClicked = true

        val slidePanel = binding.layoutProfile

        val state = slidePanel.panelState
        slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
    }


    suspend fun setCollector(){
        viewModel.getCardListState.collect{

            Log.d(TAG, "setCollector: ${it}")

            when(it){
                UiState.Failure -> {}
                UiState.Loading -> {}
                is UiState.Success -> {
                    withContext(Dispatchers.Main){
                        profileCardAdapter.submitList(it.data?.toMutableList())
                    }
                }
            }
        }
    }

    fun getSuccess(){

        viewLifecycleOwner.lifecycleScope.launch {

            mainViewModel.successPutNfc.collectLatest{
                Log.d(TAG,"ProfileFragment - getSuccess() called")

                withContext(Dispatchers.Main){

                    binding.txtTagNfc.text = "명함 등록이 완료되었습니다!"
                    binding.txtTagNfc.setTextColor(resources.getColor(R.color.blue_500))
                    binding.imgTagNfc.background = resources.getDrawable(R.drawable.ic_success)

                    delay(1000)

                    binding.layoutProfile.panelState = SlidingUpPanelLayout.PanelState.HIDDEN

                    delay(200)

                    binding.txtTagNfc.text = "NFC 태그에 디바이스를 가까이 대주세요"
                    binding.txtTagNfc.setTextColor(resources.getColor(R.color.gray_900))
                    binding.imgTagNfc.background = resources.getDrawable(R.drawable.ic_tag_nfc)

                }
            }

        }
    }



}