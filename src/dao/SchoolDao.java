package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends DAO{
	public School get(String cd)
			throws Exception{
		School school=null;
		Connection con=getConnection();

		PreparedStatement st;
		st=con.prepareStatement(
			"select * from School where cd=?" );
		st.setString(1, cd);
		ResultSet rs=st.executeQuery();

		if (rs.next()) {
			school=new School();
			school.setCd(rs.getString("cd"));
		}

		st.close();
		con.close();
		return	school;
	}
}
