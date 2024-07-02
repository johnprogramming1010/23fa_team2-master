package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class WriteFileTest {
	
	
	private String getFileData(ArrayList<String> fileNames) {

		String totalStr = "";
		
		for (String fileName : fileNames) {

			File dataFile = new File(fileName);
			try {
	
				Scanner reader = new Scanner(dataFile);
	
				while (reader.hasNextLine()) {
	
					String next = reader.nextLine();
					totalStr += next + "\n";
	
	//				if (next.equals("@END")) {
	//					totalStr += "\n";
	//				}
	
				}
	
				reader.close();
	
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}

		return totalStr;

	}
	
	
	@Test
	void testWriteFile_Admins() {
		
		Admin a1 = new Admin("Jeremy", "unth!nk@b1e", "password#3", "04/27/1992", "New York City", "New York", "11/11/2011");
		Admin a2 = new Admin("Grayson", "theGr@yS0n", "r@inB0wR0@DL0s3r", "03/09/2012", "Macon", "Georgia", "03/09/2011");
		Admin a3 = new Admin("Ryan", "RKScandrol", "N0tT311iN", "08/08/1999", "Pensacola", "Florida", "07/07/2010");
		Admin a4 = new Admin("John", "J0hnW!ck", "n0tth3D0g", "01/01/0001", "Boring", "Oregon", "12/29/2020");
		Admin a5 = new Admin("Himanshu", "the01dM@n", "L3t5D0Th!s", "07/23/1836", "Climax", "Michigan", "12/09/2007");
		
		SystemManager manager = new SystemManager();
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Admins.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (Admin a : manager.getAdmins_Alphabetically_ByUsername()) {

			expected = expected.concat(a.getAdminWriteData());

		}

		assertEquals(expected, actual);
		
		
	}

	@Test
	void testWriteFile_Users() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		SystemManager manager = new SystemManager();
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Users.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (User u : manager.getUsers_Alphabetically_ByUsername()) {

			expected = expected.concat(u.getUserWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testWriteFile_Categories() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Foods");
		category c3 = new category("Games");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Categories.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			expected = expected.concat(c.getCategoryWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testWriteFile_Groups() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		c1.addGroup(g1);
		c1.addGroup(g2);
		
		category c2 = new category("Foods");
		Group g3 = new Group("Pizza");
		Group g4 = new Group("Tacos");
		Group g5 = new Group("Steak");
		c2.addGroup(g3);
		c2.addGroup(g4);
		c2.addGroup(g5);
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Groups.txt");
		
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			for (Group g : c.getGroupsAlphabetically()) {

				expected += g.getGroupWriteData(c.getName());

			}

		}

		assertEquals(expected, actual);
		
		
	}
	
	
	@Test
	void testWriteFile_Memberships() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Memberships.txt");

		try {

			WriteFile.writeFile(manager, fileNames);

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (membership m : manager.getAllMemberships()) {

			expected += m.getMembershipWriteData();

		}

		assertEquals(expected, actual);

	}


	@Test
	void testWriteFile_Posts() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Posts.txt");

		try {

			WriteFile.writeFile(manager, fileNames);

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			expected += p.getPostWriteData();

		}

		assertEquals(expected, actual);

	}
	
	
	@Test
	void testWriteFile_Responses() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);
		
		Response r1 = new Response(m1, "r1", 0, 0);
		Response r2 = new Response(m2, "r1", 1, 0);
		Response r3 = new Response(m3, "r1", 2, 0);
		Response r4 = new Response(m4, "r1", 3, 0);
		Response r5 = new Response(m5, "r1", 4, 0);
		
		p1.addNewResponse(r1);
		p2.addNewResponse(r2);
		p3.addNewResponse(r3);
		p4.addNewResponse(r4);
		p5.addNewResponse(r5);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Responses.txt");

		try {
			WriteFile.writeFile(manager, fileNames);
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			for (Response r : p.getResponse()) {
				expected += r.getResponseWriteData();
			}

		}

		assertEquals(expected, actual);

	}

  @Test
  void testWriteFile_Suspended() {
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
  	Suspended s1 = new Suspended(u1, g1);
		Suspended s2 = new Suspended(u2, g1);
		Suspended s3 = new Suspended(u3, g1);
		Suspended s4 = new Suspended(u4, g2);
		Suspended s5 = new Suspended(u5, g2);

		g1.addSuspended(s1);
		g1.addSuspended(s2);
		g1.addSuspended(s3);
		g2.addSuspended(s4);
		g2.addSuspended(s5);
 
		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);
  
  	ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\textFiles\\WriteFile_Test\\WriteFile_Test_Suspended.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
  
		String actual = getFileData(fileNames);
  	String expected = "";
		
		for (Suspended s : manager.getAllSuspensions_ByUsername()) {
			
			expected += s.getSuspendedWriteData();
			
		}
		
		assertEquals(expected, actual);
	}
  
  
  
	void testWriteFile_Banned() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		Banned b1 = new Banned(u1, g1);
		Banned b2 = new Banned(u2, g1);
		Banned b3 = new Banned(u3, g1);
		Banned b4 = new Banned(u4, g2);
		Banned b5 = new Banned(u5, g2);

		g1.addBanned(b1);
		g1.addBanned(b2);
		g1.addBanned(b3);
		g2.addBanned(b4);
		g2.addBanned(b5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Banned.txt");
		
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		
		String expected = "";
		
		for (Banned b : manager.getAllBans_ByUsername()) {
			
			expected += b.getBannedWriteData();
			
		}
		
		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testWriteFile_Voted() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);
		
		Response r1 = new Response(m1, "r1", 0, 0);
		Response r2 = new Response(m2, "r1", 1, 0);
		Response r3 = new Response(m3, "r1", 2, 0);
		Response r4 = new Response(m4, "r1", 3, 0);
		Response r5 = new Response(m5, "r1", 4, 0);
		
		p1.addNewResponse(r1);
		p2.addNewResponse(r2);
		p3.addNewResponse(r3);
		p4.addNewResponse(r4);
		p5.addNewResponse(r5);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);
		
		Voted v1 = new Voted(u1, p1);
		Voted v2 = new Voted(u2, p1);
		Voted v3 = new Voted(u3, r1);
		Voted v4 = new Voted(u4, r2);
		Voted v5 = new Voted(u5, p2);
		
		u1.addVoted(v1);
		v1.up();
		p1.addScore();
		
		u2.addVoted(v2);
		v2.up();
		p1.addScore();
		
		u3.addVoted(v3);
		v3.up();
		r1.addScore();
		
		u4.addVoted(v4);
		v4.down();
		r2.subScore();
		
		u5.addVoted(v5);
		v5.up();
		p2.addScore();

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Voted.txt");
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_PostVoted.txt");
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_ResponseVoted.txt");

		try {
			WriteFile.writeFile(manager, fileNames);
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		fileNames.remove(2);
		fileNames.remove(1);
		String actual = getFileData(fileNames);

		String expected = "";

		for (Voted v : manager.getAllVotes()) {

			expected += v.getVotedWriteData();

		}

		assertEquals(expected, actual);

	}
	
	
	
	
	
	@Test
	void testAddAdminToFile() {
		
		Admin a1 = new Admin("Jeremy", "unth!nk@b1e", "password#3", "04/27/1992", "New York City", "New York", "11/11/2011");
		Admin a2 = new Admin("Grayson", "theGr@yS0n", "r@inB0wR0@DL0s3r", "03/09/2012", "Macon", "Georgia", "03/09/2011");
		Admin a3 = new Admin("Ryan", "RKScandrol", "N0tT311iN", "08/08/1999", "Pensacola", "Florida", "07/07/2010");
		Admin a4 = new Admin("John", "J0hnW!ck", "n0tth3D0g", "01/01/0001", "Boring", "Oregon", "12/29/2020");
		Admin a5 = new Admin("Himanshu", "the01dM@n", "L3t5D0Th!s", "07/23/1836", "Climax", "Michigan", "12/09/2007");
		
		SystemManager manager = new SystemManager();
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddAdmin.txt");
		
		Admin test = new Admin("Testing", "AddTest", "Testing1234", "10/10/10", "Valdosta", "Georgia", "04/02/1978");
		
		try {
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addAdminToFile(test, fileNames.get(0));
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Admin a : manager.getAdmins_Alphabetically_ByUsername()) {

			expected += a.getAdminWriteData();

		}
		expected += test.getAdminWriteData();

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testAddUserToFile() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		SystemManager manager = new SystemManager();
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddUser.txt");
		
		User test = new User("Testing", "AddTest", "Testing1234", "10/10/10", "Valdosta", "Georgia", "04/02/1978");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addUserToFile(test, fileNames.get(0));
			
		} 
		catch (IOException e) {
			
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (User u : manager.getUsers_Alphabetically_ByUsername()) {

			expected = expected.concat(u.getUserWriteData());

		}
		expected += test.getUserWriteData();

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testAddCategoryToFile() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Foods");
		category c3 = new category("Games");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddCategory.txt");
		
		category test = new category("Tests");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addCategoryToFile(test, fileNames.get(0));
			
			assertEquals(true, true);
			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			expected = expected.concat(c.getCategoryWriteData());

		}
		expected += test.getCategoryWriteData();

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testAddGroupToFile() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		c1.addGroup(g1);
		c1.addGroup(g2);
		
		category c2 = new category("Foods");
		Group g3 = new Group("Pizza");
		Group g4 = new Group("Tacos");
		Group g5 = new Group("Steak");
		c2.addGroup(g3);
		c2.addGroup(g4);
		c2.addGroup(g5);
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddGroup.txt");
		
		Group test = new Group("Donuts");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addGroupToFile(test, fileNames.get(0), c2.getName());
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			for (Group g : c.getGroupsAlphabetically()) {

				expected += g.getGroupWriteData(c.getName());

			}

		}
		expected += test.getGroupWriteData(c2.getName());

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testAddSuspendedToFile() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		Suspended s1 = new Suspended(u1, g1);
		Suspended s2 = new Suspended(u2, g1);
		Suspended s3 = new Suspended(u3, g1);
		Suspended s4 = new Suspended(u4, g2);
		Suspended s5 = new Suspended(u5, g2);

		g1.addSuspended(s1);
		g1.addSuspended(s2);
		g1.addSuspended(s3);
		g2.addSuspended(s4);
		g2.addSuspended(s5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);
		
		Suspended test = new Suspended(u1, g2);
		
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\textFiles\\WriteFile_Test\\WriteFile_Test_AddSuspended.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addSuspendedToFile(test, fileNames.get(0));
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		
		String expected = "";
		
		for (Suspended s : manager.getAllSuspensions_ByUsername()) {
			
			expected += s.getSuspendedWriteData();
			
		}
		expected += test.getSuspendedWriteData();
		
		assertEquals(expected, actual);
		
	}

	void testAddMembershipToFile() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddMembership.txt");
		
		membership test = new membership(u1, g2);

		try {

			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addMembershipToFile(test, fileNames.get(0));

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (membership m : manager.getAllMemberships()) {

			expected += m.getMembershipWriteData();

		}
		expected += test.getMembershipWriteData();

		assertEquals(expected, actual);

	}
	
	
	@Test
	void testAddPostToFile() {
		
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddPost.txt");
		
		Post test = new Post(m1, "Test#6", "Testing", 5);

		try {

			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addPostToFile(test, fileNames.get(0));

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			expected += p.getPostWriteData();

		}
		expected += test.getPostWriteData(true);

		assertEquals(expected, actual);
	}
	
	@Test
	void testAddResponseToFile() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);
		
		Response r1 = new Response(m1, "r1", 0, 0);
		Response r2 = new Response(m2, "r1", 1, 0);
		Response r3 = new Response(m3, "r1", 2, 0);
		Response r4 = new Response(m4, "r1", 3, 0);
		Response r5 = new Response(m5, "r1", 4, 0);
		
		p1.addNewResponse(r1);
		p2.addNewResponse(r2);
		p3.addNewResponse(r3);
		p4.addNewResponse(r4);
		p5.addNewResponse(r5);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddResponse.txt");
		
		Response test = new Response(m1, "test", 0, 1);
		

		try {

			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addResponseToFile(test, fileNames.get(0));

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			for (Response r : p.getResponse()) {
				expected += r.getResponseWriteData();
			}

		}
		expected += test.getResponseWriteData(true);

		assertEquals(expected, actual);

	}

	
	@Test
	void testAddBannedToFile() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		Banned b1 = new Banned(u1, g1);
		Banned b2 = new Banned(u2, g1);
		Banned b3 = new Banned(u3, g1);
		Banned b4 = new Banned(u4, g2);
		Banned b5 = new Banned(u5, g2);

		g1.addBanned(b1);
		g1.addBanned(b2);
		g1.addBanned(b3);
		g2.addBanned(b4);
		g2.addBanned(b5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddBanned.txt");
		
		Banned test = new Banned(u1, g2);
		
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addBannedToFile(test, fileNames.get(0));
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		
		String expected = "";
		
		for (Banned b : manager.getAllBans_ByUsername()) {
			
			expected += b.getBannedWriteData();
			
		}
		expected += test.getBannedWriteData();
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testAddVotedToFile() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);
		
		Response r1 = new Response(m1, "r1", 0, 0);
		Response r2 = new Response(m2, "r1", 1, 0);
		Response r3 = new Response(m3, "r1", 2, 0);
		Response r4 = new Response(m4, "r1", 3, 0);
		Response r5 = new Response(m5, "r1", 4, 0);
		
		p1.addNewResponse(r1);
		p2.addNewResponse(r2);
		p3.addNewResponse(r3);
		p4.addNewResponse(r4);
		p5.addNewResponse(r5);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);
		
		Voted v1 = new Voted(u1, p1);
		Voted v2 = new Voted(u2, p1);
		Voted v3 = new Voted(u3, r1);
		Voted v4 = new Voted(u4, r2);
		Voted v5 = new Voted(u5, p2);
		
		u1.addVoted(v1);
		v1.up();
		p1.addScore();
		
		u2.addVoted(v2);
		v2.up();
		p1.addScore();
		
		u3.addVoted(v3);
		v3.up();
		r1.addScore();
		
		u4.addVoted(v4);
		v4.down();
		r2.subScore();
		
		u5.addVoted(v5);
		v5.up();
		p2.addScore();

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddVoted.txt");
		
		Voted test = new Voted(u1, r1);
		test.up();
		r1.addScore();
		
		

		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addVotedToFile(test, fileNames.get(0));
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Voted v : manager.getAllVotes()) {

			expected += v.getVotedWriteData();

		}
		expected += test.getVotedWriteData();

		assertEquals(expected, actual);

	}
	
	
	
	
	@Test
	void testRemoveAdminFromFile() {
		
		Admin a1 = new Admin("Jeremy", "unth!nk@b1e", "password#3", "04/27/1992", "New York City", "New York", "11/11/2011");
		Admin a2 = new Admin("Grayson", "theGr@yS0n", "r@inB0wR0@DL0s3r", "03/09/2012", "Macon", "Georgia", "03/09/2011");
		Admin a3 = new Admin("Ryan", "RKScandrol", "N0tT311iN", "08/08/1999", "Pensacola", "Florida", "07/07/2010");
		Admin a4 = new Admin("John", "J0hnW!ck", "n0tth3D0g", "01/01/0001", "Boring", "Oregon", "12/29/2020");
		Admin a5 = new Admin("Himanshu", "the01dM@n", "L3t5D0Th!s", "07/23/1836", "Climax", "Michigan", "12/09/2007");
		
		SystemManager manager = new SystemManager();
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveAdmin.txt");

		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeAdminFromFile(a4, fileNames.get(0));
			manager.getAdmins_Alphabetically_ByUsername().remove(a4);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (Admin a : manager.getAdmins_Alphabetically_ByUsername()) {

			expected = expected.concat(a.getAdminWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testRemoveUserFromFile() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaHut", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		SystemManager manager = new SystemManager();
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveUser.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeUserFromFile(u3, fileNames.get(0));
			manager.getUsers_Alphabetically_ByUsername().remove(u3);
						
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (User u : manager.getUsers_Alphabetically_ByUsername()) {

			expected = expected.concat(u.getUserWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testRemoveCategoryFromFile() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Foods");
		category c3 = new category("Games");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveCategory.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeCategoryFromFile(c1, fileNames.get(0));
			manager.getCategories_Alphabetically().remove(c1);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			expected = expected.concat(c.getCategoryWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testRemoveGroupFromFile() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		c1.addGroup(g1);
		c1.addGroup(g2);
		
		category c2 = new category("Foods");
		Group g3 = new Group("Pizza");
		Group g4 = new Group("Tacos");
		Group g5 = new Group("Steak");
		c2.addGroup(g3);
		c2.addGroup(g4);
		c2.addGroup(g5);
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveGroup.txt");
		
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeGroupFromFile(g2, fileNames.get(0), c1.getName());
			manager.getCategoryByName("Sports").getGroupsAlphabetically().remove(g2);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			for (Group g : c.getGroupsAlphabetically()) {

				expected += g.getGroupWriteData(c.getName());

			}

		}

		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveResponseFromFile() {

		SystemManager manager1 = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);
		
		Response r1 = new Response(m1, "r1", 0, 0);
		Response r2 = new Response(m2, "r1", 1, 0);
		Response r3 = new Response(m3, "r1", 2, 0);
		Response r4 = new Response(m4, "r1", 3, 0);
		Response r5 = new Response(m5, "r1", 4, 0);
		
		p1.addNewResponse(r1);
		p2.addNewResponse(r2);
		p3.addNewResponse(r3);
		p4.addNewResponse(r4);
		p5.addNewResponse(r5);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager1.addUser(u1);
		manager1.addUser(u2);
		manager1.addUser(u3);
		manager1.addUser(u4);
		manager1.addUser(u5);
		manager1.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveResponse.txt");
		
		

		try {

			WriteFile.writeFile(manager1, fileNames);
			
			String find = r4.getResponseWriteData();
			r4.editResponseBody("Content Removed");
			String replace = r4.getResponseWriteData();
			WriteFile.removeResponseFromFile(find, replace, fileNames.get(0));

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		String actual = this.getFileData(fileNames);

		String expected = "";

		for (Post p : manager1.getAllPost()) {

			for (Response r : p.getResponse()) {
				expected += r.getResponseWriteData();
			}

		}
		assertEquals(expected, actual);

	}
	
	

	
	@Test
	void testRemoveSuspendedFromFile() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		Suspended s1 = new Suspended(u1, g1);
		Suspended s2 = new Suspended(u2, g1);
		Suspended s3 = new Suspended(u3, g1);
		Suspended s4 = new Suspended(u4, g2);
		Suspended s5 = new Suspended(u5, g2);

		g1.addSuspended(s1);
		g1.addSuspended(s2);
		g1.addSuspended(s3);
		g2.addSuspended(s4);
		g2.addSuspended(s5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);
		
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\textFiles\\WriteFile_Test\\WriteFile_Test_RemoveSuspended.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeSuspendedFromFile(s5, fileNames.get(0));
			g2.removeSuspended(s5);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		
		String expected = "";
		
		for (Suspended s : manager.getAllSuspensions_ByUsername()) {
			
			expected += s.getSuspendedWriteData();
			
		}
		
		assertEquals(expected, actual);
		
	}
	
	
	

	@Test
	void testRemoveMembershipFromFile() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveMembership.txt");
		

		try {

			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeMembershipFromFile(m4, fileNames.get(0));
			manager.leaveGroup(u4, g2);

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (membership m : manager.getAllMemberships()) {

			expected += m.getMembershipWriteData();

		}

		assertEquals(expected, actual);

	}
	
	
	@Test
	void testRemoveBannedFromFile() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		Banned b1 = new Banned(u1, g1);
		Banned b2 = new Banned(u2, g1);
		Banned b3 = new Banned(u3, g1);
		Banned b4 = new Banned(u4, g2);
		Banned b5 = new Banned(u5, g2);

		g1.addBanned(b1);
		g1.addBanned(b2);
		g1.addBanned(b3);
		g2.addBanned(b4);
		g2.addBanned(b5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddBanned.txt");
		
		
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeBannedFromFile(b5, fileNames.get(0));
			g2.getBanned().remove(b5);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		
		String expected = "";
		
		for (Banned b : manager.getAllBans_ByUsername()) {
			
			expected += b.getBannedWriteData();
			
		}
		
		assertEquals(expected, actual);
		
		
	}
	
	
	
	
	@Test
	void testRemovePostFromFile() {

		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");

		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);
		
		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemovePost.txt");

		try {

			WriteFile.writeFile(manager, fileNames);
			
			g2.getPost().remove(p4);
			WriteFile.removePostFromFile(p4, fileNames.get(0));

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			expected += p.getPostWriteData();

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testRemoveVotedFromFile() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);
		
		Response r1 = new Response(m1, "r1", 0, 0);
		Response r2 = new Response(m2, "r1", 1, 0);
		Response r3 = new Response(m3, "r1", 2, 0);
		Response r4 = new Response(m4, "r1", 3, 0);
		Response r5 = new Response(m5, "r1", 4, 0);
		
		p1.addNewResponse(r1);
		p2.addNewResponse(r2);
		p3.addNewResponse(r3);
		p4.addNewResponse(r4);
		p5.addNewResponse(r5);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);
		
		Voted v1 = new Voted(u1, p1);
		Voted v2 = new Voted(u2, p1);
		Voted v3 = new Voted(u3, r1);
		Voted v4 = new Voted(u4, r2);
		Voted v5 = new Voted(u5, p2);
		
		u1.addVoted(v1);
		v1.up();
		p1.addScore();
		
		u2.addVoted(v2);
		v2.up();
		p1.addScore();
		
		u3.addVoted(v3);
		v3.up();
		r1.addScore();
		
		u4.addVoted(v4);
		v4.down();
		r2.subScore();
		
		u5.addVoted(v5);
		v5.up();
		p2.addScore();

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveVoted.txt");
		

		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeVotedFromFile(v4, fileNames.get(0));
			v4.cancelVote();
			r2.subScore();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Voted v : manager.getAllVotes()) {

			expected += v.getVotedWriteData();

		}

		assertEquals(expected, actual);

	}

	
	
	@Test
	void testUpdateGroupInFile() {
		
		SystemManager manager = new SystemManager();

		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");


		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p2 = new Post(m2, "Test#2", "testing", 1);



		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		c1.addGroup(g1);
		c1.addGroup(g2);


		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_UpdatePost.txt");

		try {

			WriteFile.writeFile(manager, fileNames);
			
			String find = p2.getPostWriteData();
			p2.addScore();
			String replace = p2.getPostWriteData();
			WriteFile.updateGroupinFile(find, replace, fileNames.get(0));

		}
		
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}


		c1.addGroup(g1);
		c1.addGroup(g2);
		
		category c2 = new category("Foods");
		Group g3 = new Group("Pizza");
		Group g4 = new Group("Tacos");
		Group g5 = new Group("Steak");
		c2.addGroup(g3);
		c2.addGroup(g4);
		c2.addGroup(g5);
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_UpdateGroup.txt");
		
		
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			
			String find = g1.getGroupWriteData(c1.getName());

			String replace = g1.getGroupWriteData(c1.getName());
			WriteFile.updateGroupinFile(find, replace, fileNames.get(0));
			
			

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";


		for (Post p : manager.getAllPost()) {

			expected += p.getPostWriteData();

		for (category c : manager.getCategories_Alphabetically()) {

			for (Group g : c.getGroupsAlphabetically()) {

				expected += g.getGroupWriteData(c.getName());

			}


		}

		assertEquals(expected, actual);
		
		}
	}

	@Test
	void testUpdatePostInFile() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_UpdatePost.txt");

		try {

			WriteFile.writeFile(manager, fileNames);
			
			String find = p4.getPostWriteData();
			p4.addScore();
			String replace = p4.getPostWriteData();
			WriteFile.updatePostInFile(find, replace, fileNames.get(0));

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			expected += p.getPostWriteData();

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testUpdateResponseInFile() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);
		
		Response r1 = new Response(m1, "r1", 0, 0);
		Response r2 = new Response(m2, "r2", 1, 0);
		Response r3 = new Response(m3, "r3", 2, 0);
		Response r4 = new Response(m4, "r4", 3, 0);
		Response r5 = new Response(m5, "r5", 4, 0);
		
		p1.addNewResponse(r1);
		p2.addNewResponse(r2);
		p3.addNewResponse(r3);
		p4.addNewResponse(r4);
		p5.addNewResponse(r5);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_UpdateResponse.txt");

		try {

			WriteFile.writeFile(manager, fileNames);
			
			String find = r3.getResponseWriteData();
			r3.addScore();
			String replace = r3.getResponseWriteData();
			WriteFile.updateResponseInFile(find, replace, fileNames.get(0));
			

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			for (Response r : p.getResponse()) {
				expected += r.getResponseWriteData();
			}

		}

		assertEquals(expected, actual);
		
	}
	
	
	
	@Test
	void testUpdateVotedInFile() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);
		
		Response r1 = new Response(m1, "r1", 0, 0);
		Response r2 = new Response(m2, "r1", 1, 0);
		Response r3 = new Response(m3, "r1", 2, 0);
		Response r4 = new Response(m4, "r1", 3, 0);
		Response r5 = new Response(m5, "r1", 4, 0);
		
		p1.addNewResponse(r1);
		p2.addNewResponse(r2);
		p3.addNewResponse(r3);
		p4.addNewResponse(r4);
		p5.addNewResponse(r5);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);
		
		Voted v1 = new Voted(u1, p1);
		Voted v2 = new Voted(u2, p1);
		Voted v3 = new Voted(u3, r1);
		Voted v4 = new Voted(u4, r2);
		Voted v5 = new Voted(u5, p2);
		
		u1.addVoted(v1);
		v1.up();
		p1.addScore();
		
		u2.addVoted(v2);
		v2.up();
		p1.addScore();
		
		u3.addVoted(v3);
		v3.up();
		r1.addScore();
		
		u4.addVoted(v4);
		v4.down();
		r2.subScore();
		
		u5.addVoted(v5);
		v5.up();
		p2.addScore();

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_UpdateVoted.txt");
		

		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			String find = v5.getVotedWriteData();
			v5.down();
			p2.subScore();
			p2.subScore();
			String replace = v5.getVotedWriteData();
			WriteFile.updateVotedInFile(find, replace, fileNames.get(0));
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Voted v : manager.getAllVotes()) {

			expected += v.getVotedWriteData();

		}

		assertEquals(expected, actual);

	}
	
}
