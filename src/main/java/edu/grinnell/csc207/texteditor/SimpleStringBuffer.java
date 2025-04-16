package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    private int cursor;

    private String buffer;

    /**
     * Construct a new StringBuffer
     */
    public SimpleStringBuffer() {
        cursor = 0;
        buffer = "";
    }

    /**
     * insert char to the cursor
     *
     * @param ch a character to insert
     */
    public void insert(char ch) {
        buffer = buffer.substring(0, cursor) + ch + buffer.substring(cursor);
        cursor++;
    }

    /**
     * delete the char at the cursor
     */
    public void delete() {
        if (cursor > 0 && buffer.length() > 0) {
            buffer = buffer.substring(0, cursor - 1) + buffer.substring(cursor);
            cursor--;
        }
    }

    /**
     * get the position of the cursor
     *
     * @return an integer which is the position of the cursor
     */
    public int getCursorPosition() {
        return cursor;
    }

    /**
     * move the cursor to the left
     */
    public void moveLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    /**
     * move the cursor to the right
     */
    public void moveRight() {
        if (cursor < getSize()) {
            cursor++;
        }
    }

    /**
     * get the length of the buffer
     *
     * @return an integer which is the size of the buffer
     */
    public int getSize() {
        return buffer.length();
    }

    /**
     * get the char at position i
     *
     * @param i integer represents position
     * @return character that is at i
     */
    public char getChar(int i) {
        if (i >= 0 && i < buffer.length()) {
            return buffer.charAt(i);
        } else {
            throw new IndexOutOfBoundsException("Index " + i + " is invalid.");
        }
    }

    /**
     * convert the buffer to a String
     *
     * @return a String that is converted from buffer
     */
    public String toString() {
        return buffer;
    }
}
