/*****************************
CSCD 327 HomeWork 2
Winter Quarter of 2014
Author: Brandon Fowler
*****************************/
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.util.Date;
import java.lang.String;

public class MyQuery {

    private Connection conn = null;
	 private Statement statement = null;
	 private ResultSet resultSet = null;
    
    
    
    public MyQuery(Connection c)throws SQLException
    {
        conn = c;
        // Statements allow to issue SQL queries to the database
        statement = conn.createStatement();
    }
    
    
    
   //findGPAInfo()
   //Create SQL statement to find GPAs then executes it.
    public void findGPAInfo() throws SQLException
    {
      String Q =  ("select ID, name, sum(case grade "+
						"when 'A' then 4 "+
						"when 'A-' then 3.67 "+
						"when 'B+' then 3.33 "+
						"when 'B' then 3 "+
						"when 'B-' then 2.67 "+
						"when 'C+' then 2.33 "+
						"when 'C' then 2 "+
						"when 'C-' then 1.67 "+
						"when 'D+' then 1.33 "+
						"when 'D' then 1 "+
						"when 'D-' then 0.67 "+
						"when 'F' then 0 end "+
						"* credits)/sum(credits) as G "+
						"from takes natural join student join "+
                  "course on course.course_id = takes.course_id "+
						"where grade is not null group by ID;");
						
		resultSet = statement.executeQuery(Q);
    }
    
    
    
   //printGPAInfo
   //Prints the GPA information returned from SQL query with some formating
    public void printGPAInfo() throws IOException, SQLException
    {
      System.out.println();
		System.out.println("******** Query 1 ********");
      System.out.println(" ID    | Name       | GPA      ");
      System.out.println("-------|------------|----------");
      System.out.println("-"); 
      
      while(resultSet.next())
		{
			String ID = resultSet.getString("ID");
			System.out.print(ID + "  |");
			String N = resultSet.getString("name");
         N = String.format("%1$"+10+ "s", N);
			System.out.print(N + "  |");
			String G = resultSet.getString("G");
			System.out.println("  "+G);
		}
      
      System.out.println();
    }



   //findMorningCourses()
   //Create SQL statement to find Moning Classes then executes it.
    public void findMorningCourses() throws SQLException
    {
      String Q = ("select course_id, sec_id, "+
                  "title, semester, year, "+
						"name, TB1.enroll "+
						"from section "+
						"natural join time_slot "+
						"natural join course "+
						"natural join instructor "+
						"natural join teaches, "+
						"(select Sec.course_id as C, "+
                  "Sec.sec_id as S, "+ 
						"count(Sec.course_id) as enroll "+
						"from section as Sec "+
						"natural join takes as TB2 "+
						"group by Sec.course_id, Sec.sec_id) TB1 "+
						"where time_slot.start_hr < 13 "+
						"and TB1.C = section.course_id "+
						"and TB1.S = section.sec_id "+
						"and TB1.enroll > 0 "+
						"group by course_id, sec_id");
		resultSet = statement.executeQuery(Q);
    }



   //printMorningCourses()
   //Prints the course information returned from SQL query with some formating
    public void printMorningCourses() throws IOException, SQLException
    {
		System.out.println("******** Query 2 ********");
      System.out.println("Course_ID  | Sec_ID    | Title                     | Semecter| Year | Name         | Enrollment");
      System.out.println("-----------|-----------|---------------------------|---------|------|--------------|-----------");
      System.out.println("-");
      
      while(resultSet.next())
		{
			String cid = resultSet.getString("course_id");
         cid = String.format("%1$"+10+ "s", cid);
			System.out.print(cid + " |");
			String sid = resultSet.getString("sec_id");
         sid = String.format("%1$"+10+ "s", sid);
			System.out.print(sid + " |");
			String title = resultSet.getString("title");
         title = String.format("%1$"+26+ "s", title);
			System.out.print(title + " |");
			String semester = resultSet.getString("semester");
         semester = String.format("%1$"+7+ "s", semester);
			System.out.print(semester + "  | ");
			String year = resultSet.getString("year");
			System.out.print(year + " |   ");
			String name = resultSet.getString("name");
         name = String.format("%1$"+10+ "s", name);
			System.out.print(name + " |");
			String enroll = resultSet.getString("enroll");
         enroll = String.format("%1$"+10+ "s", enroll);
			System.out.println(enroll);
		}
      System.out.println();
    }



   //findBusyInstructor()
   //Create SQL statement to find busiest instructor then executes it.
    public void findBusyInstructor() throws SQLException
    {
      String Q = ("select name "+
						"from instructor "+
						"natural join teaches "+
					   "group by name "+
						"having count(course_id) >= all "+
                  "(select count(course_id) "+
						"from instructor "+
						"natural join teaches "+
						"group by name);");
		resultSet = statement.executeQuery(Q);
    }


   
   //printBusyInstructor()
   //Prints the instructor information returned from SQL query with some formating
    public void printBusyInstructor() throws IOException, SQLException
    {
		System.out.println("******** Query 3 ********");
      System.out.println("Name ");
      System.out.println("----------");
      System.out.println("-");
      
      while(resultSet.next())
		{
			String N = resultSet.getString("name");
			System.out.println(N);	
		}
      System.out.println();
    }



   //findPrereq()
   //Create SQL statement to find courses and thier prereqs then executes it.
    public void findPrereq() throws SQLException
    {
      String Q =
            ("select course.title course, "+
            "coalesce(T1.title,\'\') prereq " +
            "from course LEFT JOIN " +
            "(select PR.course_id,prereq_id,title " +
            "from course as CR JOIN prereq PR ON "+
            "CR.course_id=PR.prereq_id)as T1 "+
            "ON course.course_id=T1.course_id;");
        resultSet = statement.executeQuery(Q);
    }



   //printPrereq()
   //Prints the course information returned from SQL query with some formating
    public void printPrereq() throws IOException, SQLException
    {
      System.out.println("******** Query 4 ********");
      System.out.println("Course                      | Prereq");
      System.out.println("----------------------------|-----------------------------");
      System.out.println("-");
      
        while(resultSet.next())
        {
            String Course = resultSet.getString("course");
            String Prereq = resultSet.getString("prereq");
            Course = String.format("%1$"+28+ "s", Course);
            Prereq = String.format("%1$"+28+ "s", Prereq);
            System.out.println(Course+"|"+ Prereq);
        }
        System.out.println();
    }



   //updateTable()
   //Create SQL statement to update the credits on a copy of the students table then executes it.
    public void updateTable() throws SQLException
    {
      String Q = "create temporary table studentCopy select * from student;";
      statement.executeUpdate(Q);
        
      String Q2 = ("update studentCopy "+
						"set tot_cred = (select sum(credits) "+
						"from student "+
						"natural join takes "+
                  "join course on course.course_id "+
                  "= takes.course_id "+
                  "where grade is not null and grade <> 'F'"+
						"group by student.ID "+
						"having student.ID = studentCopy.ID);");
		statement.executeUpdate(Q2);
		
		String Q3 = ("select * from studentCopy");
		resultSet = statement.executeQuery(Q3);
    }
   
   
   
   //printUpdateTable()
   //Prints the student information returned from SQL query with some formating
    public void printUpdatedTable() throws IOException, SQLException
    {
		System.out.println("******** Query 5 ********");
      System.out.println("ID     | Name      | Department  | Credits");
      System.out.println("-------|-----------|-------------|---------");
      System.out.println("-");
      
		while(resultSet.next())
		{
			String ID = resultSet.getString("ID");
			System.out.print(ID+"  |");
			String N = resultSet.getString("name");
         N = String.format("%1$"+10+ "s", N);
			System.out.print(N+" |");
			String Dep = resultSet.getString("dept_name");
         Dep = String.format("%1$"+12+ "s", Dep);
			System.out.print(Dep+" |");
			String Creds = resultSet.getString("tot_cred");
         if(Creds==null)
            Creds = "0";
         Creds = String.format("%1$"+8+ "s", Creds);
			System.out.println(Creds);
		}
      System.out.println();
    }



   //findFirstLastSemester()
   //Create SQL statement to find the first and last quarter of each student then executes it.
    public void findFirstLastSemester() throws SQLException
    {
        String Q = ("create temporary table FL "+
                    "select id, min(year) as FY, max(year) "+
                    "as LY from takes group by id;");
                    statement.executeUpdate(Q);
          
         Q = ("create temporary table FYSemesters " + 
         "select id, case when semester = \'Fall\' then "+
         "3 when semester = \'Summer\' "+
         "then 2 when semester = \'Spring\' then 1 end Num "+
         "from takes natural join FL where year = FY; ");
         statement.executeUpdate(Q);
         
         Q = ("create temporary table LYSemesters "+ 
         "select id, case when semester = \'Fall\' then 3 when "+
         "semester = \'Summer\' then 2 when semester "+
         "= \'Spring\' then 1 end Num " +
         "from takes natural join FL where year = LY; ");
         statement.executeUpdate(Q);
         
         Q = ("select id, name, concat(case when min(First.Num) "+
         "= 1 then 'Spring' when min(First.Num) = 2 "+
         "then 'Summer' when min(First.Num) = 3 then 'Fall' end, "+
         "' ', cast(FY as char(4))) FirstSemester, "+
         "concat(case when max(Last.Num) = 1 then 'Spring' "+
         "when max(Last.Num) = 2 " +
         "then 'Summer' when max(Last.Num) = 3 then 'Fall' "+
         "end, ' ', cast(LY as char(4))) LastSemester "+
         "from FYSemesters First join FL using (id) join LYSemesters "+
         "Last using (id) join student using (id) "+
         "group by id");
         resultSet = statement.executeQuery(Q);
    }



   //printFirstLastSemester
   //Prints the student information returned from SQL query with some formating
    public void printFirstLastSemester() throws IOException, SQLException
    {
       System.out.println("******** Query 6 ********");
       System.out.println("ID      | Name      | First Semester | Last Semester   ");
       System.out.println("--------|-----------|----------------|-----------------");
       System.out.println("-");
       
       while(resultSet.next())
       {
            String ID = resultSet.getString("id");
            ID = String.format("%1$"+7+ "s", ID);
            String N = resultSet.getString("name");
            N = String.format("%1$"+10+ "s", N);
            String First = resultSet.getString("FirstSemester");
            First = String.format("%1$"+15+ "s", First);
            String Last = resultSet.getString("LastSemester");
            Last = String.format("%1$"+15+ "s", Last);
            System.out.println(ID+ " |" +N+" |" +First+" |"+Last); 
       }
       System.out.println();
    }
	
   
   
   
   //findHeadCounts()
   //Calls SQL stored procedure to find the count of students and instructors in user specified department then executes it.
   //Prints the information returned from stored procedure with some formating.
	public void findHeadCounts() throws SQLException
	{
		System.out.println("******** Query 7 ********");
            
      Scanner kb = new Scanner(System.in);
            
      System.out.println("Please enter the department name for the query: ");
      String Department = kb.nextLine();
  		
		CallableStatement StoredPro = conn.prepareCall("{CALL getNumber(?, ?, ?)}");
		
		StoredPro.setString(1, Department);
		StoredPro.registerOutParameter(2, java.sql.Types.VARCHAR);
		StoredPro.registerOutParameter(3, java.sql.Types.VARCHAR);
		
		StoredPro.execute();
		
		String I = StoredPro.getString(2);
		String S = StoredPro.getString(3);
		
		System.out.println(Department+" Department has "+I+" instructors.");
		System.out.println(Department+" Department has "+S+" students.");
      System.out.println();
                  
   }
}
