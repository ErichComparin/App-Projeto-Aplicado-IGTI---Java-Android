package app.ec.com.apppa.Helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {
    public PermissionManager(){}

    private static final String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
    private static final int REQUEST_CODE = 101;

    public boolean userHasPermission(Context context){
        int permissionCheck = ContextCompat.checkSelfPermission(context, PERMISSIONS[0]);
        int permissionCheck2 = ContextCompat.checkSelfPermission(context, PERMISSIONS[1]);
        int permissionCheck3 = ContextCompat.checkSelfPermission(context, PERMISSIONS[2]);
        return permissionCheck == PackageManager.PERMISSION_GRANTED &&
                permissionCheck2 == PackageManager.PERMISSION_GRANTED &&
                permissionCheck3 == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(Activity activity){
        ActivityCompat.requestPermissions(activity, PERMISSIONS, REQUEST_CODE);
    }
}
