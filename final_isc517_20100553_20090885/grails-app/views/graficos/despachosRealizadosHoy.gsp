<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 12/5/2014
  Time: 5:22 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" defaultCodec="none" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Despachos Realizado Hoy</title>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {

            var data = google.visualization.arrayToDataTable(${datos});

            var options = {
                title: 'Despachos Realizados Hoy'
            };

            var chart = new google.visualization.PieChart(document.getElementById('donut_single'));
            chart.draw(data, options);
        }
    </script>
</head>

<body>
    <div id="donut_single" style="width: 900px; height: 500px;"></div>
</body>
</html>