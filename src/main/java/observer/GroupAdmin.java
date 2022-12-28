package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {
    private UndoableStringBuilder usb = new UndoableStringBuilder();
    private ArrayList<Member> observers = new ArrayList<>();

    /**
     * Register an Observer.
     *
     * @param obj the interested observer
     */
    @Override
    public void register(Member obj) {
        if (!observers.contains(obj))
            observers.add(obj);
    }

    /**
     * Unregister an Observer.
     *
     * @param obj the interested observer
     */
    @Override
    public void unregister(Member obj) {
        observers.remove(obj);
    }

    /**
     * Notifies all Observers of a change.
     */
    private void notifyObservers() {
        // Notify interested parties...
        for (final Member observer : observers) {
            observer.update(usb);
        }
    }

    /**
     * Inserts the string into this character sequence and notifies all observers.
     *
     * @param offset the offset.
     * @param obj    a string.
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);
        notifyObservers();
    }

    /**
     * Appends the specified string to this character sequence and notifies all members.
     *
     * @param obj a string
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
        notifyObservers();
    }

    /**
     * Removes the characters in a substring of this sequence. The substring begins at the specified
     * start and extends to the character at index end - 1 or to the end of the sequence if no
     * such character exists. If start is equal to end, no changes are made.
     * finally, notify all members about the change.
     *
     * @param start The beginning index, inclusive.
     * @param end   The ending index, exclusive.
     */
    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
        notifyObservers();
    }

    /**
     * the object usb will hold the previous state of the string. if there is no previous state it will be an empty string,
     * then notify all observers.
     */
    @Override
    public void undo() {
        usb.undo();
        notifyObservers();
    }

    /**
     * @return number of observers
     */
    public int getNumOfObservers() {
        return observers.size();
    }

    @Override
    public String toString() {
        return "GroupAdmin{" +
                "usb=" + usb +
                ", observers=" + observers +
                '}';
    }
}
