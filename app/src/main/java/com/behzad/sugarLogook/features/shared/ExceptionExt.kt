package com.behzad.sugarLogook.features.shared

import android.content.Context
import com.behzad.sugarLogook.R

fun Exception.getErrorMessage(context: Context): String {
    return when (this) {
        is NoConnectivityException -> context.getString(R.string.error_connection_issue)
        is HttpClientError -> context.getString(R.string.error_http_client)
        is HttpServerError -> context.getString(R.string.error_http_server)
        else -> context.getString(R.string.error_http_unknown)
    }
}