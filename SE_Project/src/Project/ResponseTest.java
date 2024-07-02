package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ResponseTest {
	
	//Tests getting the responseBody of the response object.
	@Test
	void getMemberTest() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser2, testGroup1);
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);
		
		assertEquals(m.getUser(), testResponse1.getUser());
	}
	
	//Tests getting the responseBody of the response object.
	@Test
	void getResponseBodyTest() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser2, testGroup1);
		Response testResponse1 = new Response(m, "I disagree.", 1, 0);
		
		assertEquals("I disagree.", testResponse1.getPostBody());
	}
	
	@Test
	void testGetResponseWriteData() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Response testResponse1 = new Response(testUser2, testGroup1, "11/11/2007 8:24 AM", "I disagree.",1 , 1, 0);
		testResponse1.setFlagTrue();
		
		String actual = testResponse1.getResponseWriteData();
		
		String expected = "@START\n" + 
							"@RESPONSE\n" + 
							"@USERNAME=ID\n" + 
							"@GNAME=Standard Name\n" + 
							"@DATETIME=11/11/2007 8:24 AM\n" + 
							"@BODYSTART\n" + 
							"I disagree.\n" + 
							"@BODYEND\n" + 
							"@PARENTALID=1\n" +
							"@FLAG\n" + 
							"@SCORE=1\n" + 
							"@RESPONSEID=0\n" + 
							"@END\n\n";
		
		assertEquals(expected, actual);
	}

}
