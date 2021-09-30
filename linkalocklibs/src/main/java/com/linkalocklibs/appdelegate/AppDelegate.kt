package com.linkalocklibs.appdelegate

import android.app.Application
import com.linka.linkaapikit.module.api.LinkaAPIServiceConfig
import com.linka.linkaapikit.module.widget.LockController

class AppDelegate : Application() {
    override fun onCreate() {
        super.onCreate()
        LinkaAPIServiceConfig.initialize(this)
        LinkaAPIServiceConfig.setAPIProtocol(AppLinkaAPIManager(this))
    }

    companion object {
        var currentLockController: LockController? = null
    }
}