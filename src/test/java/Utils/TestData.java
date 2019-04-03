package Utils;

import lombok.Data;

@Data
public class TestData {


    private String username;
    private String password;
    private String validationMessage;

    @Override
    public String toString() {


        return "TestData{" +
                " username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", validationMessage='" + validationMessage + '\'' +
                '}';

    }

}
