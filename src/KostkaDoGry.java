import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KostkaDoGry {

    public static void main(String[] args) {

        String diceCode = askForDiceCode();
        //System.out.println(diceCode);
        int rollTheDiceResult = rollTheDice(diceCode);

    }

    public static String askForDiceCode() {
        Pattern pattern = Pattern.compile("[2-9]?D(3|4|6|8|10|12|20|100)((\\-[1-9]|\\-[1-9][0-9]|\\-100)|(\\+[1-9]|\\+[1-9][0-9]|\\+100))?");

        Scanner scan = new Scanner(System.in);
        String currentLine = "";
        boolean isTheCodeOk = false;
        System.out.println("Wprowadź kod rzutu kostką postaci xDy+z, gdzie:");
        System.out.println("x (2-9) – liczba rzutów kośćmi (jeśli rzucamy raz, ten parametr jest pomijalny)");
        System.out.println("y – rodzaj kostek, których należy użyć (np. D6, D10), typy dostępnych kostek: D3, D4, D6, D8, D10, D12, D20, D100");
        System.out.println("z – (opcjonalnie) liczba, którą należy dodać (lub odjąć) do wyniku rzutów");
        while(!isTheCodeOk) {
            currentLine = scan.nextLine().trim();
            Matcher matcher = pattern.matcher(currentLine);
            if (matcher.matches()) {
                isTheCodeOk = true;
                //System.out.println("Kod poprawny.");
            } else {
                System.out.println("Niepoprawny kod, spróbuj jeszcze raz:");
            }
        }
        return currentLine;
    }

    public static int rollTheDice(String diceCode) {

        // determining number of dice rolls
        int numberOfRolls;
        String[] numberOfRollsArray = diceCode.split("D.+");
        if (numberOfRollsArray.length == 1) {
            numberOfRolls = Integer.parseInt(numberOfRollsArray[0]);
        } else {
            numberOfRolls = 1;
        }

        // determining type of the dice
        String[] typeOfDiceArray = diceCode.split("\\d?D|\\+.?.?.?|\\-.?.?.?");
        int typeOfDice = Integer.parseInt(typeOfDiceArray[1]);

        // determining additional amount to add or subtract
        int additionalAmount = 0;
        Pattern additionPattern = Pattern.compile("\\+");
        Pattern subtractionPattern = Pattern.compile("\\-");

        Matcher additionMatcher = additionPattern.matcher(diceCode);
        Matcher subtractionMatcher = subtractionPattern.matcher(diceCode);

        String[] additionalAmountArray;
        int additionOrSubtractionOrNothing = 0;
        if (additionMatcher.find()) {
            additionalAmountArray = diceCode.split("\\+");
            additionalAmount = Integer.parseInt(additionalAmountArray[1]);
            additionOrSubtractionOrNothing = 1;
        } else if (subtractionMatcher.find()) {
            additionalAmountArray = diceCode.split("\\-");
            additionalAmount = Integer.parseInt("-" + additionalAmountArray[1]);
            additionOrSubtractionOrNothing = -1;
        }

        // calculating the result of dice roll
        int result = 0;
        for (int i = 0; i < numberOfRolls; i++) {
            int currentRandomNumber = randomNumber(typeOfDice);
            result += currentRandomNumber;
            System.out.println((i +1) + " rzut kostką: " + currentRandomNumber);
        }


        if (additionOrSubtractionOrNothing == -1) {
            System.out.println("Wynik rzutów: " + result);
            System.out.println("Odjąć " + Math.abs(additionalAmount));
        } else if (additionOrSubtractionOrNothing == 1) {
            System.out.println("Wynik rzutów: " + result);
            System.out.println("Dodać " + additionalAmount);
        }

        result += additionalAmount;
        System.out.println("Ostateczny wynik rzutu kostką: " + result);
        return result;
    }

    public static int randomNumber(int max) {

        Random rand = new Random();
        int randumNumberMaxInclusive = rand.nextInt(max) + 1;
        return randumNumberMaxInclusive;

    }

}
