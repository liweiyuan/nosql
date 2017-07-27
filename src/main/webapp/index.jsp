<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NoSQL Test</title>
</head>
<body>
<%--<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=set">set</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=get">get</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=gets">gets</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=cas">cas</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=add">add</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=replace">replace</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=append">append</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=delete">delete</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=incr">incr</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=decr">decr</a><br/><br/>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=flushAll">flushAll</a><br/><br/>--%>

<h2>Memcached操作</h2>
<a href="<%=contextPath%>/TestXMemcachedClientServlet?method=java">Memcached(Xmemcached)操作</a><br/><br/>
<a href="<%=contextPath%>/xmemcached?method=java">Memcached(whalin_memcached)操作</a><br/><br/>
<a href="<%=contextPath%>/spymemcached?method=java">Memcached(spy_memcached)操作</a><br/><br/>

<h2>Mongodb操作</h2>
<a href="<%=contextPath%>/mongodb">mongodb操作</a><br/><br/>

<h2>Redis操作</h2>
<a href="<%=contextPath%>/redis">redis操作</a><br/><br/>
</body>
</html>