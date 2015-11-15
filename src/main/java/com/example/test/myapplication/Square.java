package com.example.test.myapplication;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by test on 11/13/2015.
 */
public class Square {

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

	private final FloatBuffer vertexBuffer;
	private final ShortBuffer drawListBuffer;
	private final int mProgram;
	private int mPositionHandle;
	private int mColorHandle;
	private int mMVPMatrixHandle;


    static final int COORDS_PER_VERTEX = 3;
    static float squareCoords[] = {
            -0.5f,  0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
             0.5f, -0.5f, 0.0f,
             0.5f,  0.5f, 0.0f
    };

    private final short drawOrder[] = {0, 1, 2, 0, 2, 3};
	private float color[] = {0.2f, 0.7f, 0.8f, 1.0f};
	private final int vertexStride = COORDS_PER_VERTEX * 4;
	
	
    public Square(){
        // number of coordinates * 4 bytes per float
        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length*4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);


        // number of drawOrder * 2 bytes per short
        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length*2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
		
		// prepare shaders and OpenGL program
		int vertexShader = GameRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		int fragmentShader = GameRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
		
		// create empty OpenGL program
		mProgram = GLES20.glCreateProgram();
		// add the vertex shader to program
		GLES20.glAttachShader(mProgram, vertexShader);
		// add the fragment shader to program
		GLES20.glAttachShader(mProgram, fragmentShader);
		// create OpenGL program executables
		GLES20.glLinkProgram(mProgram);
    }


	// encapsulate openGL ES instructin for drawing
	public void draw(float[] mvpMatrix){
		// add program to OpenGL environment
		GLES20.glUseProgram(mProgram);
		
		// get handle to vertex shader's vPosition member
		mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
		
		// enable a handle to the triangle vertices
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		
		// prepare the triangle coordinate date
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);


		// get handle to fragment shader's vColor member
		mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");


		// set color for drawing the triangle
		GLES20.glUniform4fv(mColorHandle, 1, color, 0);

		// get handle to shape's transformation matrix
		mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
		GameRenderer.checkGlError("glGetUniformLocation");

		// apply the projection and view transformation
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle,1,false,mvpMatrix,0);
		GameRenderer.checkGlError("glGetUniformMatrix4fv");
	
		// draw the square
		GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
	
		// diable vertex array
		GLES20.glDisableVertexAttribArray(mPositionHandle);

	}

}
