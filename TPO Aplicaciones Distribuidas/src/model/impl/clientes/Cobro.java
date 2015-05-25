package model.impl.clientes;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.impl.PersistentObject;

@Entity
@Table(name = "Cobros")
public class Cobro extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6031414221082912157L;

	@ManyToOne
	@Column(name = "idFactura")
	private Factura factura;
	@OneToMany
	@JoinColumn(name = "idCobro")
	private Vector<CobroParcial> cobrosParciales;

	public Cobro(Factura factura){
		
		this.factura = factura;
		cobrosParciales = new Vector<CobroParcial>();
	}
	
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<CobroParcial> getCobrosParciales() {
		return cobrosParciales;
	}

	public void setCobrosParciales(Vector<CobroParcial> cobrosParciales) {
		this.cobrosParciales = cobrosParciales;
	}
	
	public float calcularMontoAPagarTotal(){
		
		Float pagado = 0f;
		for(CobroParcial c : cobrosParciales)
			if(c.isPagado())
				pagado += c.getMonto();
		
		return factura.getMonto() - pagado;
	}
	
	public float calcularMontoPorCuota(Integer cantidadCuotas){
		
		return factura.getMonto() / cantidadCuotas;
	}
	
	@SuppressWarnings("deprecation")
	public void establecerPagoEnCuotas(Integer cantidadCuotas){
		
		Float cuota = calcularMontoPorCuota(cantidadCuotas);
		
		for(int i = 0; i < cantidadCuotas; i++){
			Date now = new Date();
			now.setMonth(now.getMonth() + i);
			Date fechaVencimiento = now;
			cobrosParciales.add(new CobroParcial(fechaVencimiento, cuota));	
		}
	}
	
	public void pagarCuota() throws Exception{
		
		for(CobroParcial c : cobrosParciales)
			if(!c.isPagado()){
				c.setPagado(true);
				return ;
			}
		throw new Exception("No quedan cuotas por pagar");
	}
	
}
