package DB

import DAO.FuqaroDao
import Models.Fuqaro
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fuqaro::class], version = 1)

abstract class AppDatabase :RoomDatabase() {

    abstract fun fuqaroDao(): FuqaroDao

    companion object {
        private var instance:AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context?):AppDatabase{
            if (instance==null){
                instance = Room.databaseBuilder(context!!, AppDatabase::class.java, "fuqaro_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}