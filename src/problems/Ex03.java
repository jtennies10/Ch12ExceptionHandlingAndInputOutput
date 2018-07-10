package problems;

import java.util.Random;
import java.util.Scanner;

public class Ex03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		
		//create array and fill with random ints
		int[] a = new int[100];
		for(int i = 0; i < a.length; i++) {
			a[i] = rand.nextInt();
		}
		
		System.out.print("Enter index of array value to display: ");
		
		try {
			//get user index choice
			int index = sc.nextInt();
			System.out.println(a[index]);
		} catch(ArrayIndexOutOfBoundsException ae) {
			//alert user the index is out of bounds
			System.out.println("Index Out of Bounds");
		}
	}
}
