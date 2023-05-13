package javaTester;

import java.util.Random;

public class Topic_04_Random_Number {
    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    public static void main(String[] args) {
        System.out.println("hieupkt" + getRandomNumber() + "@gmail.com");
    }
}
