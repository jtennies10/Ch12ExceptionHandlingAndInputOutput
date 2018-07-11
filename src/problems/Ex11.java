/*Removes all occurrences of a specified string
 * from a text file, main method accepts two arguments,
 * the first being the string to remove and the second
 * being the filename
 */

package problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Ex11 {
	public static void main(String[] args) {
		String strToRemove = "";
		File originalFile = null;
		File tempFile = null;
		Scanner sc = null;
		PrintWriter output = null;
		
		//try to fill the string to be removed and the file instance, followed
		//by a scanner to read the file and a printwriter to write to it, catch
		//possible ArrayIndexOutOfBounds and FileNotFoundException exceptions
		//if caught exit early
		try {
	
			strToRemove = args[0];
			originalFile = new File(args[1]);
			tempFile = new File("tempFile.txt");
			sc = new Scanner(originalFile);
			output = new PrintWriter(tempFile);

		
		} catch(ArrayIndexOutOfBoundsException | FileNotFoundException ex) {
			System.out.println("Invalid arguments passed");
			
			System.exit(-1);
		}
		
		while(sc.hasNextLine())  {
			//grab next line and replace input string with a blank
			String currentLine = sc.nextLine();
			currentLine = currentLine.replaceAll(strToRemove, "");
			
			//print the reformatted line to the temporary file
			output.println(currentLine);
			
		}
		
		//close scanner and printwriter instances
		output.close();
		sc.close();
		
		//delete the original file or close program if failure
		if(!originalFile.delete()) {
			System.out.println("Original File could not be deleted");
			System.exit(-1);
		}
		
		//rename the temporary file to the original file name
		//or close if failure
		if(!tempFile.renameTo(originalFile)) {
			System.out.println("Temporary file could not be renamed");
			System.exit(-1);
		}
		
		
		
		
		
	}
}
