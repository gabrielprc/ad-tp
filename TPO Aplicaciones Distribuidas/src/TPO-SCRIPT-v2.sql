--use master drop database TPAD

create database TPAD
go

use TPAD
go

create table Materiales_Restringidos(
	nombre varchar(50),
	constraint PK_MaterialesRestringidos primary key (nombre)
)

create table Productos(

	id_producto int identity not null,
	nombre varchar(30),
	tipo_fragilidad varchar(50),
	tipo_tratamiento varchar(50),
	profundidad float,
	ancho float,
	alto float,
	peso float,
	apilable int,
	manipulacion varchar(200),
	material varchar(50),
	consideraciones varchar(200),
	refrigerada bit,
	
	constraint pk_productos primary key (id_producto)
)

create table Tipo_Puestos(

	id_puesto int not null,
	nombre varchar(50) not null
	constraint pk_tipo_puesto primary key(id_puesto)
)

create table Empleados(

	id_empleado int not null,
	id_puesto int,
	cuit varchar(50) not null,
	dni varchar(20),
	nombre varchar(50),
	apellido varchar(50),
	fecha_nacimiento datetime,
	

	constraint pk_empleados primary key(id_empleado, cuit),
	constraint fk_tipo_puesto foreign key(id_puesto) references Tipo_Puestos
)

create table Clientes(
	
	id_cliente int identity not null,
	codigo_unico varchar(20),
	
	constraint pk_clientes primary key (id_cliente)
)

create table Clientes_Empresas(
	
	id_cliente int identity not null,
	esRegular bit,

	constraint pk_clientes_empresas primary key (id_cliente)
)

create table Clientes_Particules(

	id_cliente int identity not null,
	nombre varchar(50),
	apellido varchar(50),
	dni varchar(20),

	constraint pk_clientes_particulares primary key (id_cliente)
)

create table Cuentas_Corrientes(

	id_cuenta_corriente int identity not null,
	id_cliente int,
	depositoPrevio bit,
	montoAutorizado float,
	montoActual float,

	constraint pk_cuentas primary key (id_cuenta_corriente),
	constraint fk_cuentas_clientes foreign key(id_cliente) references Clientes_Empresas
)

create table Ubicaciones(

	id_ubicacion int identity not null,
	pais varchar(50),
	provincia varchar(50),
	ciudad varchar(50),
	calle varchar(50),
	altura varchar(50),
	piso varchar(50),
	departamento varchar(50),
	longitud int,
	latitud int,
	
	constraint pk_ubicaciones primary key (id_ubicacion)
)

create table Sucursales(

	id_sucursal int identity not null,
	id_ubicacion int,
	nombre varchar(50),
	
	constraint pk_sucursales primary key (id_sucursal)
)


create table Depositos(
	
	id_deposito int identity not null,
	id_sucursal int,
	
	constraint pk_depositos primary key (id_deposito),
	constraint FK_Sucursales foreign key (id_sucursal) references Sucursales
)

create table Receptores(

	id_cliente int not null,
	id_receptor int not null,
	id_ubicacion int,
	dni varchar (20),
	nombre varchar(50),
	apellido varchar(50),
	
	
	constraint pk_receptores primary key(id_cliente, id_receptor),
	constraint fk_ubicaciones foreign key(id_ubicacion) references Ubicaciones
)

create table Proveedores(

	id_proveedor int identity, 
	cuit varchar(20) not null,
	nombre varchar(50),
	
	constraint pk_proveedores primary key(id_proveedor)
)

create table Tipos_Vehiculos(

	id_tipo_vehiculo int identity not null,
	tipo_vehiculo varchar(20)
	
	constraint pk_tipo_vehiculo primary key(id_tipo_vehiculo) 
)

create table Vehiculos(
	
	id_vehiculo int identity not null,
	id_tipo_vehiculo int,
	patente varchar(10),
	peso float,
	tara float, 
	tarifa float, 
	ancho float,
	alto float,
	profundidad float,
 
	constraint pk_vehiculo primary key(id_vehiculo),
	constraint fk_veh_externo_tipo_veh foreign key(id_tipo_vehiculo) references Tipos_Vehiculos
	
)

create table Vehiculos_Externos(
	
	id_vehiculo int identity not null,
	id_proveedor int,
	
	constraint pk_vehiculo_externo primary key(id_vehiculo),
	constraint fk_vehiculos_ext_proveedores foreign key(id_proveedor) references Proveedores
)

create table Vehiculos_Locales(

	id_vehiculo int identity not null,
	id_sucursal int,
	vencimiento_garantia datetime,
 
	constraint pk_vehiculos_locales primary key(id_vehiculo),
	constraint fk_vehiculos_sucursales foreign key(id_sucursal) references Sucursales
)

create table Planes_Mantenimiento(

	id_plan_mantenimiento int identity not null	,
	id_vehiculo int,
	kilometrajeActual float,
	puntoControl float,

	constraint PK_PlanesMantenimiento primary key (id_plan_mantenimiento),
	constraint FK_PlanesMantenimiento_Vehiculos foreign key (id_vehiculo) references Vehiculos_Locales
)

create table Tareas(

	id_plan_mantenimiento int,
	kilometraje float,
	fecha_Entrega datetime,
	fecha_devolucion datetime,

	constraint FK_Tareas_PlanesMantenimiento foreign key (id_plan_mantenimiento) references Planes_Mantenimiento
)	

create table Companias_Seguros(
	
	id_compania_seguros int identity not null,
	cuil varchar(20),
	nombre varchar(40),

	constraint pk_compania_seguros primary key (id_compania_seguros)
)

create table Tipos_Carga(

	id_tipo_carga int identity not null,
	nombre varchar(30),

	constraint pk_tipo_carga primary key(id_tipo_carga)
)

create table Seguros(
	
	id_seguro int identity not null,
	id_compania_seguros int,
	id_tipo_carga int,
	nombre varchar(40),
	tarifa float,
	
	constraint pk_seguros primary key (id_seguro),
	constraint fk_seguros_companiasseguros foreign key (id_compania_seguros) references Companias_Seguros,
	constraint fk_seguros_tipo_carga foreign key (id_tipo_carga) references Tipos_Carga
)

create table Viajes(

	id_viaje int identity not null,
	id_seguro int,
	id_vehiculo int,
	id_sucursal int,
	fecha_salida datetime,
	fecha_llegada datetime,
	condicion_especial varchar(50),

	constraint pk_viajes primary key(id_viaje),
	constraint fk_viajes_vehiculos foreign key(id_vehiculo) references Vehiculos,
	constraint fk_viajes_seguros foreign key(id_seguro) references Seguros

)

create table Cargas(

	id_carga int identity,
	id_cliente int,
	id_viaje int,
	id_ubicacionOrigen int,
	id_ubicacionDestino int,
	id_Deposito int,
	tipoCarga varchar(50),
	estadoCarga varchar(50),
	fechaMaximaEntrega datetime,
	fechaProbableEntrega datetime,
	manifiesto varchar(200),
	
	constraint pK_cargas primary key (id_carga),
	constraint fk_cargas_clientes foreign key (id_cliente) references Clientes,
	constraint fk_cargas_ubicacionesOrigen foreign key (id_ubicacionOrigen) references Ubicaciones,
	constraint fk_cargas_ubicacionesDestino foreign key (id_ubicacionDestino) references Ubicaciones,
	constraint fk_cargas_depositos foreign key (id_deposito) references Depositos,
	constraint fk_cargas_viajes foreign key (id_viaje) references Viajes
)

create table Items_Cargas_Producto(

	id_item_producto int identity not null,
	id_carga int,
	id_producto int,
	cantidad float,

	constraint pk_item_producto primary key(id_item_producto),
	constraint fk_items_carga foreign key(id_carga) references Cargas,
	constraint fk_items_producto foreign key(id_producto) references Productos
)

create table Facturas(

	id_factura int identity not null,
	fecha datetime,

	constraint pk_facturas primary key(id_factura),
)

create table Items_Factura(

	id_item_factura int identity not null,
	id_factura  int,
	id_carga int,
	cantidad float,
	monto float,
	fecha_pago datetime,
	pagado bit,

	constraint pk_items_fact primary key(id_item_factura),
	constraint fk_items_facturas foreign key(id_carga) references Facturas,
	constraint fk_items_cargas foreign key(id_carga) references Cargas
)

create table Pagos(
	
	id_pago int identity not null,
	id_proveedor int,
	monto float,
	fecha datetime,
	pagado bit,

	constraint pk_pagos primary key(id_pago),
	constraint fk_pagos_proveedores foreign key(id_proveedor) references Proveedores
)

create table Distancia_Sucursales(

	id_distancia_sucursales int identity not null,
	id_sucursal_a int,
	id_sucursal_b int,
	distancia float,
	duracion float,
	costo float,
	
	constraint pk_dist_sucur primary key(id_distancia_sucursales),
	constraint fk_sucursal_a foreign key(id_sucursal_a) references Sucursales,
	constraint fk_sucursal_b foreign key(id_sucursal_b) references Sucursales,
)

create table Cargas_Productos(

	id_carga int not null,
	id_producto int,
	cantidad float,
	
	constraint pk_cargas_productos primary key(id_carga, id_producto),
	constraint fk_cargas foreign key(id_carga) references Cargas
)

--drop login ADUser
create login ADuser with password = 'ADpassword'
go

create user ADuser for login ADuser
go

exec sp_addrolemember 'db_owner', 'ADuser'
go