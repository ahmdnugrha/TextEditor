package PM4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
    private StringBuilder text;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    public TextEditor() {
        this.text = new StringBuilder();
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    // Fungsi untuk menampilkan teks
    public void show() {
        System.out.println("Teks saat ini: " + text.toString());
    }

    // Fungsi untuk menulis teks
    public void write(String newText) {
        undoStack.push(text.toString()); // Simpan keadaan sebelumnya
        text.append(newText);
        redoStack.clear(); // Kosongkan redo stack setelah melakukan write
    }

    // Fungsi untuk undo
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(text.toString()); // Simpan keadaan saat ini untuk redo
            text = new StringBuilder(undoStack.pop()); // Kembalikan keadaan sebelumnya
        } else {
            System.out.println("Tidak ada yang bisa di-undo.");
        }
    }

    // Fungsi untuk redo
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(text.toString()); // Simpan keadaan saat ini untuk undo
            text = new StringBuilder(redoStack.pop()); // Kembalikan keadaan yang lebih baru
        } else {
            System.out.println("Tidak ada yang bisa di-redo.");
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Selamat datang di Text Editor!");

        while (true) {
            System.out.print("Masukkan perintah (show, write, undo, redo, exit): ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "show":
                    editor.show();
                    break;
                case "write":
                    System.out.print("Masukkan teks yang ingin ditambahkan: ");
                    String newText = scanner.nextLine();
                    editor.write(newText);
                    break;
                case "undo":
                    editor.undo();
                    break;
                case "redo":
                    editor.redo();
                    break;
                case "exit":
                    System.out.println("Keluar dari Text Editor. Terima kasih!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Perintah tidak dikenali. Silakan coba lagi.");
            }
        }
    }
}

