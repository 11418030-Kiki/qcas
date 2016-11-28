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
public class Question {

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

    /**
     *
     * @return
     */
    public String getQuestionType() {
        return this.questionType;
    }

    /**
     *
     * @param quesType
     */
    public void setQuestionType(String quesType) {
        this.questionType = quesType;
    }
    
    /**
     *
     * @return
     */
    public String getDifficulty() {
        return this.difficulty;
    }

    /**
     *
     * @param diff
     */
    public void setDifficulty(String diff) {
        this.difficulty = diff;
    }
    
    /**
     *
     * @return
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     *
     * @param ques
     */
    public void setQuestion(String ques) {
        this.question = ques;
    }
    
    /**
     *
     * @return
     */
    public String getOptionA() {
        return this.optionA;
    }

    /**
     *
     * @param optA
     */
    public void setOptionA(String optA) {
        this.optionA = optA;
    }
    
    /**
     *
     * @return
     */
    public boolean getOptionACorrect() {
        return this.optionACorrect;
    }

    /**
     *
     * @param optACorrect
     */
    public void setOptionACorrect(boolean optACorrect) {
        this.optionACorrect = optACorrect;
    }

    /**
     *
     * @return
     */
    public String getOptionB() {
        return this.optionB;
    }

    /**
     *
     * @param optB
     */
    public void setOptionB(String optB) {
        this.optionB = optB;
    }

    /**
     *
     * @return
     */
    public boolean getOptionBCorrect() {
        return this.optionBCorrect;
    }

    /**
     *
     * @param optBCorrect
     */
    public void setOptionBCorrect(boolean optBCorrect) {
        this.optionBCorrect = optBCorrect;
    }
    
    /**
     *
     * @return
     */
    public String getOptionC() {
        return this.optionC;
    }

    /**
     *
     * @param optC
     */
    public void setOptionC(String optC) {
        this.optionC = optC;
    }

    /**
     *
     * @return
     */
    public boolean getOptionCCorrect() {
        return this.optionCCorrect;
    }

    /**
     *
     * @param optCCorrect
     */
    public void setOptionCCorrect(boolean optCCorrect) {
        this.optionCCorrect = optCCorrect;
    }
    
    /**
     *
     * @return
     */
    public String getOptionD() {
        return this.optionD;
    }

    /**
     *
     * @param optD
     */
    public void setOptionD(String optD) {
        this.optionD = optD;
    }

    /**
     *
     * @return
     */
    public boolean getOptionDCorrect() {
        return this.optionDCorrect;
    }

    /**
     *
     * @param optDCorrect
     */
    public void setOptionDCorrect(boolean optDCorrect) {
        this.optionDCorrect = optDCorrect;
    }
    
    /**
     *
     */
    public Question() {

    }

    /**
     *
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
     */
    public Question(String questType, String questDifficulty, String question, String optA, boolean optACorrect, String optB, boolean optBCorrect, String optC, boolean optCCorrect, String optD, boolean optDCorrect) {
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
    }
}
