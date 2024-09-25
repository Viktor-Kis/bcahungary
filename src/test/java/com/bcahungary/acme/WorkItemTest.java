package com.bcahungary.acme;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.Collectors;

public class WorkItemTest {

    private WebDriver driver;
    private WorkItemScraper workItemScraper;
    private List<WorkItem> workItems;

    @BeforeClass
    public void setUp() {
        driver = TestUtils.createDriver();
        TestUtils.login(driver);
        driver.get("https://acme-test.uipath.com/work-items");
        workItemScraper = new WorkItemScraper(driver);
    }

    @Test
    public void testWorkItemsList() {
        workItems = workItemScraper.getWorkItems();
        Assert.assertNotNull(workItems, "A feladatlista nem lett eltárolva.");
        Assert.assertFalse(workItems.isEmpty(), "A feladatlista üres, nem találhatóak munkafeladatok.");
    }

    @Test(dependsOnMethods = {"testWorkItemsList"})
    public void testWriteWorkItemsToFile() throws IOException {
        var wfw = new WorkItemFileWriter();
        String path = TestUtils.properties.getProperty("workitem.file.path");

        List<WorkItem> wi4Items = workItems.stream()
                .filter(item -> "WI4".equals(item.getType()))
                .collect(Collectors.toList());

        wfw.writeWorkItemsToFile(wi4Items, path);

        File file = new File(path);
        Assert.assertTrue(file.exists(), "A fájl nem jött létre.");

        List<String> lines = Files.readAllLines(file.toPath());
        Assert.assertTrue(!lines.isEmpty(), "A fájl üres.");

        // Ellenőrizzük, hogy az első sor a fejléc
        Assert.assertEquals("WIID,Description,Type,Status,Date", lines.get(0));
    }

    @Test(dependsOnMethods = {"testWorkItemsList"})
    public void testMoreThan13WorkItems() {
        long wi4Count = workItems.stream()
                .filter(item -> "WI4".equals(item.getType()))
                .count();

        Assert.assertTrue(wi4Count > 13, "Kevesebb, mint 13 WI4 feladat került kigyűjtésre.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
