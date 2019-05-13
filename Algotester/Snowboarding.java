package com.company;

import java.util.Scanner;

public class Snowboarding {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);        String input = null;
        String output = null;
        String command;
        int potentialDelete = 0;
        boolean debug;

        System.out.println("Вас вітає програма 7 варіанту\nВиконав Пулик Максим, студент групи КН-108 \n" +
                "Призначення: видалення з рядка слів заданої довжини, що починаються на приголосну \n");

        if (args != null && args.length == 1)
        {
            if (args.equals("-h"))
            {
                System.out.println("Команди для використання:\n" +
                        " a: введення даних\n" +
                        " b: перегляд даних\n" +
                        " c: виконанння обчислень\n" +
                        " d: відображення результату\n" +
                        " e: завершення програми\n");
            }
            if (args.equals("-d")){
                debug = true;
            }
        }
        
        do{
            System.out.println("Введіть команду: \n");
            command = in.nextLine();
            
            /*if (command.equals("-d")){
                System.out.println("Щасливого дебагу, зараз все буде");
                System.out.println("Input: " + input + "\n" + "Output: " + output + "\n" + "PotentialDelete: " + potentialDelete + "\n");
            }*/

            if(command.equals("a")){
                System.out.print("Введіть вхідні дані [текст <Enter>, довжина слів для видалення<Enter>]: \n");
                input = in.nextLine();
                potentialDelete = in.nextInt();
            }

            if(command.equals("b")){
                if(input != null){
                    System.out.println(input + "\n" + potentialDelete);
                }
                else{
                    System.out.println("Відсутні вхідні дані");
                }
            }

            if(command.equals("c")){
                if(input != null){
                    output = Vivien.Task(Vivien.Splinter(input),potentialDelete);
                }
                else{
                    System.out.println("Відсутні вхідні дані");
                }
            }

            if(command.equals("d")){
                if(output != null){
                    System.out.println("Результат: ");
                    System.out.println(input);
                    System.out.println(output);
                    input = null;
                    output = null;
                }
                else {
                    System.out.println("Відсутні вихідні дані");
                }
            }
        }while (!command.equals("e"));

        System.exit(0);
    }
}