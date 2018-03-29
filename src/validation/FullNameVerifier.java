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
 * @author sagar
 */
public class FullNameVerifier extends InputVerifier {
    
    @Override
    public boolean verify(JComponent jComponent) {
        JTextField jTextField = (JTextField)jComponent;
        String name = jTextField.getText();
        
        if (name.matches("^\\p{L}+(?: \\p{L}+)*$") ){
            return true;
        }
        JOptionPane.showMessageDialog(null, "invalid name");
        return false;
    }
}
