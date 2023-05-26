module producto2_POO_BBDD {
requires java.sql;
requires org.hibernate.orm.core;
requires java.naming;
//requires junit;
requires javafx.controls;
requires javafx.media;
requires jakarta.persistence;
requires org.antlr.antlr4.runtime;
exports grupofp.vista to javafx.graphics;
exports grupofp.modelo;
opens grupofp.modelo to org.hibernate.orm.core;

}