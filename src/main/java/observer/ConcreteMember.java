package observer;

public class ConcreteMember implements Member{
    private UndoableStringBuilder usb;

    public ConcreteMember() {
        usb = new UndoableStringBuilder();
    }

    /**
     * Updates the member of a change.
     *
     * @param usb the updated usb.
     */
    @Override
    public void update(UndoableStringBuilder usb) {
            this.usb = usb;
    }

    @Override
    public String toString() {
        return usb.toString();
    }
}
