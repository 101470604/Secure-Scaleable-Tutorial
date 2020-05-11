/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Tom
 */
@FacesValidator(value = "passwordsMatchValidator")
public class passwordsMatchValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
        
        
        UIInput pInput = (UIInput) facesContext.getViewRoot().findComponent("form:password");

        if (pInput == null) {
            System.err.println("pInput is NULL");
            throw new IllegalArgumentException(String.format("Unable to find component with id %s", pInput));
        }
        // Get its value, the entered text of the first field.
        String password = (String) pInput.getValue();

        // Cast the value of the entered text of the second field back to String.
        String cPassword = (String) value;

        // Check if the first text is actually entered and compare it with second text.
        if (password != null && password.length() != 0 && !password.equals(cPassword)) {
            throw new ValidatorException(new FacesMessage("Passwords do not match!"));
        }
    }
}
