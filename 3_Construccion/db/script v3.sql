SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `astecadb` DEFAULT CHARACTER SET utf8 ;
USE `astecadb` ;

-- -----------------------------------------------------
-- Table `astecadb`.`paises`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`paises` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`paises` (
  `ID_PAIS` INT(11) NOT NULL ,
  `NOMBRE_PAIS` VARCHAR(50) NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_PAIS`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`estados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`estados` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`estados` (
  `ID_ESTADO` INT(11) NOT NULL ,
  `NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  `CLAVE` VARCHAR(15) NULL DEFAULT NULL ,
  `ID_PAIS` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ESTADO`) ,
  INDEX `ID_PAIS` (`ID_PAIS` ASC) ,
  CONSTRAINT `FK_EDO_PAIS`
    FOREIGN KEY (`ID_PAIS` )
    REFERENCES `astecadb`.`paises` (`ID_PAIS` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`municipios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`municipios` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`municipios` (
  `ID_MUNICIPIO` INT(11) NOT NULL ,
  `ID_ESTADO` INT(11) NOT NULL ,
  `NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  `CLAVE` VARCHAR(15) NULL DEFAULT NULL ,
  `ID_UNICO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_MUNICIPIO`, `ID_ESTADO`) ,
  INDEX `ID_ESTADO` (`ID_ESTADO` ASC) ,
  CONSTRAINT `FK_MPIO_EDO`
    FOREIGN KEY (`ID_ESTADO` )
    REFERENCES `astecadb`.`estados` (`ID_ESTADO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`asentamientos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`asentamientos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`asentamientos` (
  `ID_ASENTAMIENTO` INT(11) NOT NULL ,
  `ID_MUNICIPIO` INT(11) NOT NULL ,
  `ID_ESTADO` INT(11) NOT NULL ,
  `ID_CP` SMALLINT(6) NULL DEFAULT NULL ,
  `NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  `CLAVE` VARCHAR(15) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ASENTAMIENTO`, `ID_MUNICIPIO`, `ID_ESTADO`) ,
  INDEX `ID_MUNICIPIO` (`ID_MUNICIPIO` ASC, `ID_ESTADO` ASC) ,
  CONSTRAINT `FK_ASEN_MPIO`
    FOREIGN KEY (`ID_MUNICIPIO` , `ID_ESTADO` )
    REFERENCES `astecadb`.`municipios` (`ID_MUNICIPIO` , `ID_ESTADO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`domicilios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`domicilios` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`domicilios` (
  `ID_DOMICILIO` INT(11) NOT NULL ,
  `ID_ASENTAMIENTO` INT(11) NULL DEFAULT NULL ,
  `CALLE` VARCHAR(30) NULL DEFAULT NULL ,
  `NO_EXTERIOR` VARCHAR(10) NULL DEFAULT NULL ,
  `NO_INTERIOR` VARCHAR(10) NULL DEFAULT NULL ,
  `CP` SMALLINT(6) NULL DEFAULT NULL ,
  `TELEFONO` VARCHAR(20) NULL DEFAULT NULL ,
  `ID_MUNICIPIO` INT(11) NULL DEFAULT NULL ,
  `ID_ESTADO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_DOMICILIO`) ,
  INDEX `ID_ASENTAMIENTO` (`ID_ASENTAMIENTO` ASC, `ID_MUNICIPIO` ASC, `ID_ESTADO` ASC) ,
  CONSTRAINT `FK_DOM_ASE`
    FOREIGN KEY (`ID_ASENTAMIENTO` , `ID_MUNICIPIO` , `ID_ESTADO` )
    REFERENCES `astecadb`.`asentamientos` (`ID_ASENTAMIENTO` , `ID_MUNICIPIO` , `ID_ESTADO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`personas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`personas` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`personas` (
  `ID_PERSONA` INT(11) NOT NULL ,
  `FECHA_NAC` DATE NULL DEFAULT NULL ,
  `LUGAR_NAC` VARCHAR(50) NULL DEFAULT NULL ,
  `CURP` VARCHAR(20) NULL DEFAULT NULL ,
  `RFC` VARCHAR(20) NULL DEFAULT NULL ,
  `IFE` VARCHAR(20) NULL DEFAULT NULL ,
  `PASAPORTE` VARCHAR(30) NULL DEFAULT NULL ,
  `EMAIL` VARCHAR(50) NULL DEFAULT NULL ,
  `NOMBRE` VARCHAR(30) NULL DEFAULT NULL ,
  `APELLIDO_P` VARCHAR(30) NULL DEFAULT NULL ,
  `APELLIDO_M` VARCHAR(30) NULL DEFAULT NULL ,
  `USUARIO` VARCHAR(30) NULL DEFAULT NULL ,
  `PASSWRD` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_PERSONA`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`administrativos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`administrativos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`administrativos` (
  `ID_ADMIN` INT(11) NOT NULL ,
  `NO_EMPLEADO` VARCHAR(20) NULL DEFAULT NULL ,
  `ID_PERSONA` INT(11) NULL DEFAULT NULL ,
  `ID_DOMICILIO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ADMIN`) ,
  INDEX `ID_PERSONA` (`ID_PERSONA` ASC) ,
  INDEX `ID_DOMICILIO` (`ID_DOMICILIO` ASC) ,
  CONSTRAINT `FK_ADMIN_DOM`
    FOREIGN KEY (`ID_DOMICILIO` )
    REFERENCES `astecadb`.`domicilios` (`ID_DOMICILIO` ),
  CONSTRAINT `FK_ADMIN_PER`
    FOREIGN KEY (`ID_PERSONA` )
    REFERENCES `astecadb`.`personas` (`ID_PERSONA` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`estatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`estatus` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`estatus` (
  `ID_ESTATUS` SMALLINT(6) NOT NULL ,
  `DESC_ESTATUS` VARCHAR(20) NULL DEFAULT NULL ,
  `OBS_ESTATUS` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ESTATUS`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`familia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`familia` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`familia` (
  `ID_FAM` INT(11) NOT NULL ,
  `CONYUGUE_NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `CONYUGUE_FECHA_NAC` DATE NULL DEFAULT NULL ,
  `HIJO_1_NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `HIJO_1_FECHA_NAC` DATE NULL DEFAULT NULL ,
  `HIJO_2_NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `HIJO_2_FECHA_NAC` DATE NULL DEFAULT NULL ,
  `PADRE_NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `PADRE_FECHA_NAC` DATE NULL DEFAULT NULL ,
  `MADRE_NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `MADRE_FECHA_NAC` DATE NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_FAM`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`alumnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`alumnos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`alumnos` (
  `ID_ALUMNO` INT(11) NOT NULL ,
  `MATRICULA` VARCHAR(50) NULL DEFAULT NULL ,
  `ID_ESTATUS` SMALLINT(6) NULL DEFAULT NULL ,
  `ID_PERSONA` INT(11) NULL DEFAULT NULL ,
  `ID_DOMICILIO` INT(11) NULL DEFAULT NULL ,
  `ID_FAMILIA` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ALUMNO`) ,
  UNIQUE INDEX `UQ_ALUM_MAT` (`MATRICULA` ASC) ,
  INDEX `ID_ESTATUS` (`ID_ESTATUS` ASC) ,
  INDEX `ID_PERSONA` (`ID_PERSONA` ASC) ,
  INDEX `ID_DOMICILIO` (`ID_DOMICILIO` ASC) ,
  INDEX `ID_FAMILIA` (`ID_FAMILIA` ASC) ,
  CONSTRAINT `FK_ALUM_DOM`
    FOREIGN KEY (`ID_DOMICILIO` )
    REFERENCES `astecadb`.`domicilios` (`ID_DOMICILIO` ),
  CONSTRAINT `FK_ALUM_DT`
    FOREIGN KEY (`ID_PERSONA` )
    REFERENCES `astecadb`.`personas` (`ID_PERSONA` ),
  CONSTRAINT `FK_ALUM_EST`
    FOREIGN KEY (`ID_ESTATUS` )
    REFERENCES `astecadb`.`estatus` (`ID_ESTATUS` ),
  CONSTRAINT `FK_ALU_FAM`
    FOREIGN KEY (`ID_FAMILIA` )
    REFERENCES `astecadb`.`familia` (`ID_FAM` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`cursos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`cursos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`cursos` (
  `ID_CURSO` INT(11) NOT NULL ,
  `ID_AREA` INT(11) NULL DEFAULT NULL ,
  `ID_PROGR_ESTUDIOS` INT(11) NULL DEFAULT NULL ,
  `FECHA_INI` DATE NULL DEFAULT NULL ,
  `FECHA_FIN` DATE NULL DEFAULT NULL ,
  `ID_SEDE` INT(11) NULL DEFAULT NULL ,
  `HORA_INI` VARCHAR(5) NULL DEFAULT NULL ,
  `HORA_FIN` VARCHAR(5) NULL DEFAULT NULL ,
  `GRUPO` INT(11) NULL DEFAULT NULL ,
  `REFERENCIA` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_CURSO`) ,
  UNIQUE INDEX `UQ_CURSOS_GRUPO` (`GRUPO` ASC) ,
  UNIQUE INDEX `UQ_CURSOS_REFERENCIA` (`REFERENCIA` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`alumnos_cursos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`alumnos_cursos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`alumnos_cursos` (
  `ID_ALUMNO_CURSO` INT(11) NOT NULL ,
  `ID_ALUMNO` INT(11) NULL DEFAULT NULL ,
  `ID_CURSO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ALUMNO_CURSO`) ,
  INDEX `ID_CURSO` (`ID_CURSO` ASC) ,
  INDEX `ID_ALUMNO` (`ID_ALUMNO` ASC) ,
  CONSTRAINT `FK_AC_ALUMN`
    FOREIGN KEY (`ID_ALUMNO` )
    REFERENCES `astecadb`.`alumnos` (`ID_ALUMNO` ),
  CONSTRAINT `FK_AC_CURSO`
    FOREIGN KEY (`ID_CURSO` )
    REFERENCES `astecadb`.`cursos` (`ID_CURSO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`asistencia_curso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`asistencia_curso` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`asistencia_curso` (
  `ID_ASISTENCIA_CURSO` BIGINT(20) NOT NULL ,
  `ID_ALUMNO` INT(11) NULL DEFAULT NULL ,
  `ID_CURSO` INT(11) NULL DEFAULT NULL ,
  `FECHA` DATE NULL DEFAULT NULL ,
  `PRESENTE` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ASISTENCIA_CURSO`) ,
  INDEX `ID_CURSO` (`ID_CURSO` ASC) ,
  INDEX `ID_ALUMNO` (`ID_ALUMNO` ASC) ,
  CONSTRAINT `FK_ASISC_ALUMN`
    FOREIGN KEY (`ID_ALUMNO` )
    REFERENCES `astecadb`.`alumnos` (`ID_ALUMNO` ),
  CONSTRAINT `FK_ASISC_CURSO`
    FOREIGN KEY (`ID_CURSO` )
    REFERENCES `astecadb`.`cursos` (`ID_CURSO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`tipos_cat_gral`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`tipos_cat_gral` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`tipos_cat_gral` (
  `ID_CAT_GRAL` SMALLINT(6) NOT NULL ,
  `TIPO` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_CAT_GRAL`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`cat_gral`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`cat_gral` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`cat_gral` (
  `ID_CAT_GRAL` INT(11) NOT NULL ,
  `ID_TIPO_CAT_GRAL` SMALLINT(6) NULL DEFAULT NULL ,
  `CVE_REGISTRO` VARCHAR(30) NULL DEFAULT NULL ,
  `DSC` VARCHAR(50) NULL DEFAULT NULL ,
  `ESTATUS` VARCHAR(20) NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_CAT_GRAL`) ,
  INDEX `ID_TIPO_CAT_GRAL` (`ID_TIPO_CAT_GRAL` ASC) ,
  CONSTRAINT `FK_CAT_GRAL_TIP`
    FOREIGN KEY (`ID_TIPO_CAT_GRAL` )
    REFERENCES `astecadb`.`tipos_cat_gral` (`ID_CAT_GRAL` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`aulas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`aulas` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`aulas` (
  `ID_AULA` INT(11) NOT NULL ,
  `ID_SEDE` INT(11) NULL DEFAULT NULL ,
  `CLAVE` VARCHAR(30) NULL DEFAULT NULL ,
  `DSC` VARCHAR(30) NULL DEFAULT NULL ,
  `CAPACIDAD` INT(11) NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_AULA`) ,
  INDEX `ID_SEDE` (`ID_SEDE` ASC) ,
  CONSTRAINT `FK_AU_SED`
    FOREIGN KEY (`ID_SEDE` )
    REFERENCES `astecadb`.`cat_gral` (`ID_CAT_GRAL` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`programa_estudios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`programa_estudios` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`programa_estudios` (
  `ID_PROG_ESTUDIO` INT(11) NOT NULL ,
  `CLAVE` VARCHAR(30) NULL DEFAULT NULL ,
  `DSC` VARCHAR(50) NULL DEFAULT NULL ,
  `ID_TIPO` INT(11) NULL DEFAULT NULL ,
  `NO_AUT` VARCHAR(20) NULL DEFAULT NULL ,
  `FECHA_AUT` DATE NULL DEFAULT NULL ,
  `HORAS_PRACTICA` INT(11) NULL DEFAULT NULL ,
  `HORAS_TEORIA` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_PROG_ESTUDIO`) ,
  INDEX `ID_TIPO` (`ID_TIPO` ASC) ,
  CONSTRAINT `FK_PRGR_EST_CAT_GRAL`
    FOREIGN KEY (`ID_TIPO` )
    REFERENCES `astecadb`.`cat_gral` (`ID_CAT_GRAL` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`docs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`docs` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`docs` (
  `ID_DOC` INT(11) NOT NULL ,
  `NOMBRE` VARCHAR(30) NULL DEFAULT NULL ,
  `RUTA` VARCHAR(100) NULL DEFAULT NULL ,
  `ID_ESTATUS` SMALLINT(6) NULL DEFAULT NULL ,
  `ID_ALUMNO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_DOC`) ,
  INDEX `ID_ESTATUS` (`ID_ESTATUS` ASC) ,
  CONSTRAINT `FK_DOCSALU_EST`
    FOREIGN KEY (`ID_ESTATUS` )
    REFERENCES `astecadb`.`estatus` (`ID_ESTATUS` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`autorizaciones_progr_est`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`autorizaciones_progr_est` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`autorizaciones_progr_est` (
  `ID_AUTORIZACION` INT(11) NOT NULL ,
  `ID_PROG_EST` INT(11) NULL DEFAULT NULL ,
  `ID_DOC` INT(11) NULL DEFAULT NULL ,
  `FECHA_UPLOAD` DATE NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_AUTORIZACION`) ,
  INDEX `ID_DOC` (`ID_DOC` ASC) ,
  INDEX `ID_PROG_EST` (`ID_PROG_EST` ASC) ,
  CONSTRAINT `FK_AUT_PRGEST_PROGR_EST`
    FOREIGN KEY (`ID_PROG_EST` )
    REFERENCES `astecadb`.`programa_estudios` (`ID_PROG_ESTUDIO` ),
  CONSTRAINT `FK_AUT_PROGR_EST_DOCS`
    FOREIGN KEY (`ID_DOC` )
    REFERENCES `astecadb`.`docs` (`ID_DOC` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`bitacora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`bitacora` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`bitacora` (
  `ID_BITACORA` BIGINT(20) NOT NULL ,
  `FECHA` DATETIME NULL DEFAULT NULL ,
  `IP` VARCHAR(15) NULL DEFAULT NULL ,
  `ID_USR` INT(11) NULL DEFAULT NULL ,
  `MENSAJE` VARCHAR(50) NULL DEFAULT NULL ,
  `ACCION` VARCHAR(30) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_BITACORA`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`tipos_instructores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`tipos_instructores` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`tipos_instructores` (
  `ID_TIPO_INSTRUCTOR` SMALLINT(6) NOT NULL ,
  `NOMBRE` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_TIPO_INSTRUCTOR`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`instructores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`instructores` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`instructores` (
  `ID_INSTRUCTOR` INT(11) NOT NULL ,
  `NO_EMPLEADO` VARCHAR(20) NULL DEFAULT NULL ,
  `ID_TIPO_INSTRUCTOR` SMALLINT(6) NULL DEFAULT NULL ,
  `ID_PERSONA` INT(11) NULL DEFAULT NULL ,
  `ID_DOMICILIO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_INSTRUCTOR`) ,
  INDEX `ID_TIPO_INSTRUCTOR` (`ID_TIPO_INSTRUCTOR` ASC) ,
  INDEX `ID_DOMICILIO` (`ID_DOMICILIO` ASC) ,
  INDEX `ID_PERSONA` (`ID_PERSONA` ASC) ,
  CONSTRAINT `FK_INSTR_DOM`
    FOREIGN KEY (`ID_DOMICILIO` )
    REFERENCES `astecadb`.`domicilios` (`ID_DOMICILIO` ),
  CONSTRAINT `FK_INSTR_PER`
    FOREIGN KEY (`ID_PERSONA` )
    REFERENCES `astecadb`.`personas` (`ID_PERSONA` ),
  CONSTRAINT `FK_INSTR_T_INSTR`
    FOREIGN KEY (`ID_TIPO_INSTRUCTOR` )
    REFERENCES `astecadb`.`tipos_instructores` (`ID_TIPO_INSTRUCTOR` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`materias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`materias` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`materias` (
  `ID_MATERIA` INT(11) NOT NULL ,
  `NOMBRE` VARCHAR(30) NULL DEFAULT NULL ,
  `ID_AULA` INT(11) NULL DEFAULT NULL ,
  `ID_INSTRUCTOR` INT(11) NULL DEFAULT NULL ,
  `FH_INICIAL` DATETIME NULL DEFAULT NULL ,
  `FH_FINAL` DATETIME NULL DEFAULT NULL ,
  `ID_TIPO_MATERIA` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_MATERIA`) ,
  INDEX `ID_AULA` (`ID_AULA` ASC) ,
  INDEX `ID_INSTRUCTOR` (`ID_INSTRUCTOR` ASC) ,
  INDEX `ID_TIPO_MATERIA` (`ID_TIPO_MATERIA` ASC) ,
  CONSTRAINT `FK_MATS_AULA`
    FOREIGN KEY (`ID_AULA` )
    REFERENCES `astecadb`.`aulas` (`ID_AULA` ),
  CONSTRAINT `FK_MAT_INSTR`
    FOREIGN KEY (`ID_INSTRUCTOR` )
    REFERENCES `astecadb`.`instructores` (`ID_INSTRUCTOR` ),
  CONSTRAINT `FK_MAT_TIPO_MAT`
    FOREIGN KEY (`ID_TIPO_MATERIA` )
    REFERENCES `astecadb`.`cat_gral` (`ID_CAT_GRAL` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`calif_cursos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`calif_cursos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`calif_cursos` (
  `ID_CALIF_CURSO` BIGINT(20) NOT NULL ,
  `ID_ALUMNO` INT(11) NULL DEFAULT NULL ,
  `ID_CURSO` INT(11) NULL DEFAULT NULL ,
  `ID_MATERIA` INT(11) NULL DEFAULT NULL ,
  `CALIFICACION` DECIMAL(4,2) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_CALIF_CURSO`) ,
  INDEX `ID_MATERIA` (`ID_MATERIA` ASC) ,
  INDEX `ID_ALUMNO` (`ID_ALUMNO` ASC) ,
  INDEX `ID_CURSO` (`ID_CURSO` ASC) ,
  CONSTRAINT `FK_CALIFC_ALUMN`
    FOREIGN KEY (`ID_ALUMNO` )
    REFERENCES `astecadb`.`alumnos` (`ID_ALUMNO` ),
  CONSTRAINT `FK_CALIFC_CURSO`
    FOREIGN KEY (`ID_CURSO` )
    REFERENCES `astecadb`.`cursos` (`ID_CURSO` ),
  CONSTRAINT `FK_CALIFC_MAT`
    FOREIGN KEY (`ID_MATERIA` )
    REFERENCES `astecadb`.`materias` (`ID_MATERIA` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`tipos_licencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`tipos_licencia` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`tipos_licencia` (
  `ID_TIPO_LIC` SMALLINT(6) NOT NULL ,
  `DESC_TIPO_LIC` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_TIPO_LIC`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`capacidades`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`capacidades` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`capacidades` (
  `ID_CAPACIDAD` INT(11) NOT NULL ,
  `ID_TIPO_LICENCIA` SMALLINT(6) NULL DEFAULT NULL ,
  `TIPO_CAPACIDAD` VARCHAR(50) NULL DEFAULT NULL ,
  `AUTORIZADA` VARCHAR(50) NULL DEFAULT NULL ,
  `HORAS` INT(11) NULL DEFAULT NULL ,
  `FECHA_INIC` DATE NULL DEFAULT NULL ,
  `FECHA_FIN` DATE NULL DEFAULT NULL ,
  `ACREDITADA` VARCHAR(15) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_CAPACIDAD`) ,
  INDEX `ID_TIPO_LICENCIA` (`ID_TIPO_LICENCIA` ASC) ,
  CONSTRAINT `FK_CAP_TL`
    FOREIGN KEY (`ID_TIPO_LICENCIA` )
    REFERENCES `astecadb`.`tipos_licencia` (`ID_TIPO_LIC` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`capacidades_alumnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`capacidades_alumnos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`capacidades_alumnos` (
  `ID_CAP_ALU` INT(11) NOT NULL ,
  `ID_CAPACIDADES` INT(11) NULL DEFAULT NULL ,
  `ID_ALUMNO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_CAP_ALU`) ,
  INDEX `ID_ALUMNO` (`ID_ALUMNO` ASC) ,
  INDEX `ID_CAPACIDADES` (`ID_CAPACIDADES` ASC) ,
  CONSTRAINT `FK_CAPALU_ALU`
    FOREIGN KEY (`ID_ALUMNO` )
    REFERENCES `astecadb`.`alumnos` (`ID_ALUMNO` ),
  CONSTRAINT `FK_CAPALU_CAP`
    FOREIGN KEY (`ID_CAPACIDADES` )
    REFERENCES `astecadb`.`capacidades` (`ID_CAPACIDAD` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`certificados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`certificados` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`certificados` (
  `NO_CERTIFICADO` VARCHAR(50) NULL DEFAULT NULL ,
  `ID_ESTATUS` SMALLINT(6) NULL DEFAULT NULL ,
  `FECHA_EMISION` DATE NULL DEFAULT NULL ,
  `FECHA_CANCELACION` DATE NULL DEFAULT NULL ,
  `MOTIVO_CANCELACION` VARCHAR(150) NULL DEFAULT NULL ,
  `ID_ALUMNO` INT(11) NULL DEFAULT NULL ,
  `ID_CERT` INT(11) NOT NULL ,
  PRIMARY KEY (`ID_CERT`) ,
  INDEX `ID_ESTATUS` (`ID_ESTATUS` ASC) ,
  INDEX `ID_ALUMNO` (`ID_ALUMNO` ASC) ,
  CONSTRAINT `FK_CERT_ALUMN`
    FOREIGN KEY (`ID_ALUMNO` )
    REFERENCES `astecadb`.`alumnos` (`ID_ALUMNO` ),
  CONSTRAINT `FK_CERT_ESTATUS`
    FOREIGN KEY (`ID_ESTATUS` )
    REFERENCES `astecadb`.`estatus` (`ID_ESTATUS` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`tipos_clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`tipos_clientes` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`tipos_clientes` (
  `ID_TIPO_CLIENTE` SMALLINT(6) NOT NULL ,
  `NOMBRE` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_TIPO_CLIENTE`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`clientes` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`clientes` (
  `ID_CLIENTE` INT(11) NOT NULL AUTO_INCREMENT ,
  `CLAVE` VARCHAR(20) NULL DEFAULT NULL ,
  `NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `RESPONSABLE` VARCHAR(50) NULL DEFAULT NULL ,
  `TELEFONO` VARCHAR(30) NULL DEFAULT NULL ,
  `EMAIL` VARCHAR(30) NULL DEFAULT NULL ,
  `ID_TIPO_CLIENTE` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_CLIENTE`) ,
  INDEX `ID_TIPO_CLIENTE` (`ID_TIPO_CLIENTE` ASC) ,
  CONSTRAINT `FK_CTE_TP_CTE`
    FOREIGN KEY (`ID_TIPO_CLIENTE` )
    REFERENCES `astecadb`.`tipos_clientes` (`ID_TIPO_CLIENTE` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`cursos_materias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`cursos_materias` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`cursos_materias` (
  `ID_CURSO_MATERIA` INT(11) NOT NULL ,
  `ID_MATERIA` INT(11) NULL DEFAULT NULL ,
  `ID_CURSO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_CURSO_MATERIA`) ,
  INDEX `ID_CURSO` (`ID_CURSO` ASC) ,
  INDEX `ID_MATERIA` (`ID_MATERIA` ASC) ,
  CONSTRAINT `FK_CM_CURSO`
    FOREIGN KEY (`ID_CURSO` )
    REFERENCES `astecadb`.`cursos` (`ID_CURSO` ),
  CONSTRAINT `FK_CM_MAT`
    FOREIGN KEY (`ID_MATERIA` )
    REFERENCES `astecadb`.`materias` (`ID_MATERIA` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`docs_alumnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`docs_alumnos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`docs_alumnos` (
  `ID_DOC_ALUMNO` INT(11) NOT NULL ,
  `ID_ALUMNO` INT(11) NULL DEFAULT NULL ,
  `FECHA_UPLOAD` DATE NULL DEFAULT NULL ,
  `ID_DOC` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_DOC_ALUMNO`) ,
  INDEX `ID_ALUMNO` (`ID_ALUMNO` ASC) ,
  INDEX `ID_DOC` (`ID_DOC` ASC) ,
  CONSTRAINT `FK_DOCSALU_DOCS`
    FOREIGN KEY (`ID_DOC` )
    REFERENCES `astecadb`.`docs` (`ID_DOC` ),
  CONSTRAINT `FK_DOCSAL_ALUMN`
    FOREIGN KEY (`ID_ALUMNO` )
    REFERENCES `astecadb`.`alumnos` (`ID_ALUMNO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`docs_necesarios_alumnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`docs_necesarios_alumnos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`docs_necesarios_alumnos` (
  `ID_DOC_NEC` SMALLINT(6) NOT NULL ,
  `NOMBRE` VARCHAR(30) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_DOC_NEC`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`equipos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`equipos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`equipos` (
  `ID_EQUIPO` INT(11) NOT NULL ,
  `CLAVE` VARCHAR(20) NULL DEFAULT NULL ,
  `DSC` VARCHAR(50) NULL DEFAULT NULL ,
  `TIPO_EQUIPO` INT(11) NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_EQUIPO`) ,
  INDEX `TIPO_EQUIPO` (`TIPO_EQUIPO` ASC) ,
  CONSTRAINT `FK_EQ_CAT_GRAL`
    FOREIGN KEY (`TIPO_EQUIPO` )
    REFERENCES `astecadb`.`cat_gral` (`ID_CAT_GRAL` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`tipos_estudios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`tipos_estudios` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`tipos_estudios` (
  `ID_TIPO_ESTUDIOS` SMALLINT(6) NOT NULL ,
  `NOMBRE` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_TIPO_ESTUDIOS`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`estudios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`estudios` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`estudios` (
  `ID_ESTUDIOS` INT(11) NOT NULL ,
  `INSTITUCION` VARCHAR(50) NULL DEFAULT NULL ,
  `FECHA_INI` SMALLINT(6) NULL DEFAULT NULL ,
  `FECHA_FIN` SMALLINT(6) NULL DEFAULT NULL ,
  `CERT` VARCHAR(50) NULL DEFAULT NULL ,
  `ID_TIPO_ESTUDIO` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ESTUDIOS`) ,
  INDEX `ID_TIPO_ESTUDIO` (`ID_TIPO_ESTUDIO` ASC) ,
  CONSTRAINT `FK_EST_TE`
    FOREIGN KEY (`ID_TIPO_ESTUDIO` )
    REFERENCES `astecadb`.`tipos_estudios` (`ID_TIPO_ESTUDIOS` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`estudios_alumno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`estudios_alumno` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`estudios_alumno` (
  `ID_EST_ALU` INT(11) NOT NULL ,
  `ID_ESTUDIO` INT(11) NULL DEFAULT NULL ,
  `ID_ALUMNO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_EST_ALU`) ,
  INDEX `ID_ALUMNO` (`ID_ALUMNO` ASC) ,
  INDEX `ID_ESTUDIO` (`ID_ESTUDIO` ASC) ,
  CONSTRAINT `FK_ESTALU_ALU`
    FOREIGN KEY (`ID_ALUMNO` )
    REFERENCES `astecadb`.`alumnos` (`ID_ALUMNO` ),
  CONSTRAINT `FK_ESTALU_EST`
    FOREIGN KEY (`ID_ESTUDIO` )
    REFERENCES `astecadb`.`estudios` (`ID_ESTUDIOS` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`instituto_puestos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`instituto_puestos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`instituto_puestos` (
  `ID_PUESTO` SMALLINT(6) NOT NULL ,
  `NOMBRE` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_PUESTO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`institutos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`institutos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`institutos` (
  `ID_INSTITUTO` INT(11) NOT NULL ,
  `NOMBRE` VARCHAR(50) NULL DEFAULT NULL ,
  `RAZON_SOCIAL` VARCHAR(50) NULL DEFAULT NULL ,
  `RFC` VARCHAR(20) NULL DEFAULT NULL ,
  `ID_DOMICILIO` INT(11) NULL DEFAULT NULL ,
  `FAX` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_INSTITUTO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`instituto_contactos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`instituto_contactos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`instituto_contactos` (
  `ID_INST_CONT` INT(11) NOT NULL ,
  `ID_PERSONA` INT(11) NULL DEFAULT NULL ,
  `ID_PUESTO` SMALLINT(6) NULL DEFAULT NULL ,
  `ID_INSTITUTO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_INST_CONT`) ,
  INDEX `ID_INSTITUTO` (`ID_INSTITUTO` ASC) ,
  INDEX `ID_PUESTO` (`ID_PUESTO` ASC) ,
  INDEX `ID_PERSONA` (`ID_PERSONA` ASC) ,
  CONSTRAINT `FK_INSTCTS_INSTPTO`
    FOREIGN KEY (`ID_PUESTO` )
    REFERENCES `astecadb`.`instituto_puestos` (`ID_PUESTO` ),
  CONSTRAINT `FK_INSTCTS_PER`
    FOREIGN KEY (`ID_PERSONA` )
    REFERENCES `astecadb`.`personas` (`ID_PERSONA` ),
  CONSTRAINT `FK_INST_CTSINST`
    FOREIGN KEY (`ID_INSTITUTO` )
    REFERENCES `astecadb`.`institutos` (`ID_INSTITUTO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`modulos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`modulos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`modulos` (
  `ID_MODULO` INT(11) NOT NULL ,
  `NOMBRE` VARCHAR(25) NULL DEFAULT NULL ,
  `DSC` VARCHAR(50) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_MODULO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`notificaciones_estados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`notificaciones_estados` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`notificaciones_estados` (
  `ID_NOTIF_EDO` SMALLINT(6) NOT NULL ,
  `NOMBRE` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_NOTIF_EDO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`notificaciones_tipos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`notificaciones_tipos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`notificaciones_tipos` (
  `ID_NOTIF_TIPO` SMALLINT(6) NOT NULL ,
  `NOMBRE` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_NOTIF_TIPO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`notificaciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`notificaciones` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`notificaciones` (
  `ID_NOTIFICACION` BIGINT(20) NOT NULL ,
  `ID_ESTADO` SMALLINT(6) NULL DEFAULT NULL ,
  `ID_TIPO` SMALLINT(6) NULL DEFAULT NULL ,
  `FECHA_ALTA` DATETIME NULL DEFAULT NULL ,
  `FECHA_LEIDO` DATETIME NULL DEFAULT NULL ,
  `MENSAJE` VARCHAR(50) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_NOTIFICACION`) ,
  INDEX `ID_ESTADO` (`ID_ESTADO` ASC) ,
  INDEX `ID_TIPO` (`ID_TIPO` ASC) ,
  CONSTRAINT `FK_NOTIF_NOTIFEDO`
    FOREIGN KEY (`ID_ESTADO` )
    REFERENCES `astecadb`.`notificaciones_estados` (`ID_NOTIF_EDO` ),
  CONSTRAINT `FK_NOTIF_NOTIFTP`
    FOREIGN KEY (`ID_TIPO` )
    REFERENCES `astecadb`.`notificaciones_tipos` (`ID_NOTIF_TIPO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`permisos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`permisos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`permisos` (
  `ID_PERMISO` INT(11) NOT NULL ,
  `ALTA` SMALLINT(6) NULL DEFAULT NULL ,
  `CAMBIOS` SMALLINT(6) NULL DEFAULT NULL ,
  `BORRAR` SMALLINT(6) NULL DEFAULT NULL ,
  `CONSULTA` SMALLINT(6) NULL DEFAULT NULL ,
  `IMPRESION` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_PERMISO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`programa_est_materias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`programa_est_materias` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`programa_est_materias` (
  `ID_PROGR_EST_MAT` INT(11) NOT NULL ,
  `ID_PROGR_EST` INT(11) NULL DEFAULT NULL ,
  `ID_MATERIA` INT(11) NULL DEFAULT NULL ,
  `HORAS_TEORIA` INT(11) NULL DEFAULT NULL ,
  `HORAS_PRACTICA` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_PROGR_EST_MAT`) ,
  INDEX `ID_MATERIA` (`ID_MATERIA` ASC) ,
  INDEX `ID_PROGR_EST` (`ID_PROGR_EST` ASC) ,
  CONSTRAINT `FK_PRG_ESTMAT_EST`
    FOREIGN KEY (`ID_PROGR_EST` )
    REFERENCES `astecadb`.`programa_estudios` (`ID_PROG_ESTUDIO` ),
  CONSTRAINT `FK_PRG_ESTMAT_MAT`
    FOREIGN KEY (`ID_MATERIA` )
    REFERENCES `astecadb`.`materias` (`ID_MATERIA` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`referencias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`referencias` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`referencias` (
  `ID_REFERENCIA` INT(11) NOT NULL ,
  `ID_PERSONA` INT(11) NULL DEFAULT NULL ,
  `ID_DOMICILIO` INT(11) NULL DEFAULT NULL ,
  `ID_ALUMNO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_REFERENCIA`) ,
  INDEX `ID_ALUMNO` (`ID_ALUMNO` ASC) ,
  INDEX `ID_PERSONA` (`ID_PERSONA` ASC) ,
  INDEX `ID_DOMICILIO` (`ID_DOMICILIO` ASC) ,
  CONSTRAINT `FK_REF_ALU`
    FOREIGN KEY (`ID_ALUMNO` )
    REFERENCES `astecadb`.`alumnos` (`ID_ALUMNO` ),
  CONSTRAINT `FK_REF_DOM`
    FOREIGN KEY (`ID_DOMICILIO` )
    REFERENCES `astecadb`.`domicilios` (`ID_DOMICILIO` ),
  CONSTRAINT `FK_REF_PER`
    FOREIGN KEY (`ID_PERSONA` )
    REFERENCES `astecadb`.`personas` (`ID_PERSONA` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`roles` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`roles` (
  `ID_ROL` INT(11) NOT NULL ,
  `CLAVE` VARCHAR(15) NULL DEFAULT NULL ,
  `NOMBRE` VARCHAR(25) NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ROL`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`roles_modulos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`roles_modulos` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`roles_modulos` (
  `ID_ROL_MOD` INT(11) NOT NULL ,
  `ID_ROL` INT(11) NULL DEFAULT NULL ,
  `ID_MODULO` INT(11) NULL DEFAULT NULL ,
  `ID_PERMISOS` INT(11) NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ROL_MOD`) ,
  INDEX `ID_PERMISOS` (`ID_PERMISOS` ASC) ,
  CONSTRAINT `FK_ROLES_MODULOS_PERMISOS`
    FOREIGN KEY (`ID_PERMISOS` )
    REFERENCES `astecadb`.`permisos` (`ID_PERMISO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`roles_mod_usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`roles_mod_usuarios` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`roles_mod_usuarios` (
  `ID_ROL_MOD_USR` INT(11) NOT NULL ,
  `ID_ROL` INT(11) NULL DEFAULT NULL ,
  `PERIODO_INICIO` DATE NULL DEFAULT NULL ,
  `PERIODO_FIN` DATE NULL DEFAULT NULL ,
  `ACTIVO` SMALLINT(6) NULL DEFAULT NULL ,
  `ID_USUARIO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ROL_MOD_USR`) ,
  INDEX `ID_ROL` (`ID_ROL` ASC) ,
  INDEX `ID_USUARIO` (`ID_USUARIO` ASC) ,
  CONSTRAINT `FK_ROL_MD_USR_ROLES`
    FOREIGN KEY (`ID_ROL` )
    REFERENCES `astecadb`.`roles` (`ID_ROL` ),
  CONSTRAINT `FK_ROL_MOD_PER_USR`
    FOREIGN KEY (`ID_USUARIO` )
    REFERENCES `astecadb`.`personas` (`ID_PERSONA` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`roles_mod_permisos_usr`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`roles_mod_permisos_usr` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`roles_mod_permisos_usr` (
  `ID_ROL_MOD_PER_USR` INT(11) NOT NULL ,
  `ID_ROL_MOD_USR` INT(11) NULL DEFAULT NULL ,
  `ID_ROL_MOD` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ROL_MOD_PER_USR`) ,
  INDEX `ID_ROL_MOD_USR` (`ID_ROL_MOD_USR` ASC) ,
  INDEX `ID_ROL_MOD` (`ID_ROL_MOD` ASC) ,
  CONSTRAINT `FK_ROL_MOD_PERM_MOD`
    FOREIGN KEY (`ID_ROL_MOD` )
    REFERENCES `astecadb`.`roles_modulos` (`ID_ROL_MOD` ),
  CONSTRAINT `FK_ROL_MOD_PERM_USRS`
    FOREIGN KEY (`ID_ROL_MOD_USR` )
    REFERENCES `astecadb`.`roles_mod_usuarios` (`ID_ROL_MOD_USR` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `astecadb`.`roles_mod_permisos_sobre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `astecadb`.`roles_mod_permisos_sobre` ;

CREATE  TABLE IF NOT EXISTS `astecadb`.`roles_mod_permisos_sobre` (
  `ID_ROL_MOD_PERM_SOBRE` INT(11) NOT NULL ,
  `ID_PERMISOS` INT(11) NULL DEFAULT NULL ,
  `ID_ROL_MOD_PERM_USR` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ROL_MOD_PERM_SOBRE`) ,
  INDEX `ID_PERMISOS` (`ID_PERMISOS` ASC) ,
  INDEX `ID_ROL_MOD_PERM_USR` (`ID_ROL_MOD_PERM_USR` ASC) ,
  CONSTRAINT `FK_ROL_MOD_PERM_SOBR_USR`
    FOREIGN KEY (`ID_ROL_MOD_PERM_USR` )
    REFERENCES `astecadb`.`roles_mod_permisos_usr` (`ID_ROL_MOD_PER_USR` ),
  CONSTRAINT `FK_ROL_MOD_PER_SOBR`
    FOREIGN KEY (`ID_PERMISOS` )
    REFERENCES `astecadb`.`permisos` (`ID_PERMISO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `astecadb` ;
USE `astecadb`;

DELIMITER $$

USE `astecadb`$$
DROP TRIGGER IF EXISTS `astecadb`.`encodePassword` $$
USE `astecadb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `astecadb`.`encodePassword`
BEFORE INSERT ON `astecadb`.`personas`
FOR EACH ROW
set NEW.PASSWRD = sha1(NEW.PASSWRD);$$


USE `astecadb`$$
DROP TRIGGER IF EXISTS `astecadb`.`updateEncodedPassword` $$
USE `astecadb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `astecadb`.`updateEncodedPassword`
BEFORE UPDATE ON `astecadb`.`personas`
FOR EACH ROW
set NEW.PASSWRD = sha1(NEW.PASSWRD);$$


DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
