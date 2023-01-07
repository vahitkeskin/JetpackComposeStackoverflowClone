package com.vahitkeskin.jetpackcomposestackoverflowclone

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.FlipperNetworkObject
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
@HiltAndroidApp
class StackoverflowApp: Application() {
    override fun onCreate() {
        super.onCreate()
        //Facebook Flipper
        SoLoader.init(this, false) //TODO Without this it will never work
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val clint: FlipperClient = AndroidFlipperClient.getInstance(this)
            val networkFlipperPlugin = NetworkFlipperPlugin()
            FlipperNetworkObject.networkFlipperPlugin = networkFlipperPlugin
            clint.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            clint.addPlugin(networkFlipperPlugin)
            clint.start()
        }

        //Logger
        val formatStrategy: FormatStrategy = PrettyFormatStrategy
            .newBuilder()
            .showThreadInfo(true)
            .methodCount(1)
            .methodOffset(5)
            .tag("")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        //Timber
        Timber.plant(object: Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, "-$tag", message, t)
            }
        })
        Timber.d("Inside App!")
    }
}