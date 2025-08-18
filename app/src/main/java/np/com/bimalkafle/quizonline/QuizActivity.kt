package np.com.bimalkafle.quizonline

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import np.com.bimalkafle.quizonline.databinding.ActivityQuizBinding
import np.com.bimalkafle.quizonline.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity() {

    companion object {
        lateinit var questionModelList: List<QuestionModel>
        var time: String = ""
    }

    private lateinit var binding: ActivityQuizBinding
    private var currentQuestionIndex = 0
    private var selectedAnswer = ""
    private var score = 0
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        loadQuestions()
        startTimer()
    }

    private fun setupClickListeners() {
        binding.apply {
            btn0.setOnClickListener { onAnswerSelected(it as Button) }
            btn1.setOnClickListener { onAnswerSelected(it as Button) }
            btn2.setOnClickListener { onAnswerSelected(it as Button) }
            btn3.setOnClickListener { onAnswerSelected(it as Button) }
            nextBtn.setOnClickListener { onNextClicked() }
            voltarBtn.setOnClickListener { onVoltarClicked() }
            sairBtn.setOnClickListener { onSairClicked() }
        }
    }

    private fun startTimer() {
        val totalTimeInMillis = time.toIntOrNull()?.times(60 * 1000L) ?: 0L
        timer = object : CountDownTimer(totalTimeInMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60
                binding.timerIndicatorTextview.text = "%02d:%02d".format(minutes, seconds)
            }

            override fun onFinish() {
                finishQuiz()
            }
        }.start()
    }

    private fun loadQuestions() {
        if (currentQuestionIndex >= questionModelList.size) {
            finishQuiz()
            return
        }
        binding.voltarBtn.isEnabled = currentQuestionIndex > 0

        binding.apply {
            questionIndicatorTextview.text =
                "Questão ${currentQuestionIndex + 1}/${questionModelList.size}"
            questionProgressIndicator.progress =
                ((currentQuestionIndex.toFloat() / questionModelList.size) * 100).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].question

            listOf(btn0, btn1, btn2, btn3).forEachIndexed { index, button ->
                button.text =
                    questionModelList[currentQuestionIndex].options.getOrNull(index) ?: ""
                button.setBackgroundColor(getColor(R.color.gray))
            }
        }
        selectedAnswer = ""
    }

    private fun onAnswerSelected(button: Button) {
        resetButtonColors()
        selectedAnswer = button.text.toString()
        button.setBackgroundColor(getColor(R.color.orange))
    }

    private fun onNextClicked() {
        if (selectedAnswer.isEmpty()) {
            Toast.makeText(
                this,
                "Por favor, selecione uma resposta para continuar",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (selectedAnswer == questionModelList[currentQuestionIndex].correct) {
            score++
        }
        currentQuestionIndex++
        loadQuestions()
    }

    private fun onVoltarClicked() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            loadQuestions()
        }
    }

    private fun onSairClicked() {
        AlertDialog.Builder(this)
            .setTitle("Confirmar saída")
            .setMessage("Tem certeza que deseja voltar para a tela inicial?")
            .setPositiveButton("Sim") { _, _ -> finish() }
            .setNegativeButton("Não", null)
            .show()
    }

    private fun resetButtonColors() {
        listOf(binding.btn0, binding.btn1, binding.btn2, binding.btn3).forEach {
            it.setBackgroundColor(getColor(R.color.gray))
        }
    }

    private fun finishQuiz() {
        timer.cancel()
        val percentage = (score.toFloat() / questionModelList.size * 100).toInt()

        ScoreDialogBinding.inflate(layoutInflater).apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage%"

            if (percentage > 60) {
                scoreTitle.text = "Parabéns! Você concluiu o quiz com sucesso"
                scoreTitle.setTextColor(Color.BLUE)
            } else {
                scoreTitle.text =
                    "Oops! Infelizmente você não obteve o mínimo necessário para passar"
                scoreTitle.setTextColor(Color.RED)
            }

            scoreSubtitle.text = "$score de ${questionModelList.size} corretas"
            finishBtn.setOnClickListener { finish() }

            AlertDialog.Builder(this@QuizActivity)
                .setView(root)
                .setCancelable(false)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::timer.isInitialized) {
            timer.cancel()
        }
    }
}
