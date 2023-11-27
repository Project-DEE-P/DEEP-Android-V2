package com.dragonest.deep_v2.feature.storage

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.base.UiState
import com.dragonest.deep_v2.databinding.FragmentStorageBinding
import com.dragonest.deep_v2.feature.main.MainViewModel
import com.dragonest.deep_v2.util.TAG
import com.dragonest.domain.model.CardResponseModel
import com.ggd.zendee.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StorageFragment : BaseFragment<FragmentStorageBinding, StorageViewModel>(R.layout.fragment_storage) {

    override val viewModel : StorageViewModel by viewModels<StorageViewModel>()
    private val mainViewModel : MainViewModel by activityViewModels()

    private val cardListAdapter :StorageRecyclerViewAdapter by lazy { StorageRecyclerViewAdapter() }


    override fun start() {

        binding.recyclerviewCardList.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cardListAdapter
        }

        cardListAdapter.setItemClickListener(object : StorageRecyclerViewAdapter.OnItemClickListener{

            override fun onClick(data: CardResponseModel) {
                mainViewModel.storageToDetail = data
                findNavController().navigate(R.id.action_navigation_storage_to_cardDetailFragment)
            }

        })

        CoroutineScope(Dispatchers.Main).launch{
            setCollector()
        }

        viewModel.getCardList()

    }

    suspend fun setCollector(){
        viewModel.getRememberedListCardState.collect{
            when(it){
                UiState.Failure -> {}
                UiState.Loading -> {}
                is UiState.Success -> {
                    cardListAdapter.submitList(it.data)
                }
            }
        }
    }

}