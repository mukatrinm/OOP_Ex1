import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {
    public static final Logger logger = LoggerFactory.getLogger(GroupAdminTest.class);

    GroupAdmin ga;
    ConcreteMember member1, member2;

    @BeforeEach
    void setUp() {
        ga = new GroupAdmin();
        member1 = new ConcreteMember();
        member2 = new ConcreteMember();
    }

    @Test
    void register() {
        assertEquals(ga.getNumOfObservers(), 0);
        ga.register(member1);
        assertEquals(ga.getNumOfObservers(), 1);
        ga.register(member2);
        assertEquals(ga.getNumOfObservers(), 2);
        ga.register(member2);
        assertEquals(ga.getNumOfObservers(), 2);
    }

    @Test
    void unregister() {
        assertEquals(ga.getNumOfObservers(), 0);
        ga.unregister(member1);
        assertEquals(ga.getNumOfObservers(), 0);
        ga.register(member1);
        ga.unregister(member1);
        assertEquals(ga.getNumOfObservers(), 0);
    }

    @Test
    void insert() {
        ga.register(member1);
        ga.register(member2);

        ga.insert(0, "World");
        //logger.info(() -> ga.toString());
        ga.insert(0, "Hello ");
        ga.insert(11, "!");
        assertEquals("Hello World!", member1.toString());
    }

    @Test
    void append() {
        ga.register(member1);
        ga.register(member2);

        ga.insert(0, "abcd");
        assertEquals("abcd", member1.toString());
        ga.append("xyz");
        assertEquals("abcdxyz", member1.toString());
    }

    @Test
    void delete() {
        ga.register(member1);
        ga.register(member2);

        ga.insert(0, "abcd");
        assertEquals("abcd", member1.toString());
        ga.delete(1, 2);
        assertEquals("acd", member1.toString());
    }

    @Test
    void undo() {
        ga.register(member1);
        ga.register(member2);
        ga.undo();
        assertEquals("", member1.toString());
        ga.insert(0, "abcd");
        assertEquals("abcd", member1.toString());
        ga.delete(1, 2);
        assertEquals("acd", member1.toString());
        ga.undo();
        assertEquals("abcd", member1.toString());
    }
}