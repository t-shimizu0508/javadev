<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.Student" %>
<%@ page import="java.util.*, bean.Student" %>
 <%@ include file="../header.jsp" %>
 <%@ include file="index.jsp" %>
 <h2>学生管理</h2>
    <form action="/student/list" method="get">
        入学年度：
        <select name="entYear">
            <option value="">----</option>
            <%
                for (int y = 2020; y <= 2025; y++) {
                    out.println("<option value='" + y + "'>" + y + "</option>");
                }
            %>
        </select>
        クラス：
        <select name="classNum">
            <option value="">----</option>
            <option value="201">201</option>
            <option value="202">202</option>
        </select>
        在学中：<input type="checkbox" name="isAttend" value="true" />
        <input type="submit" value="絞り込み" />
    </form>

    <hr>

    <table border="1">
        <tr>
            <th>入学年度</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>クラス</th>
            <th>在学中</th>
        </tr>
        <%
            List<Student> list = (List<Student>) request.getAttribute("studentList");
            if (list != null && !list.isEmpty()) {
                for (Student s : list) {
        %>
        <tr>
            <td><%= s.getEntYear() %></td>
            <td><%= s.getNo() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getClassNum() %></td>
            <td><%= s.isAttend() ? "○" : "×" %></td>
            <td><a href="/student/edit?no=<%= s.getNo() %>">変更</a></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="6">該当する学生がいません。</td></tr>
        <%
            }
        %>
    </table>
 <%@ include file="../footer.jsp" %>