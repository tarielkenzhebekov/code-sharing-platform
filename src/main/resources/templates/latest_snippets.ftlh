<!DOCTYPE HTML>
<html>
<head>
    <title>Latest</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet"
    href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <#list latest_snippets as snippet>
        <div>
            <span id="load_date" style="color:green">${snippet.date}</span>
            <pre id="code_snippet"><code>${snippet.code}</code></pre>
        </div>
    </#list>
</body>
</html>