<!DOCTYPE HTML>
<html>
<head>
    <title>Latest</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <#list latest_codes as latest_code>
        <div>
            <span id="load_date">${latest_code.date}</span>
            <pre id="code_snippet">${latest_code.code}</pre>
        </div>
    </#list>
</body>
</html>