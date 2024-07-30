package com.example.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class FlatmapOperatorRxJava : AppCompatActivity() {

    companion object{
        private const val TAG = "FlatmapOperatorRxJava"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flatmap_operator_rx_java2)

        flatmapOperator()
    }

    private fun flatmapOperator() {

        val observable : Observable<Int> = Observable.just(12,13)

        val observer = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
                Log.d(TAG,"onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onError ${e.localizedMessage}")
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }

            override fun onNext(t: String) {
                Log.d(TAG,"onNext $t")
            }
        }

        observable
            .flatMap { id->
                getProfilename(id)
            }.subscribe(observer)

    }



    private fun getProfilename(profileId : Int):Observable<String>{
        return when(profileId){
            12 -> Observable.just("shams Abbas")
            13 -> Observable.just("Alhasan rizvi")
            else-> Observable.error(Exception("No data found error"))
        }

    }

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
}