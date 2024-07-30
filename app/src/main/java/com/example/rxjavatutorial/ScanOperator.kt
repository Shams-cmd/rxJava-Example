package com.example.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class ScanOperator : AppCompatActivity() {
    companion object{
        private const val TAG = "scanOperator"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_operator)
        
        scanOperatorRxjava()
    }
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
    private fun scanOperatorRxjava() {
        val observable : Observable<String> = Observable.just("s","h","a","m","s")

        val observer = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG,"onSubscribe $d")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onSubscribe ${e.localizedMessage}")
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }

            override fun onNext(t: String) {
                Log.d(TAG,"onNext $t")
            }

        }
         observable.scan { x: String?, y : String? ->x.plus(y).toUpperCase()  }.subscribe(observer)
 

  
    }
   
    

}