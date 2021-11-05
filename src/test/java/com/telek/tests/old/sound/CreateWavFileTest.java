package com.telek.tests.old.sound;

import com.telek.telekmath.TMath;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekutils.audio.TWavUtils;

import java.io.*;

public class CreateWavFileTest {


    public static void main(String[] args) throws IOException {

        byte[] sound = new byte[500000];

        for (int i = 0; i < sound.length; i++) {
            double value = Math.sin( ((double) i/ 2000d) );
            byte mappedValue = (byte) TMath.mapRange(TRange.MONE_TO_ONE, TRange.BYTE_RANGE, value);
            sound[i] = (byte) Math.abs(mappedValue);
        }

        String fileName = String.format("C:\\Users\\oguzh\\Desktop\\signal%s.wav", (int) (Math.random() * 1000));
        TWavUtils.byteArrayToWavFile(sound, new File(fileName));


    }






}
