--use master drop database TPAD

create database TPAD
go

use TPAD
go

create table MaterialesRestringidos(
	nombre varchar(50),
	constraint PK_MaterialesRestringidos primary key (nombre)
)
	
create table Productos(
	idProducto int identity,
	tipoFragilidad varchar(50),
	tipoTratamiento varchar(50),
	profundidad float,
	ancho float,
	alto float,
	peso float,
	apilable int,
	manipulacion varchar(200),
	material varchar(50),
	consideraciones varchar(200),
	refrigerada bit,
	constraint PK_Productos primary key (idProducto)
)

create table Ubicaciones(
	idUbicacion int identity,
	pais varchar(50),
	provincia varchar(50),
	ciudad varchar(50),
	calle varchar(50),
	altura varchar(50),
	piso varchar(50),
	departamento varchar(50),
	longitud int,
	latitud int,
	constraint PK_Ubicaciones primary key (idUbicacion)
)

create table Sucursales(
	idSucursal int identity,
	nombre varchar(50),
	idUbicacion int,
	constraint PK_Sucursales primary key (idSucursal)
)

create table Personas(
	cuit varchar(20),
	clasePersona varchar(10), --persona regular o empleado
	dni varchar(15),
	nombre varchar(20),
	apellido varchar(20),
	idSucursal int,
	tipoEmpleado varchar(20),
	constraint PK_Personas primary key (cuit),
	constraint FK_Personas_Sucursales foreign key (idSucursal) references Sucursales
)

create table Clientes(
	idCliente int identity,
	claseCliente varchar(10),
	cuitTitular varchar(20),
	esRegular bit,
	nombreEmpresa varchar(50),
	cuil varchar(50),
	depositoPrevio bit,
	montoAutorizado float,
	montoActual float,
	constraint PK_Clientes primary key (idCliente),
	constraint FK_Clientes_Personas foreign key (cuitTitular) references Personas
)

create table ReceptoresClienteParticular(
	idCliente int,
	cuitReceptor varchar(20),
	constraint PK_ReceptoresClienteParticular primary key (idCliente, cuitReceptor),
	constraint FK_ReceptoresClienteParticular_Clientes foreign key (idCliente) references Clientes,
	constraint FK_ReceptoresClienteParticular_Personas foreign key (cuitReceptor) references Personas
)

create table Depositos(
	idDeposito int identity,
	idSucursal int,
	constraint PK_Depositos primary key (idDeposito),
	constraint FK_Sucursales foreign key (idSucursal) references Sucursales
)

create table Cargas(
	idCarga int identity,
	idCliente int,
	idProducto int,
	idUbicacionOrigen int,
	idUbicacionDestino int,
	idDeposito int,
	tipoCarga varchar(50),
	fechaMaximaEntrega datetime,
	fechaProbableEntrega datetime,
	manifiesto varchar(200),
	constraint PK_Cargas primary key (idCarga),
	constraint FK_Cargas_Clientes foreign key (idCliente) references Clientes,
	constraint FK_Cargas_Productos foreign key (idProducto) references Productos,
	constraint FK_Cargas_UbicacionesOrigen foreign key (idUbicacionOrigen) references Ubicaciones,
	constraint FK_Cargas_UbicacionesDestino foreign key (idUbicacionDestino) references Ubicaciones,
	constraint FK_Cargas_Depositos foreign key (idDeposito) references Depositos
)

create table Facturas(
	idFactura int identity,
	idCarga int,
	monto float,
	constraint PK_Facturas primary key (idFactura),
	constraint FK_Facturas_Cargas foreign key (idCarga) references Cargas
)

create table Cobros(
	idFactura int,
	monto float,
	fecha datetime,
	constraint PK_Cobros primary key (idFactura, fecha),
	constraint FK_Cobros_Facturas foreign key (idFactura) references Facturas
)

create table ProductosEmpresaExterna(
	idEmpresa int,
	idProducto int,
	constraint PK_ProductosEmpresaExterna primary key (idEmpresa, idProducto),
	constraint FK_ProductosEmpresaExterna_Clientes foreign key (idEmpresa) references Clientes,
	constraint FK_ProductosEmpresaExterna_Productos foreign key (idProducto) references Productos
)


create table CompaniasSeguros(
	cuil varchar(20),
	nombre varchar(40),
	constraint PK_CompaniasSeguros primary key (cuil)
)

create table Seguros(
	idSeguro int identity,
	cuilCompaniaSeguros varchar(20),
	nombre varchar(40),
	tarifa float,
	constraint PK_Seguros primary key (idSeguro),
	constraint FK_Seguros_CompaniasSeguros foreign key (cuilCompaniaSeguros) references CompaniasSeguros
)

create table Vehiculos(
	patente varchar(20),
	claseVehiculoLocalExterno varchar(10), --vehiculo local o externo (de otra empresa)
	claseVehiculo varchar(20), --camion con caja, camion con tanque, tractor, camioneta, carrier, avion...
	cuilProveedor varchar(20),
	idSucursal int,
	profundidad float,
	alto float,
	ancho float,
	peso float,
	tara float,
	tarifa float,
	vencimientoGarantie datetime,
	refrigerado bit,
	tamano varchar(20), --mediana, grande? 
	constraint PK_Vehiculos primary key (patente),
	constraint FK_Vehiculos_Sucursales foreign key (idSucursal) references Sucursales,
)

create table Viajes(
	idViaje int identity,
	idSeguro int,
	patente varchar(20),
	idSucursal int,
	fechaSalida date,
	fechaLlegada date,
	condicionEspecial varchar(30),
	constraint PK_Viajes primary key (idViaje),
	constraint FK_Viajes_Seguros foreign key (idSeguro) references Seguros,
	constraint FK_Viajes_Vehiuclos foreign key (patente) references Vehiculos,
	constraint FK_Viajes_Sucursales foreign key (idSucursal) references Sucursales
)

create table Envios(
	idCarga int,
	idViaje int,
	idDestinoIntermedio int,
	constraint PK_Envios primary key (idCarga, idViaje),
	constraint FK_Envios_Cargas foreign key (idCarga) references Cargas,
	constraint FK_Envios_Viajes foreign key (idViaje) references Viajes
)

create table PlanesMantenimiento(
	idPlanMantenimiento int identity,
	patenteVehiculo varchar(20),
	kilometrajeActual float,
	puntoControl float,
	constraint PK_PlanesMantenimiento primary key (idPlanMantenimiento),
	constraint FK_PlanesMantenimiento_Vehiculos foreign key (patenteVehiculo) references Vehiculos
)

create table Tareas(
	idPlanMantenimiento int,
	kilometraje float,
	fechaEntrega datetime,
	fechadevolucion datetime,
	constraint FK_Tareas_PlanesMantenimiento foreign key (idPlanMantenimiento) references PlanesMantenimiento
)

--drop login ADUser
create login ADuser with password = 'ADpassword'
go

create user ADuser for login ADuser
go

exec sp_addrolemember 'db_owner', 'ADuser'
go