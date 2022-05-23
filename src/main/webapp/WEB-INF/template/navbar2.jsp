<ul class="nav nav-tabs" style="background-color: rgba(0, 0, 255, 0.5);">
	<li class="nav-item"><a class="nav-link" 
	style="background-image: linear-gradient(to right, red,orange,yellow,green,blue,indigo,violet);"  
	href="/">Inicio para ${param.nombreUsuario}</a></li>
	<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
		data-bs-toggle="dropdown" style="color:Black;" href="venta">PDF</a>
		<ul class="dropdown-menu">
			<li><a class="dropdown-item" style="color:Black;" href="/venta/export/pdf">Exportar Ventas a PDF
			</a></li>
			<li><a class="dropdown-item" style="color:Black;" href="">Por implementar</a></li>
		</ul>
	</li>
	
	<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
		data-bs-toggle="dropdown" style="color:Black;" href="venta">Venta</a>
		<ul class="dropdown-menu">
			<li><a class="dropdown-item" style="color:Black;" href="/venta">Ingresar Venta</a></li>
			<li><a class="dropdown-item" style="color:Black;" href="/venta/ver">Ver Venta por Id</a></li>
		</ul>
	</li>
	<li class="nav-item"><a class="nav-link" style="color:Black;" href="/direccion/cliente">Crear
			Dirección cliente</a></li>		
	<li class="nav-item"><a class="nav-link" style="color:Black;" href="/cliente">Crear
			Cliente</a></li>
	<li class="nav-item"><a class="nav-link" style="color:Black;" href="/compra">Compra</a></li>
	<li class="nav-item"><a class="nav-link" style="color:Black;" href="/producto">Producto</a></li>
	<li class="nav-item">
		<form method="POST" action="/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Logout">
		</form>
	</li>
</ul>