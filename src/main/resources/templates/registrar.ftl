<#include "header.ftl">

  <include "nav.ftl">
   
    <div class="container">
    
    <div class="caption">
        <h3 style="text-align:center;">Registrar</h3>
    </div>
     <br>
    <form action="/login" method="post">
        
        <div class="row">
          <div class="col-md-offset-4 col-md-5">              
        <div class="input-group input-group-md form-group">
            <span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-user"></i></span>
            <input type="text" class="form-control" name="correo" placeholder="Entre su Nombre" id="nombre" aria-describedby="sizing-addon1" required>
        </div> 
          </div> 
            
        </div>
        <br>
           <div class="row">
          <div class="col-md-offset-4 col-md-5">              
        <div class="input-group input-group-md form-group">
            <span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-user"></i></span>
            <input type="text" class="form-control" name="correo" placeholder="Entre su apellido" id="apellido" aria-describedby="sizing-addon1" required>
        </div> 
          </div> 
            
        </div>
        <br>
          <div class="row">
          <div class="col-md-offset-4 col-md-5">              
        <div class="input-group input-group-md form-group">
            <span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-user"></i></span>
            <input type="text" class="form-control" name="correo" placeholder="Nombre de usuario" id="Correo" aria-describedby="sizing-addon1" required>
        </div> 
          </div> 
            
        </div>
        <br>
        <div class="row">
            <div class="col-md-offset-4 col-md-5">                  
                <div class="input-group input-group-md form-group">
                    <span class="input-group-addon" id="sizing-addon1"><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" name="contra" class="form-control" placeholder="******" aria-describedby="sizing-addon1" required>
                </div>
            </div>
        </div>
      <div class="row">
          <div class="col-md-offset-4 col-md-5 form-group">
                <button class="btn btn-lg btn-primary btn-block btn-signin form-control" id="IngresoLog" type="submit" >Registrar</button>
                
          </div>
      </div>
        <br>
      
    </form>
    </div>
   

    
    
    
<#include "footer.ftl">