package lab2;

import java.util.Scanner;
import java.util.Random;

public class lab2 {

	public static void main(String[] args) {
		int choice; 
		Scanner sc = new Scanner(System.in);
		
		do 
		{
			System.out.println("Perform the following methods:");
			System.out.println("1: multiplication test");
			System.out.println("2: quotient using division by subtraction");
			System.out.println("3: remainder using division by subtraction");
			System.out.println("4: count the number of digits");
			System.out.println("5: position of a digit");
			System.out.println("6: extract all odd digits");
			System.out.println("7: quit");
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 1: 
					mulTest();
					break;
				case 2: 
					System.out.println("Key in an interger m: ");
					int m = sc.nextInt();
					System.out.println("Key in an integer n: ");
					int n = sc.nextInt();
					divide(m, n);
					break;
				case 3: 
					System.out.println("Key in an interger m: ");
					int m1 = sc.nextInt();
					System.out.println("Key in an integer n: ");
					int n1 = sc.nextInt();
					modulus(m1, n1);
				case 4: 
					System.out.println("Key in a positive integer: ");
					int n4 = sc.nextInt();
					countDigits(n4);
					break;
				case 5:
					System.out.println("Key in a positive integer n: ");
					int n3 = sc.nextInt();
					System.out.println("Key in the digit you want to find the position of: ");
					int digit = sc.nextInt();
					position(n3, digit);
					break;
				case 6: 
					System.out.println("Key in a positive number n: ");
					int n2 = sc.nextInt();
					extractOddDigits(n2);
					break;
				case 7:
					System.out.println("Program terminating...");
					break;
			}
			
		}while(choice < 7);	
	}
	
	public static void mulTest()
	{
		Scanner sc = new Scanner(System.in); //to get (read) any inputs from user 
		int numOfCorrect = 0;
		int qnNum = 1;
		
		for(qnNum = 1; qnNum<=5; qnNum++)
		{
			//generating random numbers for the question
			Random rand = new Random();
			int num1 = rand.nextInt(10);
			int num2 = rand.nextInt(10);
			
			int correctAns = num1 * num2;
			
			System.out.println("How much is " + num1 + " times " + num2 + " ?");
			int userAns = sc.nextInt();
			
			if(userAns == correctAns)
			{
				System.out.println("Correct, YAY!\n");
				numOfCorrect++;
			}
			else
			{
				System.out.println("Wrong :( Try again!\n");
			}
			System.out.println(numOfCorrect + " answers out of 5 are correct\n");
		}
	}
	
	public static int divide(int m, int n)
	{
		int answer = 0;
		
		if(m==n)
		{
			System.out.println(m + "/" + n + " = 1\n");
		}
		else if(m<n)
		{
			System.out.println(m + "/" + n + " = 0\n");
		}
		else if(n==0)
		{
			System.out.println("Cannot divide by 0 lah\n");
		}
		else if(m>n)
		{
			while(m>n)
			{
				m-=n;
				answer++;
				System.out.println(m + "/" + n + " = " + answer);
			}
		}
		
		return answer; //idk what to return eh...
	}

	public static int modulus(int m1, int n1) //am i allowed to do this :(
	{
		int ans = 0;
		if(m1==n1)
		{
			System.out.println(m1 + "%" + n1 + " = 0\n");
		}
		else if(m1<n1)
		{
			System.out.println(m1 + "%" + n1 + " = " + m1);
		}
		else if(m1>n1)
		{
			while(m1>n1)
			{
				m1-=n1;
				ans++;
				int remainder = m1-(n1*ans);
				System.out.println(m1 + "%" + n1 + " = " + remainder);
				
			}
		}
		return m1-(n1*ans); //i still dk what to return 
	}
	
	public static long extractOddDigits(long n2)
	{
		if(n2<0)
		{
			System.out.println("Error input\n");
			return -1;
		}
		
		long oddDigitNum = 0;
		int positioning = 1;
		boolean foundOddDigit = false;
		
		
		while(n2>0)
		{
			long lastDigit = n2%10;
			
			if(lastDigit%2 != 0)
			{
				oddDigitNum = lastDigit * positioning + oddDigitNum;
				positioning *= 10;
				foundOddDigit = true;
			}
			n2/=10;
			System.out.println("updated n2: " + n2);
		}
		
		if(!foundOddDigit)
		{
			System.out.println("No odd digits found in the number\n");
			return -1;
		}
		System.out.println("oddDigits = " + oddDigitNum);
		return oddDigitNum;
		
	}
	
	public static int position(int n3, int digit)
	{
		int position = 1; 
		int lastDigit = n3%10;
		
		if(n3<0)
		{
			System.out.println("Give a positive number instead\n");
		}
		
		if(lastDigit == digit)
		{
			System.out.println("Position = 1\n");
		}
		
		while(lastDigit != digit && n3>0)
		{	
			n3/=10;
			lastDigit = n3%10;
			position++;
			
			if(n3==0) //YAY you figured this out kind of by yourselfffff
			{
				System.out.println("Position = -1\n"); 
				return -1;
			}
		}
		System.out.println("Position = " + position + "\n");
		
		return position;
	
	}
	
	public static int countDigits(int n4)
	{
		int count = 0;
		int n5 = n4;
		
		if(n5<0)
		{
			System.out.println("n: " + n4 + "-Error input!!\n");
		}
		else if(n5>0)
		{
			while(n5!=0)
			{
				n5/=10;
				count++;
			}
			System.out.println("n: " + n4 + " -count = " + count);
		}
		return count;
	}
	
	
}
