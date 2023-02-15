package com.example.base.ui.btcFrgament

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.adapter.BtcAdapter
import com.example.base.base.BaseFragment
import com.example.base.common.enums.Status
import com.example.base.common.extensions.observe
import com.example.base.common.extensions.observeEvent
import com.example.base.common.tryOrLog
import com.example.base.common.utils.ProgressDialogUtil
import com.example.basestructure.R
import com.example.basestructure.databinding.FragmentBTCBinding


class BTCFragment : BaseFragment<FragmentBTCBinding, BTCViewModel>(
    layoutId = R.layout.fragment_b_t_c, viewModelClass = BTCViewModel::class.java
) {
    var isProgress = false
    private lateinit var adapter: BtcAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onInitDataBinding() {



        observeEvent(viewModel.statusData) {
            tryOrLog {
                when (it) {
                    Status.LOADING -> {
                        if (!isProgress) {
                            ProgressDialogUtil.showLoadingProgress(context = requireContext())
                            ProgressDialogUtil.start()
                        }
                    }

                    Status.SUCCESS -> {
                        observe(viewModel.btc) {nasa ->
                            adapter = BtcAdapter(nasa)

                            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                            binding.recyclerView.adapter = adapter
                            adapter.notifyDataSetChanged()
                        }
                        isProgress = false
                        ProgressDialogUtil.stop()
                    }

                    Status.ERROR -> {
                        ProgressDialogUtil.stop()
                    }
                }
            }
        }
    }



}