package com.company;

import java.util.Scanner;

public class Snowboarding {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);        String input = null;
        String output = null;
        String command;
        int potentialDelete = 0;
        boolean debug;

        System.out.println("��� ��� �������� 7 �������\n������� ����� ������, ������� ����� ��-108 \n" +
                "�����������: ��������� � ����� ��� ������ �������, �� ����������� �� ���������� \n");

        if (args != null && args.length == 1)
        {
            if (args.equals("-h"))
            {
                System.out.println("������� ��� ������������:\n" +
                        " a: �������� �����\n" +
                        " b: �������� �����\n" +
                        " c: ���������� ���������\n" +
                        " d: ����������� ����������\n" +
                        " e: ���������� ��������\n");
            }
            if (args.equals("-d")){
                debug = true;
            }
        }
        
        do{
            System.out.println("������ �������: \n");
            command = in.nextLine();
            
            /*if (command.equals("-d")){
                System.out.println("��������� ������, ����� ��� ����");
                System.out.println("Input: " + input + "\n" + "Output: " + output + "\n" + "PotentialDelete: " + potentialDelete + "\n");
            }*/

            if(command.equals("a")){
                System.out.print("������ ����� ��� [����� <Enter>, ������� ��� ��� ���������<Enter>]: \n");
                input = in.nextLine();
                potentialDelete = in.nextInt();
            }

            if(command.equals("b")){
                if(input != null){
                    System.out.println(input + "\n" + potentialDelete);
                }
                else{
                    System.out.println("³����� ����� ���");
                }
            }

            if(command.equals("c")){
                if(input != null){
                    output = Vivien.Task(Vivien.Splinter(input),potentialDelete);
                }
                else{
                    System.out.println("³����� ����� ���");
                }
            }

            if(command.equals("d")){
                if(output != null){
                    System.out.println("���������: ");
                    System.out.println(input);
                    System.out.println(output);
                    input = null;
                    output = null;
                }
                else {
                    System.out.println("³����� ������ ���");
                }
            }
        }while (!command.equals("e"));

        System.exit(0);
    }
}