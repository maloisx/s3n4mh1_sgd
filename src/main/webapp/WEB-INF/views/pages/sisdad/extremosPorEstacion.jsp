<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div >
    <div >
        <div >
            <c:choose>
                <c:when test="${accion=='ATENDIDO'}">
                    <div class="alert alert-success">
                        <p class="text-center">SOLICITUD ATENDIDA</p>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 text-center">
                            <form action="../exec.do" method="post">
                                <input type="hidden" name="accion" value="CONTINUAR_SIN_REGISTRAR"/>
                                <input type="submit" class="btn btn-default" value="Home"/>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:when test="${accion=='NOTHING'}">
                    <form action="../exec.do" method="post">
                        <input type="hidden" name="accion" value="CONSULTAR"/>
                        <div class="row">
                            <div class="col-sm-2 text-center">&nbsp;</div>
                            <div class="col-sm-4 form-group">
                                <label for="codigo_estacion">Estacion:</label>
                                <select name="codigo_estacion" required>
                                    <c:if test="${listEsta != null}">
                                        <option value="">&nbsp;</option>
                                        <c:forEach var = "f" items="${listEsta}">
                                            <option value="${f.codigo}">${f.nombre}&nbsp;&#8212;&nbsp;&#171;${f.codigo}&#187;</option>
                                        </c:forEach>
                                    </c:if>                                
                                </select>                            
                            </div>
                            <div class="col-sm-4 form-group">
                                <label for="codigo_variable">Variable:</label>
                                <select name="codigo_variable" required>
                                    <c:if test="${listVariable != null}">
                                        <option value="">&nbsp;</option>
                                        <c:forEach var = "f" items="${listVariable}">
                                            <option value="${f.codigo}">${f.nombre}&nbsp;&#8212;&nbsp;&#171;${f.codigo}&#187;</option>
                                        </c:forEach>
                                    </c:if>                                
                                </select>                            
                            </div>
                            <div class="col-sm-2 text-center">&nbsp;</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12 form-group text-center">
                                <input type="submit" class="btn btn-default" value="Consultar"/>
                            </div>
                        </div>
                    </form>
                </c:when>
                <c:when test="${accion=='NO_REGISTRADO'}">
                    <div class="alert alert-success">
                        <p class="text-center">La variable ${cod_var}, no esta registrada o asociada a la estacion ${cod_esta}. ¿Desea Registrarlo?</p>
                    </div>
                    <div class="row">
                        <div class="col-sm-3 text-center">&nbsp;</div>
                        <div class="col-sm-3 text-center">
                            <form action="../exec.do" method="post">
                                <input type="hidden" name="accion" value="ELIMINAR_Y_REGISTRAR"/>
                                <input type="hidden" name="codigo_estacion" value="${cod_esta}">
                                <input type="hidden" name="codigo_variable" value="${cod_var}">
                                <input type="submit" class="btn btn-default" value="Si"/>
                            </form>
                        </div>
                        <div class="col-sm-3 text-center">
                            <form action="../exec.do" method="post">
                                <input type="hidden" name="accion" value="CONTINUAR_SIN_REGISTRAR"/>
                                <input type="submit" class="btn btn-default" value="No"/>
                            </form>
                        </div>
                        <div class="col-sm-3 text-center">&nbsp;</div>
                    </div>
                </c:when>
                <c:when test="${accion=='SI_REGISTRADO'}">
                    <div class="alert alert-success">
                        <p class="text-center">Umbrales de la variable ${cod_var}, para la estacion ${cod_esta}.</p>
                    </div>
                    <div>
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th class="text-center h5">COD_ESTA</th>
                                    <th class="text-center h5">COD_VAR</th>
                                    <th class="text-center h5">N_R1</th>
                                    <th class="text-center h5">N_R2</th>
                                    <th class="text-center h5">FLAG</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${listUE != null}">
                                    <c:forEach var = "f" items="${listUE}">
                                        <tr>
                                            <th class="text-center h6">${cod_esta}</th>
                                            <th class="text-center h6">${cod_var}</th>
                                            <th class="text-center h6">${f.nr1}</th>
                                            <th class="text-center h6">${f.nr2}</th>
                                            <th class="text-center h6">${f.codFlagData}</th>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-sm-4 text-center">
                            <form action="../exec.do" method="post">
                                <input type="hidden" name="accion" value="ELIMINAR"/>
                                <input type="submit" class="btn btn-default" value="Eliminar"/>
                            </form>
                        </div>
                        <div class="col-sm-4 text-center">
                            <form action="../exec.do" method="post">
                                <input type="hidden" name="accion" value="ELIMINAR_Y_REGISTRAR"/>
                                <input type="hidden" name="codigo_estacion" value="${cod_esta}">
                                <input type="hidden" name="codigo_variable" value="${cod_var}">
                                <input type="submit" class="btn btn-default" value="Eliminar y registrar nuevamente"/>
                            </form>
                        </div>
                        <div class="col-sm-4 text-center">
                            <form action="../exec.do" method="post">
                                <input type="hidden" name="accion" value="CONTINUAR_SIN_REGISTRAR"/>
                                <input type="submit" class="btn btn-default" value="Cancelar"/>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:when test="${accion=='ELIMINAR_Y_REGISTRAR'}">
                    <div class="alert alert-success">
                        <p class="text-center">Registro de Umbrales de la variable ${cod_var}, para la estacion ${cod_esta}.</p>
                    </div>
                    <div class="text-center"><img src="/sis/static/img/extremos.png"/></div>
                    <form action="../exec.do" method="post">
                        <input type="hidden" name="accion" value="INSERTAR"/>
                        <input type="hidden" name="codigo_estacion" value="${cod_esta}">
                        <input type="hidden" name="codigo_variable" value="${cod_var}">
                        <div class="row well well-lg">
                            <div class="col-sm-2 text-right h3">L&iacute;mites Malos</div>
                            <div class="col-sm-1 text-center h3"><=</div>
                            <div class="col-sm-1"><input type="text" class="form-control text-center" name="lim_maloi" autocomplete="off" autofocus required ></div>
                            <div class="col-sm-1 text-center h3">>=</div>
                            <div class="col-sm-1"><input type="text" class="form-control text-center" name="lim_malod" autocomplete="off" required ></div>
                            <div class="col-sm-6"></div>
                        </div>
                        <div class="row well well-lg">
                            <div class="col-sm-2 text-right h3">L&iacute;mites Dudosos</div>
                            <div class="col-sm-1 text-center h3">>=</div>
                            <div class="col-sm-1"><input type="text" class="form-control text-center" name="lim_dudosoi1" autocomplete="off" required ></div>
                            <div class="col-sm-1 text-center h3"><=</div>
                            <div class="col-sm-1"><input type="text" class="form-control text-center" name="lim_dudosoi2" autocomplete="off" required ></div>
                            <div class="col-sm-1 text-center h3">Y</div>
                            <div class="col-sm-1 text-center h3">>=</div>
                            <div class="col-sm-1"><input type="text" class="form-control text-center" name="lim_dudosod1" autocomplete="off" required ></div>
                            <div class="col-sm-1 text-center h3"><=</div>
                            <div class="col-sm-1"><input type="text" class="form-control text-center" name="lim_dudosod2" autocomplete="off" required ></div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="row well well-lg">
                            <div class="col-sm-2 text-right h3">L&iacute;mites Buenos</div>
                            <div class="col-sm-1 text-center h3">>=</div>
                            <div class="col-sm-1"><input type="text" class="form-control text-center" name="lim_buenoi" autocomplete="off" required ></div>
                            <div class="col-sm-1 text-center h3"><=</div>
                            <div class="col-sm-1"><input type="text" class="form-control text-center" name="lim_buenod" autocomplete="off" required ></div>
                            <div class="col-sm-6"></div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12 text-center">
                                <input type="submit" class="btn btn-default" value="Guardar"/>
                            </div>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <div>&nbsp;</div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>