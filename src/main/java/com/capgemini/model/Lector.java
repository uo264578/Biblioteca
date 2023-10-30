package com.capgemini.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="lector")
public class Lector {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long nSocio;
	
	@Column
	private String nombre;

	@Column
	private String telefono;

	@Column
	private String direccion;
	
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="fk_multa")
	private Multa multa;
	
	
	
	@OneToMany(mappedBy="lector", targetEntity=Prestamo.class, cascade=CascadeType.ALL)
	private Set<Prestamo> prestamos;

	public Multa multar(int n) {
		if (this.multa != null) {
			throw new IllegalStateException("El lector ya tiene una multa activa");
		}
		Multa multa = new Multa(LocalDate.now(), LocalDate.now().plusDays(n), this);
		this.multa = multa;
		return multa;
	}
	
	
	
	public Long getnSocio() {
		return nSocio;
	}

	public void setnSocio(Long nSocio) {
		this.nSocio = nSocio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Multa getMulta() {
		return multa;
	}

	public void setMulta(Multa multa) {
		this.multa = multa;
	}

	public Set<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(Set<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	
}
