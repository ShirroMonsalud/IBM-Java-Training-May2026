package ibm.javatraining.com.day1;
import java.util.Scanner;

public class Day1 {

	enum Days 
	{
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Blackjack:");
		System.out.println("01, 02:\t" + blackjack(1,2));
		System.out.println("21, 22:\t" +blackjack(21 ,22));
		System.out.println("22, 22:\t" +blackjack(22 ,22));
		System.out.println("02, 10:\t" +blackjack(2 ,10) + "\n");
			
		System.out.print("dayWeek (Switch): ");
		int dayNumInput = input.nextInt();
		System.out.println(dayWeekSwitch(dayNumInput) + "\n");
		
		System.out.print("dayWeek (Pattern): ");
		dayNumInput = input.nextInt();
		Days daySelect = dayWeekNum(dayNumInput);
		System.out.println(dayWeekPattern(daySelect) + "\n");
		
		
		numPyramid();
	
	}
	
	public static int blackjack(int a, int b)
	{
		a = a > 21 ? 0 : a;
		b = b > 21 ? 0 : b;
		return a > b ? a : b;
	}
	
	
	
	public static String dayWeekSwitch(int a)
	{
		switch(a)
		{
			case 1:
				return "Monday";
			case 2:
				return "Tuesday";
			case 3:
				return "Wednesday";
			case 4:
				return "Thursday";
			case 5:
				return "Friday";
			case 6:
				return "Saturday";
			case 7:
				return "Sunday";
			default:
				return "Invalid day number.";
		}
			
	}
	
	public static Days dayWeekNum(int day) {
        return switch (day) {
            case 1 -> Days.MONDAY;
            case 2 -> Days.TUESDAY;
            case 3 -> Days.WEDNESDAY;
            case 4 -> Days.THURSDAY;
            case 5 -> Days.FRIDAY;
            case 6 -> Days.SATURDAY;
            case 7 -> Days.SUNDAY;
            default -> null;
        };
    }
    
    public static String dayWeekPattern(Days day) {
        if (day == null) {
            return "Invalid day number.";
        }
        
        return switch (day) {
            case MONDAY -> "Monday";
            case TUESDAY -> "Tuesday";
            case WEDNESDAY -> "Wednesday";
            case THURSDAY -> "Thursday";
            case FRIDAY -> "Friday";
            case SATURDAY -> "Saturday";
            case SUNDAY -> "Sunday";
        };
    }
	
	public static void numPyramid()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Number Pyramid: ");
		int N = input.nextInt();
		
		while (N < 1 || N > 20) 
		{
			System.out.print("Number Pyramid: ");
			N = input.nextInt();
		}
		
		for(int row = 1; row <= N ; row++)
		{
			for (int col = 1; col <= row; col++)
			{
			
				System.out.print(col + " ");
			}
			System.out.println();
		}

	}

}
