package com.ilhomjon.room5.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhomjon.room5.Entity.Employee
import com.ilhomjon.room5.databinding.ItemRvBinding

class EmployeeAdapter(val list: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(employee: Employee) {
            itemRv.txtName.text = employee.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}