package cl.ucn.disc.pdbp.tdd.model;

/**
 * Clase que representa una Foto
 */
public final class Foto {

    /**
     * Id de la foto
     */
    private Long idFoto;

    /**
     * Nombre de la foto
     */
    private String nombreFoto;

    /**
     * Constructor vacio de Foto
     */
    Foto () {

    }

    /**
     * Constructor de la clase Foto
     * @param nombreFoto
     */
    public Foto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    /**
     * @return id de la foto
     */
    public Long getIdFoto() {
        return idFoto;
    }

    /**
     * @return nombre de la foto
     */
    public String getNombreFoto() {
        return nombreFoto;
    }
}
