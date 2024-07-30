package com.example.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.Objects

class CompleteableObservable : AppCompatActivity() {

    companion object{
        private const val TAG = "CompleteableObservable"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completeable_observable)

        completeObservable()
    }

    val compositeDisposable = CompositeDisposable()

    private fun completeObservable() {
        val completeableObservable = Completable.complete()
        val observer = object : CompletableObserver{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG,"onSubscribe")
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onError ${e.localizedMessage}")
            }

        }
        completeableObservable.subscribe(observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}