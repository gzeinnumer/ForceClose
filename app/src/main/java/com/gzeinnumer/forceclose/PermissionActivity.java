package com.gzeinnumer.forceclose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.gzeinnumer.eeda.helper.FGPermission;

import rebus.permissionutils.PermissionEnum;
import rebus.permissionutils.PermissionManager;

public class PermissionActivity extends AppCompatActivity {

    PermissionEnum[] permissions = new PermissionEnum[]{
            PermissionEnum.WRITE_EXTERNAL_STORAGE,
            PermissionEnum.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        FGPermission.checkPermissions(this, permissions);

        checkPermissions();
    }

    private void onSuccessCheckPermitions() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private void checkPermissions() {
        boolean isAllGranted = FGPermission.getPermissionResult(this, permissions);

        if (isAllGranted) {
            onSuccessCheckPermitions();
        } else {
            Toast.makeText(this, "Permission Required", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handleResult(this, requestCode, permissions, grantResults);

        checkPermissions();
    }
}