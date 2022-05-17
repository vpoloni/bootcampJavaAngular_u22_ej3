package models.dto;

public class ProyectoDTO {
	
	// atributos
	private String idProyecto;
	private String nombreProyecto;
	private int horasProyecto;

	// getters y setters
	public String getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public int getHorasProyecto() {
		return horasProyecto;
	}

	public void setHorasProyecto(int horasProyecto) {
		this.horasProyecto = horasProyecto;
	}

}
