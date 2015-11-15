package com.example.test.myapplication.subTemp;

import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by test on 11/12/2015.
 */
public abstract class GLRenderer implements GLSurfaceView.Renderer {


    private boolean mFirstDraw;
    private boolean mSurfaceCreated;
    private int mWidth;
    private int mHeight;
    private long mLastTime;
    private int mFPS;


    public GLRenderer(){
        mFirstDraw = true;
        mSurfaceCreated = false;
        mWidth = -1;
        mHeight = -1;
        mLastTime = System.currentTimeMillis();
        mFPS = 0;
    }


    @Override
    public void onSurfaceCreated(GL10 notUsed, EGLConfig config){
        //if(Util.DEBUG){
            //Log.i(Util.LOG_TAG, "Surface created.");
       // }
        mSurfaceCreated = true;
        mWidth = -1;
        mHeight = -1;
    }

    public void OnSurfaceChanged(GL10 notUsed, int width, int height){
        if(!mSurfaceCreated && width == mWidth && height == mHeight){
           // if(Util.DEBUG){
             //   Log.i(Util.LOG_TAG, "Surface changed but already handled.");
           // }
            return;
        }
       /* if(Util.DEBUG){
            //android honeycomb has an option to keep the context.
            String msg = "Surface changed width:"+width+" heightP:"+height;
            if(mSurfaceCreated){
                msg += " context lost.";
            }else{
                msg += ".";
            }
            Log.i(Util.LOG_TAG, msg);
        }*/
        mWidth = width;
        mHeight = height;

        onCreate(mWidth, mHeight, mSurfaceCreated);
        mSurfaceCreated = false;
    }


    @Override
    public void onDrawFrame(GL10 notUsed){
        onDrawFrame(mFirstDraw);
       /* if(Util.DEBUG){
            mFPS++;
            long currentTime = System.currentTimeMillis();
            if(ccurrentTime - mLastTime >= 1000){
                mFPS = 0;
                mLastTime = currentTime;
            }
        }
        if(mFirstDraw){
            mFirstDraw = false;
        }
*/
    }

    public abstract void onCreate(int width, int height, boolean contextLost);

    public abstract void onDrawFrame(boolean firstDraw);


}
