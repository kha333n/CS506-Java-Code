package controller;
import vu.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ControllerServlet extends HttpServlet {
// This method only calls processRequest()
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException
{
processRequest(request, response);
}
// This method only calls processRequest()
protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException
{
processRequest(request, response);
}
protected void processRequest(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException
{
    
//    response.setContentType("text/html");
//PrintWriter out = response.getWriter();



// retrieving value of action parameter
String userAction = request.getParameter("action");
// if request comes to move to addperson.jsp from hyperlink
if (userAction.equals("addperson") ) {
response.sendRedirect("addperson.jsp");
// if request comes to move to searchperson.jsp from hyperlink
} else if (userAction.equals("searchperson")) {
response.sendRedirect("searchperson.jsp");
// if “save” button clicked on addperson.jsp to add new record
} if (userAction.equals("save")) {
// this method defined below
addPerson(request,response);

// if “search” button clicked on searchperson.jsp for search
} else if (userAction.equals("search"))
{
// this method defined below
searchPerson(request,response);
}
} // end processRequest()
// if request comes to add/save person
private void addPerson(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException
{
try
{
// creating PersonDAO object
PersonDAO pDAO = new PersonDAO();
// creating PersonInfo object
PersonInfo person = new PersonInfo();
// setting properties of Person object
// setting name property
String pName = request.getParameter("name");
person.setName(pName);
// setting address propertyt
String add = request.getParameter("address");
person.setAddress(add);
// setting phoneNumb property
String pNo = request.getParameter("phoneNum");
int phoneNum = Integer.parseInt(pNo);
person.setPhoneNum(phoneNum);
// calling PersonDAO method to save data into database
pDAO.addPerson(person);
// redirecting page to saveperson.jsp
response.sendRedirect("saveperson.jsp");
}catch (SQLException sqlex){
// setting SQLException instance
request.setAttribute("javax.servlet.jsp.JspException" , sqlex);
RequestDispatcher rd =
request.getRequestDispatcher("addbookerror.jsp");
rd.forward(request, response);

}catch (ClassNotFoundException cnfe){
// setting ClassNotFoundException instance
request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
RequestDispatcher rd =
request.getRequestDispatcher("addbookerror.jsp");
rd.forward(request, response);
}
}// end addperson()
// if request comes to search person record from database
private void searchPerson(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException
{
try {
// creating PersonDAO object
PersonDAO pDAO = new PersonDAO();
String pName = request.getParameter("name");
// calling DAO method to retrieve personlist from database
// against name
ArrayList personList = pDAO.retrievePersonList(pName);
request.setAttribute("list", personList);
// forwarding request to showpeson, so it can render personlist
RequestDispatcher rd =
request.getRequestDispatcher("showperson.jsp");
rd.forward(request, response);
}catch (SQLException sqlex){
// setting SQLException instance
request.setAttribute("javax.servlet.jsp.JspException" , sqlex);
RequestDispatcher rd =
request.getRequestDispatcher("addbookerror.jsp");
rd.forward(request, response);
}catch (ClassNotFoundException cnfe){
// setting ClassNotFoundException instance
request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
RequestDispatcher rd =
request.getRequestDispatcher("addbookerror.jsp");
rd.forward(request, response);
}
}// end searchPerson()
} // end ControllerServlet



        