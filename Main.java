import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // create initial dictionary
        HashMap dictionary = new HashMap(100);
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
        drawLogo();
        System.out.println("\n\n\nWelcome to the Spell Checker program!");
        Scanner scanner = new Scanner(System.in);
        run(dictionary, file, scanner);
        System.out.println("Check another word? (y/n)");
        String response = scanner.nextLine();
        while (response.equals("y") || response.equals("Y")) {
            run(dictionary, file, scanner);
            System.out.println("Check another word? (y/n)");
            response = scanner.nextLine();
        }
        System.out.println("Thank you for using the Spell Checker program!\n\n");
        scanner.close();
    }

    public static void run(HashMap dictionary, File file, Scanner scanner) {
        System.out.println("\nPlease enter a word to check if it is spelled correctly.");
        String input = scanner.nextLine();
        String result = dictionary.getID(input);
        if (result == null) {
            System.out.println("\nThe word is spelled incorrectly.");
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
            System.out.println("\nThe word is spelled correctly.");
        }
    }

    public static void drawLogo() {
        System.out.println("\n\n\n");
        System.out.println(" ______     ______   ______     __         __         ______     __  __     ______     ______     __  __   ");
        System.out.println("/\\  ___\\   /\\  == \\ /\\  ___\\   /\\ \\       /\\ \\       /\\  ___\\   /\\ \\_\\ \\   /\\  ___\\   /\\  ___\\   /\\ \\/ /   ");
        System.out.println("\\ \\___  \\  \\ \\  _-/ \\ \\  __\\   \\ \\ \\____  \\ \\ \\____  \\ \\ \\____  \\ \\  __ \\  \\ \\  __\\   \\ \\ \\____  \\ \\  _\"-.");
        System.out.println(" \\/\\_____\\  \\ \\_\\    \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\");
        System.out.println("  \\/_____/   \\/_/     \\/_____/   \\/_____/   \\/_____/   \\/_____/   \\/_/\\/_/   \\/_____/   \\/_____/   \\/_/\\/_/");
        System.out.println("");
    }
}