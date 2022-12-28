package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {
    private UndoableStringBuilder usb = new UndoableStringBuilder();
    private ArrayList<Member> observers = new ArrayList<>();

    /**
     * @param obj
     */
    @Override
    public void register(Member obj) {
        observers.add(obj);
    }

    /**
     * @param obj
     */
    @Override
    public void unregister(Member obj) {
        observers.remove(obj);
    }

    /**
     * Notifies all Observers of a change.
     * <p>
     * TODO - this is often public in lots of other examples I've seen - why??? Surely better to control access to this
     * so that Observers are only notified via this class' business methods i.e. createNewBuyOrder?
     */
    private void notifyObservers() {
        // Notify interested parties...
        for (final Member observer : observers) {
            observer.update(usb);
        }
    }

    /**
     * @param offset
     * @param obj
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);
        notifyObservers();
    }

    /**
     * @param obj
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
        notifyObservers();
    }

    /**
     * @param start
     * @param end
     */
    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
        notifyObservers();
    }

    /**
     *
     */
    @Override
    public void undo() {
        usb.undo();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "GroupAdmin{" +
                "usb=" + usb +
                ", observers=" + observers +
                '}';
    }
}
