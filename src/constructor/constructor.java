/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constructor;

import conexionDB.conexionDB;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import punto_venta.menuAdmin;


/**
 *
 * @author israe
 */
public class constructor {  
    private conexionDB conDB = new conexionDB();
    
    

   public String[] Login(String usuario, String contrasena) {
    String acceso=null;
    try (Connection con = conDB.conectar()) {
        String query = "SELECT * FROM vendedores WHERE (CORREO=? OR TELEFONO=?) AND CONTRASENA=?";

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, usuario);
            pst.setString(2, usuario);
            pst.setString(3, contrasena);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Obtén todos los datos del usuario
                    String nombres = rs.getString("NOMBRES");
                    String apellidoP = rs.getString("Apellidopaterno");
                    String apellidoM = rs.getString("Apellidomaterno");
                    String correo = rs.getString("CORREO");
                    String telefono = rs.getString("TELEFONO");
                    String tipoUsuario = rs.getString("TIPOUSUARIO").toUpperCase();

                    // Imprime los datos del usuario en la consola
                    System.out.println("Nombres: " + nombres);
                    System.out.println("Apellidopaterno: " + apellidoP);
                    System.out.println("Apellidomaterno: " + apellidoM);
                    System.out.println("Correo: " + correo);
                    System.out.println("Teléfono: " + telefono);
                    System.out.println("Tipo de Usuario: " + tipoUsuario);

                    // Muestra un cuadro de diálogo con la información del usuario
                    String mensaje = "Nombres: " + nombres + "\nCorreo: " + correo + "\nTeléfono: " + telefono + "\nTipo de Usuario: " + tipoUsuario;
                    if ("ADMINISTRADOR".equals(tipoUsuario)) {
                        
                        return new String[] {"ADMINISTRADOR", nombres};
                        
                    } else if ("VENDEDOR".equals(tipoUsuario)) {
                        return new String[] {"VENDEDOR", nombres};
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado en la base de datos");
                }
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
    }
        return null;
   }
   
   
   
   
   
   
   //   *******     **************           ****
   //   ********    **************          ******
   //   **    ***       ******             ***  ***
   //   **    ***       ******            ***    ***
   //   **    ***       ******           ************
   //   **    ***       ******          ************** 
   //   **    ***       ******         ****        ****  
   //   ********    **************    ****          ****
   //   ********    **************   ****            ****
   
  

        
  
   
        public Map<String, Object> buscarPorDiaMesAnio(int dia, int mes, int anio) {
            List<ResultadoVentaDia> resultados = new ArrayList<>();
            double totalDia = 0;
            
            try (Connection connection = conDB.conectar()) {
                String fechaBusqueda = String.format("%04d-%02d-%02d", anio, mes, dia);
                String consultaSQL = "SELECT v.ID, v.IDVendedor, v.Fecha, v.Total, ve.Nombres, dv.Subtotal " +
                                    "FROM Ventas v " +
                                    "INNER JOIN Vendedores ve ON v.IDVendedor = ve.ID " +
                                    "INNER JOIN DetalleVentas dv ON v.ID = dv.IDVenta " +
                                    "WHERE v.Fecha = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
                    preparedStatement.setDate(1, java.sql.Date.valueOf(fechaBusqueda));


                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (!resultSet.next()) {
                            System.out.println("No se encontró el día buscado.");
                            JOptionPane.showMessageDialog(null, "NO SE ENTONTRO EL DIA BUSCADO \n REVISA TU DIA MES Y AÑO SEAN CORRECTOS");
                        } else {
                            do {
                                int idVenta = resultSet.getInt("ID");
                                int idVendedor = resultSet.getInt("IDVendedor");
                                Date fechaVenta = resultSet.getDate("Fecha");
                                String nombreVendedor = resultSet.getString("Nombres");
                                double totalVenta = resultSet.getDouble("Total");
                                double subtotalVenta = resultSet.getDouble("Subtotal");

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                String fechaFormateada = dateFormat.format(fechaVenta);

                                System.out.println("ID Venta: " + idVenta);
                                System.out.println("ID Vendedor: " + idVendedor);
                                System.out.println("Nombre del Vendedor: " + nombreVendedor);
                                System.out.println("Fecha: " + fechaFormateada);
                                System.out.println("Subtotal Venta: " + subtotalVenta);
                                System.out.println("Total Venta: " + totalVenta);

                                totalDia += totalVenta;

                                ResultadoVentaDia resultado = new ResultadoVentaDia(idVenta, idVendedor, fechaFormateada, nombreVendedor, totalVenta, subtotalVenta);
                                resultados.add(resultado);

                                System.out.println("--------------------------------------------");
                            } while (resultSet.next());
                        }
                    }

                    System.out.println("Total Ventas del Día " + dia + " del Mes " + mes + " en el Año " + anio + ": " + totalDia);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Map<String, Object> resultadoMap = new HashMap<>();
                resultadoMap.put("resultados", resultados);
                resultadoMap.put("totalDia", totalDia);
            return resultadoMap;
        }

    

    
   
   


    public class ResultadoVentaDia {
        private int idVenta;
        private int idVendedor;
        private String fechaVenta;
        private String nombreVendedor;
        private double totalVenta;
        private double subtotalVenta;

        public ResultadoVentaDia(int idVenta, int idVendedor, String fechaVenta, String nombreVendedor, double totalVenta, double subtotalVenta) {
            this.idVenta = idVenta;
            this.idVendedor = idVendedor;
            this.fechaVenta = fechaVenta;
            this.nombreVendedor = nombreVendedor;
            this.totalVenta = totalVenta;
            this.subtotalVenta = subtotalVenta;
        }

        public int getIdVenta() {
            return idVenta;
        }

        public int getIdVendedor() {
            return idVendedor;
        }
        public String getNombreVendedor() {
                return nombreVendedor;
            }
        public String getFechaVenta() {
            return fechaVenta;
        }
        public double getTotalVenta() {
            return totalVenta;
        }

        public double getSubtotalVenta() {
            return subtotalVenta;
        }
    }   
   
   
   
   
   

    
   //   *******    *******       **************      *********
   //   ********* ********       **************    *************       
   //   ***** *****  *****       *****             ****           
   //   *****   **   *****       **********        **********
   //   *****        *****       **********         ***********       
   //   *****        *****       *****                     *****
   //   *****        *****       *****                      ****
   //   *****        *****       **************     ************ 
   //   *****        *****       **************       *********
   
  

        
  
    
       
    public Map<String, Object> buscarPorMesYAnio(int mes, int anio) {
        List<ResultadoVentaMes> resultados = new ArrayList<>();
        float totalMes=0;
    try (Connection connection = conDB.conectar()) {
            // Proporciona el mes y año
//            int mesBusqueda = 1;
//            int anioBusqueda = 2024;

            // Realizar la consulta SQL para buscar ventas por mes y año
            String consultaSQL = "SELECT v.ID, v.IDVendedor, v.Fecha, v.Total, ve.Nombres, dv.Subtotal " +
                                "FROM Ventas v " +
                                "INNER JOIN Vendedores ve ON v.IDVendedor = ve.ID " +
                                "INNER JOIN DetalleVentas dv ON v.ID = dv.IDVenta " +
                                "WHERE MONTH(v.Fecha) = ? AND YEAR(v.Fecha) = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
                preparedStatement.setInt(1, mes);
                preparedStatement.setInt(2, anio);


                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Procesar los resultados
                    if (!resultSet.next()) {
                        System.out.println("No se encontró nada en el mes.");
                        JOptionPane.showMessageDialog(null, "NO SE ENTONTRO EL MES BUSCADO \n REVISA QUE TU MES Y AÑO SEAN CORRECTOS");
                    } else {
                        do {
                            int idVenta = resultSet.getInt("ID");
                            int idVendedor = resultSet.getInt("IDVendedor");
                            Date fechaVenta = resultSet.getDate("Fecha");
                            String nombreVendedor = resultSet.getString("Nombres");
                            double totalVenta = resultSet.getDouble("Total");
                            double subtotalVenta = resultSet.getDouble("Subtotal");
                            // Otros campos...

                            // Formatear la fecha al nuevo formato (solo mes y año)
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            String fechaFormateada = dateFormat.format(fechaVenta);

                            // Imprimir los resultados
                            System.out.println("ID Venta: " + idVenta);
                            System.out.println("ID Vendedor: " + idVendedor);
                            System.out.println("Nombre del Vendedor: " + nombreVendedor);
                            System.out.println("Fecha: " + fechaFormateada); // Imprime la fecha formateada
                            System.out.println("Subtotal Venta: " + subtotalVenta);
                            System.out.println("Total Venta: " + totalVenta);

                            // Acumular el total de venta al totalMes
                            totalMes += totalVenta;
                            // Crear objeto ResultadoVentaMes y agregarlo a la lista
                            ResultadoVentaMes resultado = new ResultadoVentaMes(idVenta, idVendedor, fechaFormateada, nombreVendedor, totalVenta, subtotalVenta);
                            resultados.add(resultado);

                            // Imprimir otros campos si es necesario
                            System.out.println("--------------------------------------------");
                        } while (resultSet.next());
                    }
                }

                // Imprimir el total acumulado al final
                System.out.println("Total Ventas del Mes " + mes + " en el Año " + anio + ": " + totalMes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar el error según tus necesidades
        }
     Map<String, Object> resultadoMap = new HashMap<>();
                resultadoMap.put("resultados", resultados);
                resultadoMap.put("totalMes", totalMes);
            return resultadoMap;

    }
       
   public class ResultadoVentaMes {
    private int idVenta;
    private int idVendedor;
    private String fechaVenta;
    private String nombreVendedor;
    private double totalVenta;
    private double subtotalVenta;

    public ResultadoVentaMes(int idVenta, int idVendedor, String fechaVenta, String nombreVendedor, double totalVenta, double subtotalVenta) {
        this.idVenta = idVenta;
        this.idVendedor = idVendedor;
        this.fechaVenta = fechaVenta;
        this.nombreVendedor = nombreVendedor;
        this.totalVenta = totalVenta;
        this.subtotalVenta = subtotalVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdVendedor() {
        return idVendedor;
    }
    public String getNombreVendedor() {
            return nombreVendedor;
        }
    public String getFechaVenta() {
        return fechaVenta;
    }
    public double getTotalVenta() {
        return totalVenta;
    }

    public double getSubtotalVenta() {
        return subtotalVenta;
    }
} 
   
   
   
   
   
   
//                                     ******   
//                                     ******
//             ******           *******      *****     **********
//            ***  ***          ********     *****    ************
//           ***    ***         *********    *****   ***        ***
//          ************        ***** ****   *****  ****         ***
//         **************       *****  ****  *****  ***          ***
//        ****        ****      *****   **** *****   ***        ***
//       ****          ****     *****    *********    ************
//      ****            ****    *****     ********     **********
            
  

        
  

    public Map<String, Object> buscarPorAnio(int anio) {
         List<ResultadoVentaAnio> resultados = new ArrayList<>();
         float totalAnio=0;
    try (Connection connection = conDB.conectar()) {
        String consultaSQL = "SELECT v.ID, v.IDVendedor, v.Fecha, v.Total, ve.Nombres, dv.Subtotal " +
                "FROM Ventas v " +
                "INNER JOIN Vendedores ve ON v.IDVendedor = ve.ID " +
                "INNER JOIN DetalleVentas dv ON v.ID = dv.IDVenta " +
                "WHERE YEAR(v.Fecha) = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            preparedStatement.setInt(1, anio);

            //double totalAnio = 0;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(!resultSet.isBeforeFirst()){
                    JOptionPane.showMessageDialog(null, "AÑO NO ENCONTRADO\nVERIFICA QUE EL AÑO SEA CORRECTO");
                }else{
                while (resultSet.next()) {
                    int idVenta = resultSet.getInt("ID");
                    int idVendedor = resultSet.getInt("IDVendedor");
                    Date fechaVenta = resultSet.getDate("Fecha");
                    String nombreVendedor = resultSet.getString("Nombres");
                    double totalVenta = resultSet.getDouble("Total");
                    double subtotalVenta = resultSet.getDouble("Subtotal");

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String fechaFormateada = dateFormat.format(fechaVenta);

                    System.out.println("ID Venta: " + idVenta);
                    System.out.println("ID Vendedor: " + idVendedor);
                    System.out.println("Fecha: " + fechaFormateada);
                    System.out.println("Nombre del Vendedor: " + nombreVendedor);
                    System.out.println("Subtotal Venta: " + subtotalVenta);
                    System.out.println("Total Venta: " + totalVenta);
                    System.out.println("--------------------------------------------");

                    totalAnio += totalVenta;

                    ResultadoVentaAnio resultado = new ResultadoVentaAnio(idVenta, idVendedor, fechaFormateada, nombreVendedor, totalVenta, subtotalVenta);
                    resultados.add(resultado);
                }
            }
        }
            System.out.println("Total Ventas del Año " + anio + ": " + totalAnio);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    Map<String, Object> resultadoMap = new HashMap<>();
                resultadoMap.put("resultados", resultados);
                resultadoMap.put("totalAnio", totalAnio);
            return resultadoMap;
    }
    
    public class ResultadoVentaAnio {
    private int idVenta;
    private int idVendedor;
    private String fechaVenta;
    private String nombreVendedor;
    private double totalVenta;
    private double subtotalVenta;

    public ResultadoVentaAnio(int idVenta, int idVendedor, String fechaVenta, String nombreVendedor, double totalVenta, double subtotalVenta) {
        this.idVenta = idVenta;
        this.idVendedor = idVendedor;
        this.fechaVenta = fechaVenta;
        this.nombreVendedor = nombreVendedor;
        this.totalVenta = totalVenta;
        this.subtotalVenta = subtotalVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdVendedor() {
        return idVendedor;
    }
    public String getNombreVendedor() {
            return nombreVendedor;
        }
    public String getFechaVenta() {
        return fechaVenta;
    }
    public double getTotalVenta() {
        return totalVenta;
    }

    public double getSubtotalVenta() {
        return subtotalVenta;
    }
  }
    
    
    
    
    //      ************               ****            ****        *****       *************          **********
    //      ************              ******           *****       *****       *************        **************
    //      ****     ***             ********          ******      *****       ****                ****         ****
    //      ****     ***            ****  ****         *******     *****       ****              ****            ****
    //      *************          ****    ****        **** ***    *****       ****   *********  ****            ****
    //      **************        **************       ****  ***   *****       ****   *********  ****            ****
    //      *****      ****      ****************      ****   ***  *****       ****        ****   ****          ****
    //      *****       ****    ****          ****     ****    *********       ****************     ************** 
    //      *****       ****   ****            ****    ****     ********       ****************      ***********
    
     
    
    
    public Map<String, Object> buscarPorRangoFechas(Date fechaInicio, Date fechaFin) {
        List<ResultadoVentaRango> resultados = new ArrayList<>();
    double totalPeriodo = 0;

    try (Connection connection = conDB.conectar()) {
        String consultaSQL = "SELECT v.ID, v.IDVendedor, v.Fecha, v.Total, ve.Nombres, dv.Subtotal " +
                            "FROM Ventas v " +
                            "INNER JOIN Vendedores ve ON v.IDVendedor = ve.ID " +
                            "INNER JOIN DetalleVentas dv ON v.ID = dv.IDVenta " +
                            "WHERE v.Fecha BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
            // Configurar las fechas en el PreparedStatement
            preparedStatement.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(fechaFin.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idVenta = resultSet.getInt("ID");
                    int idVendedor = resultSet.getInt("IDVendedor");
                    Date fechaVenta = resultSet.getDate("Fecha");
                    String nombreVendedor = resultSet.getString("Nombres");
                    double totalVenta = resultSet.getDouble("Total");
                    double subtotalVenta = resultSet.getDouble("Subtotal");

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String fechaFormateada = dateFormat.format(fechaVenta);

                    totalPeriodo += totalVenta;

                    ResultadoVentaRango resultado = new ResultadoVentaRango(idVenta, idVendedor, fechaFormateada, nombreVendedor, totalVenta, subtotalVenta);
                    resultados.add(resultado);
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar errores y devolver mensajes de error si es necesario
    }
    
    Map<String, Object> resultadoMap = new HashMap<>();
    resultadoMap.put("resultados", resultados);
    resultadoMap.put("totalPeriodo", totalPeriodo);
    return resultadoMap;
}
    
    
public class ResultadoVentaRango {
    private int idVenta;
    private int idVendedor;
    private String fechaVenta;
    private String nombreVendedor;
    private double totalVenta;
    private double subtotalVenta;

    public ResultadoVentaRango(int idVenta, int idVendedor, String fechaVenta, String nombreVendedor, double totalVenta, double subtotalVenta) {
        this.idVenta = idVenta;
        this.idVendedor = idVendedor;
        this.fechaVenta = fechaVenta;
        this.nombreVendedor = nombreVendedor;
        this.totalVenta = totalVenta;
        this.subtotalVenta = subtotalVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public double getSubtotalVenta() {
        return subtotalVenta;
    }
}







//	****      ****    ****   *******     ***********     **********
//	****      ****    ****  *********    ***********     ***********
//   **********   ****    ****  ****         ****            ****     ***
//   **********   ****    ****    ******     ********        ************
//      ****      ****    ****        ***    ********        ************
//      ****      ****    ****         ***   ****            ****     ****
//                 **********    ********    ***********     ****      ****
//                  ********     ******      ***********     ****      ****







    public void agregaVendedor(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String telefonoSinFormato, String contrasenia, String tipoUser) {
      
        // Consulta SQL para insertar un nuevo usuario en la tabla Vendedores
        String sql = "INSERT INTO Vendedores (Nombres, ApellidoPaterno, ApellidoMaterno, Correo, Telefono, Contrasena, TipoUsuario) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = conDB.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Configurar los parámetros de la consulta
            preparedStatement.setString(1, nombres);
            preparedStatement.setString(2, apellidoPaterno);
            preparedStatement.setString(3, apellidoMaterno);
            preparedStatement.setString(4, correo);
            preparedStatement.setString(5, telefonoSinFormato);
            preparedStatement.setString(6, contrasenia);
            preparedStatement.setString(7, tipoUser);

            // Ejecutar la consulta para insertar el nuevo usuario
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario agregado correctamente.");
                JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
            } else {
                System.out.println("No se pudo agregar el usuario.");
                JOptionPane.showMessageDialog(null, "¡¡¡NO SE PUDO AGREGAR EL USUARIO!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores y devolver mensajes de error si es necesario
        }
      
    }
    

  
        public void editaInfoDeVendedor(String idUsuario, String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String telefonoSinFormato, String contrasenia, String tipoUser) {
           // Consulta SQL para actualizar un usuario en la tabla Vendedores
        String sql = "UPDATE Vendedores SET Nombres=?, ApellidoPaterno=?, ApellidoMaterno=?, Correo=?, Telefono=?, Contrasena=?, TipoUsuario=? WHERE ID=?";

        try (Connection connection = conDB.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Configurar los parámetros de la consulta
            preparedStatement.setString(1, nombres);
            preparedStatement.setString(2, apellidoPaterno);
            preparedStatement.setString(3, apellidoMaterno);
            preparedStatement.setString(4, correo);
            preparedStatement.setString(5, telefonoSinFormato);
            preparedStatement.setString(6, contrasenia);
            preparedStatement.setString(7, tipoUser);
            preparedStatement.setString(8, idUsuario);

            // Ejecutar la consulta para insertar el nuevo usuario
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario actualizado correctamente.");
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
            } else {
                System.out.println("No se pudo actualizar el usuario.");
                JOptionPane.showMessageDialog(null, "¡¡¡NO SE PUDO ACTUALIZAR EL USUARIO!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores y devolver mensajes de error si es necesario
        }
        }

    
 public void eliminarVendedor(String idUsuario, String nuevoTipoUsuario) {
    // Consulta SQL para verificar si el vendedor tiene ventas asociadas
    String sqlVentas = "SELECT COUNT(*) FROM Ventas WHERE IDVendedor=?";
    boolean tieneVentas = false;
    
    try (Connection connection = conDB.conectar();
         PreparedStatement preparedStatement = connection.prepareStatement(sqlVentas)) {
        preparedStatement.setString(1, idUsuario);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            tieneVentas = (count > 0);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar errores y devolver mensajes de error si es necesario
    }
    
    // Si el vendedor tiene ventas, cambia su estado al nuevo tipo de usuario; de lo contrario, elimínalo
    if (tieneVentas) {
        // Cambiar el tipo de usuario
        String sql = "UPDATE Vendedores SET TipoUsuario=? WHERE ID=?";
    
        try (Connection connection = conDB.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Configurar los parámetros de la consulta
            preparedStatement.setString(1, nuevoTipoUsuario);
            preparedStatement.setString(2, idUsuario);
    
            // Ejecutar la consulta para actualizar el tipo de usuario
            int filasAfectadas = preparedStatement.executeUpdate();
    
            if (filasAfectadas > 0) {
                System.out.println("Tipo de usuario cambiado a " + nuevoTipoUsuario + " correctamente.");
                JOptionPane.showMessageDialog(null, "ESTE USUARIO SE CAMBIO A " + nuevoTipoUsuario + " PORQUE TIENE VENTAS REALIZADAS");
            } else {
                System.out.println("No se pudo cambiar el tipo de usuario.");
                JOptionPane.showMessageDialog(null, "¡¡¡NO SE PUDO CAMBIAR EL TIPO DE USUARIO A " + nuevoTipoUsuario.toUpperCase() + "!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores y devolver mensajes de error si es necesario
        }
    } else {
        // Si no tiene ventas asociadas, se elimina el vendedor
        String sqlEliminar = "DELETE FROM Vendedores WHERE ID=?";
    
        try (Connection connection = conDB.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlEliminar)) {
    
            preparedStatement.setString(1, idUsuario);
    
            int filasAfectadas = preparedStatement.executeUpdate();
    
            if (filasAfectadas > 0) {
                System.out.println("Usuario eliminado correctamente.");
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
            } else {
                System.out.println("No se pudo eliminar el usuario.");
                JOptionPane.showMessageDialog(null, "¡¡¡NO SE PUDO ELIMINAR EL USUARIO!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores y devolver mensajes de error si es necesario
        }
    }
    }
 
 
 
 
 
 public Map<String, Object> verTodosLosVendedores(){
      List<ResultadoVendedor> resultados = new ArrayList<>();
        double totalVendedores = 0;

        try (Connection connection = conDB.conectar()) {
            String consultaSQL = "SELECT ID, Nombres, ApellidoPaterno, ApellidoMaterno, Correo, Telefono, TipoUsuario, Contrasena " +
                                "FROM Vendedores";
            try (PreparedStatement preparedStatement = connection.prepareStatement(consultaSQL)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String nombres = resultSet.getString("Nombres");
                        String apellidoPaterno = resultSet.getString("ApellidoPaterno");
                        String apellidoMaterno = resultSet.getString("ApellidoMaterno");
                        String correo = resultSet.getString("Correo");
                        String telefono = resultSet.getString("Telefono");
                        String tipoUsuario = resultSet.getString("TipoUsuario");
                        String contrasena = resultSet.getString("Contrasena");

                        ResultadoVendedor resultado = new ResultadoVendedor(id, nombres, apellidoPaterno, apellidoMaterno, correo, telefono, tipoUsuario, contrasena);
                        resultados.add(resultado);
                        totalVendedores++;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores y devolver mensajes de error si es necesario
        }

        Map<String, Object> resultadoMap = new HashMap<>();
        resultadoMap.put("resultados", resultados);
        resultadoMap.put("totalVendedores", totalVendedores);
        return resultadoMap;
    }

    public class ResultadoVendedor {
        private int id;
        private String nombres;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private String correo;
        private String telefono;
        private String tipoUsuario;
        private String contrasena;

        public ResultadoVendedor(int id, String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String telefono, String tipoUsuario, String contrasena) {
            this.id = id;
            this.nombres = nombres;
            this.apellidoPaterno = apellidoPaterno;
            this.apellidoMaterno = apellidoMaterno;
            this.correo = correo;
            this.telefono = telefono;
            this.tipoUsuario = tipoUsuario;
            this.contrasena = contrasena;
        }

        // Métodos getters

        public int getId() {
            return id;
        }

        public String getNombres() {
            return nombres;
        }

        public String getApellidoPaterno() {
            return apellidoPaterno;
        }

        public String getApellidoMaterno() {
            return apellidoMaterno;
        }

        public String getCorreo() {
            return correo;
        }

        public String getTelefono() {
            return telefono;
        }

        public String getTipoUsuario() {
            return tipoUsuario;
        }

        public String getContrasena() {
            return contrasena;
        }
    }
 
}   