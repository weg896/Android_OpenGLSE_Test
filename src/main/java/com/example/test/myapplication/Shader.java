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
        int vertexshader = loadshader(GLES30.GL_VERTEX_SHADER, vertcode)
    }

}
