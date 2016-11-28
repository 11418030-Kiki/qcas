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
    private ArrayList<String> answerArrayList;
    private int[] isAnswerCorrect;
    private int correctQuestions;
    private int incorrectQuestions;
    private int unansweredQuestions;
    private Date testDate;
    
    public int getUserID()
    {
        return this.userID;
    }
    public int getNumberOfQuestions()
    {
        return this.numberOfQuestions;
    }
    public String getDifficulty()
    {
        return this.difficulty;
    }
    public int getCorrectQuestions()
    {
        return this.correctQuestions;
    }
    public int getIncorrectQuestions()
    {
        return this.incorrectQuestions;
    }
    public int getUnansweredQuestions()
    {
        return this.unansweredQuestions;
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
    public Test(int userID, int noOfQuest, String difficulty) {
        this.userID = userID;
        this.numberOfQuestions = noOfQuest;
        this.difficulty = difficulty;
        isAnswerCorrect = new int[numberOfQuestions];
    }

    /**
     * randomly compute number of questions for each of the difficulty types
     *
     * @param noOfQues
     * @return
     */
    public int[] getRandomNumberOfQuestionForAllDifficultyTypes(int noOfQues) {
        int[] numberOfQuestion = new int[3];
        Random random = new Random();
        //number of easy question 1 to (n-2) questions
        int randomEasy = random.nextInt(noOfQues - 1) + 1;
        //number of medium 1 to (n-randomEasy-1) questions
        int randomMedium = random.nextInt(noOfQues - randomEasy) + 1;
        //number of medium 1 to (n-randomEasy-randomMedium) questions
        int randomDifficult = random.nextInt(noOfQues - randomEasy - randomMedium) + 1;
        numberOfQuestion[0] = randomEasy;
        numberOfQuestion[1] = randomMedium;
        numberOfQuestion[2] = randomDifficult;
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
    public ArrayList<Question> generateTest(int userID, int noOfQuest, String difficulty) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
    
    

}
