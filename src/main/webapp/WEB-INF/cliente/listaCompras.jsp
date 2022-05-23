<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<jsp:include page='../template/header.jsp'>
	<jsp:param name='title' value='Lsita Compras:' />
</jsp:include>

<body>

	<jsp:include page='../template/navbar2.jsp'>
		<jsp:param name='title' value='Sistema CumplePekes' />
	</jsp:include>

	<br>
	<br>

	<div class="container mt-5">
		<h3 class="fs-4 card-title fw-bold mb-4">
			Lista de Compras de
			<c:out value="${cliente.nombre}">
			</c:out>
			</h3>

			<div class="table-responsive-md">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Fecha</th>
							<th scope="col">Monto</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="compra" items="${cliente.compras}">
							<tr>
								<th scope="row"><c:out value="${compra.id}"></c:out></th>
								<td><c:out value="${compra.fecha}">
									</c:out></td>
								<td><c:out value="${compra.monto}">
									</c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
	<br>
	<br>

	<jsp:include page='../template/footer.jsp'>
		<jsp:param name='title' value='Sistema CumplePekes' />
	</jsp:include>


	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js ">
	</script>


</body>
</html>