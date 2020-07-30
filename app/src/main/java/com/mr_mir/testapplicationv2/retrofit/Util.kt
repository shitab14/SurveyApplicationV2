package com.mr_mir.testapplicationv2.retrofit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

/**
 * Created by Shitab Mir on 28,July,2020
 */
class Util {
    companion object {

        fun showToast(context: Context?, msg: String?) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun goToNextActivity(
            context: Context,
            //bundle: Bundle?,
            targetActivity: Class<out Activity?>?
        ) {
            val intent = Intent(context, targetActivity)
            //intent.putExtras(bundle!!)
            context.startActivity(intent)
        }


        fun hideKeyboard(activity: Activity) {
            val imm =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }


        fun isNetworkConnected(context: Context): Boolean {
            val conMgr = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            /** to get info of WIFI N/W :  */
            val wifi = conMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)

            /** to get info of mobile N/W :  */
            val mobile = conMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (wifi.isAvailable && wifi.isConnected) {
                Log.e(
                    "Is Net work? 1", "isNetWork:in 'isNetWork_if' is N/W Connected:"
                            + NetworkInfo.State.CONNECTED
                )
                return true
            } else if (mobile != null && mobile.isAvailable && mobile.isConnected) {
                Log.e(
                    "Is Net work? 2", "isNetWork:in 'isNetWork_if' is N/W Connected:"
                            + NetworkInfo.State.CONNECTED
                )
                return true
            } else if (conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo.isAvailable
                && conMgr.activeNetworkInfo.isConnected
            ) {
                Log.e(
                    "Is Net work? 3", "isNetWork:in 'isNetWork_if' is N/W Connected:"
                            + NetworkInfo.State.CONNECTED
                )
                return true
            }
            return false
        }


    }
}