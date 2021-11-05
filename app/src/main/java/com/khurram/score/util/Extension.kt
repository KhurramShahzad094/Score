package com.khurram.score.util

import android.content.Context
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.khurram.score.BuildConfig


fun View.showSnackbar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}

fun Context.showToast(text: String){
    if (BuildConfig.DEBUG)
    Toast.makeText(this, text , Toast.LENGTH_SHORT).show()
}

fun View.visible(){
    this.visibility= View.VISIBLE
}

fun View.invisible(){
    this.visibility= View.INVISIBLE
}

fun View.gone(){
    this.visibility= View.GONE
}