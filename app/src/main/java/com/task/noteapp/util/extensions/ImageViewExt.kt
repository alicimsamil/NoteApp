package com.task.noteapp.util.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.task.noteapp.R

fun AppCompatImageView.loadImage(imageUrl: String){
    Glide.with(this).load(imageUrl).placeholder(R.drawable.mountain_view).into(this)
}