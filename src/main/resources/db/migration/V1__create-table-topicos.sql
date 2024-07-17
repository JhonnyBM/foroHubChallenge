CREATE TABLE topicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    estado VARCHAR(50) NOT NULL,
    autor VARCHAR(255),
    curso VARCHAR(255),
    respuestas VARCHAR(255)
);
