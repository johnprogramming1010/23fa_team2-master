package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadFile {
	
	static boolean currentlyReadingData;	//True if data is currently being read, false if between sets of data
	
	
	public static void readFile(SystemManager manager, ArrayList<String> fileNames) throws FileNotFoundException, IncorrectFileFormatException {
		
		for (String fileName : fileNames) {		//Loop through each file provided
		
			File dataFile = new File(fileName);		//Create File from name
			
			try {
				Scanner reader = new Scanner(dataFile);
				
				currentlyReadingData = false;	//not Currently reading data
				
				
				while (reader.hasNextLine()) {	//While there are still lines to be read
					String line = reader.nextLine();	//Line currently being read
					
					if (line.equals("@START")) {								//If line is the start of a set of Data
						if (currentlyReadingData) {									//and data is currently being read
							throw new IncorrectFileFormatException();					//throw exception
						}
						else {														//or if data is not being read
							currentlyReadingData = true;								//set currentlyReadingData to true
							continue;													//and continue to next line
						}
					}
					else if (line.equals("@ADMIN") && currentlyReadingData) {	//If current line rules next data set to be a Admin
						readAdmin(manager, reader);									//go read the data in the Admin
					}
					else if (line.equals("@USER") && currentlyReadingData) {	//If current line rules next data set to be a User
						readUser(manager, reader);									//go read the data in the User
					}
					else if(line.equals("@CATEGORY") && currentlyReadingData) {	//If current line rules next data set to be a Category
						readCategory(manager, reader);								//go  read the data in the Category
					}
					else if(line.equals("@GROUP") && currentlyReadingData) {	//If current line rules next data set to be a Group
						readGroup(manager, reader);									//go read the data in the Group
					}
					else if(line.equals("@MEMBERSHIP") && currentlyReadingData) {//If current line rules next data set to be a Membership
						readMembership(manager, reader);							//go read the data in the Membership
					}
					else if(line.equals("@POST") && currentlyReadingData) {		//If current line rules next data set to be a Post
						readPost(manager, reader);									//go read the data in the Post
					}
					else if (line.equals("@RESPONSE") && currentlyReadingData) {//If current line rules next data set to be a Response
						readResponse(manager, reader);								//go read the data in the Response
					}
					else if (line.equals("@BANNED") && currentlyReadingData) {
						readBanned(manager, reader);
					}
					else if (line.equals("@VOTED") && currentlyReadingData) {
						readVoted(manager, reader);
					}
					else if (line.equals("@SUSPENDED")) {
						readSuspended(manager, reader);
					}
					else if (line.equals("")) {									//If the current line is empty
						continue;													//continue to next line
					}
					else {														//If none of the above
						throw new IncorrectFileFormatException();					//throw incorrectFileFormatException
					}
				}
				
				reader.close();
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				throw new FileNotFoundException();
			} catch (IncorrectFileFormatException e) {
				// TODO Auto-generated catch block
				throw e;
			}
		
		}
		
	}
	
	//test:7
	private static void readAdmin(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		String name = "";				//Name of the User
		boolean gotName = false;			//Set to true once name is read
		String birthdate = "";			//Birthday of the User
		boolean gotBday = false;			//Set to true once bday is read
		String city = "";				//City of the User
		boolean gotCity = false;			//Set to true once city is read
		String state = "";				//State of the User
		boolean gotState = false;			//Set to true once state is read
		String username = "";			//Username of the User
		boolean gotUsername = false;		//Set to true once username is read
		String password = "";			//password of the User
		boolean gotPassword = false;		//Set to true once password is read
		String regDate = "";			//RegisteredDate of the user
		boolean gotRegDate = false;			//Set to true once regDate is read
		
		
		
		while(currentlyReadingData) {		//While the current data set is being read
			
			String line = reader.nextLine();	//Line currently being read
			
			
			if (line.equals("@END")) {			//If the line rules the end of the data set
				break;								//break the loop
			}
			
			String sub = "";	//For the first part of the line to identify what data is being read
			
			try {
				sub = line.substring(0, 5);	//Set sub to first 5 characters of line
			}
			catch (StringIndexOutOfBoundsException e) {	//If that can't be read
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
			if (sub.equals("@NAME")) {					//If the substring rules that the line contains the Name
				if(gotName) {								//and if a Name has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Name has not already been read
					name = line.substring(6);					//get the Name from the line
					gotName = true;								//set gotName to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@BIRT")) {				//If the substring rules that the line contains the Birthday
				if(gotBday) {								//and if a Bday has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Bbay has not already been read
					birthdate = line.substring(11);				//get the Bday from the line
					gotBday = true;								//set gotBday to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@CITY")) {				//If the substring rules that the line contains the City
				if(gotCity) {								//and if the City has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the City has not already been read
					city = line.substring(6);					//get the City from the line
					gotCity = true;								//set gotCity to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@STAT")) {				//If the substring rules that the line contains the State
				if(gotState) {								//and if the State has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the State has not already been read
					state = line.substring(7);					//get the State from the line
					gotState = true;							//set gotState to true
					continue;									//and continue to the next line
				}
				
			}
			else if (sub.equals("@USER")) {				//If the substring rules that the line contains the Username
				if(gotUsername) {							//and if the Username has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the Username has not already been read
					username = line.substring(10);				//get Username from the line
					gotUsername = true;							//set gotUsername to true
					continue;									//and continue to the next line
				}
				
			}
			else if (sub.equals("@PASS")) {				//If the substring rules that the line contains the Password
				if(gotPassword) {							//and if the Password has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the Password has not already been read
					password = line.substring(10);				//get Password from the line
					gotPassword = true;							//set gotPassword to true
					continue;									//and continue to the next line 
				}
				
			}
			else if (sub.equals("@REGI")) {				//If the substring rules that the line contains the RegisteredDate
				if(gotRegDate) {							//and if the RegDate has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the RegDate has nor already been read
					regDate = line.substring(17);				//get RegDate from the line
					gotRegDate = true;							//set gorRegDate to true
					continue;									//and continue to the next line
				}
				
			}
			else {										//If what data is being read cannot be determined
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
		}
		
		//If all the bits of data were read from the file for the Admin
		if (gotName && gotBday && gotCity && gotState && gotUsername && gotPassword && gotRegDate) {
			Admin a = new Admin(name, username, password, birthdate, city, state, regDate);	//create new Admin
			manager.addAdmin(a);				//Add to manager
			currentlyReadingData = false;		//set currentlyReadingData to false
		}
		else {										//If any bit of data was not collected
			throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
		}
		
	}
	
	//test:6
	private static void readUser(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		String name = "";				//Name of the User
		boolean gotName = false;			//Set to true once the name has been read
		String bday = "";				//Bday of the User
		boolean gotBday = false;			//Set to true once the birthday has been read
		String city = "";				//City of the User
		boolean gotCity = false;			//Set to true once the city has been read
		String state = "";				//State of the User
		boolean gotState = false;			//Set to true once the state has been read
		String username = "";			//Username of the User
		boolean gotUsername = false;		//Set to true once the username has been read
		String password = "";			//Password of the User
		boolean gotPassword = false;		//Set to true once the password has been read
		String regDate = "";			//RegDate of the User
		boolean gotRegDate = false;			//Set to true once the register date has been read
		
		while(currentlyReadingData) {		//While current data sat is being read
			
			String line = reader.nextLine();	//current line being read
			
			
			if (line.equals("@END")) {			//If the line rules the end of the data set
				break;								//break the loop
			}
			
			String sub = "";	//For the first part of the line to identify what data is being read
			
			try {
				sub = line.substring(0, 5);			//Set sub to first 5 characters of line
			}
			catch (StringIndexOutOfBoundsException e) {	//If that can't be read
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
			if (sub.equals("@NAME")) {					//If the substring rules that the line contains the Name
				if(gotName) {								//and if a Name has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Name has not already been read
					name = line.substring(6);					//get the Name from the line
					gotName = true;								//set gotName to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@BIRT")) {				//If the substring rules that the line contains the Birthday
				if(gotBday) {								//and if a Bday has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Bbay has not already been read
					bday = line.substring(11);				//get the Bday from the line
					gotBday = true;								//set gotBday to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@CITY")) {				//If the substring rules that the line contains the City
				if(gotCity) {								//and if the City has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the City has not already been read
					city = line.substring(6);					//get the City from the line
					gotCity = true;								//set gotCity to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@STAT")) {				//If the substring rules that the line contains the State
				if(gotState) {								//and if the State has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the State has not already been read
					state = line.substring(7);					//get the State from the line
					gotState = true;							//set gotState to true
					continue;									//and continue to the next line
				}
				
			}
			else if (sub.equals("@USER")) {				//If the substring rules that the line contains the Username
				if(gotUsername) {							//and if the Username has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the Username has not already been read
					username = line.substring(10);				//get Username from the line
					gotUsername = true;							//set gotUsername to true
					continue;									//and continue to the next line
				}
				
			}
			else if (sub.equals("@PASS")) {				//If the substring rules that the line contains the Password
				if(gotPassword) {							//and if the Password has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the Password has not already been read
					password = line.substring(10);				//get Password from the line
					gotPassword = true;							//set gotPassword to true
					continue;									//and continue to the next line 
				}
				
			}
			else if (sub.equals("@REGI")) {				//If the substring rules that the line contains the RegisteredDate
				if(gotRegDate) {							//and if the RegDate has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the RegDate has nor already been read
					regDate = line.substring(17);				//get RegDate from the line
					gotRegDate = true;							//set gorRegDate to true
					continue;									//and continue to the next line
				}
				
			}
			else {										//If what data is being read cannot be determined
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
		}
		
		//If all the bits of data were read from the file for the Admin
		if (gotName && gotBday && gotCity && gotState && gotUsername && gotPassword && gotRegDate) {
			User a = new User(name, username, password, bday, city, state, regDate);	//Create a new User
			manager.addUser(a);				//Add new User to manager
			currentlyReadingData = false;	//Set currentlyReadingData to false	
		}
		else {										//If any bit of data was not collected
			throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
		}
	}

	//test:5
	private static void readCategory(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String name = "";			//Name of the Category
		boolean gotName = false;		//Set gotName to false
		
		while (currentlyReadingData) {			//While current data set is being read
			
			String line = reader.nextLine();	//Current line being read
			
			if (line.equals("@END")) {			//If the line rules the end of the data set
				break;								//break the loop
			}
			
			String sub = "";		//For the first part of the line to identify what data is being read
			
			try {
				sub = line.substring(0, 5);			//Set sub to first 5 characters of line
			}
			catch (StringIndexOutOfBoundsException e) {	//If that can't be read
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
			if (sub.equals("@NAME")) {					//If the substring rules that the line contains the Name
				if (gotName) {								//and if a Name has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Name has not already been read
					name = line.substring(6);					//get the Name from the line
					gotName = true;								//set gotName to true
					continue;									//and continue to next line
				}
				
			}
			else {										//If what data is being read cannot be determined
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
		}
		
		if (gotName) {			//If all the bits of data were read from the file for the category
			category c = new category(name);	//Create the category
			manager.addCategory(c);				//Add it to the manager
			currentlyReadingData = false;		//Set currentlyReadingData to false
		}
		else {					//If any bit of data was not collected
			throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
		}
		
	}
	
	//test:1
	private static void readGroup(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		

		String name = "";
		boolean gotName = false;
		String catName = "";
		boolean gotCatName = false;
		String postId = "";
		boolean gotPostId = false;

		
		while (currentlyReadingData) {			//While their is still more data to be read
			
			String line = reader.nextLine();		//Read the next line
			
			if (line.equals("@END")) {				//If the current line rules the end of the data set
				currentlyReadingData = false;			//Set currentlyReadingData to false
				break;									//Break the loop
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);					//Attempt to get the 1st 5 characters of the current line and save in sub
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();		//Throw exception if current line is not long enough
			}
			
			if (sub.equals("@NAME")) {							//If sub rules that the data contains the Name
				if (gotName) {										//If the Name has already been read
					throw new IncorrectFileFormatException();			//Throw exception
				}
				else {												//Otherwise
					name = line.substring(6);							//Read the Name from the line
					gotName = true;										//Set gotName to true
					continue;											//Continue to the next line
				}
			}
			else if (sub.equals("@CATE")) {						//If sub rules that the data contains the Category Name
				if (gotCatName) {									//If the Category Name has already been Read
					throw new IncorrectFileFormatException();			//Throw exception
				}
				else {												//Otherwise
					catName = line.substring(10);						//Read the Category Name from the line
					gotCatName = true;									//Set gotCatName to true
					continue;											//Continue to the next line
				}
			}
			else if (sub.equals("@POST")) {						//If sub rule that the data contains the PostID
				if (gotPostId) {									//If the PostID has already been Read
					throw new IncorrectFileFormatException();			//Throw exception
				}
				else {												//Otherwise 
					postId = line.substring(8);							//Read the PostID from the line
					gotPostId = true;									//Set gotPostId to true
					continue;											//Continue to the next line
				}
			}
			else {												//If anything else is encountered in the Line
				throw new IncorrectFileFormatException();			//Throw exception
			}
			
		}
		
		if (gotName && gotCatName) {		//If both the Group Name and Category Name was Read
			

			category c = manager.getCategoryByName(catName);	//Get the Category by Name
			int ID = Integer.parseInt(postId);					//Parse the PostID
			
			if (c != null) {					//If the Category exists
				

				Group g = new Group(name, ID);		//Create the Group
				
				c.addGroup(g);						//Save the group to the Category
				
			}
		}
		else {								//If either the Group Name or Category Name was not Read
			throw new IncorrectFileFormatException();	//Throw Exception
		}
		
	}
	
	//test:1
	private static void readMembership(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String userName = "";			//ID of the User
		boolean gotUserName = false;		//Set to true when ID is Read
		String groupName = "";			//Name of the Group
		boolean gotGroupName = false;		//Set to true when the Group Name is Read
		String regDate = "";			//Registration Date of Membership
		boolean gotRegDate = false;			//Set to true when Registration Date is Read
		
		while (currentlyReadingData) {			//While the current data set is being Read
			
			String line = reader.nextLine();		//Get the next Line
			
			if (line.equals("@END")) {				//If the current line rules the end of the Data Set
				currentlyReadingData = false;			//Set currentlyReadingData to false
				break;									//Break the loop
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);			//Attempt to get the 1st 5 characters from the line and save to sub
			}
			catch (StringIndexOutOfBoundsException e) {		//If not possible
				throw new IncorrectFileFormatException();		//Throw exception
			}
			
			if (sub.equals("@USER")) {							//If sub rules the current line contains the User ID 
				 if (gotUserName) {									//If the User ID has already been Read
					 throw new IncorrectFileFormatException();			//Throw Exception
				 }
				 else {												//Otherwise
					 userName = line.substring(6);						//Get User ID from the current line
					 gotUserName = true;								//Set gotUserName to true
					 continue;											//continue to the next line
				 }
			}
			else if (sub.equals("@GROU")) {						//If sub rules the current line contains the Group Name
				if (gotGroupName) {									//If the Group Name has already been Read
					throw new IncorrectFileFormatException();			//Throw Exception
				}
				else {												//Otherwise
					groupName = line.substring(7);						//Get Group Name from the current line
					gotGroupName = true;								//Set gotGroupName to true
					continue;											//continue to the next line
				}
			}
			else if (sub.equals("@REGI")) {						//If sub rules the current line contains the Registration Date
				if (gotRegDate) {									//If the Registration Date has already been Read
					throw new IncorrectFileFormatException();			//Throw Exception
				}
				else {												//Otherwise
					regDate = line.substring(16);						//Get the Registration Date from the current line
					gotRegDate = true;									//Set gotRegDate to true
					continue;											//continue to the next line
				}
			}
			else {												//If anything else was encountered in sub
				throw new IncorrectFileFormatException();			//Throw exception
			}
			
		}
		
		if (gotUserName && gotGroupName && gotRegDate) {	//If the User ID, Group Name, and Registration Date was Read
			

			User u = manager.getUserByUsername(userName);
			if (u == null) {
				u = manager.getAdminByUsername(userName);
			}
			Group g = manager.getGroupByName(groupName);

			
			if (!(u == null) && !(g == null)) {					//If both the USer and Group Exists
			
				membership m = new membership(u, g, regDate);		//Create the Membership
				g.addMember(m);										//Add Membership to Group
				
			}
			else {												//If either the User or Group doesn't exist
				throw new IncorrectFileFormatException();			//Throw exception
			}
			
		}
		else {												//If any of User ID, Group Name, or Registration Date was not read
			throw new IncorrectFileFormatException();			//Throw exception
		}
		
	}
	
	
	private static void readPost(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String userName = "";			//username of the User
		boolean gotUserName = false;		//Set gotUsername to true once it has been Read
		String groupName = "";			//Name of the Group
		boolean gotGroupName = false;		//Set gotGroupName to true once it has been Read
		String dateTime = "";			//DateTime the Post was made
		boolean gotDateTime = false;		//Set gotDateTime to true once it has been Read
		String postTitle = "";			//Title of the Post
		boolean gotPostTitle = false;		//Set gotPostTitle to true once it has been Read
		String postBody = "";			//Body of the Post
		boolean gotPostBody = false;		//Set gotPostBody to true once it has been Read
		String postId = "";				//ID of the Post
		boolean gotPostId = false;			//Set gotPostId to true once it has been Read
		String scoreStr = "";			//Score of the Post
		boolean gotScore = false;			//Set gotScore to true once it has been Read
		boolean flag = false;
		String responseID = "";
		boolean gotResponseID = false;
		
		
		
		while (currentlyReadingData) {			//While the current data set is being Read
			
			String line = reader.nextLine();		//Get the next line
			
			if (line.equals("@END")) {				//If the current line is the end of the data set
				currentlyReadingData = false;			//Set currentlyReadingData to false
				 break;									//break the loop
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);			//try getting the 1st few characters of the line to identify what data is currently being Read
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			if (sub.equals("@USER")) {								//If sub rules the current line contains the username
				 if (gotUserName) {										//If the username has already been Read
					 throw new IncorrectFileFormatException();				//throw exception
				 }
				 else {													//Otherwise
					 userName = line.substring(10);							//Get the username from the line
					 gotUserName = true;									//Set gotUserName to true
					 continue;												//Continue to the next line
				 }
			}
			else if (sub.equals("@GNAM")) {							//If sub rules the current line contains the Group Name
				if (gotGroupName) {										//If the Group Name has already been Read
					throw new IncorrectFileFormatException();				//Throw exception
				}
				else {													//Otherwise
					groupName = line.substring(7);							//Get the Group Name from the line
					gotGroupName = true;									//Set gotGroupName to true
					continue;												//continue to the next line
				}
			}
			else if (sub.equals("@DATE")) {							//If sub rules the current line contains the DateTime
				if (gotDateTime) {										//If the DateTime has already been Read
					throw new IncorrectFileFormatException();				//Throw exception
				}
				else {													//Otherwise
					dateTime = line.substring(10);							//Get the DateTime from the line
					gotDateTime = true;										//Set gotDateTime to true
					continue;												//continue to the next line
				}
			}
			else if (sub.equals("@TITL")) {							//If sub rules the current line contains the Post Title
				if (gotPostTitle) {										//If the Post Title has already been Read
					throw new IncorrectFileFormatException();				//Throw exception
				}
				else {													//Otherwise
					postTitle = line.substring(7);							//Get the Post Title from the line
					gotPostTitle = true;									//Set gotPostTitle to true
					continue;												//continue to the next line
				}
			}
			else if (sub.equals("@BODY")) {							//If sub rules the current line contains the Post Body
				if (gotPostBody) {										//If the Post Body has already been Read
					throw new IncorrectFileFormatException();				//Throw exception
				}
				else {													//Otherwise
					line = reader.nextLine();								//continue to the next line
					while (!line.equals("@BODYEND")) {						//Loop through lines until the end of the Post Body
						postBody += line + "\n";										//Concat lines to Post Body
						line = reader.nextLine();								//continue to the next line
					}
					gotPostBody = true;
					continue;
				}
			}
			else if (sub.equals("@PSTI")) {						//If sub rules the current line contains the PostID
				if (gotPostId) {										//If the PostID has already been Read
					throw new IncorrectFileFormatException();				//Throw exception
				}
				else {													//Otherwise
					postId = line.substring(7);								//Get the PostID from the line
					gotPostId = true;										//Set gotPostID to true
					continue;												//continue to the next line
				}
			}
			else if (sub.equals("@SCOR")) {						//If sub rules the current line contains the Score
				if (gotScore) {										//If the Score has already been Read
					throw new IncorrectFileFormatException();				//Throw exception
				}
				else {													//Otherwise
					scoreStr = line.substring(7);							//Get the Score from the line
					gotScore = true;										//Set gotScore to true
					continue;												//continue to the next line
				}
			}
			else if (sub.equals("@FLAG")) {
				if (flag) {
					throw new IncorrectFileFormatException();
				}
				else {
					flag = true;
					continue;
				}
			}
			else if (sub.equals("@RESP")) {	
				if (gotResponseID) {
					throw new IncorrectFileFormatException();
				}
				else {	
					responseID = line.substring(12);
					gotResponseID = true;		
					continue;	
				}
			}
			else {												//If anything else is encountered
				throw new IncorrectFileFormatException();			//Throw exception
			}
			
		}
		
		//If all the needed Data was Read
		if (gotUserName && gotGroupName && gotDateTime && gotPostTitle && gotPostBody && gotPostId && gotScore && gotResponseID) {
			

			Group g = manager.getGroupByName(groupName);
			User u = manager.getUserByUsername(userName);
			if (u == null) {
				u = manager.getAdminByUsername(userName);
			}
			int id = Integer.parseInt(postId);
			int score = Integer.parseInt(scoreStr);
			int rID = Integer.parseInt(responseID);
			if (g != null && u != null) {
				
				Post p = new Post(u, g, dateTime, postTitle, postBody, id, score, rID);	//Create the Post

				g.addExistingPost(p);	//Add the Post to the Group
				
				if (flag) {
					p.setFlagTrue();
				}

			}
			else {		//If either the Group or User does not Exist
				throw new IncorrectFileFormatException();	//Throw Exception
			}
		}
		else {			//If any bit of the needed data was not Read
			throw new IncorrectFileFormatException();	//Throw Exception
		}
		
	}
	
	
	private static void readResponse(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String userName = "";				//username of the User
		boolean gotUserName = false;			//Set to true once username has been Read
		String groupName = "";				//Name of the Group
		boolean gotGroupName = false;			//Set to true once Group Name has been Read
		String dateTime = "";				//DateTime the Response was made
		boolean gotDateTime = false;			//Set to true once DateTime has been Read
		String responseBody = "";			//Body of the Response
		boolean gotResponseBody = false;		//Set to true once Response Body has been Read
		String parentalId = "";				//ID of the Post this Response is under
		boolean gotParentalId = false;			//Set to true once ParentalID has been Read
		String scoreStr = "";				//Score of the Response
		boolean gotScore = false;				//Set to true once Score has been Read
		boolean flag = false;
		String responseID = "";
		boolean gotResponseID = false;
		
		
		
		while (currentlyReadingData) {			//While the current data set is being Read
			
			String line = reader.nextLine();		//Get the next Line
			
			if (line.equals("@END")) {				//If the line rules the end of the data set
				currentlyReadingData = false;			//Set currentlyReadingData to false
				break;									//Break the Loop
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);			//try to get the 1st few characters of the line
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			if (sub.equals("@USER")) {					//If sub rule the current line contains the username
				 if (gotUserName) {							//If the username has already been read
					 throw new IncorrectFileFormatException();	//Throw exception
				 }
				 else {										//Otherwise
					 userName = line.substring(10);				//Get the username from the line
					 gotUserName = true;						//Set gotUserName to true
					 continue;									//Continue to the next line
				 }
			}
			else if (sub.equals("@GNAM")) {				//If sub rule the current line contains the Group Name
				if (gotGroupName) {							//If the Group Name has already been read
					throw new IncorrectFileFormatException();	//Throw exception
				}
				else {										//Otherwise
					groupName = line.substring(7);				//Get the Group Name from the line
					gotGroupName = true;						//Set gotGroupName to true
					continue;									//Continue to the next line
				}
			}
			else if (sub.equals("@DATE")) {				//If sub rule the current line contains the DateTime
				if (gotDateTime) {							//If the DateTime has already been read
					throw new IncorrectFileFormatException();	//Throw exception
				}
				else {										//Otherwise
					dateTime = line.substring(10);				//Get the DateTime from the line
					gotDateTime = true;							//Set gotDateTime to true
					continue;									//Continue to the next line
				}
			}
			else if (sub.equals("@BODY")) {				//If sub rule the current line contains the Response Body
				if (gotResponseBody) {							//If the ResponseBody has already been read
					throw new IncorrectFileFormatException();	//Throw exception
				}
				else {										//Otherwise
					line = reader.nextLine();
					while (!line.equals("@BODYEND")) {
						responseBody += line + "\n";
						line = reader.nextLine();
					}
					gotResponseBody = true;						//Set gotResponseBody to true
					continue;									//Continue to the next line
				}
			}
			else if (sub.equals("@PARE")) {				//If sub rule the current line contains the ParentalID
				if (gotParentalId) {						//If the ParentalID has already been read
					throw new IncorrectFileFormatException();	//Throw exception
				}
				else {										//Otherwise
					parentalId = line.substring(12);			//Get the ParentalID from the line
					gotParentalId = true;						//Set gotParentalId to true
					continue;									//Continue to the next line
				}
			}
			else if (sub.equals("@SCOR")) {				//If sub rule the current line contains the Score
				if (gotScore) {								//If the Score has already been read
					throw new IncorrectFileFormatException();	//Throw exception
				}
				else {										//Otherwise
					scoreStr = line.substring(7);				//Get the Score from the line
					gotScore = true;							//Set gotScore to true
					continue;									//Continue to the next line
				}
			}
			else if (sub.equals("@FLAG")) {
				if (flag) {
					throw new IncorrectFileFormatException();
				}
				else {
					flag = true;
					continue;
				}
			}
			else if (sub.equals("@RESP")) {	
				if (gotResponseID) {
					throw new IncorrectFileFormatException();
				}
				else {	
					responseID = line.substring(12);
					gotResponseID = true;		
					continue;	
				}
			}
			else {
				throw new IncorrectFileFormatException();	//Throw exception
			}
			
		}
		
		
		//If all the needed data was Read
		if (gotUserName && gotGroupName && gotDateTime && gotResponseBody && gotParentalId && gotScore && gotResponseID) {
			
			Group g = manager.getGroupByName(groupName);
			User u = manager.getUserByUsername(userName);
			if (u == null) {
				u = manager.getAdminByUsername(userName);
			}
			int id = Integer.parseInt(parentalId);
			int score = Integer.parseInt(scoreStr);
			int rID = Integer.parseInt(responseID);
			Post p = manager.getPostByGroupId(g, id);
			if (g != null && u != null && p != null) {
				Response r = new Response(u, g, dateTime, responseBody, id, score, rID);	//Create the Response
				if (flag) {
					r.setFlagTrue();
				}
				p.addExistingResponse(r);													//Add the Response to the Post
				
			}
			else {			//If either the USer, Group, or Post does not Exist
				throw new IncorrectFileFormatException();	//Throw exception
			}
		}
		else {				//If any bit of data was not Read
			throw new IncorrectFileFormatException();	//Throw exception
		}
		
	}




	private static void readBanned(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
	
		String userName = "";
		boolean gotUserName = false;
		String groupName = "";
		boolean gotGroupName = false;
		

		
		while (currentlyReadingData) {

			
			String line = reader.nextLine();
			
			if (line.equals("@END")) {
				currentlyReadingData = false;
				break;
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			
			if (sub.equals("@USER")) {
				if (gotUserName) {
					throw new IncorrectFileFormatException();
				}
				else {
					userName = line.substring(6);
					gotUserName = true;
					continue;
				}
			}
			else if (sub.equals("@GROU")) {
				if (gotGroupName) {
					throw new IncorrectFileFormatException();
				}
				else {
					groupName = line.substring(7);
					gotGroupName = true;
					continue;
				}
			}
			else {
				throw new IncorrectFileFormatException();
			}
			
		}
		
		
		if (gotUserName && gotGroupName) {
			
			Group g = manager.getGroupByName(groupName);
			User u = manager.getUserByUsername(userName);
			
			if (!(g == null) && !(u == null)) {
				
				Banned b = new Banned(u, g);
				g.addBanned(b);
				
			}
			
		}
	
	
	}
	
	
		
	private static void readVoted(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String username = "";
		boolean gotUsername = false;
		String groupName = "";
		boolean gotGroupName = false;
		String postID = "";
		boolean gotPostID = false;
		String responseID = "";
		boolean gotResponseID = false;
		boolean upvote = false;
		boolean downvote = false;
		
		
		while (currentlyReadingData) {
			
			String line = reader.nextLine();		//Get the next line
			
			if (line.equals("@END")) {				//If the current line is the end of the data set
				currentlyReadingData = false;			//Set currentlyReadingData to false
				 break;									//break the loop
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);			//try getting the 1st few characters of the line to identify what data is currently being Read
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			if (sub.equals("@USER")) {
				if (gotUsername) {
					throw new IncorrectFileFormatException();
				}
				else {
					username = line.substring(6);
					gotUsername = true;
					continue;
				}
			}
			else if (sub.equals("@GROU")) {
				if (gotGroupName) {
					throw new IncorrectFileFormatException();
				}
				else {
					groupName = line.substring(7);
					gotGroupName = true;
					continue;
				}
			}
			else if (sub.equals("@POST")) {
				if (gotPostID) {
					throw new IncorrectFileFormatException();
				}
				else {
					postID = line.substring(8);
					gotPostID = true;
					continue;
				}
			}
			else if (sub.equals("@RESP")) {
				if (gotResponseID) {
					throw new IncorrectFileFormatException();
				}
				else {
					responseID = line.substring(12);
					gotResponseID = true;
					continue;
				}
			}
			else if (sub.equals("@UPVO")) {
				if (upvote) {
					throw new IncorrectFileFormatException();
				}
				else {
					upvote = true;
					continue;
				}
			}
			else if (sub.equals("@DOWN")) {
				if (downvote) {
					throw new IncorrectFileFormatException();
				}
				else {
					downvote = true;
					continue;
				}
			}
			else {
				throw new IncorrectFileFormatException();
			}
			
		}
		
		
		if (gotUsername && gotGroupName && gotPostID && (upvote || downvote)) {
			
			Group g = manager.getGroupByName(groupName);
			User u = manager.getUserByUsername(username);
			if (u == null) {
				u = manager.getAdminByUsername(username);
			}
			int pID = Integer.parseInt(postID);
			Post p = manager.getPostByGroupId(g, pID);
			
			if (u != null && g != null && p != null) {
				
				if (gotResponseID) {
					
					int rID = Integer.parseInt(responseID);
					Response r = manager.getResponseByPostAndID(p, rID);
					
					if (r != null) {
						Voted v = new Voted(u, r);
						u.addVoted(v);
						if (upvote) {
							v.up();
						}
						else if (downvote) {
							v.down();
						}
					}
					else {
						
					}
					
				}
				else {
					Voted v = new Voted(u, p);
					u.addVoted(v);
					if (upvote) {
						v.up();
					}
					else if (downvote) {
						v.down();
					}
				}
			
			}
			else if (u != null && g != null && p == null) {
				
			}
			else {
				throw new IncorrectFileFormatException();
			}
		}
		
		
	}



	//test:1
	private static void readSuspended(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String userId = "";
		boolean gotUserId = false;
		String groupName = "";
		boolean gotGroupName = false;
		String susDate = "";
		boolean gotSusDate = false;
		String expDate = "";
		boolean gotExpDate = false;
		
		while (currentlyReadingData) {
			
			String line = reader.nextLine();
			
			if (line.equals("@END")) {
				currentlyReadingData = false;
				break;
			}
			
			String sub = "";
			try {
				sub = line.substring(0, 5);
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			if (sub.equals("@USER")) {
				if (gotUserId) {
					throw new IncorrectFileFormatException();
				}
				else {
					userId = line.substring(6);
					gotUserId = true;
					continue;
				}
			}
			else if (sub.equals("@GROU")) {
				if (gotGroupName) {
					throw new IncorrectFileFormatException();
				}
				else {
					groupName = line.substring(7);
					gotGroupName = true;
					continue;
				}
			}
			else if (sub.equals("@SUSP")) {
				if (gotSusDate) {
					throw new IncorrectFileFormatException();
				}
				else {
					susDate = line.substring(15);
					gotSusDate = true;
					continue;
				}
			}
			else if (sub.equals("@EXPI")) {
				if (gotExpDate) {
					throw new IncorrectFileFormatException();
				}
				else {
					expDate = line.substring(13);
					gotExpDate = true;
					continue;
				}
			}
			else {
				throw new IncorrectFileFormatException();
			}
			
		}
		
		
		
		if (gotUserId && gotGroupName && gotSusDate && gotExpDate) {
			
			User u = manager.getUserByUsername(userId);
			Group g = manager.getGroupByName(groupName);
			
			if (u != null && g != null) {
				Suspended s = new Suspended(u, g, susDate, expDate);
				g.addSuspended(s);
			}
			
		}
		else {
			throw new IncorrectFileFormatException();
		}
		
	}

	
	
}

