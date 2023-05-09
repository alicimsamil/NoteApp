package com.task.noteapp.core.ui.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.task.noteapp.R
import com.task.noteapp.util.extensions.visible
import com.task.noteapp.util.extensions.gone

/**
 * This button a lifecycle aware component. Because if a View's visibility property is set to View.GONE,
 * that View and its content will be completely hidden,
 * both in memory and on the screen.
 * Thus, we avoid unnecessary memory usage.
 */
class NoteAppButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr), DefaultLifecycleObserver {

    private var icon: Drawable? = null
    private var isIconVisible = false
    private var iconSize = 0

    init {
        setShape()
        setTextColor()
        setPadding()
        getTypedAttributes(attrs, defStyleAttr)
        updateIconVisibility()
    }

    private fun updateIconVisibility() {
        if (icon != null) {
            if (isIconVisible) {
                val size = if (iconSize == 0) (textSize * 1.5f).toInt() else iconSize
                val drawable = icon?.mutate()
                drawable?.setBounds(0, 0, size, size)
                val halfPadding = resources.getDimensionPixelSize(R.dimen.space_small_small)
                gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL or Gravity.TOP
                setCompoundDrawables(drawable, null, null, null)
                compoundDrawablePadding = halfPadding
            } else {
                setCompoundDrawables(null, null, null, null)
            }
        }
    }

    fun setIcon(icon: Drawable?) {
        this.icon = icon
        updateIconVisibility()
    }

    fun setIconVisible(isVisible: Boolean) {
        isIconVisible = isVisible
        updateIconVisibility()
    }

    fun setIconSize(size: Int) {
        iconSize = size
        updateIconVisibility()
    }

    private fun setShape() {
        val shape = GradientDrawable()
        shape.cornerRadius = resources.getDimension(R.dimen.space_small)
        shape.setColor(ContextCompat.getColor(context, R.color.light_primary))
        background = shape
    }

    private fun setTextColor() {
        setTextColor(ContextCompat.getColor(context, R.color.light_primary_container))
    }

    private fun setPadding() {
        val paddingHorizontal = resources.getDimensionPixelSize(R.dimen.space_small_small)
        val paddingVertical = resources.getDimensionPixelSize(R.dimen.space_extra_small)
        setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)
    }

    private fun getTypedAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.NoteAppButton,
            defStyleAttr,
            0
        )

        icon = typedArray.getDrawable(R.styleable.NoteAppButton_nab_icon)
        isIconVisible = typedArray.getBoolean(R.styleable.NoteAppButton_nab_icon_visibility, false)
        iconSize = typedArray.getDimensionPixelSize(R.styleable.NoteAppButton_iconSize, 0)

        typedArray.recycle()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        visible()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        visible()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        gone()
    }
}
