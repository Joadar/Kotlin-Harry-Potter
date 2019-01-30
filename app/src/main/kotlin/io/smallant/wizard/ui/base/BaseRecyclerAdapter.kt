package io.smallant.wizard.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerAdapter<T : Any>(private val itemClickListener: OnItemClickListener<T>? = null) :
    RecyclerView.Adapter<BaseViewHolder>() {

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }

    private val items: ArrayList<T> = arrayListOf()

    protected abstract fun getLayoutId(item: T, position: Int): Int
    protected open fun editBinding(parent: ViewGroup, binding: ViewDataBinding) {}
    protected open fun editViewHolder(root: View, item: T, position: Int) {}

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

        editBinding(parent, binding)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val obj = items[position]
        with(holder) {
            itemView.setOnClickListener {
                itemClickListener?.onItemClick(obj)
            }
            editViewHolder(itemView, obj, position)
            bind(obj)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(items[position], position)
    }

}