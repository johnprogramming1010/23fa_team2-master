package Project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SystemManager {

	private boolean userSignedIn;		//User sign in status, true if any User is signed in
	private boolean adminSignedIn;		//Admin sign in status, true if an Admin is signed in
	private User currentUser;
	private category currentCategory;
	private Group currentGroup;
	private Post currentPost;
	
	private boolean writable;

	private ArrayList<User> users;
	private ArrayList<Admin> admins;
	private ArrayList<category> categories;
	private ArrayList<String> fileNames;

	
	
	public SystemManager() {
		userSignedIn = false;
		adminSignedIn = false;
		users = new ArrayList<User>();
		admins = new ArrayList<Admin>();		
		categories = new ArrayList<category>();
		this.writable = false;
	}
	
    //test:1
	//Constructor that will read given file
	public SystemManager(ArrayList<String> fileNames) {
		this.userSignedIn = false;
		this.adminSignedIn = false;
		this.users = new ArrayList<User>();
		this.admins = new ArrayList<Admin>();
		this.categories = new ArrayList<category>();
		this.fileNames = fileNames;
		this.writable = true;
		
		try {
			ReadFile.readFile(this, fileNames);
		} 
		catch (FileNotFoundException | IncorrectFileFormatException e) {
			e.printStackTrace();
		}		
	}
	
    //test:1
	//writes the manager
	public boolean writeManager() {
		
		try {
			WriteFile.writeFile(this, fileNames);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
    //test:2
	// add an user
	public boolean addUser(User u) {
		if (Validator.validateUserExists(u, users)) {
			return false;
		}
		else {
			users.add(u);
			return true;
		}
	}

    //test:2
	// adds an admin
	public boolean addAdmin(Admin a) {	
		if (Validator.validateAdminExists(a, admins)) {
			return false;
		}
		else {
			admins.add(a);	
			return true;
		}
	}

    //test:2
	// allows the user to be registered
	public boolean registerUser(String name, String bday, String city,
								String state, String username, String password) {

		User u = Validator.validUserName_Users(users, username);	//Check to see if there exists a User with the given username

		if (u == null) {				//There does not exist a User with the given username

			u = Validator.validUserName_Admins(admins, username);	//Check to see if there exists a Admin with the given username

			if (u == null) {			//There does not exist a Admin with the given username

				u = new User(name, username, password, bday, city, state);	//create new User	NOTICE: this will have to be updated once User class is updated
				users.add(u);			//add new user
				if (writable) {			//If there is a file to write to
					try {					//Try adding the user to the UserFile
						WriteFile.addUserToFile(u, fileNames.get(1));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			return true;				//return true
			}
		}
		return false;					//If there exists a User or Admin with the given username, return false
	}
	
	//test:1
	// allows a user to join a group
	public boolean joinGroup(User user, Group group) {
		membership m = new membership(user, group);
		boolean joined = group.addMember(m);
		
		if (writable && joined) {
			try {
				WriteFile.addMembershipToFile(m, fileNames.get(4));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	// allows a user to leave a group
	public boolean leaveGroup(User user, Group group) {
		membership m = group.getMembership(user.getId());
		boolean left = group.removeMember(m);
		
		if (writable && left) {
			try {
				WriteFile.removeMembershipFromFile(m, fileNames.get(4));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}

	//Adds a category 
    //test:2
	public boolean addCategory(category c) {
		
		if (Validator.validateCategoryExists(c, categories)) {
			return false;
		}
		else {
			categories.add(c);
			return true;
		}
		
	}

	//Allows an admin to create category
	//test:2
	public boolean createCategory(String name) {
		if (Validator.validateCategoryNameExists(categories, name)) {	//If there exists a category with given name
			return false;				//return false
		}
		else {
			category c = new category(name);	//else, create new category	NOTICE: This may require more variables as the Category class is updated
			categories.add(c);			//add category
			if (writable) {		//If there is a file to write to
				try {				//Try adding the new category
					WriteFile.addCategoryToFile(c, fileNames.get(2));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return true;				//return true
		}
	}
	
	//Admins create group
	//test:3									//Assumes GUI will send over just category name, not category object
	public boolean createGroup(String groupName, String categoryName) {
		category c = Validator.getCategoryFromName(categories, categoryName);	//Get category with given name if there exists one, null otherwise

		if (c == null) {				//If validator returned null
			return false;				//return false
		}
		else {							//If validator returned a category
			
			if (!Validator.validateGroupNameExists(this.getAllGroups(), groupName)) {
			
				c.createGroup(groupName);	//create group within category, returns true/false depending on if group was created	NOTICE: This may require more variables as the Group class is updated
				if (writable) {					//If there is a file to write to
					Group g = Validator.getGroupFromName(c.getGroupsAlphabetically(), groupName);	//Get the group
					try {							//Try adding the group to the file
						WriteFile.addGroupToFile(g, fileNames.get(3), categoryName);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
				
			}
			else {
				return false;
			}
		}
	}
	
	//test:6
	// allows the user to login
	public boolean login(String username, String password) {
		//boolean signIn = false;

		User u = Validator.validUserName_Users(users, username);	//Checks under Users

		if (u == null) {				//If the username is not of a User

			u = Validator.validUserName_Admins(admins, username);	//Checks under Admins

			if (u == null) {			//If the username is not of a Admin
				return false;			//Will return false
			}
			else {						//If the username is of an Admin
				if (Validator.validPassword(u, password)) {	//If the password was correct
					userSignedIn = true;	//Set User sign in status to true
					adminSignedIn = true;	//Set Admin sign in status to true
					this.currentUser = u;
					return true;
				}
				else { 					//If the password was incorrect
					return false;		//return false
				}
			}
		}
		else {							//If the username is of a User
			if (Validator.validPassword(u, password)) {	//If the password was correct
				userSignedIn = true;	//Set User sign in status to true
				this.currentUser = u;
				return true;			//Return true
			}
			else {						//If the password was incorrect
				return false;			//Return false
			}
		}
	}
	//test:1
	// allows a post to be created
	public boolean createNewPost(Group group, String postTitle, String postBody) {
		String find = group.getGroupWriteData(currentCategory.getName());
		
		membership m = getMembership(group, currentUser);
		int id = group.getPostId();
		if (postBody.charAt(postBody.length()-1) != '\n') {
			postBody += "\n";
		}
		Post p = new Post(m, postTitle, postBody, id);
		group.addNewPost(p);
		
		if (writable) {
			try {
				WriteFile.addPostToFile(p, fileNames.get(5));
				
				String replace = group.getGroupWriteData(currentCategory.getName());
				WriteFile.updateGroupinFile(find, replace, fileNames.get(3));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	//test:1
	// allows a post to be deleted
	//US36 - Administrator can remove a post
	public boolean deleteNewPost(Post p) {
		Group g = p.getGroup();
		
		ArrayList<Response> deleteThese = p.getResponse();
		
		boolean removed = g.removePost(p);
		
		if (writable && removed) {
			try {
				for (Response r: deleteThese) {
					WriteFile.removeResponseFromFile(r, fileNames.get(6));
				}
				
				WriteFile.removePostFromFile(p, fileNames.get(5));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else {
			return removed;
		}
	}

	//test:1
	//US37 - Administrator can remove a response to a post
	//US38 - User can remove a response to a post
	public void removeResponseToPost(Post p, Response r, String userTitle) {
		
		if(p.getResponse().contains(r)){
			
			String findR = r.getResponseWriteData(true);
			r.editResponseBody("Content Removed By: " + userTitle);
			String replaceR = r.getResponseWriteData();
			System.out.println(findR + replaceR);
			if (writable) {
				try {
					WriteFile.removeResponseFromFile(findR, replaceR, fileNames.get(6));
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//test:1
	//US39 - User can request a new group
	public boolean createNewGroup(category c, String groupName) {
		Group g = new Group(groupName);
		return(c.addGroup(g));
	}

	//test:1
	// gets the membership of the group and user inputed
	public boolean createNewResponse(Group group, String responseBody, Post post) {
		membership m = getMembership(group, currentUser);
		String findP = post.getPostWriteData(true);
		String findP2 = post.getPostWriteData();
		if (responseBody.charAt(responseBody.length()-1) != '\n') {
			responseBody += "\n";
		}
		System.out.println(responseBody);
		Response r = new Response(m, responseBody, post.getId(), post.getResponseID());
		boolean newResponse = currentPost.addNewResponse(r);		
		String replaceP = post.getPostWriteData(true);
		String replaceP2 = post.getPostWriteData();
		
		if (writable && newResponse) {
			
			try {
				WriteFile.addResponseToFile(r, fileNames.get(6));
				WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
				WriteFile.updatePostInFile(findP2, replaceP2, fileNames.get(5));
			} 
			catch (IOException e) {

				e.printStackTrace();
			}
			
			return true;
			
		}
		else {
			return newResponse;
		}
		
	}
	
	//test:1
	// gets the membership of the group and user inputed
	public membership getMembership(Group group, User user) {
		ArrayList<membership> memberships = group.getMembers();
		for (membership m : memberships) {
			if (m.getUser() == user) {
				return m;
			}
		}
		return null;
	}

	//test:1
	// returns all category alphabetically
	public ArrayList<category> getCategories_Alphabetically() {

		Collections.sort(categories, new SortCategoriesByName());

		return categories;
	}
	
	//helper method, returns a list of all groups.
	private ArrayList<Group> getAllGroups(){
		ArrayList<Group> groups = new ArrayList<>();
		for(category c : categories) {
			groups.addAll(c.getGroupsAlphabetically());
		}
		return groups;
	}
	
	//helper method, returns a list of all RequestedGroups.
	private ArrayList<Group> getAllRequestedGroups(){
		ArrayList<Group> groups = new ArrayList<>();
		for(category c : categories) {
			groups.addAll(c.getRequestedGroupsAlphabetically());
		}
		return groups;
	}
	
	//test:1
	// returns all groups alphabetically
	public ArrayList<Group> getAllGroups_Alphabetically() {
		ArrayList<Group> groups = getAllGroups();
		Collections.sort(groups, new SortGroupsByName());

		return groups;
	}
	
	//test:1
	// returns all RequestedGroups alphabetically
	public ArrayList<Group> getAllRequestedGroups_Alphabetically() {
		ArrayList<Group> groups = getAllRequestedGroups();
		Collections.sort(groups, new SortGroupsByName());

		return groups;
	}

	//test:1
	// returns users alphabetically
	public ArrayList<User> getUsers_Alphabetically() {


		Collections.sort(users, new SortUsersByUsername());


		return users;
	}

	//Gets the list of post by the data and sorts it out
	//test:1
	public ArrayList<Post> getPosts_InGroupByDate(Group g) {
		
		ArrayList<Post> alPosts = g.getPost();
		
		Collections.sort(alPosts, new SortPostsByDate());
		
		return alPosts;
		
	}

	//test:1
	// returns the status if the user is logged in
	public boolean isLoggedIn() {
		return userSignedIn;
	}
	
	//test:1
	//returns the status of the Admin
	public boolean isAdmin() {
		return adminSignedIn;
	}
	
	//Checks to see if a users is an Admin
	//test:1
	public boolean isUserAdmin(User u) {
		if (u instanceof Admin) {
			return true;
		}
		
		return false;
	}

	//test:1
	// gets current user
	public User getCurrentUser() {
		return currentUser;
	}
	
	//test:1
	// gets current category
	public category getCurrentCategory() {
		return currentCategory;
	}

	//test:1
	// sets the current category
	public void setCurrentCategory(category currentCategory) {
		this.currentCategory = currentCategory;
	}

	//Gets the current group a users belongs in
	//test:1
	public Group getCurrentGroup() {
		return currentGroup;
	}

	//test:1
 	// sets the current group
	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}
	
	//Gets the current post
	//test:1
	public Post getCurrentPost() {
		return currentPost;
	}


	//Sets a current post
	//test:1
	public void setCurrentPost(Post currentPost) {
		this.currentPost = currentPost;
	}

	//test:1
	// allows a user to logout
	public void logout() {
		currentUser = null;
	}

	//test:1
	// sorts admins alphabetically
	public ArrayList<Admin> getAdmins_Alphabetically() {
		Collections.sort(admins, new SortUsersByUsername());
		return admins;
	}
	
	//test:1
	// returns true if a user is a member of group, returns false if otherwise
	public Boolean isUserOfGroup(User u, Group g) {
		if (u == null) {
			return false;
		}
		User u1 = g.getUserInMembership(u.getId());
		
		if(u1 != null) {
			return true;
		}
		return false;
	}
	
	//test:2
	// returns true if the user is banned from the group
	public Boolean isUserBannedFromGroup(User u, Group g) {
		ArrayList<Banned> bannedUsers = g.getBanned();
		for (Banned b: bannedUsers) {
			if(b.getUser() == u) {
				return true;
			}
		}
		return false;
	}
	
	//test:2
	// returns true if the user is Suspended from the group
	public Boolean isUserSuspendedFromGroup(User u, Group g) {
		ArrayList<Suspended> suspendedUsers = g.getSuspended();
		for (Suspended s: suspendedUsers) {
			if(s.getUser() == u) {
				return true;
			}
		}
		return false;
	}
	
	//test:1
	// returns an arraylist of groups that a user is in
	 public ArrayList<Group> getGroupsByUser(User user) {
	 ArrayList<Group> group = new ArrayList<>();
	 ArrayList<Group> groups = getAllGroups();
	 for (Group g: groups) {
		 if (g.isMemberInGroupInMembership(user.getId()) == true){
			 group.add(g);
		 }
	 }
	 return group;
}
	 
	 //test:1
	 //returns an arraylist of of all the users in group
	 public ArrayList<User> getUsersInGroup(Group group) {
		 ArrayList<User> userInGroup = new ArrayList<>();
		 for (User u: users) {
			 if (group.isMemberInGroupInMembership(u.getId()) == true){
				 userInGroup.add(u);
			 }
		 }
		 return userInGroup;
	 }
	 
	 //test:1
	 public category getCategoryByGroup(Group group) {
		 for (category c : this.getCategories_Alphabetically()) {
			 for (Group g : c.getGroups()) {
				 if (g == group) {
					 return c;
				 }
			 }
		 }
		 return null;
	 }
	
	 //test:1
	 //returns an arraylist of all the groups in category alphabetically
	 public ArrayList<Group> getGroupsInCategory_Alphabetically(category c) {
		 
		 return c.getGroupsAlphabetically();
	}
	 
	 //test:1
	 //returns an arraylist of all the groups in category alphabetically
	 public ArrayList<Group> getRequestedGroupsInCategory_Alphabetically(category c) {
		 
		 return c.getRequestedGroupsAlphabetically();
	}
	 
	//test:3
	//helper method, returns a list of all posts.
	public ArrayList<Post> getAllPost(){
		ArrayList<Group> groups = getAllGroups_Alphabetically();
		ArrayList<Post> posts = new ArrayList<>();
		for(Group g: groups) {
			posts.addAll(g.getPost());
		}
		Collections.sort(posts, new SortPostsByDate());
		return posts;
	}
	
	//Gets a list of all the responses made to a given post
	//test:1
	public ArrayList<Response> viewAllPostResponses (Post p) {
		
		ArrayList<Response> alResponses = p.getResponse();
			
		Collections.sort(alResponses, new SortPostsByDate());
		
		return alResponses;
  }

	//Gets a list of all the responses made within all the post
	//test:1
	public ArrayList<Response> viewAllPostResponses () {
		if (currentPost != null) {
			return currentPost.getResponse();
		}
		return null;
	}
	
	 //Returns every post or response made
	 public ArrayList<Object> viewAllUsersPostsResponses() {
		ArrayList<Object> results = new ArrayList<Object>();
		ArrayList<Post> posts = getAllPost();
		for(Post p : posts) {
			results.add(p);
			results.addAll(p.getResponse());
		}
		 Collections.sort(results, new SortObjectsByDate());
		 return results;
	 }
	
	//test:1
	//User story 22
	//takes in a user and loops through all the posts. If a post was created by the user it records the postBody. Also checks each post for Responces. if the users are the same records ResponceBody to the string.
	 public ArrayList<Object> viewUsersPostsResponses(User user) {
		 ArrayList<Object> results = new ArrayList<Object>();
		 ArrayList<Post> posts = new ArrayList<>();
		 posts.addAll(getAllPost());
		 for(Post p : posts) {
			 if(user == p.getUser()) {
				  results.add(p);
			 }
			 ArrayList<Response> r = p.getResponse();
			 for (Response r1 : r){
				 User u = r1.getUser();
				 if(user == u){
					  results.add(r1);
				 }	
			 }
		 }
		 Collections.sort(results, new SortObjectsByDate());
		 return results;
	 }
	 
	 //test:1
	 //User story 23
	 // takes in user and a group. if the post is in the group given then it records all the posts and responses created by the user in that group. 
	 public ArrayList<Object> viewUsersPostsResponsesInGroup(User user, Group group) {
		 ArrayList<Object> results = new ArrayList<>();
		 ArrayList<Post> posts = getAllPost();
		 for(Post p : posts) {
			 if(group == p.getGroup()) {
				 if(user == p.getUser()) {
					 results.add(p);
				 }
				 ArrayList<Response> r = p.getResponse();
				 for (Response r1 : r){
					 User u = r1.getUser();
					 if(user == u){
						 results.add(r1);
					 }	
				 }
			 }
		 }
		 return results;
	 }

     //test:1
	 //User story 24
	 //loops through the post arrayList and records all the posts and responses of a given group
	 public ArrayList<Object> viewPostsResponsesInGroup(Group group) {
		 ArrayList<Object> results = new ArrayList<>();
		 ArrayList<Post> posts = getAllPost();
		 for(Post p : posts){
			 if(group == p.getGroup()) {
				 results.add(p);
				 ArrayList<Response> r = p.getResponse();
				 for (Response r1 : r){
					 results.add(r1);
				 }
			}
		 }
		 return results;
	}
	 
     //test:1
	 //loops through the post arrayList and records all the posts of a given group
	 public ArrayList<Post> viewPostsInGroup(Group group) {
		 ArrayList<Post> posts = group.getPost();
		 return posts;
	}
	 
     //test:1
	 //User story 25
	 //checks if the post has the user if so it gets the responses from the post and returns an arraylist of responses.
	 public ArrayList<Object> viewMyResponses(User user, Post post) {
		 ArrayList<Response> pr = post.getResponse();
		 ArrayList<Object> results = new ArrayList<>();
		 for (Response r1 : pr){
			 User u = r1.getUser();
			 if(user == u){
				 results.add(r1);
			}	
		}
		return results;
	} 

	 //test:1
	 // uses the validator class to get the category by name
	 public category getCategoryByName(String catName) {
		 return Validator.getCategoryFromName(categories, catName);
	 }
	 
     //test:1
	 // returns the users alphabetically by the username
	 public ArrayList<User> getUsers_Alphabetically_ByUsername(){
		 Collections.sort(users, new SortUsersByUsername());
		 
		 return users;
	 }
	 
     //test:1
	 // returns the admins alphabetically by the username
	 public ArrayList<Admin> getAdmins_Alphabetically_ByUsername() {
		 Collections.sort(admins, new SortUsersByUsername());
		 
		 return admins;
	 }
	 
     //test:2
	 // uses the validator class to get the group by name
	 public Group getGroupByName(String name) {
		 ArrayList<Group> allGroups = this.getAllGroups();
		 
		 return Validator.getGroupFromName(allGroups, name);
	 }
	 
     //test:2
	 // uses the Validator class to sort the user by username
	 public User getUserByUsername(String username) {
		 return Validator.getUserFromUsername(users, username);
	 }
	 
	 //Gets the username of the admin
	 //FIXME: needs tests
	 public User getAdminByUsername(String username) {
		 return Validator.getAdminFromUsername(admins, username);
	 }
	 
	 //test:1
	 //US28 - Administrator can suspend a User from a group and they will have a cooling period
	 //TODO add cooling period
	 public void suspendUser(Suspended s) {
		 User u = s.getUser();
		 Group g = s.getGroup();
		 membership m = g.getMembership(u.getId());
		 g.removeMember(m);
		 g.addSuspended(s);
		 
		 if (writable) {
			 try {
				WriteFile.addSuspendedToFile(s, fileNames.get(8));
			} 
			 catch (IOException e) {
				e.printStackTrace();
			}
		 }
 	 }
	 
	 //US28 - Administrator can suspend a User from a group and they will have a cooling period
	 // FIXME: Add unit tests
	 public void suspendUser(User u, Group g, int days) {
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		 Date start = new Date(new Date().getTime());
		 Date end = new Date(new Date().getTime() + 86400000 * days);
		 String timeStampStart = sdf.format(start);
		 String timeStampStop = sdf.format(end);
		 Suspended s = new Suspended(u,g, timeStampStart, timeStampStop);
		 g.addSuspended(s);
		 
		 if (writable) {
			 try {
				WriteFile.addSuspendedToFile(s, fileNames.get(8));
			} 
			 catch (IOException e) {
				e.printStackTrace();
			}
		 }
 	 }
	 
	 //test:1
	 // return a list of names of members
	 //US29 - Administrator can view a list of Users and the corresponding group(s) they are suspendend from
	 public ArrayList<Suspended> getAllSuspensions(){
		ArrayList<Group> groups = getAllGroups();
		ArrayList<Suspended> suspensions = new ArrayList<>();
		for(Group g : groups) {
			suspensions.addAll(g.getSuspended());
		}
		return suspensions;
	 }
	
	 //test:1
	 // return a list of suspensions sorted by username
	 public ArrayList<Suspended> getAllSuspensions_ByUsername(){
		 ArrayList<Suspended> suspensions = getAllSuspensions();
		 Collections.sort(suspensions, new SortSuspensionsByUsername());
		 return suspensions;
	 }
	 
	 //Gets the a list of post given an id for the post
	 // return a suspension for a particular user and group
	 // FIXME: add unit tests
	 public Suspended getSuspensions_ByUsernameGroup(User u, Group g){
		 return g.getMemberInSuspended(u.getId());
	 }
	 
	 // return string of suspension end date / time
	 // FIXME: add unit tests
	 public String getSuspensionEndDate(Suspended s) {
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		 return sdf.format(s.getExpiredDate());
	 }
	 
	 //test:1
	 public Post getPostByGroupId(Group g, int id) {
		 return Validator.getPostFromId(g.getPost(), id);
	 }
	 
	 //Creates a format for the date
	 //FIXME: add tests
	 public Response getResponseByPostAndID(Post p, int id) {
		 return Validator.getResponseFromId(p.getResponse(), id);
	 }

	 //test:1
	 public String getSimpleDate(Date date) {
			String pattern = "dd MMM yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
	 }
	 
	 //Gets format for the date
	 //test:1	 
	 public String getSimpleTime(Date date) {
			String pattern = "h:mm a";
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
	 }
	 
	//test:1
	 //Gets a list of all the groups in an alphabetically sorted method
	//FIXME: add test methods
	public ArrayList<membership> getAllMemberships() {
		 ArrayList<membership> memberships = new ArrayList<membership>();

		 for (Group g : this.getAllGroups_Alphabetically()) {

			 memberships.addAll(g.getMembers());

		 }

		 return memberships;
	}


	//test:1
	//US30 - Administrator can reinstate a suspended user to good standing in a group
	public void reinstateSuspendedUser(Suspended s, membership m) {
		Group g = s.getGroup();
		g.addMember(m);
		g.removeSuspended(s);
		
		if (writable) {
			try {
				WriteFile.removeSuspendedFromFile(s, fileNames.get(8));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//US30 - Administrator can reinstate a suspended user to good standing in a group
	// removal and re-adding the membership is not needed, and actually not preferred
	//FIXME: Add unit tests
	public void reinstateSuspendedUser(Suspended s) {
		Group g = s.getGroup();
		g.removeSuspended(s);
		
		if (writable) {
			try {
				WriteFile.removeSuspendedFromFile(s, fileNames.get(8));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//test:1
	//US31 - Administrator can ban a user from a group
	public void banUser(Banned b) {
		 User u = b.getUser();
		 Group g = b.getGroup();
		 @SuppressWarnings("unused")
		 membership m = g.getMembership(u.getId());
//		 g.removeMember(m);		// Keep as member
		 g.addBanned(b);
		 
		 if (writable) {
			 try {
				WriteFile.addBannedToFile(b, fileNames.get(9));
			} 
			 catch (IOException e) {
				e.printStackTrace();
			}
		 }
	 }
	
	//FIXME: Add unit tests
	public void banUser(User u, Group g) {
		Banned b = new Banned(u, g);
		@SuppressWarnings("unused")
		membership m = g.getMembership(u.getId());
//		g.removeMember(m);		// Keep as member
		g.addBanned(b);
		
		if (writable) {
			try {
				WriteFile.addBannedToFile(b, fileNames.get(9));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	 }
	
	//test:1 
	//US32 - Administrator can view a list of Users and the corresponding group(s) they are banned from
	public ArrayList<Banned> getAllBans(){
		ArrayList<Group> groups = getAllGroups();
		ArrayList<Banned> bans = new ArrayList<>();
		for(Group g : groups) {
			bans.addAll(g.getBanned());
		}
		return bans;
	}
	
	//test:1
	// return a list of bans sorted by username
	public ArrayList<Banned> getAllBans_ByUsername(){
		ArrayList<Banned> bans = getAllBans();
		Collections.sort(bans, new SortBannedByUsername());
		return bans;
	}
	
	//test:2
	//US33 - User can flag a post or response that I find problematic
	public void flagPost(Post p) {
		String findP = p.getPostWriteData(true);
		p.setFlagTrue();
		String replaceP = p.getPostWriteData(true);
		
		if (writable) {
			try {
				WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		 
	public void flagResponse(Response r) {
		String findR = r.getResponseWriteData(true);
		r.setFlagTrue();
		String replaceR = r.getResponseWriteData(true);
		
		if (writable) {
			try {
				WriteFile.updateResponseInFile(findR, replaceR, fileNames.get(6));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//test:1
	//US34 - Administrator can view all flagged post or responses
	public ArrayList<Object> getAllFlaggedPostAndResponses(){
		ArrayList<Post> posts = getAllPost();
		ArrayList<Response> responses = new ArrayList<>();
		ArrayList<Object> objects = new ArrayList<>();
		for(Post p : posts) {
			responses.addAll(p.getResponse());
			if(p.getFlag() == true) {
				objects.add(p);
			}
		}
		for(Response r : responses) {
			if(r.getFlag() == true) {
				objects.add(r);
			}
		}
		return objects;
	}
	
	//test:1
	// gets all flagged posts
	public ArrayList<Post> getAllFlaggedPost(){
		ArrayList<Post> posts = getAllPost();
		ArrayList<Post> results = new ArrayList<>();
		for(Post p : posts) {
			if(p.getFlag() == true) {
				results.add(p);
			}
		}
		return results;
	}
	
	//test:1
	// gets all flagged responses
	public ArrayList<Post> getAllFlaggedResponses(){
		ArrayList<Post> posts = getAllPost();
		ArrayList<Response> responses = new ArrayList<>();
		ArrayList<Post> results = new ArrayList<>();
		for(Post p : posts) {
			responses.addAll(p.getResponse());
		}
		for(Response r : responses) {
			if(r.getFlag() == true) {
				results.add(r);
			}
		}
		return results;
	}
	
	//test:2
	//US35 - Administrator can remove all flags on a post or response
	public void removeFlagOnPost(Post p) {
		String findP = p.getPostWriteData(true);
		p.setFlagFalse();
		String replaceP = p.getPostWriteData(true);
		
		if (writable) {
			try {
				WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	 
	public void removeFlagOnResponse(Response r) {
		String findR = r.getResponseWriteData(true);
		r.setFlagFalse();
		String replaceR = r.getResponseWriteData(true);
		
		if (writable) {
			try {
				WriteFile.updateResponseInFile(findR, replaceR, fileNames.get(6));
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//test:4
	// US40 - User can up-vote or down-vote a post or response other than my own
	// add a validator
	public ArrayList<Voted> getAllVotes(){
		ArrayList<Voted> votes = new ArrayList<>();
		for(User u : users) {
			votes.addAll(u.getVotedList());
		}
		return votes;
	}
	
	/*
	 * For upvote a Post:
	 * If a Voted object already exists:
	 * 		If the Voted was already upvoted
	 * 			Remove the upvote and subtract from the Post score
	 * 			Update the Post in Post.txt, remove the Voted in Voted.txt
	 * 		If the Voted was already downvoted
	 * 			Change to an upvote, adding to the Post score twice
	 * 			Update the Post and Voted in Post.txt and Voted.txt
	 * 		If the Voted existed but was neither upvoted or downvoted
	 * 			Change to an upvote, add to the Post score
	 * 			Update the Post and Voted in Post.txt and Voted.txt
	 * If a Voted does not already exist:
	 * 		Change the voted to upvote
	 * 		Add the voted to the User, add to the Post score
	 * 		Update text files
	 */
	//test:4
	 public boolean upvotePost(Post p) {
		 Voted v = new Voted(this.currentUser, p);
		 
		 if (Validator.validateVotedPostExists(v, this.currentUser.getVotedList(), true) == true) {
			 
			 Voted v1 = Validator.getVotedByUserPost(this.currentUser, p, this.currentUser.getVotedList(), true);
			 
			 if (v1.getUp()) {
				 
				 String findP = p.getPostWriteData(true);
				 v1.cancelVote();
				 p.subScore();
				 String replaceP = p.getPostWriteData(true);
				 
				 if (writable) {
					 try {
						 Voted remove = new Voted(this.currentUser, p);
						 remove.up();
						 WriteFile.removeVotedFromFile(remove, fileNames.get(7));
						 WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
					} 
					 catch (IOException e) {
						 e.printStackTrace();
					}
				 }
				 return true;
				 
			 }
			 else if (v1.getDown()) {
				 String findV = v1.getVotedWriteData();
				 String findP = p.getPostWriteData(true);
				 v1.up();
				 p.addScore();
				 p.addScore();
				 String replaceV = v1.getVotedWriteData();
				 String replaceP = p.getPostWriteData(true);
				 if (writable) {
					 try {
							WriteFile.updateVotedInFile(findV, replaceV, fileNames.get(7));
							WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
						} 
						 catch (IOException e) {
							e.printStackTrace();
						} 
				 }
				 return true;
			 }
			 else {
         
				 String findP = p.getPostWriteData(true);
				 v1.up();
				 p.addScore();
				 String replaceP = p.getPostWriteData(true);
         
				 if (writable) {
					 try {
							WriteFile.addVotedToFile(v1, fileNames.get(7));
							WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
						} 
						 catch (IOException e) {
							e.printStackTrace();
						} 
				 }
				 return true;
			 }
		 }
		 else {
			 String findP = p.getPostWriteData(true);
			 v.up();
			 this.currentUser.addVoted(v);
			 p.addScore();
			 String replaceP = p.getPostWriteData(true);
			 
			 if (writable) {
				 try {
					WriteFile.addVotedToFile(v, fileNames.get(7));
					WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
				} 
				 catch (IOException e) {
					e.printStackTrace();
				}
			 }
			 return true;
		 }
	 }
	 
	 /*
	 * For upvote a Response:
	 * If a Voted object already exists:
	 * 		If the Voted was already upvoted
	 * 			Remove the upvote and subtract from the Response score
	 * 			Update the Response in Response.txt, remove the Voted in Voted.txt
	 * 		If the Voted was already downvoted
	 * 			Change to an upvote, adding to the Response score twice
	 * 			Update the Response and Voted in Response.txt and Voted.txt
	 * 		If the Voted existed but was neither upvoted or downvoted
	 * 			Change to an upvote, add to the Response score
	 * 			Update the Response and Voted in Response.txt and Voted.txt
	 * If a Voted does not already exist:
	 * 		Change the voted to upvote
	 * 		Add the voted to the User, add to the Response score
	 * 		Update text files
	 */
	 //tests:4
	 public boolean upvoteResponse(Response r) {
		 Voted v = new Voted(this.currentUser, r);
		 
		 if (Validator.validateVotedPostExists(v, this.currentUser.getVotedList(), false) == true) {
			 
			 Voted v1 = Validator.getVotedByUserPost(this.currentUser, r, this.currentUser.getVotedList(), false);
			 
			 if (v1.getUp()) {
				 
				 String findP = r.getResponseWriteData(true);
				 v1.cancelVote();
				 r.subScore();
				 String replaceP = r.getResponseWriteData(true);
				 
				 if (writable) {
					 
					 Voted remove = new Voted(this.currentUser, r);
					 remove.up();
					 try {
						WriteFile.removeVotedFromFile(remove, fileNames.get(7));
						WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
					} 
					 catch (IOException e) {
						e.printStackTrace();
					}
				 }
				 return true;
				 
			 }
			 else if (v1.getDown()) {
				 String findV = v1.getVotedWriteData();
				 String findP = r.getResponseWriteData(true);
				 v1.up();
				 r.addScore();
				 r.addScore();
				 String replaceV = v1.getVotedWriteData();
				 String replaceP = r.getResponseWriteData(true);
				 if (writable) {
					 try {
							WriteFile.updateVotedInFile(findV, replaceV, fileNames.get(7));
							WriteFile.updateResponseInFile(findP, replaceP, fileNames.get(6));
						} 
						 catch (IOException e) {
							e.printStackTrace();
						} 
				 }
				 
				 return true;
			 }
			 else {

				 String findP = r.getResponseWriteData();
				 v1.up();
				 r.addScore();
				 String replaceP = r.getResponseWriteData();

				 if (writable) {
					 try {
							WriteFile.addVotedToFile(v1, fileNames.get(7));
							WriteFile.updateResponseInFile(findP, replaceP, fileNames.get(6));
						} 
						 catch (IOException e) {
							e.printStackTrace();
						} 
				 }
				 return true;
			 }
			 
		 }
		 else {
			 String findP = r.getResponseWriteData(true);
			 v.up();
			 this.currentUser.addVoted(v);
			 r.addScore();
			 String replaceP = r.getResponseWriteData(true);
			 
			 if (writable) {
				 try {
					WriteFile.addVotedToFile(v, fileNames.get(7));
					WriteFile.updateResponseInFile(findP, replaceP, fileNames.get(6));
				} 
				 catch (IOException e) {
					e.printStackTrace();
				}
			 }
			 
			 return true;
		 }
	}
	 
	 /*
	 * For downvote a Post:
	 * If a Voted object already exists:
	 * 		If the Voted was already downvoted
	 * 			Remove the downvote and add to Post score
	 * 			Update the Post in Post.txt, remove the Voted in Voted.txt
	 * 		If the Voted was already upvoted
	 * 			Change to an downvote, subtracting from the Post score twice
	 * 			Update the Post and Voted in Post.txt and Voted.txt
	 * 		If the Voted existed but was neither upvoted or downvoted
	 * 			Change to an downvote, subtract from the Post score
	 * 			Update the Post and Voted in Post.txt and Voted.txt
	 * If a Voted does not already exist:
	 * 		Change the voted to downvote
	 * 		Add the voted to the User, subtract from the Post score
	 * 		Update text files
	 */
	 //tests:4
	 public boolean downvotePost(Post p) {
		 Voted v = new Voted(this.currentUser, p);
		 
		 if (Validator.validateVotedPostExists(v, this.currentUser.getVotedList(), true) == true) {
			 
			 Voted v1 = Validator.getVotedByUserPost(this.currentUser, p, this.currentUser.getVotedList(), true);
			 
			 if (v1.getDown()) {
				 
				 String findP = p.getPostWriteData(true);
				 v1.cancelVote();
				 p.addScore();
				 String replaceP = p.getPostWriteData(true);
				 
				 if (writable) {
					 
					 Voted remove = new Voted(this.currentUser, p);
					 remove.down();
					 try {
						WriteFile.removeVotedFromFile(remove, fileNames.get(7));
						WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
					} 
					 catch (IOException e) {
						e.printStackTrace();
					}
				 }
				 return true;
				 
			 }
			 else if (v1.getUp()) {
				 String findV = v1.getVotedWriteData();
				 String findP = p.getPostWriteData(true);
				 v1.down();
				 p.subScore();
				 p.subScore();
				 String replaceV = v1.getVotedWriteData();
				 String replaceP = p.getPostWriteData(true);
				 if (writable) {
					 try {
							WriteFile.updateVotedInFile(findV, replaceV, fileNames.get(7));
							WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
						} 
						 catch (IOException e) {
							e.printStackTrace();
						} 
				 }
				 
				 return true;
			 }
			 else {

				 String findP = p.getPostWriteData(true);
				 v1.down();
				 p.subScore();
				 String replaceP = p.getPostWriteData(true);
         
				 if (writable) {
					 try {
							WriteFile.addVotedToFile(v1, fileNames.get(7));
							WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
						} 
						 catch (IOException e) {
							e.printStackTrace();
						} 
				 }
				 return true;
			 }
			 

		 }
		 else {
			 String findP = p.getPostWriteData(true);
			 v.down();
			 this.currentUser.addVoted(v);
			 p.subScore();
			 String replaceP = p.getPostWriteData(true);
			 
			 if (writable) {
				 try {
					WriteFile.addVotedToFile(v, fileNames.get(7));
					WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
				} 
				 catch (IOException e) {
					e.printStackTrace();
				}
			 }
			 
			 return true;
		 }
	}
	 
	 /*
	 * For downvote a Response:
	 * If a Voted object already exists:
	 * 		If the Voted was already downvoted
	 * 			Remove the downvote and add to Response score
	 * 			Update the Response in Response.txt, remove the Voted in Voted.txt
	 * 		If the Voted was already upvoted
	 * 			Change to an downvote, subtracting from the Response score twice
	 * 			Update the Response and Voted in Response.txt and Voted.txt
	 * 		If the Voted existed but was neither upvoted or downvoted
	 * 			Change to an downvote, subtract from the Response score
	 * 			Update the Response and Voted in Response.txt and Voted.txt
	 * If a Voted does not already exist:
	 * 		Change the voted to downvote
	 * 		Add the voted to the User, subtract from the Response score
	 * 		Update text files
	 */
	 //tests:4
	 public boolean downvoteResponse(Response r) {
		 Voted v = new Voted(this.currentUser, r);
		 
		 if (Validator.validateVotedPostExists(v, this.currentUser.getVotedList(), false) == true) {
			 
			 Voted v1 = Validator.getVotedByUserPost(this.currentUser, r, this.currentUser.getVotedList(), false);
			 
			 if (v1.getDown()) {

				 String findP = r.getResponseWriteData(true);
				 v1.cancelVote();
				 r.addScore();
				 String replaceP = r.getResponseWriteData(true);
				 
				 if (writable) {
					 
					 Voted remove = new Voted(this.currentUser, r);
					 remove.down();
					 try {
						WriteFile.removeVotedFromFile(remove, fileNames.get(7));
						WriteFile.updatePostInFile(findP, replaceP, fileNames.get(5));
					} 
					 catch (IOException e) {
						e.printStackTrace();
					}
				 }
				 return true;
				 
			 }
			 else if (v1.getUp()) {
				 String findV = v1.getVotedWriteData();
				 String findP = r.getResponseWriteData(true);
				 v1.down();
				 r.subScore();
				 r.subScore();
				 String replaceV = v1.getVotedWriteData();
				 String replaceP = r.getResponseWriteData(true);
				 if (writable) {
					 try {
							WriteFile.updateVotedInFile(findV, replaceV, fileNames.get(7));
							WriteFile.updateResponseInFile(findP, replaceP, fileNames.get(6));
						} 
						 catch (IOException e) {
							e.printStackTrace();
						} 
				 }
				 
				 return true;
			 }
			 else {

				 String findP = r.getResponseWriteData();
				 v1.down();
				 r.subScore();
				 String replaceP = r.getResponseWriteData();

				 if (writable) {
					 try {
							WriteFile.addVotedToFile(v1, fileNames.get(7));
							WriteFile.updateResponseInFile(findP, replaceP, fileNames.get(6));
						} 
						 catch (IOException e) {
							e.printStackTrace();
						} 
				 }
				 return true;
			 }
			 
		 }
		 else {
			 String findP = r.getResponseWriteData(true);
			 v.down();
			 this.currentUser.addVoted(v);
			 r.subScore();
			 String replaceP = r.getResponseWriteData(true);
			 
			 if (writable) {
				 try {
					WriteFile.addVotedToFile(v, fileNames.get(7));
					WriteFile.updateResponseInFile(findP, replaceP, fileNames.get(6));
				} 
				 catch (IOException e) {
					e.printStackTrace();
				}
			 }
			 
			 return true;
		 }
	}
	
	//test:2
	//US41 - User can view a list of posts with the largest number of up-votes summed across a post and all its responses
	// sorts post by score
	public ArrayList<Post> getPosts_ByScore() {
		ArrayList<Post> posts = getAllPost();
		Collections.sort(posts, new SortPostsByCombinedScore());
		return posts;
	}
	
	//test:1
	// US42 - User can view a list of most up-voted Users
	public ArrayList<User> viewMostUpVotedUsers(){
		ArrayList<Post> posts = getPosts_ByScore();
		ArrayList<User> users = new ArrayList<>();
		for(Post p: posts) {
			users.add(p.getUser());
		}
		return users;
	}
	
	//FIXME: add tests
	public boolean votedPostExists(Post p) {
		Voted v = new Voted(this.currentUser, p);
        return Validator.validateVotedPostExists(v, this.currentUser.getVotedList(), true);
	}
	
	//FIXME: add tests
	public boolean hasUpvotedPost(boolean isPost, Post p) {
		Voted v = Validator.getVotedByUserPost(this.currentUser, p, this.currentUser.getVotedList(), isPost);
		return v.getUp();
	}
	
	//FIXME: add tests
	public boolean hasDownvotedPost(boolean isPost, Post p) {
		Voted v = Validator.getVotedByUserPost(this.currentUser, p, this.currentUser.getVotedList(), isPost);
		return v.getDown();
	}
	
	
	//FIXME: add tests
	public boolean votedResponseExists(Response r) {
		Voted v = new Voted(this.currentUser, r);
        return Validator.validateVotedPostExists(v, this.currentUser.getVotedList(), false);
	}
	
	//FIXME: add tests
	public boolean hasUpvotedResponse(Response r, boolean isPost) {
		Voted v = Validator.getVotedByUserPost(this.currentUser, r, this.currentUser.getVotedList(), isPost);
		return v.getUp();
	}
	
	//FIXME: add tests
	public boolean hasDownvotedResponse(Response r, boolean isPost) {
		Voted v = Validator.getVotedByUserPost(this.currentUser, r, this.currentUser.getVotedList(), isPost);
		return v.getDown();
	}

}