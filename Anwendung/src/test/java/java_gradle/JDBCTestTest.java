package java_gradle;

import java.util.List;    
import org.junit.Test;
import static org.junit.Assert.*;
    
public class JDBCTestTest {
    
    @Test
    public void testGetEntries() {
    
        int[] zeilen 				= { 1 };
        assertTrue					("DB-Connection konnte nicht hergestellt werden", JDBCTest.connect());
        List<String[]> actual  		= JDBCTest.getEntries(zeilen);
        assertEquals				("Zu wenig/zu viel Zeilen gefunden", zeilen.length, actual.size());
        String[] expString			= { "Heiner", "Schmidt", "1922-02-02 00:00:00.000000", "43" };
        assertArrayEquals			(expString, actual.get(0));
    }
}
    