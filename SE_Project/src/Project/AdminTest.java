package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {
	
	// Test getting Admin data from disk
	@Test
	void testGetAdminWriteData() {
		Admin a = new Admin("Billy", "0010", "billyiscool", "10/10/1997", 
								"Valdosta", "Georgia", "12/05/2008");
		
		String actual = a.getAdminWriteData();
		
		String expected = "@START\n" + 
							"@ADMIN\n" + 
							"@NAME=Billy\n" + 
							"@BIRTHDATE=10/10/1997\n" + 
							"@CITY=Valdosta\n" + 
							"@STATE=Georgia\n" + 
							"@USERNAME=0010\n" + 
							"@PASSWORD=billyiscool\n" + 
							"@REGISTERED_DATE=12/05/2008\n" + 
							"@END\n\n";
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testCompareTo_Success() {
		
		Admin a1 = new Admin("Billy", "0010", "billyiscool", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Billy", "0010", "billyiscool", "10/10/1997", "Valdosta", "Georgia");
		
		int actual = a1.compareTo(a2);
		
		assertEquals(1, actual);
		
	}
	
	@Test
	void testCompareTo_Failure() {
		
		Admin a1 = new Admin("Billy", "0010", "billyiscool", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Billy", "test", "billyiscool", "10/10/1997", "Valdosta", "Georgia");
		
		int actual = a1.compareTo(a2);
		
		assertEquals(0, actual);
		
	}
	
	
	@Test
	void testCompareId_Success() {
		
		Admin a1 = new Admin("Billy", "0010", "billyiscool", "10/10/1997", "Valdosta", "Georgia");
		
		boolean actual = a1.compareId("0010");
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testCompareId_Failure() {
		
		Admin a1 = new Admin("Billy", "0010", "billyiscool", "10/10/1997", "Valdosta", "Georgia");
		
		boolean actual = a1.compareId("1000");
		
		assertEquals(false, actual);
		
	}

}
