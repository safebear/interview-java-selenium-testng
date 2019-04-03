package Utils;

import lombok.Data;

@Data
public class TestData {


    private String username;
    private String password;

    @Override
    public String toString() {


        return "TestData{" +
                " username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';

    }

}
