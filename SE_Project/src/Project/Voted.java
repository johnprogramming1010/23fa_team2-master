package Project;

public class Voted {
	
	private boolean hasUpvoted;
	private boolean hasDownvoted;
	private User user;
	private Post post;
	
	//test:1
	public Voted (User u, Post p){
		this.user = u;
		this.post = p;
		hasUpvoted = false;
		hasDownvoted = false;
	}
	
	//Makes it so a vote is upvoted
	//test:1
	public void up() {
		hasUpvoted = true;
		hasDownvoted = false;
	}
	
	//Makes it so a vote is downvoted
	//test:1
	public void down() {
		hasUpvoted = false;
		hasDownvoted = true;
	}
	
	//Gets to where a vote can be upvoted 
	//FIXME: add tests
	public void cancelVote() {
		hasUpvoted = false;
		hasDownvoted = false;
	}

	//test:1
	public boolean getUp(){
		return hasUpvoted;
	}
	
	//Gets to where a vote can be downvoted
	//test:1
	public boolean getDown(){
		return hasDownvoted;
	}
	
	//Returns a list of users
	//test:1
	public User getUser(){
		return user;
	}
	
	//Returns a list of post
	//test:1
	public Post getPost(){
		return post;
	}
	
	//Checks to make sure a users can only upvote or downvote on a post once
	//test:3
	public boolean compareTo(Voted v) {
		if (v.getUser().compareId(user.getId())) {
			if(v.getPost().compareTo(post) == 1) {
				return true;
			}
		}
		else {
			return false;
		}
		return false;
	}
	
	
	
	public String getVotedWriteData() {
		
		String msg = "@START\n" + 
						"@VOTED\n" + 
						"@USER=" + user.getId() + "\n" + 
						"@GROUP=" + post.getGroup().getGroupName() + "\n";
						
		
		if (post instanceof Response) {
			
			Response r = (Response) post;
			msg += "@POSTID=" + r.getParentalId() + "\n";
			msg += "@RESPONSEID=" + post.getResponseID() + "\n";
			
		}
		else {
			
			msg += "@POSTID=" + post.getId() + "\n";
			
		}
		
		
		if (hasUpvoted) {
			msg += "@UPVOTE\n";
		}
		else if (hasDownvoted) {
			msg += "@DOWNVOTE\n";
		}
		else {
			return "";
		}
		
		msg += "@END\n\n";
		
		return msg;
		
	}
	
}
