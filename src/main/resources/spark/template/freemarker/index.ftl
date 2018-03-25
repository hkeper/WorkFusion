<html>
<body>
<form method="post" action="index" autocomplete="off">
    <div>
        <label for="username" class="login">Username</label>
        <input id="username" name="username" autocomplete="off" type="text"/>
    </div>
    <div>
        <label for="name" class="login">Full name</label>
        <input id="name" name="name" autocomplete="off" type="text"/>
    </div>
    <div>
        <label for="password" class="login">Password</label>
        <input id="password" name="password" autocomplete="off" type="password"/>
    </div>
    <input value="Add User" type="submit" id="submit"/>
</form>
<font color="red" id="status">Status: ${status}</font>
<#list users as user>
<p id="users">${user}</p>
</#list>
</body>
</html>