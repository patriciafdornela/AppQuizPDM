//package np.com.bimalkafle.quizonline.data
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//
//@Dao
//interface QuestionDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertQuestions(questions: List<QuestionEntity>)
//
//    @Query("SELECT * FROM questions GROUP BY id")
//    suspend fun getAllQuizzes(): List<QuestionEntity>
//
//    @Query("SELECT * FROM questions WHERE id = :quizId")
//    suspend fun getQuestionsByQuiz(quizId: String): List<QuestionEntity>
//}
