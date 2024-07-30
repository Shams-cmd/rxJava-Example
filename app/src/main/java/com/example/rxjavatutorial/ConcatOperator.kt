package com.example.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class ConcatOperator : AppCompatActivity() {
    companion object{
        private const val TAG = "concatoperators"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concat_operator)
        concatOperator()
    }

    private fun concatOperator() {
        val observable1 : Observable<String> = Observable.just("A","B","C","D","E")
        val observable2 : Observable<String> = Observable.just("1","2","3","4")

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
        val observerableResult= Observable.concat(observable1,observable2)
        observerableResult.subscribe(observer)
    }

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

}