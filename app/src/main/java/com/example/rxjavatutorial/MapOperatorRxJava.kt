package com.example.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.operators.completable.CompletableDisposeOn

class MapOperatorRxJava : AppCompatActivity() {
    companion object{
        private const val TAG ="MapOperatorRxJava"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_operator_rx_java)
        mapoperator()
    }
    val compositeDisposable = CompositeDisposable()

    private fun mapoperator() {
        val observable = Observable.just<String>("Hello","shams abbas")

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
        observable.map { it.toUpperCase() }.subscribe(observer)
    }



}