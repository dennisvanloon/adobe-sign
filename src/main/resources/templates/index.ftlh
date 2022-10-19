<#import "/spring.ftl" as spring/>
<html>
<body>
Hello there, you currently have ${contracts?size} contracts

<form name="upload" enctype="multipart/form-data" action="/contract" method="POST">
    <p>
    <div><input id="file" type="file" name="file"></div>
    </p>
    <p>
        <input type="submit" value="Upload file">
    </p>
</form>
</body>
</html>