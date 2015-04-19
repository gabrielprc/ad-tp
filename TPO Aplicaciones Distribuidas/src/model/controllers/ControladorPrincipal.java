package model.controllers;

import java.util.List;

import model.impl.Cliente;
import model.impl.Cobro;
import model.impl.CompaniaSeguro;
import model.impl.EstrategiaMantenimiento;
import model.impl.Pago;
import model.impl.Proveedor;
import model.impl.Sucursal;

public class ControladorPrincipal {
	private static ControladorPrincipal instance;
	private List<Sucursal> sucursales;
	private List<Cliente> clientes;
	private List<CompaniaSeguro> companiasSeguros;
	private List<String> materialesNoTransportables;
	private List<Proveedor> proveedores;
	private List<Pago> pagos;
	private List<Cobro> cobro;
	private List<EstrategiaMantenimiento> mantenimientos;

	private ControladorPrincipal() {

	}

	public static ControladorPrincipal getInstance() {
		if (instance == null) {
			instance = new ControladorPrincipal();
		}
		return instance;
	}

}
