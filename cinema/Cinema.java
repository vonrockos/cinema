package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // size of the cinema;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rowsTotal = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();
        char[][] cinema = new char[rowsTotal][seatsPerRow];
        for (char[] chars : cinema) {
            Arrays.fill(chars, 'S');
        }
        // menu cycle
        int action;
        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            action = scanner.nextInt();
            switch (action) {
                case 1:
                    printCinemaPlan(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 3:
                    printStatistics(cinema);
                    break;
                case 0:
                    break;
            }
        } while (action != 0);
    }
    public static void buyTicket (char[][] cinema){
        int rowsTotal = cinema.length;
        int seatsPerRow = cinema[0].length;
        Scanner scanner = new Scanner(System.in);
        // input cycle
        int row, seat;
        boolean ok;
        do {
            System.out.println();
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();
            ok = (row > 0) && (row <= rowsTotal) && (seat > 0) && (seat <= seatsPerRow);
            if (!ok) {
                System.out.println("Wrong input!");
                continue;
            }
            ok = cinema[row - 1][seat - 1] != 'B';
            if (!ok)
                System.out.println("That ticket has already been purchased!");
            } while (!ok);
        cinema[row - 1][seat - 1] = 'B';
        int seatsTotal = rowsTotal * seatsPerRow;
        int firstHalf = (seatsTotal > 60) ? rowsTotal / 2 : rowsTotal;
        int ticketPrice = (row > firstHalf) ? 8 : 10;
        System.out.print("Ticket price: ");
        System.out.println("$" + ticketPrice);
    }
    public static void printCinemaPlan (char[][] cinema) {
        int rowsTotal = cinema.length;
        int seatsPerRow = cinema[0].length;
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i <= rowsTotal; i++) {
            for (int j = 0; j <= seatsPerRow; j++) {
                if (i == 0) {
                    if (j == 0)
                        System.out.print("  ");
                    else System.out.print(j + " ");
                } else if (j == 0)
                    System.out.print(i + " ");
                else System.out.print(cinema[i-1][j-1] + " ");
            }
            System.out.println();
        }
    }
    public static void printStatistics (char[][] cinema) {
        int rowsTotal = cinema.length;
        int seatsPerRow = cinema[0].length;
        int seatsTotal = rowsTotal * seatsPerRow;
        int firstHalf = (seatsTotal > 60) ? rowsTotal / 2 : rowsTotal;
        int totalIncome = seatsPerRow * (firstHalf * 10 + (rowsTotal - firstHalf) * 8);
        int ticketsTotal = 0;
        int currentIncome = 0;
        for (int row = 0; row < rowsTotal; row++) {
            for (int seat = 0; seat < seatsPerRow; seat++) {
                if (cinema[row][seat] == 'B') {
                    ticketsTotal++;
                    currentIncome += (row > firstHalf - 1) ? 8 : 10;
                }
            }
        }
        double ticketPercent = (double) ticketsTotal / seatsTotal * 100;
        System.out.println("Number of purchased tickets: " + ticketsTotal);
        System.out.printf("Percentage: %3.2f%%%n", ticketPercent);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}


