/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datereader;

import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class DateReader {

    private int dayofMonth;
    private int year;
    private int month;
    File nDate;
    File wDate;
    BufferedWriter output;
    LocalDate date;
    Scanner sc;
    ArrayList<String> DATES;

    public DateReader() {
        try {
            nDate = new File("C:\\Users\\jasg0\\Downloads\\NDates");
            sc = new Scanner(nDate);
        } catch (FileNotFoundException e) {
            System.out.println("FILE NDate NOT FOUND. MAKE SURE IT IS IN YOUR DOWNLOADS FOLDER!");
        }
        wDate = new File("wDate.txt");
        output = null;
        DATES = new ArrayList<>();
    }

    public void fileReadTest() throws FileNotFoundException {
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        sc = new Scanner(nDate);
    }

    public void getDateInfo() {
        ArrayList<String> dates = new ArrayList<>();
        String num = "";
        int numsPassed = 0;
        while (sc.hasNextLine()) {
            dates.add(sc.nextLine());
        }
        for (int j = 0; j < dates.size(); j++) {
            String date = dates.get(j);
            for (int i = 0; i < date.length(); i++) {
                if (i == 2 || i == 5) {
                    num = "";
                    continue;
                }
                num += date.substring(i, i + 1);
                if (num.length() == 2 || num.length() == 4) {
                    switch (numsPassed) {
                        case 0:
                            month = Integer.parseInt(num);
                            break;
                        case 1:
                            dayofMonth = Integer.parseInt(num);
                            break;
                        default:
                            year = Integer.parseInt(num);
                            break;
                    }
                    numsPassed++;
                }
            }
            numsPassed = 0;
            makeCalendar(month, dayofMonth, year);
            num = "";
        }
    }

    public void makeCalendar(int M, int D, int Y) {
        date = LocalDate.of(Y, M, D);
        String Dates = date.getDayOfWeek() + "\t" + date.getMonth() + " " + D + ", " + Y;
        System.out.println(Dates);
        DATES.add(Dates);
    }

    public static void main(String[] args) throws Exception {
        DateReader dr = new DateReader();
        dr.fileReadTest();
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("");
        dr.getDateInfo();
        String d = "";
        dr.output = new BufferedWriter(new FileWriter(dr.wDate));
        for (int i = 0; i < dr.DATES.size(); i++) {
            d = dr.DATES.get(i);
            dr.output.write(d);
            dr.output.newLine();
        }
        dr.output.close();
    }

}
