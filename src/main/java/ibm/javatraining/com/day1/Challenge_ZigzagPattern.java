package ibm.javatraining.com.day1;
import java.util.Scanner;

public class Challenge_ZigzagPattern {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Zigzag Pattern: ");
		int zigzagSize = input.nextInt();
		zigzagPattern(zigzagSize);
		System.out.println();
	}
	
	public static void zigzagPattern (int a)
	{
		int myCounter = 1;
		for(int row = 1; row <= a; row++)
		{
			int reverseCounter = row * a;
			for (int col = 1; col <= a; col++)
			{
				
				if(row % 2 != 0)
				{
					System.out.print(myCounter + " ");
					
				}
				
				else
				{
					System.out.print(reverseCounter + " ");
					reverseCounter--;
				}
				myCounter++;
				
			}
			System.out.println();
		}
	}

}
