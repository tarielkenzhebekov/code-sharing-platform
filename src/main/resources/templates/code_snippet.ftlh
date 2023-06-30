<!DOCTYPE HTML>
<html>
<head>
    <title>Code</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date" style="color:green">${snippet.date}</span><br>
    <#if !time_hidden>
        <span id="time_restriction">
            The code will be available for <u>${snippet.time}</u> seconds
        </span><br>
    </#if>
    <#if !views_hidden>
        <span id="views_restriction">
            <u>${snippet.views}</u> more views allowed
        </span>
    </#if>
    <pre id="code_snippet"><code>${snippet.code}</code></pre>
</body>
</html>