package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;

public class membershipTest {

	@SuppressWarnings("unused")
	@Test
	void testMembershipConstructor() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);

		boolean actual = true;
		if (m == null) {
			actual = false;
		}
		
		assertEquals(true, actual);
	}
	
	@Test
	void testGetUser() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);

		User actual = m.getUser();
		assertEquals(u, actual);
	}	
	
	@Test
	void testGetGroup() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);

		Group actual = m.getGroup();
		assertEquals(testGroup1, actual);
	}	
	
	@Test
	void testGetDate() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		

		// Create a Date object for the registration date.
		membership m = new membership(u, testGroup1, "01/12/2012");

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String actual = df.format(m.getDate());
		
		assertEquals("01/12/2012", actual);
	}	
	
	
	@Test
	void testGetMembershipWriteData() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		membership membership = new membership(u, testGroup1, "01/12/2012");
		
		String actual = membership.getMembershipWriteData();
		
		String expected = "@START\n" + 
							"@MEMBERSHIP\n" +
							"@USER=jackster3\n" + 
							"@GROUP=Standard Name\n" + 
							"@REGISTEREDDATE=01/12/2012\n" + 
							"@END\n\n";
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testCompareTo_Success() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		membership membership1 = new membership(u1, testGroup1, "01/12/2012");
		
		User u2 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Standard Name");
		
		membership membership2 = new membership(u2, testGroup2, "01/12/2012");
		
		int actual = membership1.compareTo(membership2);
		
		assertEquals(1, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentUsernames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		membership membership1 = new membership(u1, testGroup1, "01/12/2012");
		
		User u2 = new User("Jack", "test", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Standard Name");
		
		membership membership2 = new membership(u2, testGroup2, "01/12/2012");
		
		int actual = membership1.compareTo(membership2);
		
		assertEquals(0, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentGruopNames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		membership membership1 = new membership(u1, testGroup1, "01/12/2012");
		
		User u2 = new User("Jack", "test", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Test");
		
		membership membership2 = new membership(u2, testGroup2, "01/12/2012");
		
		int actual = membership1.compareTo(membership2);
		
		assertEquals(0, actual);
	}
	
}