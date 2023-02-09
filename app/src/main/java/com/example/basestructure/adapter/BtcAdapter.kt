package com.example.basestructure.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basestructure.databinding.BtcItemBinding
import com.example.basestructure.model.BtcItemResponsse
import com.example.basestructure.model.BtcResponse



class BtcAdapter(private val dataSet: BtcResponse) :
    RecyclerView.Adapter<BtcAdapter.ViewHolder>() {
    private lateinit var binding: BtcItemBinding

    class ViewHolder(view: BtcItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = BtcItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val usersResponse: BtcItemResponsse = dataSet.get(position)
        binding.btcInformation = usersResponse

    }
    override fun getItemCount() = dataSet.size

}