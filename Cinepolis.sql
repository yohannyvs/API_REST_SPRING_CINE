create database cinepolis

use cinepolis

create table usuario (
id_cedula varchar(50) primary key,
nombre varchar(50),
correo varchar(50),
pass varchar(50)
)

create table pelicula(
id_peli int primary key identity(1,1),
nombre varchar(50),
categoria varchar(50),
idioma varchar(50),
imagen varchar(MAX)
)

create table horario(
id_hora int primary key identity (1,1),
hora varchar(50)
)

create table sala(
id_sala int primary key identity(1,1),
numero_sala varchar(50)
)

create table presentacion (
id_presentacion int primary key identity(1,1),
nombre_pelicula varchar(50),
horario varchar(50),
sala varchar(50)
)

create table factura(
id_factura int primary key identity (1,1),
id_cedula varchar(50),
total int 
)

create table tiquete(
id_tiquete int primary key identity (1,1),
id_presentacion int,
aciento int
)

create table factura_tiquete(
id_factura int,
id_tiquete int
)

create table admin (
id_cedula varchar(50) primary key,
nombre varchar(50),
correo varchar(50),
pass varchar(50)
)

create table ventas(
id_admin varchar(50),
id_tiquete int
)

create table compras(
id_cedula varchar(50),
id_tiquete int)

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

CREATE PROCEDURE login

	@id varchar (50),
	@pass varchar(50)
AS
BEGIN
	
	SET NOCOUNT ON;
	
	if exists (SELECT * FROM usuario where id_cedula=@id AND pass=@pass)
	 begin
	 
	 SELECT 1 as 'respuesta'
	 
	 end
	 
	 else if exists(SELECT *FROM admin where id_cedula=@id AND pass=@pass)
	 begin
	 
	 SELECT 2 as 'respuesta'
		
	 end
else 
SELECT 3 as 'respuesta'
 
 
END

++++++++++++++++++++++++++++++++++++++++++++++++++++++
CREATE PROCEDURE insert_usuario

	@id_cedula varchar (50),
	@nombre varchar(50),
	@correo varchar(50),
	@pass varchar(50)
AS
BEGIN
	
	
	
if exists (SELECT * FROM usuario where id_cedula=@id_cedula )
begin
	 
	 SELECT 2 as 'respuesta'
	 
end
	 
	 
else 
begin

	INSERT INTO usuario VALUES(@id_cedula,@nombre,@correo,@pass)
	SELECT 1 as 'respuesta'
	
end

END
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
CREATE PROCEDURE insert_admin

	@id_cedula varchar (50),
	@nombre varchar(50),
	@correo varchar(50),
	@pass varchar(50)
AS
BEGIN
	
	
	
if exists (SELECT * FROM admin where id_cedula=@id_cedula )
begin
	 
	 SELECT 2 as 'respuesta'
	 
end
	 
	 
else 
begin

	INSERT INTO admin VALUES(@id_cedula,@nombre,@correo,@pass)
	SELECT 1 as 'respuesta'
	
end

END
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

CREATE PROCEDURE insert_peli
	@nombre varchar(50),
	@categoria varchar(50),
	@idioma varchar(50),
	@imagen varchar(MAX)
AS
BEGIN
	
	
	
if exists (SELECT * FROM pelicula where nombre=@nombre AND idioma=@idioma)
begin
	 
	 SELECT 2 as 'respuesta'
	 
end
	 
	 
else 
begin

	INSERT INTO pelicula VALUES(@nombre,@categoria,@idioma,@imagen)
	SELECT 1 as 'respuesta'
	
end

END


++++++++++++++++++++++++++++++++++++++++++++++++++
CREATE PROCEDURE insert_presentacion
	@id_pelicula int,
	@hora varchar(50),
	@sala varchar(50)
AS
BEGIN
	declare 
	@nombre_peli varchar(50)
	
	
if exists (SELECT * FROM pelicula where id_peli=@id_pelicula )
begin
	
	SET @nombre_peli=(SELECT nombre FROM pelicula where id_peli=@id_pelicula)

	INSERT INTO presentacion VALUES(@nombre_peli,@hora,@sala)
	SELECT 1 as 'respuesta'
	 
end
	 
	 
else 
begin

	SELECT 2 as 'respuesta'
	
end

END

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

CREATE PROCEDURE compra_taquilla(
@id_admin varchar(50),
@id_presentacion int,
@aciento int,
@cedula varchar(50),
@factura int)
AS
BEGIN

DECLARE
@num_factura int,
@num_tiquete int

--busca si existe la presentacion
IF EXISTS (SELECT * FROM presentacion where id_presentacion=@id_presentacion)
BEGIN

	--busca asiento libre
	IF EXISTS (SELECT *FROM tiquete inner join presentacion on tiquete.id_presentacion=presentacion.id_presentacion where tiquete.aciento=@aciento AND presentacion.id_presentacion=@id_presentacion )
	BEGIN
	--aciento ocupado
	SELECT '' as 'respuesta'
	END
	--aciento libre
	ELSE
	BEGIN
		if(@factura=0)
		begin
			INSERT INTO factura VALUES (@cedula,4000)
			
			SET @num_factura=(SELECT IDENT_CURRENT('factura'))
			
			INSERT INTO tiquete VALUES(@id_presentacion,@aciento)
			
			SET @num_tiquete=(SELECT IDENT_CURRENT('tiquete'))
			
			INSERT INTO factura_tiquete VALUES(@num_factura,@num_tiquete)
			
			INSERT INTO ventas VALUES (@id_admin,@num_tiquete)
			
			INSERT INTO compras VALUES (@cedula,@num_tiquete)
			
			-- compra lista 
			SELECT '' as 'respuesta'
			
		end
		ELSE
		BEGIN
			UPDATE factura SET total=(total+4000)where id_factura=@factura
			
			INSERT INTO tiquete VALUES(@id_presentacion,@aciento)
			
			SET @num_tiquete=(SELECT IDENT_CURRENT('tiquete'))
			
			INSERT INTO factura_tiquete VALUES(@factura,@num_tiquete)
			
			INSERT INTO ventas VALUES (@id_admin,@num_tiquete)
			
			INSERT INTO compras VALUES (@cedula,@num_tiquete)
			
			-- se agrego a la factura ya existente 
			SELECT '' as 'respuesta'
			
			 
		END
	 END
	 
END
--no existe presentacion 
ELSE
BEGIN
SELECT '' as 'respuesta'
END 

	
END
GO
+++++++++++++++++++++++++++++++++++++++++++++++++++++++

CREATE PROCEDURE update_peli
	@id_peli int,
	@nombre varchar(50),
	@categoria varchar(50),
	@idioma varchar(50)
AS
BEGIN
	
	
	
if exists (SELECT * FROM pelicula where id_peli=@id_peli )
begin
	 UPDATE pelicula SET nombre=@nombre, categoria=@categoria, idioma=@idioma where id_peli=@id_peli
	 SELECT 1 as 'respuesta'
	 
end
	 
	 
else 
begin

	
	SELECT 2 as 'respuesta'
	
end

END
++++++++++++++++++++++++++++++++++++++++++++++++++++
CREATE PROCEDURE update_presentacion
	@id_presentacion int,
	@id_pelicula int,
	@hora varchar(50),
	@sala varchar(50)
AS
BEGIN
	declare 
	@nombre_peli varchar(50)
	
	
if exists (SELECT * FROM presentacion where id_presentacion=@id_presentacion )
begin
	
	SET @nombre_peli=(SELECT nombre FROM pelicula where id_peli=@id_pelicula)

	SELECT 2 as 'respuesta'
	 
end
	 
	 
else 
begin

	SET @nombre_peli=(SELECT nombre FROM pelicula where id_peli=@id_pelicula)
	UPDATE presentacion SET nombre_pelicula=@nombre_peli,horario=@hora,sala=@sala WHERE id_presentacion=@id_presentacion
	SELECT 1 as 'respuesta'
	
end

END
