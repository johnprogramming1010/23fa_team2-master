package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;

public class SuspendedTest {
	
	@Test
	void testgetUser() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		Suspended s = new Suspended(u, testGroup1);

		User actual = s.getUser();
		assertEquals(u, actual);
	}	
	
	@Test
	void testGetGroup() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		Suspended b = new Suspended(u, testGroup1);

		Group actual = b.getGroup();
		assertEquals(testGroup1, actual);
	}	
	
	@Test
	void testGetDate() {
		
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		

		// Create a Date object for the registration date.
		Suspended s = new Suspended(u, testGroup1, "01/12/2012 8:20 pm", "01/13/2012 8:20 pm");

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String actual = df.format(s.getDate());
		
        
        String expected = "01/12/2012 8:20 PM";
		
		assertEquals(expected, actual);
	}	
	
	@Test
	void testgetExpiredDate() {
		
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		

		// Create a Date object for the registration date.
		Suspended s = new Suspended(u, testGroup1, "01/12/2012 8:20 pm", "01/13/2012 8:20 pm");

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String actual = df.format(s.getExpiredDate());
		
        
        String expected = "01/13/2012 8:20 PM";
		
		assertEquals(expected, actual);
	}	
	
	@Test
	void testGetSuspendedWriteData() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Suspended s = new Suspended(u, testGroup1, "01/12/2012 8:20 pm", "01/13/2012 8:20 pm");
				
		String actual = s.getSuspendedWriteData();
		
		String expected = "@START\n" + 
							"@SUSPENDED\n" +
							"@USER=jackster3\n" + 
							"@GROUP=Standard Name\n" + 
							"@SUSPENDEDDATE=01/12/2012 8:20 PM\n" + 
							"@EXPIREDDATE=01/13/2012 8:20 PM\n" +
							"@END\n\n";
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testCompareTo_Success() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Suspended s1 = new Suspended(u1, testGroup1, "01/12/2012 8:20 am", "01/13/2012  8:20 pm");
		
		User u2 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Standard Name");
		
		Suspended s2 = new Suspended(u2, testGroup2, "01/12/2012 8:20 pm", "01/13/2012 8:20 pm");

			
		int actual = s1.compareTo(s2);
		
		assertEquals(1, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentUsernames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Suspended s1 = new Suspended(u1, testGroup1, "01/12/2012 8:20 pm", "01/13/2012  8:20 pm");
		
		User u2 = new User("Jack", "test", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Standard Name");
		
		Suspended s2 = new Suspended(u2, testGroup2, "01/12/2012 8:20 pm", "01/13/2012 8:20 pm");
		
		int actual = s1.compareTo(s2);
		
		assertEquals(0, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentGruopNames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Suspended s1 = new Suspended(u1, testGroup1, "01/12/2012 8:20 pm", "01/13/2012 8:20 pm");
		
		User u2 = new User("Jack", "test", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Test");
		
		Suspended s2 = new Suspended(u2, testGroup2, "01/12/2012 8:20 pm", "01/13/2012 8:20 pm");
		
		int actual = s1.compareTo(s2);
		
		assertEquals(0, actual);
	}
	
}