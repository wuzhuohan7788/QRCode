package com.vvzh.qrcode.view;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vvzh.qrcode.R;
import com.vvzh.qrcode.db.DatabaseManager;

import java.util.List;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {
    ImageView img_scan, img_diary;
    private DatabaseManager dbManager;

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BGAQRCodeUtil.setDebug(true);

        dbManager = DatabaseManager.getInstance(this);

        init();
    }

    private void init() {
        img_scan = findViewById(R.id.main_img_scan);
        img_diary = findViewById(R.id.main_img_diary);

        img_scan.setOnClickListener(this);
        img_diary.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_img_scan:
                startActivity(new Intent(this, ScanActivity.class));
                break;
            case R.id.main_img_diary:
                startActivity(new Intent(this, DiaryActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermission();
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermission() {
        String[] permi = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, permi)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开摄像头和闪光灯权限", REQUEST_CODE_QRCODE_PERMISSIONS, permi);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
