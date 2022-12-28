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
     * @param offset
     * @param obj
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);
        for (Member obs : observers) {
            obs.update(usb);
        }
    }

    /**
     * @param obj
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
        for (Member obs : observers) {
            obs.update(usb);
        }
    }

    /**
     * @param start
     * @param end
     */
    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
        for (Member obs : observers) {
            obs.update(usb);
        }
    }

    /**
     *
     */
    @Override
    public void undo() {
        usb.undo();
        for (Member obs : observers) {
            obs.update(usb);
        }
    }

    @Override
    public String toString() {
        return "GroupAdmin{" +
                "usb=" + usb +
                ", observers=" + observers +
                '}';
    }
}
