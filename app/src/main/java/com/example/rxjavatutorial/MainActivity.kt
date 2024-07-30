package com.example.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.Locale

class MainActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "MainActivityTag"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rxdataflowfunction()
    }

    private val compositeDisposable = CompositeDisposable()

    private fun rxdataflowfunction() {
        val observabl= io.reactivex.rxjava3.core.Observable.just("hello","shams")

        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {

                compositeDisposable.add(d)
                Log.d(TAG,"onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onError : ${e?.localizedMessage}")
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }

            override fun onNext(t: String) {
                Log.d(TAG,"onNext : $t")
            }

        }

        observabl.map { it.uppercase(Locale.ENGLISH)}
            .subscribe(observer)
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}