import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // create initial dictionary
        HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
        File file = new File("./data/dictionary.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                dictionary.put(data, 0);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // main program intro
        System.out.println("Welcome to the Spell Checker program!");
        System.out.println("Please enter a word to check if it is spelled correctly.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Integer result = dictionary.get(input);
        if (result == null) {
            System.out.println("The word is spelled incorrectly.");
            
            String[] suggestions = new String[5];
            dictionary.forEach((k, v) -> {
                int distance = Distance.compute(input, k);
                dictionary.put(k, distance);
                if (distance < 3) {
                    for (int i = 0; i < 5; i++) {
                        if (suggestions[i] == null) {
                            suggestions[i] = k;
                            break;
                        } else if (distance < dictionary.get(suggestions[i])) {
                            for (int j = 4; j > i; j--) {
                                suggestions[j] = suggestions[j - 1];
                            }
                            suggestions[i] = k;
                            break;
                        }
                    }
                }
            });
            System.out.print("Did you mean: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(suggestions[i] + ",");
            }
            System.out.println("\n");
        } else {
            System.out.println("The word is spelled correctly.");
        }
        scanner.close();


        
    }
}