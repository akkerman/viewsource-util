package nl.viewsource.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Makes it possible to call an URL from outside the server as if it were on the server.
 * Particularly useful to redirect JSON calls (which are only allowed on the same server)
 * @author Marcel Akkerman 
 */
public class UrlWrapperServlet extends HttpServlet {

	private static final long serialVersionUID = -5779645820428290260L;

	private static final Logger logger = Logger.getLogger(UrlWrapperServlet.class);

	private String url;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {	
				
		
		final String queryString = req.getQueryString();
		
		
		String query = queryString == null ? url : url + "?" + queryString;
			
		// create URL for the query and connect to the (remote) server
		URL u = new URL(query);		
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.connect();		
		logger.debug(String.format("ContentType for %s = %s", query, conn.getContentType()));
		
		String contentType = conn.getContentType(); 
		// check if the reply is something we can parse (i.e. text type)
		if ( contentType != null && contentType.contains("text/")) {
			int responseCode = conn.getResponseCode();
			logger.debug("Responsecode: " + responseCode);
			if (responseCode == 200) { 
				// response code 200 = OK,  since it was a GET request
				// code 200 means the requested resource was sent in the response
				
				// forward the content type
				resp.setContentType(contentType);
				// open a stream for writing
				InputStream in = conn.getInputStream();				
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));				
				String line;
				PrintWriter writer = resp.getWriter();
				// for all lines in the original response, write our own response
				while ((line=reader.readLine()) != null) {
					writer.println(line);
				}								
				writer.close();		
			} else {
				logger.warn("page not reachable: " + query);
				logger.debug("Recieved response code "+responseCode);
				
				// assuming the user of this class configures the correct URL
				// we can also assume not receiving a status code 200 will be temporary
				resp.setStatus(503);  
			} 
		}
		conn.disconnect();

	}
	
	/**
	 * Set's the U
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public void init() throws ServletException {
		super.init();
		url = getServletConfig().getInitParameter("url");
	}	


}
