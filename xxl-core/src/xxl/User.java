package xxl;

public class User {

    /** Name of the user. */
    private String _name;

    public User(String name) {
        _name = name;
    }

    /**
     * 
     * @return name of the user
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets name of the user.
     * 
     * @param name
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * Compares two users.
     * 
     * @param user
     * @return true if users are the same
     */
    @Override
    public boolean equals(Object user) {
        if (user instanceof User) {
            return this.getName().equals(((User) user).getName());
        }
        return false;
    }

}
