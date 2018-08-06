import java.util.Scanner;

public class ZgadywanieLiczb2 {

    public static void main(String[] args) {

        System.out.println("Pomyśl liczbę od 1 do 1000, a ja spróbują ją zgadnąć w maksymalnie 10 próbach.");
        System.out.println("Naciśnij enter abym zaczął zgadywać...");
        waitForEnter();
        boolean isGameEnded = false;
        int min = 0;
        int max = 1000;
        int attemptCount = 1;

        while (!isGameEnded) {
            int guess = guessNumber(min, max);
            System.out.println("Zgaduję, " + attemptCount + " próba: " + guess);
            int response = checkNumber();
            if (response == -1) {
                min = guess;
            } else if (response == 1) {
                max = guess;
            } else if (response == 2){
                System.out.println("coś nie tak");
            } else {
                System.out.println("Yeeeah! Zgadłem przy " + attemptCount + " próbie.");
                isGameEnded = true;
            }
            attemptCount++;
        }


    }

    public static int checkNumber() {
        Scanner scan = new Scanner(System.in);
        boolean isLineOk = false;
        String line = "";
        while(!isLineOk) {
            line = scan.nextLine().trim();
            if (!line.equals("za mało") && !line.equals("za dużo") && !line.equals("trafiłeś")) {
                System.out.println("Nie rozumiem, napisz \"za dużo\", \"za mało\" albo \"trafiłeś\".");
                continue;
            }
            isLineOk = true;
        }

        if (line.equals("za mało")) {
            return -1;
        } else if (line.equals("za dużo")) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int guessNumber(int min, int max) {
        int number = (min + max) / 2;
        return  number;
    }

    public static void waitForEnter() {
        Scanner scan = new Scanner(System.in);
        boolean isItEnter = false;
        while(!isItEnter) {
            String line = scan.nextLine();

            if (line.equals("")) {
                return;
            } else {
                continue;
            }
        }
    }


}
