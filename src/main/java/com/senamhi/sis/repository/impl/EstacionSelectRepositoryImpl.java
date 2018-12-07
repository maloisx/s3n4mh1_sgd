package com.senamhi.sis.repository.impl;

import com.senamhi.sis.connection.ConeccionDB;
import com.senamhi.sis.dto.EstacionSelect;
import com.senamhi.sis.repository.EstacionSelectRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EstacionSelectRepositoryImpl implements EstacionSelectRepository {

    private final ConeccionDB db;

    public EstacionSelectRepositoryImpl() {
        db = new ConeccionDB();
    }

    @Override
    public List<EstacionSelect> query() {
        List<EstacionSelect> list = null;
        String sql = ""
                + "SELECT * FROM("
                + "SELECT V_COD_ESTA, V_NOM_ESTA FROM SEMAP_ESTA "
                + "WHERE "
                + "V_COD_COND='F' AND "
                + "V_SUB_ESTA='A' AND "
                + "V_COD_TIPO IN ('M','M1','M2') AND "
                + "V_COD_ENTI IN ('ANA','GCAJ','GCUS','GICA','GLAC','MACH','MEM','PRE2','PSI1','SEN','YANA') AND "
                + "V_CLAS_ESTA IN ('AMB','CAM','S+M','SIA','SUT','VAI') "
                + "UNION ALL "
                + "SELECT V_COD_ESTA, V_NOM_ESTA FROM SEMAP_ESTA "
                + "WHERE "
                + "V_COD_COND='F' AND "
                + "V_SUB_ESTA='A' AND "
                + "V_COD_TIPO='H' AND "
                + "V_COD_ENTI IN ('ANA','GCAJ','GCUS','GICA','GLAC','MACH','MEM','PRE2','PSI1','SEN' ,'YANA' ) AND "
                + "V_CLAS_ESTA IN ('AMB','CAM','S+M','SIA','SUT','VAI')"
                + ") "
                + "ORDER BY V_NOM_ESTA"
                + "";

        try (Connection cn = db.CnOracle();
                PreparedStatement ps = cn.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            list = new LinkedList<>();
            while (rs.next()) {
                list.add(new EstacionSelect(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            list = null;
        }
        return list;
    }

}
