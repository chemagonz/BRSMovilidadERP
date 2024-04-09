package com.advantys.brsmovilidaderp.Utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import javax.inject.Inject

class PermisosUtils @Inject constructor(){

    companion object {
        const val REQUEST_STORAGE_PERMISSION = 1
        fun solicitarPermisosAlmacenamiento(activity: Activity) {
            val permissionsToRequest = mutableListOf<String>()

            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (permissionsToRequest.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    activity,
                    permissionsToRequest.toTypedArray(),
                    REQUEST_STORAGE_PERMISSION
                )
            }
        }

        fun verificarPermisosAlmacenamiento(activity: Activity): Boolean {
            return ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }

        fun irConfiguracionPermisos(activity: Activity) {
            val intent = Intent()
            intent.action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivity(intent)
        }
    }
}