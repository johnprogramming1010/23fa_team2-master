package Project;

import java.util.ArrayList;
import java.util.Iterator;

public class Group implements Comparable<Group> {

    private String groupName;
    private ArrayList<membership> memberships;
    private ArrayList<Post> posts;
    private ArrayList<Banned> bans;
    private ArrayList<Suspended> suspensions;
    private int postId;

    public Group (String groupName) {
        this.groupName = groupName;
        this.memberships = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.bans = new ArrayList<>();
        this.suspensions = new ArrayList<>();
        this.postId = 0;
    }
    
    public Group (String groupName, int postId) {
    	this.groupName = groupName;
        this.memberships = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.postId = postId;
        this.bans = new ArrayList<Banned>();
        this.suspensions = new ArrayList<Suspended>();
    }
    
    //Gets a id of a post
    //FIXME: add tests
    public int getPostId() {
    	return postId;
    }

    //Gets the name of the groups that are created
	//test:1
    public String getGroupName() {
        return groupName;
    }

    //Adds members into groups
	//test:1
    public boolean addMember(membership m) {

        if (Validator.validateMemberExistsInGroup(m, memberships)) {
        	return false;
        }
        else {
        	memberships.add(m);
        	return true;
        }
    }
    
    //Removes members into groups
	  //test:2
    //FIXME: Add Unit Tests; Remove information from saved file
    public boolean removeMember(membership m) {

        if (Validator.validateMemberExistsInGroup(m, memberships)) {
        	memberships.remove(m);
        	return true;
        }
        else {
        	return false;
        }
    }

    //Gets the list of member in the group
	//test:2
    public ArrayList<membership> getMembers() {
        return memberships;
    }

    //Find members using their userID
	//test:1
    public User getUserInMembership (String userId) {
        for(membership m : memberships) {
        	User u = m.getUser();
            if(u.getId().equals(userId)) {
                return u;
            }
        }
        return null;
    }
    
  //Find memberships using their userID
	//test:2
    public membership getMembership (String userId) {
        for(membership m : memberships) {
        	User u = m.getUser();
            if(u.getId().equals(userId)) {
                return m;
            }
        }
        return null;
    }

    //Checks to see if a member is already a part of a group
	//test:2
    public boolean isMemberInGroupInMembership (String memberId) {
        for(membership m : memberships) {
        	User u = m.getUser();
            if(u.getId().equals(memberId)) {
                return true;
            }
        }
        return false;

    }

    //Adds a new post to the group
    public boolean addExistingPost(Post p) {
        
    	if (p.getId() >= this.postId) {
    		return false;
    	}
    	else {
    		this.posts.add(p);
    		return true;
    	}
    	
    }
    
  //Adds a new post to the group
    public boolean addNewPost(Post p) {

        boolean isMember = false;
        for (Post p1 : posts) {
            if (p1.equals(p)) {
                isMember = true;
                break;
            }
        }
        if (!isMember) {
            posts.add(p);
            postId++;
            return true;
        }
        return false;
    }
    
    //removes a post from the group
	//test:2
    public boolean removePost(Post p) {
        Iterator<Post> iterator = posts.iterator();
        boolean isMember = false;

        while (iterator.hasNext()) {
            Post p1 = iterator.next();
            if (p1.equals(p)) {
                iterator.remove(); // Safely removes the current element from the list
                isMember = true;
            }
        }

        return isMember;
    }


    //Gets the list of post made within a group
	//test:2
    public ArrayList<Post> getPost() {
        return posts;
    }
    
    
	//test:1
    public String getGroupWriteData(String catName) {
    	
    	String groupData = "@START\n" + 
    						"@GROUP\n" + 
    						"@NAME=" + groupName + "\n" + 
    						"@CATEGORY=" + catName + "\n" +
    						"@POSTID=" + postId + "\n" + 
    						"@END\n\n";
    	
    	return groupData;
    	
    }
    
    //Compares to see if a group already exists
	//test:2
	@Override
	public int compareTo(Group g) {
		if (g.getGroupName().equals(groupName)) {
			return 1;
		}
		return 0;
	}
	
	//Compares to see if a group name already exists
	//test:2
	public boolean compareName(String other) {
		
		if (other.equals(groupName)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//test:2
    //Adds banned
    public boolean addBanned(Banned b) {

        if (Validator.validateBannedExistsInGroup(b, bans)) {
        	return false;
        }
        else {
        	bans.add(b);
        	return true;
        }
    }
    
	//test:1
    //Gets the list of banned
    public ArrayList<Banned> getBanned() {
        return bans;
    }

	//test:2
    //Find banned using their userID
    public User getUserInBanned (String userId) {
        for(Banned b : bans) {
        	User u = b.getUser();
            if(u.getId().equals(userId)) {
                return u;
            }
        }
        return null;
    }
    
    ///test:2
    //Find banned using their userID
    public Banned getMemberInBanned (String userId) {
        for(Banned b : bans) {
        	User u = b.getUser();
            if(u.getId().equals(userId)) {
                return b;
            }
        }
        return null;
    }
    
	//test:2
    //Gets the list of Suspended in the group
    public ArrayList<Suspended> getSuspended() {
        return suspensions;
    }
    
	//test:2
    //Find users Suspended using their userID
    public User getUserInSuspended (String userId) {
        for(Suspended s : suspensions) {
        	User u = s.getUser();
            if(u.getId().equals(userId)) {
                return u;
            }
        }
        return null;
    }
    
    //test:2
    //Find membership Suspended using their userID
    public Suspended getMemberInSuspended (String userId) {
        for(Suspended s : suspensions) {
        	User u = s.getUser();
            if(u.getId().equals(userId)) {
                return s;
            }
        }
        return null;
    }
    
	//test:2
    //Adds members into suspensions
    public boolean addSuspended(Suspended s) {

        if (Validator.validateSuspendedExistsInGroup(s, suspensions)) {
        	return false;
        }
        else {
        	suspensions.add(s);
        	return true;
        }
    }
    
	//test:2
    //Removes members into groups
    public boolean removeSuspended(Suspended s) {

        if (Validator.validateSuspendedExistsInGroup(s, suspensions)) {
        	suspensions.remove(s);
        	return true;
        }
        else {
        	return false;
        }
    }

}