module producto2_POO_BBDD {
requires java.sql;
requires org.hibernate.orm.core;
requires java.naming;
//requires junit;
requires jakarta.persistence;
requires org.antlr.antlr4.runtime;
exports grupofp.modelo;
opens grupofp.modelo to org.hibernate.orm.core;

}