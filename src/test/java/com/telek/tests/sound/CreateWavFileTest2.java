package com.telek.tests.sound;

import com.telek.telekmath.core.functions.trig.TSin;
import com.telek.telekutils.audio.TWavUtils;

import java.io.File;


public class CreateWavFileTest2 {

    public static void main(String[] args) {

        TSin sin = new TSin(500);
        byte[] sound = TWavUtils.sampleFunction(sin, 500000);

        String fileName = String.format("C:\\Users\\oguzh\\Desktop\\signal%s.wav", (int) (Math.random() * 1000));
        TWavUtils.byteArrayToWavFile(sound, new File(fileName));


    }
}
