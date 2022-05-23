<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page='../template/header.jsp'>
<jsp:param name='title' value='Direccion:' />
</jsp:include> 

<body>

<jsp:include page='../template/navbar2.jsp'>
<jsp:param name='title' value='Sistema CumplePekes' />
</jsp:include> 

<br>
<br>

<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-sm-center h-100">
				<div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
					<div class="card shadow-lg" id="card">
						<div class="card-body p-5">
							<h1 class="fs-4 card-title mb-4">Formulario de Registro de Cliente</h1>

							<c:if test="${msgError !=null}">
								<div class="alert alert-danger" role="alert">
									<c:out value="${msgError}"></c:out>
								</div>
							</c:if>

							<c:if test="${msgOk !=null}">
								<div class="alert alert-success" role="alert">
									<c:out value="${msgOk}"></c:out>
								</div>
							</c:if>

							<form:form  method="POST" action="/direccion/cliente/agregar" modelAttribute="direccion">
						
								<div class="mb-3">
									<form:select path="usuario" class="form-select">
										<form:option value="0">Seleccione Cliente</form:option>
										<c:forEach items="${listaClientes}" var="user">
											<c:if test="${user.direccion.id == null }">
												<form:option value="${user.id}">
													<c:out value="${user.nombre}"></c:out>
												</form:option>
											</c:if>	
										</c:forEach>								
									</form:select>		
								</div>

								<div class="mb-3">
									<form:label class="mb-2 text-muted" path="calle">Calle: </form:label> 
									<form:input type="text" class="form-control" path="calle"
										value="" placeholder="Ingresa el nombre de la calle" />
									<form:errors path="calle" class="text-danger" />
								</div>
	    			
								<div class="mb-3">
									<form:label class="mb-2 text-muted" path="numero">Número:</form:label>
									<form:input type="text" class="form-control" path="Numero"
										value="" placeholder="Ingresa el número de la dirección" />
									<form:errors path="numero" class="text-danger" />
								</div>
					
								<div class="mb-3">
									<form:label class="mb-2 text-muted" path="poblacion">Población: </form:label>
									<form:input type="text" class="form-control" path="poblacion"
										value="" placeholder="Ingresa la población, si existe" />
									<form:errors path="poblacion" class="text-danger" />
								</div>

								<div class="mb-3">
									<form:label class="mb-2 text-muted" path="ciudad">Ciudad: </form:label>
									<form:input type="text" class="form-control" path="ciudad"
										value="" placeholder="Ingresa la ciudad" />
									<form:errors path="ciudad" class="text-danger" />
								</div>
								
								<div class="mb-3">
									<form:label class="mb-2 text-muted" path="comuna">Comuna: </form:label>
									<form:input type="text" class="form-control" path="comuna"
										value="" placeholder="Ingresa la Comuna" />
									<form:errors path="comuna" class="text-danger" />
								</div>
								
								<div class="mb-3">
									<form:label class="mb-2 text-muted" path="region">Región: </form:label>
									<form:input type="text" class="form-control" path="region"
										value="" placeholder="Ingresa la región de la dirección" />
									<form:errors path="region" class="text-danger" />
								</div>

								<div class="align-items-center d-flex">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button type="submit" class="btn btn-primary ms-auto">
										Registra la Dirección</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

<br>
<br> 

<jsp:include page='../template/footer.jsp'>
<jsp:param name='title' value='Sistema CumplePekes' />
</jsp:include> 


<!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js " >
    </script>

 
</body>
</html>