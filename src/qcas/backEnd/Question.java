/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas.backEnd;

/**
 * question class
 * @author aayush
 */
public class Question {

    
    private Integer questionID;
    private String questionType;
    private String difficulty;
    private String question;
    private String optionA;
    private boolean optionACorrect;
    private String optionB;
    private boolean optionBCorrect;
    private String optionC;
    private boolean optionCCorrect;
    private String optionD;
    private boolean optionDCorrect;
    private String answerString;

    /**
     * get questionType method
     * @return
     */
    public String getQuestionType() {
        return this.questionType;
    }

    /**
     *set questionType method
     * @param quesType
     */
    public void setQuestionType(String quesType) {
        this.questionType = quesType;
    }
    
    /**
     *getDifficulty method
     * @return
     */
    public String getDifficulty() {
        return this.difficulty;
    }

    /**
     *setDifficulty method
     * @param diff
     */
    public void setDifficulty(String diff) {
        this.difficulty = diff;
    }
    
    /**
     *getQuestion method
     * @return
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     *setQuestion method
     * @param ques
     */
    public void setQuestion(String ques) {
        this.question = ques;
    }
    
    /**
     *getOptionA method
     * @return
     */
    public String getOptionA() {
        return this.optionA;
    }

    /**
     *setOptionA method
     * @param optA
     */
    public void setOptionA(String optA) {
        this.optionA = optA;
    }
    
    /**
     *getOptionACorrect method
     * @return
     */
    public boolean getOptionACorrect() {
        return this.optionACorrect;
    }

    /**
     *setOptionACorrect method
     * @param optACorrect
     */
    public void setOptionACorrect(boolean optACorrect) {
        this.optionACorrect = optACorrect;
    }

    /**
     *getOptionB method
     * @return
     */
    public String getOptionB() {
        return this.optionB;
    }

    /**
     *setOptionB method
     * @param optB
     */
    public void setOptionB(String optB) {
        this.optionB = optB;
    }

    /**
     *getOptionB correct
     * @return
     */
    public boolean getOptionBCorrect() {
        return this.optionBCorrect;
    }

    /**
     *setOptionBCorrect
     * @param optBCorrect
     */
    public void setOptionBCorrect(boolean optBCorrect) {
        this.optionBCorrect = optBCorrect;
    }
    
    /**
     *getOptionC method
     * @return
     */
    public String getOptionC() {
        return this.optionC;
    }

    /**
     *setOptionC method
     * @param optC
     */
    public void setOptionC(String optC) {
        this.optionC = optC;
    }

    /**
     *getOptionCCorrect method
     * @return
     */
    public boolean getOptionCCorrect() {
        return this.optionCCorrect;
    }

    /**
     *setOptionCCorrect method
     * @param optCCorrect
     */
    public void setOptionCCorrect(boolean optCCorrect) {
        this.optionCCorrect = optCCorrect;
    }
    
    /**
     *getOptionD method
     * @return
     */
    public String getOptionD() {
        return this.optionD;
    }

    /**
     *setOptionD method
     * @param optD
     */
    public void setOptionD(String optD) {
        this.optionD = optD;
    }

    /**
     *getOptionD correct
     * @return
     */
    public boolean getOptionDCorrect() {
        return this.optionDCorrect;
    }

    /**
     *setOptionD correct
     * @param optDCorrect
     */
    public void setOptionDCorrect(boolean optDCorrect) {
        this.optionDCorrect = optDCorrect;
    }
    
    /**
     * ctr
     */
    public Question() {

    }

    /**
     * ctr
     * @param questionID
     * @param questType
     * @param questDifficulty
     * @param question
     * @param optA
     * @param optACorrect
     * @param optB
     * @param optBCorrect
     * @param optC
     * @param optCCorrect
     * @param optD
     * @param optDCorrect
     * @param answerString
     */
    public Question(Integer questionID, String questType, String questDifficulty, String question, String optA, boolean optACorrect, String optB, boolean optBCorrect, String optC, boolean optCCorrect, String optD, boolean optDCorrect, String answerString) {
        this.questionID=questionID;
        this.questionType = questType;
        this.difficulty = questDifficulty;
        this.question = question;
        this.optionA = optA;
        this.optionACorrect = optACorrect; //default is false
        this.optionB = optB;
        this.optionBCorrect = optBCorrect; //default is false
        this.optionC = optC;
        this.optionCCorrect = optCCorrect; //default is false
        this.optionD = optD;
        this.optionDCorrect = optDCorrect; //default is false
        this.answerString=answerString;
    }

    /**
     *getAnswer string method
     * @return
     */
    public String getAnswerString() {
        return answerString;
    }

    /**
     *set answer string method
     * @param answerString
     */
    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    /**
     *getQuestionID method
     * @return
     */
    public Integer getQuestionID() {
        return questionID;
    }

    /**
     *setQuestionID method
     * @param questionID
     */
    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }
}
