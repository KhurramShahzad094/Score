package com.khurram.score.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.*
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.khurram.score.network.Resource
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import java.util.*


object Friend {

//    companion object {
        fun checkInternet(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                val networkInfo = connectivityManager.activeNetworkInfo
                return networkInfo != null && networkInfo.isConnected
            }
            val networks = connectivityManager.allNetworks
            var hasInternet = false
            if (networks.isNotEmpty()) {
                for (network in networks) {
                    val nc = connectivityManager.getNetworkCapabilities(network)
                    if (nc!!.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) hasInternet =
                        true
                }
            }
            return hasInternet
        }


        fun showDialog(
            context: Context,
            title: String = "",
            message: String = "",
            positiveText: String = "",
            positiveFunction: () -> Unit = { dismiss() },
        ) {
            val dialogBox = android.app.AlertDialog.Builder(
                ContextThemeWrapper(
                    context,
                    android.R.style.ThemeOverlay_Material
                )
            )
            val inflater = LayoutInflater.from(context)
            val dialogView = inflater.inflate(
                com.khurram.score.R.layout.dialogbox_layout,
                null
            )
            dialogBox.setView(dialogView)

            val tvTitle: TextView = dialogView.findViewById(com.khurram.score.R.id.tvTitle)
            val tvMessage: TextView =
                dialogView.findViewById(com.khurram.score.R.id.tvMessage)
            val btnPositive: Button =
                dialogView.findViewById(com.khurram.score.R.id.btPositive)

            tvTitle.text = title
            tvMessage.text = message

            if (positiveText.isNotEmpty()) {
                btnPositive.text = positiveText
            } else {
                btnPositive.gone()
            }

            val alertDialog = dialogBox.create()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
            alertDialog.setCancelable(false)

            btnPositive.setOnClickListener {
                alertDialog.dismiss()
                positiveFunction()
            }
        }

        private fun dismiss() {}


        fun showError(response: Resource<JsonElement>): String {
            var errorMessage = when (response.exception) {
                is HttpException -> {
                    "${response.exception.code()}: ${response.exception.message()}"
                }
                is SocketTimeoutException -> {
                    "Please check your internet."
                }
                is UnknownHostException -> {
                    "Please check your backend."
                }
                else -> {
                    "Something went wrong, please try again later."
                }
            }
            return errorMessage
        }

//    }
}