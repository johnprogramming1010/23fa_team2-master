package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ReadFileTest {

	@Test
	void testReadFile_Failure_FileNotFound() {
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add("fileNotFound.txt");
		boolean fileNotFound = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			fileNotFound = true;
		}
		catch (IncorrectFileFormatException e) {
			fail();
		}
		
		assertEquals(true, fileNotFound);
	}


	@Test
	void testReadFile_Admin_Success() {
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Admin.txt");
		
		try {
		
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<Admin> expected = new ArrayList<Admin>();
			
			Admin a1 = new Admin("Jeremy", "unth!nk@b1e", "password#3", "04/27/1992", 
									"New York City", "New York", "11/11/2011");
			Admin a2 = new Admin("Grayson", "theGr@yS0n", "r@inB0wR0@DL0s3r", "03/09/2012", 
					"Macon", "Georgia", "03/09/2011");
			Admin a3 = new Admin("Ryan", "RKScandrol", "N0tT311iN", "08/08/1999", 
					"Pensacola", "Florida", "07/07/2010");
			Admin a4 = new Admin("John", "J0hnW!ck", "n0tth3D0g", "01/01/0001", 
					"Boring", "Oregon", "12/29/2020");
			Admin a5 = new Admin("Himanshu", "the01dM@n", "L3t5D0Th!s", "07/23/1836", 
					"Climax", "Michigan", "12/09/2007");
			
			expected.add(a4);
			expected.add(a3);
			expected.add(a5);
			expected.add(a2);
			expected.add(a1);
			
			ArrayList<Admin> actual = manager.getAdmins_Alphabetically_ByUsername();
			
			boolean usernamesMatch = true;
			
			for (int i = 0; i < expected.size(); i++) {
				
				//For testing purposes
				//System.out.println(expected.get(i).getId() + ", " + actual.get(i).getId());
				
				if ( !(actual.get(i).getId().equals(expected.get(i).getId())) ) {
					usernamesMatch = false;
					break;
				}
			}
			
			assertEquals(true, usernamesMatch);
			
			/*
			 * When comparing objects, 
			 * assertEquals will only check to see the references point to the same place in memory,
			 * as such, we must personally check through each username
			 */
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testReadFile_Admin_Failure_IncorrectFormat1() {
		
		/*
		 * Incorrect format that should be detected is double starts:
		 * @START
		 * @START
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Admin_Failure_IncorrectFormat1.txt");
		boolean incorrectFileFormat = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFileFormat = true;
		}
		
		assertEquals(true, incorrectFileFormat);
	}
	
	@Test
	void testReadFile_Admin_Failure_IncorrectFormat2() {
		
		/*
		 * Incorrect format that should be detected is missing data:
		 * @BIRTHDATE
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Admin_Failure_IncorrectFormat2.txt");
		boolean incorrectFileFormat = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFileFormat = true;
		}
		
		assertEquals(true, incorrectFileFormat);
	}
	
	@Test
	void testReadFile_Admin_Failure_IncorrectFormat3() {
		
		/*
		 * Incorrect format that should be detected is duplicate information:
		 * @NAME=Grayson
		 * @NAME=John
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Admin_Failure_IncorrectFormat3.txt");
		boolean incorrectFileFormat = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFileFormat = true;
		}
		
		assertEquals(true, incorrectFileFormat);
	}
	
	@Test
	void testReadFile_Admin_Failure_IncorrectFormat4() {
		
		/*
		 * Incorrect format that should be detected is incorrect identifier:
		 * @password=password#3
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Admin_Failure_IncorrectFormat4.txt");
		boolean incorrectFileFormat = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFileFormat = true;
		}
		
		assertEquals(true, incorrectFileFormat);
	}
	
	@Test
	void testReadFile_Admin_Failure_IncorrectFormat5() {
		
		/*
		 * Incorrect format that should be detected, missing start line:
		 * @START
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Admin_Failure_IncorrectFormat5.txt");
		boolean incorrectFileFormat = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFileFormat = true;
		}
		
		assertEquals(true, incorrectFileFormat);
	}
	
	
	
	
	
	@Test
	void testReadFile_User_Success() {
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_User.txt");
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<User> actual = manager.getUsers_Alphabetically_ByUsername();
			
			ArrayList<String> expectedUsernames = new ArrayList<String>();
			
			//In order of name from file
			expectedUsernames.add("IDK");
			expectedUsernames.add("jackster3");
			expectedUsernames.add("LegalTrouble");
			expectedUsernames.add("theWiz");
			expectedUsernames.add("WestCarolina");
			
			boolean usernamesMatch = true;
			
			for (int i = 0; i < expectedUsernames.size(); i++) {
				
				//For testing purposes
				//System.out.println(expectedUsernames.get(i) + ", " + actual.get(i).getId());
				
				if (!(expectedUsernames.get(i).equals(actual.get(i).getId())) ) {
					usernamesMatch = false;
				}
			}
			
			assertEquals(true, usernamesMatch);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
			
		}
	}
	
	@Test
	void testReadFile_User_Failure_IncorrectFormat1() {
		
		/*
		 * Incorrect format that should be detected, multiple end lines:
		 * @END
		 * @END
		 */
		
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_User_Failure_IncorrectFormat1.txt");
		boolean incorrectFormat = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFormat = true;
			
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	@Test
	void testReadFile_User_Failure_IncorrectFormat2() {
		
		/*
		 * Incorrect format that should be detected, missing data:
		 * @USERNAME
		 * @PASSWORD
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_User_Failure_IncorrectFormat2.txt");
		boolean incorrectFormat = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFormat = true;
			
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	@Test
	void testReadFile_User_Failure_IncorrectFormat3() {
		
		/*
		 * Incorrect format that should be detected, duplicate data:
		 * @STATE
		 * @STATE
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_User_Failure_IncorrectFormat3.txt");
		boolean incorrectFormat = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFormat = true;
			
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	@Test
	void testReadFile_User_Failure_IncorrectFormat4() {
		
		/*
		 * Incorrect format that should be detected, incorrect data identifier:
		 * @RegisteredDate
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_User_Failure_IncorrectFormat4.txt");
		boolean incorrectFormat = false;
		
		try {
			ReadFile.readFile(manager, fileNames);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFormat = true;
			
		}
		
		assertEquals(true, incorrectFormat);
	}

	
	
	
	
	@Test
	void testReadFile_Admin_User_Success() {
		
		/*
		 * NOTICE:
		 * For this test I tried to create a comparator to sort a list of Users alphabetically by username
		 * though the comparator never returned a properly sorted list
		 * sorted off of name instead, though username was preferred
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Admin.txt");
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_User.txt");
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<Admin> actualAdmins = manager.getAdmins_Alphabetically_ByUsername();
			ArrayList<User> actualUsers = manager.getUsers_Alphabetically_ByUsername();
			
			ArrayList<String> expectedAdminUsernames = new ArrayList<String>();
			ArrayList<String> expectedUserUsernames = new ArrayList<String>();
			
			//In order of name from file
			expectedAdminUsernames.add("J0hnW!ck");
			expectedAdminUsernames.add("RKScandrol");
			expectedAdminUsernames.add("the01dM@n");
			expectedAdminUsernames.add("theGr@yS0n");
			expectedAdminUsernames.add("unth!nk@b1e");
			
			//In order of name from file
			expectedUserUsernames.add("IDK");
			expectedUserUsernames.add("jackster3");
			expectedUserUsernames.add("LegalTrouble");
			expectedUserUsernames.add("theWiz");
			expectedUserUsernames.add("WestCarolina");
			
			
			boolean usernamesMatch = true;
			
			for (int i = 0; i < expectedAdminUsernames.size(); i++) {
				
				//For testing purposes
				//System.out.println(expectedAdminUsernames.get(i) + ", " + actualAdmins.get(i).getId());
				
				if (!(expectedAdminUsernames.get(i).equals(actualAdmins.get(i).getId())) ) {
					usernamesMatch = false;
					break;
				}
			}
			
			if (usernamesMatch) {
				for (int i = 0; i < expectedUserUsernames.size(); i++) {
					
					//For testing purposes
					//System.out.println(expectedUserUsernames.get(i) + ", " + actualUsers.get(i).getId());
					
					if (!(expectedUserUsernames.get(i).equals(actualUsers.get(i).getId())) ) {
						usernamesMatch = false;
						break;
					}
				}
			}
			
			assertEquals(true, usernamesMatch);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
			
		}
	}
	


	
	
	@Test
	void testReadFile_Category_Success() {
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Category.txt");
		
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<category> actualCats = manager.getCategories_Alphabetically();
			
			ArrayList<String> expectedNames = new ArrayList<String>();
			
			expectedNames.add("Foods");
			expectedNames.add("Games");
			expectedNames.add("Memes");
			expectedNames.add("Sports");
			
			boolean namesMatch = true;
			
			for (int i = 0; i < actualCats.size(); i++) {
				
				if (!actualCats.get(i).getName().equals(expectedNames.get(i))) {
					namesMatch = false;
					break;
				}
				
			}
			
			assertEquals(true, namesMatch);
			
		} catch (FileNotFoundException | IncorrectFileFormatException e) {
			
			e.printStackTrace();
			fail();
		}
		
		
	}
	
	@Test
	void testReadFile_Category_Failure_IncorrectFormat1() {
		
		/*
		 * Incorrect format that should be detected, Duplicate Data:
		 * @NAME
		 * @NAME
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Category_Failure_IncorrectFormat1.txt");
		
		boolean incorrectFormat = false;
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFormat = true;
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	@Test
	void testReadFile_Category_Failure_IncorrectFormat2() {
		
		/*
		 * Incorrect format that should be detected, Incorrect identifier:
		 * @Name
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Category_Failure_IncorrectFormat2.txt");
		
		boolean incorrectFormat = false;
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFormat = true;
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	@Test
	void testReadFile_Category_Failure_IncorrectFormat3() {
		
		/*
		 * Incorrect format that should be detected, Missing end:
		 * @END
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Category_Failure_IncorrectFormat3.txt");
		
		boolean incorrectFormat = false;
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFormat = true;
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	@Test
	void testReadFile_Category_Failure_IncorrectFormat4() {
		
		/*
		 * Incorrect format that should be detected, missing data identifier:
		 * @CATEGORY
		 */
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Category_Failure_IncorrectFormat4.txt");
		
		boolean incorrectFormat = false;
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			incorrectFormat = true;
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	
	
	
	@Test
	void testReadFile_Group_Success() {
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Group.txt");
		
		
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<Group> actualGroups = manager.getAllGroups_Alphabetically();
			
			ArrayList<String> expectedNames = new ArrayList<String>();
			
			expectedNames.add("Football");
			expectedNames.add("Hockey");
			expectedNames.add("Soccer");
			
			boolean namesMatch = true;
			
			for (int i = 0; i < actualGroups.size(); i++) {
				
				if (!actualGroups.get(i).getGroupName().equals(expectedNames.get(i))) {
					namesMatch = false;
					break;
				}
				
			}
			
			assertEquals(true, namesMatch);
			
			
			
		} catch (FileNotFoundException | IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	
	@Test
	void testReadFile_Suspended_Success() {
		
		//Create Users
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		//Create Groups
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		//Create Memberships
		Suspended s1 = new Suspended(u1, g1, "10/10/2007 8:45 PM", "10/10/2008 8:50 PM");
		Suspended s2 = new Suspended(u2, g2, "10/10/2007 8:45 PM", "10/10/2008 8:50 PM");
		Suspended s3 = new Suspended(u3, g2, "10/10/2007 8:45 PM", "10/10/2008 8:50 PM");
		Suspended s4 = new Suspended(u4, g1, "10/10/2007 8:45 PM", "10/10/2008 8:50 PM");
		Suspended s5 = new Suspended(u5, g2, "10/10/2007 8:45 PM", "10/10/2008 8:50 PM");
		Suspended s6 = new Suspended(u2, g1, "10/10/2007 8:45 PM", "10/10/2008 8:50 PM");
		//Create List for Memberships that are expected
		ArrayList<Suspended> expected = new ArrayList<Suspended>();
		//Add Memberships in expected order
		expected.add(s1);
		expected.add(s4);
		expected.add(s6);
		expected.add(s2);
		expected.add(s3);
		expected.add(s5);
		
		
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Suspended.txt");
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<Group> groups = manager.getAllGroups_Alphabetically();
			ArrayList<Suspended> actual = new ArrayList<Suspended>();
			
			for (Group g : groups) {
				actual.addAll(g.getSuspended());
			}
			
			boolean pass = true;
			for (int i = 0; i < expected.size(); i++) {
				
				if (actual.get(i).compareTo(expected.get(i)) == 0) {
					pass = false;
					break;
				}
			}
			
			assertEquals(true, pass);
			
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	
	@Test
	void testReadFile_Post_Success() {
		
		//Create Users
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		//Create Groups
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		//Create Posts
		Post p1 = new Post(u1, g1, "10/10/2008 4:27 AM", "P1", "P1 Body", 1, 666, 0);
		Post p2 = new Post(u2, g1, "10/10/2008 4:27 AM", "P2", "P2 Body", 2, 245, 0);
		Post p3 = new Post(u3, g1, "10/10/2008 4:27 AM", "P3", "P3 Body", 3, -5, 0);
		Post p4 = new Post(u2, g2, "10/10/2008 4:27 AM", "P4", "P4 Body", 4, 0, 0);
		Post p5 = new Post(u4, g2, "10/10/2008 4:27 AM", "P5", "P5 Body", 5, -1200000, 0);
		Post p6 = new Post(u5, g2, "10/10/2008 4:27 AM", "P6", "P6 Body", 6, 17171717, 0);
		//Create List of Posts
		ArrayList<Post> expected = new ArrayList<Post>();
		//Add Posts in Order
		expected.add(p1);
		expected.add(p2);
		expected.add(p3);
		expected.add(p4);
		expected.add(p5);
		expected.add(p6);
		
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Post.txt");
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<Post> actual = manager.getAllPost();
			boolean pass = true;
			
			for (int i = 0 ; i < actual.size(); i++) {
				
				if (actual.get(i).compareTo(expected.get(i)) == 0) {
					pass = false;
					break;
				}
			}
			
			assertEquals(true, pass);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	
	@Test
	void testReadFile_Response_Success() {
		
		//Create Users
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		//Create Groups
		Group g = new Group("Football");
		//Create Responses
		Response r1 = new Response(u1, g, "10/10/2009 4:27 AM", "R1 Body", 1, 1500, 0);
		Response r2 = new Response(u1, g, "10/10/2009 4:27 AM", "R2 Body", 2, -1211, 0);
		Response r3 = new Response(u2, g, "10/10/2009 4:27 AM", "R3 Body", 1, 0, 1);
		Response r4 = new Response(u2, g, "10/10/2009 4:27 AM", "R4 Body", 2, 47, 1);

		//Create List of Responses
		ArrayList<Response> expected = new ArrayList<Response>();
		//Add Responses in Order
		expected.add(r1);
		expected.add(r3);
		expected.add(r2);
		expected.add(r4);
		
		
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Response.txt");
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<Response> actual = new ArrayList<Response>();
			ArrayList<Post> posts = manager.getAllPost();
			
			for (Post p : posts) {
				actual.addAll(p.getResponse());
			}
			
			boolean pass = true;
			
			for (int i = 0 ; i < actual.size(); i++) {
				
				if (actual.get(i).compareTo(expected.get(i)) == 0) {
					pass = false;
					break;
				}
			}
			
			assertEquals(true, pass);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	

	
	@Test
	void testReadFile_Membership_Success() {
		
		//Create Users
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		//Create Groups
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		//Create Memberships
		membership m1 = new membership(u1, g1, "10/10/2007");
		membership m2 = new membership(u2, g2, "10/10/2007");
		membership m3 = new membership(u3, g2, "10/10/2007");
		membership m4 = new membership(u4, g1, "10/10/2007");
		membership m5 = new membership(u5, g2, "10/10/2007");
		membership m6 = new membership(u2, g1, "10/10/2007");
		//Create List for Memberships that are expected
		ArrayList<membership> expected = new ArrayList<membership>();
		//Add Memberships in expected order
		expected.add(m1);
		expected.add(m4);
		expected.add(m6);
		expected.add(m2);
		expected.add(m3);
		expected.add(m5);
		
		
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Membership.txt");
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<Group> groups = manager.getAllGroups_Alphabetically();
			ArrayList<membership> actual = new ArrayList<membership>();
			
			for (Group g : groups) {
				actual.addAll(g.getMembers());
			}
			
			boolean pass = true;
			for (int i = 0; i < actual.size(); i++) {
				
				if (actual.get(i).compareTo(expected.get(i)) == 0) {
					pass = false;
					break;
				}
			}
			
			assertEquals(true, pass);
			
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
		
	}


	@Test
	void testReadFile_Banned_Success() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		//Create Groups
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		//Create Banned
		Banned b1 = new Banned(u1, g1);
		Banned b2 = new Banned(u2, g1);
		Banned b3 = new Banned(u3, g1);
		Banned b4 = new Banned(u4, g2);
		Banned b5 = new Banned(u5, g2);
		
		ArrayList<Banned> expected = new ArrayList<Banned>();
		
		expected.add(b5);
		expected.add(b1);
		expected.add(b4);
		expected.add(b2);
		expected.add(b3);
		
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_Banned.txt");
		
		SystemManager manager = new SystemManager();
		
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<Banned> actual = manager.getAllBans_ByUsername();
			
			boolean namesMatch = true;
			
			for (int i = 0; i < actual.size() ; i++) {
				
				if (expected.get(i).compareTo(actual.get(i)) == 0) {
					System.out.println(expected.get(i).getUser().getId() + ", " + actual.get(i).getUser().getId());
					namesMatch = false;
					
				}
								
			}
			
			assertEquals(true, namesMatch);
			
		} 
		catch (FileNotFoundException | IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	
	
	@Test
	void testReadFile_Voted_UpvotedPost_Success() {
		
		//Create Users
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		//Create Groups
		Group g1 = new Group("Football");
		//Create Posts
		Post p1 = new Post(u1, g1, "10/10/2008 4:27 AM", "P1", "P1 Body", 1, 666, 0);
		
		Voted v1 = new Voted(u1, p1);
		Voted v2 = new Voted(u2, p1);
		Voted v3 = new Voted(u3, p1);
		Voted v4 = new Voted(u4, p1);
		Voted v5 = new Voted(u5, p1);
		
		u1.addVoted(v1);
		u2.addVoted(v2);
		u3.addVoted(v3);
		u4.addVoted(v4);
		u5.addVoted(v5);
		//Create List of Posts
		ArrayList<Voted> expected = new ArrayList<Voted>();
		//Add Posts in Order
		expected.add(v1);
		expected.add(v2);
		expected.add(v3);
		expected.add(v4);
		expected.add(v5);
		
		
		
		SystemManager manager = new SystemManager();
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test\\ReadFile_Test_UpvotedPost.txt");
		
		try {
			
			ReadFile.readFile(manager, fileNames);
			
			ArrayList<Voted> actual = manager.getAllVotes();
			boolean pass = true;
			
			for (int i = 0 ; i < actual.size(); i++) {
				
				if (actual.get(i).compareTo(expected.get(i))) {
					pass = false;
					break;
				}
			}
			
			assertEquals(true, pass);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
		
	}
		
	
	
}
