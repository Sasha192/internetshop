<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<a href="users">USERS</a><br>
<a href="index">HOME</a>
<p>______________________________________</p>
<form action="register" method="post">
    <div>
        <h3>Let's create a new User!</h3>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="name"><b>Name</b></label>
        <input type="text" placeholder="Enter name" name="name" id="name" required>

        <label for="surname"><b>Surname</b></label>
        <input type="text" placeholder="Enter surname" name="surname" id="surname" required>

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter login" name="login" id="login" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" id="psw" required>

        <label for="psw-repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
        <hr>

        <button type="submit">Register</button>
    </div>
</form>
</body>
</html>
