package cn.bingoogolapple.qrcode.core;

import android.graphics.PointF;

/**
 *
 * 描述:扫描返回结果
 */
public class ScanResult {
    String result;
    PointF[] resultPoints;

    public ScanResult(String result) {
        this.result = result;
    }

    public ScanResult(String result, PointF[] resultPoints) {
        this.result = result;
        this.resultPoints = resultPoints;
    }
}
