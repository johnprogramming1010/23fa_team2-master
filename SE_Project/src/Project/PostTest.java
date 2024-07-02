package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;


public class PostTest {
		
	//Testing Constructor and getGroup//
	@Test
	void getGroupTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);

		assertEquals(testGroup, testPost1.getGroup());
	}
	
	//Testing getUser//
	@Test
	void getUserTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		
		assertEquals(testUser, testPost1.getUser());
	}
	
	//Tests getting the postBody
	@Test
	void getPostBodyTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);


		assertEquals("This is the message", testPost1.getPostBody());
	}
	
	//Test to getting the original Post Title
	@Test
	void getPostTitleTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);


		assertEquals("I'm posting.", testPost1.getPostTitle());
	}
	
	//Tests getting the response
	@Test
	void getResponse() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

				
		Post testPost1 = new Post(m, "I'm posting.", "This is the message", 1);
		membership m1 = new membership(testUser, testGroup);
		
		Response r = new Response(m1, "n", 1, 0);
		
		ArrayList<Response> expected = new ArrayList<>();
		expected.add(r);


		testPost1.addNewResponse(r);
				
		ArrayList<Response> r1 =  testPost1.getResponse();
		
		assertEquals(expected, r1);
	}
	
	//Tests getting the score
	@Test
	void getScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		
		assertEquals(0, testPost1.getScore());
	}
	
	
	//Not totally sure how to check this without having us physically just check the time printed, test should always fail because they are different times
	@Ignore		//FIXME: Change to compare less specific strings //
	void getTimeTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		Date timeTest = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);

		String actual = df.format(testPost1.getTime());
		String expected = df.format(timeTest);
		
		assertEquals(expected, actual);
	}
	
	//Tests adding the score, end score should be positive 1
	@Test
	void addScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		testPost1.addScore();
		
		assertEquals(1, testPost1.getScore());
	}
	
	//Tests subtracting from the score, end should be negative 1
	@Test
	void subScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		testPost1.subScore();
		
		assertEquals(-1, testPost1.getScore());
	}
	

	
	
	@Test
	void testGetPostWriteData() {
		
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");

		Post testPost1 = new Post (testUser, testGroup, "10/10/2022 12:27 PM", "I'm posting.", "This is the message", 1, 2, 0);
		testPost1.setFlagTrue();
		
		String actual = testPost1.getPostWriteData();
		
		String expected = "@START\n" + 
							"@POST\n" + 
							"@USERNAME=ID\n" + 
							"@GNAME=Standard Name\n" + 
							"@DATETIME=10/10/2022 12:27 PM\n" + 
							"@TITLE=I'm posting.\n" + 
							"@BODYSTART\n" +
							"This is the message\n" + 
							"@BODYEND\n" + 
							"@PSTID=1\n" + 
							"@FLAG\n" + 
							"@SCORE=2\n" +
							"@RESPONSEID=0\n" +
							"@END\n\n";
		
		assertEquals(expected, actual);
		
	}
	

	//Testing addResponse//
	@Test
	void addResponseTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		ArrayList<Response> expected = new ArrayList<>();
		membership m = new membership(testUser, testGroup);

//		Response r1 = new Response(m, "noooo", 1);
		Response r2 = new Response(m, "no0000ooo", 1, 0);
		expected.add(r2);
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		
		testPost1.addNewResponse(r2);

		assertEquals(expected, testPost1.getResponse());
	}
	
	//Testing addResponse//
	@Test
	void removeResponseTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		ArrayList<Response> expected = new ArrayList<>();
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo", 1, 0);
		Response r2 = new Response(m, "no0000ooo", 1, 1);
		expected.add(r2);
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		
		testPost1.addNewResponse(r1);
		testPost1.addNewResponse(r2);
		
		testPost1.removeResponse(r1);

		assertEquals(expected, testPost1.getResponse());
	}
	
	//Testing getFlag//
	@Test
	void getFlagTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		
		Boolean actual = testPost1.getFlag();
		
		assertEquals(false, actual);
	}
	
	//Testing setFlag//
	@Test
	void setFlagTrueTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		
		testPost1.setFlagTrue();
		Boolean actual = testPost1.getFlag();
		
		assertEquals(true, actual);
	}
	
	//Testing setFlag//
	@Test
	void setFlagFalseTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		
		testPost1.setFlagFalse();
		Boolean actual = testPost1.getFlag();
		
		assertEquals(false, actual);
	}
	
	//Tests adding total score
	@Test
	void getTotalScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo", 1, 0);
		Post testPost1 = new Post (m, "I'm posting.", "This is the message", 1);
		r1.addScore();
		testPost1.addNewResponse(r1);
		testPost1.addScore();
		testPost1.addScore();
		
		assertEquals(3, testPost1.getTotalScore());

	}
	
}
