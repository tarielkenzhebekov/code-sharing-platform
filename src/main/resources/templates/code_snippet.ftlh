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
    <span id="load_date"><code>${snippet.date}</code></span>
    <#if snippet.views gt 0>
        <span id="views_restriction">
            <u>${snippet.views}</u> more views allowed
        </span>
    </#if>
    <pre id="code_snippet"><code>${snippet.code}</code></pre>
</body>
</html>