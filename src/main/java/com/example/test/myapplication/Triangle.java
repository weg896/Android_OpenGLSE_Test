package com.example.test.myapplication;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by test on 11/13/2015.
 */
public class Triangle {

    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;"+
			"attribute vec4 vPosition;"+
                    "void main(){"+
                    "  gl_Position = uMVPMatrix * vPosition;"+
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;"+
                    "uniform vec4 vColor;"+
                    "void main(){"+
                    "  gl_FragColor = vColor;"+
                    "}";

    private FloatBuffer vertexBuffer;
    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;



    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX =3;
    static float triangleCoords[] = {
		// counterclockwise order;
             0.0f,  0.6220f, 0.0f,
            -0.5f, -0.3110f, 0.0f,
             0.5f, -0.3110f, 0.0f
    };

    // color red, green, blue, alpha
    float color[] = {0.636f,0.769f,0.226f,1.0f};

    public Triangle(){
        // number of coordinate values * 4 bytes per float
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length*4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        int vertexShader = GameRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = GameRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        // create empty OpenGL ES program
        mProgram = GLES20.glCreateProgram();
        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);
        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);
        // create OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }




    private final int vertexCount = triangleCoords.length/COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    public void draw(float[] mvpMatrix){
        // add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);
        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        // prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        // set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        // get handle to shape's transformation matrix
		mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
		GameRenderer.checkGlError("glGetUniformLocation");
		// apply the projection and view transformation
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
		GameRenderer.checkGlError("glUniformMatrix4fv");
		// draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
