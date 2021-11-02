package com.telek.telekutils.audio;


import javax.sound.sampled.*;
import java.io.*;
import java.nio.*;



/**  Utility class for WAV files.  */
public class TWavUtils {


//	public static byte[] sampleFunction(TSin function, int sampleAmount){
//		byte[] arr = new byte[sampleAmount];
//		final TRange range = function.getRange();
//		final double startX = range.left;
//		final double rangeSize = range.size();
//		final double delta = rangeSize / (sampleAmount-1);
//		for (int i = 0; i < sampleAmount; i++) {
//			double curX = startX + i * delta;
//			double value = function.value(curX);
//			byte mappedValue = (byte) TMath.mapRange(TRange.MONE_TO_ONE, TRange.BYTE_RANGE, value);
//			arr[i] = mappedValue;
//		}
//		return arr;
//	}



	/*  ARRAY <-> WAV FILE  */

	/**
	 * @param data byte array to convert to wav file
	 * @param outputFile output directory of the wav file
	 */
	public static void byteArrayToWavFile(byte[] data, File outputFile) {
		try {

			AudioInputStream audioStream;

			try {
				try {
					ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
					audioStream = AudioSystem.getAudioInputStream(byteStream);
				}
				catch (UnsupportedAudioFileException e) {

					ByteBuffer bufferWithHeader = ByteBuffer.allocate(data.length + 44);
					bufferWithHeader.order(ByteOrder.LITTLE_ENDIAN);
					bufferWithHeader.put("RIFF".getBytes());
					bufferWithHeader.putInt(data.length + 36);
					bufferWithHeader.put("WAVE".getBytes());
					bufferWithHeader.put("fmt ".getBytes());
					bufferWithHeader.putInt(16);
					bufferWithHeader.putShort((short) 1);
					bufferWithHeader.putShort((short) 1);
					bufferWithHeader.putInt(16000);
					bufferWithHeader.putInt(32000);
					bufferWithHeader.putShort((short) 2);
					bufferWithHeader.putShort((short) 16);
					bufferWithHeader.put("data".getBytes());
					bufferWithHeader.putInt(data.length);
					bufferWithHeader.put(data);
					data = bufferWithHeader.array();

					ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
					audioStream = AudioSystem.getAudioInputStream(byteStream);
				}
			}
			catch (IOException | UnsupportedAudioFileException e) {
				throw new RuntimeException("cannot convert bytes to audio stream: " + e);
			}


			if (outputFile.getName().endsWith("wav")) {
				AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, new FileOutputStream(outputFile));
			}
			else {
				throw new RuntimeException("Unsupported encoding " + outputFile);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("could not generate file: " + e);
		}
	}



	/**
	 * All the values in the byte array will be in range [-128, 127]
	 * @param wavFile any wav file
	 * @return the wav file as a byte array
	 */
	public static byte[] wavFileToByteArray(File wavFile)  {
		try{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(wavFile));

			int read;
			byte[] buff = new byte[1024];
			while ( (read = in.read(buff) ) > 0) {
				out.write(buff, 0, read);
			}
			out.flush();
			return out.toByteArray();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("METHOD IS RETURNING NULL");
		return null;
	}


	
	
}


