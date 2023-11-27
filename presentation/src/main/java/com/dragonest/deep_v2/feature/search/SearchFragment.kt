package com.dragonest.deep_v2.feature.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentSearchBinding
import com.ggd.zendee.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {
    override val viewModel: SearchViewModel by viewModels()

    override fun start() {
    }

}