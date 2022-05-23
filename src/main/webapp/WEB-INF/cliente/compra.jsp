<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page='../template/header.jsp'>
	<jsp:param name='title' value='Compra:' />
</jsp:include>

<body>

	<jsp:include page='../template/navbar2.jsp'>
		<jsp:param name='title' value='Sistema CumplePekes' />
		<jsp:param name='usuarioEmail' value='${usuarioEmail}' />
	</jsp:include>

	<br>
	<br>

<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-sm-center h-100">
				<div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
					<div class="card shadow-lg" id="card">
						<div class="card-body p-5">
							<h1 class="fs-4 card-title mb-4">Formulario de Registro de Compras</h1>

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

							<form:form  method="POST" action="/compra" modelAttribute="compra">
						
								<div class="mb-3">
									<form:label class="mb-2 text-muted" path="fecha">Fecha: </form:label> 
									<form:input type="date" min="2022-01-01" class="form-control" path="fecha"/>
									<form:errors path="fecha" class="text-danger" />
								</div>
	    			
	    						<div class="mb-3">
									<form:select path="cliente" class="form-select">
										<form:option value="0">Seleccione al cliente</form:option>
										<c:forEach items="${listaClientes}" var="cliente">
												<form:option value="${cliente.id}">
													<c:out value="${cliente.nombre}"></c:out>
												</form:option>
										</c:forEach>								
									</form:select>
								</div>

								<div class="mb-3">
									<form:label class="mb-2 text-muted" path="monto">Monto:</form:label>
									<form:input type="number" class="form-control" path="monto"
										value="" placeholder="Ingresa el monto de la compra" />
									<form:errors path="monto" class="text-danger" />
								</div>

								<div class="align-items-center d-flex">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button type="submit" class="btn btn-primary ms-auto">
										Registrar Compra</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page='../template/footer.jsp'>
		<jsp:param name='title' value='Sistema CumplePekes' />
	</jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js ">
	</script>

</body>
</html>