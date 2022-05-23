<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<jsp:include page='../template/header.jsp'>
	<jsp:param name='title' value='Venta:' />
	<jsp:param name='usuarioEmail' value='${usuarioEmail}' />
</jsp:include>

<body>

	<jsp:include page='../template/navbar2.jsp'>
		<jsp:param name='title' value='Sistema CumplePekes' />
		<jsp:param name='usuarioEmail' value='${usuarioEmail}' />
	</jsp:include>

	<br>
	<br>

	<div class="text-center">
		<img alt="" src="">
		<h3>Registro de Venta</h3>
	</div>

	<div class="container mt-5">

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

		<form method="POST" action="/venta/agregar">
			<div class="row g-3 align-items-center">
				<input type="hidden" value="${ventaId}" name="ventaId">

				<div class="col-sm-5">
					<label for="productoId" class="form-text">Producto:</label> <select
						id="producto" name="productoId" class="form-select">
						<option value="0">Seleccione producto</option>
						<c:forEach items="${listaProductos}" var="product">
							<option value="${product.id}">
								<c:out value="${product.nombre}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-sm">
					<label class="form-text">Cantidad:</label> <input type="number"
						class="form-control" value="0"
						placeholder="Ingresa la cantidad de compra" name="cantidad" />
				</div>

				<div class="col-sm">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary ms-auto mt-4">
						Registrar Compra</button>
				</div>
			</div>
		</form>
	</div>



	<div class="container mt-5">
		<h3 class="fs-4 card-title fw-bold mb-4">Detalle Compra</h3>
		<div class="table-responsive-md">
			<table class="table">
				<thead>
					<tr>
						<!-- <th scope="col">#</th>  -->
						<th scope="col">Cantidad</th>
						<th scope="col">Nombre</th>
						<th scope="col">Precio</th>
						<th scope="col">Subtotal</th>
						<th scope="col-2">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="productosVentas" items="${listaProductosVentas}">
							<tr>
								<td><c:out value="${productosVentas.cantidad}">
									</c:out></td>
								<td><c:out value="${productosVentas.producto.nombre}">
									</c:out></td>
								<td><c:out value="${productosVentas.valorUnitario}">
									</c:out></td>
								<td><c:out value="${productosVentas.total}">
									</c:out></td>
								<td><a class="btn btn-outline-danger"
									href="/venta/eliminar/${productosVentas.id}" role="button">Eliminar</a> </td>
							</tr>
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td>TOTAL</td>
						<td>${venta.monto}</td>	
					</tr>

				</tbody>
			</table>

			<form method="POST" action="/venta/finalizar">
				<input type="hidden" value="${ventaId}" name="ventaId">
				<div class="row g-3 align-items-center">
					<div class="col-sm-5">
						<label for="productoId" class="form-text">Seleccione un
							cliente:</label> <select id="cliente" name="cliente" class="form-select">
							<option value="0">Seleccione cliente</option>
							<c:forEach items="${listaClientes}" var="cliente">
								<option value="${cliente.id}">
									<c:out value="${cliente.nombre}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="align-items-center d-flex">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button type="submit" class="btn btn-primary ms-auto">
							Finalizar venta</button>

					</div>
				</div>
			</form>
		</div>
	</div>



	<jsp:include page='../template/footer.jsp'>
		<jsp:param name='title' value='Sistema CumplePekes' />
	</jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js ">
	</script>

</body>
</html>