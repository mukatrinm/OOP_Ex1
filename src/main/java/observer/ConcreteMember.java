package observer;

public class ConcreteMember implements Member{
    private UndoableStringBuilder usb;
    /**
     * @param usb
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    @Override
    public String toString() {
        return "ConcreteMember{" +
                "usb=" + usb +
                '}';
    }
}
