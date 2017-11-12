package clercky.be.starttorun.input

import android.content.Context
import android.view.ViewGroup

/**
 * Created by Clercky on 11/11/2017.
 */
class InputManager(val context: Context, val parent: ViewGroup) {
    private val inputArray: ArrayList<InputView> = ArrayList()

    public var valueArray: ArrayList<String> = ArrayList()
        get() {
            var result: ArrayList<String> = ArrayList()

            for (i in inputArray) {
                result.add(i.value)
            }

            return result
        }

    public var disable: Boolean = false
        get
        set(value) {
            for (i in inputArray) {
                i.disable = value
            }
        }

    public fun add() : Unit {
        val newView: InputView = InputView(context)
        newView.onRemove = {
            inputView ->
            // remove in list
            inputArray.remove(inputView)
        }
        newView.disable = false

        inputArray.add(newView)
        parent.addView(newView.view)
    }

    public fun removeAll(): Unit {
        inputArray.removeAll { inputView -> true }
    }
}