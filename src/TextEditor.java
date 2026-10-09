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

    public void printText() {
        System.out.println("Current Text: [ " + currentText + " ]");
    }
}
