<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
    <h1>Регистрация</h1>
    <div
              class="flash flash-full flash-error"
              style="display:${visibilityError != null ? visibilityError : 'none'};"
            >
              <div aria-atomic="true" role="alert">
                ${errorText != null ? errorText : ""}
              </div>
            </div>
    <form action="./signup" method="post" charset = "UTF-8">
        <label for="username">Логин:</label>
        <input type="text" id="login_field" name="login" required><br>

        <label for="password">Пароль:</label>
        <input type="password" id="password_field" name="password" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email_field" name="email" required><br>

        <input type="submit" value="Зарегистрироваться">
    </form>
    <p class="login-callout">
              Already have an account?
              <a href="./login">Sign in</a>
            </p>
</body>
</html>