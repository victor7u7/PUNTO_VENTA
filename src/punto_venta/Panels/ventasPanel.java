/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto_venta.Panels;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import constructor.constructor;
import constructor.constructor.ResultadoVentaAnio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import constructor.constructor.ResultadoVentaDia;
import constructor.constructor.ResultadoVentaMes;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;

/**
 *
 * @author israe
 */
public class ventasPanel extends javax.swing.JPanel {

    /**
     * Creates new form ventasPanel
     */
    
    
    private int selectedRow = -1;
    private int lastSelectedRow = -1;
    private HashSet<Integer> selectedRows = new HashSet<>();
    private class CustomCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
             Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Cambiar el color de fondo de la fila según el índice
        if (row % 2 == 0) {
            cell.setBackground(new Color(51, 153, 255));
        } else {
            cell.setBackground(Color.WHITE);
        }

        // Configurar bordes para todas las celdas
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));

        // Ajustar el ancho de la primera columna si es necesario
        if (column == 0) {
            table.getColumnModel().getColumn(column).setPreferredWidth(50);
            setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
        }

        if (isSelected) {
    cell.setBackground(new Color(144, 238, 144)); // Verde claro muy claro
    cell.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
} else {
    // Restaurar el color de fondo de la fila no seleccionada
    if (row % 2 == 0) {
        cell.setBackground(new Color(51, 153, 255));
    } else {
        cell.setBackground(Color.WHITE);
    }

    // Restaurar el color del texto a negro
    cell.setForeground(Color.BLACK);
        }

        return cell;
    }  
}
    
    public ventasPanel() {
//        try {
//            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        initComponents();
        initStyles();
        initContenido(); // Add this line to initialize the content
//        this.setLayout(new BorderLayout());
        setSize(bg.getSize());
         setSize(750, 750);
        setLocation(0,0);


        // Obtener el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tablaVentas.getModel();
        // Establecer el renderizador personalizado para la tabla
        tablaVentas.setDefaultRenderer(Object.class, new CustomCellRenderer());
       tablaVentas.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        // Obtener la fila seleccionada
        int selectedRow = tablaVentas.getSelectedRow();

        // Actualizar la variable de clase con la fila seleccionada
        this.selectedRow = selectedRow;

        // Repintar la tabla para reflejar el cambio en la selección
        tablaVentas.repaint();
    }
    });
    }
    
    
    
    private void initStyles() {
     
     txtDia.putClientProperty("JTextField.placeholderText", "Ingresa Día.");
     txtDia.setFont(new Font("SansSerif", Font.BOLD, 18));
     txtMes.putClientProperty("JTextField.placeholderText", "Ingresa Mes.");
     txtMes.setFont(new Font("SansSerif", Font.BOLD, 18));
     txtAnio.putClientProperty("JTextField.placeholderText", "Ingresa Año.");
     txtAnio.setFont(new Font("SansSerif", Font.BOLD, 18));
     labelDia.putClientProperty( "FlatLaf.style", "font: bold 22 regular.font" );
     labelDia.setForeground(Color.white);
     labelMes.putClientProperty( "FlatLaf.style", "font: bold 22 regular.font" );
     labelMes.setForeground(Color.white);
     labelAnio.putClientProperty( "FlatLaf.style", "font: bold 22 regular.font" );
     labelAnio.setForeground(Color.white);
     jLabelTotalDia.putClientProperty( "FlatLaf.style", "font: bold 22 regular.font" );
     jLabelTotalDia.setForeground(Color.white);
     jLabelTotalMes.putClientProperty( "FlatLaf.style", "font: bold 22 regular.font" );
     jLabelTotalMes.setForeground(Color.white);
     jLabelTotalAnio.putClientProperty( "FlatLaf.style", "font: bold 22 regular.font" );
     jLabelTotalAnio.setForeground(Color.white);
    }

    private void initContenido() {
        // Set layout manager and preferred size
        
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        btnDia = new javax.swing.JButton();
        btnAnio = new javax.swing.JButton();
        btnMes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jLabelTotalMes = new javax.swing.JLabel();
        jLabelTotalDia = new javax.swing.JLabel();
        jLabelTotalAnio = new javax.swing.JLabel();
        txtDia = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        labelAnio = new javax.swing.JLabel();
        labelMes = new javax.swing.JLabel();
        labelDia = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(750, 460));

        bg.setBackground(new java.awt.Color(0, 0, 0));

        btnDia.setBackground(new java.awt.Color(0, 102, 255));
        btnDia.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        btnDia.setForeground(new java.awt.Color(255, 255, 255));
        btnDia.setText("DIA");
        btnDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiaActionPerformed(evt);
            }
        });

        btnAnio.setBackground(new java.awt.Color(0, 102, 255));
        btnAnio.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        btnAnio.setForeground(new java.awt.Color(255, 255, 255));
        btnAnio.setText("AÑO");
        btnAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnioActionPerformed(evt);
            }
        });

        btnMes.setBackground(new java.awt.Color(0, 102, 255));
        btnMes.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        btnMes.setForeground(new java.awt.Color(255, 255, 255));
        btnMes.setText("MES");
        btnMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesActionPerformed(evt);
            }
        });

        tablaVentas.setBackground(new java.awt.Color(255, 255, 255));
        tablaVentas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaVentas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        tablaVentas.setForeground(new java.awt.Color(0, 0, 0));
        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FECHA", "VENDIDO POR", "SUB-TOTAL", "TOTAL"
            }
        ));
        tablaVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaVentas.setFillsViewportHeight(true);
        tablaVentas.setOpaque(false);
        tablaVentas.setRowSelectionAllowed(true);
        tablaVentas.setSelectionBackground(new java.awt.Color(102, 255, 102));
        tablaVentas.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tablaVentas.setShowHorizontalLines(false);
        tablaVentas.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tablaVentas);
        if (tablaVentas.getColumnModel().getColumnCount() > 0) {
            tablaVentas.getColumnModel().getColumn(0).setMinWidth(50);
            tablaVentas.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabelTotalMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTotalMes.setText("TOTAL MES: 10,000");

        jLabelTotalDia.setText("TOTAL DIA: 10,000");

        jLabelTotalAnio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalAnio.setText("TOTAL AÑO: 1,000,000");

        txtDia.setBackground(new java.awt.Color(255, 255, 255));
        txtDia.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtDia.setForeground(new java.awt.Color(0, 0, 0));
        txtDia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtMes.setBackground(new java.awt.Color(255, 255, 255));
        txtMes.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtMes.setForeground(new java.awt.Color(0, 0, 0));
        txtMes.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtAnio.setBackground(new java.awt.Color(255, 255, 255));
        txtAnio.setForeground(new java.awt.Color(0, 0, 0));
        txtAnio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelAnio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAnio.setText("AÑO");

        labelMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMes.setText("MES");

        labelDia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDia.setText("DIA");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMes)
                            .addComponent(labelMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAnio)
                            .addComponent(labelAnio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addComponent(btnMes, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDia, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addComponent(jLabelTotalDia, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTotalMes, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTotalAnio, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
                .addGap(279, 279, 279))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btnDia, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMes, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMes, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                            .addComponent(txtDia)
                            .addComponent(txtAnio))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalMes, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTotalAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTotalDia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiaActionPerformed
        int dia, mes, anio;
        constructor busquedaDia = new constructor();

        String txtdia = txtDia.getText();
        String txtmes = txtMes.getText();
        String txtanio = txtAnio.getText();

        if (txtdia.length() != 2 || txtmes.length() != 2 || txtanio.length() != 4) {
            JOptionPane.showMessageDialog(null, "Para buscar por día tienes que ingresarlo en este formato: \n EJEMPLO: 21-01-2024");
        } else {
            try {
                dia = Integer.parseInt(txtdia);
                mes = Integer.parseInt(txtmes);
                anio = Integer.parseInt(txtanio);

                
                
                // Llamar al método de búsqueda
                 Map<String, Object> resultadoMap = busquedaDia.buscarPorDiaMesAnio(dia, mes, anio);
                //List<ResultadoVentaDia> resultados = busquedaDia.buscarPorDiaMesAnio(dia, mes, anio);
                List<ResultadoVentaDia> resultados = (List<ResultadoVentaDia>) resultadoMap.get("resultados");
                double totalDia = (double) resultadoMap.get("totalDia");
                // Obtener el modelo de la tabla
                 DecimalFormat df = new DecimalFormat("#,###.##");
                 String totalDiaFormateado = df.format(totalDia);
                DefaultTableModel model = (DefaultTableModel) tablaVentas.getModel();

                // Limpiar el modelo actual
                model.setRowCount(0);

                // Obtener los resultados desde el constructor
                // Agregar filas con los resultados a la tabla
                for (ResultadoVentaDia resultado : resultados) {
                    Object[] fila = {
                        resultado.getIdVenta(),
                        resultado.getFechaVenta(),
                        resultado.getNombreVendedor(),
                        resultado.getSubtotalVenta(),
                        resultado.getTotalVenta()
                    };
                    model.addRow(fila);
                }
                 // Mostrar el total del día
            jLabelTotalDia.setText(String.valueOf("TOTAL DIA: "+totalDiaFormateado+"$"));
            jLabelTotalMes.setText("");
            jLabelTotalAnio.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Para buscar por DIA tienes que ingresarlo en este formato: \n EJEMPLO 21-01-2024");
            }
        }

    }//GEN-LAST:event_btnDiaActionPerformed

    private void btnMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesActionPerformed
          constructor busquedaMes = new constructor();
          int mes, anio;

          String txtdia = txtDia.getText();
          String txtmes = txtMes.getText();
          String txtanio = txtAnio.getText();

if (txtdia.length() != 0 || txtmes.length() != 2 || txtanio.length() != 4) {
    JOptionPane.showMessageDialog(null, "Para buscar por MES tienes que ingresarlo en este formato: \n EJEMPLO: 01-2024 DEJANDO EN BLANCO EL DIA");
} else {
    try {
        mes = Integer.parseInt(txtmes);
        anio = Integer.parseInt(txtanio);

        // Llamar al método de búsqueda por mes
        List<ResultadoVentaMes> resultados = busquedaMes.buscarPorMesYAnio(mes, anio);

        // Obtener el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tablaVentas.getModel();

        // Limpiar el modelo actual
        model.setRowCount(0);

        // Agregar filas con los resultados a la tabla
        for (ResultadoVentaMes resultado : resultados) {
            Object[] fila = {
                resultado.getIdVenta(),
                resultado.getFechaVenta(),
                resultado.getNombreVendedor(),
                resultado.getSubtotalVenta(),
                resultado.getTotalVenta()
            };
            model.addRow(fila);
        }
        //model.fireTableDataChanged();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Para buscar por MES tienes que ingresarlo en este formato: \n EJEMPLO: 01-2024 DEJANDO EN BLANCO EL DIA");
    }
}
    }//GEN-LAST:event_btnMesActionPerformed

    private void btnAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnioActionPerformed
       constructor busquedaAnio = new constructor();
    int anio;

    String txtdia = txtDia.getText();
    String txtmes = txtMes.getText();
    String txtanio = txtAnio.getText();

    if (txtdia.length() != 0 || txtmes.length() != 0 || txtanio.length() != 4) {
        JOptionPane.showMessageDialog(null, "Para buscar por AÑO tienes que ingresarlo en este formato: \n EJEMPLO: 2024 DEJANDO EN BLANCO EL DIA Y MES");
    } else {
        try {
            anio = Integer.parseInt(txtanio);

            // Llamar al método de búsqueda por año
            List<ResultadoVentaAnio> resultados = busquedaAnio.buscarPorAnio(anio);

            // Obtener el modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) tablaVentas.getModel();

            // Limpiar el modelo actual
            model.setRowCount(0);

            // Agregar filas con los resultados a la tabla
            for (ResultadoVentaAnio resultado : resultados) {
                Object[] fila = {
                    resultado.getIdVenta(),
                    resultado.getFechaVenta(),
                    resultado.getNombreVendedor(),
                    resultado.getSubtotalVenta(),
                    resultado.getTotalVenta()
                };
                model.addRow(fila);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Para buscar por AÑO tienes que ingresarlo en este formato: \n EJEMPLO: 2024 DEJANDO EN BLANCO EL DIA Y MES");
        }
    }
    }//GEN-LAST:event_btnAnioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnAnio;
    private javax.swing.JButton btnDia;
    private javax.swing.JButton btnMes;
    private javax.swing.JLabel jLabelTotalAnio;
    private javax.swing.JLabel jLabelTotalDia;
    private javax.swing.JLabel jLabelTotalMes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAnio;
    private javax.swing.JLabel labelDia;
    private javax.swing.JLabel labelMes;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtMes;
    // End of variables declaration//GEN-END:variables
}
