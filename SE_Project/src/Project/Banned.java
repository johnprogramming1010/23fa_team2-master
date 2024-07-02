package Project;

public class Banned implements Comparable<Banned> {
	private User user;
	private Group group;

	//Adding new banned users
	//test:1
    public Banned(User user, Group group) {
        this.user = user;
        this.group = group;
	}
   //Gets users that are banned
	//test:1
	public User getUser() {
		return user;
	}
	
	//Gets users that are banned in group
	//test:1
	public Group getGroup() {
		return group;
	}

	//Gets users that are banned from a certin group
	//test:1
	public String getBannedWriteData() {
		
		String bannedData = "@START\n" + 
								"@BANNED\n" + 
								"@USER=" + user.getId() + "\n" + 
								"@GROUP=" + group.getGroupName() + "\n" +
								"@END\n\n";
		return bannedData;
	}

	//Compares if a user has already been banned 
	//test:3
	public int compareTo(Banned b) {
		
		if (user.compareTo(b.getUser()) == 1) {
			
			if (group.compareTo(b.getGroup()) == 1) {
				return 1;
			}
		}
		return 0;
		
	}

}
