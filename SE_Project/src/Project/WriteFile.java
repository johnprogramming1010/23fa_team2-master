package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {
	
	
	public static void writeFile(SystemManager manager, ArrayList<String> fileNames) throws IOException {
		
		
		for (String fileName : fileNames) {
			
			try {
			
				File dataFile = new File(fileName);
				
				if (!dataFile.exists()) {
					dataFile.createNewFile();
				}
				
				FileWriter writer = new FileWriter(fileName);
				
				if (fileName.contains("Admin")) {
					writeAdmins(manager, writer);
				}
				else if (fileName.contains("User")) {
					writeUsers(manager, writer);
				}
				else if (fileName.contains("Categor")) {
					writeCategories(manager, writer);
				}
				else if (fileName.contains("Group")) {
					writeGroups(manager, writer);
				}
				else if (fileName.contains("Membership")) {
					writeMemberships(manager, writer);
				}
				else if (fileName.contains("Post")) {
					writePosts(manager, writer);
				}
				else if (fileName.contains("Response")) {
					writeResponses(manager, writer);
				}
				else if (fileName.contains("Suspended")) {
					writeSuspended(manager, writer);
				}
				else if (fileName.contains("Ban")) {
					writeBanned(manager, writer);
				}
				else if (fileName.contains("Vote")) {
					writeVoted(manager, writer);
				}
				
				
				writer.close();
			
			}
			catch (IOException e) {
				throw new IOException();
			}
		}
	}
	
	//Gets a list of admins and writes them onto the file
	//test:1
	private static void writeAdmins(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<Admin> admins = manager.getAdmins_Alphabetically_ByUsername();
		
		for (Admin a : admins) {
			String adminData = a.getAdminWriteData();
			
			writer.write(adminData);
		}
		
	}
	
	//Gets a list of users and write them onto the file
	//test:1
	private static void writeUsers(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<User> users = manager.getUsers_Alphabetically_ByUsername();
		
		for (User u : users) {
			String userData = u.getUserWriteData();
			
			writer.write(userData);
		}
		
	}
	
	//Writes the categories on the system manager
	//test:1
	private static void writeCategories(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<category> categories = manager.getCategories_Alphabetically();
		
		for (category c : categories) {
			String catData = c.getCategoryWriteData();
			
			writer.write(catData);
		}
		
	}
	
	//test:1
	private static void writeGroups(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<category> categories = manager.getCategories_Alphabetically();
		
		for (category c : categories) {
			
			ArrayList<Group> groups = c.getGroupsAlphabetically();
			
			String catName = c.getName();
			
			for (Group g : groups) {
				
				String groupData = g.getGroupWriteData(catName);
				
				writer.write(groupData);
				
			}
			
		}
		
	}
	
	//test:1
	private static void writeMemberships(SystemManager manager, FileWriter writer) throws IOException {

		ArrayList<membership> memberships = manager.getAllMemberships();

		for (membership m : memberships) {

			String memberData = m.getMembershipWriteData();

			writer.write(memberData);

		}

	}

	private static void writePosts(SystemManager manager, FileWriter writer) throws IOException {

		ArrayList<Post> posts = manager.getAllPost();

		for (Post p : posts) {

			String postData = p.getPostWriteData();

			writer.write(postData);

		}

	}
	

	private static void writeResponses(SystemManager manager, FileWriter writer) throws IOException {
		
		for (Post p : manager.getAllPost()) {
			
			ArrayList<Response> responses = p.getResponse();
			
			for (Response r : responses) {
				
				String responseData = r.getResponseWriteData();
				
				writer.write(responseData);
				
			}
			
		}
		
	}
	
	
	

	//test:1
	private static void writeSuspended(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<Suspended> suspensions = manager.getAllSuspensions_ByUsername();
		
		for (Suspended s : suspensions) {
			
			String susData = s.getSuspendedWriteData();
			
			writer.write(susData);
			
		}
		
	}
	
	private static void writeBanned(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<Banned> bans = manager.getAllBans_ByUsername();
		
		for (Banned b : bans) {
			
			String banData = b.getBannedWriteData();
			
			writer.write(banData);
			
		}
		
	}
	
	//test:1
	private static void writeVoted(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<Voted> voted = manager.getAllVotes();
		
		for (Voted v : voted) {
			
			String votedData = v.getVotedWriteData();
			
			writer.write(votedData);
			
		}
		
	}
		

	
	public static void addAdminToFile(Admin a, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = a.getAdminWriteData();
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw new IOException();
		}
		
	}
	
	//test:1
	public static void addUserToFile(User u, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = u.getUserWriteData();
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	//test:1
	public static void addCategoryToFile(category c, String fileName) throws IOException {
		
		try {
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = c.getCategoryWriteData();
			
			writer.write(msg);
			
			writer.close();
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	//test:1
	public static void addGroupToFile(Group g, String fileName, String catName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = g.getGroupWriteData(catName);
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;

		}
		
	}
	
	

	public static void addMembershipToFile(membership m, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = m.getMembershipWriteData();
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;

		}
		
	}
	
	
	public static void addPostToFile(Post p, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = p.getPostWriteData(true);
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;

		}
		
	}
	
	
	public static void addResponseToFile(Response r, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = r.getResponseWriteData(true);
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
	}

	//test:1
	public static void addBannedToFile(Banned b, String filename) throws IOException {
		
		try {
			
			File dataFile = new File(filename);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = b.getBannedWriteData();
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	

	//test:1
	public static void addSuspendedToFile(Suspended s, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = s.getSuspendedWriteData();
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	//test:1
	public static void addVotedToFile(Voted v, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = v.getVotedWriteData();
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	
	//test:1
	public static void removeAdminFromFile(Admin a, String fileName) throws IOException {
		
		String find = a.getAdminWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	//test:1
	public static void removeUserFromFile(User u, String fileName) throws IOException {
		
		String find = u.getUserWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	//test:1
	public static void removeCategoryFromFile(category c, String fileName) throws IOException {
		
		String find = c.getCategoryWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	//test:1
	public static void removeGroupFromFile(Group g, String fileName, String catName) throws IOException {
		
		String find = g.getGroupWriteData(catName);
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	

	//test:1
	public static void removeSuspendedFromFile(Suspended s, String fileName) throws IOException {
		
		String find = s.getSuspendedWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String st = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((st = br.readLine()) != null) {
				    totalStr += st + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}

	
	
	public static void removeMembershipFromFile(membership m, String fileName) throws IOException {
		
		String find = m.getMembershipWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	

	public static void removePostFromFile(Post p, String fileName) throws IOException {
		
		String find = p.getPostWriteData(true);
		int idx = find.indexOf("@BODYEND");		//Index for the end of the body
		if (find.charAt(idx-1) != '\n' ) {		//if the character directly before the end of the body is anything other than a newline
			find = find.replace("@BODYEND", "\n@BODYEND");	//add newline before end of body
		}
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
	}
	
	public static void removeResponseFromFile(Response r, String fileName) throws IOException {
		
		String find = r.getResponseWriteData(true);
		int idx = find.indexOf("@BODYEND");
		if (find.charAt(idx-1) != '\n' ) {
			find = find.replace("@BODYEND", "\n@BODYEND");
		}
		System.out.println(find);
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
	}
	
	
	public static void removeResponseFromFile(String find, String replace, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	//test:1
	public static void removeBannedFromFile(Banned b, String filename) throws IOException {
		
		String find = b.getBannedWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(filename);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	//test:1
	public static void removeVotedFromFile(Voted v, String fileName) throws IOException {
		
		String find = v.getVotedWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	
	//tests:1
	public static void updateGroupinFile(String find, String replace, String fileName) throws IOException {

		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {

					totalStr += s + "\n";

				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			

			reader.close();
			


		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	public static void updatePostInFile(String find, String replace, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {

					totalStr += s + "\n";

				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			

			reader.close();
			


		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	//tests:1
	public static void updateResponseInFile(String find, String replace, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {

					totalStr += s + "\n";

				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			

			reader.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	//test:1
	public static void updateVotedInFile(String find, String replace, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			try (BufferedReader br = new BufferedReader(reader)) {
				while ((s = br.readLine()) != null) {
				    totalStr += s + "\n";
				}
			}
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}



}
