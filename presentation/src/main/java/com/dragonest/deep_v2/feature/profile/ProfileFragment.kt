package com.dragonest.deep_v2.feature.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.base.UiState
import com.dragonest.deep_v2.databinding.FragmentProfileBinding
import com.dragonest.deep_v2.util.TAG
import com.ggd.zendee.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    override val viewModel: ProfileViewModel by viewModels()

    private val profileCardAdapter : ProfileCardAdapter by lazy { ProfileCardAdapter() }

    override fun start() {

        binding.recyclerviewCardList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = profileCardAdapter
        }

        CoroutineScope(Dispatchers.Main).launch{
            setCollector()
        }

        viewModel.getCardList()

    }

    suspend fun setCollector(){
        viewModel.getCardListState.collect{

            Log.d(TAG, "setCollector: ${it}")

            when(it){
                UiState.Failure -> {}
                UiState.Loading -> {}
                is UiState.Success -> {
                    profileCardAdapter.submitList(it.data)
                }
            }
        }
    }

}