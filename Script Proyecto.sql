--------------------------------------------------------------------------------
/*                              PROVINCIA                                     */
--------------------------------------------------------------------------------

CREATE TABLE TB_PROVINCIA(
PROVINCIA_ID INT PRIMARY KEY,
NAME VARCHAR(100));

--------------------------------------------------------------------------------
/*                              SUCURSALES                                    */
--------------------------------------------------------------------------------

CREATE TABLE TB_SUCURSALES (
SUCURSAL_ID INT PRIMARY KEY,
NOMBRE_SUCURSAL VARCHAR(100),
PROVINCIA_ID INT,
FOREIGN KEY (PROVINCIA_ID) REFERENCES TB_PROVINCIA(PROVINCIA_ID));

--------------------------------------------------------------------------------
/*                              AUDITORIA                                    */
--------------------------------------------------------------------------------

CREATE SEQUENCE log_id_sequence
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE Audit_Log (
    Log_ID NUMBER DEFAULT log_id_sequence.NEXTVAL PRIMARY KEY,
    Table_Name VARCHAR2(50),
    Action VARCHAR2(10),
    Action_Date TIMESTAMP 
    );
    
--------------------------------------------------------------------------------
/*                              USUARIOS                                      */
--------------------------------------------------------------------------------

--TABLA USUARIOS

CREATE TABLE TB_USERS(
USER_ID INT PRIMARY KEY,
NAME_USERS VARCHAR(100),
EMAIL VARCHAR(255),
PASSWORD VARCHAR(100));

--PROCEDIMIENTO CREAR USUARIO

CREATE OR REPLACE PROCEDURE CREATE_USER(
    p_user_id IN NUMBER,
    p_Name IN VARCHAR2,
    p_Email IN VARCHAR2,
    p_password IN VARCHAR2
)
AS
BEGIN
    INSERT INTO TB_USERS(USER_ID, NAME_USERS, EMAIL, PASSWORD)
    VALUES (p_user_id,p_Name,p_Email,p_password);
    DBMS_OUTPUT.PUT_LINE('USUARIO CREADO');
END CREATE_USER;


--PROCEDIMIENTO ELIMINAR USUARIO

CREATE OR REPLACE PROCEDURE Delete_User(
    p_UserID NUMBER
) AS
BEGIN
    DELETE FROM TB_USERS
    WHERE USER_ID = p_UserID;
END;


--PROCEDIMIENTO MODIFICAR USUARIO

CREATE OR REPLACE PROCEDURE Update_User(
    P_USER_ID INT,
    P_NAME_USERS VARCHAR2,
    P_EMAIL VARCHAR2,
    P_PASSWORD VARCHAR2
)
AS
BEGIN
    UPDATE TB_USERS
    SET
        NAME_USERS = P_NAME_USERS,
        EMAIL = P_EMAIL,
        PASSWORD = P_PASSWORD
    WHERE
        USER_ID = P_USER_ID;

END Update_User;


-- FUNCION MOSTRAR USUARIOS

CREATE OR REPLACE PACKAGE types AS
  TYPE ref_cursor IS REF CURSOR;
END types;


CREATE OR REPLACE FUNCTION findAllUsers
return types.ref_cursor
as
    user_tb_cursor types.ref_cursor;    
begin 
    open user_tb_cursor for
    select USER_ID, NAME_USERS, EMAIL from TB_USERS;
RETURN user_tb_cursor;

END;

     
--------------------------------------------------------------------------------
/*                              CLIENTES                                      */
--------------------------------------------------------------------------------

--TABLA CLIENTES

CREATE TABLE TB_CLIENTES(
    CLIENTE_ID NUMBER PRIMARY KEY,
    FIRST_NAME VARCHAR(255),
    LAST_NAME VARCHAR(255),
    EMAIL VARCHAR(255),
    FECHA_NACIMIENTO DATE,
    PROVINCIA_ID NUMBER,
    ESTADO_CLIENTE NUMBER DEFAULT 1, 
    FOREIGN KEY (PROVINCIA_ID) REFERENCES TB_PROVINCIA(PROVINCIA_ID)
);


--PROCEDIMIENTO CREAR CLIENTES

CREATE OR REPLACE PROCEDURE Create_Client(
    p_CEDULA IN NUMBER,
    p_FIRST_NAME VARCHAR2,
    p_LAST_NAME VARCHAR2,
    p_EMAIL VARCHAR2,
    p_FECHA_NACIMIENTO DATE,
    p_PROVINCIA_ID NUMBER

    
) AS
BEGIN
    INSERT INTO TB_CLIENTES(CLIENTE_ID, FIRST_NAME, LAST_NAME, EMAIL, FECHA_NACIMIENTO, PROVINCIA_ID)
    VALUES (p_CEDULA, p_FIRST_NAME, p_LAST_NAME, p_EMAIL, p_FECHA_NACIMIENTO, p_PROVINCIA_ID);
END;


--PROCEDIMIENTO MODIFICAR CLIENTES

CREATE OR REPLACE PROCEDURE UPDATE_CLIENT(
    P_CLIENTE_ID NUMBER,
    P_FIRST_NAME VARCHAR2,
    P_LAST_NAME VARCHAR2,
    P_EMAIL VARCHAR2,
    P_FECHA_NACIMIENTO DATE,
    P_PROVINCIA_ID NUMBER,
    P_ESTADO_CLIENTE NUMBER
)
AS
BEGIN
    UPDATE TB_CLIENTES
    SET
        FIRST_NAME = P_FIRST_NAME,
        LAST_NAME = P_LAST_NAME,
        EMAIL = P_EMAIL,
        FECHA_NACIMIENTO = P_FECHA_NACIMIENTO,
        PROVINCIA_ID = P_PROVINCIA_ID,
        ESTADO_CLIENTE = P_ESTADO_CLIENTE
    WHERE CLIENTE_ID = P_CLIENTE_ID;


END UPDATE_CLIENT;



--PROCEDIMIENTO INACTIVAR CLIENTES

CREATE OR REPLACE PROCEDURE inactivar_Client(
    P_CLIENTE_ID NUMBER,
    P_NUEVO_ESTADO NUMBER
)
AS
BEGIN
    UPDATE TB_CLIENTES
    SET ESTADO_CLIENTE = P_NUEVO_ESTADO
    WHERE CLIENTE_ID = P_CLIENTE_ID;

END inactivar_Client;


--------------------------------------------------------------------------------
/*                           ESPECIALISTAS                                    */
--------------------------------------------------------------------------------


ALTER TABLE TB_ESPECIALISTAS
DROP COLUMN USER_ID;

ALTER TABLE TB_ESPECIALISTAS
ADD NOMBRE VARCHAR(255);

ALTER TABLE TB_ESPECIALISTAS
ADD ESTADO_ESPECIALISTA INT DEFAULT 1;


--TABLA ESPECIALISTAS

CREATE TABLE TB_ESPECIALISTAS (
ESPECIALISTA_ID INT PRIMARY KEY,
NOMBRE VARCHAR(255),
ESPECIALIDAD VARCHAR(100),
ESTADO_ESPECIALISTA NUMBER DEFAULT 1

);

--PROCEDIMIENTO CREAR ESPECIALISTAS

CREATE OR REPLACE PROCEDURE Create_Especialista(
    P_ESPECIALISTA_ID INT,
    P_NOMBRE VARCHAR2,
    P_ESPECIALIDAD VARCHAR2
)
AS
BEGIN
    INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, NOMBRE, ESPECIALIDAD)
    VALUES (P_ESPECIALISTA_ID, P_NOMBRE, P_ESPECIALIDAD);

END Create_Especialista;


--PROCEDIMIENTO MODIFICAR ESPECIALISTAS

CREATE OR REPLACE PROCEDURE Update_Especialistas(
    P_ESPECIALISTA_ID INT,
    P_NOMBRE VARCHAR2,
    P_ESPECIALIDAD VARCHAR2,
    P_NUEVO_ESTADO INT
)
AS
BEGIN
    UPDATE TB_ESPECIALISTAS
    SET
        NOMBRE = P_NOMBRE,
        ESPECIALIDAD = P_ESPECIALIDAD,
        ESTADO_ESPECIALISTA = P_NUEVO_ESTADO
    WHERE ESPECIALISTA_ID = P_ESPECIALISTA_ID;

END Update_Especialistas;



--PROCEDIMIENTO INACTIVAR ESPECIALISTAS

CREATE OR REPLACE PROCEDURE Inactivar_Especialista(
    P_ESPECIALISTA_ID INT,
    P_NUEVO_ESTADO INT
)
AS
BEGIN
    UPDATE TB_ESPECIALISTAS
    SET ESTADO_ESPECIALISTA = P_NUEVO_ESTADO
    WHERE ESPECIALISTA_ID = P_ESPECIALISTA_ID;

END Inactivar_Especialista;


--VALIDAR FUNCIONAMIENTO

BEGIN
    Create_Especialista(8, 'John', 'Cardiología');
END;

BEGIN
    Update_Especialistas(8, 'Jane', 'Neurología',0);
END;

BEGIN
    Inactivar_Especialista(8, 0);
END;



SELECT * FROM TB_ESPECIALISTAS;

--------------------------------------------------------------------------------
/*                                 CITAS                                      */
--------------------------------------------------------------------------------

CREATE TABLE TB_TIPOCITAS(
TIPOCITA_ID INT PRIMARY KEY,
DESCRIPCION VARCHAR2(300)
)

CREATE TABLE TB_APPOINTMENTS(
APPOINTMENT_ID INT PRIMARY KEY,
CLIENTE_ID INT,
ESPECIALISTA_ID INT,
FECHA DATE,
HORA VARCHAR (255),
SUCURSAL_ID INT,
TIPOCITA_ID INT,
ESTADO VARCHAR(100) DEFAULT 'Programada',
FOREIGN KEY (CLIENTE_ID) REFERENCES TB_CLIENTES(CLIENTE_ID),
FOREIGN KEY (ESPECIALISTA_ID) REFERENCES TB_ESPECIALISTAS(ESPECIALISTA_ID),
FOREIGN KEY (TIPOCITA_ID) REFERENCES TB_TIPOCITAS(TIPOCITA_ID),
FOREIGN KEY (SUCURSAL_ID) REFERENCES TB_SUCURSALES(SUCURSAL_ID));

 -- Puede ser 'Virtual', 'Presencial'.
CREATE TABLE TB_AUDITORIACITAS (
    AUDITORIA_ID INT PRIMARY KEY,
    APPOINTMENT_ID INT,
    NOMBRE VARCHAR(255),
    DESCRIPCION VARCHAR(500),
    FECHA DATE,
    FOREIGN KEY (APPOINTMENT_ID) REFERENCES TB_APPOINTMENTS(APPOINTMENT_ID));   

--crear  cita
CREATE OR REPLACE PROCEDURE Crear_Cita(
P_APPOINTMENT_ID IN NUMBER,
P_CLIENTE_ID IN NUMBER,
P_ESPECIALISTA_ID IN NUMBER,
P_FECHA DATE,
P_HORA VARCHAR2,
p_Sucursal_ID int,
p_TIPOCITA_ID int,
p_ESTADO VARCHAR2
)
AS
BEGIN
INSERT INTO TB_APPOINTMENTS(APPOINTMENT_ID,CLIENTE_ID,ESPECIALISTA_ID,FECHA,HORA,SUCURSAL_ID,TIPOCITA_ID, ESTADO)
VALUES(P_APPOINTMENT_ID,P_CLIENTE_ID,P_ESPECIALISTA_ID,P_FECHA,P_HORA,p_Sucursal_ID,p_TIPOCITA_ID,p_ESTADO);
  DBMS_OUTPUT.PUT_LINE('Cita creada');
END Crear_Cita;

-- Actualizar Detalles de una Cita
CREATE OR REPLACE PROCEDURE Update_Appointment(
    P_APPOINTMENT_ID IN NUMBER,
    P_CLIENTE_ID IN NUMBER,
    P_ESPECIALISTA_ID IN NUMBER,
    P_FECHA DATE,
    P_HORA VARCHAR2,
    p_SUCURSAL_ID int,
    p_TIPOCITA_ID int,
    p_ESTADO VARCHAR2
) AS
BEGIN
    UPDATE TB_APPOINTMENTS
    SET ESPECIALISTA_ID = P_ESPECIALISTA_ID, 
    FECHA = P_FECHA,
    HORA = P_HORA,
    SUCURSAL_ID = p_SUCURSAL_ID,
    TIPOCITA_ID = p_TIPOCITA_ID,
    ESTADO = p_ESTADO
    WHERE APPOINTMENT_ID = P_APPOINTMENT_ID;
END;


--------------------------------------------------------------------------------
/*                                 LOGIN                                      */
--------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE VALIDAR_USUARIO_CONTRASEÑA(
    P_USERNAME VARCHAR2,
    P_PASSWORD VARCHAR2,
    P_RESULTADO OUT NUMBER
)
AS
    V_COUNT NUMBER;
BEGIN
   
    SELECT COUNT(*)
    INTO V_COUNT
    FROM TB_USERS
    WHERE USER_ID = P_USERNAME AND PASSWORD = P_PASSWORD;


    IF V_COUNT > 0 THEN
        P_RESULTADO := 1;
    ELSE
        P_RESULTADO := 0;
    END IF;
END VALIDAR_USUARIO_CONTRASEÑA;



---Probar funcionalidad
SET SERVEROUTPUT ON;

SELECT * FROM TB_USERS

DECLARE
    V_RESULTADO NUMBER;
BEGIN
    VALIDAR_USUARIO_CONTRASEÑA('102', 'pass2', V_RESULTADO);
    DBMS_OUTPUT.PUT_LINE('Resultado: ' || V_RESULTADO);
END;



--------------------------------------------------------------------------------
/*                                TRIGGERS                                    */
--------------------------------------------------------------------------------

--TRIGGER DE AUDITORIA AL INSERTAR UN USUARIO
CREATE OR REPLACE TRIGGER User_Insert_Trigger
BEFORE INSERT ON TB_USERS
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_USERS', 'INSERT', CURRENT_TIMESTAMP);
END;

--TRIGGER DE AUDITORIA AL MODIFICAR UN USUARIO
CREATE OR REPLACE TRIGGER User_Update_Trigger
BEFORE UPDATE ON TB_USERS
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_USERS', 'UPDATE', CURRENT_TIMESTAMP);
END;

--TRIGGER DE AUDITORIA AL ELIMINAR UN USUARIO
CREATE OR REPLACE TRIGGER User_Delete_Trigger
BEFORE DELETE ON TB_USERS
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_USERS', 'DELETE', CURRENT_TIMESTAMP);
END;


--TRIGGER DE AUDITORIA AL INSERTAR UN CLIENTE
CREATE OR REPLACE TRIGGER Cliente_Insert_Trigger
BEFORE INSERT ON TB_CLIENTES
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_CLIENTES', 'INSERT', CURRENT_TIMESTAMP);
END;

--TRIGGER DE AUDITORIA AL MODIFICAR UN CLIENTE
CREATE OR REPLACE TRIGGER Cliente_Update_Trigger
BEFORE UPDATE ON TB_CLIENTES
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_CLIENTES', 'UPDATE', CURRENT_TIMESTAMP);
END;


--TRIGGER DE AUDITORIA AL INSERTAR UN ESPECIALISTA
CREATE OR REPLACE TRIGGER Especialista_Insert_Trigger
BEFORE INSERT ON TB_ESPECIALISTAS
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_ESPECIALISTAS', 'INSERT', CURRENT_TIMESTAMP);
END;

--TRIGGER DE AUDITORIA AL MODIFICAR UN ESPECIALISTA
CREATE OR REPLACE TRIGGER Especialista_Update_Trigger
BEFORE UPDATE ON TB_ESPECIALISTAS
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_ESPECIALISTAS', 'UPDATE', CURRENT_TIMESTAMP);
END;

--TRIGGER DE AUDITORIA AL INSERTAR UNA CITA
CREATE OR REPLACE TRIGGER Cita_Insert_Trigger
BEFORE INSERT ON TB_APPOINTMENTS
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_APPOINTMENTS', 'INSERT', CURRENT_TIMESTAMP);
END;

--TRIGGER DE AUDITORIA AL MODIFICAR UN CITA
CREATE OR REPLACE TRIGGER Cita_Update_Trigger
BEFORE UPDATE ON TB_APPOINTMENTS
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_APPOINTMENTS', 'UPDATE', CURRENT_TIMESTAMP);
END;


--------------------------------------------------------------------------------
/*                                VISTAS                                      */
--------------------------------------------------------------------------------


--Vista de Clientes y Provincias:
CREATE OR REPLACE VIEW ClientsAndPROVINCIA_ID AS
SELECT C.CLIENTE_ID, C.FIRST_NAME, C.LAST_NAME, C.FECHA_NACIMIENTO, P.NAME AS PROVINCIA_NAME
FROM TB_CLIENTES C
INNER JOIN TB_PROVINCIA P ON C.PROVINCIA_ID = P.PROVINCIA_ID;


--Vista de Clientes y Citas:
CREATE OR REPLACE VIEW ClientssAndAppointments AS
SELECT C.CLIENTE_ID, C.FIRST_NAME, A.FECHA, A.HORA
FROM TB_CLIENTES C
INNER JOIN TB_APPOINTMENTS A ON C.CLIENTE_ID = A.CLIENTE_ID;


--Vista de Citas por Provincia:
CREATE OR REPLACE VIEW AppointmentsByProvince AS
SELECT A.FECHA, A.HORA, S.NOMBRE_SUCURSAL
FROM TB_APPOINTMENTS A
INNER JOIN TB_SUCURSALES S ON S.SUCURSAL_ID = A.SUCURSAL_ID;

--------------------------------------------------------------------------------
/*                                FUNCIONES                                   */
--------------------------------------------------------------------------------

--Funcion para Calcular la Edad de un Cliente a partir de su Fecha de Nacimiento:
CREATE OR REPLACE FUNCTION CalculateAge(p_Birthdate DATE) RETURN NUMBER IS
    v_Age NUMBER;
BEGIN
    v_Age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_Birthdate) / 12);
    RETURN v_Age;
END;


--Funcion para Verificar si un Usuario Existe por su Correo Electrï¿½nico:
CREATE OR REPLACE FUNCTION UserExists(p_Email VARCHAR2) RETURN BOOLEAN IS
    v_Count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_Count
    FROM TB_USERS
    WHERE EMAIL = p_Email;
    RETURN v_Count > 0;
END;


--Funcion para Encontrar el Prï¿½ximo Disponible en una Cita:
CREATE OR REPLACE FUNCTION FindNextAvailableAppointment(p_AppointmentID NUMBER) RETURN DATE IS
    v_NextAppointment DATE;
BEGIN
    SELECT MIN(FECHA) INTO v_NextAppointment
    FROM TB_APPOINTMENTS
    WHERE APPOINTMENT_ID = p_AppointmentID AND FECHA > SYSDATE;
    RETURN v_NextAppointment;
END;


--Funcion para Calcular la Cantidad de Citas de un Cliente en una Fecha Especï¿½fica:
CREATE OR REPLACE FUNCTION CountAppointmentsOnDate(p_AppointmentID NUMBER, p_Date DATE) RETURN NUMBER IS
    v_Count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_Count
    FROM TB_APPOINTMENTS
    WHERE APPOINTMENT_ID = p_AppointmentID AND FECHA = p_Date;
    RETURN v_Count;
END;

--Funcion para Calcular la Cantidad de Clientes por Provincia:
CREATE OR REPLACE FUNCTION CountClientsInProvince(p_ProvincIAID NUMBER) RETURN NUMBER IS
    v_Count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_Count
    FROM TB_CLIENTES
    WHERE PROVINCIA_ID = p_ProvinciaID;
    RETURN v_Count;
END;


--------------------------------------------------------------------------------
/*                                CURSORES                                    */
--------------------------------------------------------------------------------

--Cursor Simple para Seleccionar Todos los Usuarios
DECLARE
    CURSOR user_cursor IS
        SELECT * FROM TB_USERS;
BEGIN
    FOR user_rec IN user_cursor LOOP
     
        DBMS_OUTPUT.PUT_LINE('USER_ID: ' || user_rec.USER_ID);
        DBMS_OUTPUT.PUT_LINE('NAME_USERS: ' || user_rec.NAME_USERS);
        DBMS_OUTPUT.PUT_LINE('EMAIL: ' || user_rec.EMAIL);
        
    END LOOP;
END;

--Cursor para Listar Citas Pendientes
CREATE OR REPLACE PROCEDURE List_Pending_Appointments_Cursor AS
    CURSOR appointment_cursor IS
        SELECT *
        FROM TB_APPOINTMENTS
        WHERE FECHA > SYSDATE;
BEGIN
    FOR rec IN appointment_cursor
    LOOP
        DBMS_OUTPUT.PUT_LINE('Appointment ID: ' || rec.APPOINTMENT_ID || ', Date: ' || rec.FECHA || ', Time: ' || rec.HORA);
    END LOOP;
END;

--Cursor para Listar Usuarios con Citas Pendientes
CREATE OR REPLACE PROCEDURE List_Users_With_Pending_Appointments_Cursor AS
    CURSOR user_cursor IS
        SELECT U.*
        FROM TB_USERS U
        JOIN TB_APPOINTMENTS A ON U.USER_ID = A.APPOINTMENT_ID;
BEGIN
    FOR rec IN user_cursor
    LOOP
        DBMS_OUTPUT.PUT_LINE('User ID: ' || rec.USER_ID || ', Name: ' || rec.NAME_USERS || ', Email: ' || rec.EMAIL);
    END LOOP;
END;

--Cursor para Obtener Informaciï¿½n del Usuario
CREATE OR REPLACE PROCEDURE Get_User_Info_Cursor(
    p_UserID NUMBER,
    p_Name OUT VARCHAR2,
    p_Email OUT VARCHAR2
) AS
    CURSOR user_cursor IS
        SELECT NAME_USERS, EMAIL
        INTO p_Name, p_Email
        FROM TB_USERS
        WHERE USER_ID = p_UserID;
BEGIN
    OPEN user_cursor;
    FETCH user_cursor INTO p_Name, p_Email;
    CLOSE user_cursor;
END;



--------------------------------------------------------------------------------
/*                                  PAQUETES                                  */
--------------------------------------------------------------------------------

--PAQUETE USUARIOS
   -- Procedimiento para crear usuarios
CREATE OR REPLACE PACKAGE Usuarios_Package AS
   PROCEDURE CREATE_USER(
      p_user_id IN NUMBER,
      p_Name IN VARCHAR2,
      p_Email IN VARCHAR2,
      p_password IN VARCHAR2
   );

   -- Procedimiento para eliminar usuarios
   PROCEDURE Delete_User(
      p_UserID NUMBER
   );

   -- Procedimiento para modificar usuarios
   PROCEDURE Update_User(
      P_USER_ID INT,
      P_NAME_USERS VARCHAR2,
      P_EMAIL VARCHAR2,
      P_PASSWORD VARCHAR2
   );

   -- Función para mostrar todos los usuarios
   FUNCTION findAllUsers RETURN types.ref_cursor;
END Usuarios_Package;


CREATE OR REPLACE PACKAGE BODY Usuarios_Package AS
   -- Procedimiento para crear usuarios
   PROCEDURE CREATE_USER(
      p_user_id IN NUMBER,
      p_Name IN VARCHAR2,
      p_Email IN VARCHAR2,
      p_password IN VARCHAR2
   ) AS
   BEGIN
      INSERT INTO TB_USERS(USER_ID, NAME_USERS, EMAIL, PASSWORD)
      VALUES (p_user_id, p_Name, p_Email, p_password);
      DBMS_OUTPUT.PUT_LINE('USUARIO CREADO');
   END CREATE_USER;

   -- Procedimiento para eliminar usuarios
   PROCEDURE Delete_User(
      p_UserID NUMBER
   ) AS
   BEGIN
      DELETE FROM TB_USERS
      WHERE USER_ID = p_UserID;
   END Delete_User;

   -- Procedimiento para modificar usuarios
   PROCEDURE Update_User(
      P_USER_ID INT,
      P_NAME_USERS VARCHAR2,
      P_EMAIL VARCHAR2,
      P_PASSWORD VARCHAR2
   ) AS
   BEGIN
      UPDATE TB_USERS
      SET
         NAME_USERS = P_NAME_USERS,
         EMAIL = P_EMAIL,
         PASSWORD = P_PASSWORD
      WHERE
         USER_ID = P_USER_ID;
   END Update_User;

   -- Función para mostrar todos los usuarios
   FUNCTION findAllUsers RETURN types.ref_cursor AS
      user_tb_cursor types.ref_cursor;
   BEGIN
      OPEN user_tb_cursor FOR
         SELECT USER_ID, NAME_USERS, EMAIL FROM TB_USERS;
      RETURN user_tb_cursor;
   END findAllUsers;
END Usuarios_Package;


--PAQUETE CLIENTES

CREATE OR REPLACE PACKAGE Clientes_Package AS
   PROCEDURE Create_Client(
      p_CEDULA IN NUMBER,
      p_FIRST_NAME VARCHAR2,
      p_LAST_NAME VARCHAR2,
      p_EMAIL VARCHAR2,
      p_FECHA_NACIMIENTO DATE,
      p_PROVINCIA_ID NUMBER
   );

   PROCEDURE Update_Client(
      P_CLIENTE_ID NUMBER,
      P_FIRST_NAME VARCHAR2,
      P_LAST_NAME VARCHAR2,
      P_EMAIL VARCHAR2,
      P_FECHA_NACIMIENTO DATE,
      P_PROVINCIA_ID NUMBER,
      P_ESTADO_CLIENTE NUMBER
   );

   PROCEDURE Inactivar_Client(
      P_CLIENTE_ID NUMBER,
      P_NUEVO_ESTADO NUMBER
   );
END Clientes_Package;


CREATE OR REPLACE PACKAGE BODY Clientes_Package AS
   PROCEDURE Create_Client(
      p_CEDULA IN NUMBER,
      p_FIRST_NAME VARCHAR2,
      p_LAST_NAME VARCHAR2,
      p_EMAIL VARCHAR2,
      p_FECHA_NACIMIENTO DATE,
      p_PROVINCIA_ID NUMBER
   ) AS
   BEGIN
      INSERT INTO TB_CLIENTES(CLIENTE_ID, FIRST_NAME, LAST_NAME, EMAIL, FECHA_NACIMIENTO, PROVINCIA_ID)
      VALUES (p_CEDULA, p_FIRST_NAME, p_LAST_NAME, p_EMAIL, p_FECHA_NACIMIENTO, p_PROVINCIA_ID);
   END Create_Client;

   PROCEDURE Update_Client(
      P_CLIENTE_ID NUMBER,
      P_FIRST_NAME VARCHAR2,
      P_LAST_NAME VARCHAR2,
      P_EMAIL VARCHAR2,
      P_FECHA_NACIMIENTO DATE,
      P_PROVINCIA_ID NUMBER,
      P_ESTADO_CLIENTE NUMBER
   ) AS
   BEGIN
      UPDATE TB_CLIENTES
      SET
         FIRST_NAME = P_FIRST_NAME,
         LAST_NAME = P_LAST_NAME,
         EMAIL = P_EMAIL,
         FECHA_NACIMIENTO = P_FECHA_NACIMIENTO,
         PROVINCIA_ID = P_PROVINCIA_ID,
         ESTADO_CLIENTE = P_ESTADO_CLIENTE
      WHERE CLIENTE_ID = P_CLIENTE_ID;
   END Update_Client;

   PROCEDURE Inactivar_Client(
      P_CLIENTE_ID NUMBER,
      P_NUEVO_ESTADO NUMBER
   ) AS
   BEGIN
      UPDATE TB_CLIENTES
      SET ESTADO_CLIENTE = P_NUEVO_ESTADO
      WHERE CLIENTE_ID = P_CLIENTE_ID;
   END Inactivar_Client;
END Clientes_Package;








--PAQUETE ESPECIALISTAS

CREATE OR REPLACE PACKAGE Especialistas_Package AS
   -- Procedimiento para crear especialistas
   PROCEDURE Create_Especialista(
      P_ESPECIALISTA_ID INT,
      P_NOMBRE VARCHAR2,
      P_ESPECIALIDAD VARCHAR2
   );

   -- Procedimiento para modificar especialistas
   PROCEDURE Update_Especialistas(
      P_ESPECIALISTA_ID INT,
      P_NOMBRE VARCHAR2,
      P_ESPECIALIDAD VARCHAR2,
      P_NUEVO_ESTADO INT
   );

   -- Procedimiento para inactivar especialistas
   PROCEDURE Inactivar_Especialista(
      P_ESPECIALISTA_ID INT,
      P_NUEVO_ESTADO INT
   );
END Especialistas_Package;


CREATE OR REPLACE PACKAGE BODY Especialistas_Package AS
   -- Procedimiento para crear especialistas
   PROCEDURE Create_Especialista(
      P_ESPECIALISTA_ID INT,
      P_NOMBRE VARCHAR2,
      P_ESPECIALIDAD VARCHAR2
   ) AS
   BEGIN
      INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, NOMBRE, ESPECIALIDAD)
      VALUES (P_ESPECIALISTA_ID, P_NOMBRE, P_ESPECIALIDAD);
   END Create_Especialista;

   -- Procedimiento para modificar especialistas
   PROCEDURE Update_Especialistas(
      P_ESPECIALISTA_ID INT,
      P_NOMBRE VARCHAR2,
      P_ESPECIALIDAD VARCHAR2,
      P_NUEVO_ESTADO INT
   ) AS
   BEGIN
      UPDATE TB_ESPECIALISTAS
      SET
         NOMBRE = P_NOMBRE,
         ESPECIALIDAD = P_ESPECIALIDAD,
         ESTADO_ESPECIALISTA = P_NUEVO_ESTADO
      WHERE ESPECIALISTA_ID = P_ESPECIALISTA_ID;
   END Update_Especialistas;

   -- Procedimiento para inactivar especialistas
   PROCEDURE Inactivar_Especialista(
      P_ESPECIALISTA_ID INT,
      P_NUEVO_ESTADO INT
   ) AS
   BEGIN
      UPDATE TB_ESPECIALISTAS
      SET ESTADO_ESPECIALISTA = P_NUEVO_ESTADO
      WHERE ESPECIALISTA_ID = P_ESPECIALISTA_ID;
   END Inactivar_Especialista;
END Especialistas_Package;


--------------------------------------------------------------------------------
/*                           INSERCION DE DATOS                               */
--------------------------------------------------------------------------------

-- Inserts para TB_PROVINCIA
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (1, 'San José');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (2, 'Alajuela');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (3, 'Heredia');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (4, 'Puntarenas');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (5, 'Cartago');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (6, 'Limón');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (7, 'Guanacaste');

-- Inserts para TB_SUCURSALES
INSERT INTO TB_SUCURSALES (SUCURSAL_ID, NOMBRE_SUCURSAL, PROVINCIA_ID) VALUES (1, 'Sucursal1', 1);
INSERT INTO TB_SUCURSALES (SUCURSAL_ID, NOMBRE_SUCURSAL, PROVINCIA_ID) VALUES (2, 'Sucursal2', 2);
INSERT INTO TB_SUCURSALES (SUCURSAL_ID, NOMBRE_SUCURSAL, PROVINCIA_ID) VALUES (3, 'Sucursal3', 3);
INSERT INTO TB_SUCURSALES (SUCURSAL_ID, NOMBRE_SUCURSAL, PROVINCIA_ID) VALUES (4, 'Sucursal4', 4);

-- Inserts para TB_USERS
INSERT INTO TB_USERS (USER_ID,  NAME_USERS, EMAIL, PASSWORD) VALUES (859,  'User 1', 'user1@example.com', 'pass1');
INSERT INTO TB_USERS (USER_ID,  NAME_USERS, EMAIL, PASSWORD) VALUES (102,  'User 2', 'user2@example.com', 'pass2');
INSERT INTO TB_USERS (USER_ID,  NAME_USERS, EMAIL, PASSWORD) VALUES (103,  'User 3', 'user3@example.com', 'pass3');
INSERT INTO TB_USERS (USER_ID,  NAME_USERS, EMAIL, PASSWORD) VALUES (104,  'User 4', 'user4@example.com', 'pass4');


-- Inserts para TB_ESPECIALISTAS
INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, NOMBRE, ESPECIALIDAD) VALUES (52, 'Edgar Castro', 'Especialidad1');
INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, NOMBRE, ESPECIALIDAD) VALUES (2, 'Edgar Castro', 'Especialidad2');
INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, NOMBRE, ESPECIALIDAD) VALUES (3, 'Edgar Castro', 'Especialidad3');
INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, NOMBRE, ESPECIALIDAD) VALUES (4, 'Edgar Castro', 'Especialidad3');


-- Inserts para TB_CLIENTES
INSERT INTO TB_CLIENTES (CLIENTE_ID,  FIRST_NAME, last_name, EMAIL,  fecha_Nacimiento,   PROVINCIA_ID) VALUES (1,  'Cliente 1', 'Apellido 1',  'user1@example.com', '25-SEP-23', 1);
INSERT INTO TB_CLIENTES (CLIENTE_ID,  FIRST_NAME, last_name, EMAIL, fecha_Nacimiento,   PROVINCIA_ID) VALUES (2,  'Cliente 2', 'Apellido 2',  'user2@example.com', '20-MAY-20', 2);
INSERT INTO TB_CLIENTES (CLIENTE_ID,  FIRST_NAME, last_name, EMAIL, fecha_Nacimiento,   PROVINCIA_ID) VALUES (3,  'Cliente 3', 'Apellido 3',  'user3@example.com', '05-OCT-21', 3);
INSERT INTO TB_CLIENTES (CLIENTE_ID,  FIRST_NAME, last_name, EMAIL, fecha_Nacimiento,   PROVINCIA_ID) VALUES (4,  'Cliente 4', 'Apellido 4',  'user4@example.com', '15-SEP-20', 4);

-- Inserts para TB_TIPOCITAS
INSERT INTO TB_TIPOCITAS (TIPOCITA_ID, DESCRIPCION) VALUES (1, 'Virtual');
INSERT INTO TB_TIPOCITAS (TIPOCITA_ID, DESCRIPCION) VALUES (2, 'Presencial');

-- Inserts para TB_APPOINTMENTS
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA , SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (1, 25, 8, '10-NOV-23', '10:00 AM', 1, 1, 'Programada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA , SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (2, 25, 8, '11-NOV-23', '02:30 PM', 2, 2, 'Realizada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA , SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (3, 25, 8, '16-NOV-23', '10:00 AM', 3, 2, 'Cancelada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA , SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (4, 25, 8, '17-NOV-23', '02:30 PM', 4, 1, 'Realizada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA , SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (5, 30, 8, '10-NOV-23', '10:00 AM', 1, 1, 'Programada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA , SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (6, 34, 8, '11-NOV-23', '02:30 PM', 2, 2, 'Realizada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA , SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (7, 30, 8, '16-NOV-23', '10:00 AM', 3, 2, 'Cancelada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA , SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (8, 34, 8, '17-NOV-23', '02:30 PM', 4, 1, 'Realizada');

-- Inserts para TB_AUDITORIACITAS
INSERT INTO TB_AUDITORIACITAS (AUDITORIA_ID, APPOINTMENT_ID, NOMBRE, DESCRIPCION, FECHA) VALUES (1, 1, '', '','11-NOV-23');
INSERT INTO TB_AUDITORIACITAS (AUDITORIA_ID, APPOINTMENT_ID, NOMBRE, DESCRIPCION, FECHA) VALUES (2, 2, '', '', '10-NOV-23');
INSERT INTO TB_AUDITORIACITAS (AUDITORIA_ID, APPOINTMENT_ID, NOMBRE, DESCRIPCION, FECHA) VALUES (3, 3, '', '', '10-NOV-23');
INSERT INTO TB_AUDITORIACITAS (AUDITORIA_ID, APPOINTMENT_ID, NOMBRE, DESCRIPCION, FECHA) VALUES (4, 4, '', '', '11-NOV-23');
