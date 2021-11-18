package main;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {
    public static void main(String[] args) {
        // gson test
        Gson gson = new Gson();
        System.out.println(gson.toJson(List.of(1,2,3,4)));
    }

}
