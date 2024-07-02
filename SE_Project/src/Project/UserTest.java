package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserTest {
	
	@SuppressWarnings("unused")
	@Test
	void testUser_AddingNewUser_Constructor() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		boolean actual = true;
		if (u == null) {
			actual = false;
		}
		
		assertEquals(true, actual);
	}
	
	@SuppressWarnings("unused")
	@Test
	void testUser_AddingExistingUser_Constructor() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", 
							"Valdosta", "Georgia", "12/17/2007");
		
		boolean actual = true;
		if (u == null) {
			actual = false;
		}
		
		assertEquals(true, actual);
	}
  
  @Test
	void testGetUserWriteData() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", 
							"Valdosta", "Georgia", "12/17/2007");
		
		String actual = u.getUserWriteData();
		
		String expected = "@START\n" + 
							"@USER\n" + 
							"@NAME=Jack\n" + 
							"@BIRTHDATE=06/17/2000\n" + 
							"@CITY=Valdosta\n" + 
							"@STATE=Georgia\n" + 
							"@USERNAME=jackster3\n" + 
							"@PASSWORD=HKb@wser!\n" + 
							"@REGISTERED_DATE=12/17/2007\n" + 
							"@END\n\n";
		
		assertEquals(expected, actual);
	}
  
	@Test
	void testGetId() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getId();
		
		assertEquals("jackster3", actual);
	}
	
	@Test
	void testGetPassword() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getPassword();
		
		assertEquals("HKb@wser!", actual);
	}
	
	@Test
	void testGetName() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getName();
		
		assertEquals("Jack", actual);
	}
	
	@Test
	void testGetBirthdate() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		Date dbay = u.getBirthday();
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String actual = df.format(dbay);
		
		assertEquals("06/17/2000", actual);
	}
	
	@Test
	void testGetCity() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getCity();
		
		assertEquals("Valdosta", actual);
	}
	
	@Test
	void testGetState() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getState();
		
		assertEquals("Georgia", actual);
	}
	
	@Test
	void testGetRegisteredDate() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", 
							"Valdosta", "Georgia", "10/12/2009");
		
		Date regDate = u.getRegisteredDate();
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String actual = df.format(regDate);
		
		assertEquals("10/12/2009", actual);
	}
	
	@Test
	void addVotedTest_Success(){
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, testGroup1);

		Post p1 = new Post(m1, "goofy goober", "YEAHHH", 1);
		
		Voted v1 = new Voted(u1, p1);
		
		testGroup1.addNewPost(p1);
		boolean actual = u1.addVoted(v1);

		assertEquals(true, actual);
	}
	
	@Test
	void addVotedTest_Failure(){
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, testGroup1);

		Post p1 = new Post(m1, "goofy goober", "YEAHHH", 1);

		Voted v1 = new Voted(u1, p1);
		
		testGroup1.addNewPost(p1);
		u1.addVoted(v1);
		boolean actual = u1.addVoted(v1);

		assertEquals(false, actual);
	}
	
	// also tests getPost()
	@Test
	void removeVotedTest_Success(){
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, testGroup1);

		Post p1 = new Post(m1, "goofy goober", "YEAHHH", 1);

		Voted v1 = new Voted(u1, p1);
		
		testGroup1.addNewPost(p1);
		u1.addVoted(v1);
		boolean actual = u1.removeVoted(v1);

		assertEquals(true, actual);
	}
	
	// also tests getPost()
	@Test
	void removeVotedTest_Failure(){
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, testGroup1);

		Post p1 = new Post(m1, "goofy goober", "YEAHHH", 1);

		Voted v1 = new Voted(u1, p1);
		
		testGroup1.addNewPost(p1);
		boolean actual = u1.removeVoted(v1);

		assertEquals(false, actual);
	}
	
	@Test
	void getVotedTest_Success(){
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, testGroup1);

		Post p1 = new Post(m1, "goofy goober", "YEAHHH", 1);

		
		Voted v1 = new Voted(u1, p1);
		
		testGroup1.addNewPost(p1);
		u1.addVoted(v1);
		
		ArrayList<Voted> expected = new ArrayList<>();
		expected.add(v1);

		assertEquals(expected, u1.getVotedList());
	}
	
	@Test 
	void testCompareTo_Success() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		User u2 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		int actual = u1.compareTo(u2);
		
		assertEquals(1, actual);
		
	}
	
	@Test 
	void testCompareTo_Failure() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		User u2 = new User("Jack", "test", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		int actual = u1.compareTo(u2);
		
		assertEquals(0, actual);
		
	}
	
	
	@Test
	void testCompareId_Success() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		boolean actual = u1.compareId("jackster3");
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testCompareId_Failure() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		boolean actual = u1.compareId("test");
		
		assertEquals(false, actual);
		
	}
	
	
	@Test
	void testComparePassword_Success() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		boolean actual = u1.comparePassword("HKb@wser!");
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testComparePassword_Failure() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		boolean actual = u1.comparePassword("test");
		
		assertEquals(false, actual);
		
	}
	

}
