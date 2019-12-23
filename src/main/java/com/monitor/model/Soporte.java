package com.monitor.model;
// Generated 23/06/2017 12:40:03 PM by Hibernate Tools 5.2.3.Final

import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Soporte generated by hbm2java
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "SOPORTE", schema = "MONITOR")
public class Soporte implements java.io.Serializable {

	private int idSoporte;
	private Blob docto;

	public Soporte() {
	}

	public Soporte(int idSoporte) {
		this.idSoporte = idSoporte;
	}

	public Soporte(int idSoporte, Blob docto) {
		this.idSoporte = idSoporte;
		this.docto = docto;
	}

	@Id

	@Column(name = "ID_SOPORTE", unique = true, nullable = false, precision = 6, scale = 0)
	public int getIdSoporte() {
		return this.idSoporte;
	}

	public void setIdSoporte(int idSoporte) {
		this.idSoporte = idSoporte;
	}

	@Column(name = "DOCTO")
	public Blob getDocto() {
		return this.docto;
	}

	public void setDocto(Blob docto) {
		this.docto = docto;
	}

}