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
    String username = "scott";
    String password = "tiger123";
    String query;
    Connection conn = null;

    /**
     * constructor for db manager class
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.sql.SQLException
     * @throws java.lang.IllegalAccessException
     */
    public DatabaseManager() throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        //url for database
        url = "jdbc:mysql://cmuqcas.csnb2ea61dmx.us-west-2.rds.amazonaws.com:3306/qcas";
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
        //query to get user data
        String query3 = "SELECT * FROM qcas.user where username = '" + userName + "' and password = '" + userPassword + "' and usertype = '" + userType + "'";
        User user = new User();

        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query3);
            //access resultset for every record present in the records set
            while (rs.next()) {
                int userID = rs.getInt("userID");
                String firstname = rs.getString("firstNAME");
                String lastName = rs.getString("lastNAME");
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

    /**
     * get test date Details
     *
     * @return @throws SQLException
     */
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
     * get instructor graph
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public double[] getInstructorGraph() throws SQLException, ClassNotFoundException {
        // Create a simple query
        ArrayList<String> arr = new ArrayList<>();
        //query to get number of rows from test table
        String query0 = "SELECT count(*) as rc_0 FROM qcas.test";
        //query to get avg of score from table
        String query1 = "Select sum(scaledScore)/count(*) as rc_1 FROM qcas.test ";
        //query to get avg score from table bassed for Hard
        String query2 = "Select sum(scaledScore)/count(scaledScore) as rc_2 FROM qcas.test where difficultyLevel = \"Hard\"";
        arr.add(query0);
        arr.add(query1);
        arr.add(query2);
        double result[] = new double[3];
        double result1 = 0.00;
        double result2 = 0.00;
        double result3 = 0.00;
        try {
            ResultSet rs = null;
            Statement stmt0 = conn.createStatement();
            rs = stmt0.executeQuery(query0);
            int count = 0;
            while (rs.next()) {
                result1 = rs.getDouble("rc_0");
                System.out.println("Test Count -->" + rs.getDouble("rc_0"));
            }
            Statement stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query1);
            while (rs.next()) {
                result2 = rs.getDouble("rc_1");
                System.out.println("Test Count -->" + rs.getDouble("rc_1"));
            }
            Statement stmt2 = conn.createStatement();
            rs = stmt2.executeQuery(query2);
            while (rs.next()) {
                result3 = rs.getDouble("rc_2");
                System.out.println("Test Count -->" + rs.getDouble("rc_2"));
            }
            result[0] = result1;
            result[1] = result2;
            result[2] = result3;

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }
        return result;
    }

    /**
     *
     * @param period
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public double[] getStudentNumberGraph(String period) throws SQLException, ClassNotFoundException {
        // Create a simple query
        String query0 = "SELECT count(*) as count FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL -1 MONTH) ";
        String query1 = "SELECT count(*) as count FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL -3 MONTH) ";
        String query2 = "SELECT count(*) as count FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL -12 MONTH)";

        double result[] = new double[3];
        double result1 = 0.00;
        double result2 = 0.00;
        double result3 = 0.00;
        try {

            ResultSet rs = null;

            if (period.equals("LastMonth")) {
                Statement stmt0 = conn.createStatement();
                rs = stmt0.executeQuery(query0);
                while (rs.next()) {
                    result1 = rs.getDouble("count");
                    System.out.println("Get Student Graph1 -->" + rs.getDouble("count"));
                }
            } else if (period.equals("LastQuarter")) {
                Statement stmt0 = conn.createStatement();
                rs = stmt0.executeQuery(query1);
                while (rs.next()) {
                    result1 = rs.getDouble("count");
                    System.out.println("Get Student Graph2 -->" + rs.getDouble("count"));
                }
            } else if (period.equals("LastYear")) {
                Statement stmt0 = conn.createStatement();
                rs = stmt0.executeQuery(query2);
                while (rs.next()) {
                    result1 = rs.getDouble("count");
                    System.out.println("Get Student Graph3 -->" + rs.getDouble("count"));
                }
            }
            result[0] = result1;

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }
        return result;
    }
    
    public double[] getStudentAvgGraph(String period) throws SQLException, ClassNotFoundException {
        // Create a simple query
        String query0 = "SELECT avg(scaledScore) as ave FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL -1 MONTH) ";
        String query1 = "SELECT avg(scaledScore) as ave FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL -3 MONTH) ";
        String query2 = "SELECT avg(scaledScore) as ave FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL -12 MONTH)";

        double result[] = new double[3];
        double result1 = 0.00;

        try {

            ResultSet rs = null;

            if (period.equals("LastMonth")) {
                Statement stmt0 = conn.createStatement();
                rs = stmt0.executeQuery(query0);
                while (rs.next()) {
                    result1 = rs.getDouble("ave");
                    System.out.println("Get Avg Graph1 -->" + rs.getDouble("ave"));
                }
            } else if (period.equals("LastQuarter")) {
                Statement stmt0 = conn.createStatement();
                rs = stmt0.executeQuery(query1);
                while (rs.next()) {
                    result1 = rs.getDouble("ave");
                    System.out.println("Get Avg Graph2 -->" + rs.getDouble("ave"));
                }
            } else if (period.equals("LastYear")) {
                Statement stmt0 = conn.createStatement();
                rs = stmt0.executeQuery(query2);
                while (rs.next()) {
                    result1 = rs.getDouble("ave");
                    System.out.println("Get Avg Graph3 -->" + rs.getDouble("ave"));
                }
            }
            result[0] = result1;

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }
        return result;
    }
    
        public double[] getStudentDifficultyGraph(String period) throws SQLException, ClassNotFoundException {
        // Create a simple query
        int periodValue=0;
        if(period.equals("LastMonth")){
            periodValue=-1;
        }else if(period.equals("LastQuarter")){
            periodValue=-3;
        }else if(period.equals("LastYear")){
            periodValue=-12;
        }
        String query0 = "Select avg(scaledScore) as ave FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL "+periodValue+" MONTH) and difficultyLevel = \"Easy\"";
        String query1 = "Select avg(scaledScore) as ave FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL "+periodValue+" MONTH) and difficultyLevel = \"Medium\"";
        String query2 = "Select avg(scaledScore) as ave FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL "+periodValue+" MONTH) and difficultyLevel = \"Hard\"";
        String query3 = "Select avg(scaledScore) as ave FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL "+periodValue+" MONTH) and difficultyLevel = \"Mixed\"";

        double result[] = new double[4];
        double result1 = 0.00;
        double result2 = 0.00;
        double result3 = 0.00;
        double result4 = 0.00;
        
        try {

            ResultSet rs = null;
            
                Statement stmt0 = conn.createStatement();
                rs = stmt0.executeQuery(query0);
                while (rs.next()) {
                    result1 = rs.getDouble("ave");
                    System.out.println("Get Difficulty Easy -->" + rs.getDouble("ave"));
                }
                
                Statement stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query1);
                while (rs.next()) {
                    result2 = rs.getDouble("ave");
                    System.out.println("Get Difficulty Medium -->" + rs.getDouble("ave"));
                }
                
                Statement stmt2 = conn.createStatement();
                rs = stmt2.executeQuery(query2);
                while (rs.next()) {
                    result3 = rs.getDouble("ave");
                    System.out.println("Get Difficulty Hard -->" + rs.getDouble("ave"));
                }
                
                Statement stmt3 = conn.createStatement();
                rs = stmt3.executeQuery(query3);
                while (rs.next()) {
                    result4 = rs.getDouble("ave");
                    System.out.println("Get Difficulty Mixed -->" + rs.getDouble("ave"));
                }
             
            result[0] = result1;
            result[1] = result2;
            result[2] = result3;
            result[3] = result4;

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }
        return result;
    }
        
         public double[] getPassFailPiechart(String period) throws SQLException, ClassNotFoundException {
        // Create a simple query
        int periodValue = 0;
        if (period.equals("LastMonth")) {
            periodValue = -1;
        } else if (period.equals("LastQuarter")) {
            periodValue = -3;
        } else if (period.equals("LastYear")) {
            periodValue = -12;
        }
        String query0 = "Select count(*) as count FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL " + periodValue + " MONTH) and Result = \"Pass\"";
        String query1 = "Select count(*) as count FROM qcas.test where testdate > DATE_ADD(NOW(),INTERVAL " + periodValue + " MONTH) and Result = \"Fail\"";

        double result[] = new double[4];
        double result1 = 0.00;
        double result2 = 0.00;

        try {

            ResultSet rs = null;
            Statement stmt0 = conn.createStatement();
            rs = stmt0.executeQuery(query0);
            while (rs.next()) {
                result1 = rs.getDouble("count");
                System.out.println("Get Students Passed -->" + rs.getDouble("count"));
            }

            Statement stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query1);
            while (rs.next()) {
                result2 = rs.getDouble("count");
                System.out.println("Get Students Failed -->" + rs.getDouble("count"));
            }

            result[0] = result1;
            result[1] = result2;

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }
        return result;
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
                String course = (rs.getString("course"));
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
     * get random question type
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
                //for FIB test
                questionType = "FIB";
                break;
            case 2:
                //for multi correct type
                questionType = "MA";
                break;
            case 3:
                //for mcq test type
                questionType = "MC";
                break;
            case 4:
                //for TF type
                questionType = "TF";
                break;
            default:
                break;
        }

        return questionType;
    }

    /**
     * get question from database
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
            //create answer string containing("answerOption" )
            if (questionType.equals("MC")) {
                //for MC type
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
                //for MA type
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
                //for FIB and TF type
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
    /* for specific difficulty type test
     *
     * @param numberOfQuest
     * @param testDifficultyLevel
     * @return
     * @throws SQLException
     */
    public ArrayList<Question> generateTest(int numberOfQuest, String testDifficultyLevel) throws SQLException {
        //compare difficulty tpe and sets it accordingly
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

                //query to get question from db randomly
                query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = '" + testDifficultyLevel + "' order by rand() LIMIT 1";
                Question questionObject = getQuestionFromDB(con, query, questionType);
                //map to check whether the question is unique or not                
                uniqueQuesMap.put(questionObject.getQuestionID(), questionObject);
            }
            for (Map.Entry<Integer, Question> entry : uniqueQuesMap.entrySet()) {
                questionList.add(entry.getValue());
            }
            //shuffles the question
            Collections.shuffle(questionList);
            String qType = questionList.get(questionList.size() - 1).getQuestionType();
            while (qType.equalsIgnoreCase("TF")) {
                if (qType.equalsIgnoreCase("TF")) {
                    Collections.shuffle(questionList);
                }
                qType = questionList.get(questionList.size() - 1).getQuestionType();
            }

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            //System.exit(0);
        }
        return questionList;
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
        HashMap<Integer, Question> uniqueQuesMap = new HashMap();
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            int i = 0;
            while (i < numberOfQuest) {
                //for (int i = 0; i < numberOfQuest; i++) {
                while (numberOfQuestOfAllDificultyType[0] > 0) {
                    //get easy type questions
                    //get random next question type
                    questionType = getRandomQuestionType();
                    //     query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = 'E' and questionID >= (SELECT FLOOR( MAX(questionID) * RAND()) FROM qcas.questions ) ORDER BY questionID LIMIT 1";
                    query = "SELECT * FROM qcas.questions WHERE questionType = '" + questionType + "' and difficultyLevel = 'E' order by rand() LIMIT 1";

                    Question questionObject = getQuestionFromDB(con, query, questionType);
                    //add this question to the questionlist only if it is not already present
                    if (!questionList.contains(questionObject)) {
                        questionList.add(questionObject);
                        numberOfQuestOfAllDificultyType[0]--;
                        i++;
                    }
                }
                while (numberOfQuestOfAllDificultyType[1] > 0) {
                    //get medium type questions
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
                    //get difficult type questions
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
            //sort the question list
            Collections.shuffle(questionList);
            String qType = questionList.get(questionList.size() - 1).getQuestionType();
            while (qType.equalsIgnoreCase("TF")) {
                if (qType.equalsIgnoreCase("TF")) {
                    Collections.shuffle(questionList);
                }
                qType = questionList.get(questionList.size() - 1).getQuestionType();
            }
            return questionList;
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            return questionList;
            //System.exit(0);
        }
    }

    /**
     * save test details in the database
     *
     * @param testObject
     * @param numberOfQuest
     * @return
     * @throws SQLException
     */
    public void saveTestDetails(Test testObject) throws SQLException {
        String query;

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            //saves test details
            query = "INSERT INTO qcas.test values (" + null + ", '" + testObject.getTestDate() + "'," + testObject.getUserID() + "," + testObject.getNumberOfQuestions() + ",'" + testObject.getDifficulty() + "'," + testObject.getCorrectQuestions() + "," + testObject.getIncorrectQuestions() + "," + testObject.getUnansweredQuestions() + "," + testObject.getScore() + "," + testObject.getScaledScore() + ",'" + testObject.getResult() + "')";

//            query = "INSERT INTO TEST (testID," + "29 - November - 2016" + "," + testObject.getUserID() + "," + testObject.getNumberOfQuestions() + ",'" + testObject.getDifficulty() + "'," + testObject.getCorrectQuestions() + "," + testObject.getIncorrectQuestions() + "," + testObject.getUnansweredQuestions() + "," + testObject.getScore() + "," + testObject.getScaledScore() + ",'" + testObject.getResult() + "')";
            stmt.executeUpdate(query);
            // int a = 1;
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            // System.exit(0);
        }

    }

    /**
     * save user details
     *
     * @param userObject
     * @throws SQLException
     */
    public void saveUserDetails(User userObject) throws SQLException {
        String query2;

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            //query to save user details
            query2 = "INSERT INTO user values(" + userObject.getUserID() + ",'" + userObject.getUserName() + "','" + userObject.getPassword() + "','" + userObject.getUserType() + "','" + userObject.getFirstName() + "','" + userObject.getLastName() + "','" + userObject.getCourse() + "')";

            stmt.executeUpdate(query2);
            int a = 1;
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            // System.exit(0);
        }

    }
}
