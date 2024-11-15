package utils;

import modelos.Empleado;

public class EmpleadoUtils {

    /*public static void mostrarTodos(Empleado[] empleados, int totalEmpleados) {
        System.out.println("ID | Nombre | Edad | Salario | Departamento");
        for (int i = 0; i < totalEmpleados; i++) {
            System.out.println((i + 1) + " | " + empleados[i]);
        }
    }/* */

    public static boolean buscarPorNombre(Empleado[] empleados, String nombre, int totalEmpleados) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public static Empleado[] ordenarEmpleados(Empleado[] empleados, String atributo, int totalEmpleados) {
        // Solo ordenar hasta el totalEmpleados y no el tamaño completo del arreglo
        for (int i = 0; i < totalEmpleados - 1; i++) {
            for (int j = 0; j < totalEmpleados - i - 1; j++) {
                boolean modificar = false;
                switch (atributo.toLowerCase()) {
                    case "nombre":
                        modificar = empleados[j].getNombre().compareTo(empleados[j + 1].getNombre()) > 0;
                        break;
                    case "edad":
                        modificar = empleados[j].getEdad() > empleados[j + 1].getEdad();
                        break;
                    case "salario":
                        modificar = empleados[j].getSalario() > empleados[j + 1].getSalario();
                        break;
                    case "departamento":
                        modificar = empleados[j].getDepartamento().compareTo(empleados[j + 1].getDepartamento()) > 0;
                        break;
                }
                if (modificar) {
                    Empleado temp = empleados[j];
                    empleados[j] = empleados[j + 1];
                    empleados[j + 1] = temp;
                }
            }
        }
        return empleados;
    }
    


    public static Empleado[] filtrarPorNombre(Empleado[] empleados, String nombre, int totalEmpleados) {
        Empleado[] filtrados = new Empleado[totalEmpleados];
        int index = 0;
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getNombre().equalsIgnoreCase(nombre)) {
                filtrados[index++] = empleados[i];
            }
        }
        return filtrados;
    }

    public static Empleado[] filtrarPorEdad(Empleado[] empleados, int edadMin, int edadMax, int totalEmpleados) {
        Empleado[] filtrados = new Empleado[totalEmpleados];
        int index = 0;
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getEdad() >= edadMin && empleados[i].getEdad() <= edadMax) {
                filtrados[index++] = empleados[i];
            }
        }
        return filtrados;
    }

    public static Empleado[] filtrarPorSalario(Empleado[] empleados, double salarioMin, double salarioMax, int totalEmpleados) {
        Empleado[] filtrados = new Empleado[totalEmpleados];
        int index = 0;
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getSalario() >= salarioMin && empleados[i].getSalario() <= salarioMax) {
                filtrados[index++] = empleados[i];
            }
        }
        return filtrados;
    }

    public static Empleado[] filtrarPorDepartamento(Empleado[] empleados, String departamento, int totalEmpleados) {
        Empleado[] filtrados = new Empleado[totalEmpleados];
        int index = 0;
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getDepartamento().equalsIgnoreCase(departamento)) {
                filtrados[index++] = empleados[i];
            }
        }
        return filtrados;
    }

    public static void mostrarTodos(Empleado[] empleados, int totalEmpleados) {
        System.out.println("EMPLEADOS ------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-15s %-15s%n", "Nro", "Nombre", "Edad", "Salario", "Departamento");
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i] != null) {
                System.out.printf("%-5d %-20s %-10d %-15.2f %-15s%n", 
                    i + 1, empleados[i].getNombre(), empleados[i].getEdad(),
                    empleados[i].getSalario(), empleados[i].getDepartamento());
            }
        }
    }

    public static void incrementarSalario(Empleado empleado, double porcentaje) {
        double salarioActualizado = empleado.getSalario() + (empleado.getSalario() * porcentaje / 100);
        empleado.setSalario(salarioActualizado);
    }
    
}
