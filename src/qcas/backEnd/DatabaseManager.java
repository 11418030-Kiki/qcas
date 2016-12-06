/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas.backEnd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * DatabaseManager handles database operations, creates connection, inserts and
 * reads rows from database
 *
 * @author aayush
 */
public class DatabaseManager {

    String url;
    String username = "rajeev";
    String password = "rajeev123";
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
        String query = "SELECT * FROM qcas.user where username = '" + userName + "' and password = '" + userPassword + "' and usertype = '" + userType + "'";
        User user = new User();

        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            //access resultset for every record present in the records set
            while (rs.next()) {
                int userID = rs.getInt("userID");
                String firstname = rs.getString("firstNAME");
                String lastName = rs.getString("lastNAME");
                //String userType = rs.getString("userType");
                String course = rs.getString("course");
                user = new User(userID, userName, userPassword, userType, firstname, lastName, course);
            }

            return user;//null if wrong credentials
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            //System.exit(0);
            return user; //null 
        }
    }

    public int[] getDateDetails() throws SQLException {
//String query2 = "SELECT * FROM qcas.test where  YEAR(testdate) = YEAR(dateadd(yy, -1, getdate()))\n AND MONTH(testdate) = MONTH(dateddd(mm, -1, getdate()))";
        int[] arr = new int[3];

//        String query_0 = "SELECT count(*) as rc_0 FROM qcas.test where testdate < dateadd(month, -1, getdate())";
//        String query_1 = "SELECT count(*) as rc_1 FROM qcas.test where testdate < dateadd(month, -3, getdate())";
//        String query_2 = "SELECT count(*) as rc_2 FROM qcas.test where testdate < dateadd(month, -12, getdate())";
        String query_0 = "SELECT count(*) as rc_0 FROM qcas.test";
        String query_1 = "SELECT count(*) as rc_1 FROM qcas.test";
        String query_2 = "SELECT count(*) as rc_2 FROM qcas.test";

        ResultSet rs = null;
        //Connection con = DriverManager.getConnection(url, username, password);
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            for (int i = 0; i < 3; i++) {
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery("query_" + i);
                //access resultset for every record present in the records set

                while (rs.next()) {

                    arr[i] = rs.getInt("rc_" + i);

                }
            }
            return arr;//null if wrong credentials
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            //System.exit(0);
            // return user; //null 
        }
        return arr;
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
                int userID = rs.getInt("userID");
                String password = rs.getString("password");
                String firstname = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String course = rs.getString("course");
                user = new User(userID, userName, password, userType, firstname, lastName, course);

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
                query = "INSERT INTO qcas.questions VALUES (NULL,'" + quest.getQuestionType() + "','" + quest.getDifficulty() + "','" + quest.getQuestion() + "','" + quest.getOptionA();

                //if (quest.getQuestionType().equals("MC") || quest.getQuestionType().equals("MA")) {
                query += "'," + quest.getOptionACorrect() + ",'" + quest.getOptionB() + "'," + quest.getOptionBCorrect() + ",'" + quest.getOptionC() + "'," + quest.getOptionCCorrect() + ",'" + quest.getOptionD() + "'," + quest.getOptionDCorrect() + ")";

                //} else if(quest.getQuestionType().equals("TF") || quest.getQuestionType().equals("FIB")){
                //}
                //query += "')";
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
                questionType = "FIB";
                break;
            case 2:
                questionType = "MA";
                break;
            case 3:
                questionType = "MC";
                break;
            case 4:
                questionType = "TF";
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
            String difficulty = rs.getString("difficultyLevel");
            String question = rs.getString("question");
            String optionA = rs.getString("optionA");
            boolean optionACorrect = rs.getBoolean("optionACorrect");
            String optionB = rs.getString("optionB");
            boolean optionBCorrect = rs.getBoolean("optionBCorrect");
            String optionC = rs.getString("optionC");
            boolean optionCCorrect = rs.getBoolean("optionCCorrect");
            String optionD = rs.getString("optionD");
            boolean optionDCorrect = rs.getBoolean("optionDCorrect");
            StringBuilder answerString = new StringBuilder();
            if (questionType.equals("MC")) {
                if (optionACorrect) {
                    answerString.append(optionA);
                }
                if (optionBCorrect) {
                    answerString.append(optionB);
                }
                if (optionCCorrect) {
                    answerString.append(optionC);
                }
                if (optionDCorrect) {
                    answerString.append(optionD);
                }
            } else if (questionType.equals("MA")) {
                if (optionACorrect) {
                    answerString.append("A").append(",");
                }
                if (optionBCorrect) {
                    answerString.append("B").append(",");
                }
                if (optionCCorrect) {
                    answerString.append("C").append(",");
                }
                if (optionDCorrect) {
                    answerString.append("D").append(",");
                }
                if (answerString.length() > 0) {
                    answerString.setLength(answerString.length() - 1);
                }
            } else if (questionType.equals("TF") || questionType.equals("FIB")) {
                answerString.append(optionA);
            }
            questionObject = new Question(questionID, questionType, difficulty, question, optionA, optionACorrect, optionB, optionBCorrect, optionC, optionCCorrect, optionD, optionDCorrect, answerString.toString());
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
    /*  public ArrayList<Question> generateTest(int numberOfQuest, String testDifficultyLevel) throws SQLException {
        //query to be executed, finds sailor details with1 max salary in every position
        // String query = "Select * from question where difficulty = '" + testDifficultyLevel + "'";
        String questionType;
        ArrayList<Question> questionList = new ArrayList<>();
        if (testDifficultyLevel.equals("Easy")) {
            testDifficultyLevel = "E";
        } else if (testDifficultyLevel.equals("Medium")) {
            testDifficultyLevel = "M";
        } else {
            testDifficultyLevel = "H";
        }
        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            // rs = stmt.executeQuery(query);
            int i = 0;
            while (i < numberOfQuest) {
                questionType = getRandomQuestionType();
                query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = '" + testDifficultyLevel + "' and questionID >= (SELECT FLOOR( MAX(questionID) * RAND()) FROM qcas.questions ) ORDER BY questionID LIMIT 1";
                // query = "Select * from qcas.questions where questionType = '" + questionType + "' and difficultyLevel = '" + testDifficultyLevel + "'";
                Question questionObject = getQuestionFromDB(con, query, questionType);
                if (!questionList.contains(questionObject)) {
                    questionList.add(questionObject);
                    i++;
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
     */
    //issue--looping unnecesarily
    ///*
    public ArrayList<Question> generateTest(int numberOfQuest, String testDifficultyLevel) throws SQLException {
        //query to be executed, finds sailor details with1 max salary in every position
        if (testDifficultyLevel.equals("Easy")) {
            testDifficultyLevel = "E";
        } else if (testDifficultyLevel.equals("Medium")) {
            testDifficultyLevel = "M";
        } else {
            testDifficultyLevel = "H";
        }
        //  String query = "Select * from qcas.questions where difficultyLevel = '" + testDifficultyLevel + "'";
        String questionType;
        ArrayList<Question> questionList = new ArrayList<>();
        HashMap<Integer, Question> uniqueQuesMap = new HashMap();
        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            // rs = stmt.executeQuery(query);
            while (uniqueQuesMap.size() < numberOfQuest) {
                questionType = getRandomQuestionType();
                //query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = '" + testDifficultyLevel + "' and questionID >= (SELECT FLOOR( MAX(questionID) * RAND()) FROM qcas.questions ) ORDER BY questionID LIMIT 1";
                query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = '" + testDifficultyLevel + "' order by rand() LIMIT 1";
                Question questionObject = getQuestionFromDB(con, query, questionType);
                uniqueQuesMap.put(questionObject.getQuestionID(), questionObject);
            }
            for (Map.Entry<Integer, Question> entry : uniqueQuesMap.entrySet()) {
                questionList.add(entry.getValue());
            }
            Collections.shuffle(questionList);
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            //System.exit(0);
        }
        return questionList;
    }

    //*/
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
        HashMap<Integer, Question> uniqueQuesMap = new HashMap();
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            int i = 0;
            while (i < numberOfQuest) {
                //for (int i = 0; i < numberOfQuest; i++) {
                while (numberOfQuestOfAllDificultyType[0] > 0) {
                    questionType = getRandomQuestionType();
                    //     query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = 'E' and questionID >= (SELECT FLOOR( MAX(questionID) * RAND()) FROM qcas.questions ) ORDER BY questionID LIMIT 1";
                    query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = 'E' order by rand() LIMIT 1";

                    // query = "Select * from questions where questionType = '" + questionType + "' and difficultyLevel = 'E'";
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
                    // query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = 'M' and questionID >= (SELECT FLOOR( MAX(questionID) * RAND()) FROM qcas.questions ) ORDER BY questionID LIMIT 1";
                    //query = "Select * from questions where questionType = '" + questionType + "' and difficultyLevel = 'M'";
                    query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = 'M' order by rand() LIMIT 1";

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
                    //query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = 'H' and questionID >= (SELECT FLOOR( MAX(questionID) * RAND()) FROM qcas.questions ) ORDER BY questionID LIMIT 1";
                    //query = "Select * from questions where questionType = '" + questionType + "' and difficultyLevel = 'H'";
                    query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = 'H' order by rand() LIMIT 1";

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
            query = "INSERT INTO qcas.test values (" + null + ", '" + testObject.getTestDate() + "'," + testObject.getUserID() +","+ testObject.getNumberOfQuestions() + ",'" + testObject.getDifficulty() + "'," + testObject.getCorrectQuestions() +","+ testObject.getIncorrectQuestions()+"," + testObject.getUnansweredQuestions()+"," + testObject.getScore() + ","+testObject.getScaledScore() + ",'" + testObject.getResult() + "')";

//            query = "INSERT INTO TEST (testID," + "29 - November - 2016" + "," + testObject.getUserID() + "," + testObject.getNumberOfQuestions() + ",'" + testObject.getDifficulty() + "'," + testObject.getCorrectQuestions() + "," + testObject.getIncorrectQuestions() + "," + testObject.getUnansweredQuestions() + "," + testObject.getScore() + "," + testObject.getScaledScore() + ",'" + testObject.getResult() + "')";
            stmt.executeUpdate(query);
           // int a = 1;
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
