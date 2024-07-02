package Project;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class categoryTest {


	/*
	 * NOTICE:
	 *
	 * Tests will have to be updated as classes are updated to where
	 * constructors take more arguments
	 *
	 */
	
	@Test
	void testGetName() {
		category c = new category("test");

		String actual = c.getName();

		String expected = "test";

		assertEquals(expected, actual);
	}

	@Test
	void testGetGroupsAlphabetically() {
		category c = new category("test");

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");

		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);
		c.addGroup(g4);
		c.addGroup(g5);

		ArrayList<Group> actual = c.getGroupsAlphabetically();

		ArrayList<Group> expected = new ArrayList<>();

		expected.add(g4);
		expected.add(g3);
		expected.add(g1);
		expected.add(g2);
		expected.add(g5);

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetRequestedGroupsAlphabetically() {
		category c = new category("test");

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");

		c.addRequestedGroup(g1);
		c.addRequestedGroup(g2);
		c.addRequestedGroup(g3);
		c.addRequestedGroup(g4);
		c.addRequestedGroup(g5);

		ArrayList<Group> actual = c.getRequestedGroupsAlphabetically();

		ArrayList<Group> expected = new ArrayList<>();

		expected.add(g4);
		expected.add(g3);
		expected.add(g1);
		expected.add(g2);
		expected.add(g5);

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetGroups() {
		category c = new category("test");

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");

		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);
		c.addGroup(g4);
		c.addGroup(g5);

		ArrayList<Group> actual = c.getGroups();

		ArrayList<Group> expected = new ArrayList<>();

		expected.add(g1);
		expected.add(g2);
		expected.add(g3);
		expected.add(g4);
		expected.add(g5);

		assertEquals(expected, actual);
	}



	@Test
	void testCreateGroup_Success() {
		category c = new category("Sports");

		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Tennis");

		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);

		boolean actual = c.createGroup("Basketball");

		assertEquals(true, actual);
	}


	@Test
	void testCreateGroup_Failure() {
		category c = new category("Sports");

		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Tennis");

		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);

		boolean actual = c.createGroup("Tennis");

		assertEquals(false, actual);
	}
	
	@Test
	void testAddGroup_Success() {
		category c = new category("test");

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");

		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);
		c.addGroup(g4);
		c.addGroup(g5);

		Group test = new Group("Golf");
		Boolean actual = c.addGroup(test);

		assertEquals(true, actual);
	}

	@Test
	void testAddGroup_Failure() {
		category c = new category("test");

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");

		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);
		c.addGroup(g4);
		c.addGroup(g5);

		Group test = new Group("Soccer");
		Boolean actual = c.addGroup(test);


		assertEquals(false, actual);
	}
	
	@Test
	void testAddRequestedGroup_Success() {
		category c = new category("test");

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");

		c.addRequestedGroup(g1);
		c.addRequestedGroup(g2);
		c.addRequestedGroup(g3);
		c.addRequestedGroup(g4);
		c.addRequestedGroup(g5);

		Group test = new Group("Golf");
		Boolean actual = c.addRequestedGroup(test);

		assertEquals(true, actual);
	}

	@Test
	void testAddRequestedGroup_Failure() {
		category c = new category("test");

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");

		c.addRequestedGroup(g1);
		c.addRequestedGroup(g2);
		c.addRequestedGroup(g3);
		c.addRequestedGroup(g4);
		c.addRequestedGroup(g5);

		Group test = new Group("Soccer");
		Boolean actual = c.addRequestedGroup(test);


		assertEquals(false, actual);
	}
	
	@Test
	void testIsGroupInCategory() {
		category c1 = new category("test");
		category c2 = new category("yoooo");

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");
  	
    c1.addGroup(g1);
		c1.addGroup(g2);
		c2.addGroup(g3);
		c2.addGroup(g4);
		c2.addGroup(g5);

		Boolean actual = c1.isGroupInCategory(g1.getGroupName());

		assertEquals(true, actual);
	}

  @Test
	void testGetCategoryWriteData() {
		category c = new category("Sports");
		
		
		String actual = c.getCategoryWriteData();
		
		String expected = "@START\n" + 
							"@CATEGORY\n" + 
							"@NAME=Sports\n" +  
							"@END\n\n";
		
		assertEquals(expected, actual);
	}
  
  
  @Test
  void testCompareTo_Success() {
	  
	  category c1 = new category("test");
	  category c2 = new category("test");
	  
	  int actual = c1.compareTo(c2);
	  
	  assertEquals(1, actual);
	  
  }
  
  @Test
  void testCompareTo_Failure() {
	  
	  category c1 = new category("test");
	  category c2 = new category("Test1");
	  
	  int actual = c1.compareTo(c2);
	  
	  assertEquals(0, actual);
	  
  }
  
  
  @Test 
  void testCompareName_Success() {
	  
	  category c1 = new category("test");
	  
	  boolean actual = c1.compareName("test");
	  
	  assertEquals(true, actual);
	  
  }
  
  @Test 
  void testCompareName_Failure() {
	  
	  category c1 = new category("test");
	  
	  boolean actual = c1.compareName("fail");
	  
	  assertEquals(false, actual);
	  
  }
}
