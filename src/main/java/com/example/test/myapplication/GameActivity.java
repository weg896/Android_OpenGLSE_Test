package com.example.test.myapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by test on 11/11/2015.
 */
public class GameActivity extends Activity {

    private GLSurfaceView mSurfaceView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mSurfaceView = new GLSurfaceView(this);
        mSurfaceView.setEGLContextClientVersion(2);
        mSurfaceView.setRenderer(new MainRenderer());
        mSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(mSurfaceView);
    }
/*
    @Override
    protected void onResume(){
        super.onResume();
        //mainSurfaceView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        //mainSurfaceView.onPause();
    }*/
}
