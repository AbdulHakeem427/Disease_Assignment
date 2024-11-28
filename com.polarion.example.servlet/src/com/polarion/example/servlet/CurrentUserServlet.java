package com.polarion.example.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polarion.alm.tracker.ITrackerService;
import com.polarion.alm.tracker.model.IWorkItem;
import com.polarion.platform.core.PlatformContext;

/*
 * Copyright (C) 2004-2014 Polarion Software
 * All rights reserved.
 * Email: dev@polarion.com
 *
 *
 * Copyright (C) 2004-2014 Polarion Software
 * All Rights Reserved.  No use, copying or distribution of this
 * work may be made except in accordance with a valid license
 * agreement from Polarion Software.  This notice must be
 * included on all copies, modifications and derivatives of this
 * work.
 *
 * POLARION SOFTWARE MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESSED OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. POLARION SOFTWARE
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT
 * OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */


/**
 * This is the servlet which counts workload of current user. The code here calculates the workload
 * which is then rendered by currentUserWorkload.jsp.
 * 
 * The time is count according to these rules:
 *          1) time to resolve work item (TTRWI) = remaining estimation time (RMT)
 *          2) if RMT is not set, then TTRWI = initial estimate time (IET)
 *          3) if IET is not set, then TTRWI = 1 day
 * 
 * @author Stepan Roh
 */


public class CurrentUserServlet extends HttpServlet {
	ITrackerService trackerService = (ITrackerService) PlatformContext.getPlatform().lookupService(ITrackerService.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	            List<IWorkItem> workItems = trackerService.queryWorkItems("configDemo","");
	            for (IWorkItem workItem : workItems) {
	               
	                System.out.println("Work Item ID: " + workItem.getId());
	                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}