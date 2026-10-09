import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor();

        System.out.println("=== Simple Text Editor ===");
        System.out.println("Commands: write (:w) <text> | delete (:d) <count|word> | find (:f) <word> | replace (:r) <old> <new> | undo (:z) | redo (:y) | exit");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+", 2);
            String command = tokens[0].toLowerCase();
            String argsText = tokens.length > 1 ? tokens[1].trim() : "";

            if (command.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (command.equals("undo") || command.equals(":z")) {
                editor.undo();
            } else if (command.equals("redo") || command.equals(":y")) {
                editor.redo();
            } else if (command.equals("write") || command.equals(":w")) {
                if (!argsText.isEmpty()) {
                    editor.write(argsText);
                } else {
                    System.out.println("Usage: write <text> or :w <text>");
                }
            } else if (command.equals("find") || command.equals(":f")) {
                if (!argsText.isEmpty()) {
                    editor.find(argsText);
                } else {
                    System.out.println("Usage: find <word> or :f <word>");
                }
            } else if (command.equals("replace") || command.equals(":r")) {
                String[] parts = argsText.split("\\s+", 2);
                if (parts.length == 2) {
                    editor.replace(parts[0], parts[1]);
                } else {
                    System.out.println("Usage: replace <target> <replacement> or :r <target> <replacement>");
                }
            } else if (command.equals("delete") || command.equals(":d")) {
                if (!argsText.isEmpty()) {
                    try {
                        int count = Integer.parseInt(argsText);
                        editor.delete(count);
                    } catch (NumberFormatException e) {
                        editor.delete(argsText);
                    }
                } else {
                    System.out.println("Usage: delete <number|word> or :d <number|word>");
                }
            } else {
                System.out.println("Unknown command! Try again.");
            }

            editor.printText();
        }

        scanner.close();
    }
}