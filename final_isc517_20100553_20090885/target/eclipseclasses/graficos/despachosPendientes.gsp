<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 12/5/2014
  Time: 5:11 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Despachos Pendientes</title>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {

            var data = google.visualization.arrayToDataTable(${datos});

            var options = {
                pieHole: 0.5,
                pieSliceTextStyle: {
                    color: 'black'
                },
                legend: 'none'
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