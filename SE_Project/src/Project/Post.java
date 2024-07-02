package Project;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;


public class Post implements Comparable<Post> {

	protected User user;
	protected Group group;
	protected String postTitle;
	protected String postBody;
	private ArrayList<Response> responses;
	protected Date dateTime;
    protected int score;
    protected boolean flag;
    private int id;
    protected int responseID;
    
    
    
    public Post (membership memberships, String postTitle, String postBody, int Id) {
    	this.user = memberships.getUser();
    	this.group = memberships.getGroup();
    	this.postBody = postBody;
    	this.postTitle = postTitle;
    	this.responses = new ArrayList<>();
    	this.dateTime = new Date();
    	this.score = 0;
    	this.flag = false;
    	this.id = Id;
    	this.responseID = 0;
    }
	
    
	public Post (User u, Group g, String dateTime, String postTitle, String postBody, int id, int score, int responseID) {
    	this.user = u;
    	this.group = g;
    	this.postTitle = postTitle;
    	this.postBody = postBody;
    	this.responses = new ArrayList<>();
    	try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
			this.dateTime = df.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	this.score = score;
    	this.flag = false;
    	this.id = id;
    	this.responseID = responseID;
    }
    //Gets the users
	//test:1
	public User getUser() {
		return user;
	}
	
	//Gets the group
	//test:1
	public Group getGroup() {
		return group;
	}
	
	//Gets the the title of the post
	//test:1
	public String getPostTitle() {
		return postTitle;
	}
	
	//Gets the body of the post
	//test:1
	public String getPostBody() {
		return postBody;
	}
	
	//Gets the ID of the post
	//test:1
	public int getId() {
		return id;
	}
	
	public ArrayList<Response> getResponse() {
		return responses;
	}
	
	//test:1
    //Adds responses into responses
    public boolean addNewResponse(Response r) {
        responses.add(r);
        this.responseID++;
        return true;
    }
    
    //FIXME: add tests
    public boolean addExistingResponse(Response r) {
    	if (r.getResponseID() >= this.responseID) {
    		return false;
    	}
    	else {
    		responses.add(r);
    		return true;
    	}
    }
    
    //test:1
    //removes responses into responses
    public void removeResponse(Response r) {
        responses.remove(r);
    }
	
    //Gets the score
	//test:1
	public int getScore() {
		return score;
	}
	
	//Gets the flag
	//test:1
	public boolean getFlag() {
		return flag;
	}
	
	//Flagging the post
	//test:1
	public void setFlagTrue() {
		flag = true;
	}
	
	//Unflagging a post
	//test:1
	public void setFlagFalse() {
		flag = false;
	}
	
	//test:1
	//Returns date saved in dateTime
	public Date getTime() {
		return dateTime;
	}
	
	//test:1
	//Increases score
	public void addScore() {
		score++;
	}
	
	//test:1
	//Decreases score
	public void subScore() {
		score--;
	}
	
	//Wirets the post with the data
	//test:1
	public String getPostWriteData() {
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String date = df.format(dateTime);
    	
    	String postData = "@START\n" + 
    						"@POST\n" + 
    						"@USERNAME=" + getUser().getId() + "\n" + 
    						"@GNAME=" + getGroup().getGroupName() + "\n" + 
    						"@DATETIME=" + date + "\n" + 
    						"@TITLE=" + postTitle + "\n" +
    						"@BODYSTART\n" + 
    						postBody + "\n" + 
    						"@BODYEND\n" + 
    						"@PSTID=" + id + "\n";
    	if (flag) {
    		postData += "@FLAG" + "\n";
    	}
    	postData += "@SCORE=" + score + "\n" + 
    					"@RESPONSEID=" + responseID + "\n" +
    					"@END\n\n";
    	
    	return postData;
    }
	
	/*
	 * When the PostBody is Read from the system,
	 * a newline is automatically put at the end of the PostBody,
	 * so putting a newline after PostBody here will only cause each PostBody to get longer and longer,
	 * or cause them to not get updated/removed when needed
	 */
	public String getPostWriteData(boolean ignore) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String date = df.format(dateTime);

    	String postData = "@START\n" + 
    						"@POST\n" + 
    						"@USERNAME=" + getUser().getId() + "\n" + 
    						"@GNAME=" + getGroup().getGroupName() + "\n" + 
    						"@DATETIME=" + date + "\n" + 
    						"@TITLE=" + postTitle + "\n" +
    						"@BODYSTART\n" + 
    						postBody + 		//newline statement removed
    						"@BODYEND\n" + 
    						"@PSTID=" + id + "\n";
    	if (flag) {
    		postData += "@FLAG" + "\n";
    	}
    	postData += "@SCORE=" + score + "\n" + 
    					"@RESPONSEID=" + responseID + "\n" +
    					"@END\n\n";
    	return postData;
    }
	
	//Gete a total score of the post
    public int getTotalScore() {
        int totalScore = this.score;
        for (Response response : responses) {
            totalScore += response.getScore();
        }
        return totalScore;
    }

    //Compares to see if a users has already made an identical post in the group
	//FIXME: add tests
	@Override
	public int compareTo(Post p) {
		
		if (user.compareTo(p.getUser()) == 1 && group.compareTo(p.getGroup()) == 1 &&
				id == p.getId()) {
			
			return 1;
			
		}

		return 0;
	}
	
	//FIXME: needs tests
	public int getResponseID() {
		return responseID;
	}

}
