<%@ page import="com.google.gson.Gson" %>
<%@ page import="tiktok.service.Extractor" %>
<%@ page import="tiktok.service.Collector" %>
<%@ page import="tiktok.service.UrlGenerator" %>
<%@ page import="tiktok.service.DataAnalyser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
</head>
<body>

<p>Your results here: </p>
<%
    String keyword = request.getParameter("keyword");
    Gson gson = new Gson();
    Extractor extractor = new Extractor(gson);
    Collector collector = new Collector();
    UrlGenerator urlGenerator = new UrlGenerator();
    DataAnalyser analyser = new DataAnalyser(collector, extractor, urlGenerator);

    try {
        out.print(analyser.showResults(keyword));
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
%>

</body>
</html>
