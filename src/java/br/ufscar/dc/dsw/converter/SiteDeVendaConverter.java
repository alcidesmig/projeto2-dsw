package br.ufscar.dc.dsw.converter;

import br.ufscar.dc.dsw.dao.DAOSiteDeVenda;
import br.ufscar.dc.dsw.pojo.SiteDeVenda;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("SiteDeVendaConverter")
public class SiteDeVendaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Long id = Long.parseLong(string);
        DAOSiteDeVenda dao = new DAOSiteDeVenda();
        return dao.get(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        SiteDeVenda siteDeVenda = (SiteDeVenda) o;
        return String.valueOf(siteDeVenda.getId());
    }

}
