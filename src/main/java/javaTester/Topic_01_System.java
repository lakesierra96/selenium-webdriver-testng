package javaTester;

import java.io.File;

public class Topic_01_System {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);

        String img = "FirstIMG.JPG";
        String imgPath = projectPath + File.separator + "uploadFiles" + File.separator + img;
        System.out.println(imgPath);
    }
}
