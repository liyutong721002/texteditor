package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    private int gapStart;

    private int gapEnd;

    private char[] buffer;

    /**
     * Construct a new GapBuffer
     */
    public GapBuffer() {
        buffer = new char[10];
        gapStart = 0;
        gapEnd = 3;
    }

    /**
     * expand capacity
     */
    public void expand() {
        char[] temp = new char[buffer.length * 2];
        System.arraycopy(buffer, 0, temp, 0, gapStart);
        System.arraycopy(buffer, gapEnd, temp,
                temp.length - buffer.length + gapEnd, buffer.length - gapEnd);
        gapEnd = temp.length - buffer.length + gapEnd;
        buffer = temp;
    }

    /**
     * insert char to the cursor
     *
     * @param ch a character to insert
     */
    public void insert(char ch) {
        if (gapStart == gapEnd) {
            expand();
        }
        buffer[gapStart] = ch;
        gapStart++;

    }

    /**
     * delete the char at the cursor
     */
    public void delete() {
        if (gapStart > 0) {
            gapStart--;
            buffer[gapStart] = '\0';
        }
    }

    /**
     * get the position of the cursor
     *
     * @return an integer which is the position of the cursor
     */
    public int getCursorPosition() {
        return gapStart;
    }

    /**
     * move the cursor to the left
     */
    public void moveLeft() {
        if (gapStart > 0) {
            gapStart--;
            buffer[gapEnd] = buffer[gapStart];
            gapEnd--;
            buffer[gapStart] = '\0';
        }
    }

    /**
     * move the cursor to the right
     */
    public void moveRight() {
        if (gapStart < getSize()) {
            gapEnd++;
            buffer[gapStart] = buffer[gapEnd];
            gapStart++;
            buffer[gapEnd] = '\0';
        }
    }

    /**
     * get the length of the buffer
     *
     * @return an integer which is the size of the buffer
     */
    public int getSize() {
        int size = 0;
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] != '\0') {
                size++;
            }
        }
        return size;
    }

    /**
     * get the char at position i
     *
     * @param i integer represents position
     * @return character that is at i
     */
    public char getChar(int i) {
        char ch;
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException("Index " + i + " is invalid.");
        }
        if (i < gapStart) {
            ch = buffer[i];
        } else {
            ch = buffer[i + gapEnd - gapStart];
        }
        return ch;
    }

    /**
     * convert the buffer to a String
     *
     * @return a String that is converted from buffer
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] != '\0') {
                result += buffer[i];
            }
        }
        return result;
    }
}
