import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class MainClass
{
	private static boolean index;
	private static String nameOfStandartFile = "file.txt";
	private static String nameOfXmlFile = "f.xml";


	public void writeStandart(LinkedList<Book> l) throws IOException
	{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nameOfStandartFile));
		os.writeInt(l.size());
		for(Iterator it = l.iterator();it.hasNext();)
		{
			os.writeObject(it.next());
		}
		l.clear();
		os.close();
	}

	public void readFromStandart(LinkedList<Book> l) throws IOException, ClassNotFoundException
	{
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(nameOfStandartFile));
		int length = is.readInt();

		for(int i = 0;i<length;i++)
		{
			l.push((Book)is.readObject());
		}
		toStr(l);
		is.close();
	}

	public  void writeToXml(LinkedList<Book> l) throws Exception
	{
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nameOfXmlFile)));
		encoder.writeObject((int)l.size());
		//T book = l.getFirst();
		Iterator it = l.iterator();
		while(it.hasNext())
		{
			encoder.writeObject(it.next());
		}
		l.clear();
		encoder.close();
	}

	public void readFromXml(LinkedList<Book> l,MainClass ob) throws Exception 
	{
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameOfXmlFile)));

		int size = (int)decoder.readObject();
		for(int i = 0; i < size; i++)
		{
			l.push((Book) decoder.readObject());
		}
		ob.toStr(l);
		decoder.close();
	}

	public  void toStr(LinkedList<Book> l)
	{
		Book el;String s = "";
		for(int i = 0;i<l.size();i++)
		{
			el = l.get(i);
			s = "Date " + el.getDate() + "\n" + "Isbn " + el.getIsbn() + "\n" + "Genre " + el.getGenre()+ "\n"+ "Name of Book" 
					+ el.getName() + "\n" + "Widawniztwo " + el.getWidawniztwo() + "\n" + "Authors ";
			for(int k = 0;k<el.getAuthors().size();k++)
			{
				s += el.getAuthors().get(k) + " ";
			}
			System.out.println(s);
		}
	}

	public void addElements(boolean index,Scanner in,LinkedList<Book> l)
	{
		ArrayList<String> authors = new ArrayList<String>();
		Book b  = new Book();
		if(index)
		{
			l.add(b);
		}
		System.out.println("Enter number of books");
		int number = in.nextInt();
		String au_thors = "";
		for(int i = 0;i<number;i++)
		{
			System.out.println("Enter authors of the book\n");
			in.nextLine();
			au_thors = in.nextLine();
			for(String author:au_thors.split("  "))
			{
				authors.add(author);
			}
			b.setAuthors(authors);
			authors.clear();
			System.out.println("Enter date of the book");
			b.setDate(in.nextLine());
			System.out.println("Enter genre of the book");
			b.setGenre(in.nextLine());
			System.out.println("Enter ISBN of the book");
			b.setIsbn(in.nextLine());
			System.out.println("Enter name of the book");
			b.setName(in.nextLine());
			System.out.println("Enter widawniztwo of the book");
			b.setWidawniztwo(in.nextLine());
			l.add(b);
			b = new Book();
		}
	}

	public static void main(String[] args) throws Exception
	{
		ArrayList<String> authors = new ArrayList<String>();
		Book b  = new Book();
		authors.add("Jack London");
		b.setAuthors(authors);
		authors.clear();
		b.setDate("29.04.1932");
		b.setGenre("Hudozhnij");
		b.setIsbn("9102-323-23-234-12");
		b.setName("Live life");
		b.setWidawniztwo("Jack Corp");
		LinkedList<Book> l = new LinkedList<Book>();
		MainClass ob = new MainClass();
		index = false;
		for(int i = 0;i<args.length;i++)
		{
			if(args[i].equals("-auto"))
			{
				index = true;
				break;
			}
		}
		int num = 0;
		int count = 0;
		int[] int_arr = {3,6,7};
		Scanner in = new Scanner(System.in);
		while(true) 
		{
			System.out.println("\n1 - Заповнити  домейн-екземпляри і додати в список");
			System.out.println("2 - Запишіть дані в файл");
			System.out.println("3 - Зчитайте дані з xml-файлу");
			System.out.println("4 - Серіалізація за стандартним протоколом");
			System.out.println("5 - Десеріалізація за стандартним протоколом");
			System.out.println("6 - Продемонструвати функції: додавання,видалення,формування стрічки");
			System.out.println("7 - вихід");
			if(!index)
			{
				num = in.nextInt();
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
				ob.addElements(index,in,l);
				break;
			}
			case 2:
			{
				ob.writeToXml(l);
				break;
			}
			case 3:
			{
				ob.readFromXml(l, ob);
				break;
			}
			case 4:
			{
				ob.writeStandart(l);
				break;
			}
			case 5:
			{
				ob.readFromStandart(l);
				break;
			}
			case 6:
			{
				l.add(b);
				System.out.println("Elements after adding");
				ob.toStr(l);
				System.out.println("");
				System.out.println("Enter element to remove");
				l.remove(in.nextInt() - 1);
				System.out.println("Elements after remove");
				ob.toStr(l);
				System.out.println("");
				if(l.contains(b))
				{
					System.out.println("List contains Book " + b.getName());
				}
				else
				{
					System.out.println("List doesn't containt Book" + b.getName());
				}
				System.out.println("");
				Collections.sort(l, new ComparatorLeks());
				System.out.println("Elements after sort by name");
				ob.toStr(l);
				break;
			}
			case 7:
			{
				System.out.println("Bye!");
				System.exit(0);
			}
			}
		}
	}
}


