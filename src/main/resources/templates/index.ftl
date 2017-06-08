<#include "header.ftl">
<#include "nav.ftl">

<!-- Used to list article sin index site -->
<#if articulos??>
    <#if articulos?size!=0>
        <#list articulos as articulo>
            <div class="article-container">
                <div class="row">
                    <div class=" col-md-offset-3 col-md-6">
                        <a href="/ver/articulo/${articulo.getId()}" >
                            <h3 class="article-title">${articulo.getTitulo()}</h3>
                        </a>
                        <#assign cuerpoArticulo = articulo.getCuerpo()>
                        <#if cuerpoArticulo?length &gt; 70>
                            <#assign maxLength = 70>
                        <#else>
                            <#assign maxLength = cuerpoArticulo?length>
                         </#if>
                        <p class="article-preview">${cuerpoArticulo?substring(0, maxLength)}...<a href="/ver/articulo/${articulo.getId()}" >Leer mas</a></p>

                    </div>

                    <div class="row">
                        <div class="col-md-offset-6 col-md-3">
                            <p><b style="">By:</b>: <a href="/usuario/${articulo.getAutor().getId()}">${articulo.getAutor().getNombre()} <i class="fa fa-user"></i></a></p>
                        </div>

                        <#assign articuloEtiqueta = articulo.getEtiquetas()>
                        <#if articuloEtiqueta?size != 0>
                            <div class="col-md-offset-3 col-md-6 article-tags">
                                <p>
                                    Etiqueta(s) <i class="fa fa-tags"></i>:
                                    <#list articuloEtiqueta as etiqueta>
                                        <span style="background-color: #5bc0de;font-size:15px;color:white">${etiqueta.getEtiqueta()}</span>
                                    </#list>
                                </p>
                            </div>
                        </#if>

                    </div>

                </div>

            </div>
             <hr>
         </#list>
     </#if>
</#if>

<script type="text/javascript">
    $(document).ready(function(){
        $('.borrar').click(function () {
            $(this).fadeIn('slow',0.9)
        });
    });
</script>
<#include "footer.ftl">


