
  <#macro articulo> 
   <div class="container" style="width:1000px; ">
       <div class="article-container">
           <div class="row">
            <div class="col-xs-12">
                <h3 class="article-title">
                    ${articulo.getTitulo()}
                </h3>
                <span style="float:right">${articulo.getFecha()}</span>
                <span style="float:left">${articulo.getAutor().getNombre()}</span>
                <p class="article-preview">
                    {articulo.getCuerpo()}
                </p>
            
                                
            </div>
                <#assign etiquetas = articulo.getEtiquetas()>
                <#if etiquetas?size!=0>
                <div class="col-xs-12 article-tags">
                    <p>
                        Etiqueta(s) <i class="fa fa-tags"></i>:
                        <#list etiquetas as etiqueta>
                            <span>etiqueta  <a class="borrar" href="/etiqueta/${articulo.getId()}/borrar/${etiqueta.getId()}" style="display:none;background:red;color:white;text-decoration:none">X</a></span>
                        </#list>
                    </p>
                </div>
                </#if>
                <#assign comentarios = articulo.getComentarios()>
                <#if comentarios?size!=0>
                   <#list comentarios as comentario>
                    <div>
                        <p style="border:1px solid gray;">${comentario} <a class="borrar" href="/comentarios/borrar/${comentario.getId()}" style="background:red;color:white;text-decoration:none;display:none">X</a></p>
                    </div>
                    
                   </#list>
                   
                    </#if>
                <form action="/agregar/comentario/${articulo.getId()}">
                 <div class="row">
                     <div class="col-md-offset-5 col-md-5">
                        <div class="form-group">
                            <textarea type="text" class="form-control" name="comentario" placeholder="Agrega tu comentario" id="comment" aria-describedby="sizing-addon1" rows="1"></textarea>
                        </div>
                     </div>
                 </div>

                    <div class="row">
                     <div class="col-md-offset-5 col-md-5">
                         <button class="btn btn-lg btn-primary btn-block btn-signin form-control" id="IngresoLog" type="submit" >Agregar</button>
                     </div>
                 </div>          
             </form>
        </div>
       </div>
        
   </div>
</#macro>
  <script type="text/javascript">
    $(document).ready(function(){
        $('.borrar').click(function () {
           $(this).fadeIn('slow',0.9)
        });

    });
  </script>