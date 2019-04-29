import java.io.Serializable;

public class Node<T>
{
	private int porNumber;
	private T val;
	private Node next;

	public void setVal(T value)
	{
		this.val = value;
	}
	public T getVal()
	{
		return val;
	}
	public void setNext(Node next)
	{
		this.next = next;
	}
	public Node getNext()
	{
		return  next;
	}
	public void setPorNumber(int num)
	{
		porNumber = num;
	}
	public int getPorNumber()
	{
		return porNumber;
	}
}

class LinkedList<T>
{
	private Node head;
	private Node crawler;
	private int size;

	public void add(T new_node,int porNumber)
	{
		if(porNumber<0)
		{
			return;
		}
		if(head == null && porNumber == 0)
		{
			head =   new Node();
			head.setVal(new_node);	
			head.setPorNumber(0);
			size++;
			return;
		}
		Node to_add = null;
		boolean was = false;
		crawler = head;
		while(crawler!=null)
		{
			if(crawler.getPorNumber() == porNumber - 1)
			{
				was = true;
				to_add = new Node();
				to_add.setPorNumber(porNumber);
				to_add.setVal(new_node);
				to_add.setNext(crawler.getNext());
				crawler.setNext(to_add);
				crawler = crawler.getNext();
			}
			crawler = crawler.getNext();
			if(crawler!=null && was)
			{
				crawler.setPorNumber(crawler.getPorNumber() + 1);
			}
		}
		if(!was)
		{
			System.out.println("Такого елемента в списку не знайдено");
			return;
		}  
		size++;
	}

	public boolean hasNext(Node element)
	{
		if(element == null)
		{
			return false;
		}
		crawler = element;
		if(crawler.getNext()!= null )
		{
			return true;
		}
		return false;
	}

	public  void delete(int position)
	{
		if(position == 0)
		{
			crawler = head.getNext();
			head.setNext(null);
			head = crawler;
			size--;
			return;
		}
		boolean was = false;
		for (crawler = head; hasNext(crawler);)
		{
			if(crawler.getPorNumber() == position - 1)
			{
				was = true;
				crawler.setNext(crawler.getNext().getNext());
				size--;
			}
			crawler = crawler.getNext();
			if(was && crawler != null)
			{
				crawler.setPorNumber(crawler.getPorNumber() - 1);
			}
		}
	}


	public boolean isEmpty()
	{
		if(head == null)
		{
			return true;
		}
		return false;
	}

	public Node[] makeMas()
	{
		crawler = head;
		Node[] arr = new Node [size];
		int i = 0;
		for (; crawler!=null; )
		{
			arr[i] =  crawler;
			crawler = crawler.getNext();
			i++;
		}
		return arr;
	}

	public String toString(int porNumber)
	{
		Node[] new_arr = makeMas().clone();
		String s = null;
		if(new_arr.length<1)
		{
			return "Кількість домейн об'єктів == 0";
		}
		Book a = (Book)new_arr[porNumber].getVal();
		s = "Isbn: " + a.getIsbn() + "\n" + "Date: " +  a.getDate() + "\n" 
				+ "Name of Book: " + a.getName() + "\n" + "Widawniztwo of Book: " + a.getWidawniztwo() + "\n"
				+ "Genre of Book: " + a.getGenre() + "\n";

		return s;
	}

	public void clear()
	{
		head = null;
		size = 0;
	}

	public int getSize()
	{
		return size;
	}

	public Node getHead()
	{
		return head;
	}
}

