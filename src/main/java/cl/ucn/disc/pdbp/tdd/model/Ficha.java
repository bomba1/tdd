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
    private final String sexo;

    /**
     * Color del paciente
     */
    private final String color;

    /**
     * Tipo del paciente
     */
    private final String tipo;
}
