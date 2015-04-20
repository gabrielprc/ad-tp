package model.controllers;

import java.util.ArrayList;
import java.util.List;

import model.impl.Cliente;
import model.impl.Cobro;
import model.impl.CompaniaSeguro;
import model.impl.Empresa;
import model.impl.EstrategiaMantenimiento;
import model.impl.Pago;
import model.impl.Particular;
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
	private List<Cobro> cobros;
	private List<EstrategiaMantenimiento> mantenimientos;

	private ControladorPrincipal() {

		clientes = new ArrayList<Cliente>();
		sucursales = new ArrayList<Sucursal>();
		companiasSeguros = new ArrayList<CompaniaSeguro>();
		materialesNoTransportables = new ArrayList<String>();
		proveedores = new ArrayList<Proveedor>();
		pagos = new ArrayList<Pago>();
		cobros = new ArrayList<Cobro>();
		mantenimientos = new ArrayList<EstrategiaMantenimiento>();

	}

	public static ControladorPrincipal getInstance() {
		if (instance == null) {
			instance = new ControladorPrincipal();
		}
		return instance;
	}

	public void altaClienteParticular(String codigoUnico, String dni,
			String nombre, String apellido) {

		clientes.add(new Particular(codigoUnico, dni, nombre, apellido));

	}

	public void altaClienteEmpresa(String codigoUnico, String nombre) {

		clientes.add(new Empresa(codigoUnico, nombre));
	}

	public Cliente obtenerCliente(String codigoUnico) {

		for (Cliente c : clientes)
			if (c.getCodigoUnico().equals(codigoUnico))
				return c;
		return null;
	}

}
