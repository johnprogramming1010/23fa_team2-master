package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VotedTest {

	@SuppressWarnings("unused")
	@Test
	void testVotedConstructor() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);
		Post p = new Post(m, "fdsjgdf", "fdkjshfu", 0);
		Voted v = new Voted(u, p);

		boolean actual = true;
		if (v == null) {
			actual = false;
		}
		
		assertEquals(true, actual);
	}
	
	@Test
	void testUp() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);
		Post p = new Post(m, "fdsjgdf", "fdkjshfu", 0);
		Voted v = new Voted(u, p);
		v.up();
		boolean actual = v.getUp();
		assertEquals(true, actual);
	}	
	
	@Test
	void testDown() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);
		Post p = new Post(m, "fdsjgdf", "fdkjshfu", 0);
		Voted v = new Voted(u, p);
		v.down();
		boolean actual = v.getDown();
		assertEquals(true, actual);
	}	
	
	@Test
	void testGetUser() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);
		Post p = new Post(m, "fdsjgdf", "fdkjshfu", 0);
		Voted v = new Voted(u, p);

		User actual = v.getUser();
		assertEquals(u, actual);
	}	
	
	@Test
	void testGetPost() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);
		Post p = new Post(m, "fdsjgdf", "fdkjshfu", 0);
		Voted v = new Voted(u, p);

		Post actual = v.getPost();
		assertEquals(p, actual);
	}
	
	@Test
	void testCompareTo_Success() {
		
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);
		Post p = new Post(m, "fdsjgdf", "fdkjshfu", 0);
		Voted v = new Voted(u, p);

			
		boolean actual = v.compareTo(v);
		
		assertEquals(true, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentUsernames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		User u2 = new User("Jack", "jackster3", "fail", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m1 = new membership(u1, testGroup1);
		membership m2 = new membership(u2, testGroup1);
		Post p1 = new Post(m1, "fdsjgdf", "fdkjshfu", 0);
		Post p2 = new Post(m2, "fdsjgdf", "fdkjshfu", 1);

		Voted v1 = new Voted(u1, p1);
		Voted v2 = new Voted(u2, p2);

			
		boolean actual = v1.compareTo(v2);
		
		assertEquals(false, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentGroupNames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		User u2 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		Group testGroup2 = new Group("fail Name");
		membership m1 = new membership(u1, testGroup1);
		membership m2 = new membership(u2, testGroup2);
		Post p1 = new Post(m1, "fdsjgdf", "fdkjshfu", 0);
		Post p2 = new Post(m2, "fdsjgdf", "fdkjshfu", 1);

		Voted v1 = new Voted(u1, p1);
		Voted v2 = new Voted(u2, p2);

			
		boolean actual = v1.compareTo(v2);
		
		assertEquals(false, actual);
	}
	
}
