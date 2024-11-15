import modelos.Empleado;
import utils.EmpleadoUtils;

import java.util.Scanner;

public class Application {
    private static final int MAX_EMPLEADOS = 10;
    private static Empleado[] empleados = new Empleado[MAX_EMPLEADOS];
    private static int totalEmpleados = 0;

    public static void main(String[] args) {
        System.out.println("\033\143");
        Scanner scanner = new Scanner(System.in);
        int option;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- MENU SISTEMA DE GESTION ---");
            System.out.println("1. Mostrar todos los empleados");
            System.out.println("2. Crear empleado");
            System.out.println("3. Filtrar empleados");
            System.out.println("4. Ordenar empleados");
            System.out.println("5. Incrementar salario");
            System.out.println("6. Limpiar filtros");
            System.out.println("7. Salir del programa");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (option) {
                case 1 -> mostrarEmpleados();
                case 2 -> crearEmpleado(scanner);
                case 3 -> filtrarEmpleados(scanner);
                case 4 -> ordenarEmpleadosPorAtributo(scanner);
                case 5 -> incrementarSalario(scanner);
                case 6 -> limpiarFiltros();
                case 7 -> exit = true;
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
        System.out.println("Programa finalizado.");
    }

    private static void mostrarEmpleados() {
        if (totalEmpleados == 0) {
            System.out.println("No hay empleados registrados.");
        } else {
            EmpleadoUtils.mostrarTodos(empleados, totalEmpleados);
        }
    }

    private static void crearEmpleado(Scanner scanner) {
        if (totalEmpleados >= MAX_EMPLEADOS) {
            System.out.println("No se pueden agregar más empleados, capacidad máxima alcanzada.");
            return;
        }

        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        if (EmpleadoUtils.buscarPorNombre(empleados, nombre, totalEmpleados)) {
            System.out.println("Error: Ya existe un empleado con este nombre.");
            return;
        }

        System.out.print("Ingrese la edad del empleado: ");
        int edad = scanner.nextInt();
        System.out.print("Ingrese el salario del empleado: ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Ingrese el departamento del empleado: ");
        String departamento = scanner.nextLine();

        Empleado nuevoEmpleado = new Empleado(nombre, edad, salario, departamento);
        empleados[totalEmpleados++] = nuevoEmpleado;
        System.out.println("Empleado creado con éxito.");
    }

    private static void filtrarEmpleados(Scanner scanner) {
        System.out.println("Seleccione el atributo para filtrar:");
        System.out.println("1. Nombre");
        System.out.println("2. Edad");
        System.out.println("3. Salario");
        System.out.println("4. Departamento");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
    
        Empleado[] filtrados = new Empleado[totalEmpleados];
    
        switch (opcion) {
            case 1 -> {
                System.out.print("Ingrese el nombre a filtrar: ");
                String nombre = scanner.nextLine();
                filtrados = EmpleadoUtils.filtrarPorNombre(empleados, nombre, totalEmpleados);
            }
            case 2 -> {
                System.out.print("Ingrese la edad mínima: ");
                int edadMin = scanner.nextInt();
                System.out.print("Ingrese la edad máxima: ");
                int edadMax = scanner.nextInt();
                filtrados = EmpleadoUtils.filtrarPorEdad(empleados, edadMin, edadMax, totalEmpleados);
            }
            case 3 -> {
                System.out.print("Ingrese el salario mínimo: ");
                double salarioMin = scanner.nextDouble();
                System.out.print("Ingrese el salario máximo: ");
                double salarioMax = scanner.nextDouble();
                filtrados = EmpleadoUtils.filtrarPorSalario(empleados, salarioMin, salarioMax, totalEmpleados);
            }
            case 4 -> {
                System.out.print("Ingrese el departamento a filtrar: ");
                String departamento = scanner.nextLine();
                filtrados = EmpleadoUtils.filtrarPorDepartamento(empleados, departamento, totalEmpleados);
            }
            default -> System.out.println("Opción de filtro no válida.");
        }
    
        if (filtrados[0] == null) {
            System.out.println("No se encontraron empleados con ese filtro.");
        } else {
            System.out.println("Empleados filtrados:");
            EmpleadoUtils.mostrarTodos(filtrados, totalEmpleados);
        }
    }
    
    private static void ordenarEmpleadosPorAtributo(Scanner scanner) {
        System.out.println("¿Por qué atributo desea ordenar a los empleados?");
        System.out.println("1. Nombre");
        System.out.println("2. Edad");
        System.out.println("3. Salario");
        System.out.println("4. Departamento");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer
    
        String atributo = "";
        switch (opcion) {
            case 1:
                atributo = "nombre";
                break;
            case 2:
                atributo = "edad";
                break;
            case 3:
                atributo = "salario";
                break;
            case 4:
                atributo = "departamento";
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
    
        // Pasamos totalEmpleados como parámetro
        Empleado[] empleadosOrdenados = EmpleadoUtils.ordenarEmpleados(empleados, atributo, totalEmpleados);
        System.out.println("Empleados ordenados por " + atributo + ":");
        EmpleadoUtils.mostrarTodos(empleadosOrdenados, totalEmpleados);
    }
    
    

    
    private static void incrementarSalario(Scanner scanner) {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        Empleado empleadoEncontrado = null;
    
        // Buscar al empleado por nombre en el arreglo
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getNombre().equalsIgnoreCase(nombre)) {
                empleadoEncontrado = empleados[i];
                break;
            }
        }
    
        if (empleadoEncontrado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
    
        System.out.print("Ingrese el porcentaje de aumento: ");
        double porcentaje = scanner.nextDouble();
    
        // Llamar al método de incremento de salario con el empleado encontrado
        EmpleadoUtils.incrementarSalario(empleadoEncontrado, porcentaje);
        System.out.println("Salario actualizado a: " + empleadoEncontrado.getSalario());
    }
    
    private static void limpiarFiltros() {
        System.out.println("Filtros limpiados.");
    }
}
