package Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class membership implements Comparable<membership> {
	private User user;
	private Group group;
	private Date registeredDate;

	//Gives a membership, given user, group, resisterdDate
	//test:1
    public membership(User user, Group group, String registeredDate) {
        this.user = user;
        this.group = group;
        
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			this.registeredDate = df.parse(registeredDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
    //Create membership given user and group
	//test:1
	public membership(User user, Group group) {
        this.user = user;
        this.group = group;
		registeredDate = new Date();
	}
	
	//Gets the users
	//test:1
	public User getUser() {
		return user;
	}
	
	//Gets the groups
	//test:1
	public Group getGroup() {
		return group;
	}
	
	//Gets the data 
	//test:1
	public Date getDate() {
		return registeredDate;
	}
	
	//Gets the membership and write the data
	//test:1
	public String getMembershipWriteData() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String regDate = df.format(registeredDate);
		
		String memberData = "@START\n" + 
								"@MEMBERSHIP\n" + 
								"@USER=" + user.getId() + "\n" + 
								"@GROUP=" + group.getGroupName() + "\n" +
								"@REGISTEREDDATE=" + regDate + "\n" + 
								"@END\n\n";
		return memberData;
	}
	
	//test:3
	//Compares to see if you have the same membership 
	@Override
	public int compareTo(membership m) {
		
		if (user.compareTo(m.getUser()) == 1) {
			
			if (group.compareTo(m.getGroup()) == 1) {
				return 1;
			}
		}
		return 0;
		
	}

}
