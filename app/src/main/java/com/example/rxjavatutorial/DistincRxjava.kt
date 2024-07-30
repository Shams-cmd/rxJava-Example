package com.example.rxjavatutorial

import android.database.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class DistincRxjava : AppCompatActivity() {
    companion object{
        private const val TAG = "distincRXjava"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distinc_rxjava)
        distinRxOperator()
    }

    private fun distinRxOperator() {
        val observable : io.reactivex.rxjava3.core.Observable<String> = io.reactivex.rxjava3.core.Observable.just("A","B","C",
            "D","E")

        val observer = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
                Log.d(TAG,"onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onError $e")
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }

            override fun onNext(t: String) {
                Log.d(TAG,"onNext $t")
            }
        }

        observable.skip(2).subscribe(observer)
    }

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()




}