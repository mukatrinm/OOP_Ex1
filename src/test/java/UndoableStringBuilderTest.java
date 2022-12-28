import observer.UndoableStringBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class UndoableStringBuilderTest {
    StringBuilder sb;
    UndoableStringBuilder usb = new UndoableStringBuilder();

    @BeforeEach
    void setUp() {
        sb = new StringBuilder();
        usb = new UndoableStringBuilder();
    }

    @org.junit.jupiter.api.Test
    void append() {
        sb.append("to be or not to be");
        usb.append("to be or not to be");

        assertEquals(sb.toString(), usb.toString());

        sb.append("to ");
        usb.append("to ");

        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void append_empty() {
        usb.append("");

        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void delete() {
        sb.append("to be or not to be");
        usb.append("to be or not to be");

        sb.delete(0, 2);
        usb.delete(0, 2);

        assertEquals(sb.toString(), usb.toString());

        sb.delete(3, 5);
        usb.delete(3, 5);

        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void delete_start_bigger_than_end() {
        usb.append("to be or not to be");

        usb.delete(5, 2);

        assertEquals("to be or not to be", usb.toString());
    }

    @org.junit.jupiter.api.Test
    void delete_start_bigger_than_length() {
        usb.append("to be or not to be");

        usb.delete(50, 2);

        assertEquals("to be or not to be", usb.toString());
    }

    @org.junit.jupiter.api.Test
    void delete_start_negative() {
        usb.append("to be or not to be");

        usb.delete(-1, 2);

        assertEquals("to be or not to be", usb.toString());
    }

    @org.junit.jupiter.api.Test
    void delete_check_exception() {
        sb.append("to be or not to be");
        usb.append("to be or not to be");

        assertThrows(StringIndexOutOfBoundsException.class, () -> sb.delete(100, 2));

        assertDoesNotThrow(() -> {
            usb.delete(100, 2);
        });
    }

    @org.junit.jupiter.api.Test
    void insert() {
        sb.append("to be or not to be");
        usb.append("to be or not to be");

        sb.insert(1, "aa");
        usb.insert(1, "aa");

        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void insert_null() {
        usb.append("to be or not to be");

        usb.insert(1, null);

        assertEquals("tnullo be or not to be", usb.toString());
    }

    @org.junit.jupiter.api.Test
    void insert_invalid() {
        usb.append("to be or not to be");

        usb.insert(100, "aa");

        assertEquals("to be or not to be", usb.toString());
    }

    @org.junit.jupiter.api.Test
    void replace() {
        sb.append("to be or not to be");
        usb.append("to be or not to be");

        sb.replace(1, 2, "aa");
        usb.replace(1, 2, "aa");

        assertEquals(sb.toString(), usb.toString());

        sb.replace(1, 5, "abc");
        usb.replace(1, 5, "abc");

        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void replace_null() {
        usb.append("to be or not to be");

        usb.replace(1, 2, null);

        assertEquals("to be or not to be", usb.toString());
    }

    @org.junit.jupiter.api.Test
    void replace_invalid() {
        usb.append("to be or not to be");

        usb.replace(10, 2, "aa");

        assertEquals("to be or not to be", usb.toString());
    }

    @org.junit.jupiter.api.Test
    void reverse() {
        sb.reverse();
        usb.reverse();

        assertEquals(sb.toString(), usb.toString());

        sb.append("to be or not to be");
        usb.append("to be or not to be");

        sb.reverse();
        usb.reverse();

        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void reverse_empty() {
        usb.append("");

        sb.reverse();
        usb.reverse();

        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals(sb.toString(), usb.toString());

        sb.append("to be or not to be");
        usb.append("to be or not to be");

        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void testToString_empty() {
        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void undo() {
        sb.append("to be or not to be");
        usb.append("to be or not to be");

        usb.append("to to");
        usb.undo();

        assertEquals(sb.toString(), usb.toString());
    }

    @org.junit.jupiter.api.Test
    void undo_no_history() {
        usb.undo();

        assertEquals("", usb.toString());

        usb.append("to be");
        usb.append("to be or not to be");

        usb.undo();
        assertEquals("to be", usb.toString());
        usb.undo();
        assertEquals("", usb.toString());
        usb.undo();
        assertEquals("", usb.toString());
    }
}