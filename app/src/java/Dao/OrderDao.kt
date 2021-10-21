package com.ilhomjon.room5.Dao

import androidx.room.*
import com.ilhomjon.room5.Entity.Orders

@Dao
interface OrderDao {
    @Query("select * from orders")
    fun getAllOrders():List<Orders>

    @Insert
    fun addOrders(orders: Orders)

    @Delete
    fun deleteOrders(orders: Orders)

    @Update
    fun editOrders(orders: Orders)

    @Query("select * from orders where id=:id")
    fun getOrdersById(id:Int): Orders

}