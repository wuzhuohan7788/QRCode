package com.vvzh.qrcode.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

import com.vvzh.qrcode.R;
import com.vvzh.qrcode.db.DatabaseManager;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener, QRCodeView.Delegate {
    ZXingView zx_scanView;

    TextView tv_StartPreview;
    TextView tv_StopPreview;
    TextView tv_StartSpot;
    TextView tv_StopSpot;
    TextView tv_SpotAndShowRect;
    TextView tv_StopAndHiddenRect;
    TextView tv_onFlashLight;
    TextView tv_offFlashLight;
    TextView tv_ShowRect;
    TextView tv_HiddenRect;
    TextView tv_RectArea;
    TextView tv_FullArea;
    TextView tv_Only_OneDimension;
    TextView tv_Only_TwoDimension;
    TextView tv_Only_QRCode;
    TextView tv_scan_all;
    TextView tv_scanByImage;

    DatabaseManager dbManager;

    private static final String TAG = "ScanActivity";
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        getSupportActionBar().hide();

        dbManager = DatabaseManager.getInstance(this);

        init();
    }

    private void init() {
        //开启时创建并启动ZXingView
        zx_scanView = findViewById(R.id.scan_zxingview);
        zx_scanView.setDelegate(this);

        tv_StartPreview = findViewById(R.id.tv_scan_startPreview);
        tv_StopPreview = findViewById(R.id.tv_scan_stopPreview);
        tv_StartSpot = findViewById(R.id.tv_scan_startSpot);
        tv_StopSpot = findViewById(R.id.tv_scan_stopSpot);
        tv_SpotAndShowRect = findViewById(R.id.tv_scan_SpotAndShowRect);
        tv_StopAndHiddenRect = findViewById(R.id.tv_scan_StopAndHiddenRect);
        tv_onFlashLight = findViewById(R.id.tv_scan_on_flashLight);
        tv_offFlashLight = findViewById(R.id.tv_scan_off_flashLight);
        tv_ShowRect = findViewById(R.id.tv_scan_showRect);
        tv_HiddenRect = findViewById(R.id.tv_scan_hiddenRect);
        tv_RectArea = findViewById(R.id.tv_scan_rect_area);
        tv_FullArea = findViewById(R.id.tv_scan_full_screenArea);
        tv_Only_OneDimension = findViewById(R.id.tv_scan_only_oneDimension);
        tv_Only_TwoDimension = findViewById(R.id.tv_scan_only_twoDimension);
        tv_Only_QRCode = findViewById(R.id.tv_scan_only_QRcode);
        tv_scan_all = findViewById(R.id.tv_scan_all);
        tv_scanByImage = findViewById(R.id.tv_scan_getScanByImage);

        tv_StartPreview.setOnClickListener(this);
        tv_StopPreview.setOnClickListener(this);
        tv_StartSpot.setOnClickListener(this);
        tv_StopSpot.setOnClickListener(this);
        tv_SpotAndShowRect.setOnClickListener(this);
        tv_StopAndHiddenRect.setOnClickListener(this);
        tv_onFlashLight.setOnClickListener(this);
        tv_offFlashLight.setOnClickListener(this);
        tv_ShowRect.setOnClickListener(this);
        tv_HiddenRect.setOnClickListener(this);
        tv_RectArea.setOnClickListener(this);
        tv_FullArea.setOnClickListener(this);
        tv_Only_OneDimension.setOnClickListener(this);
        tv_Only_TwoDimension.setOnClickListener(this);
        tv_Only_QRCode.setOnClickListener(this);
        tv_scan_all.setOnClickListener(this);
        tv_scanByImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_scan_startPreview:
                zx_scanView.startCamera(); //打开摄像头开始预览，但并不识别
                break;
            case R.id.tv_scan_stopPreview:
                zx_scanView.stopCamera(); //关闭摄像头停止预览
                break;
            case R.id.tv_scan_startSpot:
                zx_scanView.startSpot(); //开始识别
                break;
            case R.id.tv_scan_stopSpot:
                zx_scanView.stopSpot(); //关闭识别
                break;
            case R.id.tv_scan_SpotAndShowRect:
                zx_scanView.startSpotAndShowRect(); //显示扫描框，并开启识别
                break;
            case R.id.tv_scan_StopAndHiddenRect:
                zx_scanView.stopSpotAndHiddenRect(); //隐藏扫描框，并关闭识别
                break;
            case R.id.tv_scan_on_flashLight:
                zx_scanView.openFlashlight(); //打开闪光灯
                break;
            case R.id.tv_scan_off_flashLight:
                zx_scanView.closeFlashlight(); //关闭闪光灯
                break;
            case R.id.tv_scan_showRect:
                zx_scanView.showScanRect(); //显示扫描框
                break;
            case R.id.tv_scan_hiddenRect:
                zx_scanView.hiddenScanRect(); //隐藏扫描框
                break;
            case R.id.tv_scan_rect_area:
                zx_scanView.getScanBoxView().setOnlyDecodeScanBoxArea(true); //只识别框内二维码
                break;
            case R.id.tv_scan_full_screenArea:
                zx_scanView.getScanBoxView().setOnlyDecodeScanBoxArea(false); //识别整个屏幕的码
                break;
            //切换扫描框样式并设置只识别一维条码
            case R.id.tv_scan_only_oneDimension:
                zx_scanView.changeToScanBarcodeStyle();
                zx_scanView.setType(BarcodeType.ONE_DIMENSION, null);
                zx_scanView.startSpotAndShowRect();
                break;
            case R.id.tv_scan_only_twoDimension:
                zx_scanView.changeToScanQRCodeStyle();
                zx_scanView.setType(BarcodeType.TWO_DIMENSION, null);
                zx_scanView.startSpotAndShowRect();
                break;
            case R.id.tv_scan_only_QRcode:
                zx_scanView.changeToScanQRCodeStyle();
                zx_scanView.setType(BarcodeType.ONLY_QR_CODE, null);
                zx_scanView.startSpotAndShowRect();
                break;
            case R.id.tv_scan_all:
                zx_scanView.hiddenScanRect();
                zx_scanView.setType(BarcodeType.ALL, null);
                zx_scanView.startSpotAndShowRect();
                break;
            case R.id.tv_scan_getScanByImage:
                Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                        .cameraFileDir(null)
                        .maxChooseCount(1)
                        .selectedPhotos(null)
                        .pauseOnScroll(false)
                        .build();
                startActivityForResult(photoPickerIntent, REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        zx_scanView.startSpotAndShowRect();

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
                final String picturePath = BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);

                zx_scanView.decodeQRCode(picturePath);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        zx_scanView.startCamera(); //开启时打开摄像头
        zx_scanView.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        zx_scanView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zx_scanView.onDestroy(); //销毁控件
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "onScanQRCodeSuccess: " + result);
        Toast.makeText(this, "扫描结果：" + result, Toast.LENGTH_SHORT).show();
        vibrate();
//        zx_scanView.startSpot();

        dbManager.insertScanResult(result);
    }

    /**
     * 判断环境明暗，通过isDark的值实现文字界面交互
     *
     * @param isDark
     */
    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        String tipText = zx_scanView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯!";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                zx_scanView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                zx_scanView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "onScanQRCodeOpenCameraError: 打开相机出错！");
        Toast.makeText(this, "打开相机出错！", Toast.LENGTH_LONG).show();
    }
}
