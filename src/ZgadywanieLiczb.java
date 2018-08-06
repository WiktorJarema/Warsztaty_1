import java.util.Random;
import java.util.Scanner;

public class ZgadywanieLiczb {

    public static void main(String[] args) {

        int numberToBeGuessed = randomNumber(0, 100);
        //System.out.println(guessNum);

        boolean numberGuessed = false;
        System.out.println("Spróbuj zgadnąć liczbę z zakresu od 0 do 100...");
        while(!numberGuessed){
            int num = askForNumber();
            if (checkNumber(numberToBeGuessed, num) == true) {
                numberGuessed = true;
            }
        }
        System.out.println("Koniec gry.");
    }

    // returns random number from <min, max>
    public static int randomNumber(int min, int max) {

        Random rand = new Random();
        int randumNumberBetweenMinAndMaxInclusive = rand.nextInt((max - min) + 1) + min;
        return randumNumberBetweenMinAndMaxInclusive;

    }

    public static boolean checkNumber(int numberToBeGuessed, int number) {

        if (number < numberToBeGuessed) {
            System.out.println("Za mało!");
            return false;
        } else if (number > numberToBeGuessed) {
            System.out.println("Za dużo!");
            return false;
        } else {
            System.out.println("Zgadłeś, BRAWO!");
            return true;
        }

    }

    public static int askForNumber() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("To nie jest liczba, wprowadź liczbę!");
            scan.next();
        }
        int number = scan.nextInt();
        return number;
    }


}
