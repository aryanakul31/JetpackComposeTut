package com.nakul.template.network_calling

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.google.gson.Gson
import com.nakul.template.MainActivity
import com.nakul.template.R
import com.nakul.template.database.preference.PreferenceImpl
import com.nakul.template.model.response.BaseResponseModel
import com.nakul.template.utils.safeCall
import io.ktor.client.features.ResponseException
import io.ktor.client.features.cache.InvalidCacheStateException
import io.ktor.client.statement.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.Stack
import javax.inject.Inject


class ApiCalling @Inject constructor(sharedPrefImpl: PreferenceImpl) {
    private fun showError(context: Context, message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }


    private val onApiErrorDefaultHandler =
        { context: Context, message: String, exception: Throwable? ->
            Log.e("Api Util", "onApiErrorDefaultHandler $message")
            when (exception) {
                is InvalidCacheStateException -> {
                    //TODO Handle 304 Response with absent cache
                }
            }
            exception?.printStackTrace()
            showError(context, message)
        }

    private val onResponseErrorDefaultHandler =
        { context: Context, responseException: ResponseException ->
            responseException.printStackTrace()
            Log.e("Api Util", "onResponseErrorDefaultHandler ${responseException.response.status}")
            when (responseException.response.status.value) {
                401 -> {
                    showError(context, context.getString(R.string.unauthorised_access))
                    MainActivity.iMainActivity?.getActivity()?.apply {
                        sharedPrefImpl.clearPreference()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }

                else -> {
                    val dataResponse = flow {
                        emit(responseException.response.readText(Charsets.UTF_8))
                    }.flowOn(Dispatchers.IO)

                    CoroutineScope(Dispatchers.Main).launch {
                        dataResponse.catch {
                            showError(context, responseException.response.status.description)
                        }.collectLatest { response ->
                            val text = try {
                                Gson().fromJson(
                                    response,
                                    BaseResponseModel::class.java
                                ).message?.ifBlank { throw NullPointerException() }
                                    ?: throw NullPointerException()
                            } catch (e: Exception) {
                                e.printStackTrace()
                                responseException.response.status.description
                            }
                            showError(context, text)
                        }
                    }
                }
            }
            Unit
        }

    suspend fun <T> hitApi(
        requestHandler: RequestHandler<T>,
        loaderView: View? = null,
        onResponse: (T) -> Unit,
        onApiError: (context: Context, message: String, exception: Throwable?) -> Unit = onApiErrorDefaultHandler,
        onResponseError: (context: Context, responseException: ResponseException) -> Unit = onResponseErrorDefaultHandler,
    ) {
        val context = MainActivity.iMainActivity?.getActivity() ?: return
        if (!isNetworkAvailable(context)) {
            onApiError.invoke(context, context.getString(R.string.no_internet), null)
            return
        }

        if (loaderView == null) {
//            showProgress(context, R.layout.progress_layout)
        } else
            loaderView.visibility = View.INVISIBLE


//        val focusManager = LocalFocusManager.current
//        focusManager.clearFocus()

        val dataResponse = flow {
            emit(requestHandler.sendRequest())
        }.flowOn(Dispatchers.IO)
//        hideSoftKeyBoard()
        dataResponse.catch { exception ->
            if (loaderView == null)
                hideProgress()
            loaderView?.visibility = View.VISIBLE
            when (exception) {
                is ResponseException -> onResponseError.invoke(context, exception)
                else -> onApiError.invoke(
                    context,
                    exception.message ?: exception.localizedMessage,
                    exception
                )
            }
        }.collectLatest { response ->
            if (loaderView == null)
                hideProgress()
            loaderView?.visibility = View.VISIBLE
            onResponse(response)
        }
    }

    //Loader
    private val customDialog = Stack<AlertDialog>()
    private fun showProgress(context: Context, @LayoutRes layoutId: Int) =
        CoroutineScope(Dispatchers.Main).launch {
            val customAlertBuilder = AlertDialog.Builder(context)
            val view = LayoutInflater.from(context).inflate(layoutId, null, false)
            customAlertBuilder.setView(view)
            customAlertBuilder.setCancelable(false)
            val temp = customAlertBuilder.create()
            customDialog.add(temp)

            temp.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            temp.show()
        }

    private fun hideProgress() = CoroutineScope(Dispatchers.Main).launch {
        safeCall {
            customDialog.pop()?.dismiss()
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}