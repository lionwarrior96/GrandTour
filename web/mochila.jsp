<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="controlador.*"%>
<%@page import="modelo.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css"/>
        <style>
            .capaClick{
                background: #C9E0DE;
                width: 700px;
                height: 280px;
                border: 5px solid #067774;
                border-style: dashed;
                text-align: center;
                display: table;
            }
            .capaClick h3{
                display: table-cell;
                vertical-align: middle;
            }
            #droppable{
                background: white;
                width: 700px;
                height: 280px;
                border: 5px solid #067774;
                text-align: center;
                display: table;
                cursor: pointer;
                position: relative;
                top:-280px;
                font-size: 26px;
                padding: 120px 0px 0px 0px;
            }
            #droppable:hover,#droppable.hover{
                opacity:0;
            }
            .add{
                margin: 0 auto;
                margin-top: 50px;
                margin-bottom: 50px;
                width: 110px;
            }
            * {
                transition: 0ms;
            }
        </style>
        <!--Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
        <!------------->
        <title>Mochila</title>
    </head>
    <body>
        <!------------------------------------------------------Christian Valera Murillo Design-------------------------------------------------------->
        <section style="position: fixed; width: 100%; z-index: 1;">
            <header class="header">
                <nav class="navbar-light navbar header" style="background-color: #067774;">
                    <div class="btn-menu">
                        <label class="button_menu" for="btn-menu">☰</label>	
                        <a style="color: white;" class="navbar-brand">GrandTour</a>
                    </div>
                    <div style="display:flex;">
                        <c:if test="${user == null}">
                            <a style="margin-right: 20px;" class="btn btn-primary" href="http://localhost:8080/Turismo/login.jsp" role="button">INICIAR SESIÓN</a>
                        </c:if>
                        <c:if test="${user != null}">
                            <a style="margin-right: 20px;" class="btn btn-primary" href="http://localhost:8080/Turismo/Servlet_CloseSession" role="button">CERRAR SESIÓN</a>
                            <div class="cuenta">USUARIO: ${user.getNombre()}</div>
                        </c:if>
                    </div>
                </nav>
            </header>
            <div class="capa"></div>
            <input type="checkbox" id="btn-menu">
            <div class="container-menu">
                <div class="cont-menu">
                    <nav>
                        <a href="http://localhost:8080/Turismo/inicio.jsp" class="nav-main__link-item"><i class="fas fa-user-cog"></i>INICIO</a>
                        <a href="http://localhost:8080/Turismo/Servlet_Filter" class="nav-main__link-item"><i class="fas fa-shopping-cart"></i>PAQUETES</a>
                        <c:if test="${user != null}">
                            <a href="http://localhost:8080/Turismo/Servlet_LoadBags" class="nav-main__link-item"><i class="fas fa-shopping-bag"></i>MOCHILA</a>
                            <a href="#" class="nav-main__link-item"><i class="fas fa-exclamation-circle"></i>MI CUENTA</a>
                        </c:if>
                    </nav>
                    <label for="btn-menu">✖️</label>
                </div>
            </div>
        </section>
        <!--------------------------------------------------------------------------------------------------------------------------------------------->
        <section style="padding: 90px 30px 0px 30px;">
            <div style="display:flex;">
                <div style="width: 520px; height: 650px; padding: 8px; border: 5px solid #067774; margin-right: 20px; overflow-y:scroll;">
                    <c:forEach var="bgitem" items="${bgitems}">
                        <!--Paquete-->
                        <div style="display:flex; cursor: move;" id="draggable" draggable="true">
                            <div class="card text-black" style="margin-bottom: 5px; height: 230px; width: 410px">
                                <img style="opacity: 0.5" class="card-img" src="${bgitem.getImg()}" height="230px" alt="Card image">
                                <div class="card-img-overlay">
                                    <h5 class="card-title">${bgitem.getName()}</h5>
                                    <p style="font-size: 13px;" class="card-text"><b>${bgitem.getDescription()}</b></p>
                                    <p class="card-text">Costo: S/ ${bgitem.getCost()}</p>
                                </div>
                            </div>
                            <form action="Servlet_DeleteFromBag" method="post">
                                <input type="hidden" name="bgcodpack" value="${bgitem.getCodigo()}">
                                <input style="margin: 90px 0px 0px 15px; padding: 8px 20px 8px 20px;" class="btn btn-danger textoprod" type="submit" value="[]">
                            </form>
                        </div>
                        <!----------->
                    </c:forEach>
                </div>
                <section style="width: 920px; height: 650px; overflow-y:scroll; padding: 40px; border: 5px solid #067774;">
                    <c:forEach var="bslist" items="${bslists}">
                        <!--Lista-->
                        <div style="display:flex">
                            <div style="width: 700px; height: 280px; margin-bottom: 20px;">
                                <div class="capaClick"><h3>SELECCIONAR</h3></div>
                                <form id="droppable" action="" method="post">${bslist.getBname()}</form>
                            </div>
                            <div>
                                <form action="Servlet_DeleteFromSBag" method="post">
                                    <input type="hidden" name="codbs" value="${bslist.getCodbs()}">
                                    <input style="margin: 110px 0px 0px 15px; padding: 8px 20px 8px 20px;" class="btn btn-danger textoprod" type="submit" value="ELIMINAR">
                                </form>
                            </div>
                        </div>
                        <!--------->
                    </c:forEach>
                    <div style="width: 100%; height: 2px; margin: 0 auto; margin-top: 20px; background: gray;"></div>
                    <!--Añadir Lista-->
                    <div class="add">
                        <button style="padding: 8px 20px 8px 20px; width: 170px;" class="btn btn-warning textoprod" onclick="add()">NUEVA LISTA</button>
                    </div>
                    <div style="display: none; width: 100%; height: 200px; border: 5px solid #067774; padding: 20px;" id="addnew">
                        <div style="display: flex">
                            <h3>Ingrese Nombre de Lista</h3>
                            <button style="margin-left: auto; margin-right: 0;" class="btn btn-danger" onclick="closeadd()">✖</button>
                        </div>
                        <form action="Servlet_AddToSBag" method="post">
                            <input style="margin-top: 15px;" type="text" name="bname" size="90%" autocomplete="off" required><br>
                            <input style="padding: 8px 20px 8px 20px; margin-top: 20px;" class="btn btn-primary textoprod" type="submit" value="CREAR LISTA">
                        </form>
                    </div>
                    <!---------------->
                </section>
            </div>
        </section>
        <div class="sticky-ref">Christian Valera Murillo Design</div>
        
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
        <script>
            function add(){
                var n = document.getElementById("addnew");
                n.style.display = "block";
            };
            function closeadd(){
                var n = document.getElementById("addnew");
                n.style.display = "none";
            };
            //Drag and Drop Functions=====================================
            $(document).on('dragstart','#draggable',function(e){
                //alert("hola");
            });
            $(document).on('dragover', false);
            $(document).on('drop','#droppable',function(e){
                $(this).submit();
                $(this).removeClass('hover');
            });
            $(document).on('dragenter','#droppable',function(e){
                e.preventDefault();
                $(this).addClass('hover');
            });
            $(document).on('dragleave','#droppable',function(e){
                $(this).removeClass('hover');
            });
            //============================================================
        </script>
    </body>
</html>
