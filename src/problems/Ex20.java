package problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*Accepts a srcRootDirectory argument and removes the package chapteri statement from 
 * each source file in the chapteri directory
 * Assume the chapteri directory is in the srcRootDirectory
 */

public class Ex20 {

	public static void main(String[] args) {
		File srcRootDirectory = null;
		
		//initiate the file
		try {
			srcRootDirectory = new File(args[0]);
		} catch(ArrayIndexOutOfBoundsException ae) {
			System.out.println("Directory not provided.");
			System.out.println("Usage: java problems.Ex20 srcRootDirectory");
			System.exit(-1);
		}
		
		//make sure the created file object is a directory
		if(!srcRootDirectory.isDirectory()) {
			System.out.println("The passed directory argument is not a directory.");
			System.exit(-1);
		}
		
		//call the method to remove the package labels, catch potential FileNotFoundException
		try {
			srcRootDirectory = removePackageLabels(srcRootDirectory);
		} catch(FileNotFoundException fnfe) {
			System.out.println("Error reading files");
			System.exit(-1);
		}
		
		if(srcRootDirectory != null) {
			System.out.println("Directory updated.");
		} else {
			System.out.println("There was an error modifying the directory.");
		}
		
	}
	
	/*
	 * Parses through a source file and recursively through any subdirectories,
	 * removing all package chapteri instances using regex
	 * 
	 * @param srcRootDirectory The directory to be parsed
	 * @param chCount keeps count of the current chapter for string matching
	 * @return The modified directory
	 */
	public static File removePackageLabels(File srcRootDirectory) throws FileNotFoundException {
		//parse through the files in srcRootDirectory
		for(File f : srcRootDirectory.listFiles()) {
			if(f.isDirectory()) {
				f = removePackageLabels(f);
			} else {
				Scanner sc = new Scanner(f);
				File temp = new File("temp.txt");
				PrintWriter output = new PrintWriter(temp);
				
				while(sc.hasNextLine()) {
					String line = sc.nextLine();
					
					//use regex to check if the line contains the package statement
					if(line.matches("package chapter\\d;")) {
						line = "";
					}
					
					output.println(line);
				}
				
				//close Scanner and PrintWriter
				sc.close();
				output.close();
				
				if(!f.delete() || !temp.renameTo(f)) {
					return null;
				}				
			}
		}
		
		return srcRootDirectory;
	}

}
