# CRUDMedico

```sql
DROP database IF EXISTS gestorconsultas;
create database gestorconsultas;
use gestorconsultas;
-- Eliminar tablas existentes (respeta orden por claves foráneas)
DROP TABLE IF EXISTS tratamiento;
DROP TABLE IF EXISTS consulta;
DROP TABLE IF EXISTS users;

-- Crear tabla users
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    dni VARCHAR(15),
    birth_year INT,
    phone VARCHAR(20),
    address VARCHAR(255),
    role ENUM('paciente', 'admin') NOT NULL DEFAULT 'paciente',
    password VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla consulta
CREATE TABLE consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    is_first BOOLEAN,
    exploracion TEXT,
    pruebas_extra TEXT,
    juicio_clinico TEXT,
    tratamiento TEXT,
    fecha DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Crear tabla tratamiento
CREATE TABLE tratamiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    consulta_id INT NOT NULL,
    tipo_dieta VARCHAR(100),
    tipo_ejercicio VARCHAR(100),
    tipo_medicamento VARCHAR(100),
    tipo_estilo_vida VARCHAR(100),
    FOREIGN KEY (consulta_id) REFERENCES consulta(id)
);

-- Inserts básicos de ejemplo

-- Insert en users
INSERT INTO users (username, email, dni, birth_year, phone, address, role, password)
VALUES 
('jdoe', 'jdoe@example.com', '12345678A', 1985, '600123456', 'Calle Falsa 123', 'admin', 'pass123'),
('asmith', 'asmith@example.com', '87654321B', 1990, '600654321', 'Avenida Siempreviva 742', 'paciente', 'pass456');

-- Insert en consulta
INSERT INTO consulta (user_id, is_first, exploracion, pruebas_extra, juicio_clinico, tratamiento, fecha)
VALUES 
(1, TRUE, 'Exploración inicial', 'Análisis de sangre', 'Juicio clínico 1', 'Tratamiento inicial', '2025-07-09 10:00:00'),
(2, FALSE, 'Revisión', 'Radiografía', 'Juicio clínico 2', 'Tratamiento de seguimiento', '2025-07-10 12:00:00');

-- Insert en tratamiento
INSERT INTO tratamiento (consulta_id, tipo_dieta, tipo_ejercicio, tipo_medicamento, tipo_estilo_vida)
VALUES
(1, 'Dieta mediterránea', 'Caminar 30 min/día', 'Ibuprofeno', 'No fumar'),
(2, 'Baja en azúcares', 'Natación', 'Paracetamol', 'Dormir 8 horas');
