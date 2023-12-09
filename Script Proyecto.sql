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
    CLIENTE_ID INT PRIMARY KEY,
    FIRST_NAME VARCHAR(255),
    LAST_NAME VARCHAR(255),
    EMAIL VARCHAR(255),
    FECHA_NACIMIENTO DATE,
    PROVINCIA_ID INT,
    ESTADO_CLIENTE INT DEFAULT 1, 
    FOREIGN KEY (PROVINCIA_ID) REFERENCES TB_PROVINCIA(PROVINCIA_ID)
);


--PROCEDIMIENTO CREAR CLIENTES
CREATE OR REPLACE PROCEDURE Create_Client(
    p_CEDULA IN NUMBER,
    p_FIRST_NAME VARCHAR2,
    p_LAST_NAME VARCHAR2,
    p_EMAIL VARCHAR2,
    p_FECHA_NACIMIENTO DATE,
    p_PROVINCIA_ID INT

    
) AS
BEGIN
    INSERT INTO TB_CLIENTES(CLIENTE_ID, FIRST_NAME, LAST_NAME, EMAIL, FECHA_NACIMIENTO, PROVINCIA_ID)
    VALUES (p_CEDULA, p_FIRST_NAME, p_LAST_NAME, p_EMAIL, p_FECHA_NACIMIENTO, p_PROVINCIA_ID);
END;



--PROCEDIMIENTO INACTIVAR CLIENTES
CREATE OR REPLACE PROCEDURE inactivar_Client(
    P_CLIENTE_ID INT,
    P_NUEVO_ESTADO INT
)
AS
BEGIN
    UPDATE TB_CLIENTES
    SET ESTADO_CLIENTE = P_NUEVO_ESTADO
    WHERE CLIENTE_ID = P_CLIENTE_ID;

END inactivar_Client;


--PROCEDIMIENTO MODIFICAR CLIENTES
CREATE OR REPLACE PROCEDURE UPDATE_CLIENT(
    P_CLIENTE_ID INT,
    P_FIRST_NAME VARCHAR2,
    P_LAST_NAME VARCHAR2,
    P_EMAIL VARCHAR2,
    P_FECHA_NACIMIENTO DATE,
    P_PROVINCIA_ID INT,
    P_ESTADO_CLIENTE INT
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



--------------------------------------------------------------------------------
/*                           ESPECIALISTAS                                    */
--------------------------------------------------------------------------------
ALTER TABLE TB_ESPECIALISTAS
ADD ESTADO_ESPECIALISTA INT DEFAULT 1;


--TABLA ESPECIALISTAS
CREATE TABLE TB_ESPECIALISTAS (
ESPECIALISTA_ID INT PRIMARY KEY,
USER_ID INT,
ESPECIALIDAD VARCHAR(100),
ESTADO_ESPECIALISTA INT DEFAULT 1,
FOREIGN KEY (USER_ID) REFERENCES TB_USERS(USER_ID));




PROCEDIMIENTO 
CREATE OR REPLACE PROCEDURE Create_Especialista(
    P_ESPECIALISTA_ID INT,
    P_USER_ID INT,
    P_ESPECIALIDAD VARCHAR2
)
AS
BEGIN
    INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, USER_ID, ESPECIALIDAD)
    VALUES (P_ESPECIALISTA_ID, P_USER_ID, P_ESPECIALIDAD);

    COMMIT;
END Create_Especialista;


















CREATE TABLE TB_PROVINCIA(
PROVINCIA_ID INT PRIMARY KEY,
NAME VARCHAR(100));


CREATE TABLE TB_SUCURSALES (
SUCURSAL_ID INT PRIMARY KEY,
NOMBRE_SUCURSAL VARCHAR(100),
PROVINCIA_ID INT,
FOREIGN KEY (PROVINCIA_ID) REFERENCES TB_PROVINCIA(PROVINCIA_ID));

CREATE TABLE TB_TIPOCITAS (
    TIPOCITA_ID INT PRIMARY KEY,
    NOMBRE_TIPOCITA VARCHAR(50));

CREATE TABLE TB_APPOINTMENTS(
APPOINTMENT_ID INT PRIMARY KEY,
CLIENTE_ID INT,
ESPECIALISTA_ID INT,
FECHA DATE,
HORA VARCHAR (255),
PROVINCIA_ID INT,
SUCURSAL_ID INT,
TIPOCITA_ID INT,
ESTADO VARCHAR(20) DEFAULT 'Programada', -- Puede ser 'Programada', 'Realizada', 'Cancelada', etc.
FOREIGN KEY (CLIENTE_ID) REFERENCES TB_CLIENTES(CLIENTE_ID),
FOREIGN KEY (ESPECIALISTA_ID) REFERENCES TB_ESPECIALISTAS(ESPECIALISTA_ID),
FOREIGN KEY (TIPOCITA_ID) REFERENCES TB_TIPOCITAS(TIPOCITA_ID),
FOREIGN KEY (SUCURSAL_ID) REFERENCES TB_SUCURSALES(SUCURSAL_ID),
FOREIGN KEY (PROVINCIA_ID) REFERENCES TB_PROVINCIA(PROVINCIA_ID));


CREATE TABLE TB_AUDITORIACITAS (
    AUDITORIA_ID INT PRIMARY KEY,
    APPOINTMENT_ID INT,
    NOMBRE VARCHAR(255),
    DESCRIPCION VARCHAR(500),
    FECHA DATE,
    FOREIGN KEY (APPOINTMENT_ID) REFERENCES TB_APPOINTMENTS(APPOINTMENT_ID));   

-- Procedimientos Almacenados 

-- Iniciar Sesiï¿½n
CREATE OR REPLACE PROCEDURE Login_User(
    p_Email VARCHAR2,
    p_Password VARCHAR2,
    p_Usuario OUT NUMBER
) AS
BEGIN
    SELECT USER_ID INTO p_Usuario
    FROM TB_USERS
    WHERE EMAIL = p_Email AND PASSWORD = p_Password;
END;




-- Sacar Cita
-- Crear Secuencia para Citas
CREATE SEQUENCE APPOINTMENT_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;


-- Programar Cita
CREATE OR REPLACE PROCEDURE Schedule_Appointment(
    p_Cliente NUMBER,
    p_Especialista NUMBER,
    p_Date DATE,
    p_Time VARCHAR2,
    p_ProvinceID NUMBER,
    p_Sucursal NUMBER,
    p_TipoCita NUMBER,
    p_Estado Varchar2,
    p_AppointmentID OUT NUMBER
    
) AS
BEGIN
    INSERT INTO TB_APPOINTMENTS(APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID,FECHA, HORA, PROVINCIA_ID,SUCURSAL_ID,TIPOCITA_ID,ESTADO)
    VALUES (APPOINTMENT_SEQ.NEXTVAL, p_Cliente,p_Especialista, p_Date, p_Time, p_ProvinceID,p_Sucursal,p_TipoCita,p_Estado)
    RETURNING APPOINTMENT_ID INTO p_AppointmentID;
END;



-- Borrar Cita
CREATE OR REPLACE PROCEDURE Cancel_Appointment(
    p_AppointmentID NUMBER
) AS
BEGIN
    DELETE FROM TB_APPOINTMENTS
    WHERE APPOINTMENT_ID = p_AppointmentID;
END;



-- Listar Citas de un Usuario
CREATE OR REPLACE PROCEDURE List_User_Appointments(
    p_UserID NUMBER
) AS
BEGIN
    FOR rec IN (SELECT * FROM TB_APPOINTMENTS WHERE ID_SESSIONS = p_UserID) 
    LOOP
        DBMS_OUTPUT.PUT_LINE('Appointment ID: ' || rec.APPOINTMENT_ID || ', Date: ' || rec.FECHA || ', Time: ' || rec.HORA);
    END LOOP;
END;



-- Obtener Detalles de una Cita
CREATE OR REPLACE PROCEDURE Get_Appointment_Details(
    p_AppointmentID NUMBER,
    p_Date OUT DATE,
    p_Time OUT VARCHAR2,
    p_ProvinceID OUT NUMBER
) AS
BEGIN
    SELECT FECHA, HORA, PROVINCIA_ID INTO p_Date, p_Time, p_ProvinceID
    FROM TB_APPOINTMENTS
    WHERE APPOINTMENT_ID = p_AppointmentID;
END;

-- Listar Todas las Citas
CREATE OR REPLACE PROCEDURE List_All_Appointments AS
BEGIN
    FOR rec IN (SELECT * FROM TB_APPOINTMENTS) 
    LOOP
        DBMS_OUTPUT.PUT_LINE('Appointment ID: ' || rec.APPOINTMENT_ID || ', Date: ' || rec.FECHA || ', Time: ' || rec.HORA);
    END LOOP;
END;



-- Actualizar Detalles de una Cita
CREATE OR REPLACE PROCEDURE Update_Appointment_Details(
    p_AppointmentID NUMBER,
    p_NewDate DATE,
    p_NewTime VARCHAR2,
    p_NewProvinceID NUMBER
) AS
BEGIN
    UPDATE TB_APPOINTMENTS
    SET FECHA = p_NewDate, HORA = p_NewTime, PROVINCIA_ID = p_NewProvinceID
    WHERE APPOINTMENT_ID = p_AppointmentID;
END;

-- Obtener Detalles de un Usuario por Correo Electrï¿½nico
CREATE OR REPLACE PROCEDURE Get_User_DetailsByEmail(
    p_Email VARCHAR2,
    p_UserID OUT NUMBER,
    p_Name OUT VARCHAR2
) AS
BEGIN
    SELECT USER_ID, NAME_USERS INTO p_UserID, p_Name
    FROM TB_USERS
    WHERE EMAIL = p_Email;
END;

-- Obtener Detalles de una Cita por Fecha
CREATE OR REPLACE PROCEDURE Get_Appointment_DetailsByDate(
    p_Date DATE
) AS
BEGIN
    FOR rec IN (SELECT * FROM TB_APPOINTMENTS WHERE FECHA = p_Date) 
    LOOP
        DBMS_OUTPUT.PUT_LINE('Appointment ID: ' || rec.APPOINTMENT_ID || ', Date: ' || rec.FECHA || ', Time: ' || rec.HORA);
    END LOOP;
END;

-- Obtener Citas Programadas para Hoy
CREATE OR REPLACE PROCEDURE Get_AppointmentsForToday AS
BEGIN
    FOR rec IN (SELECT * FROM TB_APPOINTMENTS WHERE FECHA = TRUNC(SYSDATE)) 
    LOOP
        DBMS_OUTPUT.PUT_LINE('Appointment ID: ' || rec.APPOINTMENT_ID || ', Date: ' || rec.FECHA || ', Time: ' || rec.HORA);
    END LOOP;
END;


--vistas


--Vista de Clientes y Provincias:
CREATE OR REPLACE VIEW ClientsAndPROVINCIA_ID AS
SELECT C.CLIENTE_ID, C.FIRST_NAME, C.LAST_NAME, C.fechanacimiento, P.NAME AS PROVINCIA_NAME
FROM TB_CLIENTES C
INNER JOIN TB_PROVINCIA P ON C.PROVINCIA_ID = P.PROVINCIA_ID;


--Vista de Clientes y Citas:
CREATE OR REPLACE VIEW ClientssAndAppointments AS
SELECT C.CLIENTE_ID, C.FIRST_NAME, A.FECHA, A.HORA
FROM TB_CLIENTES C
INNER JOIN TB_APPOINTMENTS A ON C.CLIENTE_ID = A.CLIENTE_ID;


--Vista de Citas por Provincia:
CREATE OR REPLACE VIEW AppointmentsByProvince AS
SELECT A.FECHA, A.HORA, P.NAME AS PROVINCIA_NAME
FROM TB_APPOINTMENTS A
INNER JOIN TB_PROVINCIA P ON A.PROVINCIA_ID = P.PROVINCIA_ID;



--Funciones

--Funciï¿½n para Calcular la Edad de un Cliente a partir de su Fecha de Nacimiento:
CREATE OR REPLACE FUNCTION CalculateAge(p_Birthdate DATE) RETURN NUMBER IS
    v_Age NUMBER;
BEGIN
    v_Age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_Birthdate) / 12);
    RETURN v_Age;
END;


--Funciï¿½n para Verificar si un Usuario Existe por su Correo Electrï¿½nico:
CREATE OR REPLACE FUNCTION UserExists(p_Email VARCHAR2) RETURN BOOLEAN IS
    v_Count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_Count
    FROM TB_USERS
    WHERE EMAIL = p_Email;
    RETURN v_Count > 0;
END;


--Funciï¿½n para Encontrar el Prï¿½ximo Disponible en una Cita:
CREATE OR REPLACE FUNCTION FindNextAvailableAppointment(p_AppointmentID NUMBER) RETURN DATE IS
    v_NextAppointment DATE;
BEGIN
    SELECT MIN(FECHA) INTO v_NextAppointment
    FROM TB_APPOINTMENTS
    WHERE APPOINTMENT_ID = p_AppointmentID AND FECHA > SYSDATE;
    RETURN v_NextAppointment;
END;


--Funciï¿½n para Calcular la Cantidad de Citas de un Cliente en una Fecha Especï¿½fica:
CREATE OR REPLACE FUNCTION CountAppointmentsOnDate(p_AppointmentID NUMBER, p_Date DATE) RETURN NUMBER IS
    v_Count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_Count
    FROM TB_APPOINTMENTS
    WHERE APPOINTMENT_ID = p_AppointmentID AND FECHA = p_Date;
    RETURN v_Count;
END;

--Funciï¿½n para Calcular el Promedio de Edades de los Clientes en una Provincia:
CREATE OR REPLACE FUNCTION CalculateAverageAgeInProvince(p_ProvinciaID NUMBER) RETURN NUMBER IS
    v_AvgAge NUMBER;
BEGIN
    SELECT AVG(CalculateAge(C.fechanacimiento)) INTO v_AvgAge
    FROM TB_CLIENTES C
    WHERE C.PROVINCIA_ID = p_ProvinciaID;
    RETURN v_AvgAge;
END;

--Funciï¿½n para Calcular la Cantidad de Clientes por Provincia:
CREATE OR REPLACE FUNCTION CountClientsInProvince(p_ProvincIAID NUMBER) RETURN NUMBER IS
    v_Count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_Count
    FROM TB_CLIENTES
    WHERE PROVINCIA_ID = p_ProvinciaID;
    RETURN v_Count;
END;




--triggers

CREATE TABLE Audit_Log (
    Log_ID NUMBER PRIMARY KEY,
    Table_Name VARCHAR2(50),
    Action VARCHAR2(10),
    Action_Date DATE);

--Un trigger que se activa antes de insertar un nuevo registro en la tabla TB_USERS y registra la fecha y hora de la acciï¿½n en otra tabla.

CREATE OR REPLACE TRIGGER User_Insert_Trigger
BEFORE INSERT ON TB_USERS
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_USERS', 'INSERT', SYSDATE);
END;
/

--Un trigger que se activa antes de eliminar un registro en la tabla TB_USERS y registra la fecha y hora de la acciï¿½n en otra tabla.
CREATE OR REPLACE TRIGGER User_Delete_Trigger
BEFORE DELETE ON TB_USERS
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_USERS', 'DELETE', SYSDATE);
END;

--Un trigger que se activa antes de insertar un nuevo registro en la tabla TB_CLIENTES y registra la fecha y hora de la acciï¿½n en otra tabla.
CREATE OR REPLACE TRIGGER Cliente_Insert_Trigger
BEFORE INSERT ON TB_CLIENTES
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_CLIENTES', 'INSERT', SYSDATE);
END;


--Un trigger que se activa antes de actualizar un registro en la tabla TB_CLIENTES y registra la fecha y hora de la acciï¿½n en otra tabla.
CREATE OR REPLACE TRIGGER Cliente_Update_Trigger
BEFORE UPDATE ON TB_CLIENTES
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_CLIENTES', 'UPDATE', SYSDATE);
END;

--Un trigger que se activa antes de eliminar un registro en la tabla TB_CLIENTES y registra la fecha y hora de la acciï¿½n en otra tabla.
CREATE OR REPLACE TRIGGER Cliente_Delete_Trigger
BEFORE DELETE ON TB_CLIENTES
FOR EACH ROW
BEGIN
    INSERT INTO Audit_Log (Table_Name, Action, Action_Date)
    VALUES ('TB_CLIENTES', 'DELETE', SYSDATE);
END;

--Cursor

--Cursor Simple para Seleccionar Todos los Usuarios
DECLARE
    CURSOR user_cursor IS
        SELECT * FROM TB_USERS;
BEGIN
    FOR user_rec IN user_cursor LOOP
        -- Procesa los registros aquï¿½
        -- Por ejemplo, puedes imprimir informaciï¿½n sobre cada registro:
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

--Cursor para Listar Citas de una Provincia
CREATE OR REPLACE PROCEDURE List_Appointments_By_Province_Cursor(
    p_ProvinciaID NUMBER
) AS
    CURSOR appointment_cursor IS
        SELECT *
        FROM TB_APPOINTMENTS
        WHERE PROVINCIA_ID = p_ProvinciaID;
BEGIN
    FOR rec IN appointment_cursor
    LOOP
        DBMS_OUTPUT.PUT_LINE('Appointment ID: ' || rec.APPOINTMENT_ID || ', Date: ' || rec.FECHA || ', Time: ' || rec.HORA);
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

----CREAR CURSOR PARA ENLISTAR USUARIOS


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

---------
CREATE OR REPLACE PROCEDURE Buscar_Cita(
    p_APPOINTMENT_ID OUT NUMBER,
    p_CLIENTE_ID IN NUMBER,
    p_FECHA_CITA IN DATE,
    p_SUCURSAL_ID IN NUMBER,
    P_TIPOCITA_ID IN NUMBER,
    p_ESTADO VARCHAR2,
    p_RESULTADO OUT VARCHAR2
) AS
BEGIN
    -- Seleccionar la información de la cita
    SELECT APPOINTMENT_ID
    INTO p_APPOINTMENT_ID
    FROM TB_APPOINTMENTS
    WHERE APPOINTMENT_ID = p_APPOINTMENT_ID
        AND FECHA = p_FECHA_CITA
        AND SUCURSAL_ID=p_SUCURSAL_ID
        AND TIPOCITA_ID=P_TIPOCITA_ID
        AND ESTADO = p_ESTADO;

    -- Verificar si se encontró la cita
    IF p_CITA_ID IS NOT NULL THEN
        p_RESULTADO := 'Cita encontrada';
    ELSE
        p_RESULTADO := 'Cita no encontrada';
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Manejo de excepción si no se encuentra ninguna cita
        p_RESULTADO := 'Cita no encontrada';
    WHEN OTHERS THEN
        -- Manejo de otras excepciones
        p_RESULTADO := 'Error al buscar la cita';
END Buscar_Cita;
CREATE OR REPLACE PROCEDURE Crear_Cita(
    p_APPOINTMENTID INT,  -- Agregado el prefijo 'p' al nombre del parámetro
    p_ClienteID INT,
    p_EspecialistaID INT,
    p_Fecha DATE,
    p_Fecha DATE,
    p_Hora VARCHAR(255),
    p_ProvinciaID INT,
    p_SucursalID INT,
    p_TipoCitaID INT
)
BEGIN
    -- Insertar la nueva cita
    INSERT INTO TB_APPOINTMENTS (
        APPOINTMENT_ID,  -- Agregado el campo APPOINTMENT_ID
        CLIENTE_ID,
        ESPECIALISTA_ID,
        FECHA,
        FECHA,
        HORA,
        PROVINCIA_ID,
        SUCURSAL_ID,
        TIPOCITA_ID
    ) VALUES (
        p_APPOINTMENT_ID,
        p_ClienteID,
        p_EspecialistaID,
        p_Fecha,
        p_Fecha,
        p_Hora,
        p_ProvinciaID,
        p_SucursalID,
        p_TipoCitaID
    );

    -- Llamar a otro procedimiento o realizar acciones adicionales según sea necesario
    -- En este ejemplo, llamaremos a un procedimiento que obtiene los apellidos y el nombre de la sucursal.
    CALL Obtener_Apellidos(p_ClienteID);
    CALL Obtener_Nombre_Sucursal(p_SucursalID);
END;

------------------------------------------------------------------------------------------

-- Inserts para TB_USERS
INSERT INTO TB_USERS (USER_ID,  NAME_USERS, EMAIL, PASSWORD) VALUES (101,  'User 1', 'user1@example.com', 'pass1');
INSERT INTO TB_USERS (USER_ID,  NAME_USERS, EMAIL, PASSWORD) VALUES (102,  'User 2', 'user2@example.com', 'pass2');
INSERT INTO TB_USERS (USER_ID,  NAME_USERS, EMAIL, PASSWORD) VALUES (103,  'User 3', 'user3@example.com', 'pass3');
INSERT INTO TB_USERS (USER_ID,  NAME_USERS, EMAIL, PASSWORD) VALUES (104,  'User 4', 'user4@example.com', 'pass4');

-- Inserts para TB_PROVINCIA
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (1, 'San José');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (2, 'Alajuela');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (3, 'Heredia');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (4, 'Puntarenas');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (5, 'Cartago');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (6, 'Limón');
INSERT INTO TB_PROVINCIA (PROVINCIA_ID, NAME) VALUES (7, 'Guanacaste');

-- Inserts para TB_CLIENTES
INSERT INTO TB_CLIENTES (CLIENTE_ID,  FIRST_NAME, last_name, EMAIL,  fechaNacimiento,   PROVINCIA_ID) VALUES (1,  'Cliente 1', 'Apellido 1',  'user1@example.com', '25-SEP-23', 1);
INSERT INTO TB_CLIENTES (CLIENTE_ID,  FIRST_NAME, last_name, EMAIL, fechaNacimiento,   PROVINCIA_ID) VALUES (2,  'Cliente 2', 'Apellido 2',  'user2@example.com', '20-MAY-20', 2);
INSERT INTO TB_CLIENTES (CLIENTE_ID,  FIRST_NAME, last_name, EMAIL, fechaNacimiento,   PROVINCIA_ID) VALUES (3,  'Cliente 3', 'Apellido 3',  'user3@example.com', '05-OCT-21', 3);
INSERT INTO TB_CLIENTES (CLIENTE_ID,  FIRST_NAME, last_name, EMAIL, fechaNacimiento,   PROVINCIA_ID) VALUES (4,  'Cliente 4', 'Apellido 4',  'user4@example.com', '15-SEP-20', 4);


-- Inserts para TB_ESPECIALISTAS
INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, USER_ID, ESPECIALIDAD) VALUES (1, 101, 'Especialidad1');
INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, USER_ID, ESPECIALIDAD) VALUES (2, 102, 'Especialidad2');
INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, USER_ID, ESPECIALIDAD) VALUES (3, 103, 'Especialidad3');
INSERT INTO TB_ESPECIALISTAS (ESPECIALISTA_ID, USER_ID, ESPECIALIDAD) VALUES (4, 104, 'Especialidad4');


-- Inserts para TB_SUCURSALES
INSERT INTO TB_SUCURSALES (SUCURSAL_ID, NOMBRE_SUCURSAL, PROVINCIA_ID) VALUES (1, 'Sucursal1', 1);
INSERT INTO TB_SUCURSALES (SUCURSAL_ID, NOMBRE_SUCURSAL, PROVINCIA_ID) VALUES (2, 'Sucursal2', 2);
INSERT INTO TB_SUCURSALES (SUCURSAL_ID, NOMBRE_SUCURSAL, PROVINCIA_ID) VALUES (3, 'Sucursal3', 3);
INSERT INTO TB_SUCURSALES (SUCURSAL_ID, NOMBRE_SUCURSAL, PROVINCIA_ID) VALUES (4, 'Sucursal4', 4);



-- Inserts para TB_TIPOCITAS
INSERT INTO TB_TIPOCITAS (TIPOCITA_ID, NOMBRE_TIPOCITA) VALUES (1, 'Virtual');
INSERT INTO TB_TIPOCITAS (TIPOCITA_ID, NOMBRE_TIPOCITA) VALUES (2, 'Presencial');


-- Inserts para TB_APPOINTMENTS
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA, PROVINCIA_ID, SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (1, 1, 1, '10-NOV-23', '10:00 AM', 1, 1, 1, 'Programada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA, PROVINCIA_ID, SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (2, 2, 2, '11-NOV-23', '02:30 PM', 2, 2, 2, 'Realizada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA, PROVINCIA_ID, SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (3, 3, 3, '10-NOV-23', '10:00 AM', 3, 3, 2, 'Cancelada');
INSERT INTO TB_APPOINTMENTS (APPOINTMENT_ID, CLIENTE_ID, ESPECIALISTA_ID, FECHA, HORA, PROVINCIA_ID, SUCURSAL_ID, TIPOCITA_ID, ESTADO) VALUES (4, 4, 4, '11-NOV-23', '02:30 PM', 4, 4, 1, 'Realizada');


-- Inserts para TB_AUDITORIACITAS
INSERT INTO TB_AUDITORIACITAS (AUDITORIA_ID, APPOINTMENT_ID, NOMBRE, DESCRIPCION, FECHA) VALUES (1, 1, '', '','11-NOV-23');
INSERT INTO TB_AUDITORIACITAS (AUDITORIA_ID, APPOINTMENT_ID, NOMBRE, DESCRIPCION, FECHA) VALUES (2, 2, '', '', '10-NOV-23');
INSERT INTO TB_AUDITORIACITAS (AUDITORIA_ID, APPOINTMENT_ID, NOMBRE, DESCRIPCION, FECHA) VALUES (3, 3, '', '', '10-NOV-23');
INSERT INTO TB_AUDITORIACITAS (AUDITORIA_ID, APPOINTMENT_ID, NOMBRE, DESCRIPCION, FECHA) VALUES (4, 4, '', '', '11-NOV-23');
