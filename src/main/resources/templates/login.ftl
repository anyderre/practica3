<#include "header.ftl">

<div class="container">

    <div class="Icon">
        <!--Icono de usuario-->
        <span class="glyphicon glyphicon-user"></span>
    </div>
    <div class="caption">
        <h3>Login</h3>
    </div>

    <form action="/login" method="post">

        <div class="input-group input-group-lg">
            <span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-envelope"></i></span>
            <input type="email" class="form-control" name="correo" placeholder="Entre su correo" id="Correo" aria-describedby="sizing-addon1" required>
        </div>
        <br>
        <div class="input-group input-group-lg">
            <span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-lock"></i></span>
            <input type="password" name="contra" class="form-control" placeholder="******" aria-describedby="sizing-addon1" required>
        </div>
        <br>
        <button class="btn btn-lg btn-primary btn-block btn-signin" id="IngresoLog" type="submit">Entrar</button>
        <div class="opcioncontra"><a href="">Olvidaste tu contraseña?</a></div>
    </form>
</div>
<#include "footer.ftl">