package clercky.be.starttorun.input

import android.content.Context
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

/**
 * Created by Clercky on 10/11/2017.
 */
class InputView(private val context: Context) {
    private val container: LinearLayout = LinearLayout(context)
    private val txtInput: EditText = EditText(context)
    private val btnRemove: Button = Button(context)

    public var value: String
        public get() = btnRemove.text as String
        private set(value) { btnRemove.text = value}

    public val view: LinearLayout = container

    public var disable: Boolean = false
        get
        set(isDisabled) {
            if (isDisabled) {
                txtInput.isEnabled = false
                btnRemove.isEnabled = false
            } else {
                txtInput.isEnabled = true
                btnRemove.isEnabled = true
            }
        }

    public var onRemove : (inputView: InputView) -> Unit = {inputView ->  }

    init {
        createLayout()
    }

    private fun createLayout() : Unit {
        // container
        container.orientation = LinearLayout.HORIZONTAL
        container.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        // txtInput
        txtInput.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        txtInput.inputType = InputType.TYPE_CLASS_NUMBER

        container.addView(txtInput)

        // btnRemove
        btnRemove.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        btnRemove.text = "-"
        btnRemove.setOnClickListener(View.OnClickListener {
            view ->
                delete()
        })

        this.container.addView(btnRemove)
    }

    public fun delete() : Unit {
        if (container.parent != null) {
            (container.parent as ViewGroup).removeView(container)
            if (onRemove != null) {
                onRemove(this)
            }
        }
    }
}