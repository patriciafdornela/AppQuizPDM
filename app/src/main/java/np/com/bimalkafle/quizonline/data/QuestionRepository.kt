//package np.com.bimalkafle.quizonline.data
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.liveData
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.GenericTypeIndicator
//import kotlinx.coroutines.*
//import kotlinx.coroutines.tasks.await
//import np.com.bimalkafle.quizonline.QuizModel
//import np.com.bimalkafle.quizonline.QuestionModel
//import np.com.bimalkafle.quizonline.local.AppDatabase
//import np.com.bimalkafle.quizonline.local.QuestionEntity
//
//class QuestionRepository(private val context: Context) {
//
//    private val db = AppDatabase.getDatabase(context)
//    private val firebaseRef = FirebaseDatabase.getInstance().reference.child("quizzes")
//    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
//
//    fun syncQuestions() = coroutineScope.launch {
//        try {
//            val snapshot = firebaseRef.get().await()
//            val questions = mutableListOf<QuestionEntity>()
//
//            snapshot.children.forEach { quizSnap ->
//                val quizId = quizSnap.key ?: return@forEach
//                val title = quizSnap.child("title").getValue(String::class.java) ?: ""
//                val subtitle = quizSnap.child("subtitle").getValue(String::class.java) ?: ""
//                val time = quizSnap.child("time").getValue(String::class.java) ?: ""
//
//                quizSnap.child("questions").children.forEach { qSnap ->
//                    val qText = qSnap.child("question").getValue(String::class.java) ?: ""
//                    val options = qSnap.child("options")
//                        .getValue(object : GenericTypeIndicator<List<String>>() {}) ?: emptyList()
//                    val correct = qSnap.child("correct").getValue(String::class.java) ?: ""
//
//                    val entity = QuestionEntity(
//                        quizId = quizId,
//                        quizTitle = title,
//                        quizSubtitle = subtitle,
//                        quizTime = time,
//                        questionText = qText,
//                        optionA = options.getOrNull(0) ?: "",
//                        optionB = options.getOrNull(1) ?: "",
//                        optionC = options.getOrNull(2) ?: "",
//                        optionD = options.getOrNull(3) ?: "",
//                        correctAnswer = correct
//                    )
//                    questions.add(entity)
//                }
//            }
//
//            db.questionDao().insertQuestions(questions)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    suspend fun getLocalQuestions(): List<QuizModel> = withContext(Dispatchers.IO) {
//        val quizEntities = db.questionDao().getAllQuizzes()
//        quizEntities.map { entity ->
//            val questions = db.questionDao().getQuestionsByQuiz(entity.quizId).map { q ->
//                QuestionModel(
//                    question = q.questionText,
//                    options = listOf(q.optionA, q.optionB, q.optionC, q.optionD),
//                    correct = q.correctAnswer
//                )
//            }
//            QuizModel(
//                id = entity.quizId,
//                title = entity.quizTitle,
//                subtitle = entity.quizSubtitle,
//                time = entity.quizTime,
//                questionList = questions
//            )
//        }
//    }
//}
