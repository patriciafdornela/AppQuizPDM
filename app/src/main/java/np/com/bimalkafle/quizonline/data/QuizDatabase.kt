//package np.com.bimalkafle.quizonline.local
//
//import QuestionDao
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import np.com.bimalkafle.quizonline.data.QuestionEntity
//
//@Database(entities = [QuestionEntity::class], version = 1, exportSchema = false)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun questionDao(): QuestionDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "quiz_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
