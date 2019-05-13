
public class LinkedList 
{

	class Node
	{
		private Node next;
		private Integer data;
	
		public Node() {
		}

		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Integer getData() {
			return data;
		}
		public void setData(Integer data) {
			this.data = data;
			}
}
	public static int size;
	Node first;
	Node last;
		/* Конструктор без аргументів */
	public LinkedList()
	{
		first = null;
		last = null;
		size = 0;
	}
		/* Додати елемент в кінець списку */
	public void add(Integer data) 
	{
		Node new_node = new Node();
		new_node.setData(data);
		new_node.setNext(null);
		
		if(size == 0)
		{
			first = new_node;
		}
		else if(size == 1)
		{
			last = new_node;
			first.setNext(last);
		}
		else
		{
			Node lastEl = new_node;
			last.setNext(lastEl);
			last = lastEl;
		}
	size++;
	}
		/* Отримати елемент по індексу, повертає null якщо такий елемент недоступний */
	public Integer get(int index) 
	{
		Node crawler = first;
		int i = 0;
		while(i < size)
		{
			if(i == index)
			{
				return crawler.getData();
			}
		crawler = crawler.getNext();
		i++;
		}
		return null;
	}
		/* Вилучення елементу за індексом, повертає true у разі успіху або false в іншому випадку */
	public boolean delete(int index) 
	{
		boolean check = false;
		Node crawler = first;
		
		if(index == 0)
		{
			first = first.getNext();
			check = true;
			size--;
			return check;
		}
		if(index<size)
		{			
			
			for( int i = 0;i<index;i++)
			{
				if(i == index - 1)
				{
					crawler.setNext(crawler.getNext().getNext());
					check = true;
					break;
				}
				crawler = crawler.getNext();
			}
			size--;
		}
		return check;
	}
		/*Пверта розмір списку: якщо елементів в списку нема то повертає 0 (нуль)*/
	public int size() 
	{
		return size;
	}
}
