package observer;

import java.util.Stack;
/**
 * StringBuilder with undo support
 * java.lang.StringBuilder - class with modifier <b>final</b>,
 * so no inheritance, use delegation.
 */
interface Action{
    void undo();
}

public class UndoableStringBuilder {


    private StringBuilder stringBuilder; // delegate
    /**
     * Operations that are the reverse of those performed.
     * That is, when append is called, it is placed on the stack
     * "delete" operation. When calling undo() it
     * will be executed.    */
    private Stack<Action> actions = new Stack<>();

    // constructor
    public UndoableStringBuilder() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the sequence.
     *
     * @return a reference to this object.
     */
    public UndoableStringBuilder reverse() {
        stringBuilder.reverse();
        Action action = new Action(){
            public void undo() {
                stringBuilder.reverse();
            }
        };
        actions.add(action);
        return this;
    }

    /**
     * Appends the specified string to this character sequence.
     *
     * @param str a string
     * @return a reference to this object.
     */
    public UndoableStringBuilder append(String str) {
        stringBuilder.append(str);

        Action action = new Action(){
            public void undo() {
                stringBuilder.delete(
                        stringBuilder.length() - str.length(),
                        stringBuilder.length());
            }
        };
        actions.add(action);
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
            stringBuilder.insert(offset, str);
            Action action = new Action() {
                public void undo() {
                    stringBuilder.delete(offset, offset + str.length());
                }
            };
            actions.add(action);
        } catch (StringIndexOutOfBoundsException ex) {
            if (offset < 0)
                System.err.println("Offset can not be negative");
            else if (offset > str.length() - 1)
                System.err.println("Offset can not be greater then the last index");
        }
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
            String deleted = stringBuilder.substring(start, end);
            stringBuilder.delete(start, end);
            Action action = new Action() {
                public void undo() {
                    stringBuilder.insert(start, deleted);
                }
            };
            actions.add(action);
        } catch (StringIndexOutOfBoundsException ex) {
            if (start < 0)
                System.err.println("Start index is negetive");
            else if (end > this.stringBuilder.length())
                System.err.println("End index is greater then the length of the string");
            else if (start > end)
                System.err.println("Start index is greater then end index");
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
            String deleted = stringBuilder.substring(start, end);
            stringBuilder.replace(start, end, str);
            Action action = new Action() {
                public void undo() {
                    stringBuilder.replace(start, start + str.length(), deleted);
                }
            };
            actions.add(action);
        } catch (StringIndexOutOfBoundsException ex) {
            if (start < 0)
                System.err.println("Start index is negative");
            else if (end > this.stringBuilder.length())
                System.err.println("End index is greater then the length of the string");
            else if (start > end)
                System.err.println("Start index is greater then end index");
        } catch (NullPointerException ex) {
            System.out.println("null string");
        }
        return this;
    }

    /**
     * undo to the previous state.
     */
    public void undo(){
        if(!actions.isEmpty()){
            actions.pop().undo();
        }
    }

    /**
     * Returns a string representing the data in this sequence. A new String object is allocated and initialized to
     * contain the character sequence currently represented by this object. This String is then returned.
     * Subsequent changes to this sequence do not affect the contents of the String.
     *
     * @return a string representation of this sequence of characters.
     */
    public String toString() {
        return stringBuilder.toString();
    }
}