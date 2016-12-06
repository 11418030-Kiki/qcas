/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas.backEnd;

/**
 *
 * @author aayush
 */
public class User {

    private int userID; //PK
    private String userName; //unique userName
    private String password;
    //private UserType userType; //1 for admin 2 for teacher 3 for students
    private String userType; //1 for admin 2 for teacher 3 for students
    private String firstName;
    private String lastName;
    private String course;

    /**
     *
     * @return
     */
    public int getUserID() {
        return this.userID;
    }

    /**
     *
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getUserType() {
        return this.userType;
    }

    /**
     *
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getCourse() {
        return this.course;
    }

    /**
     *
     * @param course
     * 
     */
    public void setCourse(String course) {
        this.course= course;
    }
   
    /**
     *
     */
    public User() {

    }

    /**
     *
     * @param UserID
     * @param userName
     * @param password
     * @param userType
     * @param firstName
     * @param lastName
     * @param course
     */
    public User(int UserID, String userName, String password, String userType, String firstName, String lastName, String course) {
        this.userID = UserID;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;

    }
}
