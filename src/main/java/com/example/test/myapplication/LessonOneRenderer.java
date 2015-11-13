package com.example.test.myapplication;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class LessonOneRenderer implements GLSurfaceView.Renderer {


    private FloatBuffer vertbuffer;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Shader.makeprogram();

        GLES20.glEnableVertexAttribArray(Shader.positionhandle);

        float[] verts =
                {
                        0.0f, 1.0f, 0.0f,
                        0.0f, 0.0f, 1.0f,
                        1.0f, 0.0f, 0.0f
                };
        vertbuffer = makefloatbuffer(verts);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        GLES20.glUniform4f(Shader.colorhandle, 1.0f, 0.0f, 0.0f, 1.0f);

        GLES20.glVertexAttribPointer(Shader.positionhandle, 3, GLES20.GL_FLOAT, false, 0, vertbuffer);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }
    public FloatBuffer makefloatbuffer(float[] array){
        FloatBuffer floatbuff = ByteBuffer.allocateDirect(array.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();

        floatbuff.put(array).position();

        return floatbuff;
    }

}
