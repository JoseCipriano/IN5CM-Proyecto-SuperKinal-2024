drop database if exists SuperDB;
 create database SuperDB;
use SuperDB;

create table Cargos(
	cargoId int not null auto_increment,
    nombreCargo varchar(30) not null,
    descripcionCargo varchar(100) not null,
    primary key Pk_cargoId (cargoId)
);

create table Empleados(
	empleadoId int not null auto_increment,
    nombreEmpleado varchar(30) not null,
    apellidoEmpleado varchar(30) not null,
    sueldo decimal (10,2),
    horaEntrada time not null  ,
    horaSalida time not null ,
    cargoId int,
    encargadoId int ,
    primary key PK_empleadoId(empleadoId),
	constraint FK_encargadoId foreign key Empleados(encargadoId)
        references Empleados(empleadoId),
	constraint Fk_Empleados_Cargos foreign key(cargoId)
		references Cargos(cargoId)
);

create table Clientes(
	clienteId int not null auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    telefono varchar(15) not null,
    direccion varchar(50) not null,
    NIT varchar (50) not null,
    primary key PK_clienteId (clienteId)
);

create table Facturas(
	facturaId int not null auto_increment,
    fecha date not null,
    hora time not null,
    total decimal (10,2),
    clienteId int,
    empleadoId int,
    primary key Pk_facturaId (facturaId),
    constraint FK_Facturas_Clientes foreign key(clienteId)
		references Clientes (clienteId),
	constraint Fk_Facturas_Empleados foreign key(empleadoId)
		references Empleados (empleadoId)
);



create table TicketS(
	ticketSId int not null auto_increment,
    descripcionTicket varchar(250) not null,
    estatus varchar(30) not null,
    clienteId int,
    facturaId int,
    primary key Pk_ticketSId (ticketSId),
    constraint Fk_TicketS_Clientes foreign key (clienteId)
		references Clientes (clienteId),
	constraint Fk_TicketS_Facturas foreign key (facturaId)
		references Facturas (facturaId)
);

create table Distribuidores(
	distribuidorId int not null auto_increment,
    nombreDistribuidor varchar(30) not null,
    direccionDistribuidor varchar(200) not null,
    nitDistribuidor varchar(15) not null,
    telefonoDistribuidor varchar(15) not null,
    web varchar(50),
    primary key Pk_distribuidorId (distribuidorId)
);

create table CategoriaProductos(
	categoriaProductoId int not null auto_increment,
    nombreCategoria varchar(30) not null,
    descripcionCategoria varchar(100) not null,
    primary key Pk_categoriaProductoId (categoriaProductoId)
);

create table Productos(
	productoId int not null auto_increment,
    nombreProducto varchar(50) not null,
    descripcionProducto varchar(100),
    cantidadStock int not null,
    precioVentaUnitario decimal(10,2) not null,
    precioVentaMayor decimal(10,2) not null,
    precioCompra decimal(10,2) not null,
    imagenProducto blob,
    distribuidorId int,
    categoriaProductoId int,
    primary key Pk_productoId (productoId),
    constraint Fk_Productos_Distribuidores foreign key (distribuidorId)
		references Distribuidores (distribuidorId),
	constraint Fk_Productos_CategoriaProductos  foreign key (categoriaProductoId)
		references CategoriaProductos (categoriaProductoId)
);

create table Promociones(
	promocionId int not null auto_increment,
    precioPromocion decimal(10,2)not null,
    descripcionPromocion varchar(100),
    fechaInicio date not null,
    fechaFinalizacion date not null,
    productoId int,
    primary key Pk_promocionId (promocionId),
    constraint Fk_Promociones_Productos foreign key (productoId)
		references Productos (productoId)
);

create table DetalleFacturas(
	detalleFacturaId int not null auto_increment,
    facturaId int,
    productoId int,
    primary key Pk_detalleFacturaId (detalleFacturaId),
	constraint Fk_DetalleFacturas_Facturas foreign key (facturaId)
		references Facturas (facturaId),
	constraint Fk_DetalleFacturas_Productos foreign key (productoId)
		references Productos (productoId)
);

create table Compras(
	compraId int not null auto_increment,
    fechaCompra date not null,
    totalCompra decimal(10,2),
    primary key Pk_compraId (compraId)
);

create table DetalleCompras(
	detalleCompraId int not null auto_increment,
    cantidadCompra int not null,
    productoId int,
    compraId int,
    primary key Pk_detalleCompraId (detalleCompraId),
    constraint Fk_DetalleCompras_Productos foreign key (productoId)
		references Productos (productoId),
	constraint Fk_DetalleCompras_Compras foreign key (compraId)
		references Compras (compraId)
);


insert into Cargos(nombreCargo, descripcionCargo) values 
('Asistente','asitente del encargado superior'),
('Ayudante','ayudante del delegado ');

insert Empleados(nombreEmpleado, apellidoEmpleado, sueldo,horaEntrada, horaSalida, cargoId, encargadoId) values
('Alejandro','Carrera',100.00, '18:8','01:01',1,null),
('Alexsander','Vasquez',200.00,'19:02','20:44',2,1);

insert into Clientes(nombre, apellido, telefono, direccion, NIT) values 
('Luis','Perez','1245-7885','Ciudad','11565154'),
('Alejandro','Carrillo','4541-4654','Ciudad', '2515151'),
('Jesus','sis','4571-5742','Ciudad', '21413322');

insert into Facturas(fecha, hora, total, clienteId, empleadoId)values
('2015-02-01','12:00','20',2,1),
('2015-02-01','01:1','50',1,2);

insert into TicketS (descripcionTicket,estatus,clienteId, facturaId)values
('Error en el Sistema','en proceso',2,1),
('Error en el cobro','finalizado',3,1);

insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, nitDistribuidor,telefonoDistribuidor,web)values
('Fundacion Kinal','zona3','132454','2254-4174',null),
('DollarCITY','zona10','8/78465','4041-7841',null);

insert into CategoriaProductos(nombreCategoria,descripcionCategoria)values 
('Carnes ','Parte de embutidos'),
('Liquidos ','Cosas Liquidas');

insert into Productos(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductoId  ) values
('Slachi','pack de salchichas','20',18.00,15.00,17.99,null,1,1),
('Jug','Jugos de Frutas','12',5.00,6.00,4.00,null,2,2);

insert into Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId)values 
(10.00,'Promocion dia de las Madres','2024-05-05','2024-05-11',1),
(09.00,'SemanaSanta ','2024-03-15','2024-03-20',2);

insert into DetalleFacturas(facturaId, productoId)values
(1,1),
(2,2);


insert into Compras(fechaCompra, totalCompra)values
('2024-05-11',10.20),
('2024-04-20',11.10);

insert into DetalleCompras(cantidadCompra, productoId, compraId)values 
(10,2,1),
(04,1,2);






-- -----------------------------Clientes 

DELIMITER $$
create procedure sp_agregarClientes(nom varchar(30), ape varchar(30), tel varchar(15), dir varchar(200), NIT varchar(50))

BEGIN
	insert into Clientes(nombre, apellido, telefono, direccion, NIT) values
		(nom, ape, tel, dir, nit);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarClientes()
BEGIN 
	SELECT 
		Clientes.clienteId,
        Clientes.nombre, 
        Clientes.apellido, 
        Clientes.telefono, 
        Clientes.direccion,
        Clientes.nit
        FROM Clientes;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarClientes(cliId INT)
BEGIN 
	DELETE FROM Clientes 
    where clienteId = cliId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarClientes(cliId int)
BEGIN 
	SELECT*from Clientes
		where ClienteId = cliId;
	END $$
DELIMITER ; 
call sp_BuscarClientes(1);

DELIMITER $$ 
CREATE PROCEDURE sp_EditarClientes(cliId INT,nom varchar(30), ape varchar(30), tel varchar(15), dir varchar(200),NIT varchar(50))
BEGIN 
UPDATE Clientes 
SET 
	nombre = nom, 
    apellido = ape, 
    telefono = tel,
    direccion =  dir, 
    nit = nit,
    clienteId = cliId
    WHERE clienteId =  cliId;
    end $$
DELIMITER ;

select * from Clientes;
-- -------------------------------------------------------Facturas
DELIMITER $$
create procedure sp_agregarTicketS(descT varchar(250), cliId int, facId int)

BEGIN
	insert into TicketS(descripcionTicket, clienteId, facturaId) 
    values (descT, 'Recien Creado' , cliId, facId);
END $$
DELIMITER ;



DELIMITER $$
create procedure sp_ListarTicketS()
BEGIN 
	SELECT 
		TicketS.ticketSId,
        TicketS.descripcionTicket,
        TicketS.estatus,
        TicketS.clienteId,
        TicketS.facturaId
        FROM TicketS;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarTicketS(tickId INT)
BEGIN 
	DELETE FROM TicketS
    where ticketSId = tickId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarTicketS(tickId int)
BEGIN 
	SELECT*from TicketS
		where ticketSId = tickId;
	END $$
DELIMITER ; 

DELIMITER $$ 
CREATE PROCEDURE sp_EditarTicketS(tickId int, descT varchar(250), est varchar(30), clienteId int, facId int)
BEGIN 
UPDATE TicketS
SET 
	descripcionTicket = descT,
    estatus = est,
    clienteId = cliId,
    facturaId = facId,
	ticketSId = tickId
    WHERE ticketSId = tickId;
    end $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE sp_ListarTicketS2 ()
BEGIN 
	SELECT TS.ticketSId, TS.descripcionTicket, TS.descripcionTicket, TS.estatus ,
    CONCAT(C.clienteId , C.nombre) AS cliente , TS.facturaId FROM TicketS TS
    join Clientes C on TS.clienteId = C.clienteId;

END $$
DELIMITER  ;
-- --------------------------------------------------------------Cargos

DELIMITER $$
create procedure sp_agregarCargos(nomC varchar(30), desCr varchar(100))

BEGIN
	insert into Cargos(nombreCargo, descripcionCargo) values
		(nomC , desCr);
END $$
DELIMITER ;


DELIMITER $$
create procedure sp_ListarCargos()
BEGIN 
	SELECT 
		Cargos.cargoId,
        Cargos.nombreCargo,
        Cargos.descripcionCargo
        FROM Cargos;
		END $$
DELIMITER ; 

call sp_ListarCargos();

DELIMITER $$
create procedure sp_eliminarCargos(carId INT)
BEGIN 
	DELETE FROM Cargos 
    where cargoId = carId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarCargos(carId int)
BEGIN 
	SELECT*from Cargos
		where cargoId = carId;
	END $$
DELIMITER ; 

DELIMITER $$ 
CREATE PROCEDURE sp_EditarCargos(carId INT,nomC varchar(30), desCr varchar(100))
BEGIN 
UPDATE Cargos
SET 
	nombreCargo = nomC,
    descripcionCargo = desCr,
    cargoId = carId
    WHERE clienteId =  cliId;
    end $$
DELIMITER ;

-- ----------------------------------------------------CategoriaProductos
DELIMITER $$
create procedure sp_agregarCategoriaProductos(nomCat varchar(30), desCat varchar(100))

BEGIN
	insert into CategoriaProductos(nombreCategoria, descripcionCategoria) values
		(nomCat, desCat);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarCategoriaProductos()
BEGIN 
	SELECT 
		CategoriaProductos.categoriaProductoId, 
        CategoriaProductos.nombreCategoria,
        CategoriaProductos.descripcionCategoria
        FROM CategoriaProductos;
		END $$
DELIMITER ; 

call sp_ListarCategoriaProductos();
DELIMITER $$
create procedure sp_eliminarCategoriaProductos(catPId INT)
BEGIN 
	DELETE FROM CategoriaProductos
    where categoriaProductoId = catPId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarCategoriaProductos(catPId int)
BEGIN 
	SELECT*from CategoriaProductos
		where categoriaProductoId = catPId;
	END $$
DELIMITER ; 

DELIMITER $$ 
CREATE PROCEDURE sp_EditarCategoriaProductos(catPId int, nomCat varchar(30), desCat varchar(100))
BEGIN 
UPDATE CategoriaProductos
SET 
	categoriaProductoId = catPId,
    nombreCategoria = nomCat,
    descripcionCategoria = desCat,
    categoriaProductoId = catPId
    WHERE categoriaProductoId = catPId;
    end $$
DELIMITER ;

-- --------------------------------------------------Empleados
DELIMITER $$
create procedure sp_agregarEmpleados(nomE varchar(30), apeE varchar(30), sue decimal(10,2), horE time , horS time, carId int, encId int)

BEGIN
	insert into Cargos(nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargoId, encargadoId) values
		(nomE, apeE, sue, horE, horS, carId, encId);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarEmpleados()
BEGIN 
	SELECT 
		Empleados.empleadoId,
        Empleados.nombreEmpleado,
        Empleados.apellidoEmpleado,
        Empleados.sueldo,
        Empleados.horaEntrada,
        Empleados.horaSalida,
        Empleados.cargoId,
        Empleados.encargadoId
        FROM Empleados;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarEmpleados(empId INT)
BEGIN 
	DELETE FROM Empleados
    where empleadoId = empId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarEmpleados(empId int)
BEGIN 
	SELECT*from Empleados
		where empleadoId = empId;
	END $$
DELIMITER ; 


DELIMITER $$ 
CREATE PROCEDURE sp_EditarEmpleados(empId int, nomE varchar(30), apeE varchar(30), sue decimal(10,2), horE time , horS time, carId int, encId int)
BEGIN 
UPDATE Empleados
SET 
	nombreEmpleado = nomE,
    apellidoEmpleado = apeE,
    sueldo = sue,
    horaEntrada = horE,
    horaSalida = horS,
    cargoId = carId,
    encargadoId = encId,
    empleadoId = empId
    WHERE empleadoId = empId;
    end $$
DELIMITER ;








        