/*
CREATE TABLE dadtbp_umbrales_esta_amz
AS
SELECT v_cod_esta, v_cod_var, n_r1, n_r2, v_cod_flagdata FROM dadtbp_umbrales_esta;
 */
package com.senamhi.sis.repository.impl;

import com.senamhi.sis.connection.ConeccionDB;
import com.senamhi.sis.dto.UmbralesPorEstacion;
import com.senamhi.sis.repository.UmbralesPorEstacionRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UmbralesPorEstacionRepositoryImpl implements UmbralesPorEstacionRepository {

    private final ConeccionDB db;

    public UmbralesPorEstacionRepositoryImpl() {
        db = new ConeccionDB();
    }

    @Override
    public String insert(List<UmbralesPorEstacion> list) {
        String result = null;

        String sql = ""
                + "INSERT INTO dadtbp_umbrales_esta(v_cod_esta,v_cod_var,n_r1,n_r2,v_cod_flagdata) "
                + "VALUES (?,?,?,?,?)"
                + "";
        try (Connection cn = db.CnOracle();) {
            PreparedStatement ps = cn.prepareStatement(sql);

            cn.setAutoCommit(false);

            for (UmbralesPorEstacion dto : list) {
                ps.setString(1, dto.getCodEstacion());
                ps.setInt(2, dto.getCodVariable());
                try {
                    ps.setDouble(3, dto.getNr1());
                } catch (Exception e) {
                    ps.setNull(3, java.sql.Types.DOUBLE);
                }
                try {
                    ps.setDouble(4, dto.getNr2());
                } catch (Exception e) {
                    ps.setNull(4, java.sql.Types.DOUBLE);
                }
                try {
                    ps.setString(5, dto.getCodFlagData());
                } catch (Exception e) {
                    ps.setNull(5, java.sql.Types.VARCHAR);
                }
                ps.addBatch();
            }

            int[] ctos = ps.executeBatch();

            if (ctos.length == list.size()) {
                cn.commit();
                cn.close();
            } else {
                cn.rollback();
                cn.close();
                throw new SQLException("0 filas afectadas");
            }
        } catch (SQLException e) {
            result = e.getMessage();
        }
        return result;
    }

    @Override
    public String delete(UmbralesPorEstacion dto) {
        String result = null;

        String sql = "DELETE FROM dadtbp_umbrales_esta WHERE v_cod_esta = ? AND v_cod_var = ?";

        try (Connection cn = db.CnOracle();) {
            PreparedStatement ps = cn.prepareStatement(sql);

            cn.setAutoCommit(false);

            ps.setString(1, dto.getCodEstacion());
            ps.setInt(2, dto.getCodVariable());

            int ctos = ps.executeUpdate();

            if (ctos > 0) {
                cn.commit();
                cn.close();
            } else {
                cn.rollback();
                cn.close();
                throw new SQLException("0 filas afectadas");
            }
        } catch (SQLException e) {
            result = e.getMessage();
        }
        return result;
    }

    @Override
    public String update(List<UmbralesPorEstacion> list) {
        String result = null;

        String sql = ""
                + "UPDATE dadtbp_umbrales_esta "
                + "SET n_r1 = ?, n_r2 = ?, v_cod_flagdata = ? "
                + "WHERE v_cod_esta = ? AND v_cod_var = ?"
                + "";
        try (Connection cn = db.CnOracle();) {
            PreparedStatement ps = cn.prepareStatement(sql);

            cn.setAutoCommit(false);

            for (UmbralesPorEstacion dto : list) {
                ps.setDouble(1, dto.getNr1());
                ps.setDouble(2, dto.getNr2());
                ps.setString(3, dto.getCodFlagData());
                ps.setString(4, dto.getCodEstacion());
                ps.setInt(5, dto.getCodVariable());
                ps.addBatch();
            }

            int[] ctos = ps.executeBatch();

            if (ctos.length == list.size()) {
                cn.commit();
                cn.close();
            } else {
                cn.rollback();
                cn.close();
                throw new SQLException("0 filas afectadas");
            }
        } catch (SQLException e) {
            result = e.getMessage();
        }
        return result;
    }

    @Override
    public List<UmbralesPorEstacion> get(UmbralesPorEstacion dto) {
        List<UmbralesPorEstacion> list = null;
        String sql = ""
                + "SELECT v_cod_esta, v_cod_var, NVL(n_r1,-999999999) n_r1, NVL(n_r2,-999999999) n_r2, v_cod_flagdata "
                + "FROM dadtbp_umbrales_esta "
                + "WHERE v_cod_esta = ? AND v_cod_var = ? "
                + "ORDER BY v_cod_flagdata DESC NULLS LAST";

        try (Connection cn = db.CnOracle();
                PreparedStatement ps = cn.prepareStatement(sql);) {

            ps.setString(1, dto.getCodEstacion());
            ps.setInt(2, dto.getCodVariable());

            ResultSet rs = ps.executeQuery();

            list = new LinkedList<>();

            while (rs.next()) {
                UmbralesPorEstacion ue = new UmbralesPorEstacion();
                ue.setCodEstacion(rs.getString(1));
                ue.setCodVariable(rs.getInt(2));
                if (rs.getDouble(3) != -999999999) {
                    ue.setNr1(rs.getDouble(3));
                }
                if (rs.getDouble(4) != -999999999) {
                    ue.setNr2(rs.getDouble(4));
                }
                ue.setCodFlagData(rs.getString(5));
                list.add(ue);
            }
        } catch (SQLException e) {
            list = null;
        }
        return list;
    }

}
