import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor();

        System.out.println("=== Simple Text Editor ===");
        System.out.println("Commands: write <text> | undo (or :z) | redo (or :y) | exit");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("undo") || input.equalsIgnoreCase(":z")) {
                editor.undo();
            } else if (input.equalsIgnoreCase("redo") || input.equalsIgnoreCase(":y")) {
                editor.redo();
            } else if (input.toLowerCase().startsWith("write ")) {
                String textToAdd = input.substring(6);
                editor.write(textToAdd);
            } else {
                System.out.println("Unknown command! Try again.");
            }

            editor.printText();
        }

        scanner.close();
    }
}