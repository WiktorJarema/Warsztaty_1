import java.util.Arrays;
import java.util.Scanner;

public class SymulatorLotto_pop {

    public static void main(String[] args) {

        System.out.println("Wytypuj swoje 6 liczb:");
        int[] userLottoNumbers = userSixNumbers();
        System.out.println(Arrays.toString(userLottoNumbers));

    }

    public static int[] userSixNumbers() {
        int[] numbers = new int[6];
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Podaj "+ (i + 1) +". liczbę");
            boolean isNumberOk = false;
            while (!isNumberOk) {
                int currentNumber = userNumber();
                if (!checkIfNumberIsInRange(currentNumber)) {
                    System.out.println("Liczba nie jest z zakresu 1 - 49, podaj włąściwą liczbę.");
                    continue;
                }
                for (int j = 0; j <= i; j++) {
                    if (currentNumber == numbers[j]) {
                        System.out.println("Już wytypowałeś tę liczbę, podaj inną.");
                        isNumberOk = false;
                        break;
                    } else {
                        isNumberOk = true;
                    }
                }
                numbers[i] = currentNumber;
            }
        }
        Arrays.sort(numbers);
        return numbers;
    }

    public static int userNumber() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("To nie liczba, podaj liczbę.");
            scan.next();
        }
        int number = scan.nextInt();
        return number;
    }

    public static boolean checkIfNumberIsInRange (int number) {
        return (number >= 1 && number <= 49);
    }


//    public static int[] userSixNumbers() {
//        int[] numbers = new int[6];
//        for (int i = 0; i < numbers.length; i++) {
//            System.out.println("Podaj "+ (i + 1) +". liczbę");
//            boolean isNumberOk = true;
//            while (isNumberOk) {
//                int currentNumber = userNumber();
//                if (!checkIfNumberIsInRange(currentNumber)) {
//                    System.out.println("Liczba nie jest z zakresu 1 - 49.");
//                    break;
//                }
//                numbers[i] = currentNumber;
//                isNumberOk = false;
//            }
//        }
//        return numbers;
//    }

}
