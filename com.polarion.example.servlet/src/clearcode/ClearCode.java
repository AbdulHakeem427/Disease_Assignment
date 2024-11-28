package clearcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polarion.alm.projects.model.IProject;
import com.polarion.alm.tracker.ITrackerService;
import com.polarion.alm.tracker.model.IWorkItem;
import com.polarion.core.util.types.duration.DurationTime;
import com.polarion.platform.core.PlatformContext;
import com.polarion.platform.persistence.model.IPObject;
import com.polarion.platform.persistence.model.IPObjectList;
import com.polarion.platform.security.ISecurityService;

/**
 * Servlet that calculates and displays the workload for the current user across all projects.
 * Workload calculation rules:
 * 1. Uses remaining estimation time if available
 * 2. Falls back to initial estimate time if remaining time is not set
 * 3. Defaults to 1 day if neither estimate is available
 */
public class ClearCode extends HttpServlet {  
	    private static final long serialVersionUID = 1L;
	    private static final String JSP_PAGE = "/currentUserWorkload2.jsp";
	    private static final String EMPTY_QUERY = "";
	    
	    private final ITrackerService trackerService;
	    private final ISecurityService securityService;
	    
	    public ClearCode() {
	        this.trackerService = getPlatformService(ITrackerService.class);
	        this.securityService = getPlatformService(ISecurityService.class);
	    }
	    
	    @SuppressWarnings("unchecked")
	    private <T> T getPlatformService(Class<T> serviceClass) {
	        return (T) PlatformContext.getPlatform().lookupService(serviceClass);
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String currentUser = securityService.getCurrentUser();
	        long dayLength = trackerService.getPlanningManager().getOneDayLength();
	        
	        List<ProjectWorkload> projectWorkloads = calculateUserWorkloads(currentUser, dayLength);
	        
	        setRequestAttributes(request, projectWorkloads, dayLength);
	        forwardToJsp(request, response);
	    }
	    
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    private List<ProjectWorkload> calculateUserWorkloads(String username, long dayLength) {
	        List<ProjectWorkload> workloads = new ArrayList<>();
	        List<IProject> projects = getActiveProjects();
	        
	        for (IProject project : projects) {
	            ProjectWorkload workload = calculateProjectWorkload(project, username, dayLength);
	            if (workload != null) {
	                workloads.add(workload);
	            }
	        }
	        
	        return workloads;
	    }
	    
	    private List<IProject> getActiveProjects() {
	        IPObjectList projectsList = trackerService.getProjectsService().searchProjects(EMPTY_QUERY);
	        return (List<IProject>) projectsList.stream()
	                .filter(obj -> obj instanceof IProject)
	                .map(obj -> (IProject) obj)
	                .collect(Collectors.toList());
	    }
	    
	    private ProjectWorkload calculateProjectWorkload(IProject project, String username, long dayLength) {
	        IPObjectList workItems = queryUserWorkItems(project, username);
	        
	        if (workItems.isEmpty()) {
	            return null;
	        }
	        
	        long totalTime = calculateTotalWorkload(workItems, dayLength);
	        return new ProjectWorkload();
	    }
	    
	    private IPObjectList queryUserWorkItems(IProject project, String username) {
	        String query = String.format("assignee.id:%s", username);
	        return trackerService.queryWorkItems(project, query, "remainingEstimate");
	    }
	    
	    private long calculateTotalWorkload(IPObjectList workItems, long defaultDayLength) {
	        return workItems.stream()
	                .filter(obj -> obj instanceof IWorkItem)
	                .map(obj -> (IWorkItem) obj)
	                .mapToLong(workItem -> getWorkItemDuration(workItem, defaultDayLength))
	                .sum();
	    }
	    
	    private long getWorkItemDuration(Object workItem, long defaultDayLength) {
	        DurationTime remainingEstimate = ((IWorkItem) workItem).getRemainingEstimate();
	        if (remainingEstimate != null) {
	            return remainingEstimate.getLength();
	        }
	        
	        DurationTime initialEstimate = ((IWorkItem) workItem).getInitialEstimate();
	        return initialEstimate != null ? initialEstimate.getLength() : defaultDayLength;
	    }
	    
	    private void setRequestAttributes(HttpServletRequest request, 
	            List<ProjectWorkload> workloads, long dayLength) {
	        request.setAttribute("pairs", workloads);
	        request.setAttribute("dayLength", dayLength);
	    }
	    
	    private void forwardToJsp(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
	    }
	}
	/**
	 * Immutable class representing the workload for a specific project
	 */
	
	

