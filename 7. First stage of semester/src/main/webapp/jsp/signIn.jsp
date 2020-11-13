<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signIn.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal" action="/signIn" method="post">
                <span class="heading">АВТОРИЗАЦИЯ</span>
                <div class="form-group">
                    <input name="email" type="email" class="form-control" placeholder="E-mail">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input name="password" type="password" class="form-control" placeholder="Password">
                    <i class="fa fa-lock"></i>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn-right" value="SignIn">ВХОД</button>
<%--                    <button type="submit" class="btn-left" value="SignUp">РЕГИСТРАЦИЯ</button>--%>
                    <a href="/signUp" class="btn-left">РЕГИСТРАЦИЯ</a>
                </div>
            </form>
        </div>

    </div><!-- /.row -->
</div><!-- /.container -->

<script type="application/javascript">
    console.log("111");
</script>

</body>
</html>
