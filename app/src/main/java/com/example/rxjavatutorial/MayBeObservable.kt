package com.example.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.Locale

class MayBeObservable : AppCompatActivity() {

    companion object{
        private const val TAG = "MayBeObservable"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_may_be_observable)
        maybeObservable()
    }

    val compositeDisposable = CompositeDisposable()

    private fun maybeObservable() {

        val mayBeObservable = Maybe.just("hello shams abbas")
      //  val mayBeObservable = Maybe.empty<String>()
        val maybeObserver = object : MaybeObserver<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG,"onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onError ${e.localizedMessage}")
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }

            override fun onSuccess(t: String) {
                Log.d(TAG,"onSuccess")
            }

        }

        mayBeObservable.map { it.uppercase(Locale.ENGLISH) }
            .subscribe(maybeObserver)
    }
}