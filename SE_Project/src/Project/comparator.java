package Project;

import java.util.Comparator;

//test:1
class SortCategoriesByName implements Comparator<category> {

	@Override
	public int compare(category c1, category c2) {

		return c1.getName().toUpperCase().compareTo(c2.getName().toUpperCase());
	}

}

//test:1
class SortGroupsByName implements Comparator<Group> {

	@Override
	public int compare(Group g1, Group g2) {

		return g1.getGroupName().toUpperCase().compareTo(g2.getGroupName().toUpperCase());
	}

}

//test:1
class SortUsersByName implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {

		return u1.getName().toUpperCase().compareTo(u2.getName().toUpperCase());
	}

}

//test:1
class SortUsersByUsername implements Comparator<User> {
	
	@Override
	public int compare(User u1, User u2) {
		
		return u1.getId().toUpperCase().compareTo(u2.getId().toUpperCase());
		
//		return u1.getId().compareTo(u2.getId());
	}
	
}


class SortSuspensionsByUsername implements Comparator<Suspended> {
    @Override
    public int compare(Suspended s1, Suspended s2) {
    	User u1 = s1.getUser();
    	User u2 = s2.getUser();

    	return u1.getId().toUpperCase().compareTo(u2.getId().toUpperCase());
    }
}

class SortPostsByCombinedScore implements Comparator<Post> {
    @Override
    public int compare(Post p1, Post p2) {
        int totalScoreP1 = p1.getTotalScore();
        int totalScoreP2 = p2.getTotalScore();

        // Sort in descending order based on total score
        return Integer.compare(totalScoreP2, totalScoreP1);
    }
}

class SortBannedByUsername implements Comparator<Banned> {
    @Override
    public int compare(Banned b1, Banned b2) {
    	User u1 = b1.getUser();
    	User u2 = b2.getUser();

    	return u1.getId().toUpperCase().compareTo(u2.getId().toUpperCase());
    }
}

//FIXME : Add Unit Tests
class SortPostsByDate implements Comparator<Post> {
	@Override
	public int compare(Post p1, Post p2) {
		return p2.getTime().compareTo(p1.getTime());
	}
}

class SortObjectsByDate implements Comparator<Object> {
	@Override
	public int compare(Object o1, Object o2) {
		Post p2 = (Post) o2;
		Post p1 = (Post) o1;

		return p2.getTime().compareTo(p1.getTime());
	}
}