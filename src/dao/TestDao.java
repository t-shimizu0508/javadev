package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {

    // SQLのベース構文（条件付きで拡張）
    String baseSql = "SELECT * FROM test WHERE 1=1";

    public List<Test> filter(String entYear, String classNum, Subject subject, int no, School school) {
        List<Test> list = new ArrayList<>();

        String sql = ""
          + "SELECT t.no, t.point, t.class_num, "
          + "       stu.no   AS student_no, "
          + "       stu.name AS student_name, "
          + "       stu.ent_year "
          + "FROM test t "
          + "  JOIN student stu "
          + "    ON t.student_no = stu.no "
          + "   AND stu.ent_year = ? "
          + "WHERE t.school_cd = ? "
          + "  AND t.subject_cd = ? "
          + "  AND t.no = ? "
          + "  AND t.class_num = ? "
          + "ORDER BY stu.no";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, entYear);
            st.setString(2, school.getCd());
            st.setString(3, subject.getCd());
            st.setInt   (4, no);
            st.setString(5, classNum);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test t = new Test();

                    t.setNo(rs.getInt("no"));
                    int p = rs.getInt("point");
                    if (rs.wasNull()) {
                        p = 0;
                    }
                    t.setPoint(p);

                    t.setClassNum(rs.getString("class_num"));

                    Student stu = new Student();
                    stu.setNo(rs.getString("student_no"));
                    stu.setName(rs.getString("student_name"));
                    int entYearValue = rs.getInt("ent_year");
                    stu.setEntYear(entYearValue);
                    t.setStudent(stu);

                    Subject sub = new Subject();
                    sub.setCd(subject.getCd());
                    t.setSubject(sub);

                    t.setSchool(school);

                    list.add(t);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



    // 1件取得
    public Test get(Student student, Subject subject, School school, int no) {
        Test t = null;

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(
                "SELECT * FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?")) {

            st.setString(1, student.getNo());
            st.setString(2, subject.getCd());
            st.setString(3, school.getCd());
            st.setInt(4, no);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                t = new Test();
                t.setNo(rs.getInt("no"));
                t.setPoint(rs.getInt("point"));
                t.setClassNum(rs.getString("class_num"));
                // setStudent, setSubject, setSchool が必要ならここでセット
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    // postFilter：結果セットを加工する（今回は学校で絞る例）
    public List<Test> postFilter(ResultSet rs, School school) throws Exception {
        List<Test> list = new ArrayList<>();

        while (rs.next()) {
            if (rs.getString("school_cd").equals(school.getCd())) {
                Test t = new Test();
                t.setNo(rs.getInt("no"));
                t.setPoint(rs.getInt("point"));
                t.setClassNum(rs.getString("class_num"));
                list.add(t);
            }
        }

        return list;
    }

    // テスト情報を複数保存
    public boolean save(List<Test> tests) {
        try (Connection con = getConnection()) {
            for (Test t : tests) {
                if (!save(t, con)) return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // テスト1件を保存（外部Connection受け取り）
    public boolean save(Test test, Connection con) {
        try {
            // 既存チェック用SQL
            String checkSql = "SELECT COUNT(*) FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
            try (PreparedStatement checkSt = con.prepareStatement(checkSql)) {
                checkSt.setString(1, test.getStudent().getNo());
                checkSt.setString(2, test.getSubject().getCd());
                checkSt.setString(3, test.getSchool().getCd());
                checkSt.setInt(4, test.getNo());

                ResultSet rs = checkSt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    // すでに存在 → UPDATE
                    try (PreparedStatement updateSt = con.prepareStatement(
                            "UPDATE test SET point = ?, class_num = ? " +
                            "WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?")) {
                        updateSt.setInt(1, test.getPoint());
                        updateSt.setString(2, test.getClassNum());
                        updateSt.setString(3, test.getStudent().getNo());
                        updateSt.setString(4, test.getSubject().getCd());
                        updateSt.setString(5, test.getSchool().getCd());
                        updateSt.setInt(6, test.getNo());

                        return updateSt.executeUpdate() > 0;
                    }

                } else {
                    // 存在しない → INSERT
                    try (PreparedStatement insertSt = con.prepareStatement(
                            "INSERT INTO test (student_no, subject_cd, school_cd, no, point, class_num) " +
                            "VALUES (?, ?, ?, ?, ?, ?)")) {

                        insertSt.setString(1, test.getStudent().getNo());
                        insertSt.setString(2, test.getSubject().getCd());
                        insertSt.setString(3, test.getSchool().getCd());
                        insertSt.setInt(4, test.getNo());
                        insertSt.setInt(5, test.getPoint());
                        insertSt.setString(6, test.getClassNum());

                        return insertSt.executeUpdate() > 0;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
