package com.okan.ecommerce.data

import android.app.Application
import android.content.Context

open class BaseApplication : Application() {

    companion object {

        @JvmField
        var appInstance: BaseApplication? = null



        @JvmStatic fun getAppInstance(): BaseApplication {
            return appInstance as BaseApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance=this;
    }

}