package com.example.dogapiapp.requirement2.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dogapiapp.R

class DogBreedSearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var editText: AppCompatEditText

    private var view: View = inflate(context, R.layout.dog_breed_seach_bar, this)

    init{
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?){
        attrs ?: return

        editText = view.findViewById(R.id.filter)
    }
}