DROP DATABASE IF EXISTS HOTELMANAGMENT;
CREATE DATABASE HOTELMANAGMENT;

USE HOTELMANAGMENT;

create table tblHotel(

	idHotel int primary key not null,
    NombreHotel nvarchar(60) not null,
    NumeroCuartos int not null,
    Descripcion TEXT not null,
    Precio float not null

);

Insert into tblHotel values(1, 'HotelTest', 2, 'El mejor Hotel Test', 45.89),(2, 'HotelTest2', 3, 'El mejor Hotel Test2', 102.89),(3, 'HotelTest3', 2, 'El mejor Hotel Test3', 78.35),(4, 'HotelTest4', 80, 'El mejor Hotel Test4', 45.89);

create table tblUser(

	idUser int primary key not null,
    Usuario nvarchar(60) not null,
    Contrasena nvarchar(60) not null,
    Nombre nvarchar(60) not null,
    Apellidos nvarchar(60) not null,
    tipoUser enum('Administrador', 'Turista')

);

insert into tblUser values(1,'test', md5('test'), 'Christian', 'Ontiveros', 'Turista'), (2, 'admin', md5('admin'), 'AdminNombre', 'AdminApellidos', 'Administrador');
Select * from tblUser;

create table tblFavs(

	idFav int primary key not null,
    idUser int not null,
    idHotel int not null,
    foreign key (idUser) references tblUser(idUser),
    foreign key (idHotel) references tblHotel(idHotel)

);

create table tblComentario(

	idComentario int primary key not null,
    comentario nvarchar(60) not null,
    idHotel int not null,
    idUser int not null,
    img nvarchar(60) not null,
    foreign key (idUser) references tblUser(idUser),
    foreign key (idHotel) references tblHotel(idHotel)

);

Insert into tblcomentario values(1,'Fantastica experiencia', 1,1,'assets\\images\\01.jpg'), (2,'Volvería a venir', 1,1,'assets\\images\\03.jpg'), (3,'Estuvo divertido', 1,1,'');

create table tblReservation(

	idreservation int primary key not null,
    idHotel int not null,
    idUser int not null,
    fecha date not null,
    foreign key (idUser) references tblUser(idUser),
    foreign key (idHotel) references tblHotel(idHotel)

);

delimiter //

	drop procedure if exists spLogin//
    create procedure spLogin(IN Usuarioxd nvarchar(60), IN Contrasenaxd nvarchar(60))
    BEGIN
    
		Select IFNULL((Select idUser from tblUser where Usuario = Usuarioxd AND Contrasena = md5(Contrasenaxd)),-1) as Mensaje, IFNULL((Select tipoUser from tblUser where Usuario = Usuarioxd AND Contrasena = md5(Contrasenaxd)),'Nothing') as Rol;
    
    END //
    
    drop procedure if exists spDesplegarHoteles//
    create procedure spDesplegarhoteles()
    BEGIN 
    
		Select * from tblHotel;
        
    END//
    
    drop procedure if exists spBuscarHoteles//
    create procedure spBuscarHoteles(IN queryString nvarchar(60))
    BEGIN
    
		Select * from tblHotel where NombreHotel LIKE concat('%',queryString,'%') OR NumeroCuartos LIKE concat('%',queryString,'%') OR Descripcion LIKE concat('%',queryString,'%');
    
    END //
    
    drop procedure if exists spDesplegarInfohoteles//
    create procedure spDesplegarInfohoteles(IN idHotelxd int)
    BEGIN
    
		Select * from tblHotel where idHotel = idHotelxd;
    
    END //

	drop procedure if exists spAgregarFavs//

	CREATE PROCEDURE spAgregarFavs(IN idHotelxd int, IN idUserxd int)
    BEGIN
    
		declare idSiguiente int;
        Select IFNULL((Select MAX(idFav) from tblfavs)+1,1) INTO idSiguiente;
        IF((Select idFav from tblfavs where idUser = idUserxd AND idHotel = idHotelxd) is null)
        THEN
			Insert into tblfavs values(idSiguiente, idUserxd, idHotelxd);
		END IF;
    
    END //
    
    drop procedure if exists spDespliegaComentsPorHotel//
    
    create procedure spDespliegaComentsPorHotel(IN idHotelxd int)
    BEGIN 
    
		Select m1.idComentario, m1.comentario, m1.img, CONCAT(m2.Nombre, ' ', m2.Apellidos) as Nombre
        from tblcomentario m1
        INNER JOIN tbluser m2
        ON m1.idUser = m2.idUser
        WHERE m1.idHotel = idHotelxd;
    
    END //
    
    drop procedure if exists spAgregarComentario//
    
    create procedure spAgregarComentario(IN idHotelxd int, IN idUserxd int, IN comentarioxd TEXT, IN imgxd nvarchar(60))
    BEGIN
    
		Declare idSiguiente int;
        Select IFNULL((Select MAX(idComentario) from tblcomentario)+1, 1) INTO idSiguiente;
        Insert into tblcomentario values(idSiguiente, comentarioxd, idHotelxd, idUserxd, imgxd);
    
    END //
    
    drop procedure if exists spAgregarReservacion//
    
    create procedure spAgregarReservacion(IN idHotelxd int, IN idUserxd int, IN fechaxd date)
    BEGIN
    
		Declare idSiguiente int;
        Select IFNULL((Select MAX(idreservation) from tblreservation)+1, 1) INTO idSiguiente;
    
		Insert into tblreservation values(idSiguiente, idHotelxd, idUserxd, fechaxd);
    
    END //

	drop procedure if exists spGetProfileInfo//
    create procedure spGetProfileInfo(IN idUserxd int)
    BEGIN
    
		Select CONCAT(Apellidos, ' ', Nombre) as Nombre from tbluser where idUser = idUserxd;
    
    END //

	drop procedure if exists spDesplegarFavoritosUser//
    create procedure spDesplegarFavoritosUser(In idUserxd int)
    BEGIN
    
		Select m2.NombreHotel, m2.Descripcion, m2.idHotel 
        from tblHotel m2
        INNER JOIN tblfavs m1
        ON m1.idHotel = m2.idHotel
        WHERE m1.idUser = idUserxd;
    
    END //
    
    drop procedure if exists spDesplegarReservaciones//
    
    create procedure spDesplegarReservaciones(IN idUserxd int)
    BEGIN
    
		Select m2.NombreHotel, m1.fecha, m1.idReservation
        FROM tblreservation m1
        INNER JOIN tblHotel m2
        ON m1.idHotel = m2.idHotel
        WHERE m1.idUser = idUserxd;
    
    END //
    
    drop procedure if exists spEliminarReservaciones//
    
    create procedure spEliminarReservaciones(IN idReservacionxd int)
    BEGIN
    
		Delete from tblreservation where idReservation = idReservacionxd;
    
    END //
    
    drop procedure if exists spAgregarUser//
    
    create procedure spAgregarUser(IN userxd nvarchar(60), IN passxd nvarchar(60), IN namexd nvarchar(60), IN lastnamexd nvarchar(60))
    BEGIN
    
		Declare idSiguiente int;
        Select IFNULL((Select MAX(iduser) from tbluser)+1, 1) INTO idSiguiente;
        IF((Select idUser from tbluser where usuario = userxd) is null)
        THEN
        
			Insert into tbluser values(idSiguiente, userxd, md5(passxd), namexd, lastnamexd, 'Turista');
            
		else
        
			SET idSiguiente = 0;
        
        END IF;
        Select idSiguiente as Mensaje;
    
    END //

	drop procedure if exists spCrearHotel//
    
    create procedure spCrearHotel(In NombreHotelxd nvarchar(60), IN NumeroCuartosxd int, IN Descripcionxd TEXT, IN Precioxd float)
    BEGIN
    
		Declare idSiguiente int;
        Select IFNULL((Select MAX(idHotel) from tblHotel)+1, 1) INTO idSiguiente;
        
        Insert into tblHotel values(idSiguiente, NombreHotelxd, NumeroCuartosxd, Descripcionxd, Precioxd);
    
    END //

delimiter ;

Select * from tblhotel;

Select * from tblreservation;
#call spDesplegarInfohoteles(1);
Select * from tblfavs;
/*call spLogin('test', 'test');
call spBuscarHoteles('2');
call spDesplegarhoteles();*/