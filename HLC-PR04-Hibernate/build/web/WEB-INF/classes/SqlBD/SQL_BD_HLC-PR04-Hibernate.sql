/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  z3rh10
 * Created: 27-may-2021
 */

CREATE TABLE USUARIOS(

	idU INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY
                                    (START WITH 1, INCREMENT BY 1),
	usu VARCHAR(20) NOT NULL,
	contra VARCHAR(20) NOT NULL,
	apodo VARCHAR(20) NOT NULL,
	telefono VARCHAR(9) NOT NULL,
	email VARCHAR(30) NOT NULL,
	diaAlta DATE NOT NULL
);


CREATE TABLE PERSONAJES(

	idP INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY
                                    (START WITH 1, INCREMENT BY 1),
	nombreP VARCHAR(40) NOT NULL,
	costeP INTEGER NOT NULL,
	rol VARCHAR(4) NOT NULL,
	lanzamientoP DATE NOT NULL
	
	
);


insert into usuarios(usu,contra,apodo,telefono,email,diaalta)VALUES('sca','sca','Sergio','600000000','sca@gmail.com','2021-05-27');
insert into personajes(nombrep, costep,rol,lanzamientop) values ('Braum',6500,'SUPP','2021-12-12');

