public class Sort
{
	private static void sort(int[] array, Comparator comp)
	{
		for (int gap = array.length / 2; gap > 0; gap /= 2)
		{
			for (int i = gap; i < array.length; i++)
			{
				int val = array[i];
				int j;
				for (j = i; j >= gap && comp.compare(array[j - gap], val) > 0; j -= gap)
				{
					array[j] = array[j - gap];
				}
				array[j] = val;
			}
		}
	}

	public static void main(String[] args)
	{
		int[] array = {1,5,2,4,10,6,0,3,10};
		Comparator comp = new Comparator();
		
		//Change your code here
		sort(array, comp);
		int j = array.length - 1;
		int k = 0;
		
		while(k<=j)
		{
			int temp = array[k];
			array[k] = array[j];
			array[j] = temp;
			k++;
			j--;
		}
		
		for (int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
	}
	
}