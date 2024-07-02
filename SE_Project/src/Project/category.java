package Project;
import java.util.ArrayList;
import java.util.Collections;


public class category implements Comparable<category> {

	private String name;
	private ArrayList<Group> groups;
	private ArrayList<Group> requestedGroups;

	// gives a category a name with an arraylist of groups
	public category(String name) {
		this.name = name;
		this.groups = new ArrayList<Group>();
		this.requestedGroups = new ArrayList<Group>();
	}

	//test:1
	public String getName() {
		return name;
	}
	
	//test:2
	// adds a group if the group name doesn't exist
	public boolean addGroup(Group g) {
		if (Validator.validateGroupNameExists(groups, g.getGroupName())) {	//If the name of group g already exists within the list of Groups
			return false;	//do not add the group and return false
		}
		else {
			groups.add(g);	//else, add the group	NOTICE: This may require more variables as the Group class is updated
			return true;	//and return true
		}
	}
	
	//test:2
	// adds a group if the group name doesn't exist
	public boolean addRequestedGroup(Group g) {
		if (Validator.validateGroupNameExists(requestedGroups, g.getGroupName())) {	//If the name of group g already exists within the list of Groups
			return false;	//do not add the group and return false
		}
		else {
			requestedGroups.add(g);	//else, add the group	NOTICE: This may require more variables as the Group class is updated
			return true;	//and return true
		}
	}

	//test:2
	// create group if the group name doesn't exist
	public boolean createGroup(String name) {
		if(Validator.validateGroupNameExists(groups, name)) {	//If there exists a group with the given name
			return false;	//Return false
		}
		else {
			Group g = new Group(name);	//else, create the group	NOTICE: This may require more variables as the Group class is updated
			groups.add(g);		//add group
			return true;		//and return true
		}
	}

	//test:1
	// returns all groups alphabetically
	public ArrayList<Group> getGroupsAlphabetically() {
		Collections.sort(groups, new SortGroupsByName());

		return groups;
	}
	
	//test:1
	// returns all groups alphabetically
	public ArrayList<Group> getRequestedGroupsAlphabetically() {
		Collections.sort(requestedGroups, new SortGroupsByName());

		return requestedGroups;
	}
	
	//test:1
    //Checks to see if a Group is already a part of a category
    public boolean isGroupInCategory (String groupName) {
        for(Group g : groups) {
            if(g.getGroupName().equals(groupName)) {
                return true;
            }
        }
        return false;
        
    }

	@Override
	public String toString() {
		return "category [name=" + name + ", groups=" + groups + "]";
	}


	//test:1
	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	
	/*
	 * Formats Category data to be written
	 * Format:
	 * 
	 * @START
	 * @CATEGORY
	 * @NAME="name_of_category"
	 * @GROUP="name_of_group_under_category"
	 * ...
	 * @GROUP="name_of_group_under_category"
	 * @END
	 * 
	 */
	
	//Gets a list of categorys 
	//test:1
	public String getCategoryWriteData() {
		String categoryData = "@START\n" + 
								"@CATEGORY\n" + 
								"@NAME=" + name + "\n" + 
								"@END\n\n";
		
		return categoryData;
	}

	//Compares if a catagory already exists 
	//test:2
	@Override
	public int compareTo(category c) {
		if (c.getName().equals(name)) {
			return 1;
		}
		return 0;
	}
	
	//Checks to see if a category has the same name
	//test:2
	public boolean compareName(String other) {
		if (other.equals(name)) {
			return true;
		}
		else {
			return false;
		}
	}

}
