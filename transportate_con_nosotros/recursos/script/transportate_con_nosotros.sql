-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-11-2021 a las 00:15:56
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `transportate_con_nosotros`
--
CREATE DATABASE IF NOT EXISTS `transportate_con_nosotros` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `transportate_con_nosotros`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `gestionarCatalogos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `gestionarCatalogos` (IN `p_datos` VARCHAR(100))  BEGIN
Declare identificador int;
Set AUTOCOMMIT = 0;    

START TRANSACTION;

select id into identificador from secuencia_catalogo for update;

insert into catalogos_viajes values(identificador,YEAR(sysdate()));

 select replace(p_datos,'-',identificador) into p_datos;
 call gestionarinserciondinamica('rutas',p_datos);

 Update secuencia_catalogo set id = id + 1;

COMMIT WORK;


END$$

DROP PROCEDURE IF EXISTS `gestionarDestinos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `gestionarDestinos` (IN `p_identificador` VARCHAR(20), IN `p_nombre` VARCHAR(35))  BEGIN
DECLARE existencia int;

Set AUTOCOMMIT = 0;    

START TRANSACTION;

SELECT COUNT(1) INTO existencia FROM destinos WHERE identificacion=p_identificador;

IF(existencia>0) THEN
	UPDATE destinos SET nombre=p_nombre WHERE identificacion=p_identificador;
ELSE 

	insert into destinos values(p_identificador, p_nombre); 

END IF;

COMMIT WORK;

END$$

DROP PROCEDURE IF EXISTS `gestionarinserciondinamica`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `gestionarinserciondinamica` (IN `p_tabla` VARCHAR(100), IN `p_datos` VARCHAR(10000))  BEGIN

DECLARE i  INT;
DECLARE fila  VARCHAR(255);
Declare longitudFila int;

Set i = 1;

loop_filas:LOOP

	Select SPLIT_STR(p_datos, '/', i) into fila;
    select length(fila) into longitudFila;
    
     if (longitudFila = 0) THEN

        leave loop_filas;
        
      else  
    
    	SET @sentenciaSQL = CONCAT( "INSERT INTO ", p_tabla ," VALUES (", fila, ");" );
      PREPARE insertar FROM @sentenciaSQL;
      EXECUTE insertar;
    
    end if;
	
    set i = i + 1;
End loop;
End$$

--
-- Funciones
--
DROP FUNCTION IF EXISTS `SPLIT_STR`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `SPLIT_STR` (`x` VARCHAR(255), `delim` VARCHAR(12), `pos` INT) RETURNS VARCHAR(255) CHARSET latin1 RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos),
       LENGTH(SUBSTRING_INDEX(x,delim, pos-1)) + 1), delim, '')$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignaciones`
--
-- Creación: 18-11-2021 a las 19:36:28
--

DROP TABLE IF EXISTS `asignaciones`;
CREATE TABLE `asignaciones` (
  `identificacion` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `cedula_empleado` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `placa_transporte` varchar(8) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `catalogos_viajes`
--
-- Creación: 18-11-2021 a las 19:36:28
-- Última actualización: 20-11-2021 a las 19:43:34
--

DROP TABLE IF EXISTS `catalogos_viajes`;
CREATE TABLE `catalogos_viajes` (
  `identificacion` int(11) NOT NULL,
  `Annorealizado` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `catalogos_viajes`
--

INSERT INTO `catalogos_viajes` (`identificacion`, `Annorealizado`) VALUES
(10, 2021),
(11, 2021),
(12, 2021),
(13, 2021);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comunicaciones`
--
-- Creación: 18-11-2021 a las 19:36:28
--

DROP TABLE IF EXISTS `comunicaciones`;
CREATE TABLE `comunicaciones` (
  `cedula_empleado` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `id_tipo` varchar(12) COLLATE latin1_spanish_ci NOT NULL,
  `numero` varchar(14) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos`
--
-- Creación: 18-11-2021 a las 19:36:28
--

DROP TABLE IF EXISTS `destinos`;
CREATE TABLE `destinos` (
  `identificacion` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(35) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `destinos`
--

INSERT INTO `destinos` (`identificacion`, `nombre`) VALUES
('MON', 'Monteria'),
('SIN', 'Sincelejo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_ruta`
--
-- Creación: 18-11-2021 a las 19:36:28
--

DROP TABLE IF EXISTS `detalles_ruta`;
CREATE TABLE `detalles_ruta` (
  `identificacion_ruta` varchar(35) COLLATE latin1_spanish_ci NOT NULL,
  `codigo_destino` varchar(20) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--
-- Creación: 18-11-2021 a las 19:36:28
-- Última actualización: 20-11-2021 a las 22:19:18
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE `empleados` (
  `cedula` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `codigo_tipo_empleado` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  `nombres` varchar(35) COLLATE latin1_spanish_ci NOT NULL,
  `apellidos` varchar(35) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`cedula`, `codigo_tipo_empleado`, `nombres`, `apellidos`) VALUES
('1009', '1', 'Fernando', 'Ruiz'),
('1090', '2', 'Andres', 'Benitez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medios_transportes`
--
-- Creación: 18-11-2021 a las 19:36:28
--

DROP TABLE IF EXISTS `medios_transportes`;
CREATE TABLE `medios_transportes` (
  `codigo` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(40) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajes`
--
-- Creación: 18-11-2021 a las 19:36:28
--

DROP TABLE IF EXISTS `pasajes`;
CREATE TABLE `pasajes` (
  `tiquete` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `identificacion_viaje` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  `cedula_funcionario` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `fecha_venta` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutas`
--
-- Creación: 19-11-2021 a las 17:39:15
-- Última actualización: 20-11-2021 a las 19:43:34
--

DROP TABLE IF EXISTS `rutas`;
CREATE TABLE `rutas` (
  `codigo` varchar(35) COLLATE latin1_spanish_ci NOT NULL,
  `identificacion_catalogos` int(11) NOT NULL,
  `identificacion_origen` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `identificacion_destino` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `forma_ruta` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `rutas`
--

INSERT INTO `rutas` (`codigo`, `identificacion_catalogos`, `identificacion_origen`, `identificacion_destino`, `forma_ruta`, `precio`) VALUES
('RU1', 10, 'SIN', 'MON', 'Directo', 2000),
('RU10', 13, 'SIN', 'MON', 'Directo', 2000),
('RU11', 13, 'MON', 'SIN', 'Directo', 2000),
('RU2', 10, 'MON', 'SIN', 'Directo', 2000),
('RU3', 11, 'SIN', 'MON', 'Directo', 2000),
('RU4', 11, 'MON', 'SIN', 'Directo', 2000),
('RU5', 12, 'SIN', 'MON', 'Directo', 2000),
('RU6', 12, 'MON', 'SIN', 'Directo', 2000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `secuencia_catalogo`
--
-- Creación: 19-11-2021 a las 17:34:51
-- Última actualización: 20-11-2021 a las 19:43:34
--

DROP TABLE IF EXISTS `secuencia_catalogo`;
CREATE TABLE `secuencia_catalogo` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `secuencia_catalogo`
--

INSERT INTO `secuencia_catalogo` (`id`) VALUES
(14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_comunicacion`
--
-- Creación: 18-11-2021 a las 19:36:28
--

DROP TABLE IF EXISTS `tipos_comunicacion`;
CREATE TABLE `tipos_comunicacion` (
  `identificacion` varchar(12) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(30) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_empleados`
--
-- Creación: 18-11-2021 a las 19:36:28
-- Última actualización: 20-11-2021 a las 21:01:10
--

DROP TABLE IF EXISTS `tipos_empleados`;
CREATE TABLE `tipos_empleados` (
  `codigo` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(40) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `tipos_empleados`
--

INSERT INTO `tipos_empleados` (`codigo`, `nombre`) VALUES
('1', 'Conductor'),
('2', 'Funcionario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transportes`
--
-- Creación: 18-11-2021 a las 19:36:28
--

DROP TABLE IF EXISTS `transportes`;
CREATE TABLE `transportes` (
  `placa` varchar(8) COLLATE latin1_spanish_ci NOT NULL,
  `codigo_medios_transporte` varchar(30) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--
-- Creación: 18-11-2021 a las 19:36:28
-- Última actualización: 20-11-2021 a las 23:12:01
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `cedula_funcionario` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  `contrasena` varchar(140) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`cedula_funcionario`, `email`, `contrasena`) VALUES
('1090', 'andres@gmail.com', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajes`
--
-- Creación: 18-11-2021 a las 19:36:28
--

DROP TABLE IF EXISTS `viajes`;
CREATE TABLE `viajes` (
  `identificacion` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  `identificacion_asignaciones` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `codigo_ruta` varchar(35) COLLATE latin1_spanish_ci NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignaciones`
--
ALTER TABLE `asignaciones`
  ADD PRIMARY KEY (`identificacion`),
  ADD KEY `asignaciones_empleado_fk` (`cedula_empleado`),
  ADD KEY `asignaciones_transportes_fk` (`placa_transporte`);

--
-- Indices de la tabla `catalogos_viajes`
--
ALTER TABLE `catalogos_viajes`
  ADD PRIMARY KEY (`identificacion`);

--
-- Indices de la tabla `comunicaciones`
--
ALTER TABLE `comunicaciones`
  ADD UNIQUE KEY `cedula_empleado` (`cedula_empleado`,`numero`),
  ADD KEY `comunicaciones_tipo_fk` (`id_tipo`);

--
-- Indices de la tabla `destinos`
--
ALTER TABLE `destinos`
  ADD PRIMARY KEY (`identificacion`);

--
-- Indices de la tabla `detalles_ruta`
--
ALTER TABLE `detalles_ruta`
  ADD UNIQUE KEY `identificacion_ruta` (`identificacion_ruta`,`codigo_destino`),
  ADD KEY `detallesR_destinos_fk` (`codigo_destino`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`cedula`),
  ADD KEY `empleados_tipos_fk` (`codigo_tipo_empleado`);

--
-- Indices de la tabla `medios_transportes`
--
ALTER TABLE `medios_transportes`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `pasajes`
--
ALTER TABLE `pasajes`
  ADD PRIMARY KEY (`tiquete`),
  ADD KEY `pasajes_funcionarios_fk` (`cedula_funcionario`),
  ADD KEY `pasajes_viajes_fk` (`identificacion_viaje`);

--
-- Indices de la tabla `rutas`
--
ALTER TABLE `rutas`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `rutas_origen_fk` (`identificacion_origen`),
  ADD KEY `rutas_destino_fk` (`identificacion_destino`),
  ADD KEY `rutas_catalogos_fk` (`identificacion_catalogos`);

--
-- Indices de la tabla `tipos_comunicacion`
--
ALTER TABLE `tipos_comunicacion`
  ADD PRIMARY KEY (`identificacion`);

--
-- Indices de la tabla `tipos_empleados`
--
ALTER TABLE `tipos_empleados`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `transportes`
--
ALTER TABLE `transportes`
  ADD PRIMARY KEY (`placa`),
  ADD KEY `transportes_medtosT_fk` (`codigo_medios_transporte`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `cedula_funcionario` (`cedula_funcionario`,`email`);

--
-- Indices de la tabla `viajes`
--
ALTER TABLE `viajes`
  ADD PRIMARY KEY (`identificacion`),
  ADD KEY `viajes_asignaciones_fk` (`identificacion_asignaciones`),
  ADD KEY `viajes_rutas_fk` (`codigo_ruta`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignaciones`
--
ALTER TABLE `asignaciones`
  ADD CONSTRAINT `asignaciones_empleado_fk` FOREIGN KEY (`cedula_empleado`) REFERENCES `empleados` (`cedula`) ON DELETE CASCADE,
  ADD CONSTRAINT `asignaciones_transportes_fk` FOREIGN KEY (`placa_transporte`) REFERENCES `transportes` (`placa`) ON DELETE CASCADE;

--
-- Filtros para la tabla `comunicaciones`
--
ALTER TABLE `comunicaciones`
  ADD CONSTRAINT `comunicaciones_empleados_fk` FOREIGN KEY (`cedula_empleado`) REFERENCES `empleados` (`cedula`) ON DELETE CASCADE,
  ADD CONSTRAINT `comunicaciones_tipo_fk` FOREIGN KEY (`id_tipo`) REFERENCES `tipos_comunicacion` (`identificacion`) ON DELETE CASCADE;

--
-- Filtros para la tabla `detalles_ruta`
--
ALTER TABLE `detalles_ruta`
  ADD CONSTRAINT `destallesR_rutas_fk` FOREIGN KEY (`identificacion_ruta`) REFERENCES `rutas` (`codigo`) ON DELETE CASCADE,
  ADD CONSTRAINT `detallesR_destinos_fk` FOREIGN KEY (`codigo_destino`) REFERENCES `destinos` (`identificacion`) ON DELETE CASCADE;

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_tipos_fk` FOREIGN KEY (`codigo_tipo_empleado`) REFERENCES `tipos_empleados` (`codigo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `pasajes`
--
ALTER TABLE `pasajes`
  ADD CONSTRAINT `pasajes_funcionarios_fk` FOREIGN KEY (`cedula_funcionario`) REFERENCES `empleados` (`cedula`) ON DELETE CASCADE,
  ADD CONSTRAINT `pasajes_viajes_fk` FOREIGN KEY (`identificacion_viaje`) REFERENCES `viajes` (`identificacion`) ON DELETE CASCADE;

--
-- Filtros para la tabla `rutas`
--
ALTER TABLE `rutas`
  ADD CONSTRAINT `rutas_catalogos_fk` FOREIGN KEY (`identificacion_catalogos`) REFERENCES `catalogos_viajes` (`identificacion`) ON DELETE CASCADE,
  ADD CONSTRAINT `rutas_destino_fk` FOREIGN KEY (`identificacion_destino`) REFERENCES `destinos` (`identificacion`) ON DELETE CASCADE,
  ADD CONSTRAINT `rutas_origen_fk` FOREIGN KEY (`identificacion_origen`) REFERENCES `destinos` (`identificacion`) ON DELETE CASCADE;

--
-- Filtros para la tabla `transportes`
--
ALTER TABLE `transportes`
  ADD CONSTRAINT `transportes_medtosT_fk` FOREIGN KEY (`codigo_medios_transporte`) REFERENCES `medios_transportes` (`codigo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_empleados_fk` FOREIGN KEY (`cedula_funcionario`) REFERENCES `empleados` (`cedula`) ON DELETE CASCADE;

--
-- Filtros para la tabla `viajes`
--
ALTER TABLE `viajes`
  ADD CONSTRAINT `viajes_asignaciones_fk` FOREIGN KEY (`identificacion_asignaciones`) REFERENCES `asignaciones` (`identificacion`) ON DELETE CASCADE,
  ADD CONSTRAINT `viajes_rutas_fk` FOREIGN KEY (`codigo_ruta`) REFERENCES `rutas` (`codigo`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
