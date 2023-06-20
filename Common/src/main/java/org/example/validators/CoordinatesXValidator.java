package org.example.validators;

public class CoordinatesXValidator implements IValidatorable {
    @Override
    public boolean validate(String value) {
        try {
            long temp = Long.parseLong(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
