/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modeloDAO;

import com.config.Conexion;
import com.modelo.Carrito;
import com.modelo.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author MIGUEL
 */
public class CompraDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public int GenerarCompra(Compra compra) {
        int idcompras;
        String sql = "insert into compras(idCliente,idPago,FechaCompras,Monto,Estado)values(?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, compra.getCliente().getId());
            ps.setInt(2, compra.getIdpago());
            ps.setString(3, compra.getFecha());
            ps.setDouble(4, compra.getMonto());
            ps.setString(5, compra.getEstado());
            r = ps.executeUpdate();
            
            sql = "Select @@IDENTITY AS idCompras";
            rs =ps.executeQuery(sql);
            rs.next();
            idcompras = rs.getInt("idCompras");
            rs.close();

            for (Carrito detalle : compra.getDetallecompras()) {
                sql = "insert into detalle_compras(idProducto,idCompras,Cantidad,PrecioCompra)values(?,?,?,?)";
                ps = con.prepareStatement(sql);

                ps.setInt(1, detalle.getIdProducto());
                ps.setInt(2, idcompras);
                ps.setInt(3, detalle.getCantidad());
                ps.setDouble(4, detalle.getPrecioCompra());
                r = ps.executeUpdate();
                
                
            }

        } catch (Exception e) {
        }

        return r;
    }

}
