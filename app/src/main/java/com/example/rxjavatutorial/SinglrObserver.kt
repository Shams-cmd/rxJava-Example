package com.example.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.Locale
import java.util.Objects

class SinglrObserver : AppCompatActivity() {

    companion object{
        private const val TAG = "SinglrObserver"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singlr_observer)

        singleObserver()
    }

    private val compositeDisposable = CompositeDisposable()

    private fun singleObserver() {
        val singeleObserverble = Single.just("my name is shams",)
         val singeleObserver = object : SingleObserver<String>{
             override fun onSubscribe(d: Disposable) {
                 Log.d(TAG,"onSubscribe")
             }

             override fun onError(e: Throwable) {
                 Log.d(TAG,"onError  ${e.localizedMessage}")
             }

             override fun onSuccess(t: String) {
                 Log.d(TAG,"onSuccess $t")
             }

         }

        singeleObserverble
            .map { it.uppercase(Locale.ENGLISH) }
            .subscribe(singeleObserver)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}