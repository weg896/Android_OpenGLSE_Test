package com.example.test.myapplication;

import android.opengl.GLES20;

import com.example.test.myapplication.GLRenderer;

import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;

public class GLES20Renderer{

    public void onCreate(int width, int height, boolean contextLost){
        GLES20.glClearColor(0f,0f,0f,1f);
    }

    public void onDrawFrame(boolean firstDraw){
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}