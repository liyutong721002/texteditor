package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    private int cursor;
    private String buffer;

    public SimpleStringBuffer() {
        cursor = 0;
        buffer = "";
    }

    public void insert(char ch) {
        String temp = "";
        for (int i = 0; i < cursor; i++) {
            temp += buffer.charAt(i);
        }
        temp += ch;
        for (int i = cursor + 1; i < buffer.length(); i++) {
            temp += buffer.charAt(i);
        }
        cursor++;
        buffer = temp;
    }

    public void delete() {
        if (cursor > 0) {
            String temp = "";
            for (int i = 0; i < cursor - 1; i++) {
                temp += buffer.charAt(i);
            }
            for (int i = cursor; i < buffer.length(); i++) {
                temp += buffer.charAt(i);
            }
            buffer = temp;
            cursor--;
        }
    }

    public int getCursorPosition() {
        return cursor;
    }

    public void moveLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    public void moveRight() {
        if (cursor < buffer.length()) {
            cursor++;
        }
    }

    public int getSize() {
        return buffer.length();
    }

    public char getChar(int i) {
        if (i >= 0 && i < buffer.length()) {
            return buffer.charAt(i);
        } else {
            throw new IndexOutOfBoundsException("Index " + i + " is invalid.");
        }
    }

    @Override
    public String toString() {
        return buffer;
    }
}
