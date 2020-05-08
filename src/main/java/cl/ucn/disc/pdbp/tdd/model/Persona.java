package cl.ucn.disc.pdbp.tdd.model;

/**
 * Clase que representa una persona
 */
public final class Persona {

    /**
     * Nombre de la persona
     */
    private String nombre;

    /**
     * Apellido de la persona
     */
    private String apellido;

    /**
     * Rut de la persona
     */
    private String rutOk;




    /**
     * Constructor de una persona
     * @param nombre a utilizar
     * @param apellido a utilizar
     * @param rut valido
     */
    public Persona(String nombre, String apellido, String rut) {
        Validation validar = new Validation ();

        //Verificaciones
        validar.verificarNulidad(nombre,apellido,rut);

        validar.verificarTamanioNombre(nombre);
        this.nombre = nombre;

        validar.verificarTamanioApellido(apellido);
        this.apellido = apellido;

        validar.validarRut2(rutOk);
        this.rutOk = rut;


    }

    /**
     * Funcion que retorna el nombre de la persona
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Funcion que entrega el apellido de la persona
     */
    public String getApellido(){
        return this.apellido;
    }

    /**
     * Retorna el nombre y apellido de la perosna
     */
    public String getNombreApellido() {
        return this.nombre + " " + this.apellido;
    }
}
