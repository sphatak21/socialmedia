package application;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import profiles.Profile;
import profiles.ProfileDAO;
import profiles.UseDate;

@SuppressWarnings("serial")
public class Controller extends HttpServlet{
	private ProfileDAO dao; 
	private int loggedInId = -1;
	private UseDate loginTime; 
	private Profile logProfile; 
	public void init() {
		final String url = getServletContext().getInitParameter("JDBC-URL");
		final String username = getServletContext().getInitParameter("JDBC-USERNAME");
		final String password = getServletContext().getInitParameter("JDBC-PASSWORD");
		
		dao = new ProfileDAO(url, username, password);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String action = request.getServletPath();
		
		try {
			switch(action) {
				case "/showProfile": showProfile(request, response); break; 
				case "/editProfile": showEditProfile(request, response); break;
				case "/update": updateProfile(request, response); break;
				case "/UploadServlet": uploadServlet(request, response); break; 
				default: login(request, response); break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	private void uploadServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                	    byte [] data = item.get();  
                	    logProfile.setProfilePic(data);
                    }
                }
            }catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
        }else{
            request.setAttribute("message","Sorry this Servlet only handles file upload request");
        }    
		dao.updateProfile(logProfile);
		showProfile(request, response);
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	private void showProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {	
		String email_address = request.getParameter("email_address");
		String password = request.getParameter("password");
		if(password != null) {
			if(!dao.verifyProfilePassword(dao.getProfile(email_address), password)) {
				login(request, response);
			}else {
				logProfile = dao.getProfile(email_address);
			}
		}
		if(logProfile != null) {
			request.setAttribute("profile", logProfile);
			RequestDispatcher dispatcher = request.getRequestDispatcher("yourProfile.jsp");
			dispatcher.forward(request, response);
			
			final String action = request.getServletPath();
			loggedInId = logProfile.getID();
			loginTime = new UseDate(); 
			try {
				switch(action) {
					case "edit": showEditProfile(request, response); break; 
					case "logout": logout(request, response); break; 
				}
			}catch (SQLException e) {
				throw new ServletException(e);
			}
		}
	}	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Profile profile = dao.getProfile(loggedInId);
		profile.setLast_login(loginTime);
		dao.updateProfile(profile);
		loggedInId = -1; 
		loginTime = null; 
		logProfile = null;
		request.setAttribute("profile", profile);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
		login(request, response); 
		
	}
	private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		final String action = request.getParameter("action") != null
			? request.getParameter("action")
			: request.getParameter("submit").toLowerCase();
			
			
		Profile profile = dao.getProfile(loggedInId);
		switch(action) {
			case "save": 
				String first_name = request.getParameter("first_name");
				String last_name = request.getParameter("last_name");
				String college_name = request.getParameter("college_name");
				String highschool_name = request.getParameter("highschool_name");
				String email_address = request.getParameter("email_address");
				long phone_number = Long.parseLong(request.getParameter("phone_number"));
				
				profile.setFirst_name(first_name);
				profile.setLast_name(last_name);
				profile.setCollege_name(college_name);
				profile.setHighschool_name(highschool_name);
				profile.setEmail_address(email_address);
				profile.setPhone_number(phone_number);
				profile.setLast_login(loginTime);
				profile.setLast_profile_modified(new UseDate());
				break;
		}
		dao.updateProfile(profile);
		logProfile = profile;
		response.sendRedirect(request.getContextPath() + "/showProfile");
	}
	private void showEditProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		request.setAttribute("profile", logProfile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("profileform.jsp");
		dispatcher.forward(request, response);		
	}
}

