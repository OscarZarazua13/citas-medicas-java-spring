--------------------------------------------------------------------------------------------------
---------------------------CREACION DE LA TABLA PARA SECCIONES DE LA APP--------------------------
--------------------------------------------------------------------------------------------------

--CREAR LA SECUENCIA PARA LAS SECCIONES DE LA APP
CREATE SEQUENCE IF NOT EXISTS id_doctor_seq;

--INICIALIZAR LA SECUENCIA
ALTER SEQUENCE id_doctor_seq RESTART;


CREATE TABLE IF NOT EXISTS doctor(
	doctor_id     INTEGER NOT NULL UNIQUE DEFAULT nextval('id_doctor_seq'),
	nombre   VARCHAR(50) NOT NULL,
    apellido_paterno   VARCHAR(50) NOT NULL,
    apellido_materno   VARCHAR(50) NOT NULL,
    especialidad        VARCHAR(50) NOT NULL,
	CONSTRAINT doctor_id_pkey PRIMARY KEY (doctor_id) 
);



--------------------------------------------------------------------------------------------------
---------------------------CREACION DE LA TABLA PARA CONSULTORIOS--------------------------
--------------------------------------------------------------------------------------------------

--CREAR LA SECUENCIA PARA LAS SECCIONES DE LA APP
CREATE SEQUENCE IF NOT EXISTS id_consultorio_seq;

--INICIALIZAR LA SECUENCIA
ALTER SEQUENCE id_consultorio_seq RESTART;


CREATE TABLE IF NOT EXISTS consultorio(
	consultorio_id     INTEGER NOT NULL UNIQUE DEFAULT nextval('id_consultorio_seq'),
	numero   INTEGER NOT NULL,
    piso   INTEGER NOT NULL,
	CONSTRAINT consultorio_id_pkey PRIMARY KEY (consultorio_id) 
);



--------------------------------------------------------------------------------------------------
---------------------------CREACION DE LA TABLA PARA CITAS--------------------------
--------------------------------------------------------------------------------------------------

--CREAR LA SECUENCIA PARA LAS SECCIONES DE LA APP
CREATE SEQUENCE IF NOT EXISTS id_cita_seq;

--INICIALIZAR LA SECUENCIA
ALTER SEQUENCE id_cita_seq RESTART;


CREATE TABLE IF NOT EXISTS cita(
	cita_id     INTEGER NOT NULL UNIQUE DEFAULT nextval('id_cita_seq'),
	horario_consulta   TIMESTAMP NOT NULL,
    nombre_paciente   VARCHAR(50) NOT NULL,
    consultorio_id INTEGER NOT NULL,
    doctor_id INTEGER NOT NULL,
	CONSTRAINT cita_id_pkey PRIMARY KEY (cita_id),
    FOREIGN KEY (consultorio_id) REFERENCES consultorio (consultorio_id),
    FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id)
);


INSERT INTO doctor (nombre, apellido_paterno, apellido_materno, especialidad)
VALUES 
('Adrian', 'Zarazua', 'Gómez', 'Medicina General'),
('Ana', 'Garibay', 'Sanchez', 'Cardiologia'),
('Oscar', 'Mendez', 'Martinez', 'Neurologia');



INSERT INTO consultorio (numero, piso)
VALUES 
(8, 1),
(12, 1),
(30, 2);