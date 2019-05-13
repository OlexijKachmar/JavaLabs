
public class ListCheck 
{
	public static void main(String[] args)
	{
		var ob = new LinkedList();
		for(int i = 0;i<4;i++)
		{
			ob.add(i);
		}
		
		ob.delete(0);
		System.out.println(ob.get(0));
		System.out.println(ob.size());
		ob.add(5);
		System.out.println(ob.size());
		for(int i = 0;i<4;i++)
		{
			System.out.println(ob.get(i));
		}
	}
}
