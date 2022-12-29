import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    GroupAdmin ga1, ga2;
    ConcreteMember member1, member2, member3;

    @BeforeEach
    void setUp() {
        ga1 = new GroupAdmin();
        member1 = new ConcreteMember();
        member2 = new ConcreteMember();
        member3 = new ConcreteMember();
    }

    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        logger.info(()->JvmUtilities.objectFootprint(ga1));
        ga1.register(member1);
        ga1.register(member2);
        logger.info(()->JvmUtilities.objectFootprint(ga1));
        logger.info(()->"member 1 before insert");
        logger.info(()->JvmUtilities.objectFootprint(member1));
        ga1.insert(0, "abc");
        logger.info(()->"ga1 and all members after insert");
        logger.info(()->JvmUtilities.objectFootprint(ga1));
        logger.info(()->JvmUtilities.objectFootprint(member1));
        logger.info(()->JvmUtilities.objectFootprint(member2));
        logger.info(()->JvmUtilities.objectFootprint(member3));
        ga1.insert(0, "def");
        logger.info(()->"ga1 and all members after second insert");
        logger.info(()->JvmUtilities.objectFootprint(ga1));
        logger.info(()->JvmUtilities.objectFootprint(member1));
        logger.info(()->JvmUtilities.objectFootprint(member2));
        logger.info(()->JvmUtilities.objectFootprint(member3));
        ga1.undo();
        logger.info(()->JvmUtilities.objectFootprint(ga1));
        logger.info(()->JvmUtilities.objectFootprint(member1));
        logger.info(()->JvmUtilities.objectFootprint(member2));
        logger.info(()->JvmUtilities.objectFootprint(member3));
        logger.info(()->JvmUtilities.objectTotalSize(ga1));
        logger.info(()->JvmUtilities.jvmInfo());
    }
}
