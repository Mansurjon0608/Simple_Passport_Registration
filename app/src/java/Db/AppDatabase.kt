package com.ilhomjon.room5.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilhomjon.room5.Dao.CustomerDao
import com.ilhomjon.room5.Dao.EmployeeDAo
import com.ilhomjon.room5.Dao.OrderDao
import com.ilhomjon.room5.Entity.Customer
import com.ilhomjon.room5.Entity.Employee
import com.ilhomjon.room5.Entity.Orders

@Database(entities = [Customer::class, Employee::class, Orders::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun customerDao():CustomerDao
    abstract fun employeeDAo():EmployeeDAo
    abstract fun orderDao():OrderDao

    companion object{
        private var instance:AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context):AppDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "db_order")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}