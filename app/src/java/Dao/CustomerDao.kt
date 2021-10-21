package com.ilhomjon.room5.Dao

import androidx.room.*
import com.ilhomjon.room5.Entity.Customer

@Dao
interface CustomerDao {
    @Query("select * from customer")
    fun getAllCustomer():List<Customer>

    @Insert
    fun addCustomer(customer: Customer)

    @Delete
    fun deleteCustomer(customer: Customer)

    @Update
    fun editCustomer(customer: Customer)

    @Query("select * from customer where id=:id")
    fun getCustomerById(id:Int):Customer

}