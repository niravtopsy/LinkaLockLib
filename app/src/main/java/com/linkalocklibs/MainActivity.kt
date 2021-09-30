package com.linkalocklibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.linkalocklibs.`interface`.IAPILinkaLockCallback
import com.linkalocklibs.singletone.LinkaSingleToneClass

class MainActivity : AppCompatActivity(), IAPILinkaLockCallback {

    lateinit var tvLock: TextView
    lateinit var tvUnLock: TextView
    lateinit var tvQuery: TextView

    lateinit var linkaSingleToneClass: LinkaSingleToneClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvLock = findViewById(R.id.tvLock)
        tvUnLock = findViewById(R.id.tvUnLock)
        tvQuery = findViewById(R.id.tvQuery)

        linkaSingleToneClass = LinkaSingleToneClass(this@MainActivity, this)
        linkaSingleToneClass.fetchAccessToken(this@MainActivity)

        tvLock.setOnClickListener {
            linkaSingleToneClass.onLinkaLockDoLock("E0:D3:4C:75:99:19")
        }

        tvUnLock.setOnClickListener {
            linkaSingleToneClass.onLinkaLockDoUnLock("E0:D3:4C:75:99:19")
        }

        tvQuery.setOnClickListener {
            linkaSingleToneClass.onLinkaLockQuery("E0:D3:4C:75:99:19")
        }
    }

    override fun onLinkaLockPairingSuccess() {

    }

    override fun onLinkaLockBatteryPercent(batteryPercent: Int) {

    }

    override fun onLinkaLockLockedSuccess() {

    }

    override fun onLinkaLockUnLockedSuccess() {

    }

    override fun onLinkaLockQueryLocked() {

    }

    override fun onLinkaLockQueryUnLocked() {

    }

    override fun onLinkaLockErrorInternetOff() {

    }

    override fun onLinkaLockErrorInvalidMac() {

    }

    override fun onLinkaLockErrorScanTimeout() {

    }

    override fun onLinkaLockErrorEmptymacAddress() {

    }

    override fun onLinkaLockErrorBluetoothOff() {

    }

    override fun onLinkaLockErrorLocationOff() {

    }

    override fun onLinkaLockErrorGpsOff() {

    }

    override fun onLinkaLockErrorInvalidAccessToken() {

    }

    override fun onLinkaLockErrorMacAddress(macError: String) {

    }

    override fun onLinkaLockErrorConnectionTimeout() {

    }

    override fun onLinkaLockErrorUnexpectedDisconnect() {

    }

    override fun onLinkaLockErrorLockingTimeout() {

    }

    override fun onLinkaLockErrorUnlockingTimeout() {

    }

    override fun onLinkaLockErrorLockJam() {

    }

    override fun onLinkaLockErrorAppNotInForeground() {

    }

    override fun onLinkaLockErrorLockStall() {

    }

    override fun onLinkaLockErrorPairTimeout() {

    }

    override fun onLinkaLockErrorLockBlock() {

    }
}