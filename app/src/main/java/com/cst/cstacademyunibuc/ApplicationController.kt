package com.cst.cstacademyunibuc

import android.app.Application

class ApplicationController: Application() {

    companion object {
        var instance: ApplicationController? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

}