<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Схема</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/sigma/build/sigma.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/sigma/build/plugins/sigma.parsers.json/sigma.parsers.json.js"></script>

    <style>
        html {
            height: 100%;
        }

        body {
            height: 100%;
        }

        #sigma-container {
            width: 100%;
            height: 100%;
            background-color: white
        }
    </style>
</head>

<body>
<%@ include file="nav.jsp" %>

<div id='sigma-container'></div>

<script>
    s = new sigma({
        graph: ${graph},
        container: document.getElementById('sigma-container'),
        type: 'canvas',
        settings: {
            defaultNodeColor: 'black',
            minEdgeSize: 0.1,
            maxEdgeSize: 2,
            minNodeSize: 1,
            maxNodeSize: 8,
            defaultEdgeType: 'curve'
        }
    });

    s.graph.read(${graph});
    s.refresh();
</script>
</body>
</html>