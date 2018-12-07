package com.senamhi.sis.repository.impl;

import com.senamhi.sis.connection.ConeccionDB;
import com.senamhi.sis.dto.VariableSelect;
import com.senamhi.sis.repository.VariableSelectRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class VariableSelectRepositoryImpl implements VariableSelectRepository {

    private final ConeccionDB db;

    public VariableSelectRepositoryImpl() {
        db = new ConeccionDB();
    }

    @Override
    public List<VariableSelect> query() {
        List<VariableSelect> list = null;
        String sql = "SELECT v_cod_var, v_nom_var FROM dadtbp_variable";

        try (Connection cn = db.CnOracle();
                PreparedStatement ps = cn.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            list = new LinkedList<>();
            while (rs.next()) {
                list.add(new VariableSelect(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            list = null;
        }
        return list;
    }

    @Override
    public VariableSelect get(Integer cod_var) {
        VariableSelect dto = null;
        String sql = "SELECT v_cod_var, v_nom_var FROM dadtbp_variable WHERE v_cod_var = ?";

        try (Connection cn = db.CnOracle();) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, cod_var);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                dto = new VariableSelect(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
        }
        return dto;
    }

}
