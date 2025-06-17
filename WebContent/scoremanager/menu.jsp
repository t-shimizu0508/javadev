<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<div class="main-wrapper">
	<%@ include file="index.jsp"%>

	<div class="menu-container_2">
		<h2>メニュー</h2>
		<ul>
			<li><a
				href="<%= request.getContextPath() %>/scoremanager/menu.jsp">メニュー</a></li>
			<li><a
				href="<%= request.getContextPath() %>/scoremanager.main.StudentList.action">学生管理</a></li>
			<li>成績管理
				<ul class="sub-menu">
					<li><a
						href="<%= request.getContextPath() %>/scoremanager.main.TestRegist.action">成績登録</a></li>
					<li><a
						href="<%= request.getContextPath() %>/scoremanager/test_list.jsp">成績参照</a></li>
				</ul>
			</li>
			<li><a
				href="<%= request.getContextPath() %>/scoremanager.main.SubjectList.action">科目管理</a></li>
		</ul>
	</div>
</div>

<%@ include file="../footer.jsp"%>
