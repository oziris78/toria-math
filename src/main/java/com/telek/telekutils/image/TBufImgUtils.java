package com.telek.telekutils.image;


import com.telek.telekmath.core.functions.general.TRange;
import com.telek.telekmath.exceptions.NotInRangeException;
import static com.telek.telekmath.exceptions.TelekMathException.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public final class TBufImgUtils {



	/*  GENERAL FUNCTIONS  */

	public static BufferedImage getCopyOf(BufferedImage imageToCopy){
		BufferedImage newImg = new BufferedImage(imageToCopy.getWidth(), imageToCopy.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < newImg.getWidth(); i++)
			for(int j = 0; j < newImg.getHeight(); j++){
				Color c = new Color(imageToCopy.getRGB(i,j));
				newImg.setRGB(i, j, c.getRGB() );
			}
		return newImg;
	}



	public static BufferedImage getEmptyImage(int width, int height, int type){
		return new BufferedImage(width, height, type);
	}


	public static BufferedImage getEmptyImage(int width, int height){
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}



	/**
	 * Exports the specified image to the specified location
	 * @param imageToExport image that will be exported
	 * @param exportLocation File object specifying the export location
	 * @param extension image extension, use "PNG" or "JPG" or else you'll get an error
	 */
	public static void exportImage(BufferedImage imageToExport, File exportLocation, String extension){
		checkForExtension(extension);
		try{ ImageIO.write(imageToExport, extension, exportLocation); }
		catch(Exception e){ e.printStackTrace(); }
	}



	/**
	 * Exports the specified image to the specified location
	 * @param imageToExport image that will be exported
	 * @param exportPathWithExtension a string specifying the export location
	 * @param extension image extension, use "PNG" or "JPG" or else you'll get an error
	 */
	public static void exportImage(BufferedImage imageToExport, String exportPathWithExtension, String extension){
		exportImage(imageToExport, new File(exportPathWithExtension), extension);
	}




	/*  IMAGE COMBINING  */


	/**
	 * Returns an image that is made of leftImage and rightImage, puts them side by side.
	 * @param leftImage image to put to the left side
	 * @param rightImage image to put to the rigth side
	 * @return an image containing leftImage in its left side and rightImage in its right side
	 */
	public static BufferedImage getSideBySideImage(BufferedImage leftImage, BufferedImage rightImage){
		BufferedImage img1 = getCopyOf(leftImage);
		BufferedImage img2 = getCopyOf(rightImage);
		BufferedImage newImg = new BufferedImage(img1.getWidth() + img2.getWidth(),
				Math.max(img1.getHeight(), img2.getHeight()), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newImg.createGraphics();
		g2d.drawImage(img1, 0, 0, null);
		g2d.drawImage(img2, img1.getWidth(), 0, null);
		g2d.dispose();
		return newImg;
	}



	/**
	 * Puts image2 onto image1 and returns the result
	 * @param image1 any image
	 * @param image2 any image
	 * @return the image that is created when image2 is put onto image1 (in (0,0))
	 */
	public static BufferedImage getCombinedImage(BufferedImage image1, BufferedImage image2){
		BufferedImage img1 = getCopyOf(image1);
		BufferedImage img2 = getCopyOf(image2);
		BufferedImage newImg = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newImg.createGraphics();
		g2d.drawImage(img1, 0, 0, null);
		g2d.drawImage(img2, 0, 0, null);
		g2d.dispose();
		return newImg;
	}



	/*  COLOR MANIPULATION  */


	/**
 	 * @param imageToBlur any image
	 * @param neighborCount how many neighboring pixels will be accounted for when the effect is being applied, higher values means more blur
	 * @param similarity defines how similar neighboring pixels will look a like, this value should be in range [0,1]
	 * @return a blurred image
	 */
	public static BufferedImage getBlurredImage(BufferedImage imageToBlur, int neighborCount, double similarity){
		if(similarity > 1 || similarity < 0) throw new NotInRangeException(TRange.ZERO_TO_ONE, similarity);
		BufferedImage blurredImage = new BufferedImage( imageToBlur.getWidth(), imageToBlur.getHeight(), imageToBlur.getType());
		Color c1, c2;
		for(int i = 0; i < blurredImage.getWidth(); i++){
			for(int j = 0; j < blurredImage.getHeight(); j++){
				c1 = getAverageColorUsingNeighboringCells(imageToBlur, neighborCount, i, j);
				c2 = new Color(imageToBlur.getRGB(i,j));
				int r = c2.getRed() - ((int) ( (c2.getRed() - c1.getRed()) * similarity ));
				int g = c2.getGreen() - ((int) ( (c2.getGreen() - c1.getGreen()) * similarity ));
				int b = c2.getBlue() - ((int) ( (c2.getBlue() - c1.getBlue()) * similarity ));
				blurredImage.setRGB(i, j, new Color(r,g,b).getRGB());
			}
		}
		return blurredImage;
	}


	/**
	 * Does {@link #getBlurredImage(BufferedImage, int, double)}  with similarity = 1d
	 * @param imageToBlur any image
	 * @param neighborCount how many neighboring pixels will be accounted for when the effect is being applied, higher values means more blur
	 * @return a blurred image
	 */
	public static BufferedImage getBlurredImage(BufferedImage imageToBlur, int neighborCount){
		return getBlurredImage(imageToBlur, neighborCount, 1d);
	}


	public static BufferedImage getGreenfulImage(BufferedImage imageToChange){ return changeGreen(imageToChange, 255); }

	public static BufferedImage getGreenlessImage(BufferedImage imageToChange){ return changeGreen(imageToChange, 0); }

	public static BufferedImage getRedfulImage(BufferedImage imageToChange){ return changeRed(imageToChange, 255); }

	public static BufferedImage getRedlessImage(BufferedImage imageToChange){ return changeRed(imageToChange, 0); }

	public static BufferedImage getBluefulImage(BufferedImage imageToChange){ return changeBlue(imageToChange, 255); }

	public static BufferedImage getBluelessImage(BufferedImage imageToChange){ return changeBlue(imageToChange, 0); }



	/**
	 * Does {@link #getGrayscaledImage(BufferedImage, int, boolean, Color)} with Color set to either Color.BLACK or Color.WHITE
	 * depending one the boolean setColorToDarkAreas.
	 * @param imageToChange any image
	 * @param harshness an integer in range [0,255] specifying the threshold for secondaryColor
	 * @param setColorToDarkAreas specifies if dark areas or bright areas are going to be painted by secondaryColor according to harshness
	 * @return a grayscaled image with dark/bright areas painted with secondaryColor
	 */
	public static BufferedImage getGrayscaledImage(BufferedImage imageToChange, int harshness, boolean setColorToDarkAreas){
		if(setColorToDarkAreas) return getGrayscaledImage(imageToChange, harshness, setColorToDarkAreas, Color.BLACK);
		else return getGrayscaledImage(imageToChange, harshness, setColorToDarkAreas, Color.WHITE);
	}



	/**
	 * This method is extremely similar to {@link #getGrayscaledImage(BufferedImage, int, boolean, Color)} and
	 * {@link #getGrayscaledImage(BufferedImage, int, boolean)}. The only difference is that this is more of a default
	 * (also a different) method for creating grayscaled images because this method doesn't care about harshness and just
	 * grayscales the images you give it.
	 * @param imageToChange any image
	 * @return a grayscaled image which is a copy of imageToChange
	 */
	public static BufferedImage getGrayscaledImage(BufferedImage imageToChange){
		BufferedImage img = getCopyOf(imageToChange);
		Color currentColor;
		Color newColor;
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				currentColor = new Color( img.getRGB(i,j) );

				int red = (int) (currentColor.getRed() * 0.299d);
				int green = (int) (currentColor.getGreen() * 0.587d);
				int blue = (int) (currentColor.getBlue() * 0.114d);
				int sum = red + green + blue;

				newColor = new Color(sum, sum, sum);

				img.setRGB(i, j, newColor.getRGB());
			}
		}
		return img;
	}


	/**
	 * Paints the dark or bright areas with secondaryColor and returns a copy of that image
	 * @param imageToChange any image
	 * @param harshness an integer in range [0,255] specifying the threshold for secondaryColor
	 * @param setColorToDarkAreas specifies if dark areas or bright areas are going to be painted by secondaryColor according to harshness
	 * @param secondaryColor any color to paint the dark/bright areas
	 * @return a grayscaled image with dark/bright areas painted with secondaryColor
	 */
	public static BufferedImage getGrayscaledImage(BufferedImage imageToChange, int harshness, boolean setColorToDarkAreas, Color secondaryColor){
		if(harshness >= 256 || harshness < 0) throw new NotInRangeException(new TRange(0,255), harshness);
		BufferedImage img = getCopyOf(imageToChange);
		Color currentColor;
		Color newColor;
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				currentColor = new Color( img.getRGB(i,j) );

				int red = (int) (currentColor.getRed() * 0.299d);
				int green = (int) (currentColor.getGreen() * 0.587d);
				int blue = (int) (currentColor.getBlue() * 0.114d);
				int sum = red + green + blue;

				newColor = new Color(sum, sum, sum);

				if(setColorToDarkAreas){
					if(sum > harshness) img.setRGB(i, j, newColor.getRGB());
					else img.setRGB(i, j, secondaryColor.getRGB());
				}
				else{
					if(sum > harshness) img.setRGB(i, j, secondaryColor.getRGB());
					else img.setRGB(i, j, newColor.getRGB());
				}

			}
		}
		return img;
	}


	public static BufferedImage getInvertedImage(BufferedImage imageToInvert){
		BufferedImage img = new BufferedImage(imageToInvert.getWidth(), imageToInvert.getHeight(), imageToInvert.getType());
		Color curColor, invColor;
		for(int i = 0; i < imageToInvert.getWidth(); i++){
			for(int j = 0; j < imageToInvert.getHeight(); j++){
				curColor = new Color( imageToInvert.getRGB(i,j) );
				invColor = new Color( 255 - curColor.getRed(), 255 - curColor.getGreen(), 255 - curColor.getBlue() );
				img.setRGB(i,j, invColor.getRGB());
			}
		}
		return img;
	}



	/*  HELPER FUNCTIONS  */

	private static void checkForExtension(String extension) {
		if( ! (extension.toLowerCase().startsWith("png") || extension.toLowerCase().startsWith("jpg")) )
			throw new InvalidExtensionException();
	}

	private static Color getAverageColorUsingNeighboringCells(BufferedImage imageToBlur, int neighborCount, int x, int y){
		Color c1;
		int termCount = 0;
		int sumR = 0;
		int sumG = 0;
		int sumB = 0;
		for(int i = x - neighborCount; i < x + neighborCount; i++){
			for(int j = y - neighborCount; j < y + neighborCount; j++){
				if(i >= 0 && j >= 0 && i < imageToBlur.getWidth() && j < imageToBlur.getHeight()){
					c1 = new Color( imageToBlur.getRGB(i,j));
					sumR += c1.getRed();
					sumG += c1.getGreen();
					sumB += c1.getBlue();
					termCount++;
				}
			}
		}
		return new Color(sumR / termCount, sumG / termCount, sumB / termCount);
	}

	private static BufferedImage changeGreen(BufferedImage imageToChange, int greenChange){
		BufferedImage img = getCopyOf(imageToChange);
		Color currentColor;
		Color newColor;
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				currentColor = new Color( img.getRGB(i,j) );
				newColor = new Color(currentColor.getRed(), greenChange, currentColor.getBlue());
				img.setRGB(i, j, newColor.getRGB());
			}
		}
		return img;
	}

	private static BufferedImage changeRed(BufferedImage imageToChange, int redChange){
		BufferedImage img = getCopyOf(imageToChange);
		Color currentColor;
		Color newColor;
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				currentColor = new Color( img.getRGB(i,j) );
				newColor = new Color(redChange, currentColor.getGreen(), currentColor.getBlue());
				img.setRGB(i, j, newColor.getRGB());
			}
		}
		return img;
	}

	private static BufferedImage changeBlue(BufferedImage imageToChange, int blueChange){
		BufferedImage img = getCopyOf(imageToChange);
		Color currentColor;
		Color newColor;
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				currentColor = new Color( img.getRGB(i,j) );
				newColor = new Color(currentColor.getRed(), currentColor.getGreen(), blueChange);
				img.setRGB(i, j, newColor.getRGB());
			}
		}
		return img;
	}


}
