public class TextEditor {
    private String currentText;
    private MyStack undoStack;
    private MyStack redoStack;

    public TextEditor() {
        this.currentText="";
        this.undoStack = new MyStack();
        this.redoStack = new MyStack();
    }

    public void write(String newText) {
        undoStack.push(currentText);
        if (!currentText.isEmpty()) {
            currentText += " ";
        }
        currentText += newText;
        redoStack.clear();
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo!");
            return;
        }
        redoStack.push(currentText);
        currentText = undoStack.pop();
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo!");
            return;
        }
        undoStack.push(currentText);
        currentText = redoStack.pop();
    }

    public void printText() {
        System.out.println("Current Text: [ " + currentText + " ]");
    }
}
