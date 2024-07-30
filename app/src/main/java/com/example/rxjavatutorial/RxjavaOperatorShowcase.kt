package com.example.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class RxjavaOperatorShowcase : AppCompatActivity() {

    companion object{
        private const val TAG = "RxjavaOperatorShowcase"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_operator_showcase)

       rxjavaoperstorRange()
    //    rxjavaRepateOperator()
    }

    private fun rxjavaRepateOperator() {

        val observable = Observable.interval(1,TimeUnit.SECONDS)
    }

    private fun rxjavaoperstorRange() {
        val observable = Observable.interval(1,TimeUnit.SECONDS)

        val observer = object : Observer<Long>{
            override fun onSubscribe(d: Disposable) {
             Log.d(TAG,"onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onError ${e.localizedMessage}")
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }

            override fun onNext(t: Long) {
                Log.d(TAG,"onNext $t")
            }

        }
        observable.subscribe(observer)
    }
}