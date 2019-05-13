package com.company;

import java.lang.String;

public class Vivien 
{
    private static int j;
    public static boolean isVovel(String word)
    {
        word = word.toLowerCase();
        char c = word.charAt(0);
        char [] chars = {'a','e','o','i','y','u'};

        for (char a: chars){
            if (a == c)
            {
                return true;
            }
        }
        return false;
    }

    public static String[] Splinter(String input)
    {
        int m = input.length();
        char[] num = input.toCharArray();

        j=0;

        String[] now = new String[m];
        
        for(int i = 0; i < m; i++){
            if(num[i] == ' ')
            {
               j++;
            }
            else
            {
                now[j]+= num[i];
            }
        }
        return now;
    }

    public static String Task(String[] now,int size)
    {
        StringBuilder k = new StringBuilder();
        for (int i = 0; i < j+1; i++){
            if(now[i].length()==size && !Vivien.isVovel(now[i]))
            {
                k.append(' ');
            }
            else
            {
                k.append(now[i]);
            }
        }
        return k.toString();
    }
}