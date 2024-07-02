package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class ComparatorTest {

	
	@Test
	void testSortCategoriesByName() {
		
		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");
		
		ArrayList<category> actual = new ArrayList<category>();
		
		actual.add(c1);
		actual.add(c2);
		actual.add(c3);
		actual.add(c4);
		actual.add(c5);
		
		String expected[] = {"Apples", "Foods", "Games", "Sports", "Video Games"};
		
		Collections.sort(actual, new SortCategoriesByName());
		
		boolean namesMatch = true;
		
		for (int i = 0 ; i < actual.size() ; i++) {
			
			if (!actual.get(i).getName().equals(expected[i])) {
				namesMatch = false;
				break;
			}
		}
		
		assertEquals(true, namesMatch);
		
	}
	
	
	@Test
	void testSortGroupsByName() {
		
		Group g1 = new Group("Sports");
		Group g2 = new Group("Games");
		Group g3 = new Group("Video Games");
		Group g4 = new Group("Foods");
		Group g5 = new Group("Apples");
		
		ArrayList<Group> actual = new ArrayList<Group>();
		
		actual.add(g1);
		actual.add(g2);
		actual.add(g3);
		actual.add(g4);
		actual.add(g5);
		
		String expected[] = {"Apples", "Foods", "Games", "Sports", "Video Games"};
		
		Collections.sort(actual, new SortGroupsByName());
		
		boolean namesMatch = true;
		
		for (int i = 0 ; i < actual.size() ; i++) {
			
			if (!actual.get(i).getGroupName().equals(expected[i])) {
				namesMatch = false;
				break;
			}
		}
		
		assertEquals(true, namesMatch);
		
	}
	
	
	@Test
	void testSortUsersByName() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		ArrayList<User> actual = new ArrayList<User>();
		
		actual.add(u1);
		actual.add(u2);
		actual.add(u3);
		actual.add(u4);
		actual.add(u5);
		
		String expected[] = {"WestCarolina", "theWiz", "LegalTrouble", "IDK", "jackster3"};
		
		Collections.sort(actual, new SortUsersByName());
		
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
	void testSortUsersByUsername() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		ArrayList<User> actual = new ArrayList<User>();
		
		actual.add(u1);
		actual.add(u2);
		actual.add(u3);
		actual.add(u4);
		actual.add(u5);
		
		String expected[] = {"IDK", "jackster3", "LegalTrouble", "theWiz", "WestCarolina"};
		
		Collections.sort(actual, new SortUsersByUsername());
		
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
	void testSortSuspensionsByUsername() {
		
		Group g1 = new Group("haha");
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		Suspended s1 = new Suspended(u1, g1);
		Suspended s2 = new Suspended(u2, g1);
		Suspended s3 = new Suspended(u3, g1);
		Suspended s4 = new Suspended(u4, g1);
		Suspended s5 = new Suspended(u5, g1);

		ArrayList<Suspended> expected = new ArrayList<Suspended>();
		
		expected.add(s5);
		expected.add(s1);
		expected.add(s4);
		expected.add(s2);
		expected.add(s3);
		
		ArrayList<Suspended> actual = new ArrayList<Suspended>();
		actual.add(s1);
		actual.add(s2);
		actual.add(s3);
		actual.add(s4);
		actual.add(s5);

				
		Collections.sort(actual, new SortSuspensionsByUsername());
		
		assertEquals(actual, expected);
		
	}
	
	@Test
	void testSortPostsByCombinedScore() {
		
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo", 1, 0);
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		Post testPost2 = new Post (m, "I'm posting.", "This is the message", 2);
		r1.addScore();
		testPost1.addNewResponse(r1);
		testPost1.addScore();
		testPost1.addScore();
		testPost2.addScore();
		
		ArrayList<Post> expected = new ArrayList<Post>();
		expected.add(testPost1);
		expected.add(testPost2);

		ArrayList<Post> actual = new ArrayList<Post>();
		actual.add(testPost1);
		actual.add(testPost2);

		Collections.sort(actual, new SortPostsByCombinedScore());
		
		
		assertEquals(actual, expected);
		
	}
	
	@Test
	void testSortBannedByUsername() {
		
		Group g1 = new Group("haha");
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		Banned b1 = new Banned(u1, g1);
		Banned b2 = new Banned(u2, g1);
		Banned b3 = new Banned(u3, g1);
		Banned b4 = new Banned(u4, g1);
		Banned b5 = new Banned(u5, g1);

		ArrayList<Banned> expected = new ArrayList<Banned>();
		
		expected.add(b5);
		expected.add(b1);
		expected.add(b4);
		expected.add(b2);
		expected.add(b3);
		
		ArrayList<Banned> actual = new ArrayList<Banned>();
		actual.add(b1);
		actual.add(b2);
		actual.add(b3);
		actual.add(b4);
		actual.add(b5);

				
		Collections.sort(actual, new SortBannedByUsername());
		
		assertEquals(actual, expected);
		
	}

}
