package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    private int cursor;
    private String buffer;
    public SimpleStringBuffer(){
        cursor = 0;
        buffer = "";
    }
    public void insert(char ch) {
        buffer = buffer.substring(0, cursor) + ch + buffer.substring(cursor);
        cursor++;
    }

    public void delete() {
        if (cursor > 0){
            buffer = buffer.substring(0, cursor - 1) + buffer.substring(cursor);
            cursor--;
        }
    }

    public int getCursorPosition() {
        return cursor;
    }

    public void moveLeft() {
        if (cursor > 0){
            cursor--;
        }
    }

    public void moveRight() {
        if (cursor < buffer.length()){
            cursor++;
        }
    }

    public int getSize() {
        return buffer.length();
    }

    public char getChar(int i) {
        if (i >= 0 && i < buffer.length()){
            return buffer.charAt(i);
        } else {
            throw new IndexOutOfBoundsException("Index " + i + " is invalid.");
        }
    }

    @Override
    public String toString() {
        return buffer.substring(0, cursor) + (char) 219 + buffer.substring(cursor);
    }
}
