package com.ilhomjon.room5.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhomjon.room5.Db.AppDatabase
import com.ilhomjon.room5.Entity.Orders
import com.ilhomjon.room5.databinding.OrderItemBinding

class OrderAdapter(val list: List<Orders>, var appDatabase: AppDatabase) : RecyclerView.Adapter<OrderAdapter.Vh>() {

    inner class Vh(var itemRv: OrderItemBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(orders: Orders) {
            itemRv.date.text = orders.date
            itemRv.customerName.text = appDatabase.customerDao().getCustomerById(orders.customerId!!).name
            itemRv.employeeName.text = appDatabase.employeeDAo().getEmployeeById(orders.employeeId!!).name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}