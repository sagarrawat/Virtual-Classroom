/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Sagar-PC
 */
public class UsernameVerifier extends InputVerifier{

    @Override
    public boolean verify(JComponent jComponent) {
        JTextField jTextField = (JTextField)jComponent;
        String name = jTextField.getText();
        
        if (name.matches("^[a-zA-Z\\\\s]+") ){
            return true;
        }
        JOptionPane.showMessageDialog(null, "invalid username");
        return false;
    }

}
