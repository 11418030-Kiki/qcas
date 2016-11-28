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
public enum Gender {

    /**
     *
     */
    FEMALE('F'),

    /**
     *
     */
    MALE('M');

    private char charGender;

    Gender(char charGender) {
        this.charGender = charGender;
    }

    /**
     *
     * @return
     */
    public int getCharGender() {
        return charGender;
    }
}
