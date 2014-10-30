package com.google.meetupdemo;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ScreenCaptureFragment extends Fragment implements View.OnClickListener {

    private MediaProjectionManager mManager;
    private MediaProjection mProjection;
    private VirtualDisplay mDisplay;
    private SurfaceView mSurfaceView;
    private final static int REQUEST_CODE = 1;

    private final static String DISPLAY_NAME = "virtual_display";

    public ScreenCaptureFragment() {}

    @Override
    public void onResume() {
        super.onResume();
        mManager = (MediaProjectionManager) getActivity().getSystemService(Context.MEDIA_PROJECTION_SERVICE);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mProjection != null)
            mProjection.stop();
        if (mDisplay != null) {
            mDisplay.release();
            mSurfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
            mSurfaceView.getHolder().setFormat(PixelFormat.OPAQUE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_capture, container, false);

        view.findViewById(R.id.captureScreenButton).setOnClickListener(this);
        mSurfaceView = (SurfaceView) view.findViewById(R.id.surfaceView);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.captureScreenButton:
                captureScreen();
                break;
        }
    }

    private void captureScreen() {
        Intent i = mManager.createScreenCaptureIntent();
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

       if (requestCode == REQUEST_CODE && resultCode != 0) {
            mProjection = mManager.getMediaProjection(resultCode, data);
            mDisplay = mProjection.createVirtualDisplay(
                   DISPLAY_NAME,
                   mSurfaceView.getWidth(), mSurfaceView.getHeight(),
                   90, DisplayManager.VIRTUAL_DISPLAY_FLAG_PRESENTATION,
                   mSurfaceView.getHolder().getSurface(),
                   null, null);
       }
    }

}
