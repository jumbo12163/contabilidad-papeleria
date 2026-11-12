
package com.example.contabilidadpapeleria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionOracle {

    private static final String URL =
            "jdbc:oracle:thin:@//localhost:1521/FREEPDB1";

    private static final String USUARIO = "SYSTEM";

    // Conserva tu contraseña actual de Oracle.
    private static final String CLAVE = "espe_jumbo14";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    // CONSULTAR CUENTAS CONTABLES
    public static void consultarCuentas() {

        String sql = "SELECT C.CODIGO, C.NOMBRE AS CUENTA, "
                + "T.NOMBRE AS TIPO "
                + "FROM CUENTA_CONTABLE C "
                + "JOIN TIPO_CUENTA T ON C.ID_TIPO = T.ID_TIPO "
                + "ORDER BY C.CODIGO";

        try (Connection conexion = conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            System.out.println("\n===== CUENTAS CONTABLES =====");

            while (resultado.next()) {
                System.out.println(
                        resultado.getString("CODIGO") + " | "
                        + resultado.getString("CUENTA") + " | "
                        + resultado.getString("TIPO")
                );
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al consultar las cuentas: " + e.getMessage());
        }
    }

    // CONSULTAR COMPROBANTES CONTABLES
    public static void consultarComprobantes() {

        String sql = "SELECT ID_COMPROBANTE, FECHA, "
                + "OBSERVACIONES, ESTADO "
                + "FROM COMPROBANTE_CONTABLE "
                + "ORDER BY ID_COMPROBANTE DESC";

        try (Connection conexion = conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            System.out.println("\n===== COMPROBANTES CONTABLES =====");

            while (resultado.next()) {
                System.out.println(
                        "Número: "
                        + resultado.getInt("ID_COMPROBANTE")
                        + " | Fecha: "
                        + resultado.getDate("FECHA")
                        + " | Observaciones: "
                        + resultado.getString("OBSERVACIONES")
                        + " | Estado: "
                        + resultado.getString("ESTADO")
                );
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al consultar comprobantes: "
                    + e.getMessage());
        }
    }

    // CONSULTAR DETALLE DE UN COMPROBANTE
    public static void consultarDetalleComprobante(int idComprobante) {

        String sql = "SELECT C.CODIGO, C.NOMBRE AS CUENTA, "
                + "D.DEBE, D.HABER "
                + "FROM DETALLE_CONTABLE D "
                + "JOIN CUENTA_CONTABLE C "
                + "ON D.ID_CUENTA = C.ID_CUENTA "
                + "WHERE D.ID_COMPROBANTE = ? "
                + "ORDER BY D.ID_DETALLE";

        try (Connection conexion = conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idComprobante);

            try (ResultSet resultado = sentencia.executeQuery()) {

                System.out.println(
                        "\n===== DETALLE DEL COMPROBANTE "
                        + idComprobante + " =====");

                double totalDebe = 0;
                double totalHaber = 0;
                boolean encontrado = false;

                while (resultado.next()) {
                    encontrado = true;

                    double debe = resultado.getDouble("DEBE");
                    double haber = resultado.getDouble("HABER");

                    System.out.println(
                            resultado.getString("CODIGO") + " | "
                            + resultado.getString("CUENTA")
                            + " | Debe: " + debe
                            + " | Haber: " + haber
                    );

                    totalDebe += debe;
                    totalHaber += haber;
                }

                if (!encontrado) {
                    System.out.println(
                            "No se encontraron detalles para este comprobante.");
                }

                System.out.println("------------------------------");
                System.out.println("TOTAL DEBE: " + totalDebe);
                System.out.println("TOTAL HABER: " + totalHaber);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al consultar el detalle: " + e.getMessage());
        }
    }

    // PROGRAMA PRINCIPAL
    public static void main(String[] args) {

        try (Connection conexion = conectar()) {
            System.out.println("Conexion con Oracle exitosa.");
            System.out.println("Base de datos conectada correctamente.");

        } catch (SQLException e) {
            System.out.println("No se pudo conectar con Oracle.");
            System.out.println("Detalle: " + e.getMessage());
            return;
        }

        consultarCuentas();
        consultarComprobantes();
        consultarDetalleComprobante(1);
    }
}
