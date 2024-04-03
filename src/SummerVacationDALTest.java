import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class SummerVacationDALTest {
    private static ConfigReader config;

    @BeforeClass
    public static void Setup() {
        config = new ConfigReader();
    }

    @Test
    public void testInitializeConnectionWithGoodInput() {
        SummerVacationDAL sVD = new SummerVacationDAL("Vacation", config.getUsername(), config.getPassword());
        assertNotNull(sVD.getConnection());
    }

    @Test
    public void testInitializeConnectionWithBadDatabaseName() {
        SummerVacationDAL sVD = new SummerVacationDAL("NotVacation", config.getUsername(), config.getPassword());
        assertNull(sVD.getConnection());
    }

    @Test
    public void testInitializeConnectionWithBadUsername() {
        SummerVacationDAL sVD = new SummerVacationDAL("Vacation", "badusername", config.getPassword());
        assertNull(sVD.getConnection());
    }

    @Test
    public void testInitializeConnectionWithBadPassword() {
        SummerVacationDAL sVD = new SummerVacationDAL("Vacation", config.getUsername(), "badpassword");
        assertNull(sVD.getConnection());
    }

    @Test
    public void testTryGetDestinationForGoodActivityHiking() {
        SummerVacationDAL sVD = new SummerVacationDAL("Vacation", config.getUsername(), config.getPassword());
        List<String> destinations = sVD.TryGetDestinationForActivity("Hiking");
        assertEquals("5 results expected. but 5 not found.", 5, destinations.size());
    }

    @Test
    public void testTryGetDestinationForBadActivityNotHiking() {
        SummerVacationDAL sVD = new SummerVacationDAL("Vacation", config.getUsername(), config.getPassword());
        List<String> destinations = sVD.TryGetDestinationForActivity("NotHiking");
        assertEquals("No results expected. but some found.", 0, destinations.size());
    }
}