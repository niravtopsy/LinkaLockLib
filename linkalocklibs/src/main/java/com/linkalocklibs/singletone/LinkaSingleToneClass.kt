package com.linkalocklibs.singletone

import android.app.Activity
import com.linka.linkaapikit.module.api.LinkaMerchantAPIServiceImpl
import com.linka.linkaapikit.module.api.LinkaMerchantAPIServiceResponse
import com.linka.linkaapikit.module.api.ResponseCallback
import com.linka.linkaapikit.module.helpers.LogHelper
import com.linka.linkaapikit.module.model.LockConnectionService
import com.linka.linkaapikit.module.model.LockConnectionService.ErrorCallbacks
import com.linka.linkaapikit.module.model.LockConnectionService.SuccessCallbacks
import com.linkalocklibs.`interface`.IAPILinkaLockCallback
import retrofit2.Response

class LinkaSingleToneClass(activity: Activity, iapiLinkaLockCallback: IAPILinkaLockCallback) {

    var lockConnectionService =
        LockConnectionService(activity, object : SuccessCallbacks {
            override fun onPairingSuccess() {
                iapiLinkaLockCallback.onLinkaLockPairingSuccess()
            }

            override fun onBatteryPercent(percent: Int) {
                LogHelper.e("batteryPercent", Integer.toString(percent))
                iapiLinkaLockCallback.onLinkaLockBatteryPercent(percent)
            }

            override fun onScanFound() {
            }

            override fun onConnected() {
            }

            override fun onUnlockStarted() {
            }

            override fun onLockStarted() {
            }

            override fun onLock() {
                iapiLinkaLockCallback.onLinkaLockLockedSuccess()
            }

            override fun onUnlock() {
                iapiLinkaLockCallback.onLinkaLockUnLockedSuccess()
            }

            override fun onQueryLocked() {
                iapiLinkaLockCallback.onLinkaLockQueryLocked()
            }

            override fun onQueryUnlocked() {
                iapiLinkaLockCallback.onLinkaLockQueryUnLocked()
            }
        }, object : ErrorCallbacks {
            override fun errorInternetOff() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorInternetOff()
            }

            override fun onInvalidMac() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorInvalidMac()
            }

            override fun onScanTimeout() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorScanTimeout()
            }

            override fun errorEmptymacAddress() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorEmptymacAddress()
            }

            override fun errorBluetoothOff() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorBluetoothOff()
            }

            override fun errorLocationOff() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorLocationOff()
            }

            override fun errorGpsOff() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorGpsOff()
            }

            override fun errorInvalidAccessToken() {
                //Upon receipt of this error, fetch a new access token
                onLinkaDisconnect()
                fetchAccessToken(activity)
                iapiLinkaLockCallback.onLinkaLockErrorInvalidAccessToken()
            }

            override fun errorMacAddress(macError: String) {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorMacAddress(macError)
            }

            override fun errorConnectionTimeout() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorConnectionTimeout()
            }

            override fun errorUnexpectedDisconnect() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorUnexpectedDisconnect()
            }

            override fun errorLockingTimeout() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorLockingTimeout()
            }

            override fun errorUnlockingTimeout() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorUnlockingTimeout()
            }

            override fun errorLockJam() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorLockJam()
            }

            override fun errorAppNotInForeground() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorAppNotInForeground()
            }

            override fun errorLockStall() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorLockStall()
            }

            override fun pairTimeout() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorPairTimeout()
            }

            override fun errorLockBlock() {
                onLinkaDisconnect()
                iapiLinkaLockCallback.onLinkaLockErrorLockBlock()
            }
        })

    fun fetchAccessToken(activity: Activity) {

        LinkaMerchantAPIServiceImpl.fetch_access_token(object :
            ResponseCallback<LinkaMerchantAPIServiceResponse?>() {
            override fun onResponse(response: Response<LinkaMerchantAPIServiceResponse?>) {
                if (LinkaMerchantAPIServiceImpl.check(response, false, null)) {

                } else {

                }
            }

            override fun onError(code: Int, message: String) {}
        })
    }


    fun onLinkaLockDoLock(macAddress: String): Boolean {

        lockConnectionService.setLinkaMacAddress(macAddress)
        val isSuccess: Boolean = lockConnectionService.doLock()

        return isSuccess
    }

    fun onLinkaLockDoUnLock(macAddress: String): Boolean {

        lockConnectionService.setLinkaMacAddress(macAddress)
        val isSuccess: Boolean = lockConnectionService.doUnlock()

        return isSuccess
    }

    fun onLinkaLockQuery(macAddress: String): Boolean {

        lockConnectionService.setLinkaMacAddress(macAddress)
        val isSuccess: Boolean = lockConnectionService.query()

        return isSuccess
    }

    fun onLinkaTryAgainLock() {
        lockConnectionService.tryAgainLock()
    }

    fun onLinkaTryAgainUnlock() {
        lockConnectionService.tryAgainUnlock()
    }

    fun onLinkaDisconnect() {
        lockConnectionService.onDisconnect()
    }
}