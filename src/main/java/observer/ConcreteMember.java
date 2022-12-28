package observer;

import java.util.Objects;

public class ConcreteMember implements Member{
    private UndoableStringBuilder usb;
    /**
     * Updates the member of a change.
     *
     * @param usb the updated usb.
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        // update only if there is a change
        if (this.usb != null &&  !this.usb.toString().equals(usb.toString()))
            this.usb = usb;
    }

    @Override
    public String toString() {
        return "ConcreteMember{" +
                "usb=" + usb +
                '}';
    }
}
