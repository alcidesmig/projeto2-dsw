package br.ufscar.dc.dsw.converter;

import br.ufscar.dc.dsw.dao.DAOSalaDeTeatro;
import br.ufscar.dc.dsw.pojo.SalaDeTeatro;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("SalaDeTeatroConverter")
public class SalaDeTeatroConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Long id = Long.parseLong(string);
        DAOSalaDeTeatro dao = new DAOSalaDeTeatro();
        return dao.get(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        SalaDeTeatro sala = (SalaDeTeatro) o;
        return String.valueOf(sala.getId());
    }
    
}
