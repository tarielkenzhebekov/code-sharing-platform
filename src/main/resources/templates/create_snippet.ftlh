<!DOCTYPE HTML>
<html>
<head>
    <title>Create</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <form method="post">
        <label for="code_snippet">Write/Paste your code below:</label><br>
        <textarea id="code_snippet" rows="10" cols="60"></textarea><br>
        <label for="time_restriction">Time restriction:</label>
        <input id="time_restriction" type="text" placeholder="0" /><br>
        <label for="views_restriction">Views restriction:</label>
        <input id="views_restriction" type="text" placeholder="0" /><br>
        <button id="send_snippet" type="submit" onclick="send()">Share</button>
    </form>
    <script>
        function send() {
        let object = {
            "code": document.getElementById("code_snippet").value,
            "time": document.getElementById("time_restriction").value,
            "views": document.getElementById("views_restriction").value
        };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        if (xhr.status == 200) {
          alert("Success!");
        }
    }
    </script>
</body>
</html>