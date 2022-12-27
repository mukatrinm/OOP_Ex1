package observer;

import java.util.Stack;

/*
Use the class you've implemented in previous assignment
 */
public class UndoableStringBuilder {
    private final Stack<StringBuilder> history; // a stack to save previous versions of the string builder.
    private StringBuilder sb;

    /**
     * Constructs a history stack and a string builder with no characters in it and an initial capacity of 16 characters.
     */
    public UndoableStringBuilder() {
        sb = new StringBuilder();
        history = new Stack<>();
    }

    /**
     * Appends the specified string to this character sequence.
     *
     * @param str a string
     * @return a reference to this object.
     */
    public UndoableStringBuilder append(String str) {
        sb.append(str);
        history.push(new StringBuilder(sb));

        return this;
    }

    /**
     * Removes the characters in a substring of this sequence. The substring begins at the specified
     * start and extends to the character at index end - 1 or to the end of the sequence if no
     * such character exists. If start is equal to end, no changes are made.
     *
     * @param start The beginning index, inclusive.
     * @param end   The ending index, exclusive.
     * @return This object.
     */
    public UndoableStringBuilder delete(int start, int end) {
        try {
            sb.delete(start, end);
            history.push(new StringBuilder(sb));
        } catch (StringIndexOutOfBoundsException ex) {
            if (start < 0)
                System.err.println("Start index is negetive");
            else if (end > this.sb.length())
                System.err.println("End index is greater then the length of the string");
            else if (start > end)
                System.err.println("Start index is greater then end index");
        }

        return this;
    }

    /**
     * Inserts the string into this character sequence.
     *
     * @param offset the offset.
     * @param str    a string.
     * @return a reference to this object.
     */
    public UndoableStringBuilder insert(int offset, String str) {
        try {
            sb.insert(offset, str);
            history.push(new StringBuilder(sb));
        } catch (StringIndexOutOfBoundsException ex) {
            if (offset < 0)
                System.err.println("Offset can not be negative");
            else if (offset > str.length() - 1)
                System.err.println("Offset can not be greater then the last index");
        }

        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in the specified String.
     * The substring begins at the specified start and extends to the character at index end - 1 or to the
     * end of the sequence if no such character exists. First the characters in the substring are removed and then
     * the specified String is inserted at start. (This sequence will be lengthened to accommodate the specified
     * String if necessary.)
     *
     * @param start The beginning index, inclusive.
     * @param end   The ending index, exclusive.
     * @param str   String that will replace previous contents.
     * @return This object.
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        try {
            sb.replace(start, end, str);
            history.push(new StringBuilder(sb));
        } catch (StringIndexOutOfBoundsException ex) {
            if (start < 0)
                System.err.println("Start index is negative");
            else if (end > this.sb.length())
                System.err.println("End index is greater then the length of the string");
            else if (start > end)
                System.err.println("Start index is greater then end index");
        } catch (NullPointerException ex) {
            System.out.println("null string");
        }

        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the sequence.
     *
     * @return a reference to this object.
     */
    public UndoableStringBuilder reverse() {
        sb.reverse();
        history.push(new StringBuilder(sb));

        return this;
    }

    /**
     * Returns a string representing the data in this sequence. A new String object is allocated and initialized to
     * contain the character sequence currently represented by this object. This String is then returned.
     * Subsequent changes to this sequence do not affect the contents of the String.
     *
     * @return a string representation of this sequence of characters.
     */
    @Override
    public String toString() {
        return sb.toString();
    }

    /**
     * the object sb will hold the previous state of the string. if there is no previous state it will be an empty string
     */
    public void undo() {
        if (!history.empty()) {
            history.pop();

            // need to check if history is empty again because we want to get the top of the stack after popping.
            if (!history.empty())
                sb = history.peek();
            else
                sb = new StringBuilder();
        }
    }
}