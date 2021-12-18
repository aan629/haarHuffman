public class Execute {

	public static void main(String[] args) {
		
		//Please define this variables before executing the algorithm.
		String directoryInput = "/home/severmateus/Pictures/NIT/";
		String directoryOutput = "/home/severmateus/";
		String fileOutputExtension = ".out";
		/*Example: 
		 * directoryInput = "/home/severmateus/Pictures/NIT/";
		 * directoryOutput = "/home/severmateus/";
		 */
		String filename = "11";
		//Currently Lossless Supported: PNG
		String extension = ".png";
			
		//Encoding	
		//new MainAlgorithm(directoryInput+filename+extension, directoryOutput+filename+fileOutputExtension, "encoding");
			
		//Decoding
		new MainAlgorithm(directoryOutput+filename+fileOutputExtension, directoryOutput+filename);

	}

}
 