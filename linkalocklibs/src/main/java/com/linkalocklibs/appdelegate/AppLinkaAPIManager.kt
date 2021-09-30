package com.linkalocklibs.appdelegate

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.linka.linkaapikit.module.api.LinkaAPIServiceConfig
import com.linka.linkaapikit.module.api.LinkaMerchantAPIProtocol

class AppLinkaAPIManager(var context: Context) : LinkaMerchantAPIProtocol {
    override fun getLastKnownLocation(): Location {
        return curLocation!!
    }

    override fun getSecureAndroidId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    override fun LinkaMerchantAPI_getAPIKey(): String {
        return LinkaMerchantAPIKey
    }

    override fun LinkaMerchantAPI_getSecretKey(): String {
        return LinkaMerchantSecretKey
    }

    override fun LinkaMerchantAPI_getIsButtonUsed(): Boolean {
        return isButtonUsedForLocking
    }

    val curLocation: Location?
        get() {
            if (ActivityCompat.checkSelfPermission(
                    LinkaAPIServiceConfig.getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    LinkaAPIServiceConfig.getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return null
            }
            val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            var net_loc: Location? = null
            var gps_loc: Location? = null
            var finalLoc: Location? = null
            if (gps_enabled) gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (network_enabled) net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (gps_loc != null && net_loc != null) {
                finalLoc = if (gps_loc.accuracy >= net_loc.accuracy) gps_loc else net_loc
            } else {
                if (gps_loc != null) {
                    finalLoc = gps_loc
                } else if (net_loc != null) {
                    finalLoc = net_loc
                }
            }
            return finalLoc
        }

    companion object {
        const val LinkaMerchantAPIKey = "562e0453-f660-479c-9bd8-69365fd31215"
        const val LinkaMerchantSecretKey = "4c981ecd-845d-473d-89d9-af0cc51a954e"

        // IMPORTANT
        // Keep this value as FALSE for "Simple Mode"
        const val isButtonUsedForLocking = false
    }
}