package Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class User implements Comparable<User> {

	protected String id;
	protected String name;
	protected Date birthdate;
	protected String city;
	protected String state;
	protected String password;
	protected Date registeredDate;
	protected ArrayList<Voted> votes;

	//test:1
	//Constructor is intended to be used for adding new Users
	public User(String name, String id, String password, String birthdate, String city, String state) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.birthdate = new Date();
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			this.birthdate = df.parse(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.city = city;
		this.state = state;
		registeredDate = new Date();
		this.votes = new ArrayList<>();
	}
	
	//test:1
	//Constructor is intended to be used for adding an Existing User read from the disk
	public User(String name, String id, String password, String birthdate, String city, String state, String registeredDate) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.birthdate = new Date();
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			this.birthdate = df.parse(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.city = city;
		this.state = state;
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			this.registeredDate = df.parse(registeredDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.votes = new ArrayList<>();
	}

	//Gets a list of users
	public User(String id) {
		this.id = id;
	}
	
	//Gets the ids for the user
	//test:1
	public String getId() {
		return id;
	}

	//Gets the password associated to the user
	//test:1
	public String getPassword() {
		return password;
	}

	//Gets the name that belongs to the user
	//test:1
	public String getName() {
		return name;
	}

	//Gets the birthday for the user
	//test:1
	public Date getBirthday(){
		return birthdate;
	}

	//Gets the city a user is from
	//test:1
	public String getCity() {
		return city;
	}

	//Gets the state a users is from
	//test:1
	public String getState() {
		return state;
	}

	//Gets the data a user registered for the account
	//test:1
	public Date getRegisteredDate() {
		return registeredDate;
	}
	
	//Gets a list of votes a users might have recived
	//test:1
	public ArrayList<Voted> getVotedList(){
		return votes;
	}
	
	//Adds a new Voted to the group
	//test:2
	public boolean addVoted(Voted v) {
		boolean isMember = false;
		for (Voted v1 : votes) {
			if (v1.equals(v)) {
				isMember = true;
	            break;
	        }
	   }
	   if (!isMember) {
		   votes.add(v);
	       return true;
	   }
	   return false;
	}
	
	//Removes a Voted to the group
	//test:2
    public boolean removeVoted(Voted v) {
        Iterator<Voted> iterator = votes.iterator();
        boolean isMember = false;

        while (iterator.hasNext()) {
            Voted v1 = iterator.next();
            if (v1.equals(v)) {
                iterator.remove(); // Safely removes the current element from the list
                isMember = true;
            }
        }

        return isMember;
    }
    
    /*
     * Formats User data to be written
     * Format:
     * 
     * @START
	 * @USER
	 * @NAME="name_of_user"
	 * @BIRTHDATE=MM/DD/YYYY
	 * @CITY="city_of_user"
	 * @STATE="state_of_user"
	 * @USERNAME="username_of_user"
	 * @PASSWORD="password_of_user"
	 * @REGISTERED_DATE=MM/DD/YYYY
	 * @END
     *  
     */
    
    //Writes the data for the user
	//test:1
    public String getUserWriteData() {
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	String bday = df.format(birthdate);
    	String regDate = df.format(registeredDate);
    	
    	String userData = "@START\n" + 
    						"@USER\n" + 
    						"@NAME=" + name + "\n" + 
    						"@BIRTHDATE=" + bday + "\n" + 
    						"@CITY=" + city + "\n" + 
    						"@STATE=" + state + "\n" + 
    						"@USERNAME=" + id + "\n" + 
    						"@PASSWORD=" + password + "\n" + 
    						"@REGISTERED_DATE=" + regDate + "\n" + 
    						"@END\n\n";
    	
    	return userData;
    }

    //Test to see if a user with the same id is already there
	//test:2
	@Override
	public int compareTo(User u) {
		if (u.getId().equals(id)) {
			return 1;
		}
		return 0;
	}
	
	//Compares the ids for the users incase we have a identical one
	//test:2
	public boolean compareId(String other) {
		if (other.equals(id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Compares the password to make sure the passwords match when creating a account
	//test:2
	public boolean comparePassword(String other) {
		if (other.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
  
}
