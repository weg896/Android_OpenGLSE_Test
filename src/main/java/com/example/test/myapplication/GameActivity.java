package com.example.test.myapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;

/**
 * Created by test on 11/11/2015.
 */
public class GameActivity extends Activity {

    private GLSurfaceView mGLSurfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);

        // checek system support openGL ES 2.0
        //final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        //final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        //final boolean supportsEs2 = configurationInfo.reqGlEsVersion > 0x20000;

        //if(supportsEs2){
            mGLSurfaceView = new GLSurfaceView(this);
           // mGLSurfaceView.setEGLContextClientVersion(2);
            mGLSurfaceView.setRenderer(new LessonOneRenderer());
        /*}else{
            return;
        }*/

        setContentView(mGLSurfaceView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mGLSurfaceView.onPause();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


}
