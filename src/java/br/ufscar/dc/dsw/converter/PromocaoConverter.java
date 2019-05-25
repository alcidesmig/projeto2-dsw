package br.ufscar.dc.dsw.converter;

import br.ufscar.dc.dsw.dao.DAOPromocao;
import br.ufscar.dc.dsw.pojo.Promocao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("PromocaoConverter")
public class PromocaoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        long id = Long.parseLong(string);
        DAOPromocao dao = new DAOPromocao();
        return dao.get(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Promocao prom = (Promocao) o;
        return String.valueOf(prom.getId_promocao());
    }
    
}
