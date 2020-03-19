/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg487.loan.system;

/**
 *
 * @author ryan
 */
public class UserLoginException extends Exception {

    public UserLoginException(String authentication_failed) {
	super(authentication_failed);
    }
    
}
