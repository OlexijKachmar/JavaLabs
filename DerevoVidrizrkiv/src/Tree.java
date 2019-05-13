
public class Tree
{
	static int[] array = new int[] {-1,2,5,4,6,2,-7,5};
	static int[] tree = new int[2*array.length-1];
	static int nr,nl;
	static int sum;
	public static void main(String[] args)
	{
    	Tree ob = new Tree();
   		ob.build(0, 0, array.length - 1);
   		nl = 0;nr = 7;sum = 0;
   		ob.sum(0, array.length - 1, 0);
   		System.out.println(sum);
	}
	
	int counter = 0;
	
	private void build(int v,int l,int r)
	{
		counter++;
		if(l == r && counter!=1)
		{
			tree[v] =array[l];
			return;
			
		}
		int vm = l + (r-l)/2;
		build(2*v + 1,l,vm);
		build(2*v + 2,vm+1,r);
		tree[v] = tree[2*v + 1]+ tree[2*v+2];
	}
	private void sum(int l,int r,int iterator)
	{
	
		if(nl<= l && nr>=r)
		{
			sum += tree[iterator];
			return;
		}
		if(l == r) {return;}
		int nm = l + (r-l)/2;	
		sum(l,nm,2*iterator+1);
		sum(nm+1, r,2*iterator+2);
	}
}
