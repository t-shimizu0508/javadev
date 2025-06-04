package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends DAO {

	public Subject get(String cd, School school) {
	    Subject subject = null;

	    try (Connection con = getConnection();
	         PreparedStatement st = con.prepareStatement(
	             "SELECT cd, name FROM subject WHERE cd = ? AND school_cd = ?")) {

	        st.setString(1, cd);
	        st.setString(2, school.getCd());

	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            subject = new Subject();
	            subject.setCd(rs.getString("cd"));
	            subject.setName(rs.getString("name"));
	            subject.setSchool(school);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return subject;
	}


    // 科目一覧取得
    public List<Subject> filter(School school) {
        List<Subject> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(
                 "SELECT cd, name FROM subject WHERE school_cd = ? ORDER BY cd")) {

            st.setString(1, school.getCd());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setCd(rs.getString("cd"));
                s.setName(rs.getString("name"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

 // 科目保存（更新のみ）
    public boolean save(Subject subject) {
        try (Connection con = getConnection()) {

            String sql = "UPDATE subject SET name = ? WHERE cd = ? AND school_cd = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, subject.getName());
            st.setString(2, subject.getCd());
            st.setString(3, subject.getSchool().getCd());

            int rows = st.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 科目削除
    public boolean delete(Subject subject) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(
                 "DELETE FROM subject WHERE cd = ? AND school_cd = ?")) {

            st.setString(1, subject.getCd());
            st.setString(2, subject.getSchool().getCd());

            int rows = st.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
