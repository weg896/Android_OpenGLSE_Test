package com.example.test.myapplication;

import android.opengl.GLES30;

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
        int vertexshader = loadshader(GLES30.GL_VERTEX_SHADER, vertcode);
        int fragmentshader = loadshader(GLES30.GL_FRAGMENT_SHADER, fragcode);

        program = GLES30.glCreateProgram();
        GLES30.glAttachShader(program,vertexshader);
        GLES30.glAttachShader(program,fragmentshader);
        GLES30.glLinkProgram(program);

        positionhandle = GLES30.glGetAttribLocation(program, "a_pos");
        colorhandle = GLES30.glGetUniformLocation(program, "u_color");

        GLES30.glUseProgram(program);

    }

    private static int loadshader(int type, String shadertext){
        int shader = GLES30.glCreateShader(type);

        GLES30.glShaderSource(shader, shadertext);
        GLES30.glCompileShader(shader);

        return shader;
    }

}
