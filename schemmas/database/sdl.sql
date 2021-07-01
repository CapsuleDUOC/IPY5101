-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sdl
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sdl
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sdl` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `sdl` ;

-- -----------------------------------------------------
-- Table `sdl`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`cliente` ;

CREATE TABLE IF NOT EXISTS `sdl`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rut` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `mail` VARCHAR(45) NULL DEFAULT NULL,
  `telefono` VARCHAR(45) NULL DEFAULT NULL,
  `direccion` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`comuna`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`comuna` ;

CREATE TABLE IF NOT EXISTS `sdl`.`comuna` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`venta` ;

CREATE TABLE IF NOT EXISTS `sdl`.`venta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_dte` INT NULL DEFAULT NULL,
  `folio` INT NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `tipo_pago` VARCHAR(45) NULL DEFAULT NULL,
  `tipo_despacho` VARCHAR(45) NULL DEFAULT NULL,
  `monto_bruto` INT NULL DEFAULT NULL,
  `monto_iva` INT NULL DEFAULT NULL,
  `monto_total` INT NULL DEFAULT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`, `cliente_id`),
  INDEX `fk_venta_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_venta_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `sdl`.`cliente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`despacho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`despacho` ;

CREATE TABLE IF NOT EXISTS `sdl`.`despacho` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `venta_id` INT NOT NULL,
  `direccion` VARCHAR(45) NULL DEFAULT NULL,
  `cliente_id` INT NOT NULL,
  `comuna_id` INT NOT NULL,
  PRIMARY KEY (`id`, `venta_id`, `cliente_id`, `comuna_id`),
  INDEX `fk_despacho_venta1_idx` (`venta_id` ASC) VISIBLE,
  INDEX `fk_despacho_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  INDEX `fk_despacho_comuna1_idx` (`comuna_id` ASC) VISIBLE,
  CONSTRAINT `fk_despacho_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `sdl`.`cliente` (`id`),
  CONSTRAINT `fk_despacho_comuna1`
    FOREIGN KEY (`comuna_id`)
    REFERENCES `sdl`.`comuna` (`id`),
  CONSTRAINT `fk_despacho_venta1`
    FOREIGN KEY (`venta_id`)
    REFERENCES `sdl`.`venta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`libro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`libro` ;

CREATE TABLE IF NOT EXISTS `sdl`.`libro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `isdbn` VARCHAR(20) NULL DEFAULT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `autor` VARCHAR(45) NULL DEFAULT NULL,
  `editorial` VARCHAR(45) NULL DEFAULT NULL,
  `precio` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`reserva` ;

CREATE TABLE IF NOT EXISTS `sdl`.`reserva` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `venta_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  `estado` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `venta_id`, `cliente_id`),
  INDEX `fk_reserva_venta1_idx` (`venta_id` ASC) VISIBLE,
  INDEX `fk_reserva_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_reserva_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `sdl`.`cliente` (`id`),
  CONSTRAINT `fk_reserva_venta1`
    FOREIGN KEY (`venta_id`)
    REFERENCES `sdl`.`venta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`sucursal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`sucursal` ;

CREATE TABLE IF NOT EXISTS `sdl`.`sucursal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `encargado` VARCHAR(50) NULL DEFAULT NULL,
  `comuna_id` INT NOT NULL,
  PRIMARY KEY (`id`, `comuna_id`),
  INDEX `fk_sucursal_comuna1_idx` (`comuna_id` ASC) VISIBLE,
  CONSTRAINT `fk_sucursal_comuna1`
    FOREIGN KEY (`comuna_id`)
    REFERENCES `sdl`.`comuna` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`sucursal_libro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`sucursal_libro` ;

CREATE TABLE IF NOT EXISTS `sdl`.`sucursal_libro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sucursal_id` INT NOT NULL,
  `libro_id` INT NOT NULL,
  `stock` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sucursal_has_libro_libro1_idx` (`libro_id` ASC) VISIBLE,
  INDEX `fk_sucursal_has_libro_sucursal1_idx` (`sucursal_id` ASC) VISIBLE,
  CONSTRAINT `fk_sucursal_has_libro_libro1`
    FOREIGN KEY (`libro_id`)
    REFERENCES `sdl`.`libro` (`id`),
  CONSTRAINT `fk_sucursal_has_libro_sucursal1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `sdl`.`sucursal` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`vendedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`vendedor` ;

CREATE TABLE IF NOT EXISTS `sdl`.`vendedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `mail` VARCHAR(45) NULL DEFAULT NULL,
  `sucursal_id` INT NOT NULL,
  PRIMARY KEY (`id`, `sucursal_id`),
  INDEX `fk_vendedor_sucursal1_idx` (`sucursal_id` ASC) VISIBLE,
  CONSTRAINT `fk_vendedor_sucursal1`
    FOREIGN KEY (`sucursal_id`)
    REFERENCES `sdl`.`sucursal` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`venta_bitacora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`venta_bitacora` ;

CREATE TABLE IF NOT EXISTS `sdl`.`venta_bitacora` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `registro_instante` DATETIME NULL DEFAULT NULL,
  `nota` VARCHAR(250) NULL DEFAULT NULL,
  `venta_id` INT NOT NULL,
  PRIMARY KEY (`id`, `venta_id`),
  INDEX `fk_venta_bitacora_venta1_idx` (`venta_id` ASC) VISIBLE,
  CONSTRAINT `fk_venta_bitacora_venta1`
    FOREIGN KEY (`venta_id`)
    REFERENCES `sdl`.`venta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sdl`.`venta_libro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sdl`.`venta_libro` ;

CREATE TABLE IF NOT EXISTS `sdl`.`venta_libro` (
  `libro_id` INT NOT NULL AUTO_INCREMENT,
  `venta_id` INT NOT NULL,
  PRIMARY KEY (`libro_id`, `venta_id`),
  INDEX `fk_libro_has_venta_venta1_idx` (`venta_id` ASC) VISIBLE,
  INDEX `fk_libro_has_venta_libro1_idx` (`libro_id` ASC) VISIBLE,
  CONSTRAINT `fk_libro_has_venta_libro1`
    FOREIGN KEY (`libro_id`)
    REFERENCES `sdl`.`libro` (`id`),
  CONSTRAINT `fk_libro_has_venta_venta1`
    FOREIGN KEY (`venta_id`)
    REFERENCES `sdl`.`venta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
