package com.example.lockscreennew

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private var mDPM: DevicePolicyManager? = null
    private var mDeviceAdmin: ComponentName? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        mDPM = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        mDeviceAdmin = ComponentName(this, MyReceiver::class.java)
        if (mDPM?.isAdminActive(mDeviceAdmin!!) == true) {
            mDPM?.lockNow()
            finish()
        } else {
            activeManage()
            finish()
        }
    }

    /**
     * 激活设备管理器
     */
    private fun activeManage() {
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdmin)
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "激活后才能使用锁屏功能")
        startActivity(intent)
    }
}