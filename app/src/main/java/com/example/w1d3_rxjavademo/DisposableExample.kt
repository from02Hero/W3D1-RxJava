package com.example.w1d3_rxjavademo

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

fun main() {
    rightWayToDoIt()
    Thread.sleep(3000)
}

fun emitSomething() {
    Observable.just("Apple", "Orange", "Banana")
        .subscribe { v -> println("Received: $v") }.dispose()
}

fun failureExample() {
    Observable.just("Apple", "Orange", "Banana")
        .subscribeOn(Schedulers.io()) //Changing the thread
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { v -> println("Received: $v") }.dispose()
}

fun rightWayToDoIt() {
    val compositeDisposable = CompositeDisposable()

    val observableOne = Observable.just("Tree")
        .subscribe { v -> println("Received: $v") }
    val observableTwo = Observable.just("Blue")
        .subscribe { v -> println("Received: $v") }

    compositeDisposable.add(observableOne)
    compositeDisposable.add(observableTwo)
    compositeDisposable.clear()
}