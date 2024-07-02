package Project;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Test;

class SystemManagerTest {

	/*
	 * NOTICE:
	 *
	 * Tests will have to be updated as classes are updated to where
	 * constructors take more arguments
	 *
	 */

	@Test
	void testLogin_Success_User() {

		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean tf = sm.login("LegalTrouble", "D@uble&Tr@uble");

		assertEquals(true, tf);
	}

	@Test
	void testLogin_Failure_Username_User() {

		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean tf = sm.login("Failure", "D@uble&Tr@uble");

		assertEquals(false, tf);
	}


	@Test
	void testLogin_Failure_Password_User() {

		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean tf = sm.login("LegalTrouble", "Failure");

		assertEquals(false, tf);
	}

	@Test
	void testLogin_Success_Admin() {

		SystemManager sm = new SystemManager();

		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");

		sm.addAdmin(a1);
		sm.addAdmin(a2);

		boolean tf = sm.login("jackster3", "HKb@wser!");

		assertEquals(true, tf);
	}

	@Test
	void testLogin_Failure_Username_Admin() {

		SystemManager sm = new SystemManager();

		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");

		sm.addAdmin(a1);
		sm.addAdmin(a2);

		boolean tf = sm.login("jackster7", "HKb@wser!");

		assertEquals(false, tf);
	}

	@Test
	void testLogin_Falilure_Password_Admin() {

		SystemManager sm = new SystemManager();

		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");

		sm.addAdmin(a1);
		sm.addAdmin(a2);

		boolean tf = sm.login("jackster3", "Failure");

		assertEquals(false, tf);
	}


	@Test
	void testGetGroupsSortedAlphabetically() {

		SystemManager sm = new SystemManager();
		
		category c1 = new category("fun");
		
		sm.addCategory(c1);

		Group g1 = new Group("Sports");
		Group g2 = new Group("Games");
		Group g3 = new Group("Video Games");
		Group g4 = new Group("Foods");
		Group g5 = new Group("Apples");
		
		c1.addGroup(g1);
		c1.addGroup(g2);
		c1.addGroup(g3);
		c1.addGroup(g4);
		c1.addGroup(g5);

		ArrayList<Group> actual = sm.getAllGroups_Alphabetically();

		ArrayList<Group> expected = new ArrayList<>();

		expected.add(g5);
		expected.add(g4);
		expected.add(g2);
		expected.add(g1);
		expected.add(g3);

		assertEquals(expected, actual);

	}
	
	void testGetRequestedGroupsSortedAlphabetically() {

		SystemManager sm = new SystemManager();
		
		category c1 = new category("fun");
		
		sm.addCategory(c1);

		Group g1 = new Group("Sports");
		Group g2 = new Group("Games");
		Group g3 = new Group("Video Games");
		Group g4 = new Group("Foods");
		Group g5 = new Group("Apples");
		
		c1.addRequestedGroup(g1);
		c1.addRequestedGroup(g2);
		c1.addRequestedGroup(g3);
		c1.addRequestedGroup(g4);
		c1.addRequestedGroup(g5);

		ArrayList<Group> actual = sm.getAllRequestedGroups_Alphabetically();

		ArrayList<Group> expected = new ArrayList<>();

		expected.add(g5);
		expected.add(g4);
		expected.add(g2);
		expected.add(g1);
		expected.add(g3);

		assertEquals(expected, actual);

	}
	
	@Test
	void testGetUsers_Alphabetically() {

		SystemManager sm = new SystemManager();
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		ArrayList<User> actual = sm.getUsers_Alphabetically();

		ArrayList<User> expected = new ArrayList<>();

		expected.add(u5);
		expected.add(u1);
		expected.add(u4);
		expected.add(u2);
		expected.add(u3);

		assertEquals(expected, actual);

	}

	@Test
	void testGetCategoiesSortedAlphabetically() {

		SystemManager sm = new SystemManager();

		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");

		sm.addCategory(c1);
		sm.addCategory(c2);
		sm.addCategory(c3);
		sm.addCategory(c4);
		sm.addCategory(c5);

		ArrayList<category> actual = sm.getCategories_Alphabetically();

		ArrayList<category> expected = new ArrayList<>();

		expected.add(c5);
		expected.add(c4);
		expected.add(c2);
		expected.add(c1);
		expected.add(c3);

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetMembership() {

		SystemManager sm = new SystemManager();
		
		Group g = new Group("gfun");
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, g);
		g.addMember(m1);
		
		membership actual = sm.getMembership(g, u1);



		assertEquals(m1, actual);
	}
	
	@Test
	void testGetSimpleDate() {
		SystemManager sm = new SystemManager();
		Date testDate = new Date();
		String formattedDate = sm.getSimpleDate(testDate);
		
		SimpleDateFormat expectedDateFormat = new SimpleDateFormat("dd MMM yyyy");
        String expectedDate = expectedDateFormat.format(testDate);
		
        assertEquals(expectedDate, formattedDate);
	}
	
    @Test
    void testGetSimpleTime() {
		SystemManager sm = new SystemManager();
        Date testDate = new Date();
        String formattedTime = sm.getSimpleTime(testDate);

        SimpleDateFormat expectedTimeFormat = new SimpleDateFormat("h:mm a");
        String expectedTime = expectedTimeFormat.format(testDate);

        assertEquals(expectedTime, formattedTime);
    }
	
	@Test
	void testGetAllMemberships() {

		SystemManager sm = new SystemManager();
		
		category c = new category("dsjg");
		
		Group g = new Group("gfun");
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, g);
		sm.addCategory(c);
		c.addGroup(g);
		g.addMember(m1);
		
		ArrayList<membership> actual = sm.getAllMemberships();
		
		ArrayList<membership> expected = new ArrayList<>();
		expected.add(m1);

		assertEquals(expected, actual);
	}
	
	@Test
	void testCreateCategory_Success() {
		SystemManager sm = new SystemManager();

		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");

		sm.addCategory(c1);
		sm.addCategory(c2);
		sm.addCategory(c3);
		sm.addCategory(c4);
		sm.addCategory(c5);

		boolean actual = sm.createCategory("hey");

		assertEquals(true, actual);
	}


	@Test
	void testCreateCategory_Failure() {
		SystemManager sm = new SystemManager();

		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");

		sm.addCategory(c1);
		sm.addCategory(c2);
		sm.addCategory(c3);
		sm.addCategory(c4);
		sm.addCategory(c5);

		boolean actual = sm.createCategory("Foods");

		assertEquals(false, actual);
	}


	@Test
	void testCreateGroup_Success() {
		SystemManager sm = new SystemManager();

		category c = new category("Sports");

		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		c.addGroup(g1);
		c.addGroup(g2);

		sm.addCategory(c);

		boolean actual = sm.createGroup("Tennis", "Sports");

		assertEquals(true, actual);
	}


	@Test
	void testCreateGroup_Failure_InvalidCategory() {
		SystemManager sm = new SystemManager();

		category c = new category("Sports");

		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		c.addGroup(g1);
		c.addGroup(g2);

		sm.addCategory(c);

		boolean actual = sm.createGroup("Tennis", "Foods");

		assertEquals(false, actual);
	}


	@Test
	void testCreateGroup_Failure_DuplicateGroup() {
		SystemManager sm = new SystemManager();

		category c = new category("Sports");

		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		c.addGroup(g1);
		c.addGroup(g2);

		sm.addCategory(c);

		boolean actual = sm.createGroup("Soccer", "Sports");

		assertEquals(false, actual);
	}


	@Test
	void testRegisterUser_Success() {
		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean actual = sm.registerUser("Jack", "11/3/99", "Valdosta", "GA", "JackTheWack", "W@ck0#5");

		assertEquals(true, actual);
	}

	@Test
	void testRegisterUser_Failure() {
		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean actual = sm.registerUser("Jack", "11/3/99", "Valdosta", "GA", "jackster3", "W@ck0#5");

		assertEquals(false, actual);

	}
	
	@Test
	void testJoinGroup_Success() {
		SystemManager sm = new SystemManager();
		
		Group g1 = new Group("fun");

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		membership m = new membership(u5, g1);
		User expected = m.getUser();
		
		sm.joinGroup(u1, g1);
		sm.joinGroup(u2, g1);
		sm.joinGroup(u3, g1);
		sm.joinGroup(u4, g1);
		sm.joinGroup(u5, g1);
		
		User actual = sm.getMembership(g1, u5).getUser();

		assertEquals(expected, actual);
	}
	
	@Test
	void testCreateNewPost_Success() {
		SystemManager sm = new SystemManager();
		
		category c1 = new category("djsh"); 
		
		Group g1 = new Group("fun");

		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
				
		membership m = new membership(u5, g1);
		
		Post p1 = new Post(m, "dsudgu", "dusdg", 1);
		Post p2 = new Post(m, "dsudgu", "dusdg", 2);

		
		sm.addCategory(c1);
		sm.addUser(u5);
		c1.addGroup(g1);
		g1.addMember(m);
		g1.addNewPost(p1);
		g1.addNewPost(p2);
		
		ArrayList<Post> actual = g1.getPost();
		
		ArrayList<Post> expected = g1.getPost();

		assertEquals(expected, actual);
	}
	
	@Test
	void testDeleteNewPost_Success() {
		SystemManager sm = new SystemManager();
		
		category c1 = new category("djsh"); 
		
		Group g1 = new Group("fun");

		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
				
		membership m = new membership(u5, g1);
		
		Post p1 = new Post(m, "dsudgu", "dusdg", 1);
		Post p2 = new Post(m, "dsudgu", "dusdg", 2);

		
		sm.addCategory(c1);
		sm.addUser(u5);
		c1.addGroup(g1);
		g1.addMember(m);
		g1.addNewPost(p1);
		g1.addNewPost(p2);
		sm.deleteNewPost(p1);

				
		ArrayList<Post> actual = g1.getPost();
		
		ArrayList<Post> expected = g1.getPost();

		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveResponseToPost_Success() {
		SystemManager sm = new SystemManager();
		
		category c1 = new category("djsh"); 
		
		Group g1 = new Group("fun");

		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
				
		membership m = new membership(u5, g1);
		
		Post p1 = new Post(m, "dsudgu", "dusdg", 1);
		Post p2 = new Post(m, "dsudgu", "dusdg", 2);
		Response r1 = new Response(m, "fdihsfi", 1, p1.getResponseID());
		Response r2 = new Response(m, "fdihsgfgfi", 2, p2.getResponseID());
		String userTitle = "Admin";


		p1.addNewResponse(r1);
		p1.addNewResponse(r2);
		sm.addCategory(c1);
		c1.addGroup(g1);
		g1.addMember(m);
		g1.addNewPost(p1);
		g1.addNewPost(p2);
		sm.removeResponseToPost(p1, r1, userTitle);
		
		String actual = r1.getPostBody();
		String expected = "Content Removed By: Admin";

		assertEquals(expected, actual);
	}
	
	@Test
	void testCreateNewGroup_Success() {
		SystemManager sm = new SystemManager();
		
		category c1 = new category("djsh"); 
		
		Group g1 = new Group("fun");

		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
				
		membership m = new membership(u5, g1);
		
		sm.addCategory(c1);
		c1.addGroup(g1);
		g1.addMember(m);
		
		sm.createNewGroup(c1, "dsd");
		
		ArrayList<Group> actual = c1.getGroups();
		
		ArrayList<Group> expected = c1.getGroups();

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetAdmins_Alphabetically() {
		SystemManager manager = new SystemManager();
		
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		Admin a3 = new Admin("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		Admin a4 = new Admin("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		Admin a5 = new Admin("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		ArrayList<Admin> actual = manager.getAdmins_Alphabetically();
		
		ArrayList<Admin> expected = new ArrayList<Admin>();
		
		expected.add(a5);
		expected.add(a1);
		expected.add(a4);
		expected.add(a2);
		expected.add(a3);
		
		assertEquals(expected, actual);
  }
	
	@Test
	void testIsUserOfGroup() {
		SystemManager sm = new SystemManager();
		Group g1 = new Group("Funny");
		category c1 = new category("happy");
		
		c1.addGroup(g1);
		
		sm.addCategory(c1);

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/05/12", "10/5/12", "10/5/12");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/05/12", "10/5/12", "5/5/5");
		
		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);

		g1.addMember(m1);
		g1.addMember(m2);
		
		c1.addGroup(g1);

		sm.addUser(u1);
		sm.addUser(u2);

		Boolean actual = sm.isUserOfGroup(u2, g1);
		Boolean expected = true;

		assertEquals(expected, actual);
	}
	
		

	@Test
	void testgetGroupsByUser_success() {
		SystemManager sm = new SystemManager();
		Group g1 = new Group("Funny");
		Group g2 = new Group("Happy");
		category c1 = new category("happy");
		
		c1.addGroup(g1);
		c1.addGroup(g2);
		
		sm.addCategory(c1);

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/05/12", "10/5/12", "10/5/12");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/05/12", "10/5/12", "5/5/5");
		
		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u1, g2);

		g1.addMember(m1);
		g1.addMember(m2);
		g2.addMember(m3);
		
		c1.addGroup(g1);
		c1.addGroup(g2);

		sm.addUser(u1);
		sm.addUser(u2);

		ArrayList<Group> actual = new ArrayList<>();
		actual.addAll(sm.getGroupsByUser(u1));
		
		ArrayList<Group> expected = new ArrayList<>();
		expected.add(g1);
		expected.add(g2);

		assertEquals(expected, actual);
	}
	
	@Test
	void testgetUsersInGroup_Success() {
		SystemManager sm = new SystemManager();
		Group g = new Group("Funny");
		
		category c1 = new category("Sports");

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/05/12", "10/5/12", "10/5/12");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/05/12", "10/5/12", "5/5/5");
		
		membership m1 = new membership(u1, g);
		membership m2 = new membership(u2, g);

		g.addMember(m1);
		g.addMember(m2);
		
		c1.addGroup(g);

		sm.addUser(u1);
		sm.addUser(u2);

		ArrayList<User> actual = new ArrayList<>();
		actual.addAll(sm.getUsersInGroup(g));
		
		ArrayList<User> expected = new ArrayList<>();
		expected.add(u1);
		expected.add(u2);

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetGroupsInCategory_Alphabetically() {
		SystemManager sm = new SystemManager();
		
		category c1 = new category("test");
		
		sm.addCategory(c1);

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");
		
		c1.addGroup(g1);
		c1.addGroup(g2);
		c1.addGroup(g3);
		c1.addGroup(g4);
		c1.addGroup(g5);


		ArrayList<Group> expected = new ArrayList<>();
		expected.add(g4);
		expected.add(g3);
		expected.add(g1);
		expected.add(g2);
		expected.add(g5);
		
		 
		ArrayList<Group> actual = new ArrayList<>();
		actual.addAll(sm.getGroupsInCategory_Alphabetically(c1));
		assertEquals(expected, actual);

	}
	
	@Test
	void testGetRequestedGroupsInCategory_Alphabetically() {
		SystemManager sm = new SystemManager();
		
		category c1 = new category("test");
		
		sm.addCategory(c1);

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");
		
		c1.addRequestedGroup(g1);
		c1.addRequestedGroup(g2);
		c1.addRequestedGroup(g3);
		c1.addRequestedGroup(g4);
		c1.addRequestedGroup(g5);


		ArrayList<Group> expected = new ArrayList<>();
		expected.add(g4);
		expected.add(g3);
		expected.add(g1);
		expected.add(g2);
		expected.add(g5);
		
		 
		ArrayList<Group> actual = new ArrayList<>();
		actual.addAll(sm.getRequestedGroupsInCategory_Alphabetically(c1));
		assertEquals(expected, actual);

	}
	
	@Test
	void testViewAllPostResponses() {
		SystemManager sm = new SystemManager();
		category c = new category("fun");
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		Post testPost1 = new Post (m, "I", "This is the message", testGroup.getPostId());
		Response r1 = new Response(m, "n", 1, testPost1.getResponseID());
		Response r2 = new Response(m, "n000", 1, testPost1.getResponseID());

		ArrayList<Response> expected = new ArrayList<>();
		expected.add(r1);
		expected.add(r2);
		
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addMember(m);
		testGroup.addNewPost(testPost1);
		sm.addUser(testUser);
		sm.setCurrentPost(testPost1);
		
		testPost1.addNewResponse(r1);
		testPost1.addNewResponse(r2);
		
		assertEquals(expected, sm.viewAllPostResponses());
	}
	
	@Test
	void testViewUsersPostsResponses() {
		SystemManager sm = new SystemManager();
		category c = new category("fun");
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		Post testPost1 = new Post (m, "I", "This is the message", testGroup.getPostId());
		Response r1 = new Response(m, "n", 1, testPost1.getResponseID());
		Response r2 = new Response(m, "n000", 1, testPost1.getResponseID());

		ArrayList<Object> expected = new ArrayList<>();
		expected.add(testPost1);
		expected.add(r1);
		expected.add(r2);
		
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addMember(m);
		testGroup.addNewPost(testPost1);
		sm.addUser(testUser);
		
		testPost1.addNewResponse(r1);
		testPost1.addNewResponse(r2);
		
		assertEquals(expected, sm.viewUsersPostsResponses(testUser));
	}
	
	@Test
	void testViewUsersPostsResponsesInGroup() {
		SystemManager sm = new SystemManager();
		category c = new category("fun");
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		Post testPost1 = new Post (m, "I", "This is the message", testGroup.getPostId());
		Response r1 = new Response(m, "n", 1, testPost1.getResponseID());
		Response r2 = new Response(m, "n000", 1, testPost1.getResponseID());

		ArrayList<Object> expected = new ArrayList<>();
		expected.add(testPost1);
		expected.add(r1);
		expected.add(r2);
		
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addMember(m);
		testGroup.addNewPost(testPost1);
		sm.addUser(testUser);
		
		testPost1.addNewResponse(r1);
		testPost1.addNewResponse(r2);
		
		assertEquals(expected, sm.viewUsersPostsResponsesInGroup(testUser, testGroup));
	}
	
	@Test
	void testViewPostsResponsesInGroup() {
		SystemManager sm = new SystemManager();
		category c = new category("fun");
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		Post testPost1 = new Post (m, "I", "This is the message", testGroup.getPostId());
		Response r1 = new Response(m, "n", 1, testPost1.getResponseID());
		Response r2 = new Response(m, "n000", 1, testPost1.getResponseID());

		ArrayList<Object> expected = new ArrayList<>();
		expected.add(testPost1);
		expected.add(r1);
		expected.add(r2);
		
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addMember(m);
		testGroup.addNewPost(testPost1);
		sm.addUser(testUser);	
		
		testPost1.addNewResponse(r1);
		testPost1.addNewResponse(r2);
		
		assertEquals(expected, sm.viewPostsResponsesInGroup(testGroup));
	}
	
	@Test
	void testViewPostsInGroup() {
		SystemManager sm = new SystemManager();
		category c = new category("fun");
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		Post testPost1 = new Post (m, "I", "This is the message", testGroup.getPostId());
		Response r1 = new Response(m, "n", 1, testPost1.getResponseID());
		Response r2 = new Response(m, "n000", 1, testPost1.getResponseID());

		ArrayList<Object> expected = new ArrayList<>();
		expected.add(testPost1);
		
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addMember(m);
		testGroup.addNewPost(testPost1);
		sm.addUser(testUser);	
		
		testPost1.addNewResponse(r1);
		testPost1.addNewResponse(r2);
		
		assertEquals(expected, sm.viewPostsInGroup(testGroup));
	}
	
	@Test
	void testViewMyResponses() {
		SystemManager sm = new SystemManager();
		category c = new category("fun");
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		Post testPost1 = new Post (m, "I", "This is the message", testGroup.getPostId());
		Response r1 = new Response(m, "n", 1, testPost1.getResponseID());
		Response r2 = new Response(m, "n000", 1, testPost1.getResponseID());

		ArrayList<Object> expected = new ArrayList<>();
		expected.add(r1);
		expected.add(r2);
		
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addMember(m);
		testGroup.addNewPost(testPost1);
		sm.addUser(testUser);
		
		testPost1.addNewResponse(r1);
		testPost1.addNewResponse(r2);
		
		assertEquals(expected, sm.viewMyResponses(testUser, testPost1));
	}
	
	@Test
	void testAddAdmin_Success() {
		SystemManager manager = new SystemManager();
		
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		Admin a3 = new Admin("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		Admin a4 = new Admin("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		Admin a5 = new Admin("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		
		
		boolean actual = manager.addAdmin(a5);
		
		assertEquals(true, actual);
	}
	
	@Test
	void testAddAdmin_Failure() {
		SystemManager manager = new SystemManager();
		
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		Admin a3 = new Admin("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		Admin a4 = new Admin("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		Admin a5 = new Admin("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		
		boolean actual = manager.addAdmin(a5);
		
		assertEquals(false, actual);
	}
	
	
	@Test
	void testAddCategory_Success() {
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		manager.addCategory(c4);
		
		category c5 = new category("Apples");
		
		boolean actual = manager.addCategory(c5);
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testAddCategory_Failure() {
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		manager.addCategory(c4);
		manager.addCategory(c5);
		
		boolean actual = manager.addCategory(c5);
		
		assertEquals(false, actual);
		
	}	
	
	@Test
	void testAddUser_Success() {
		SystemManager manager = new SystemManager();
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		boolean actual = manager.addUser(u5);
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testAddUser_Failure() {
		SystemManager manager = new SystemManager();
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		
		boolean actual = manager.addUser(u3);
		
		assertEquals(false, actual);
		
	}
	
	
	@Test
	void testSystemManager_ReadFileConstructor_Admins() {
		
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Admin.txt";
		ArrayList<String> fileNames = new ArrayList<>();
		fileNames.add(fileName);
		
		SystemManager manager = new SystemManager(fileNames);
		
		ArrayList<Admin> actual = manager.getAdmins_Alphabetically();
		
		String[] expected = {"John", "Ryan", "Himanshu", "Grayson", "Jeremy"};
		
		boolean namesMatch = true;
		
		for (int i = 0; i < actual.size(); i++) {
			
			if (!actual.get(i).getName().equals(expected[i])) {
				namesMatch = false;
				break;
			}
		}
		
		assertEquals(true, namesMatch);
		
	}
	
	
	@Test
	void testWriteManager_Admins() {
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteManager_Test_Admins";
		ArrayList<String> fileNames = new ArrayList<>();
		fileNames.add(fileName);
		SystemManager manager = new SystemManager(fileNames);
		
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		Admin a3 = new Admin("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		Admin a4 = new Admin("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		Admin a5 = new Admin("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		boolean actual = manager.writeManager();
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testGetCategoryByName() {

		SystemManager sm = new SystemManager();
		
		category c1 = new category("fun");
		sm.addCategory(c1);
		sm.setCurrentCategory(c1);	

		assertEquals(c1, sm.getCategoryByName("fun"));
	}
	
	@Test
	void testGetCategoryByGroup() {

		SystemManager sm = new SystemManager();
		
		category c1 = new category("fun");
		Group g1 = new Group("funny", 0);
		sm.addCategory(c1);
		sm.setCurrentCategory(c1);	
		c1.addGroup(g1);

		assertEquals(c1, sm.getCategoryByGroup(g1));
	}	
	
	@Test
	void testGetUsers_Alphabetically_ByUsernames() {
		
		SystemManager manager = new SystemManager();
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		
		ArrayList<User> actual = manager.getUsers_Alphabetically_ByUsername();
		
		String expected[] = {"IDK", "jackster3", "LegalTrouble", "theWiz", "WestCarolina"};
		
		boolean namesMatch = true;
		
		for (int i = 0; i < actual.size(); i++) {
			
			if (!actual.get(i).getId().equals(expected[i])) {
				namesMatch = false;
				break;
			}
		}
		
		assertEquals(true, namesMatch);
	}
	
	@Test
	void testGetAdmins_Alphabetically_ByUsername() {
		
		SystemManager manager = new SystemManager();
		
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		Admin a3 = new Admin("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		Admin a4 = new Admin("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		Admin a5 = new Admin("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		ArrayList<Admin> actual = manager.getAdmins_Alphabetically_ByUsername();
		
		String expected[] = {"IDK", "jackster3", "LegalTrouble", "theWiz", "WestCarolina"};
		
		boolean namesMatch = true;
		
		for (int i = 0; i < actual.size(); i++) {
			
			if (!actual.get(i).getId().equals(expected[i])) {
				namesMatch = false;
				break;
			}
		}
		
		assertEquals(true, namesMatch);
	}
	
	
	@Test
	void testGetGroupByName_Success() {
		
		SystemManager sm = new SystemManager();
		
		category c1 = new category("fun");

		Group g1 = new Group("Sports");
		Group g2 = new Group("Games");
		Group g3 = new Group("Video Games");
		Group g4 = new Group("Foods");
		Group g5 = new Group("Apples");
		
		c1.addGroup(g1);
		c1.addGroup(g2);
		c1.addGroup(g3);
		c1.addGroup(g4);
		c1.addGroup(g5);
		
		sm.addCategory(c1);
		
		Group actual = sm.getGroupByName("Foods");
		
		assertEquals(g4, actual);
	}
	
	@Test
	void testGetGroupByName_Failure() {
		
		SystemManager sm = new SystemManager();
		
		category c1 = new category("fun");

		Group g1 = new Group("Sports");
		Group g2 = new Group("Games");
		Group g3 = new Group("Video Games");
		Group g4 = new Group("Foods");
		Group g5 = new Group("Apples");
		
		c1.addGroup(g1);
		c1.addGroup(g2);
		c1.addGroup(g3);
		c1.addGroup(g4);
		c1.addGroup(g5);
		
		sm.addCategory(c1);
		
		Group actual = sm.getGroupByName("testing");
		
		assertEquals(null, actual);
	}
	
	
	@Test
	void testGetUserByUsername_Success() {
		
		SystemManager manager = new SystemManager();
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		
		User actual = manager.getUserByUsername("theWiz");
		
		assertEquals(u2, actual);
	}
	
	@Test
	void testGetUserByUsername_Failure() {
		
		SystemManager manager = new SystemManager();
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		
		User actual = manager.getUserByUsername("testing");
		
		assertEquals(null, actual);
	}
	
	@Test
	void testIsLoggedIn() {
		
		SystemManager sm = new SystemManager();
		
		User u1 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		
		sm.addUser(u1);

		sm.login("LegalTrouble", "D@uble&Tr@uble");

		boolean expected = true;
		boolean actual = sm.isLoggedIn();

		assertEquals(expected, actual);
	}
	
	@Test
	void testIsAdmin() {

	SystemManager sm = new SystemManager();

	Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
	Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");

	sm.addAdmin(a1);
	sm.addAdmin(a2);
	
	sm.login("jackster3", "HKb@wser!");

	boolean expected = true;
	boolean actual = sm.isAdmin();

	assertEquals(expected, actual);
	}
	
	@Test
	void testGetCurrentUser() {

		SystemManager sm = new SystemManager();
		
		User u1 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		
		sm.addUser(u1);

		sm.login("LegalTrouble", "D@uble&Tr@uble");
		
		ArrayList<User> actual = new ArrayList<>();
		actual.add(sm.getCurrentUser());
		
		ArrayList<User> expected = new ArrayList<>();
		expected.add(u1);

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetCurrentCategoryAndSetCurrentCategory() {

		SystemManager sm = new SystemManager();
		
		category c1 = new category("fun");

		sm.setCurrentCategory(c1);	

		assertEquals(c1, sm.getCurrentCategory());
	}
	
	@Test
	void testGetCurrentGroupAndSetCurrentGroup() {

		SystemManager sm = new SystemManager();
		
		Group g1 = new Group("fun");

		sm.setCurrentGroup(g1);	

		assertEquals(g1, sm.getCurrentGroup());
	}
	
	@Test
	void testGetCurrentPostAndSetCurrentPost() {

		SystemManager sm = new SystemManager();
		User u1 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
	
		Group g1 = new Group("fun");

		
		membership m = new membership(u1, g1);

		
		Post p1 = new Post(m, "fun", "dsuagd", 1);

		sm.setCurrentPost(p1);	

		assertEquals(p1, sm.getCurrentPost());
	}
	
	@Test
	void testLogout() {

		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		sm.login("LegalTrouble", "D@uble&Tr@uble");
		
		sm.logout();
	
		assertEquals(null, sm.getCurrentUser());
	}
	
	@Test
	void testSuspendUser() {

		SystemManager sm = new SystemManager();
		
		Group testGroup1 = new Group("MembersTest");

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, testGroup1);
		membership m2 = new membership(u2, testGroup1);
		membership m3 = new membership(u3, testGroup1);
		membership m4 = new membership(u4, testGroup1);
		membership m5 = new membership(u5, testGroup1);
		
		testGroup1.addMember(m1);
		testGroup1.addMember(m2);
		testGroup1.addMember(m3);
		testGroup1.addMember(m4);
		testGroup1.addMember(m5);
		
		Suspended s5 = new Suspended(u5, testGroup1);

		
		ArrayList<membership> expected = new ArrayList<>();
		
		expected.add(m1);
		expected.add(m2);
		expected.add(m3);
		expected.add(m4);

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);
		
		
		sm.suspendUser(s5);
		ArrayList<membership> actual = testGroup1.getMembers();

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetAllSuspensions() {

		SystemManager sm = new SystemManager();
		
		category c = new category("ds");	
		Group testGroup1 = new Group("MembersTest");
		sm.addCategory(c);
		c.addGroup(testGroup1);

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		Suspended s1 = new Suspended(u1, testGroup1);
		Suspended s2 = new Suspended(u2, testGroup1);
		Suspended s3 = new Suspended(u3, testGroup1);
		Suspended s4 = new Suspended(u4, testGroup1);
		Suspended s5 = new Suspended(u5, testGroup1);

		testGroup1.addSuspended(s1);
		testGroup1.addSuspended(s2);
		testGroup1.addSuspended(s3);
		testGroup1.addSuspended(s4);
		testGroup1.addSuspended(s5);
		
		ArrayList<Suspended> expected = new ArrayList<>();
		
		expected.add(s1);
		expected.add(s2);
		expected.add(s3);
		expected.add(s4);
		expected.add(s5);

		ArrayList<Suspended> actual = sm.getAllSuspensions();

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetAllSuspensions_ByUsername() {

		SystemManager sm = new SystemManager();
		
		category c = new category("ds");	
		Group testGroup1 = new Group("MembersTest");
		sm.addCategory(c);
		c.addGroup(testGroup1);

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		Suspended s1 = new Suspended(u1, testGroup1);
		Suspended s2 = new Suspended(u2, testGroup1);
		Suspended s3 = new Suspended(u3, testGroup1);
		Suspended s4 = new Suspended(u4, testGroup1);
		Suspended s5 = new Suspended(u5, testGroup1);

		testGroup1.addSuspended(s3);
		testGroup1.addSuspended(s2);
		testGroup1.addSuspended(s4);
		testGroup1.addSuspended(s5);
		testGroup1.addSuspended(s1);
		
		ArrayList<Suspended> expected = new ArrayList<>();
		
		expected.add(s5);
		expected.add(s1);
		expected.add(s4);
		expected.add(s2);
		expected.add(s3);

		ArrayList<Suspended> actual = sm.getAllSuspensions_ByUsername();

		assertEquals(expected, actual);
	}
	
	@Test
	void testReinstateSuspendUser() {

		SystemManager sm = new SystemManager();
		
		Group testGroup1 = new Group("MembersTest");

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, testGroup1);
		membership m2 = new membership(u2, testGroup1);
		membership m3 = new membership(u3, testGroup1);
		membership m4 = new membership(u4, testGroup1);
		membership m5 = new membership(u5, testGroup1);
		
		testGroup1.addMember(m1);
		testGroup1.addMember(m2);
		testGroup1.addMember(m3);
		testGroup1.addMember(m4);
		testGroup1.addMember(m5);
		
		Suspended s5 = new Suspended(u5, testGroup1);

		ArrayList<membership> expected = new ArrayList<>();
		
		expected.add(m1);
		expected.add(m2);
		expected.add(m3);
		expected.add(m4);
		expected.add(m5);

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);
		
		sm.suspendUser(s5);
		sm.reinstateSuspendedUser(s5, m5);
		ArrayList<membership> actual = testGroup1.getMembers();

		assertEquals(expected, actual);
	}
	
	@Test
	void testBanUser() {

		SystemManager sm = new SystemManager();
		
		Group testGroup1 = new Group("MembersTest");

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, testGroup1);
		membership m2 = new membership(u2, testGroup1);
		membership m3 = new membership(u3, testGroup1);
		membership m4 = new membership(u4, testGroup1);
		membership m5 = new membership(u5, testGroup1);
		
		testGroup1.addMember(m1);
		testGroup1.addMember(m2);
		testGroup1.addMember(m3);
		testGroup1.addMember(m4);
		testGroup1.addMember(m5);
		
		Banned b5 = new Banned(u5, testGroup1);

		testGroup1.addBanned(b5);
		
		ArrayList<Banned> expected = new ArrayList<>();
		
		expected.add(b5);

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);
		
		sm.banUser(b5);
		ArrayList<Banned> actual = testGroup1.getBanned();

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetAllBans() {

		SystemManager sm = new SystemManager();
		
		category c = new category("ds");	
		Group testGroup1 = new Group("MembersTest");
		sm.addCategory(c);
		c.addGroup(testGroup1);

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		Banned s1 = new Banned(u1, testGroup1);
		Banned s2 = new Banned(u2, testGroup1);
		Banned s3 = new Banned(u3, testGroup1);
		Banned s4 = new Banned(u4, testGroup1);
		Banned s5 = new Banned(u5, testGroup1);

		testGroup1.addBanned(s1);
		testGroup1.addBanned(s2);
		testGroup1.addBanned(s3);
		testGroup1.addBanned(s4);
		testGroup1.addBanned(s5);
		
		ArrayList<Banned> expected = new ArrayList<>();
		
		expected.add(s1);
		expected.add(s2);
		expected.add(s3);
		expected.add(s4);
		expected.add(s5);

		ArrayList<Banned> actual = sm.getAllBans();

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetAllBans_ByUsername() {

		SystemManager sm = new SystemManager();
		
		category c = new category("ds");	
		Group testGroup1 = new Group("MembersTest");
		sm.addCategory(c);
		c.addGroup(testGroup1);

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		Banned s1 = new Banned(u1, testGroup1);
		Banned s2 = new Banned(u2, testGroup1);
		Banned s3 = new Banned(u3, testGroup1);
		Banned s4 = new Banned(u4, testGroup1);
		Banned s5 = new Banned(u5, testGroup1);

		testGroup1.addBanned(s1);
		testGroup1.addBanned(s2);
		testGroup1.addBanned(s3);
		testGroup1.addBanned(s4);
		testGroup1.addBanned(s5);
		
		ArrayList<Banned> expected = new ArrayList<>();
		
		expected.add(s5);
		expected.add(s1);
		expected.add(s4);
		expected.add(s2);
		expected.add(s3);

		ArrayList<Banned> actual = sm.getAllBans_ByUsername();

		assertEquals(expected, actual);
	}
	
	@Test
	void testflagPost() {
		
		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		
		sm.flagPost(testPost1);
		Boolean actual = testPost1.getFlag();
		assertEquals(true, actual);
	}
	
	@Test
	void testflagResponse() {
		
		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);

		sm.flagResponse(testResponse1);
		Boolean actual = testResponse1.getFlag();
		assertEquals(true, actual);
	}
	
	@Test
	void testGetAllFlaggedPostAndResponses() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		sm.flagResponse(testResponse1);
		sm.flagPost(testPost1);
		
		ArrayList<Object> expected = new ArrayList<>();
		
		expected.add(testPost1);
		expected.add(testResponse1);

		ArrayList<Object> actual = sm.getAllFlaggedPostAndResponses();

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetPostByGroupId() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 0);
		
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		
		sm.flagPost(testPost1);
		
		Post actual = sm.getPostByGroupId(testGroup, 0);

		assertEquals(testPost1, actual);
	}
	
	@Test
	void testGetAllFlaggedPost() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		
		sm.flagPost(testPost1);
		
		ArrayList<Post> expected = new ArrayList<>();
		
		expected.add(testPost1);

		ArrayList<Post> actual = sm.getAllFlaggedPost();

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetAllFlaggedResponses() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		sm.flagResponse(testResponse1);
		
		ArrayList<Post> expected = new ArrayList<>();
		
		expected.add(testResponse1);

		ArrayList<Post> actual = sm.getAllFlaggedResponses();

		assertEquals(expected, actual);
	}

	@Test
	void testRemoveFlagOnPost() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		Post testPost2 = new Post (m, "I'm posting.", "This is the message", 1);

		
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testGroup.addNewPost(testPost2);
		
		sm.flagPost(testPost1);
		sm.flagPost(testPost2);
		sm.removeFlagOnPost(testPost2);
		
		ArrayList<Post> expected = new ArrayList<>();
		
		expected.add(testPost1);

		ArrayList<Post> actual = sm.getAllFlaggedPost();

		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveFlagOnResponse() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Response testResponse2 = new Response(m, "I disagree.", 1, 1);		

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		testPost1.addNewResponse(testResponse2);

		
		sm.flagResponse(testResponse1);
		sm.flagResponse(testResponse2);
		sm.removeFlagOnResponse(testResponse2);

		
		ArrayList<Post> expected = new ArrayList<>();
		
		expected.add(testResponse1);

		ArrayList<Post> actual = sm.getAllFlaggedResponses();

		assertEquals(expected, actual);
	}
	
	@Test
	void testUpVotePost_NewUpVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);	
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		sm.upvotePost(testPost1);

		assertEquals(1, testPost1.getScore());
	}
	
	@Test
	void testUpVotePost_Existing_RemoveUpVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);	
		
		Voted v = new Voted(testUser, testPost1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		v.up();
		testPost1.addScore();
		sm.upvotePost(testPost1);

		assertEquals(0, testPost1.getScore());
	}
	
	@Test
	void testUpVotePost_Existing_ChangeToUpVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);	
		
		Voted v = new Voted(testUser, testPost1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		v.down();
		testPost1.subScore();
		sm.upvotePost(testPost1);

		assertEquals(1, testPost1.getScore());
	}
	
	@Test
	void testUpVotePost_Existing_NotVoted() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);	
		
		Voted v = new Voted(testUser, testPost1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		sm.upvotePost(testPost1);

		assertEquals(1, testPost1.getScore());
	}
	
	
	@Test
	void testDownVotePost_NewDownVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);	
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		sm.downvotePost(testPost1);

		assertEquals(-1, testPost1.getScore());
	}
	
	@Test
	void testDownVotePost_Existing_RemoveDownVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);	
		
		Voted v = new Voted(testUser, testPost1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		v.down();
		testPost1.subScore();
		sm.downvotePost(testPost1);

		assertEquals(0, testPost1.getScore());
	}
	
	@Test
	void testDownVotePost_Existing_ChangeToDownVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);	
		
		Voted v = new Voted(testUser, testPost1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		v.up();
		testPost1.addScore();
		sm.downvotePost(testPost1);

		assertEquals(-1, testPost1.getScore());
	}
	
	@Test
	void testDownVotePost_Existing_NotVoted() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);	
		
		Voted v = new Voted(testUser, testPost1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		sm.downvotePost(testPost1);

		assertEquals(-1, testPost1.getScore());
	}
	
	
	@Test
	void testUpVoteResponse_NewUpVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		sm.upvoteResponse(testResponse1);

		assertEquals(1, testResponse1.getScore());
	}
	
	@Test
	void testUpVoteResponse_Existing_RemoveUpVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		Voted v = new Voted(testUser, testResponse1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		v.up();
		testResponse1.addScore();
		sm.upvoteResponse(testResponse1);

		assertEquals(0, testResponse1.getScore());
	}
	
	@Test
	void testUpVoteResponse_Existing_ChangeToUpVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		Voted v = new Voted(testUser, testResponse1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		v.down();
		testResponse1.subScore();
		sm.upvoteResponse(testResponse1);

		assertEquals(1, testResponse1.getScore());
	}
	
	@Test
	void testUpVoteResponse_Existing_NotVoted() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		Voted v = new Voted(testUser, testResponse1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		sm.upvoteResponse(testResponse1);

		assertEquals(1, testResponse1.getScore());
	}
	
	
	@Test
	void testDownVoteResponse_NewDownVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		sm.downvoteResponse(testResponse1);

		assertEquals(-1, testResponse1.getScore());
	}
	
	@Test
	void testDownVoteResponse_Existing_RemoveDownVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		Voted v = new Voted(testUser, testResponse1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		v.down();
		testResponse1.subScore();
		sm.downvoteResponse(testResponse1);

		assertEquals(0, testResponse1.getScore());
	}
	
	@Test
	void testDownVoteResponse_Existing_ChangeToDownVote() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		Voted v = new Voted(testUser, testResponse1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		v.up();
		testResponse1.addScore();
		sm.downvoteResponse(testResponse1);

		assertEquals(-1, testResponse1.getScore());
	}
	
	@Test
	void testDownVoteResponse_Existing_NotVoted() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		
		Voted v = new Voted(testUser, testResponse1);
		
		sm.addUser(testUser);
		sm.login("ID", "pw");
		category c = new category("ds");	
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testPost1.addNewResponse(testResponse1);
		
		testUser.addVoted(v);
		sm.downvoteResponse(testResponse1);

		assertEquals(-1, testResponse1.getScore());
	}

	
	@Test
	void testGetPosts_ByScore() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response testResponse1 = new Response(m, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());
		Post testPost2 = new Post (m, "I'm posting.", "This is the message", testGroup.getPostId());

		
		category c = new category("ds");
		sm.addUser(testUser);
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testGroup.addNewPost(testPost2);

		testPost1.addNewResponse(testResponse1);
		
		sm.login("ID", "pw");
		sm.upvotePost(testPost1);
		sm.upvotePost(testPost2);
		

		ArrayList<Post> expected = new ArrayList<>();
		expected.add(testPost1);
		expected.add(testPost2);

		ArrayList<Post> actual = sm.getPosts_ByScore();
	
		assertEquals(expected, actual);
	}
	
	@Test
	void testViewMostUpVotedUsers() {

		SystemManager sm = new SystemManager();

		Group testGroup = new Group("Standard Name");
		User testUser1 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		User testUser2 = new User("Bob", "ID1", "pw", "11/11/2001", "Valdosta", "GA");

		membership m1 = new membership(testUser1, testGroup);
		membership m2 = new membership(testUser2, testGroup);


		Response testResponse1 = new Response(m1, "I disagree.", 1, 0);		
		Post testPost1 = new Post (m1, "I'm posting1.", "This is the message", testGroup.getPostId());
		Post testPost2 = new Post (m2, "I'm posting2.", "This is the message", testGroup.getPostId());
		
		category c = new category("ds");
		sm.addUser(testUser1);
		sm.addUser(testUser2);
		sm.addCategory(c);
		c.addGroup(testGroup);
		testGroup.addNewPost(testPost1);
		testGroup.addNewPost(testPost2);

		testPost2.addNewResponse(testResponse1);
		
		sm.login("ID", "pw");
		sm.upvotePost(testPost1);
		

		ArrayList<User> expected = new ArrayList<>();
		expected.add(testUser1);
		expected.add(testUser2);

		ArrayList<User> actual = sm.viewMostUpVotedUsers();
	
		assertEquals(expected, actual);
	}
	
	@Test
	void testgetSimpleDate_Success() {
		SystemManager manager = new SystemManager();
		Date d1 = new Date();
		
		String day = d1.toString().substring(8,10);
		String month = d1.toString().substring(4,7);
		String year = d1.toString().substring(24);
	 
		String expected = day + " " + month + " " + year;
		
		String result = manager.getSimpleDate(d1);
		
		assertEquals(expected, result);
	}
	
	@Test
	void testgetSimpleDate_Failure() {
		SystemManager manager = new SystemManager();
		Date d1 = new Date();
		Date d2 = new Date(7/11/1111);
		String day = d1.toString().substring(8,10);
		String month = d1.toString().substring(4,7);
		String year = d1.toString().substring(24);
	 
		String expected = day + " " + month + " " + year;
		
		String result = manager.getSimpleDate(d2);
		
		assertNotSame(expected, result);
	}
	

	
	@Test
	void testJoinGroup_Fail() {
		SystemManager manager = new SystemManager();
		Group g1 = new Group("Test1");
		Group g2 = new Group("Test2");
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		manager.joinGroup(u1, g1);

		assertEquals(false, manager.isUserOfGroup(u1, g2));
	}
	
	@Test
	void testLeaveGroup_Success() {
		SystemManager manager = new SystemManager();
		Group g1 = new Group("Test1");
		Group g2 = new Group("Test2");
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		manager.joinGroup(u1, g1);
		manager.joinGroup(u1, g2);
		
		manager.leaveGroup(u1, g1);

		assertEquals(false, manager.isUserOfGroup(u1, g1));
	}
	
	@Test
	void testLeaveGroup_Fail() {
		SystemManager manager = new SystemManager();
		Group g1 = new Group("Test1");
		Group g2 = new Group("Test2");
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		manager.joinGroup(u1, g1);
		manager.joinGroup(u1, g2);
		
		manager.leaveGroup(u1, g1);

		assertEquals(true, manager.isUserOfGroup(u1, g2));
	}
	
	@SuppressWarnings("unused")
	@Test
	void testIsUserBannedFromGroup_Success() {
		SystemManager manager = new SystemManager();
		Group g1 = new Group("Test1");
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		membership m = new membership(u1, g1);
		
		Banned b = new Banned(u1, g1);
		manager.banUser(b);
		
		boolean result = manager.isUserBannedFromGroup(u1, g1);
		
		assertEquals(true, result);
		
	}
	
	@SuppressWarnings("unused")
	@Test
	void testIsUserSuspendedFromGroup_Success() {
		SystemManager manager = new SystemManager();
		Group g1 = new Group("Test1");
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		
		membership m = new membership(u1, g1);
		Suspended s = new Suspended(u1, g1);
		manager.suspendUser(s);
		
		boolean result = manager.isUserSuspendedFromGroup(u1, g1);
		
		assertEquals(true, result);
	}
	
	@SuppressWarnings("unused")
	@Test
	void testIsUserBannedFromGroup_Fail() {
		SystemManager manager = new SystemManager();
		Group g1 = new Group("Test1");
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g1);
		membership m5 = new membership(u5, g1);
		
		Banned b = new Banned(u1, g1);
		manager.banUser(b);
		
		boolean result = manager.isUserBannedFromGroup(u2, g1);
		
		assertEquals(false, result);
		
	}
	
	@SuppressWarnings("unused")
	@Test
	void testIsUserSuspendedFromGroup_Fail() {
		SystemManager manager = new SystemManager();
		Group g1 = new Group("Test1");
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g1);
		membership m5 = new membership(u5, g1);
		
		Suspended s = new Suspended(u1, g1);
		manager.suspendUser(s);
		
		boolean result = manager.isUserSuspendedFromGroup(u2, g1);
		
		assertEquals(false, result);
	}
}

