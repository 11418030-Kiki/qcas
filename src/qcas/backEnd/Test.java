/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas.backEnd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author aayush
 */
public class Test {

    private int userID;
    private int numberOfQuestions;
    private String difficulty;
    private ArrayList<Question> questionList;
    //private ArrayList<String> answerArrayList;
    private String[] answerArrayList;
    private int[] isAnswerCorrect;
    private int correctQuestions;
    private int incorrectQuestions;
    private int unansweredQuestions;
    private Date testDate;
    private int currentQuestionNumber;
    private int score;
    private Double scaledScore;
    private String result;
    private User userData;

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNumberOfQuestions() {
        return this.numberOfQuestions;
    }

    public void setNumberOfQuestions(int noOfQues) {
        this.numberOfQuestions = noOfQues;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<Question> getQuestionList() {
        return this.questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    public String[] getAnswerArrayList() {
        return this.answerArrayList;
    }

    public void setAnswerArrayList(String[] answerArrayList) {
        this.answerArrayList = answerArrayList;
    }

    public int[] getIsAnswerCorrectArray() {
        return this.isAnswerCorrect;
    }

    public void setIsAnswerCorrectArray(int[] isAnswerCorrect) {
        this.isAnswerCorrect = isAnswerCorrect;
    }

    public int getCorrectQuestions() {
        return this.correctQuestions;
    }

    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    public int getIncorrectQuestions() {
        return this.incorrectQuestions;
    }

    public void setIncorrectQuestions(int incorrectQuestions) {
        this.incorrectQuestions = incorrectQuestions;
    }

    public int getUnansweredQuestions() {
        return this.unansweredQuestions;
    }

    public void setUnansweredQuestions(int unansweredQuestions) {
        this.unansweredQuestions = unansweredQuestions;
    }

    public Date getTestDate() {
        return this.testDate;
    }

    public void setTestDate(Date date) {
        this.testDate = date;
    }

    public int getCurrentQuestionNumber() {
        return this.currentQuestionNumber;
    }

    public void setCurrentQuestionNumber(int currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = currentQuestionNumber;
    }

    public Double getScaledScore() {
        return this.scaledScore;
    }

    public void setScaledScore(Double scaledScore) {
        this.scaledScore = scaledScore;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public User getUserData() {
        return this.userData;
    }

    public void setUserData(User user) {
        this.userData = user;
    }

    /**
     *
     */
    public Test() {
    }

    /**
     * constructor
     *
     * @param noOfQuest
     * @param difficulty
     */
    public Test(User userData, int noOfQuest, String difficulty) {

        this.numberOfQuestions = noOfQuest;
        this.difficulty = difficulty;
        isAnswerCorrect = new int[numberOfQuestions];
        this.userData = userData;
        this.userID = userData.getUserID();
    }

    /**
     * randomly compute number of questions for each of the difficulty types
     *
     * @param noOfQues
     * @return
     */
    public int[] getRandomNumberOfQuestionForAllDifficultyTypes(int noOfQues) {
        int[] numberOfQuestion = new int[3];
        if(noOfQues==5)
        {
            numberOfQuestion[0] =2;
            numberOfQuestion[1] =2;
            numberOfQuestion[2] =1;
        }
        else if(noOfQues==10)
        {
            numberOfQuestion[0] =3;
            numberOfQuestion[1] =4;
            numberOfQuestion[2] =3;
        }
        else if(noOfQues==15)
        {
            numberOfQuestion[0] =5;
            numberOfQuestion[1] =6;
            numberOfQuestion[2] =4;
        }
        /*
        Random random = new Random();
        //number of easy question 1 to (n-2) questions
        int randomEasy = random.nextInt(noOfQues - 2) + 1;
        //number of medium 1 to (n-randomEasy-1) questions
        int randomMedium = random.nextInt(noOfQues - randomEasy - 1) + 1;
        //number of medium 1 to (n-randomEasy-randomMedium) questions
        int randomDifficult = random.nextInt(noOfQues - randomEasy - randomMedium) + 1;
        numberOfQuestion[0] = randomEasy;
        numberOfQuestion[1] = randomMedium;
        numberOfQuestion[2] = randomDifficult;
        */
        return numberOfQuestion;
    }

    /**
     * generate a test for the student depending on number of question and
     * difficulty type selected by the student
     *
     * @param noOfQuest
     * @param difficulty
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Question> generateTest(int noOfQuest, String difficulty) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList<Question> questionList = new ArrayList<>();

        DatabaseManager dbManager = new DatabaseManager();
        if (difficulty.equals("Mixed")) {
            //for mixed type get number of question for each difficulty tpes (Easy/Medium/Hard)
            int[] randomNumberOfQuestion = new int[3];
            randomNumberOfQuestion = getRandomNumberOfQuestionForAllDifficultyTypes(noOfQuest);
            questionList = dbManager.generateTest(numberOfQuestions, randomNumberOfQuestion);
        } else {
            //for specif diffulty type, send number of question and difficulty type
            questionList = dbManager.generateTest(numberOfQuestions, difficulty);
        }

        this.questionList = questionList;
        return questionList;
    }

    public void saveTestDetails(Test testObject) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.saveTestDetails(testObject);
    }
}
