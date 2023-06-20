package org.example.validators;

import java.util.ArrayList;

public class IdValidator implements IValidatorable {
    private ArrayList<Integer> arrayIds = new ArrayList<>();

    @Override
    public boolean validate(String value) {
        try {
            int temp = Integer.parseInt(value);
            if (temp < 1) {
                return false;
            }
            return !arrayIds.contains(temp);
        } catch (Exception ex) {
            return false;
        }
    }

    public ArrayList<Integer> getArrayIds() {
        return arrayIds;
    }
    public void setArrayIds(ArrayList<Integer> arrayIds) {
        this.arrayIds = arrayIds;
    }
}
