public class TextEditor {
    private StringBuilder currentText;
    private MyStack undoStack;
    private MyStack redoStack;

    public TextEditor() {
        this.currentText= new StringBuilder();
        this.undoStack = new MyStack();
        this.redoStack = new MyStack();
    }

    public void write(String newText) {
        undoStack.push(String.valueOf(currentText));
        if (!currentText.isEmpty()) {
            currentText.append(" ");
        }
        currentText.append(newText);
        redoStack.clear();
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo!");
            return;
        }
        redoStack.push(String.valueOf(currentText));
        currentText.setLength(0);
        currentText.append(undoStack.pop());
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo!");
            return;
        }
        undoStack.push(String.valueOf(currentText));
        currentText.setLength(0);
        currentText.append(redoStack.pop());
    }

    public void find (String word) {
        int index = currentText.indexOf(word);

        if (index != -1) System.out.println(word + " Found at: " +index);

        else System.out.println("NOT found");
    }

    public void replace (String target, String replacement) {
        int index = currentText.indexOf(target);

        if (index == -1 ) {
            System.out.println(target+" NOT found");
            return;
        }

        undoStack.push(currentText.toString());
        currentText.replace(index, index + target.length(), replacement);
        redoStack.clear();;
    }

    public void delete(int count) {
        if (currentText.isEmpty() || count <= 0) {
            System.out.println("Nothing to delete!!");
            return;
        }

        undoStack.push(currentText.toString());
        int start = Math.max(0, currentText.length() - count);
        int end = currentText.length();

        currentText.delete(start, end);
        redoStack.clear();
    }

    public void delete (String word) {
        int index = currentText.indexOf(word);

        if (index != -1) {
            undoStack.push(currentText.toString());
            int start = index;
            int end = index + word.length();

            currentText.delete(start, end);
            redoStack.clear();
        }

        else System.out.println("The word does NOT exist");
    }

    public void printText() {
        System.out.println("Current Text: [ " + currentText + " ]");
    }
}
