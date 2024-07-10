CREATE DATABASE BancoCentral;
USE BancoCentral;

-- Crear la tabla Cliente
CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL
);

-- Crear la tabla Tarjeta
CREATE TABLE Tarjeta (
    id INT PRIMARY KEY,
    numero_tarjeta VARCHAR(20) NOT NULL,
    fecha_expiracion DATE NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    facilitador VARCHAR(20) NOT NULL,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

-- Crear la tabla Compra
CREATE TABLE Compra (
    id INT PRIMARY KEY,
    fecha DATE NOT NULL,
    monto DOUBLE NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    tarjeta_id INT,
    FOREIGN KEY (tarjeta_id) REFERENCES Tarjeta(id)
);