package com.bcahungary.acme;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class WorkItemScraper {

    private final WebDriver driver;

    public WorkItemScraper(WebDriver driver) {
        this.driver = driver;
    }

    public List<WorkItem> getWorkItems() {
        List<WorkItem> allWorkItems = new ArrayList<>();

        while (true) {
            List<WorkItem> workItemsOnPage = extractWorkItemsFromCurrentPage();
            allWorkItems.addAll(workItemsOnPage);

            // Ellenőrizzük, van-e 'Next' gomb
            WebElement nextButton = getNextButton();
            if (nextButton == null) {
                break;
            }
            nextButton.click();
            TestUtils.waitforDomComplete(driver);
        }

        return allWorkItems;
    }

    private List<WorkItem> extractWorkItemsFromCurrentPage() {
        List<WorkItem> workItems = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.cssSelector(".table tbody tr"));

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() == 6) {
                String wiid = columns.get(1).getText();
                String description = columns.get(2).getText();
                String type = columns.get(3).getText();
                String status = columns.get(4).getText();
                String date = columns.get(5).getText();

                WorkItem workItem = new WorkItem(wiid, description, type, status, date);
                workItems.add(workItem);
            }
        }
        return workItems;
    }

    private WebElement getNextButton() {
        try {
            return driver.findElement(By.cssSelector("a.page-numbers[rel='next']"));
        } catch (Exception e) {
            return null;
        }
    }

}
