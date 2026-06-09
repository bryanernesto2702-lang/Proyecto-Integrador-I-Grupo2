package pe.edu.utp;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.List;

public class GuavaTest {

    public static void main(String[] args) {

        System.out.println("--- PRUEBA 1: JOINER (Generación de SKU/Reporte) ---");
        probarJoiner();

        System.out.println("\n--- PRUEBA 2: PRECONDITIONS (Validación de Stock) ---");

        // Prueba exitosa: Se intenta ingresar una cantidad válida
        probarValidacionSegura(25);

        // Descomentar la siguiente línea para provocar el Fail-Fast por cantidad negativa
        // probarValidacionSegura(-5);
    }

    /**
     * Demuestra cómo Joiner concatena los elementos de un producto ignorando valores nulos,
     * útil para generar etiquetas, descripciones compuestas o filas de reportes.
     */
    private static void probarJoiner() {

        // Datos simulados de un producto en Multitop (SKU, Nombre, Categoría, Ubicación opcional)
        List<String> datosProducto = Arrays.asList(
                "CAR-122",
                "Tela Velvet Azul",
                "Tapicería",
                null, // Ubicación en estante no asignada aún
                "Sede Lima Centro"
        );

        // Une los datos usando un separador limpio e ignorando el valor nulo
        String resultado = Joiner.on(" | ")
                .skipNulls()
                .join(datosProducto);

        System.out.println("Registro de Producto formateado: " + resultado);
    }

    /**
     * Demuestra el principio Fail-Fast aplicado al control de inventario.
     * Valida las reglas de negocio antes de realizar operaciones en la base de datos.
     */
    private static void probarValidacionSegura(int cantidadMovimiento) {

        try {

            // Validación con Guava: El stock a ingresar o modificar no puede ser negativo
            // Esto asegura la integridad antes de actualizar la tabla INVENTARIO
            Preconditions.checkArgument(
                    cantidadMovimiento >= 0,
                    "Error de Inventario: La cantidad ingresada (%s) no puede ser negativa.",
                    cantidadMovimiento
            );

            System.out.println(
                    "Validación exitosa. Cantidad de "
                            + cantidadMovimiento + " unidades lista para procesar."
            );

        } catch (IllegalArgumentException e) {

            System.err.println("Fail-Fast activado: " + e.getMessage());
        }
    }
}