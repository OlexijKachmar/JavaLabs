import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Words
{
	static void helperMethod()
	{
		System.out.println("This is the first java application of Oleksiy Kachmar.\n"
				+ "This program is determined to substitute defined letter in all words of array\n"
				+ "If word length is too short , it returns message about it\n"
				+ "input consists of String , number of letter to be changed in every word,and letter to substitute on\n"
				+ "output should consist of changed string and added info\n");
	}
	public static void main(String[] args) 
	{		
		boolean b = false;
		boolean debug = false;
		
		for(String arg: args)
		{
			if(arg.equals("-h"))
			{
				Words.helperMethod();
			}
			if(arg.equals("-d"))
			{
				debug = true;
			}
			
		}

		Scanner ob = new Scanner(System.in);
		String input = null;
		String s = "";StringBuffer str = new StringBuffer();
		int indexOfNum = 0;char charToChange = 0,tempChar = 0;
		List<String> ints = new ArrayList<String>();
		while(true) 
		 {
	            System.out.println("\n1 - input");
	            System.out.println("2 - show input");
	            System.out.println("3 - calculate and show result");
	            System.out.println("4 - exit");
	            int num = ob.nextInt();
	      switch(num)
	      {
	      case 1:
	        
	    	  System.out.println("Enter string:");
	    	  ob.nextLine();
              s = ob.nextLine()+" ";
              str.append(s);
              indexOfNum = ob.nextInt() - 1;
	   		try 
	   		{
	   			charToChange = (char)System.in.read();
	   		}
	   		catch (IOException e) 
	   		{
	   			e.printStackTrace();
	   		}
	   		break;
	      case 2:
			if(s!="" && str != null && indexOfNum >= 0)
			{
				System.out.println("Input was correct");
				System.out.println("String:" + s +"\nIndex of number is" + indexOfNum);
			}
			else
			{
				System.out.println("Input is incorrect or absent");
			}
			break;
		case 3:
		 if(s != "" && str != null && indexOfNum>=0)
		 {
	   		String temp = "";
	   		int i = 0,currentLetter = 0,count = 0;
	   		boolean checkIfFound;
	        while(i<str.length())
	   		{
	   			checkIfFound = false;
	   			tempChar = str.charAt(i);
	   			currentLetter = 0;
	   			while (tempChar != ' ' &&  i<str.length())
	   			{
	   				if(currentLetter == indexOfNum)
	   				{
	   					checkIfFound = true;
	   					str.setCharAt(i, charToChange);
	   				}
	   				temp += str.charAt(i);
	   				i++;
	   				if(i<str.length())
	   				{
	   					tempChar = str.charAt(i);
	   				}
	   				currentLetter++;
	   			}
	   			if(!checkIfFound)
	   			{
	   				if(debug) 
	   				{
	   					Debug.state(str.charAt(currentLetter- 1), temp, currentLetter);
	   				}
	   				ints.add(temp);
	   				System.out.print(temp+ " ");
	   				count++;
	   			}
	   			else
	   			{
	   				if(debug) 
	   				{
	   					Debug.state(str.charAt(currentLetter- 1), temp, currentLetter);
	   				}
	   				System.out.print(temp + " ");
	   			}
	   			temp = "";
	   			i++;
	   		}
	   		for(int q = 0;q<ints.size();q++)
	   		{
	   			System.out.print("\nWord " + ints.get(q) + " is too short");
	   		}
		}
		else
		{
			System.out.println("Fullfill correct input");
		}
		 break;
		case 4:
			 System.out.println("System exited");
             System.exit(0);
         default:
             System.out.println("unknown command.");
	      }
	   }
	}
} 

class Debug
{
	public static void state(char tempChar,String temp,int currentLetter)
	{
		System.out.println("Number of letter that is checking\t" + currentLetter + "\nLetter that's checking\t" + tempChar + "\nWord that is checking\t"+ temp);
	}
}