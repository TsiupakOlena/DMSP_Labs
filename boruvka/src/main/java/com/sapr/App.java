package com.sapr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        do {
            Scanner scan = new Scanner(System.in);
            Boruvky.Type type;
            System.out.println( "Введіть тип алгоритму: \n1) Мінімальне дерево; 2) Максимальне дерево" );
            try {
                int choice = scan.nextInt();
                switch(choice) {
                    case 1:
                        type = Boruvky.Type.MIN;
                        break;
                    case 2:
                        type = Boruvky.Type.MAX;
                        break;
                    default:
                        System.out.println( " Помилка! Можливі варіанти вибору - 1 і 2. " );
                        continue;
                }
            } catch (Exception e) {
                System.out.println( " Помилка! Можливі варіанти вибору - 1 і 2. " );
                continue;
            }

            scan = new Scanner(System.in);
            System.out.println( "Введіть шлях до вхідного файлу: " );
            String file = scan.nextLine();
            ArrayList<ArrayList<Integer>> matrix;
            try {
                matrix = MatrixReader.read(file);
            } catch (FileNotFoundException e) {
                System.out.println( " Помилка читання файлу! Спробуйте ще раз! " );
                continue;
            }
            showMatrix(matrix);
            String outputFile = file.substring(0, file.lastIndexOf("\\") + 1) + "output.txt";
            try {
                MatrixReader.write(outputFile, Boruvky.boruvky(matrix, type));
            } catch (IOException e) {
                System.out.println( " Помилка запису! " );
            }
            break;
        } while(true);
    }

    private static void showMatrix(ArrayList<ArrayList<Integer>> matrix) {
        System.out.println("-----------------------");
        for (ArrayList<Integer> line: matrix) {
            for (Integer num: line) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}
