import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SymulatorLotto {

    public static void main(String[] args) {

        System.out.println("Witaj w LOTTO. Wytypuj swoje 6 liczb z zakresu od 1 do 49.");

        int[] userLottoNumbers = userNumbers(6);
        System.out.println("Twoje liczby to:");
        System.out.println(Arrays.toString(userLottoNumbers));

        int[] lottoNumbers = randomNumbes(6);
        System.out.println();
        System.out.println("Wylosowane liczby to:");
        System.out.println(Arrays.toString(lottoNumbers));
        System.out.println();

        int result = checkResult(lottoNumbers, userLottoNumbers);
        if(result > 3) {
            System.out.println("Trafiłeś " + result + " liczby! Gratulacje!");
        } else {
            System.out.println("Niestety nic nie wygrałeś.");
        }


    }


    public static int[] userNumbers(int howManyNumbers) {

        int[] userNumbers = new int[howManyNumbers];

        for (int i = 0; i < userNumbers.length; i++) {
            System.out.println("Podaj " + (i + 1) + " liczbę:");
            boolean isNumberOk = false;
            while(!isNumberOk) {
                int currentNumber = askForNumber();
                if (!checkIfNumberIsInRange(currentNumber)) {
                    System.out.println("Podana liczba nie jest z zakresu 1-49, podaj właściwą liczbę.");
                    continue;
                }
                if (checkIfNumberIsRepeated(currentNumber, userNumbers)) {
                    System.out.println("Ta liczba została już podana, podaj inną liczbę.");
                    continue;
                }
                isNumberOk = true;
                userNumbers[i] = currentNumber;
            }
        }

        Arrays.sort(userNumbers);
        return userNumbers;

    }


    public static int randomNumber(int min, int max) {

        Random rand = new Random();
        int randumNumberBetweenMinAndMaxInclusive = rand.nextInt((max - min) + 1) + min;
        return randumNumberBetweenMinAndMaxInclusive;

    }


    public static int askForNumber() {

        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("To nie jest liczba, podaj liczbę!");
            scan.next();
        }
        int number = scan.nextInt();
        return number;

    }


    public static boolean checkIfNumberIsInRange(int number) {

        return (number >= 1 && number <= 49);

    }


    public static boolean checkIfNumberIsRepeated(int number, int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                return true;
            }
        }
        return false;

    }

    public static int[] randomNumbes(int howManyNumbers) {
        int[] numbers = new int[howManyNumbers];
        for (int i = 0; i < numbers.length; i++) {
            boolean isNumberOk = false;
            while(!isNumberOk) {
                int currentNumber = randomNumber(1, 49);
                if(checkIfNumberIsRepeated(currentNumber, numbers)) {
                    continue;
                }
                isNumberOk = true;
                numbers[i] = currentNumber;
            }
        }
        Arrays.sort(numbers);
        return numbers;
    }

    public static int checkResult(int[] lottoNumbers, int[] userLottoNumbers) {

        int count = 0;

        for (int i = 0; i < lottoNumbers.length; i++) {
            for (int j = 0; j < userLottoNumbers.length; j++) {
                if(lottoNumbers[i] == userLottoNumbers[j]) {
                        count++;
                }
            }
        }

        return count;

    }

}
