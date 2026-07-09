package com.example.randomjoke

import android.app.Application
import android.content.Context

class JokeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: JokeApp? = null

        fun getContext(): Context {
            return instance!!.applicationContext
        }
    }
}