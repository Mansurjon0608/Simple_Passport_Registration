package DAO

import Models.Fuqaro
import androidx.room.*

@Dao

interface FuqaroDao {

    @Query("select * from fuqaro")
    fun getAllFuqaro():List<Fuqaro>

    @Insert
    fun addFuqaro(fuqaro: Fuqaro)

    @Delete
    fun deleteCitizen(fuqaro: Fuqaro)

    @Update
    fun updateCitizen(fuqaro: Fuqaro)

    @Query("select * from fuqaro where id=:id")
    fun getFuqaroById(id:Int): Fuqaro

    @Query("select * from fuqaro where passportSeriya=:passportSeriyaN")
    fun getFuqaroById(passportSeriyaN:String):Int
}