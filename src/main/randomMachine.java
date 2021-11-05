package studentRegistirationSystem;

public class randomMachine {

    static int number=1000;

    public static int studentID() {
        return number++;
    }

    public static int semester() {
        return (int)(1+Math.random()*7);
    }

}

