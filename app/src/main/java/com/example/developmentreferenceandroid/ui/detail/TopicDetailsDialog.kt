
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.developmentreferenceandroid.R
import com.example.developmentreferenceandroid.ui.components.ClickableSpanTextView
import com.example.developmentreferenceandroid.utils.CLASS_METHOD_MAP

class TopicDetailsDialog : DialogFragment() {

    private var topicTitle: String? = null
    private var topicContent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topicTitle = arguments?.getString(ARG_TOPIC_TITLE)
        topicContent = arguments?.getString(ARG_TOPIC_CONTENT)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.custom_alert_dialog, null)
            builder.setView(dialogView)

            val alertTitle = dialogView.findViewById<TextView>(R.id.alertTitle)
            val alertMessage = dialogView.findViewById<ClickableSpanTextView>(R.id.alertMessage)

            alertTitle.text = topicTitle
            val classMethodMap = CLASS_METHOD_MAP
            alertMessage.setTextWithSpans(topicContent ?: "", classMethodMap)


            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        private const val ARG_TOPIC_TITLE = "topic_title"
        private const val ARG_TOPIC_CONTENT = "topic_content"

        fun newInstance(title: String, content: String): TopicDetailsDialog {
            val args = Bundle().apply {
                putString(ARG_TOPIC_TITLE, title)
                putString(ARG_TOPIC_CONTENT, content)
            }
            return TopicDetailsDialog().apply {
                arguments = args
            }
        }
    }
}