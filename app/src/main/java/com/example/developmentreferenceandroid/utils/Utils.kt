import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.example.developmentreferenceandroid.R
import com.example.developmentreferenceandroid.ui.components.ClickableSpanTextView

fun showCustomAlertDialog(context: Context, title: String, message: String, classMethodMap: Map<String, String>) {
    val builder = AlertDialog.Builder(context)
    val inflater = LayoutInflater.from(context)
    val dialogView = inflater.inflate(R.layout.custom_alert_dialog, null)
    builder.setView(dialogView)

    val alertTitle = dialogView.findViewById<TextView>(R.id.alertTitle)
    val alertMessage = dialogView.findViewById<ClickableSpanTextView>(R.id.alertMessage)

    alertTitle.text = title
    alertMessage.setTextWithSpans(message, classMethodMap)

    builder.setPositiveButton("OK") { dialog, _ ->
        dialog.dismiss()
    }

    val dialog = builder.create()
    dialog.show()
}