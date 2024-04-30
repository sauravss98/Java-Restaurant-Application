package User;

/**
 * Class to create new instance of User
 * @author Saurav
 */
public class User {
    private  int userId;
    private String email;
    private String password;
    private  String firstName;
    private  String lastName;
    private String userType;
    private  boolean isLoggedIn = false;

    /**
     * The function returns the details of the user
     * @return Returns a string
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    /**
     * Function to get the full name of the user
     * @return The string with full name is returned
     */
    public String getFullName(){
        return firstName+" "+lastName;
    }

    /**
     * Function to get the description of the staff
     * @return Returns the string
     */
    public String getStaffDescription(){
        return "ID: "+userId+" Name: "+getFullName()+"Staff Type: "+userType;
    }

    /**
     * Constructor to create new instance of the class
     * @param userID The user id is passed as integer
     * @param email The email is passed as string
     * @param firstName The first name is passed as string
     * @param lastName The last name is passed as string
     * @param userType The user type is passed as string
     * @param isLoggedIn The log in status is passed as boolean
     */
    public User(int userID,String email, String firstName, String lastName,String userType,boolean isLoggedIn) {
        this.userId = userID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.isLoggedIn = false;
    }

    /**
     * Function to get the log in status
     * @return Returns the log in status as boolean
     */
    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Function to set the logged in status
     * @param loggedIn The boolean status is passed
     */
    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    /**
     * Function to get the user id
     * @return The user id is returned
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Function to set the user id
     * @param userId The user id is passed
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Function to get the first name
     * @return The first name is returned
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Function yo get the last name
     * @return The last name is returned
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Function to set the first name
     * @param firstName The first name is passed
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Function to set the last name
     * @param lastName The last name is passed
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Function to set the password
     * @return The password is passed
     * @deprecated
     */
    public String getPassword() {
        return password;
    }

    /**
     * Function to get the email is passed
     * @return The email is returned as string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Function to set the email
     * @param email The email is passed
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Function to set the password
     * @param password The password is passed
     * @deprecated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Function to get user type
     * @return The user type is returned as String
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Function to set the user type
     * @param userType The user type is passed as string
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
}
