package com.erzhanov.coreapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import com.erzhanov.coreapp.BuildConfig
import com.erzhanov.coreapp.R
import com.erzhanov.coreapp.data.common.AppInfoProvider

class AppInfoProviderImpl(context: Context) : AppInfoProvider {
    @SuppressLint("HardwareIds")
    private val androidIdVal = Settings.Secure.getString(context.contentResolver,
            Settings.Secure.ANDROID_ID)
    private val manufacturer = Build.MANUFACTURER
    private val model = Build.MODEL
    private val deviceNameVal = if (model.startsWith(
                    manufacturer)) model else "$manufacturer $model"

    override val androidId: String = androidIdVal
    override val deviceName: String = deviceNameVal
    override val isDebug: Boolean = BuildConfig.DEBUG
    override val applicationId: String = BuildConfig.APPLICATION_ID
    override val buildType: String = BuildConfig.BUILD_TYPE
    override val versionCode: Int = BuildConfig.VERSION_CODE
    override val versionName: String = BuildConfig.VERSION_NAME
    override val osVersion: String = Build.VERSION.RELEASE
    override val baseHost: String = BuildConfig.API_BASE_HOST
    override val apiKey: String = BuildConfig.API_X_API_KEY
    override val appName: String = context.getString(R.string.app_name)
}