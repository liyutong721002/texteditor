package edu.grinnell.csc207.texteditor;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class GapBufferTests {

    /**
     * TODO: fill me in with unit and property tests!
     */
    @Test
    public void InsertSize() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        assertEquals(2, buffer.getSize());
    }

    @Test
    public void InsertString() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        assertEquals("ab", buffer.toString());
    }

     @Test
    public void InsertMiddle() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        assertEquals(3, buffer.getSize());
        buffer.moveLeft(); 
        assertEquals(2, buffer.getCursorPosition());
        buffer.insert('u'); 
        assertEquals("abuc", buffer.toString());
    }
    
    @Test
    public void InsertMoreThanGap() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        buffer.insert('d');
        buffer.insert('e');
        buffer.insert('f');
        assertEquals("abcdef", buffer.toString());
    }

    @Test
    public void MoveLeft() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveLeft();
        assertEquals(1, buffer.getCursorPosition());
    }

    @Test
    public void DeleteNothing() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveLeft();
        buffer.moveLeft();
        buffer.delete();
        assertEquals(0, buffer.getCursorPosition());
        assertEquals("ab", buffer.toString());
    }

    @Test
    public void DeleteMiddle() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        buffer.moveLeft(); 
        assertEquals(2, buffer.getCursorPosition());
        buffer.delete(); 
        assertEquals(1, buffer.getCursorPosition());
        assertEquals("ac", buffer.toString());
    }

    @Test
    public void DeleteAll() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.delete();
        buffer.delete();
        assertEquals(0, buffer.getCursorPosition());
        assertEquals("", buffer.toString());
    }

    @Test
    public void Delete() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.delete();
        assertEquals(1, buffer.getCursorPosition());
        assertEquals("a", buffer.toString());
    }

    @Test
    public void DeleteOnEmptyBuffer() {
        GapBuffer buffer = new GapBuffer();
        buffer.delete();
        assertEquals("", buffer.toString());
        assertEquals(0, buffer.getCursorPosition());
    }

    @Test
    public void MoveRight() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveLeft();
        buffer.moveRight();
        assertEquals(2, buffer.getCursorPosition());
    }

    @Test
    public void EmptyBuffer() {
        GapBuffer buffer = new GapBuffer();
        assertEquals("", buffer.toString());
        assertEquals(0, buffer.getCursorPosition());
    }

    @Test
    public void GetCharAtValidIndex() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('x');
        buffer.insert('y');
        buffer.insert('z');
        assertEquals('y', buffer.getChar(1));
    }

    @Test
    public void GetCharAtInvalidIndex() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('x');
        assertThrows(IndexOutOfBoundsException.class, () -> {
            buffer.getChar(5);
        });
    }

    @Property
    public boolean CursorPosition(@ForAll @IntRange(min = 2, max = 1000) int sz) {
        GapBuffer buffer = new GapBuffer();
        for (int i = 0; i < sz; i++) {
            buffer.insert('a');
        }
        buffer.moveLeft();
        buffer.moveLeft();
        return buffer.getCursorPosition() == sz - 2;
    }

}
