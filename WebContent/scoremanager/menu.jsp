<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<div class="main-wrapper">
	<%@ include file="index.jsp"%>

	<!-- ここからメニュー全体 -->
	<div class="menu-area">
		<h2 class="menu-title">メニュー</h2>

		<div class="menu-button-wrapper">
			<a href="<%= request.getContextPath() %>/scoremanager.main.StudentList.action"
			   class="menu-button menu-red">
				学生管理
			</a>

			<div class="menu-button menu-green">
				成績管理
				<div class="menu-sub-links">
					<a href="<%= request.getContextPath() %>/scoremanager.main.TestRegist.action">成績登録</a>
					<a href="<%= request.getContextPath() %>/scoremanager.main.TestList.action">成績参照</a>
				</div>
			</div>

			<a href="<%= request.getContextPath() %>/scoremanager.main.SubjectList.action"
			   class="menu-button menu-purple">
				科目管理
			</a>
		</div>
	</div>
</div>

<%@ include file="../footer.jsp"%>
