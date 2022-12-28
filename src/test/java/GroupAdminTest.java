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
        logger.info(()->JvmUtilities.objectFootprint(ga));
        ga.register(member1);
        logger.info(()->JvmUtilities.objectFootprint(ga));
        ga.register(member2);
        logger.info(()->JvmUtilities.objectFootprint(ga));
    }

    @Test
    void unregister() {
        logger.info(()->JvmUtilities.objectFootprint(ga));
        ga.unregister(member1);
        logger.info(()->JvmUtilities.objectFootprint(ga));
        ga.register(member1);
        logger.info(() -> "register string");
        logger.info(()->JvmUtilities.objectFootprint(ga));
        ga.unregister(member1);
        logger.info(()->JvmUtilities.objectFootprint(ga));
    }

    @Test
    void insert() {
        ga.register(member1);
        ga.register(member2);

        ga.insert(0, "abcd");
        logger.info(() -> ga.toString());
    }

    @Test
    void append() {
        ga.register(member1);
        ga.register(member2);

        ga.insert(0, "abcd");
        logger.info(() -> ga.toString());
        ga.append("xyz");
        logger.info(() -> ga.toString());
    }

    @Test
    void delete() {
        ga.register(member1);
        ga.register(member2);

        ga.insert(0, "abcd");
        logger.info(() -> ga.toString());
        ga.delete(1, 2);
        logger.info(() -> ga.toString());
    }

    @Test
    void undo() {
        ga.register(member1);
        ga.register(member2);
        ga.insert(0, "abcd");
        logger.info(() -> ga.toString());
        ga.delete(1, 2);
        logger.info(() -> ga.toString());
        ga.undo();
        logger.info(() -> ga.toString());
    }
}