import java.beans.XMLDecoder;


import java.util.regex.Pattern;

import java.util.regex.Matcher;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.Vector;

public class Main<T>
{
	private static int amountOfObj;
	private static int counter = 0;
	private static String nameOfXmlFile = "file.xml";
	private static String nameOfStandartFile = "f.txt";
	private static boolean indecator = false;
	private static String shablon;
	private static String shablonForIsbn;
	private static String shablonForDate;
	//	private static ArrayList<Book> books = new ArrayList<Book>();




	public static  void write(LinkedList ob) throws Exception
	{
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nameOfXmlFile)));
		encoder.writeObject((int)ob.getSize());
		Node n = ob.getHead();
		while(n!=null)
		{
			encoder.writeObject(n.getVal());
			n = n.getNext();
		}
		ob.clear();
		encoder.close();
	}

	public static boolean checkWordInput(Book b,String value)
	{
		/*Node crawler = ob.getHead();
		Book val = null;
		while(true)
		{
			if(crawler.getPorNumber() == porNumber)
			{
				val = (Book)crawler.getVal();
				break;
			}
			crawler = crawler.getNext();
			if(crawler == null)
			{	
				System.out.println("Елемент з таким порядковим номером не знайдено checkWordInput");
			}
		}*/
		if(value.equals("Назва"))
		{
			for(String s : b.getName().split(" "))
			{
				if(!(s.matches(shablon)))
				{
					System.out.println("Невірна назва книги checkWordInput");
					return false;
				}
			}
		}
		else if(value.equals("Жанр"))
		{
			for(String s : b.getGenre().split(" "))
			{
				if(!(s.matches(shablon)))
				{
					System.out.println("Невірний жанр книги checkWordInput");
					return false;
				}
			}
		}
		else if(value.equals("Видавництво"))
		{
			for(String s : b.getWidawniztwo().split(" "))
			{
				if(!(s.matches(shablon)))
				{
					System.out.println("Невірне видавництво книги checkWordInput");
					return false;
				}
			}
		}
		else
		{
			for(String s: b.getAuthors())
			{
				for(String str : s.split(" "))
				{
					if(!(str.matches(shablon)))
					{
						System.out.println("Невірний автор книги checkWordInput");
						return false;
					}
				}
			}
		}
		return true;
	}


	public static void read(LinkedList ob) throws Exception
	{
		boolean w;
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameOfXmlFile)));
		int size = (int)decoder.readObject();
		int i = 0,counts = 0;
		Book b = null;
		do 
		{
			w = false;
			i++;
			counts++;
			b = (Book) decoder.readObject();

			if(!(b.getIsbn().matches(shablonForIsbn)))
			{
				System.out.println("Book:" + b.getName()+" has wrong Isbn");
				w = true;
			}
			if(!(checkWordInput(b, "Назва")))
			{
				w = true;
			}
			if(!(checkWordInput(b,"Жанр")))
			{
				w = true;
			}
			if(!(b.getDate().matches(shablonForDate)))
			{
				System.out.println("Book:" + b.getName()+" has wrong Date");
				w = true;
			}
			if(!(checkWordInput(b,"Видавництво")))
			{
				w = true;
			}

			if(!(checkWordInput(b,"Автори")))
			{
				w = true;
			}
			if(!w)
			{
				ob.add(b, i-1);
			}
			else
			{
				i--;
			}

		}while(counts < size);
		decoder.close();
	}


	public static void createModels (Scanner ob,LinkedList obj)
	{
		Book b = null;
		String name,widawniztwo,genre,date,ISBN,input;
		String authors; 
		ArrayList<String> Authors = new ArrayList<String>();
		do
		{
			ob.nextLine();
			System.out.println("Вкажіть кількість домейн-об'єктів: ");
			input = ob.nextLine();

		}while(!input.matches("\\d+"));
		amountOfObj = Integer.parseInt(input);

		for(int i = 0;i<amountOfObj;i++)
		{
			b = new Book();
			ob.nextLine();

			System.out.println("Enter book's ISBN:\n");
			ISBN = ob.nextLine();
			while(!ISBN.matches(shablonForIsbn))
			{
				System.out.println("Wrong input,again:");
				ISBN = ob.nextLine();
				break;
			}
			b.setIsbn(ISBN);

			System.out.println("Enter name:\n");
			name = ob.nextLine();
			b.setName(name);
			while(!(checkWordInput(b, "Назва")))
			{
				name = ob.nextLine();
				b.setName(name);
			}

			System.out.println("Enter widawniztwo:\n");
			widawniztwo = ob.nextLine();
			b.setWidawniztwo(widawniztwo);
			while(!(checkWordInput(b,"Видавництво")))
			{
				widawniztwo = ob.nextLine();
				b.setWidawniztwo(widawniztwo);
			}

			System.out.println("Enter genre of Book:\n");
			genre = ob.nextLine();
			b.setGenre(genre);
			while(!(checkWordInput(b,"Жанр")))
			{
				genre = ob.nextLine();
				b.setGenre(genre);
			}

			System.out.println("Enter date of widannja:\n");
			date = ob.nextLine();
			while(!date.matches(shablonForDate))
			{
				System.out.println("Wrong input,again:");
				date = ob.nextLine();
			}
			b.setDate(date);

			System.out.println("Enter authors,that wrote the book:\n");
			do
			{
				authors = ob.nextLine();
				for(String author:authors.split("  "))
				{
					Authors.add(author);
				}
				b.setAuthors(Authors);
			}while(!checkWordInput(b,"Автори"));

			obj.add(b, i);
			Authors.clear();
		}
	}

	public static Book formAuto()
	{

		ArrayList<String> Authors = new ArrayList<String>();
		Authors.add("Kerningan Richi C");
		Book b = new Book();
		b.setWidawniztwo("Prosvita");
		b.setDate("january of 29 , 2001 ");
		b.setGenre("Naukovo-Pop");
		b.setName("Kerningan Rich");
		b.setIsbn("95237124");
		b.setAuthors(Authors);
		if(!(b.getIsbn().matches(shablonForIsbn)))
		{
			System.out.println("This book does not exist,Isbn is wrong");
		}
		checkWordInput(b,"Назва");
		
		checkWordInput(b,"Видавництво");
		
		checkWordInput(b,"Жанр");
		
		if(!("january of 29 , 2001 ".matches(shablonForDate)))
		{
			System.out.println("This book does not exist,date is wrong");
		}
		checkWordInput(b,"Автори");

		Authors.clear();
		return b;
	}

	public static void main(String[] args) throws Exception
	{
		shablonForIsbn = "\\d+-\\d+-\\d+-\\d+-\\d+";
		shablon = "\\w+";
		shablonForDate = "\\d{2}[.]\\d{2}[.]\\d{4}";
		Scanner ob = new Scanner(System.in);
		LinkedList obj = new LinkedList();
		int[] int_arr = new int[] {3,6,7};
		int num = 0,number = 0;
		int count = 0;
		Book book;
		for(String s : args)
		{
			if(s.equals("-auto"))
			{
				indecator = true;
				break;
			}
		}
		while(true) 
		{
			System.out.println("\n1 - Заповнити  домейн-екземпляри і додати в список");
			System.out.println("2 - Запишіть дані в файл");
			System.out.println("3 - Зчитайте дані з xml-файлу");
			System.out.println("4 - Серіалізація за стандартним протоколом");
			System.out.println("5 - Десеріалізація за стандартним протоколом");
			System.out.println("6 - Продемонструвати функції: додавання,видалення,формування стрічки");
			System.out.println("7 - вихід");
			if(!indecator)
			{
				num = ob.nextInt();
			}
			else
			{
				if(count == int_arr.length)
				{
					break;
				}
				num = int_arr[count];
				count++;
			}
			switch(num)
			{
			case 1: 
			{
				int k = 0;
				createModels(ob,obj);
				break;
			}
			case 2:
			{
				write(obj);
				break;
			}
			case 3:
			{
				Book el;//boolean comin = false;
				number = (int)new XMLDecoder(new BufferedInputStream(new FileInputStream(nameOfXmlFile))).readObject();
				read(obj);
				for(int i = 0;i<number;i++)
				{
					System.out.println(obj.toString(i));
				}
				if(number <= 0)
				{
					System.out.println("Жоден об'єкт не пройшов валідацію");
				}
				break;
			}
			case 4:
			{
				try
				{
					ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nameOfStandartFile));
					Node crawler = obj.getHead();
					os.writeInt(obj.getSize());
					while(crawler!=null)
					{
						os.writeObject(crawler.getVal());
						crawler = crawler.getNext();
					}
					obj.clear();
					os.close();
				} 
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}	 
				catch (IOException e)
				{
					e.printStackTrace();
				}	
				break;
			}
			case 5:
			{
				try 
				{
					ObjectInputStream is = new ObjectInputStream(new FileInputStream(nameOfStandartFile));
					int length = is.readInt();
					for(int i = 0;i<length;i++)
					{
						obj.add((Book)is.readObject(), i);
						System.out.println(obj.toString(i));
					}
					
					is.close();
				} 
				catch (FileNotFoundException e) 
				{	
					e.printStackTrace();
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
				break;
			}
			case 6:
			{
				int pos = 0;
				if(!indecator)
				{
					createModels(ob, obj);
				}
				else
				{
					book = formAuto();
					obj.add(book, obj.getSize());
				}
				System.out.println("Дані про всі об'єкти після додавання:");
				for(int start = 0;start<obj.getSize();start++)
				{
					System.out.println(obj.toString(start));
					System.out.println();
				}
				if(!indecator)
				{
					System.out.println("Виберіть з якої позиції видалити елемент: ");
					pos = ob.nextInt();
				}
				else
				{
					pos = obj.getSize();
				}
				obj.delete(pos - 1);
				System.out.println("Дані про всі об'єкти після видалення:");
				for(int start = 0;start < obj.getSize();start++)
				{
					System.out.println(obj.toString(start));
					System.out.println();
				}
				obj.clear();
				if(obj.isEmpty())
				{
					System.out.println("Розмір контейнера після очищення: " + obj.getSize()+"\n Контейнер порожній");
				}
				break;
			}
			case 7:
			{
				System.out.println("System exited");
				System.exit(0);
			}
			default:
			{
				System.out.println("Wrong command");
			}

			}
		}
	}
}
