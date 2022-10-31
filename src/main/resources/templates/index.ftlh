<#import "/spring.ftl" as spring/>
<html lang="en">
    <body>
        <div>Hello there, you currently have ${contracts?size} contracts</div>

        <div>
            <form name="upload" enctype="multipart/form-data" action="/contract" method="POST">
                <input id="file" type="file" name="file">
                <input id="signerOne" type="text" name="signerOne">
                <input id="signerTwo" type="text" name="signerTwo">
                <input type="submit" value="Upload file">
            </form>
        </div>

        <div>
            <table>
                <tr>
                    <th>id</th>
                    <th>fileName</th>
                    <th>fileType</th>
                    <th>signerOne</th>
                    <th>signerTwo</th>
                    <th>download</th>
                </tr>
                <#foreach contract in contracts>
                <tr>
                    <td>${contract.id}</td>
                    <td>${contract.fileName}</td>
                    <td>${contract.fileType}</td>
                    <td>${contract.signerOne}</td>
                    <td>${contract.signerTwo}</td>
                    <td><a href="download/${contract.id}">download</a></td>
                </tr>
                </#foreach>
            </table>
        </div>
        <div>Message from the config: ${message}</div>
    </body>
</html>