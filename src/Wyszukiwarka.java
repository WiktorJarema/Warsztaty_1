import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;

public class Wyszukiwarka {


    public static void main(String[] args) {

        ArrayList<String> popularWords = new ArrayList<String>();
        ArrayList<String> excludedWords = new ArrayList<String>();
        excludedWords.add("oraz");
        excludedWords.add("ponieważ");
        excludedWords.add("także");
        excludedWords.add("gdzie");
        excludedWords.add("teraz");
        excludedWords.add("skąd");
        excludedWords.add("podczas");
        excludedWords.add("nieraz");
        excludedWords.add("który");
        excludedWords.add("które");
        excludedWords.add("którą");
        excludedWords.add("taki");
        excludedWords.add("takie");


        wordsFromTitles(popularWords, "http://www.wp.pl", "div._3I9Ewz");
        wordsFromTitles(popularWords, "http://www.onet.pl", "span.title");
        wordsFromTitles(popularWords, "http://www.wpolityce.pl", "span.long-title");
        writeToFile(popularWords, "src/popular_words.txt");


        ArrayList<String> wordsFromFile = new ArrayList<String>();
        readFromFile(wordsFromFile, "src/popular_words.txt");
        filterList(wordsFromFile, excludedWords);
        writeToFile(wordsFromFile, "src/filtered_popular_words.txt");

    }

    public static void wordsFromTitles(ArrayList<String> list, String url, String cssQuery) {

        Connection connection = Jsoup.connect(url);

        try {
            Document document = connection.get();
            Elements links = document.select(cssQuery);
            //list.add("Tytuły z seriwisu " + url);
            for (Element element : links) {
                //System.out.println(element.text());
                String[] words = element.text().replaceAll("[!?,.\"():;]","").split("\\s+");
                for (String word : words) {
                    if (word.length() > 3) {
                        list.add(word);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void writeToFile(ArrayList<String> list, String fileName) {
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file);
             BufferedWriter fileWriter = new BufferedWriter(writer)) {
            for (String element: list) {
                fileWriter.write(element + "\n");
            }
        } catch (IOException e) {
            System.out.println("Problem z plikiem");
        }
    }

    static void readFromFile(ArrayList<String> list, String fileName) {
        File file = new File(fileName);

        try (FileReader reader = new FileReader(file);
             BufferedReader fileReader = new BufferedReader(reader)) {
            String currentLine = null;
            while ((currentLine = fileReader.readLine()) != null) {
                list.add(currentLine);
            }
        } catch (IOException e) {
            System.out.println("Problem z plikiem");
        }
    }

    public static void filterList(ArrayList<String> wordsToBeFiltered, ArrayList<String> filter) {
        for (int i = 0; i < wordsToBeFiltered.size(); i++) {
            for (String filterWord : filter) {
                String temp = wordsToBeFiltered.get(i);
                if (temp.equals(filterWord)) {
                    wordsToBeFiltered.remove(i);
                }
            }
        }
    }

    public static void printList(ArrayList<String> list) {
        for (String element : list) {
            System.out.println(element);
        }
    }

}
