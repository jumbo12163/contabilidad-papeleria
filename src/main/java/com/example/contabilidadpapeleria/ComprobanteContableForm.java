/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.contabilidadpapeleria;

/**
 *
 * @author Bryan
 */
public class ComprobanteContableForm extends javax.swing.JFrame {

    /**
     * Creates new form ComprobanteContableForm
     */
public ComprobanteContableForm() {
   
    initComponents();
    txtIdComprobante.setText("");

    corregirModeloTabla();

    habilitarEdicionTabla();
    tblDetalles.addMouseListener(
    new java.awt.event.MouseAdapter() {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {

            int fila = tblDetalles.rowAtPoint(evt.getPoint());
            int columna = tblDetalles.columnAtPoint(evt.getPoint());

            if (fila >= 0 && columna == 3) {
                eliminarDetalleFila(fila);
            }
        }
    }
);

    txtFecha.setText(java.time.LocalDate.now().toString());
    txtObservaciones.setText("");

    setLocationRelativeTo(null);
}

private void configurarTabla() {
    javax.swing.JComboBox<String> combo =
        new javax.swing.JComboBox<>();

    String sql =
        "SELECT CODIGO, NOMBRE "
        + "FROM CUENTA_CONTABLE "
        + "ORDER BY CODIGO";

    try (
        java.sql.Connection conexion =
            ConexionOracle.conectar();
        java.sql.PreparedStatement sentencia =
            conexion.prepareStatement(sql);
        java.sql.ResultSet resultado =
            sentencia.executeQuery()
    ) {
        while (resultado.next()) {
            String codigo = resultado.getString("CODIGO");
            String nombre = resultado.getString("NOMBRE");

            combo.addItem(codigo + " - " + nombre);
        }

        tblDetalles.getColumnModel()
            .getColumn(0)
            .setCellEditor(
                new javax.swing.DefaultCellEditor(combo)
            );

    
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error al cargar las cuentas: " + e.getMessage()
            );
        } // ESTA LLAVE ES LA QUE FALTA

        tblDetalles.setDefaultEditor(
            Object.class,
            new javax.swing.DefaultCellEditor(
                new javax.swing.JTextField()
            )
        );
    }


private void habilitarEdicionTabla() {
    javax.swing.JComboBox<String> combo =
        new javax.swing.JComboBox<>();

    String sql =
        "SELECT CODIGO, NOMBRE "
        + "FROM CUENTA_CONTABLE "
        + "ORDER BY CODIGO";

    try (
        java.sql.Connection conexion =
            ConexionOracle.conectar();
        java.sql.PreparedStatement sentencia =
            conexion.prepareStatement(sql);
        java.sql.ResultSet resultado =
            sentencia.executeQuery()
    ) {
        while (resultado.next()) {
            String codigo = resultado.getString("CODIGO");
            String nombre = resultado.getString("NOMBRE");

            combo.addItem(codigo + " - " + nombre);
        }

        tblDetalles.getColumnModel()
            .getColumn(0)
            .setCellEditor(
                new javax.swing.DefaultCellEditor(combo)
            );
     
tblDetalles.getColumnModel()
    .getColumn(1)
    .setCellEditor(
        new javax.swing.DefaultCellEditor(
            new javax.swing.JTextField()
        )
    );

tblDetalles.getColumnModel()
    .getColumn(2)
    .setCellEditor(
        new javax.swing.DefaultCellEditor(
            new javax.swing.JTextField()
        )
    );

tblDetalles.setSurrendersFocusOnKeystroke(true);
tblDetalles.putClientProperty(
    "terminateEditOnFocusLost", Boolean.TRUE
);
   
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Error al cargar las cuentas: " + e.getMessage()
        );
    }
}

private void corregirModeloTabla() {
    javax.swing.table.DefaultTableModel modelo =
        new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Cuenta", "Debe", "Haber", "Eliminar"
            }
        ) {
            @Override
            public boolean isCellEditable(
                    int fila, int columna) {
                return columna >= 0 && columna <= 2;
            }
        };

    tblDetalles.setModel(modelo);
}
private void eliminarDetalleFila(int fila) {

    if (fila < 0 || fila >= tblDetalles.getRowCount()) {
        return;
    }

    int respuesta = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "¿Desea eliminar este detalle?",
        "Confirmar eliminación",
        javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (respuesta == javax.swing.JOptionPane.YES_OPTION) {

        javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel) tblDetalles.getModel();

        modelo.removeRow(fila);
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtObservaciones = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        btnAgregarDetalle = new javax.swing.JButton();
        lblIdComprobante = new javax.swing.JLabel();
        txtIdComprobante = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Comprobantes Contables");

        jTextField1.setText("Registro de Comprobantes Contables.");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha:");

        jLabel3.setText("Observaciones:");

        jLabel4.setText("Detalle contable:");

        txtFecha.setText("jTextField2");

        btnGuardar.setText("Guardar comprobante.");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        tblDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cuenta", "Debe", "Haber", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDetalles);

        btnAgregarDetalle.setText("Agregar detalle");
        btnAgregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDetalleActionPerformed(evt);
            }
        });

        lblIdComprobante.setText("ID Comprobante");

        txtIdComprobante.setToolTipText("");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(lblIdComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(txtFecha)
                            .addComponent(txtIdComprobante)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(btnGuardar)
                        .addGap(46, 46, 46)
                        .addComponent(btnAgregarDetalle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnModificar)
                        .addGap(41, 41, 41)
                        .addComponent(btnEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(232, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdComprobante)
                    .addComponent(txtIdComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnAgregarDetalle))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        // Finalizar la edición de la tabla
        if (tblDetalles.isEditing()) {
            tblDetalles.getCellEditor().stopCellEditing();
        }

        double totalDebe = 0;
        double totalHaber = 0;
        int filasValidas = 0;

        try {
            // Validar los valores de la tabla
            for (int i = 0; i < tblDetalles.getRowCount(); i++) {

                Object cuentaObj = tblDetalles.getValueAt(i, 0);
                Object debeObj = tblDetalles.getValueAt(i, 1);
                Object haberObj = tblDetalles.getValueAt(i, 2);

                String cuenta = cuentaObj == null
                        ? "" : cuentaObj.toString().trim();

                String debeTexto = debeObj == null
                        ? "0" : debeObj.toString().trim();

                String haberTexto = haberObj == null
                        ? "0" : haberObj.toString().trim();

                double debe = debeTexto.isEmpty()
                        ? 0 : Double.parseDouble(
                                debeTexto.replace(",", "."));

                double haber = haberTexto.isEmpty()
                        ? 0 : Double.parseDouble(
                                haberTexto.replace(",", "."));

                if (cuenta.isEmpty() && debe == 0 && haber == 0) {
                    continue;
                }

                if (cuenta.isEmpty()) {
                    throw new Exception(
                            "Seleccione una cuenta en la fila " + (i + 1));
                }

                if (debe < 0 || haber < 0
                        || (debe > 0 && haber > 0)
                        || (debe == 0 && haber == 0)) {
                    throw new Exception(
                            "Revise el Debe y el Haber en la fila "
                            + (i + 1));
                }

                totalDebe += debe;
                totalHaber += haber;
                filasValidas++;
            }

            if (filasValidas < 2) {
                throw new Exception(
                        "El comprobante necesita al menos dos movimientos.");
            }

            if (Math.abs(totalDebe - totalHaber) > 0.009) {
                throw new Exception(
                        "El Debe y el Haber no coinciden.\n"
                        + "Debe: " + totalDebe
                        + "\nHaber: " + totalHaber);
            }

            String fechaTexto = txtFecha.getText().trim();
            java.sql.Date fecha;

            if (fechaTexto.isEmpty()
                    || fechaTexto.equals("jTextField2")) {
                fecha = new java.sql.Date(System.currentTimeMillis());
            } else {
                fecha = java.sql.Date.valueOf(
                        java.time.LocalDate.parse(fechaTexto));
            }

            String observaciones = txtObservaciones.getText().trim();

            try (java.sql.Connection conexion =
                    ConexionOracle.conectar()) {

                conexion.setAutoCommit(false);

                try {
                    // Crear la cabecera del comprobante
                    String sqlCabecera =
                            "BEGIN INSERT INTO COMPROBANTE_CONTABLE "
                            + "(FECHA, OBSERVACIONES, ESTADO) "
                            + "VALUES (?, ?, 'CONTABILIZADO') "
                            + "RETURNING ID_COMPROBANTE INTO ?; END;";

                    int idComprobante;

                    try (java.sql.CallableStatement cs =
                            conexion.prepareCall(sqlCabecera)) {

                        cs.setDate(1, fecha);
                        cs.setString(2, observaciones);
                        cs.registerOutParameter(
                                3, java.sql.Types.NUMERIC);

                        cs.execute();
                        idComprobante = cs.getInt(3);
                    }

                    // Guardar los movimientos
                    String sqlCuenta =
                            "SELECT ID_CUENTA FROM CUENTA_CONTABLE "
                            + "WHERE CODIGO = ?";

                    String sqlDetalle =
                            "INSERT INTO DETALLE_CONTABLE "
                            + "(ID_COMPROBANTE, ID_CUENTA, DEBE, HABER) "
                            + "VALUES (?, ?, ?, ?)";

                    try (java.sql.PreparedStatement buscar =
                                conexion.prepareStatement(sqlCuenta);
                         java.sql.PreparedStatement insertar =
                                conexion.prepareStatement(sqlDetalle)) {

                        for (int i = 0;
                                i < tblDetalles.getRowCount(); i++) {

                            Object cuentaObj =
                                    tblDetalles.getValueAt(i, 0);

                            Object debeObj =
                                    tblDetalles.getValueAt(i, 1);

                            Object haberObj =
                                    tblDetalles.getValueAt(i, 2);

                            String cuenta = cuentaObj == null
                                    ? "" : cuentaObj.toString().trim();

                            String debeTexto = debeObj == null
                                    ? "0" : debeObj.toString().trim();

                            String haberTexto = haberObj == null
                                    ? "0" : haberObj.toString().trim();

                            double debe = debeTexto.isEmpty()
                                    ? 0 : Double.parseDouble(
                                            debeTexto.replace(",", "."));

                            double haber = haberTexto.isEmpty()
                                    ? 0 : Double.parseDouble(
                                            haberTexto.replace(",", "."));

                            if (cuenta.isEmpty()
                                    && debe == 0 && haber == 0) {
                                continue;
                            }

                            String codigo = cuenta.split(" - ", 2)[0];

                            buscar.setString(1, codigo);

                            int idCuenta;

                            try (java.sql.ResultSet rs =
                                    buscar.executeQuery()) {

                                if (!rs.next()) {
                                    throw new Exception(
                                            "No existe la cuenta: " + cuenta);
                                }

                                idCuenta = rs.getInt("ID_CUENTA");
                            }

                            insertar.setInt(1, idComprobante);
                            insertar.setInt(2, idCuenta);
                            insertar.setDouble(3, debe);
                            insertar.setDouble(4, haber);
                            insertar.executeUpdate();
                        }
                    }

                    conexion.commit();

                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Comprobante guardado correctamente.\n"
                            + "Número: " + idComprobante
                            + "\nTotal Debe: " + totalDebe
                            + "\nTotal Haber: " + totalHaber);

                } catch (Exception e) {
                    conexion.rollback();
                    throw e;
                } finally {
                    conexion.setAutoCommit(true);
                }
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se pudo guardar el comprobante:\n"
                    + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDetalleActionPerformed

    javax.swing.table.DefaultTableModel modelo =
        (javax.swing.table.DefaultTableModel) tblDetalles.getModel();

    modelo.addRow(new Object[] {
        "",
        0.00,
        0.00,
        "Eliminar"
    });
    }//GEN-LAST:event_btnAgregarDetalleActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    String idTexto = txtIdComprobante.getText().trim();

    if (idTexto.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Ingrese el ID del comprobante.");
        return;
    }

    try {

        int id = Integer.parseInt(idTexto);

        String sql =
            "SELECT ID_COMPROBANTE, FECHA, OBSERVACIONES "
            + "FROM COMPROBANTE_CONTABLE "
            + "WHERE ID_COMPROBANTE = ?";

        try (
            java.sql.Connection conexion = ConexionOracle.conectar();
            java.sql.PreparedStatement ps =
                conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (java.sql.ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    txtIdComprobante.setText(
                        String.valueOf(rs.getInt("ID_COMPROBANTE"))
                    );

                    txtFecha.setText(
                        rs.getDate("FECHA").toLocalDate().toString()
                    );

                    txtObservaciones.setText(
                        rs.getString("OBSERVACIONES") == null
                        ? "" : rs.getString("OBSERVACIONES")
                    );

                    cargarDetalles(id);

                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Comprobante encontrado correctamente."
                    );

                } else {

                    javax.swing.JOptionPane.showMessageDialog(
                        this, "No existe el comprobante: " + id
                    );
                }
            }
        }

    } catch (NumberFormatException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this, "El ID debe ser numérico.");

    } catch (Exception e) {

        javax.swing.JOptionPane.showMessageDialog(
            this, "Error al buscar: " + e.getMessage());
    }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

String idTexto = txtIdComprobante.getText().trim();

    if (idTexto.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Primero busque un comprobante.");
        return;
    }

    try {

        idTexto = idTexto.replaceAll("[^0-9]", "");

        int id = Integer.parseInt(idTexto);

        java.time.LocalDate fechaLocal =
            java.time.LocalDate.parse(txtFecha.getText().trim());

        java.sql.Date fecha = java.sql.Date.valueOf(fechaLocal);

        String observaciones = txtObservaciones.getText().trim();

        try (java.sql.Connection conexion = ConexionOracle.conectar()) {

            conexion.setAutoCommit(false);

            try {

                String comprobar =
                    "SELECT ID_COMPROBANTE FROM COMPROBANTE_CONTABLE "
                    + "WHERE ID_COMPROBANTE = ? FOR UPDATE";

                try (java.sql.PreparedStatement ps =
                        conexion.prepareStatement(comprobar)) {

                    ps.setInt(1, id);

                    try (java.sql.ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new Exception(
                                "El comprobante no existe.");
                        }
                    }
                }

                String actualizar =
                    "UPDATE COMPROBANTE_CONTABLE "
                    + "SET FECHA = ?, OBSERVACIONES = ? "
                    + "WHERE ID_COMPROBANTE = ?";

                try (java.sql.PreparedStatement ps =
                        conexion.prepareStatement(actualizar)) {

                    ps.setDate(1, fecha);
                    ps.setString(2, observaciones);
                    ps.setInt(3, id);
                    ps.executeUpdate();
                }

                String borrarDetalles =
                    "DELETE FROM DETALLE_CONTABLE "
                    + "WHERE ID_COMPROBANTE = ?";

                try (java.sql.PreparedStatement ps =
                        conexion.prepareStatement(borrarDetalles)) {

                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                guardarDetallesEnOracle(conexion, id);

                conexion.commit();

                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Comprobante modificado correctamente."
                );

            } catch (Exception e) {

                conexion.rollback();
                throw e;

            } finally {

                conexion.setAutoCommit(true);
            }
        }

    } catch (Exception e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "No se pudo modificar el comprobante:\n" + e.getMessage(),
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

String idTexto = txtIdComprobante.getText().trim();

    if (idTexto.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Primero busque el comprobante que desea eliminar.");
        return;
    }

    int respuesta = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "¿Está seguro de eliminar el comprobante "
        + idTexto + " y todos sus detalles?",
        "Confirmar eliminación",
        javax.swing.JOptionPane.YES_NO_OPTION,
        javax.swing.JOptionPane.WARNING_MESSAGE
    );

    if (respuesta != javax.swing.JOptionPane.YES_OPTION) {
        return;
    }


       try {

    idTexto = idTexto.replaceAll("[^0-9]", "");

    if (idTexto.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Ingrese un ID válido.");
        return;
    }

    int id = Integer.parseInt(idTexto);

        try (java.sql.Connection conexion = ConexionOracle.conectar()) {

            conexion.setAutoCommit(false);

            try {

                String borrarDetalles =
                    "DELETE FROM DETALLE_CONTABLE "
                    + "WHERE ID_COMPROBANTE = ?";

                try (java.sql.PreparedStatement ps =
                        conexion.prepareStatement(borrarDetalles)) {

                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                String borrarCabecera =
                    "DELETE FROM COMPROBANTE_CONTABLE "
                    + "WHERE ID_COMPROBANTE = ?";

                int filas;

                try (java.sql.PreparedStatement ps =
                        conexion.prepareStatement(borrarCabecera)) {

                    ps.setInt(1, id);
                    filas = ps.executeUpdate();
                }

                if (filas == 0) {
                    throw new Exception(
                        "No se encontró el comprobante.");
                }

                conexion.commit();

                limpiarFormulario();

                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Comprobante eliminado correctamente."
                );

            } catch (Exception e) {

                conexion.rollback();
                throw e;

            } finally {

                conexion.setAutoCommit(true);
            }
        }

    } catch (Exception e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Error al eliminar: " + e.getMessage(),
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }

    }//GEN-LAST:event_btnEliminarActionPerformed


private void cargarDetalles(int idComprobante)
        throws java.sql.SQLException {

    javax.swing.table.DefaultTableModel modelo =
        (javax.swing.table.DefaultTableModel) tblDetalles.getModel();

    modelo.setRowCount(0);

    String sql =
        "SELECT C.CODIGO, C.NOMBRE, D.DEBE, D.HABER "
        + "FROM DETALLE_CONTABLE D "
        + "JOIN CUENTA_CONTABLE C "
        + "ON C.ID_CUENTA = D.ID_CUENTA "
        + "WHERE D.ID_COMPROBANTE = ? "
        + "ORDER BY D.ID_DETALLE";

    try (
        java.sql.Connection conexion = ConexionOracle.conectar();
        java.sql.PreparedStatement ps =
            conexion.prepareStatement(sql)
    ) {

        ps.setInt(1, idComprobante);

        try (java.sql.ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String cuenta =
                    rs.getString("CODIGO") + " - "
                    + rs.getString("NOMBRE");

                modelo.addRow(new Object[] {
                    cuenta,
                    rs.getBigDecimal("DEBE"),
                    rs.getBigDecimal("HABER"),
                    "Eliminar"
                });
            }
        }
    }
}

private void limpiarFormulario() {

    lblIdComprobante.setText("");
    txtFecha.setText(java.time.LocalDate.now().toString());
    txtObservaciones.setText("");

    javax.swing.table.DefaultTableModel modelo =
        (javax.swing.table.DefaultTableModel)
        tblDetalles.getModel();

    modelo.setRowCount(0);

    tblDetalles.clearSelection();
}

private void guardarDetallesEnOracle(
        java.sql.Connection conexion,
        int idComprobante) throws Exception {

    if (tblDetalles.isEditing()) {
        tblDetalles.getCellEditor().stopCellEditing();
    }

    double totalDebe = 0;
    double totalHaber = 0;
    int cantidad = 0;

    String sqlBuscar =
        "SELECT ID_CUENTA FROM CUENTA_CONTABLE WHERE CODIGO = ?";

    String sqlInsertar =
        "INSERT INTO DETALLE_CONTABLE "
        + "(ID_COMPROBANTE, ID_CUENTA, DEBE, HABER) "
        + "VALUES (?, ?, ?, ?)";

    try (
        java.sql.PreparedStatement buscar =
            conexion.prepareStatement(sqlBuscar);
        java.sql.PreparedStatement insertar =
            conexion.prepareStatement(sqlInsertar)
    ) {

        for (int i = 0; i < tblDetalles.getRowCount(); i++) {

            String cuenta = String.valueOf(
                tblDetalles.getValueAt(i, 0)).trim();

            String debeTexto = String.valueOf(
                tblDetalles.getValueAt(i, 1)).trim();

            String haberTexto = String.valueOf(
                tblDetalles.getValueAt(i, 2)).trim();

            if (cuenta.isEmpty() || cuenta.equals("null")) {
                throw new Exception(
                    "Seleccione una cuenta en la fila " + (i + 1));
            }

            double debe = Double.parseDouble(
                debeTexto.replace(",", "."));

            double haber = Double.parseDouble(
                haberTexto.replace(",", "."));

            if (!Double.isFinite(debe) || !Double.isFinite(haber)
                    || debe < 0 || haber < 0
                    || (debe > 0 && haber > 0)
                    || (debe == 0 && haber == 0)) {

                throw new Exception(
                    "Valores incorrectos en la fila " + (i + 1));
            }

            String codigo = cuenta.split(" - ", 2)[0].trim();

            buscar.setString(1, codigo);

            int idCuenta;

            try (java.sql.ResultSet rs = buscar.executeQuery()) {

                if (!rs.next()) {
                    throw new Exception(
                        "No se encontró la cuenta " + cuenta);
                }

                idCuenta = rs.getInt("ID_CUENTA");
            }

            insertar.setInt(1, idComprobante);
            insertar.setInt(2, idCuenta);
            insertar.setBigDecimal(3,
                java.math.BigDecimal.valueOf(debe));
            insertar.setBigDecimal(4,
                java.math.BigDecimal.valueOf(haber));

            insertar.executeUpdate();

            totalDebe += debe;
            totalHaber += haber;
            cantidad++;
        }
    }

    if (cantidad < 2) {
        throw new Exception(
            "El comprobante necesita al menos dos detalles.");
    }

    if (Math.abs(totalDebe - totalHaber) > 0.009) {
        throw new Exception(
            "El comprobante no está cuadrado. "
            + "Debe: " + totalDebe
            + " Haber: " + totalHaber);
    }
}


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComprobanteContableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComprobanteContableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComprobanteContableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComprobanteContableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComprobanteContableForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarDetalle;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblIdComprobante;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdComprobante;
    private javax.swing.JTextField txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
