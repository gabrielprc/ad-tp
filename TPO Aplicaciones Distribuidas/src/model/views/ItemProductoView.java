package model.views;

public class ItemProductoView {
	public int producto;
	public float cantidad;
	
	public ItemProductoView(int producto, float cantidad){
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public int getProducto() {
		return producto;
	}
	public void setProducto(int producto) {
		this.producto = producto;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}	
}
