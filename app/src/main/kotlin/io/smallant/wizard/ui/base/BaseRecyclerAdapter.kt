package io.smallant.wizard.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder>() {

    protected val items: ArrayList<T> = arrayListOf()

    // protected abstract fun getObjAtPosition(position: Int): Any
    protected abstract fun getLayoutIdAtPosition(position: Int): Int

    fun setItems(list: List<T>?, clearList: Boolean = false) {
        if (clearList)
            items.clear()

        list?.let {
            items.addAll(list)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val obj: Any = items[position] as Any
        holder.bind(obj)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdAtPosition(position)
    }

}