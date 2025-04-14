package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TerminalPosition;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {

        screen.clear();

        int row = 0;
        int col = 0;

        for (int i = 0; i < buf.getSize(); i++) {
            char ch = buf.getChar(i);
            if (ch == '\n') {
                row++;
                col = 0;
            } else if (i == buf.getCursorPosition()) {
                TerminalPosition pos = new TerminalPosition(col, row);
                screen.setCursorPosition(pos);
            } else if (ch != '\0') {
                screen.setCharacter(col, row, TextCharacter.fromCharacter(ch)[0]);
                col++;
            }
        }
        if (buf.getCursorPosition() == buf.getSize()) {
            TerminalPosition pos = new TerminalPosition(col, row);
            screen.setCursorPosition(pos);
        }

        screen.refresh();

    }

    /**
     * The main entry point for the TextEditor application.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        // TODO: fill me in with a text editor TUI!
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        Screen screen = factory.createScreen();
        screen.startScreen();

        String path = args[0];
        System.out.format("Loading %s...\n", path);

        Path fp = Paths.get(path);
        String content = "";

        if (Files.exists(fp) && Files.isRegularFile(fp)) {
            content = Files.readString(fp);
        }

        GapBuffer buffer = new GapBuffer();
        for (int i = 0; i < content.length(); i++) {
            buffer.insert(content.charAt(i));
        }
        drawBuffer(buffer, screen);

        boolean isRunning = true;

        while (isRunning) {
            KeyStroke stroke = screen.readInput();
            KeyType keyType = stroke.getKeyType();

            switch (keyType) {
                case Character:
                    buffer.insert(stroke.getCharacter());
                    break;
                case ArrowLeft:
                    buffer.moveLeft();
                    break;
                case ArrowRight:
                    buffer.moveRight();
                    break;
                case Backspace:
                    buffer.delete();
                    break;
                case Escape:
                    isRunning = false;
                    break;
                default:
                    break;
            }

            drawBuffer(buffer, screen);
        }

        Files.writeString(fp, buffer.toString());
        screen.stopScreen();
    }
}
