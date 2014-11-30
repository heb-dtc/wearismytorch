package com.heb.wear.torch;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.WatchViewStub;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private DismissOverlayView dismissOverlay;
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBrightness();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                dismissOverlay = (DismissOverlayView) stub.findViewById(R.id.dismiss_overlay);
                dismissOverlay.setIntroText("long press to quit");
                dismissOverlay.showIntroIfNecessary();
            }
        });

        detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent ev) {
                dismissOverlay.show();
            }
        });
    }

    private void setBrightness() {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.screenBrightness = 100.0f;
        this.getWindow().setAttributes(lp);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return detector.onTouchEvent(ev) || super.onTouchEvent(ev);
    }

}
