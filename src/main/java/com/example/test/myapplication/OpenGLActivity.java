package com.example.test.myapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by test on 11/12/2015.
 */
public class OpenGLActivity extends Activity {

    private GLSurfaceView mSurfaceView;
    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // test if support openGL ES 2.0
        if(hasGLES20()){
            mGLView = new GLSurfaceView(this);
            mGLView.setEGLContextClientVersion(2);
            mGLView.setPreserveEGLContextOnPause(true);
            //mGLView.setRenderer(new GLES20Renderer());
        }else{
            return;
        }

        setContentView(mGLView);
    }

    @Override
    protected void onResume(){
        super.onResume();

        //the activity must call GL surface view onResume()
        if(mSurfaceView != null) {
            mSurfaceView.onResume();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();

        //the activity must call GL surface view onPause()
        if(mSurfaceView != null) {
            mSurfaceView.onPause();
        }
    }


    // test if device support openGL ES 2.0
    private boolean hasGLES20(){
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return info.reqGlEsVersion >= 0x20000;
    }
}


