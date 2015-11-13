package com.example.test.myapplication;

import android.opengl.GLES20;

/**
 * Created by test on 11/12/2015.
 */
public class Shader {

    private final static String vertcode =
            "attribute vec4 a_pos;"+
                "void main(){"+
                "gl_Position = a_pos;"+
                "}";
    private final static String fragcode =
            "precision mediump float;"+
                    "uniform vec4 u_color;"+
                    "void main(){"+
                    "gl_FragColor = u_color;"+
                    "}";

    private static int program;

    public static int positionhandle;
    public static int colorhandle;

    public static void makeprogram(){
        int vertexshader = loadshader(GLES20.GL_VERTEX_SHADER, vertcode);
        int fragmentshader = loadshader(GLES20.GL_FRAGMENT_SHADER, fragcode);

        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program,vertexshader);
        GLES20.glAttachShader(program,fragmentshader);
        GLES20.glLinkProgram(program);

        positionhandle = GLES20.glGetAttribLocation(program, "a_pos");
        colorhandle = GLES20.glGetUniformLocation(program, "u_color");

        GLES20.glUseProgram(program);

    }

    private static int loadshader(int type, String shadertext){
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shadertext);
        GLES20.glCompileShader(shader);

        return shader;
    }

}
