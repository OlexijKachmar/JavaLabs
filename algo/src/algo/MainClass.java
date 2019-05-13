package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class MainClass 
{
	public static void main(String[] args)
	{
		Scanner ob = new Scanner(System.in);
		String ch = ob.nextLine();
		String[] nums = ch.split(" ");
		
        HashMap<String, Integer> mapForData = new HashMap<String, Integer>();
        String[] mapData = new String[2];
        int edge1 = Integer.parseInt(nums[0]);
        for(int i = 0;i<edge1;i++)
        {
        	ch = ob.nextLine();
        	mapData = ch.split(" ");
        	mapForData.put(mapData[0], Integer.parseInt(mapData[1]));
        }
        int edge2 = Integer.parseInt(nums[1]);
        int localCounter = 0;
        int globalCounter = 0;
        ArrayList<String> l = new ArrayList<String>();
        int a = 4;
        Integer b = 4;
       
        Vector<String> v = new Vector<String>(edge2);
    	int edge3 = Integer.parseInt(nums[2]);
        for(int k = 0;k<edge2;k++)
        {
        	ch = ob.nextLine();
        	localCounter = 0;
        	for(int j = 0;j<ch.length();j++)
        	{
        		localCounter += mapForData.get(Character.toString(ch.charAt(j)));
        	}
        	if(localCounter == globalCounter)
        	{
        		l.add(ch);
        	}
        	else if(localCounter <= edge3  && localCounter > globalCounter)
        	{
        		globalCounter = localCounter;
        		l.clear();
        		l.add(ch);
        	}
        }
        if(l.size() > 1)
        {
        	Collections.sort(l);
        }
        System.out.println(l.get(0));
	}
}
