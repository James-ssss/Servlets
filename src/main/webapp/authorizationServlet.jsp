<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
</head>
<body>
    <h1>Авторизация</h1>
    <div
              class="flash flash-full flash-error"
              style="display:${visibilityError != null ? visibilityError : 'none'};"
            >
              <div aria-atomic="true" role="alert">
                ${errorText != null ? errorText : ""}
              </div>
            </div>
    <form action="./login" method="post" charset = "UTF-8">
      <label for="login_field">Логин:</label>
      <input type="text" id="login_field" name="login"><br><br>
      <label for="password">Пароль:</label>
      <input type="password" id="password" name="password"><br><br>
      <input type="submit" value="Войти">
    </form>
    <p class="login-callout">
        <a href="./signup">Create an account</a>
    </p>
</body>
</html>