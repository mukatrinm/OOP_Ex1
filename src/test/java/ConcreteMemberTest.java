import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {
    GroupAdmin ga;
    ConcreteMember member1, member2;

    @Test
    void update() {
        ga = new GroupAdmin();
        member1 = new ConcreteMember();
        member2 = new ConcreteMember();
        ga.register(member1);
        ga.register(member2);
        assertEquals("", member1.toString());
        ga.insert(0, "hi bye");
        assertEquals("hi bye", member1.toString());
        assertEquals("hi bye", member2.toString());
    }
}