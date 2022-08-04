package com.gateway.gms

import android.app.Application
import com.gateway.gms.di.GMServiceLocator
import timber.log.Timber

class GMSApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        timberConfig()
        GMServiceLocator.initializeService(this)
    }

    private fun timberConfig(){
        if (BuildConfig.DEBUG)
            Timber.plant(object : Timber.DebugTree() {
                /**
                 * Override [log] to modify the tag and add a "global tag" prefix to it. You can rename the String "global_tag_" as you see fit.
                 */
                override fun log(
                    priority: Int, tag: String?, message: String, t: Throwable?
                ) {
                    super.log(priority, "DEBUGGING_$tag", message, t)
                }

                /**
                 * Override [createStackElementTag] to include a add a "method name" to the tag.
                 */
                override fun createStackElementTag(element: StackTraceElement): String {
                    return String.format(
                        "%s:%s",
                        element.methodName,
                        super.createStackElementTag(element)
                    )
                }
            })
    }
}