import java.beans.XMLDecoder;
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

public class Main<T>
{
	private static int amountOfObj;
	private static int counter = 0;
	private static String nameOfXmlFile = "file.xml";
	private static String nameOfStandartFile = "f.txt";
	private static Book[] arr = null;
	private static boolean indecator = false;



	public static void write(String path, LinkedList ob) throws Exception
	{
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
		encoder.writeObject((int)ob.getSize());
		Node n = ob.getHead();
		while(n!=null)
		{
			encoder.writeObject(n.getVal());
			n = n.getNext();
		}
		encoder.close();
	}

	public static Book[] read(String filename) throws Exception 
	{

		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));

		int size = (int)decoder.readObject();
		Book[] books = new Book[size];

		for(int i = 0; i < size; i++)
		{
			books[i] = (Book)decoder.readObject();
		}
		decoder.close();
		return books;
	}

	public static Book[] createModels (Scanner ob,LinkedList obj)
	{
		Book b;
		String name,widawniztwo,genre,date,ISBN;
		String authors; 
		ArrayList<String> Authors = new ArrayList<String>();
		System.out.println("Вкажіть кількість домейн-об'єктів: ");
		amountOfObj = ob.nextInt();
		Book[] arr = new Book[amountOfObj];
		for(int i = 0;i<amountOfObj;i++)
		{
			b = new Book();
			ob.nextLine();
			System.out.println("Enter book's ISBN:\n");
			ISBN = ob.nextLine();
			b.setIsbn(ISBN);
			System.out.println("Enter name:\n");
			name = ob.nextLine();
			b.setName(name);
			System.out.println("Enter widawniztwo:\n");
			widawniztwo = ob.nextLine();
			b.setWidawniztwo(widawniztwo);
			System.out.println("Enter genre of Book:\n");
			genre = ob.nextLine();
			b.setGenre(genre);
			System.out.println("Enter date of widannja:\n");
			date = ob.nextLine();
			b.setDate(date);
			System.out.println("Enter authors,that wrote thee book:\n");
			authors = ob.nextLine();
			for(String author:authors.split("  "))
			{
				Authors.add(author);
				b.setAuthors(Authors);
			}
			arr[i] = b;
			Authors.clear();
		}
		return arr;
	}
	
	public static Book formAuto()
	{
		ArrayList<String> Authors = new ArrayList<String>();
		Book b = new Book();
		b.setIsbn("95237-124");
		b.setName("Бьорн Страуструп с++");
		b.setWidawniztwo("Просвіта");
		b.setGenre("Науково-популярний");
		b.setDate("29.06.2001");
		for(int i = 0; i<1;i++)
		{
			Authors.add("Бьорн Страуструп");
			b.setAuthors(Authors);
		}
		Authors.clear();
		return b;
	}
	
	public static void main(String[] args) throws Exception
	{
		Scanner ob = new Scanner(System.in);
		LinkedList obj = new LinkedList();
		int[] int_arr = new int[] {3,6,7};
		int num = 0;
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
			
				arr = createModels(ob,obj).clone();
				for(int k = 0;k<arr.length;k++)
				{
					obj.add(arr[k], counter);
					counter++;
					arr[k] = null;
				}
				break;
			}
			case 2:
			{
				write(nameOfXmlFile,obj);
				break;
			}
			case 3:
			{
				arr = read(nameOfXmlFile).clone();
				Book el;
				for(int i = 0;i<arr.length;i++)
				{
					el = (Book)arr[i];
					System.out.println("Isbn: " + el.getIsbn() + "\n" + "Date: " +  el.getDate() + "\n" 
							+ "Name of Book: " + el.getName() + "\n" + "Widawniztwo of Book: " + el.getWidawniztwo() + "\n"
							+ "Genre of Book: " + el.getGenre() + "\n");
					if(indecator)
					{
						obj.add(el, i);
					}
					arr[i] = null;
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
					arr = new Book[length];
					for(int i = 0;i<length;i++)
					{
						arr[i] = (Book) is.readObject();
						System.out.println("Isbn: " + arr[i].getIsbn() + "\n" + "Date: " +  arr[i].getDate() + "\n" 
								+ "Name of Book: " + arr[i].getName() + "\n" + "Widawniztwo of Book: " + arr[i].getWidawniztwo() + "\n"
								+ "Genre of Book: " + arr[i].getGenre() + "\n");		    	
						arr[i] = null;
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
					 arr = createModels(ob,obj);
					 System.out.println("Enter place to add element:");
					 pos= ob.nextInt();
					 obj.add(arr[0], pos - 1);
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
