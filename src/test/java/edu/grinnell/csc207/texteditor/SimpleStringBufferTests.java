package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleStringBufferTests {

    /**
     * TODO: fill me in with unit and property tests!
     */
    @Test
    public void InsertSize() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        assertEquals(2, buffer.getSize());
    }

    @Test
    public void InsertString() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        assertEquals("ab", buffer.toString());
    }

    @Test
    public void MoveLeft() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveLeft();
        assertEquals(1, buffer.getCursorPosition());
    }

    @Test
    public void DeleteNothing() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveLeft();
        buffer.moveLeft();
        buffer.delete();
        assertEquals(0, buffer.getCursorPosition());
        assertEquals("ab", buffer.toString());
    }

    @Test
    public void DeleteAll() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.delete();
        buffer.delete();
        assertEquals(0, buffer.getCursorPosition());
    }

    @Test
    public void DeleteOnEmptyBuffer() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.delete();
        assertEquals("", buffer.toString());
        assertEquals(0, buffer.getCursorPosition());
    }

    @Test
    public void MoveRight() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveLeft();
        buffer.moveRight();
        assertEquals(2, buffer.getCursorPosition());
    }

    @Test
    public void EmptyBuffer() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        assertEquals("", buffer.toString());
        assertEquals(0, buffer.getCursorPosition());
    }

    @Test
    public void GetCharAtValidIndex() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('x');
        buffer.insert('y');
        buffer.insert('z');
        assertEquals('y', buffer.getChar(1));
    }

    @Test
    public void GetCharAtInvalidIndex() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('x');
        assertThrows(IndexOutOfBoundsException.class, () -> {buffer.getChar(5);});
    }
    
    @Property
    public boolean CursorPosition(@ForAll @IntRange(min = 2, max = 1000) int sz) {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        for (int i = 0; i < sz; i++) {
            buffer.insert('a');
        }
        buffer.moveLeft();
        buffer.moveLeft();
        return buffer.getCursorPosition() == sz - 2;
    }

}
