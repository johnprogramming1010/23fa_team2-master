package Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Suspended implements Comparable<Suspended> {
	private User user;
	private Group group;
	private Date suspensionDate;
	private Date expiredSuspensionDate;

	//test:1
    public Suspended(User user, Group group, String suspensionDate, String expiredSuspensionDate) {
        this.user = user;
        this.group = group;
        
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
			this.suspensionDate = df.parse(suspensionDate);
			this.expiredSuspensionDate = df.parse(expiredSuspensionDate);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	//test:1
	public Suspended(User user, Group group) {
        this.user = user;
        this.group = group;
        suspensionDate = new Date();
		expiredSuspensionDate = new Date();
        // Adding 5 minutes to the expiredSuspensionDate
        Calendar cal = Calendar.getInstance();
        cal.setTime(expiredSuspensionDate);
        cal.add(Calendar.MINUTE, 5);

        expiredSuspensionDate = cal.getTime();
	}
	
	//Gets the users that are suspended
	//test:1
	public User getUser() {
		return user;
	}
	
	//Gets the group that the users are suspended from
	//test:1
	public Group getGroup() {
		return group;
	}
	
	//Gets the date of when the user was suspended
	//test:1
	public Date getDate() {
		return suspensionDate;
	}
	
	//Gets the date of when the users is no longer suspended
	//test:1
	public Date getExpiredDate() {
		return expiredSuspensionDate;
	}
	
	//Gets the suspended data and writes it on the the suspendedData
	//test:1
	public String getSuspendedWriteData() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String susDate = df.format(suspensionDate);
		String expDate = df.format(expiredSuspensionDate);
		
		String suspendedData = "@START\n" + 
								"@SUSPENDED\n" + 
								"@USER=" + user.getId() + "\n" + 
								"@GROUP=" + group.getGroupName() + "\n" +
								"@SUSPENDEDDATE=" + susDate + "\n" + 
								"@EXPIREDDATE=" + expDate + "\n" + 
								"@END\n\n";
		return suspendedData;
	}
	
	//Compares to see if a users has already been suspended in a group
	//test:3
	public int compareTo(Suspended s) {
		
		if (user.compareTo(s.getUser()) == 1) {
			
			if (group.compareTo(s.getGroup()) == 1) {
				return 1;
			}
		}
		return 0;
		
	}

}