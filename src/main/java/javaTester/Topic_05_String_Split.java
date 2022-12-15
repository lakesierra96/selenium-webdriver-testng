package javaTester;

public class Topic_05_String_Split {
    public static void main(String[] args) {
        String url = "http://the-internet.herokuapp.com";
        String[] authenUrlArray = url.split("//");

        System.out.println(authenUrlArray[0]);
        System.out.println(authenUrlArray[1]);

        url = authenUrlArray[0] + "//" + "admin:" + "admin@" + authenUrlArray[1];
        System.out.println(url);
    }
}
