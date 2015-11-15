package com.example.test.myapplication;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created by test on 11/11/2015.
 */
public class GameActivity extends Activity {

    private GLSurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mSurfaceView = new GameSurfaceView(this);
        setContentView(mSurfaceView);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSurfaceView.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSurfaceView.onResume();
    }
}
