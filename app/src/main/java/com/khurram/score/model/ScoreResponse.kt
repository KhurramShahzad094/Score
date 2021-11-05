package com.khurram.score.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScoreResponse( var creditReportInfo : CreditReportInfo?) : Parcelable

@Parcelize
data class CreditReportInfo( var score : Int,  var maxScoreValue : Int, val clientRef : String?,val status : String?) : Parcelable
