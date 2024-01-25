package com.example.openit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class CustomButton  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val textView: TextView
    private val imageView: ImageView
    private val parentView: ConstraintLayout

    init {
        LayoutInflater.from(context).inflate(R.layout.button_border_transparent, this, true)
        textView = findViewById<TextView>(R.id.buttonText)
        imageView = findViewById<ImageView>(R.id.buttonImage)
        parentView = findViewById(R.id.buttonParentView)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ButtonBorderAttr)
        val customText = typedArray.getString(R.styleable.ButtonBorderAttr_customText)
        val image = typedArray.getResourceId(R.styleable.ButtonBorderAttr_customImage,0);
        val background = typedArray.getResourceId(R.styleable.ButtonBorderAttr_customBackground,0);


        typedArray.recycle()
        customText?.let {
            textView.text = it
        }
        image?.let {
            imageView.setImageResource(image)
        }
        background?.let {
            parentView.setBackgroundResource(background)
        }
    }

    fun setMyText(text: String) {
        textView.text = text
    }
    fun setMyImageResource(resourceId: Int) {
        imageView.setImageResource(resourceId)
    }
    fun setMyBackgroundResource(resourceId: Int){
        parentView.setBackgroundResource(resourceId)
    }
}