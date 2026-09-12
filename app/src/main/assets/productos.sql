BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS "ventas" (
    "codigo" char(12) UNIQUE,
    "nombre" varchar(200),
    "precio" decimal,
    "cantidad" integer,
    "tipo" varchar(20),
    "fecha_venta" datetime
);

INSERT INTO "ventas"
("codigo","nombre","precio","cantidad","tipo","fecha_venta")
VALUES
('xyz001','laptop gamer php',2500,1,'factura','20/07/2026'),
('xyz002','teclado lagtech',70,1,'boleta','14/08/2026'),
('xyz003','mouse gamer',120,2,'boleta','20/08/2026'),
('xyz004','monitor 24',650,1,'factura','25/08/2026'),
('xyz005','audifonos gamer',180,1,'boleta','30/08/2026');

COMMIT;