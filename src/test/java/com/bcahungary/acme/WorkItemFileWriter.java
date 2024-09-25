package com.bcahungary.acme;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WorkItemFileWriter {

    public void writeWorkItemsToFile(List<WorkItem> workItems, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("WIID,Description,Type,Status,Date");
            writer.newLine();

            for (WorkItem workItem : workItems) {
                writer.write(String.format("%s,%s,%s,%s,%s",
                        workItem.getWiid(),
                        workItem.getDescription(),
                        workItem.getType(),
                        workItem.getStatus(),
                        workItem.getDate()));
                writer.newLine();
            }
        }
    }
}
