package com.ilhomjon.room5.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
class Orders {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    var customerId:Int? = null
    var employeeId:Int? = null
    var date:String? = SimpleDateFormat("dd.MM.yyyy").format(Date())
}