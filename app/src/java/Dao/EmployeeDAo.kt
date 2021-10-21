package com.ilhomjon.room5.Dao

import androidx.room.*
import com.ilhomjon.room5.Entity.Employee

@Dao
interface EmployeeDAo {
    @Query("select * from employee")
    fun getAllEmployee():List<Employee>

    @Insert
    fun addEmployee(employee: Employee)

    @Delete
    fun deleteEmployee(employee: Employee)

    @Update
    fun editEmployee(employee: Employee)

    @Query("select * from employee where id=:id")
    fun getEmployeeById(id:Int): Employee

}