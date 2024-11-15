package modelos;

public class Empleado {
    private String nombre;
    private int edad;
    private double salario;
    private String departamento;

    public Empleado() {
    }

    // Constructor con todos los parámetros
    public Empleado(String nombre, int edad, double salario, String departamento) {
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
        this.departamento = departamento;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    /*@Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", Salario: " + salario + ", Departamento: " + departamento;
    }*/
}
