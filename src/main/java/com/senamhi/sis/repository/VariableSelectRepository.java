package com.senamhi.sis.repository;

import com.senamhi.sis.dto.VariableSelect;
import java.util.List;

public interface VariableSelectRepository {

    public List<VariableSelect> query();
    
    public VariableSelect get(Integer cod_var);
}
