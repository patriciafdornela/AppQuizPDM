package np.com.bimalkafle.quizonline

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import np.com.bimalkafle.quizonline.databinding.QuizItemRecyclerRowBinding

class QuizListAdapter(
    private val quizModelList: List<QuizModel>
) : RecyclerView.Adapter<QuizListAdapter.QuizViewHolder>() {

    inner class QuizViewHolder(
        private val binding: QuizItemRecyclerRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(quizModel: QuizModel) {
            binding.apply {
                quizTitleText.text = quizModel.title
                quizSubtitleText.text = quizModel.subtitle
                quizTimeText.text = "${quizModel.time} min"

                root.setOnClickListener {
                    QuizActivity.apply {
                        questionModelList = quizModel.questionList
                        time = quizModel.time
                    }
                    root.context.startActivity(
                        Intent(root.context, QuizActivity::class.java)
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding = QuizItemRecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }

    override fun getItemCount() = quizModelList.size
}
