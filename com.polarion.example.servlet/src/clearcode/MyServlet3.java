package clearcode;

////public class ProjectWorkload {
////    private final String projectName;
////    private final long time;
////    
////    public ProjectWorkload(String projectName, long time) {
////        this.projectName = projectName;
////        this.time = time;
////    }
////    
////    public String getProjectName() {
////        return projectName;
////    }
////    
////    public long getTime() {
////        return time;
////    }
//	
////}
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//public class PageAttachmentsApiExample {
//
//    public static void main(String[] args) throws IOException, InterruptedException { 
//        String polarionToken = "{eyJraWQiOiI0OGMyNTUxMS1jMGE4MDQ4Ny03NDU3NTEyNC03MmYxMjJjNCIsInR5cCI6IkpXVCIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJhSGFrZWVtIiwiaWQiOiI0ZDczODc4MC1jMGE4MDQ4Ny03NDU3NTEyNC01ZTAwNmIxNyIsImV4cCI6MTczOTk0NzIzNywiaWF0IjoxNzMyMTcxMjM3fQ.rfAHQUy1dkdu-MjGTiKFfKK3HBGYKErxFmK_bXKJ616Hrv5lAKG998ABvmUY6E2SRJc-eOC-zpzSHtZnO8uN0Q767B4_SU8F3G6FmWZDSouJFhow0CdVPZE7nVndimSswSVAkwM4wKEIQP03ogEjOk43tT7FG5N8It93jxSN6RW2SugzquGQCqZh-SzU4iZxeqZY2yB7n0QiZIssZKrlJPrvBM4id3EiU9KppeO7zaQtpTQEF3tlQBSn7k5MWbP_VoaBnxezrNBYFyCGtGbT2NrO8hfgpcCd2ruFFZdzcWpAYcXpAj4XTA0_hnNB_gtWDG4z_wBwUgIpF8AIaqsBQg}";
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:8080/polarion/rest/v1/projects/{projectId}/spaces/{spaceId}/pages/{pageName}/attachments/{attachmentId}?fields[categories]={fields[categories]}&fields[document_attachments]={fields[document_attachments]}&fields[document_comments]={fields[document_comments]}&fields[document_parts]={fields[document_parts]}&fields[documents]={fields[documents]}&fields[enumerations]={fields[enumerations]}&fields[externallylinkedworkitems]={fields[externallylinkedworkitems]}&fields[featureselections]={fields[featureselections]}&fields[globalroles]={fields[globalroles]}&fields[icons]={fields[icons]}&fields[jobs]={fields[jobs]}&fields[linkedoslcresources]={fields[linkedoslcresources]}&fields[linkedworkitems]={fields[linkedworkitems]}&fields[page_attachments]={fields[page_attachments]}&fields[pages]={fields[pages]}&fields[plans]={fields[plans]}&fields[projectroles]={fields[projectroles]}&fields[projects]={fields[projects]}&fields[projecttemplates]={fields[projecttemplates]}&fields[revisions]={fields[revisions]}&fields[testparameter_definitions]={fields[testparameter_definitions]}&fields[testparameters]={fields[testparameters]}&fields[testrecord_attachments]={fields[testrecord_attachments]}&fields[testrecords]={fields[testrecords]}&fields[testrun_attachments]={fields[testrun_attachments]}&fields[testrun_comments]={fields[testrun_comments]}&fields[testruns]={fields[testruns]}&fields[teststep_results]={fields[teststep_results]}&fields[teststepresult_attachments]={fields[teststepresult_attachments]}&fields[teststeps]={fields[teststeps]}&fields[usergroups]={fields[usergroups]}&fields[users]={fields[users]}&fields[workitem_approvals]={fields[workitem_approvals]}&fields[workitem_attachments]={fields[workitem_attachments]}&fields[workitem_comments]={fields[workitem_comments]}&fields[workitems]={fields[workitems]}&fields[workrecords]={fields[workrecords]}&include={include}&revision={revision}"))
//                .header("Accept", "application/json")
//                .header("Authorization", "Bearer " + polarionToken)
//                .GET()
//                .build();
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response);
//    }
//}

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
@WebServlet("/")
public class MyServlet3 extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String workItemId = request.getParameter("id"); // Retrieve the work item ID from the query parameter
 
        if (workItemId == null || workItemId.isEmpty()) {
            request.setAttribute("apiData", "Please provide a valid work item ID.");
        } else {
            String apiUrl = "https://polarion-lgs.com/polarion/rest/v1/projects/elibrary/workitems/" + workItemId;
            String bearerToken = "eyJraWQiOiI0OGMyNTUxMS1jMGE4MDQ4Ny03NDU3NTEyNC03MmYxMjJjNCIsInR5cCI6IkpXVCIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJhSGFrZWVtIiwiaWQiOiI0ZTk1YjQ4OS1jMGE4MDQ4Ny0yMWVkMjA1Mi1kYjY0OTEwMCIsImV4cCI6MTczOTk2NjI1NCwiaWF0IjoxNzMyMTkwMjU0fQ.kIa0grh7ttX0z33cErMlL3NDcOmIdiY6tqyWD3BGuOyNMpSyQRnoMzSZCuSU6Zs9bG5p-JUX4yaIUIgrAUY7kLnKFktr5CLw2Vw2r4CYsHl8gqAPi8MVzECZBeCrZG2ZdufJVTiMbNybyuUKre6n1FZzmkIcvOfZwJbquRkGPmjaRw_pN1d8-bRlHU1YXD4TRs76vLPUkq11L9wF_64UWkXzM11Me7iRc3bcEK3RysCfZIURDsmERWnppOjmlNZLkI_R264fNp08Xyk-Zd5RsQnT0X_vZ9VAuJDPgfvEVo48BU1HemPVSUrLuTJW3bGTX9rmuVGktn5n0VQ4pIvazQ";
 
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
 
                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder responseContent = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                    request.setAttribute("apiData", responseContent.toString());
                } else {
                    request.setAttribute("apiData", "Failed to retrieve data: HTTP " + status);
                }
 
                connection.disconnect();
            } catch (Exception e) {
                request.setAttribute("apiData", "An error occurred: " + e.getMessage());
            }
        }
 
        request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
    }
}
