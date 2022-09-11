package com.neonusa.marketplace.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.popUpMenu
import com.inyongtisto.myhelper.extension.toJson
import com.neonusa.marketplace.core.data.source.model.AlamatToko
import com.neonusa.marketplace.databinding.ItemAlamatTokoBinding
import com.neonusa.marketplace.ui.alamattoko.EditStoreAddressActivity

@SuppressLint("NotifyDataSetChanged")
class StoreAddressAdapter : RecyclerView.Adapter<StoreAddressAdapter.ViewHolder>() {

    private var data = ArrayList<AlamatToko>()

    inner class ViewHolder(private val itemBinding: ItemAlamatTokoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: AlamatToko, position: Int) {
            itemBinding.apply {
                tvKota.text = item.kota
                var kecamatan = ""
                if (item.kecamatan != null) kecamatan = ", Kec. ${item.kecamatan}"

                tvAlamat.text =
                    "${item.alamat}$kecamatan, ${item.kota}, ${item.provinsi}, ${item.kodepost}"
                tvEmail.text = item.email
                tvPhone.text = item.phone

                val context = root.context
                btnMenu.setOnClickListener {
                    val listMenu = listOf("detail","hapus")
                    root.context.popUpMenu(btnMenu,listMenu){
                        when(it){
                            "detail" -> context.intentActivity(
                                EditStoreAddressActivity::class.java,
                                item.toJson()
                            )
                            "hapus" -> logs("hapus")
                        }
                    }
                }

            }
        }
    }

    fun addItems(items: List<AlamatToko>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAlamatTokoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}