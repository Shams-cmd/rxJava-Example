package com.example.rxjavatutorial

import android.database.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class DebounceOperatorRxjava : AppCompatActivity() {
    companion object{
        private const val TAG = "debounceOperator"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debounce_operator_rxjava)
           debounceRxjava()
    }
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    private fun debounceRxjava() {
        val observable : io.reactivex.rxjava3.core.Observable<String> = io.reactivex.rxjava3.core.Observable.create {
            emitter ->
            emitter.onNext("Ball 1")
            Thread.sleep(1500)
            emitter.onNext("Ball 2")
            Thread.sleep(500)
            emitter.onNext("Ball 3")
            Thread.sleep(800)
            emitter.onNext("Ball 4")
            Thread.sleep(2000)
            emitter.onComplete()
        }
        val observer = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
                Log.d(TAG,"onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onError ${e?.localizedMessage}")
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }

            override fun onNext(t: String) {
                Log.d(TAG,"onNext $t")
            }

        }

        observable.debounce(1,TimeUnit.SECONDS).subscribe(observer)
    }


}