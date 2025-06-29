package testng.webApp;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.listener.ReportNGListener;

@Listeners(ReportNGListener.class)
public class Administrator {
    //CRUD: Create Read Update Delete
    //API: Post Get Put Delete

    @Test(groups = "web")
    public void Admin_01_Create_New_Customer() {

    }

    @Test(groups = "web", dependsOnMethods = "Admin_01_Create_New_Customer")
    public void Admin_02_View_Customer() {

    }

    @Test(groups = "web", dependsOnMethods = {"Admin_01_Create_New_Customer", "Admin_02_View_Customer"})
    public void Admin_03_Edit_Customer() {
        Assert.assertFalse(true);
    }

    @Test(groups = "web", dependsOnMethods = "Admin_03_Edit_Customer")
    public void Admin_04_Delete_Customer() {

    }
}
