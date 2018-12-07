package com.senamhi.sis.repository;

import com.senamhi.sis.dto.UmbralesPorEstacion;
import java.util.List;

public interface UmbralesPorEstacionRepository {

    public String insert(List<UmbralesPorEstacion> list);

    public String delete(UmbralesPorEstacion dto);
    
    public String update(List<UmbralesPorEstacion> list);
    
    public List<UmbralesPorEstacion> get(UmbralesPorEstacion dto);
}
