package com.hakeem.plugin;

import com.polarion.platform.ITransactionService;
//import com.polarion.platform.core.model.IWorkItem;
//import com.polarion.platform.core.transaction.ITransaction;
//import com.polarion.platform.internal.service.RepositoryServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.polarion.alm.tracker.ITrackerService;
import com.polarion.alm.tracker.model.IWorkItem;
import com.polarion.platform.core.PlatformContext;
import com.polarion.platform.security.ISecurityService;
import com.polarion.platform.persistence.model.IPObjectList;

//@WebServlet("/workitems")
public class WorkItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        try {
            // Get required services
            ITrackerService tracker = PlatformContext.getPlatform().lookupService(ITrackerService.class);
            ISecurityService security = PlatformContext.getPlatform().lookupService(ISecurityService.class);
            
            // Ensure user is authenticated
//            if (!security.getCurrentUser().isAuthenticated()) {
//                resp.sendRedirect(req.getContextPath() + "/login");
//                return;
//            }
            
            // Query all work items
            List<WorkItemInfo> workItems = new ArrayList<>();
            IPObjectList<IWorkItem> queryResult = tracker.queryWorkItems("project.id:* AND NOT HAS_VALUE:resolution", null);
            
            if (queryResult != null && !queryResult.isEmpty()) {
                for (IWorkItem item : queryResult) {
                    WorkItemInfo info = new WorkItemInfo(
                        item.getId(),
                        item.getTitle(),
                        item.getProject().getId(),
                        item.getType().getId()
                    );
                    workItems.add(info);
                }
                req.setAttribute("workItems", workItems);
                req.setAttribute("message", "Found " + workItems.size() + " work items");
            } else {
                req.setAttribute("message", "No work items found");
            }
            
        } catch (Exception e) {
            req.setAttribute("error", "Error retrieving work items: " + e.getMessage());
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/workids.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        doGet(req, resp);
    }
    
    // Inner class to hold work item information
    public static class WorkItemInfo {
        private String id;
        private String title;
        private String projectId;
        private String type;
        
        public WorkItemInfo(String id, String title, String projectId, String type) {
            this.id = id;
            this.title = title;
            this.projectId = projectId;
            this.type = type;
        }
        
        // Getters
        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getProjectId() { return projectId; }
        public String getType() { return type; }
    }
}
