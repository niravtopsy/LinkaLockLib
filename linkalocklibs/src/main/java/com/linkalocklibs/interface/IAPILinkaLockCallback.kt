package com.linkalocklibs.`interface`

interface IAPILinkaLockCallback {

    fun onLinkaLockPairingSuccess()
    fun onLinkaLockBatteryPercent(batteryPercent: Int)
    fun onLinkaLockLockedSuccess()
    fun onLinkaLockUnLockedSuccess()
    fun onLinkaLockQueryLocked()
    fun onLinkaLockQueryUnLocked()

    fun onLinkaLockErrorInternetOff()
    fun onLinkaLockErrorInvalidMac()
    fun onLinkaLockErrorScanTimeout()
    fun onLinkaLockErrorEmptymacAddress()
    fun onLinkaLockErrorBluetoothOff()
    fun onLinkaLockErrorLocationOff()
    fun onLinkaLockErrorGpsOff()
    fun onLinkaLockErrorInvalidAccessToken()
    fun onLinkaLockErrorMacAddress(macError: String)
    fun onLinkaLockErrorConnectionTimeout()
    fun onLinkaLockErrorUnexpectedDisconnect()
    fun onLinkaLockErrorLockingTimeout()
    fun onLinkaLockErrorUnlockingTimeout()
    fun onLinkaLockErrorLockJam()
    fun onLinkaLockErrorAppNotInForeground()
    fun onLinkaLockErrorLockStall()
    fun onLinkaLockErrorPairTimeout()
    fun onLinkaLockErrorLockBlock()

}