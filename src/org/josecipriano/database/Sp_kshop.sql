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

-- -------------------------------------------------------------------------------Empleados
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

-- -------------------------------------------------------------------------------Clientes 

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

-- ------------------------------------------------------------ Facturas
DELIMITER $$
create procedure sp_agregarFacturas(fec date, hor date, tot decimal(10,2), cliId int, empId int)

BEGIN
	insert into Facturas(fecha, hora, total, clienteId, empleadoId) values
		(fec, hor, tot, cliId, empId);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarFacturas()
BEGIN 
	SELECT 
		Facturas.facturaId,
        Facturas.fecha, 
        Facturas.hora, 
        Facturas.total, 
        Facturas.clienteId, 
        Facturas.empleadoId
        FROM Facturas;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarFacturas(facId INT)
BEGIN 
	DELETE FROM Facturas
    where facturaId = facId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarFacturas(facId int)
BEGIN 
	SELECT*from Facturas
		where facturaId = facId;
	END $$
DELIMITER ; 

DELIMITER $$ 
CREATE PROCEDURE sp_EditarFacturas(facId int ,fec date, hor date, tot decimal(10,2), cliId int, empId int)
BEGIN 
UPDATE Facturas
SET 
	fecha = fec,
    hora = hor,
    total = tot,
    clienteId = cliId,
    empleadoId = empId,
    facturaId = facId
    WHERE facturaId = facId;
    end $$
DELIMITER ;

-- ---------------------------------------------------------------------------------TicketSoporte
DELIMITER $$
create procedure sp_agregarTicketS(descT varchar(250), est varchar(30), clienteId int, facId int)

BEGIN
	insert into Facturas(descripcionTicket, estatus, clienteId, facturaId) values
		(descT, est, cliId, empId);
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

-- ---------------------------------------------------------------------------------Distribuidores
DELIMITER $$
create procedure sp_agregarDistribuidores(nomD varchar(30), dirD varchar (200), nitD varchar(15), telD varchar(15), web varchar(50) )

BEGIN
	insert into Facturas(descripcionTicket, estatus, clienteId, facturaId) values
		(descT, est, cliId, empId);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarDistribuidores()
BEGIN 
	SELECT 
		Distribuidores.ditribuidorId,
        Distribuidores.nombreDistribuidor, 
        Distribuidores.direccionDistribuidor, 
        Distribuidores.nitDistribuidor,
        Distribuidores.telefonoDistribuidor,
        Distribuidores.web
        FROM Distribuidores;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarDistribuidores(distId INT)
BEGIN 
	DELETE FROM Distribuidores
    where distribuidorId = distId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarDistribuidores(distId int)
BEGIN 
	SELECT*from Distribuidores
		where distribuidorId = distId;
	END $$
DELIMITER ; 

DELIMITER $$ 
CREATE PROCEDURE sp_EditarDistribuidores(distId int , nomD varchar(30), dirD varchar (200), nitD varchar(15), telD varchar(15), web varchar(50) )
BEGIN 
UPDATE Distribuidores
SET 
	nombreDistribuidor = nomD,
    direccionDistribuidor = dirD,
    nitDistribuidor = nitD,
    telefonoDistribuidor = telD,
    web =  web,
	distribuidorId = distId
    WHERE distribuidorId = distId;
    end $$
DELIMITER ;

-- -------------------------------------------------------------------------------------CategoriaProductos
DELIMITER $$
create procedure sp_agregarCategoriaProductos(nomCat varchar(30), desCat varchar(100))

BEGIN
	insert into CategoriaProductos(nombreCateegoria, descripcionCategoria) values
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

-- ---------------------------------------------------------------------------------------------------Productos

DELIMITER $$
create procedure sp_agregarProductos(nomP varchar(50), desP varchar(100), catSt int, preVeU decimal(10,2),  preVeM decimal(10,2), preC decimal(10,2), imaP blob, distId int, catPId int)
BEGIN
	insert into Productos(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductoId) values
		    (nomP, desP, catSt, preVeU, preVeM, preC, imaP, distId, catPId );
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarProductos()
BEGIN 
	SELECT 
		Productos.productoId, 
        Productos.nombreDescripcion, 
        Productos.descripcionProducto, 
        Productos.cantidadStock, 
        Productos.precioVentaUnitario,
        Productos.precioVentaMayor, 
        Productos.precioComprea, 
        Productos.imagenProducto, 
        Productos.distribuidorId, 
        Productos.categoriaProductoId
        FROM Productos;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarProductos(proId INT)
BEGIN 
	DELETE FROM Productos
    where productoId = proId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarProductos(proId int)
BEGIN 
	SELECT*from Productos
		where productoId = proId;
	END $$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EditarProductos(proId int, nomP varchar(50), desP varchar(100), catSt int, preVeU decimal(10,2),  preVeM decimal(10,2), preC decimal(10,2), imaP blob, distId int, catPId int)
BEGIN 
UPDATE Productos
SET 
	nombreProducto = nomP,
    descripcionProducto = desP,
    cantidadStock = catSt,
    precioVentaUnitario = preVeU,
    precioVentaMayor = preVeM, 
    precioCompra = preC,
    imagenProducto = imaP,
    distribuidorId = distId,
    catedoriaProductoId = CatPId,
    productoId = proId
    WHERE productoId = proId;
    end $$
DELIMITER ;

-- ---------------------------------------------------------------------------------Promociones 

DELIMITER $$
create procedure sp_agregarPromociones(preP decimal (10,2), desP varchar(100), fecI date, fecF date, proId int )
BEGIN
	insert into Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId) values
		(preP, desP, fecI, fecF, proId);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarPromociones()
BEGIN 
	SELECT 
		Promociones.promocionId, 
        Promociones.precioPromocion, 
        Promociones.descripcionPromocion, 
        Promociones.fechaInicio, 
        Promociones.fechaFinalizacion, 
        Promociones.productoId
        FROM Promociones;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarPromociones(promId INT)
BEGIN 
	DELETE FROM Promociones
    where promocionesId = promId;
END $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE sp_BuscarPromociones(promId int)
BEGIN 
	SELECT*from Promociones
		where promocionId = promId;
	END $$
DELIMITER ;


DELIMITER $$ 
CREATE PROCEDURE sp_EditarPromociones(promId int, preP decimal (10,2), desP varchar(100), fecI date, fecF date, proId int )
BEGIN 
UPDATE Promociones
SET 
	precioPromocion = preP,
    descripcionPromocion = desP,
    fechaInicio = fecI,
    fechaFinalizacion = fecF, 
    preoductoId = proId,
   promocionId = promId
    WHERE promocionId = promId;
    end $$
DELIMITER ;


-- ----------------------------------------------------------------------------DetalleFacturas

DELIMITER $$
create procedure sp_agregarDetalleFacturas(facId int, proId int)
BEGIN
	insert into DetalleFacturas(facturaId, productoId) values
		(facId, proId );
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarDetalleFacturas()
BEGIN 
	SELECT 
		DetalleFacturas.detalleFacturaId,
		DetalleFacturas.facturaId, 
        DetalleFacturas.productoId
        FROM DetalleFacturas;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarDetalleFacturas(detFaId INT)
BEGIN 
	DELETE FROM DetalleFacturas
    where detalleFacturaId = detFaId;
END $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE sp_BuscarDetalleFacturas(detFaId int)
BEGIN 
	SELECT*from DetalleFacturas
		where detalleFacturaId = detFaId;
	END $$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EditarDetalleFacturas(detFaId int, facId int, proId int)
BEGIN 
UPDATE DetalleFacturas
SET 
	facturaId = facId,
    productoId = proId,
    detalleFacturaId = detFaId
    WHERE detalleFacturaId = detFaId;
    end $$
DELIMITER ;

-- -------------------------------------------------------------------------------------------Compras

DELIMITER $$
create procedure sp_agregarCompras(fecCom date , totC decimal(10,2))
BEGIN
	insert into Compras(fechaCompra, totalCompra) values
		(fecCom, totC );
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarCompras()
BEGIN 
	SELECT 
		Compras.compraId, 
        Compras.fechaCompra, 
        Compras.totalCompra
        FROM Compras;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarCompras(compId INT)
BEGIN 
	DELETE FROM Compras
    where compraId = compId;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarCompras(compId int)
BEGIN 
	SELECT*from Compras
		where compraId = compId;
	END $$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EditarCompras(compId int, fecCom date , totC decimal(10,2))
BEGIN 
UPDATE Compras
SET 
	fechaCompra = fecCom,
    totalCompra = totC,
    compraId = compId
    WHERE compraId = compId;
    end $$
DELIMITER ;

-- ----------------------------------------------------------------------------------------DetalleCompras

DELIMITER $$
create procedure sp_agregarDetalleCompras(cantC int, proId int , compId int)
BEGIN
	insert into Compras(cantidadCompra, productoId, compraId) values
		(cantC, proId, compId);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarDetalleCompras()
BEGIN 
	SELECT 
		DetalleCompras.detalleCompraId, 
        DetalleCompras.cantidadCompra, 
        DetalleCompras.productoId,
        DetalleCompras.compraId
        FROM DetalleCompras;
		END $$
DELIMITER ; 

DELIMITER $$
create procedure sp_eliminarDetalleCompras(detCId INT)
BEGIN 
	DELETE FROM DetalleCompra
    where detalleCompraId = detCId;
END $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE sp_BuscarDetalleCompras(detCid int)
BEGIN 
	SELECT*from DetalleCompras
		where detalleCompraId = detCId;
	END $$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EditarDetalleCompras(detCId int, cantC int, proId int , compId int)
BEGIN 
UPDATE DetalleCompras
SET 
	cantidadCompra = cantC,
    productoId = proId, 
    compraId = comId
    WHERE detalleCompraId = detCId;
    end $$
DELIMITER ;