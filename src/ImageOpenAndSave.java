import java.awt.image.DataBufferByte;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageOpenAndSave {

	public static void main(String[] args) {
		
		String filename = "21";
		String imageDir = "/home/severmateus/Pictures/NIT/"+filename+".png";
		String fileImageOutput = "/home/severmateus/"+filename;
		
		BufferedImage image;
		try {
			
			//System.out.println("Starting to read image");
			image = ImageIO.read(new File(imageDir));
			//System.out.println("Finishing to read image");
			
			//System.out.println(image.getHeight());

			// 2. Convert Buffered image to 2D Array
			//System.out.println("Starting 2D Array Conversion");
			float[][] rawData = compute2(image); // some code to read raw bytes from image file
			saveImage(rawData, fileImageOutput);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
		
		private static void saveImage(float[][] finalArray, String dir) {
			BufferedImage image = new BufferedImage(finalArray.length, finalArray[0].length, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < finalArray.length; x++) {
				for (int y = 0; y < finalArray[x].length; y++) {
					image.setRGB(x, y, (int) finalArray[x][y]);
				}
			}

			File ImageFile = new File(dir + ".png");
			try {
				ImageIO.write(image, "png", ImageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static float[][] compute(BufferedImage img) {
			try {
				// BufferedImage img= ImageIO.read(file);
				Raster raster = img.getData();
				int w = raster.getWidth(), h = raster.getHeight();
				float pixels[][] = new float[w][h];
				for (int x = 0; x < w; x++) {
					for (int y = 0; y < h; y++) {
						pixels[x][y] = raster.getSample(x, y, 0);
					}
				}

				return pixels;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public static float[][] compute2(BufferedImage img) {
			try {
				// BufferedImage img= ImageIO.read(file);
				int w = img.getData().getWidth(), h = img.getData().getHeight();
				float pixels[][] = new float[w][h];
				for(int i = 0; i < w; i++)
				    for(int j = 0; j < h; j++)
				    	pixels[i][j] = img.getRGB(i, j);

				return pixels;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		private static float[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

			  final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			  final int width = image.getWidth();
			  final int height = image.getHeight();
			  final boolean hasAlphaChannel = image.getAlphaRaster() != null;

			  float[][] result = new float[height][width];
			  if (hasAlphaChannel) {
			     final int pixelLength = 4;
			     for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
			        int argb = 0;
			        argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
			        argb += ((int) pixels[pixel + 1] & 0xff); // blue
			        argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
			        argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
			        result[row][col] = argb;
			        col++;
			        if (col == width) {
			           col = 0;
			           row++;
			        }
			     }
			  } else {
			     final int pixelLength = 3;
			     for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
			        int argb = 0;
			        argb += -16777216; // 255 alpha
			        argb += ((int) pixels[pixel] & 0xff); // blue
			        argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
			        argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
			        result[row][col] = argb;
			        col++;
			        if (col == width) {
			           col = 0;
			           row++;
			        }
			     }
			  }

			  return result;
			 }

}
