import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.company.Breake;


public class MainClass
{	
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, ExecutionException 
	{
		Scanner ob = new Scanner(System.in);
		Container c = new Container();
		c.generateAndAddRandomWords(10000, c);
		c.add("Programming");

		System.out.println("Введіть час роботи програми: ");
		Breake brek = new Breake(ob.nextLong());
		brek.start();
		long start = System.currentTimeMillis();

		c.findMinMax();
		System.out.println(c.toString());
		c.contains("Programming");
		
		long end = System.currentTimeMillis() - start;
		System.out.println("Час виконання основного потоку,послідовна обробка "+ end + "ms");

		MyCallable1 ob1 = new MyCallable1(c);
		FutureTask f1 = new FutureTask(ob1);
		Thread thread1 = new Thread(f1);

		MyCallable2 ob2 = new MyCallable2(c);
		FutureTask f2 = new FutureTask(ob2);
		Thread thread2 = new Thread(f2);

		MyCallable3 ob3 = new MyCallable3(c);
		FutureTask f3 = new FutureTask(ob3);
		Thread thread3 = new Thread(f3);


		thread1.start();

		thread2.start();

		thread3.start();


		System.out.println("Час роботи потоку minMax = " + f1.get());
		System.out.println("Час роботи потоку toString = " + f2.get());
		System.out.println("Час роботи потоку contains = " + f3.get());
		double diff =  (double)end/(long)f2.get();
		System.out.println("Паралельна обробка працює швидше в " + diff + "раз");

	}
}

//minMax
class MyCallable1 implements Callable<Long>
{
	private Container ob;
	public MyCallable1(Container c)
	{
		ob = c;
	}
	@Override
	public Long call() throws InterruptedException 
	{
		long start = System.currentTimeMillis();
		ob.findMinMax();

		long end = System.currentTimeMillis() - start;
		return end;
	}
}
//toString
class MyCallable2 implements Callable<Long>
{
	private Container ob;
	public MyCallable2(Container c)
	{
		ob = c;
	}
	@Override
	public Long call() throws InterruptedException 
	{
		long start = System.currentTimeMillis();
		ob.toString();
		long end = System.currentTimeMillis() - start;
		return end;
	}
}

//contains
class MyCallable3 implements Callable<Long>
{
	private Container ob;
	public MyCallable3(Container c)
	{
		ob = c;
	}
	@Override
	public Long call() throws InterruptedException 
	{
		long start = System.currentTimeMillis();
		ob.contains("Programming");
		long end = System.currentTimeMillis() - start;
		return end;
	}
}

class Container extends Thread  
{

	public static String [] string = new String [] {};
	private int counter;
	
	public void generateAndAddRandomWords(int numberOfWords,Container c)
	{
		Random random = new Random();
		String s = "";
		for(int i = 0; i < numberOfWords; i++)
		{
			char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
			for(int j = 0; j < word.length; j++)
			{
				s += (char)('a' + random.nextInt(26));
			}
			c.add(s);
			s = "";
		}
	}

	void findMinMax() throws InterruptedException
	{
		if(size() == 0)
		{
			System.out.println("Контейнер порожній findMinMax");
			return ;
		}
		int Max = -1;
		int Min = 10000;
		int[] arr = new int[2];

		for(int start = 0;start<string.length;start++)
		{
			if(string[start].length() > Max)
			{
				Max = string[start].length();
			}
			else if(string[start].length() < Min)
			{
				Min = string[start].length();
			}
			Thread.sleep(1);
		}
		System.out.println("Max element is " + Max);
		System.out.println("Min element is " + Min);
	}
	
	boolean contains(String input) throws InterruptedException
	{
		if(size() == 0)
		{
			System.out.println("Контейнер порожній contains");
		}
		for(int i = 0;i<this.string.length;i++)
		{
			if(this.string[i].equals(input))
			{
				System.out.println("Контейнер містить слово Programming");
				return true;
			}
			Thread.sleep(1);
		}
		System.out.println("Контейнер не містить слова Programming");
		return false;
	}
	void add(String s)
	{
		string = Arrays.copyOf(string, string.length + 1); //create new array from old array and allocate one more element
		string[string.length - 1] = s;
	}
	public String toString()
	{
		if(size() == 0)
		{
			System.out.println("Контейнер порожній tostring");
		}
		String s = "";
		for(int i = 0;i<string.length;i++)
		{
			s = s + string[i] + " ";
			try 
			{
				Thread.sleep(1);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("String constructed");
		return s;
	}
	void clear()
	{
		this.string = new String[0];
	}
	Object[] toArray()
	{
		return this.string;
	}
	int size()
	{
		return string.length - counter;
	}
}