package org.example.validators;

public class NameValidator implements IValidatorable {
    @Override
    public boolean validate(String value) {
        try {
            return value != null && !value.equals("");
        } catch (Exception ex) {
            return false;
        }
    }
}
