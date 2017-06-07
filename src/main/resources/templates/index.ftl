<#include "header.ftl">
<#include "nav.ftl">

<#macro article_list articulos> <!-- Used to list article sin index site -->
<#if articulos?size!=0>
<#list articulos as articulo>
<div class="article-container">
    <div class="row">
        <div class="col-xs-12">
            <a href="/ver/articulo/${articulo.getId()}" >
                <h3 class="article-title">${articulo.getTitulo()}</h3>
            </a>
            <#assign cuerpoArticulo = articulo.getCuerpo()>
            <#if cuerpoArticulo?length &gt; 70>
                <#assign maxLength = 70>
            <#else>
                <#assign maxLength = cuerpoArticulo?length>
             </#if>
            <p class="article-preview">${cuerpoArticulo?substring(0, maxLength)}</p>
        </div>
        <div class="col-xs-8">
            <p><b>Autor</b>: <a href="/usuario/${articulo.getAutor().getId()}">${articulo.getAutor()} <i class="fa fa-user"></i></a></p>
        </div>
        <div class="col-xs-4 text-right">
            <a href="/ver/articulo/${articulo.getId()}" >Leer mas</a>
        </div>
        <#assign articuloEtiqueta = articulo.getEtiquetas()>
        <#if articuloEtiqueta?size != 0>
            <div class="col-xs-12 article-tags">
                <p>
                    Etiqueta(s) <i class="fa fa-tags"></i>:
                    <#list articuloEtiqueta as etiqueta>
                        <span>etiqueta  <a class ="borrar" href="/etiqueta/${articulo.getId()}/borrar/${etiqueta.getId()}" style="background:red;color:white;text-decoration:none;display:none">X</a></span>
                    </#list>
                </p>
            </div>
        </#if>
    </div>
    
</div>
 <form action="/agregar/${articulo.getId()}/comentario/">
     <div class="row">
         <div class="col-md-offset-5 col-md-5">
            <div class=" form-group">        
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
 </#list>
 </#if>
</#macro>

<script type="text/javascript">
    $(document).ready(function(){
        $('.borrar').click(function () {
            $(this).fadeIn('slow',0.9)
        });
    });
</script>
<#include "footer.ftl">


