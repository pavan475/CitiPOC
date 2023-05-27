package com.example.citipoc.utils

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.example.citipoc.R

class HeadTextViewHolder(itemView: View, context: Context?, adapter: BaseRecyclerAdapter<*>?) :
    BaseViewHolder<HeadTextViewModel?>(itemView, context!!, adapter) {
    var textView: AppCompatTextView
    fun onBind(position: Int, model: HeadTextViewModel, listener: BaseInteractionListener?) {
        super.onBind(position, model, listener)
        if (!TextUtils.isEmpty(model.text)) {
            textView.setText(model.text)

        } else {
            textView.text = ""
        }
    }

    init {
        textView = itemView.findViewById(R.id.text2)
    }
}
