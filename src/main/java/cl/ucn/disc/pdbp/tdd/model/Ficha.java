package cl.ucn.disc.pdbp.tdd.model;
import java.time.ZonedDateTime;
/**
 *Ficha Veterinaria
 */
public final class Ficha {

    /**
     * Numero de ficha
     */
    private final long numero;

    /**
     * Fecha de nacimiento
     */
    private final ZonedDateTime fechaNacimiento;

    /**
     * Nombre del paciente
     */
    private final String nombrePaciente;

    /**
     * Especie del paciente
     */
    private final String especie;

    /**
     * Raza del paciente
     */
    private final String raza;

    /**
     * Sexo del paciente
     */
    private final Sexo sexo;

    /**
     * Color del paciente
     */
    private final String color;

    /**
     * Tipo del paciente
     */
    private final Tipo tipo;

    /**
     * Constructor de Ficha
     * @param numero
     * @param fechaNacimiento
     * @param nombrePaciente
     * @param especie
     * @param raza
     * @param sexo
     * @param color
     * @param tipo
     */
    public Ficha(long numero, ZonedDateTime fechaNacimiento, String nombrePaciente, String especie, String raza, Sexo sexo, String color, Tipo tipo) {
        this.numero = numero;
        this.fechaNacimiento = fechaNacimiento;
        this.nombrePaciente = nombrePaciente;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tipo = tipo;
    }

    /**
     *
     * @return the numero
     */
    public long getNumero() {
        return numero;
    }

    /**
     *
     * @return the fecha Nacimiento
     */
    public ZonedDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @return the nombrePaciente
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     *
     * @return the Especie
     */
    public String getEspecie() {
        return especie;
    }

    /**
     *
     * @return the Raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     *
     * @return the Sexo
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }
}
