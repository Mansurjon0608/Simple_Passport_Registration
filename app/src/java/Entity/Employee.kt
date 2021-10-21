package com.ilhomjon.room5.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Employee {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    var name:String? = null
}