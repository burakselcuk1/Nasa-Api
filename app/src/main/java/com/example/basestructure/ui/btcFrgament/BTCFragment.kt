package com.example.basestructure.ui.btcFrgament

import android.os.Handler
import android.os.Looper
import com.example.basestructure.R
import com.example.basestructure.base.BaseFragment
import com.example.basestructure.common.enums.Status
import com.example.basestructure.common.extensions.observe
import com.example.basestructure.common.extensions.observeEvent
import com.example.basestructure.common.tryOrLog
import com.example.basestructure.common.utils.ProgressDialogUtil
import com.example.basestructure.databinding.FragmentBTCBinding


class BTCFragment : BaseFragment<FragmentBTCBinding, BTCViewModel>(
    layoutId = R.layout.fragment_b_t_c, viewModelClass = BTCViewModel::class.java
) {
    var isProgress = false

    override fun onInitDataBinding() {

        binding.reflesh.setOnClickListener {
            RefreshApiReload()
        }

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
                        observe(viewModel.btc) {
                            binding.name.text = it.name.toString()
                            binding.price.text = it.rate.toString()
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


    private fun RefreshApiReload() {

        isProgress = true;
        ProgressDialogUtil.showLoadingProgress(context = requireContext())
        ProgressDialogUtil.start()

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getBtc()
        }, 3000)
    }

    private fun getToken() {
        viewModel.getBtc()
    }
}