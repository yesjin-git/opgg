package com.example.opgg.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.opgg.BuildConfig
import com.example.opgg.R
import com.example.opgg.ui.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.HttpException
import java.net.UnknownHostException

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
        attachUnexpectedErrorHandler()
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun attachUnexpectedErrorHandler() {
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            if (BuildConfig.BUILD_TYPE == "release") {
                when (e) {
                    is UnknownHostException -> showToastMessage("네트워크 연결 상태를 확인해 주세요.")
                    else -> showToastMessage("알 수 없는 오류입니다. 다시 시도해 주세요.")
                }
            } else if (BuildConfig.BUILD_TYPE == "debug") {
                e.printStackTrace()
                when (e) {
                    is UnknownHostException -> Log.e("ERROR_HANDLER", "Check network connection")
                    is HttpException -> when (e.code()) {
                        404 -> Log.e("ERROR_HANDLER", "Page not found error ${e.response()}")
                    }
                }
                throw e
            }
        }
    }
}
