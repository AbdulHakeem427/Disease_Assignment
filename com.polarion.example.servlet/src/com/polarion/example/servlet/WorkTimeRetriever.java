package com.polarion.example.servlet;

import com.polarion.alm.tracker.model.IWorkItem;
import com.polarion.platform.core.PlatformContext;
import java.util.List;
import java.util.stream.Collectors;
import com.polarion.alm.tracker.ITrackerService;

public class WorkTimeRetriever {
    public static void main(String[] args) {
        ITrackerService trackerService = (ITrackerService) PlatformContext.getPlatform().lookupService(ITrackerService.class);
        try {
            List<IWorkItem> workItems = trackerService.queryWorkItems("configDemo", "");

            // Option 1: Concise forEach with lambda expression (Java 8+)
            workItems.forEach(workItem -> System.out.println("Work Item ID: " + workItem.getId()));

            // Option 2: Stream API for more complex operations (Java 8+)
            List<String> workItemIds = workItems.stream()
                .map(IWorkItem::getId) // Extract IDs using method reference
                .collect(Collectors.toList()); // Collect IDs into a new list

            workItemIds.forEach(id -> System.out.println("Work Item ID: " + id));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}