/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas.backEnd;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DatabaseManager handles database operations, creates connection, inserts and
 * reads rows from database
 *
 * @author aayush
 */
public class DatabaseManager {

    String url;
    String username = "scott";
    String password = "tiger123";
    String query;
    Connection conn = null;

    /**
     * constructor for db manager class
     */
    public DatabaseManager() throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        //url for database
        // url = "jdbc:derby://localhost:1527/EmployeeDB2;create=true;";
        //username for database
        //username = "app";
        //password for database
        //password = "app";
        url = "jdbc:mysql://qcas.csnb2ea61dmx.us-west-2.rds.amazonaws.com:3306/qcas";
        //jdbc:mysql://cmuqcas.csnb2ea61dmx.us-west-2.rds.amazonaws.com:3306/qcas?zeroDateTimeBehavior=convertToNull
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection(url, username, password);
    }

    /**
     * method to check user credentials and to login
     *
     * @param userName
     * @param userPassword
     * @param userType
     * @return
     */
    public User login(String userName, String userPassword, String userType) {
        /* Connection con = DriverManager.getConnection(conn, username, password);
        if (!con.isClosed()) {
            con.close();
        }
         */
//username and password will be varchar in database usertype is int
        String query = "SELECT * FROM user where username = '" + userName + "' and password = '" + userPassword + "' and usertype = '" + userType + "'";
        //int userType = 0;
        User user = new User();

        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            //access resultset for every record present in the records set
            while (rs.next()) {
                int userID = rs.getInt("USERID");
                //String password = rs.getString("PASSWORD");
                // userType = rs.getInt("USERTYPE");
                String firstname = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                //char gender = (rs.getString("Gender")).charAt(0);
                user = new User(userID, userName, userPassword, userType, firstname, lastName);
            }
            return user;//null if wrong credentials
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            //System.exit(0);
            return user; //null 
        }
    }

    /**
     * method to get user details from database
     *
     * @param userType
     * @param userName
     * @return
     */
    public User getUserDetails(String userType, String userName) {
        //query to be executed, finds sailor details with1 max salary in every position
        String query = "SELECT * FROM USER where username is '" + userName + "'";
        User user = new User();
        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            //access resultset for every record present in the records set
            while (rs.next()) {
                int userID = rs.getInt("USERID");
                String password = rs.getString("PASSWORD");
                String firstname = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                char gender = (rs.getString("Gender")).charAt(0);
                user = new User(userID, userName, password, userType, firstname, lastName);

            }
            return user;
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            return user;
            //System.exit(0);
        }
    }

    /**
     * inserts row into database
     *
     * @param arrQuestion
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertQuestionInDatabase(ArrayList<Question> arrQuestion) throws SQLException, ClassNotFoundException {
        // Create a simple query
        String query;

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            for (Question quest : arrQuestion) {
                // Add a record into the QUIZ table of the database
                query = "INSERT INTO QUIZ VALUES ('" + quest.getQuestionType() + "','" + quest.getDifficulty() + "','" + quest.getQuestion() + "','" + quest.getOptionA();

                if (quest.getQuestionType().equals("MC") || quest.getQuestionType().equals("MA")) {
                    query += "'," + quest.getOptionACorrect() + ",'" + quest.getOptionB() + "'," + quest.getOptionBCorrect() + ",'" + quest.getOptionC() + "'," + quest.getOptionCCorrect() + ",'" + quest.getOptionD() + "'," + quest.getOptionDCorrect() + ")";

                } else //if(quest.getQuestionType().equals("TF") || quest.getQuestionType().equals("FIB"))
                {
                    query += "')";
                }

                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }
    }

    /**
     *
     * @return
     */
    public String getRandomQuestionType() {
        String questionType = "MC";//default mcq
        Random random = new Random();
        //1 for MC, 2 for MA, 3 for TF, 4 for FIB
        int questionTypeValue = random.nextInt(4) + 1;
        switch (questionTypeValue) {
            case 1:
                questionType = "MC";
                break;
            case 2:
                questionType = "MA";
                break;
            case 3:
                questionType = "TF";
                break;
            case 4:
                questionType = "FIB";
                break;
            default:
                break;
        }

        return questionType;
    }

    /**
     *
     * @param con
     * @param query
     * @param questionType
     * @return
     * @throws SQLException
     */
    public Question getQuestionFromDB(Connection con, String query, String questionType) throws SQLException {
        ResultSet rs = null;
        Question questionObject = null;
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        //access resultset for every record present in the records set
        while (rs.next()) {
            int questionID = rs.getInt("questionID");
            String difficulty = rs.getString("difficulty");
            String question = rs.getString("question");
            String optionA = rs.getString("optionA");
            boolean optionACorrect = rs.getBoolean("optionACorrect");
            String optionB = rs.getString("optionB");
            boolean optionBCorrect = rs.getBoolean("optionBCorrect");
            String optionC = rs.getString("optionC");
            boolean optionCCorrect = rs.getBoolean("optionCCorrect");
            String optionD = rs.getString("optionD");
            boolean optionDCorrect = rs.getBoolean("optionDCorrect");

            questionObject = new Question(questionType, difficulty, question, optionA, optionACorrect, optionB, optionBCorrect, optionC, optionCCorrect, optionD, optionDCorrect, "");
        }
        return questionObject;
    }

    /**
     * for specific difficulty type test
     *
     * @param numberOfQuest
     * @param numberOfQuestOfAllDificultyType
     * @param testDifficultyLevel
     * @return
     * @throws SQLException
     */
    public ArrayList<Question> generateTest(int numberOfQuest, String testDifficultyLevel) throws SQLException {
        //query to be executed, finds sailor details with1 max salary in every position
        String query = "Select * from question where difficulty = '" + testDifficultyLevel + "'";
        String questionType;
        ArrayList<Question> questionList = new ArrayList<>();
        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            for (int i = 0; i < numberOfQuest; i++) {
                questionType = getRandomQuestionType();
                query = "Select * from Question where questionType = '" + questionType + "' and difficulty = '" + testDifficultyLevel + "'";
                Question questionObject = getQuestionFromDB(con, query, questionType);
                questionList.add(questionObject);
            }
            Collections.shuffle(questionList);
            return questionList;
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            return questionList;
            //System.exit(0);
        }
    }

    /**
     * for mixed difficulty test
     *
     * @param numberOfQuest
     * @param numberOfQuestOfAllDificultyType
     * @return
     * @throws SQLException
     */
    public ArrayList<Question> generateTest(int numberOfQuest, int[] numberOfQuestOfAllDificultyType) throws SQLException {
        String query;
        String questionType;
        ArrayList<Question> questionList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            int i = 0;
            while (i < numberOfQuest) {
                //for (int i = 0; i < numberOfQuest; i++) {
                while (numberOfQuestOfAllDificultyType[0] > 0) {
                    questionType = getRandomQuestionType();
                    query = "Select * from Question where questionType = '" + questionType + "' and difficulty = 'E'";
                    Question questionObject = getQuestionFromDB(con, query, questionType);
                    //add this question to the questionlist only if it is not already present
                    if (!questionList.contains(questionObject)) {
                        questionList.add(questionObject);
                        numberOfQuestOfAllDificultyType[0]--;
                        i++;
                    }
                }
                while (numberOfQuestOfAllDificultyType[1] > 0) {
                    questionType = getRandomQuestionType();
                    query = "Select * from Question where questionType = '" + questionType + "' and difficulty = 'M'";
                    Question questionObject = getQuestionFromDB(con, query, questionType);
                    //add this question to the questionlist only if it is not already present
                    if (!questionList.contains(questionObject)) {
                        questionList.add(questionObject);
                        numberOfQuestOfAllDificultyType[1]--;
                        i++;
                    }
                }
                while (numberOfQuestOfAllDificultyType[2] > 0) {
                    questionType = getRandomQuestionType();
                    query = "Select * from Question where questionType = '" + questionType + "' and difficulty = 'H'";
                    Question questionObject = getQuestionFromDB(con, query, questionType);
                    //add this question to the questionlist only if it is not already present
                    if (!questionList.contains(questionObject)) {
                        questionList.add(questionObject);
                        numberOfQuestOfAllDificultyType[2]--;
                        i++;
                    }
                }
            }
            Collections.shuffle(questionList);
            return questionList;
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            return questionList;
            //System.exit(0);
        }
    }

    /**
     * for mixed difficulty test
     *
     * @param numberOfQuest
     * @param numberOfQuestOfAllDificultyType
     * @return
     * @throws SQLException
     */
    public void saveTestDetails(Test testObject) throws SQLException {
        String query;

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            query = "INSERT INTO TEST (testID," + "29 - November - 2016" + "," + testObject.getUserID() + "," + testObject.getNumberOfQuestions() + ",'" + testObject.getDifficulty() + "'," + testObject.getCorrectQuestions() + "," + testObject.getIncorrectQuestions() + "," + testObject.getUnansweredQuestions() + "," + testObject.getScore() + "," + testObject.getScaledScore() + ",'" + testObject.getResult() + "')";

            stmt.executeUpdate(query);
            int a = 1;
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            // System.exit(0);
        }

    }

    /**
     * method to getNummberOfTestsTakenOverAPeriod (last month/quarter)
     *
     * @param userType
     * @param userName
     * @return
     */
    /*
    public User getNummberOfTestsTakenOverAPeriod(int numberOfMonth) {
        //query to be executed, finds sailor details with1 max salary in every position
        //String query = "SELECT * FROM USER where username is '" + userName + "'";
        String query = "SELECT COUNT(*) AS rowcount FROM TEST where month(testDate) = month(getdate()) - "+ numberOfMonth;

        User user = new User();
        ResultSet rs = null;
        int numberOfRecords;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            //access resultset for every record present in the records set
            while (rs.next()) {
                int numberOfRecords = rs.getInt("rowcount");
                
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            return user;
            //System.exit(0);
        }
    }

     */
}
