package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends DAO {

    // 学年のリストを取得
    public List<String> get(String class_Num, School school) {
        List<String> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(
                 "SELECT DISTINCT class_Num FROM class WHERE class_Num = ? AND school_cd = ?")) {

            st.setString(1, class_Num);
            st.setString(2, school.getCd());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("class_Num"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 学校に関連するすべてのクラス番号を取得
    public List<String> filter(School school) {
        List<String> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(
                 "SELECT DISTINCT class_Num FROM class WHERE school_cd = ?")) {

            st.setString(1, school.getCd());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("class_Num"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean save(ClassNum class_Num, String newClassNum) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(
                 "UPDATE class SET class_Num = ? WHERE class_Num = ? AND school_cd = ?")) {

            st.setString(1, newClassNum);
            st.setString(2, class_Num.getClass_num());
            st.setString(3, class_Num.getSchool().getCd());

            int updated = st.executeUpdate();
            return updated > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
