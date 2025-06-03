package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends DAO {
	 private final String baseSql = "SELECT * FROM student";

	 public Student get(String no) {
		    Student student = null;

		    try (Connection con = getConnection();
		         PreparedStatement st = con.prepareStatement("SELECT * FROM student WHERE no = ?")) {

		        st.setString(1, no);
		        ResultSet rs = st.executeQuery();

		        if (rs.next()) {
		            student = new Student();
		            student.setNo(rs.getString("no"));
		            student.setName(rs.getString("name"));
		            student.setClassNum(rs.getString("class_Num"));
		            student.setEntYear(rs.getInt("ent_Year"));
		            student.setAttend(rs.getBoolean("is_Attend"));
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return student;
		}


	    private List<Student> postFilter(ResultSet rs, School school) throws SQLException {
	        List<Student> list = new ArrayList<>();
	        while (rs.next()) {
	            Student s = new Student();
	            s.setNo(rs.getString("no"));
	            s.setName(rs.getString("name"));
	            s.setClassNum(rs.getString("class_Num"));
	            s.setEntYear(rs.getInt("ent_Year"));
	            s.setAttend(rs.getBoolean("is_Attend"));
	            s.setSchool(school);
	            list.add(s);
	        }
	        return list;
	    }

	    public List<Student> filter(School school) {
	        List<Student> list = new ArrayList<>();

	        try (Connection con = getConnection();
	             PreparedStatement st = con.prepareStatement(baseSql + " WHERE school_cd=?")) {

	            st.setString(1, school.getCd());
	            ResultSet rs = st.executeQuery();
	            list = postFilter(rs, school);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }



	    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) {
	        List<Student> list = new ArrayList<>();

	        try (Connection con = getConnection();
	             PreparedStatement st = con.prepareStatement(
	                 baseSql + " WHERE school_cd=? AND ent_Year=? AND class_Num=? AND is_Attend=?")) {

	        	st.setString(1, school.getCd());
	            st.setInt(2, entYear);
	            st.setString(3, classNum);
	            st.setBoolean(4, isAttend);

	            ResultSet rs = st.executeQuery();
	            list = postFilter(rs, school);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }

	    public List<Student> filter(School school, int entYear, String classNum) {
	        List<Student> list = new ArrayList<>();

	        try (Connection con = getConnection();
	             PreparedStatement st = con.prepareStatement(
	                 baseSql + " WHERE school_cd=? AND ent_Year=? AND class_Num=?")) {

	        	st.setString(1, school.getCd());
	            st.setInt(2, entYear);
	            st.setString(3, classNum);

	            ResultSet rs = st.executeQuery();
	            list = postFilter(rs, school);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }

	    public List<Student> filter(School school, int entYear, boolean isAttend) {
	        List<Student> list = new ArrayList<>();

	        try (Connection con = getConnection();
	             PreparedStatement st = con.prepareStatement(
	                 baseSql + " WHERE school_cd=? AND ent_Year=? AND is_Attend=?")) {

	        	st.setString(1, school.getCd());
	            st.setInt(2, entYear);
	            st.setBoolean(3, isAttend);

	            ResultSet rs = st.executeQuery();
	            list = postFilter(rs, school);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }

	    public List<Student> filter(School school, int entYear) {
	        List<Student> list = new ArrayList<>();

	        try (Connection con = getConnection();
	             PreparedStatement st = con.prepareStatement(
	                 baseSql + " WHERE school_cd=? AND ent_Year=?")) {

	            st.setString(1, school.getCd());
	            st.setInt(2, entYear);

	            ResultSet rs = st.executeQuery();
	            list = postFilter(rs, school);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }

	    public List<Student> filter(School school, boolean isAttend) {
	        List<Student> list = new ArrayList<>();

	        try (Connection con = getConnection();
	             PreparedStatement st = con.prepareStatement(
	                 baseSql + " WHERE school_cd=? AND is_Attend=?")) {

	        	st.setString(1, school.getCd());
	            st.setBoolean(2, isAttend);

	            ResultSet rs = st.executeQuery();
	            list = postFilter(rs, school);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }

	    public boolean save(Student student) {
	        try (Connection con = getConnection();
	             PreparedStatement st = con.prepareStatement(
	                 "INSERT INTO student (no, name, classNum, entYear, isAttend, school) VALUES (?, ?, ?, ?, ?, ?)")) {

	            st.setString(1, student.getNo());
	            st.setString(2, student.getName());
	            st.setString(3, student.getClassNum());
	            st.setInt(4, student.getEntYear());
	            st.setBoolean(5, student.isAttend());

	            int rows = st.executeUpdate();
	            return rows > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
