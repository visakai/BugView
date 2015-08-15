package com.cisco.webex.sikpeng;

import javax.servlet.http.HttpServletRequest;

public class QueryCreation {
	

	public String create(HttpServletRequest request) {

		
		String product = addProduct(request);
		String severity = addSeverity(request);

		String status = addStatus(request);
		String attribute = addAttribute(request);

		String concatedQuery = concat(product, severity, attribute, status);

		return concatedQuery;
	}

	private String addProduct(HttpServletRequest request) {

		String p = request.getParameter("Project");

		System.out.println("Selected project: " + p);
		String product = "";

		switch (p) {
		case "Trains":
			product = "thin-client,magicboat,cca-portal,systools,train,nbr,excalibur,db,mmp,mwm,web-com,telephony,client-com,eureka,webex-user,client-uilib,webex-pt,mobile,app-mc,app-ec,app-ps,app-sc,app-sac,app-st,app-tc,app-webacd,app-framework,video-sharing,wsapi,blis,vtg-integration,it-webex,documentation,platform,client-component,performance,wdfs,api-framework,server,2012pcap,app-coc,app-exe,app-intranets,ec-3rd-party,opencloudplatform,sc-3rd-party";
			break;
		case "Orion":
			product = "orion,orionmats";
			break;
		case "WebEx11":
			product = "webex11,wapi,cwcapi,cas,userservice,csapi,qd,cn,activityserver,feed,data-analytics,searchplatform,reporting,webex-doc,migration,osapi";
		case "Cloud Services":
			product = "system,management-tools,automation-tools,sv-test-center,cs-csgias";
		case "WebEx EOL":
			product = "unspecified,special,bps,others,is,quickbook,app-weboffice,pcnow,app-labs,meetmenow,weathermap,app-cust,artemis,app-intuit,none,qaforum,cloud-alpha,app-cisco-ipphone";
			/*
			 * case "Meeting Place": Product = "meetingplace";
			 * //Project:CSC.voice
			 */
		default:
			// throw new IllegalArgumentException("Invalid input");
		}

		System.out.println("Product: " + product);

		return removeLastComma(product);
	}

	private String addSeverity(HttpServletRequest request) {

		StringBuffer severity = new StringBuffer("");

		if (request.getParameter("S1") == null) {
			severity.append("1,");
		}

		if (request.getParameter("S2") == null) {
			severity.append("2,");
		}

		if (request.getParameter("S3") == null) {
			severity.append("3,");
		}

		if (request.getParameter("S4") == null) {
			severity.append("4,");
		}

		if (request.getParameter("S5") == null) {
			severity.append("5,");
		}

		if (request.getParameter("S6") == null) {
			severity.append("6,");
		}

		return removeLastComma(severity.toString());
	}

	private String addAttribute(HttpServletRequest request) {
		//
		String p = request.getParameter("Project");
		String attribute = "";

		if ("Trains".equals(p)) {
			attribute = "notint30";
		}
		return removeLastComma(attribute);
	}

	private String addStatus(HttpServletRequest request) {
		StringBuffer status = new StringBuffer("");

		if (request.getParameter("S") == null) {
			status.append("S,");
		}

		if (request.getParameter("N") == null) {
			status.append("N,");
		}

		if (request.getParameter("A") == null) {
			status.append("A,");
		}

		if (request.getParameter("O") == null) {
			status.append("O,");
		}

		if (request.getParameter("W") == null) {
			status.append("W,");
		}

		if (request.getParameter("P") == null) {
			status.append("P,");
		}

		if (request.getParameter("H") == null) {
			status.append("H,");
		}

		if (request.getParameter("I") == null) {
			status.append("I,");
		}

		if (request.getParameter("M") == null) {
			status.append("M,");
		}

		if (request.getParameter("J") == null) {
			status.append("J,");
		}

		if (request.getParameter("U") == null) {
			status.append("U,");
		}

		if (request.getParameter("D") == null) {
			status.append("D,");
		}

		if (request.getParameter("C") == null) {
			status.append("C,");
		}

		if (request.getParameter("R") == null) {
			status.append("R,");
		}

		if (request.getParameter("V") == null) {
			status.append("V,");
		}

		return removeLastComma(status.toString());
	}

	private String removeLastComma(String s) {

		if (s != null && s.endsWith(",")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	private String concat(String product, String severity, String attribute, String status) {

		StringBuffer sb = new StringBuffer("Project:CSC.csg and Product:" + product);

		if (severity.length() > 0) {
			sb.append(" minus Severity:" + severity);
		}

		if (attribute.length() > 0) {
			sb.append(" minus Attribute:" + attribute);
		}

		if (status.length() > 0) {
			sb.append(" minus Status:" + status);
		}

		return sb.toString();
	}

}
