package org.pigne;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DateFormat;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountDown extends HttpServlet {

	@Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
   {

      // Get information
      String cityName = request.getParameter("city");
      TimeZone zone = getTimeZone(cityName);

      // Set content type to HTML
      response.setContentType("text/html");

      // Print formatted information
      PrintWriter out = response.getWriter();

	String title = "Fuseau horaire des capitales";
	out.println("<!DOCTYPE html>");
	out.println("<html><head>");
	out.println("<meta charset=\"utf-8\" />");
	out.println("<title>");
	out.println(title);
	out.println("</title></head><body style=\"background-color:powderblue;\"><h1>");
	out.println("</h1><p>");

      if (zone == null)
         out.println("Ville inconnue ...");
      else
      {
         out.print("Le temps actuel à <b>");
         out.print(cityName);
         out.print("</b> est: ");
         DateFormat formatter = DateFormat.getTimeInstance();
         formatter.setTimeZone(zone);
         Date now = new Date();
         out.print(formatter.format(now));
	 out.println("</p>");
	 //Local time
	 out.print("<p>Le temps <b>local</b> est: ");
	 out.println(""+diff()+"</p>");
      }
      out.println("</body></html>");

      out.close();
   }

	private String diff(){
		String theDate = "02/11/2016 17:30:00";
		String pattern = "dd/MM/yyyy HH:mm:ss";
		Date d2 = null;
		try {
			d2 = new SimpleDateFormat(pattern).parse(theDate);
		} catch (ParseException e) {
			return "erreur du serveur...";
		}
		Date d1 = new Date();
		 String DATE_FORMAT_NOW = "HH:mm:ss";
		 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		 String stringDate = sdf.format(d1);

		//long diff = d2.getTime() - d1.getTime();
		long diff = d1.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return stringDate;

	      //return (diffHours+1)+" heure(s) "+diffMinutes+" minute(s) "+diffSeconds+" seconde(s) ";

	}

   /**
      Looks up the time zone for a city
      @param aCity the city for which to find the time zone
      @return the time zone or null if no match is found
   */
   private static TimeZone getTimeZone(String city)
   {
      String[] ids = TimeZone.getAvailableIDs();
      for (int i = 0; i < ids.length; i++)
         if (timeZoneIDmatch(ids[i], city))
            return TimeZone.getTimeZone(ids[i]);
      return null;
   }

   /**
      Checks whether a time zone ID matches a city
      @param id the time zone ID (e.g. "America/Los_Angeles")
      @param aCity the city to match (e.g. "Los Angeles")
      @return true if the ID and city match
   */
   private static boolean timeZoneIDmatch(String id, String city)
   {
      String idCity = id.substring(id.indexOf('/') + 1);
      return idCity.replace('_', ' ').equals(city);
   }

}

//*********************************************************************
/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.getServletContext()
			.getRequestDispatcher( "/WEB-INF/CountDownView.jsp" )
			.forward( request, response );

			String diff = diff();
			request.setAttribute( "diff", diff );
			this.getServletContext()
			.getRequestDispatcher( "/WEB-INF/CountDownView.jsp" )
			.forward( request, response );

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\" />");
		out.println("<title>CountDown</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>"+diff()+"</p>");
		out.println("</body>");
		out.println("</html>");

	}
*/

