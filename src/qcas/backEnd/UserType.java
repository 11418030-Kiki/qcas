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
public enum UserType {

    /**
     *
     */
    ADMIN(1),

    /**
     *
     */
    TEACHER(2),

    /**
     *
     */
    STUDENT(3);

    private int numUserType;

    UserType(int numUserType) {
        this.numUserType = numUserType;
    }

    /**
     *
     * @return
     */
    public int getNumUserType() {
        return numUserType;
    }
}
