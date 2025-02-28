package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    private int gapStart;
    private int gapEnd;
    private char[] buffer;

    public GapBuffer() {
        buffer = new char[10];
        gapStart = 0;
        gapEnd = 3;
    }

    public void expand() {
        char[] temp = new char[buffer.length * 2];
        System.arraycopy(buffer, 0, temp, 0, gapStart);
        System.arraycopy(buffer, gapEnd, temp, temp.length - buffer.length + gapEnd, buffer.length - gapEnd);
        gapEnd = temp.length - buffer.length + gapEnd;
        buffer = temp;
    }

    public void insert(char ch) {
        if (gapStart == gapEnd) {
            expand();
        }
        buffer[gapStart] = ch;
        gapStart++;

    }

    public void delete() {
        if (gapStart > 0) {
            gapStart--;
            buffer[gapStart] = '\0';
        }
    }

    public int getCursorPosition() {
        return gapStart;
    }

    public void moveLeft() {
        if (gapStart > 0) {
            gapStart--;
            gapEnd--;
            buffer[gapEnd] = buffer[gapStart];
            buffer[gapStart] = '\0';
        }
    }

    public void moveRight() {
        if (gapEnd < buffer.length) {
            buffer[gapStart] = buffer[gapEnd];
            gapStart++;
            buffer[gapEnd] = '\0';
            gapEnd++;
        }
    }

    public int getSize() {
        int size = 0;
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] != '\0') {
                size++;
            }
        }
        return size;
    }

    public char getChar(int i) {
        char ch;
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException("Index " + i + " is invalid.");
        }
        if (i < gapStart) {
            ch = buffer[i];
        } else {
            ch = buffer[i + gapStart - gapEnd];
        }
        return ch;
    }

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
