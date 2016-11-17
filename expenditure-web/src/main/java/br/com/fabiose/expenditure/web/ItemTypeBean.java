/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.expenditure.web;

import br.com.fabiose.expenditure.dao.ItemDao;
import br.com.fabiose.expenditure.dao.ItemTypeDao;
import br.com.fabiose.expenditure.model.ItemTypeModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author fabioestrela
 */
@ManagedBean
@SessionScoped
public class ItemTypeBean implements Serializable {

    private ItemTypeModel itemTypeModel;
    private List<ItemTypeModel> itemsType;
    private List<ItemTypeModel> selectedItemTypes;

    @PostConstruct
    public void init() {
        itemTypeModel = new ItemTypeModel();
    }

    public void save() {
        ItemTypeDao itemTypeDao = new ItemTypeDao();
        try {
            itemTypeDao.save(itemTypeModel);
            itemsType.add(itemTypeModel);
            RequestContext.getCurrentInstance().update("formListItem:panelListItem");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info - ", "Categoria cadastrada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - ", e.getMessage()));
        }
    }

    public void update(ItemTypeModel _iItemTypeModel) {
        ItemTypeDao itemTypeDao = new ItemTypeDao();
        try {
            itemTypeDao.update(_iItemTypeModel);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info - ", "Categoria atualizada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - ", e.getMessage()));
        }
    }

    public void delete() {
        ItemTypeDao itemTypeDao = new ItemTypeDao();
        for (ItemTypeModel itemType : selectedItemTypes) {
            try {
                itemTypeDao.delete(itemType);
                itemsType.remove(itemType);
                RequestContext.getCurrentInstance().update("formListItem:panelListItem");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info - ", "Categoria deletada"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - ", e.getMessage()));
            }
        }
    }

    public void loadItemTypes() {
        itemsType = new ArrayList<ItemTypeModel>();
        try {
            itemsType = new ItemTypeDao().getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onRowEdit(RowEditEvent event) {
       ItemTypeModel _itemTypeModel = (ItemTypeModel) event.getObject();
       update(_itemTypeModel);
    }
    
    public ItemTypeModel getItemTypeModel() {
        return itemTypeModel;
    }

    public void setItemTypeModel(ItemTypeModel itemTypeModel) {
        this.itemTypeModel = itemTypeModel;
    }

    public List<ItemTypeModel> getItemsType() {
        return itemsType;
    }

    public void setItemsType(List<ItemTypeModel> itemsType) {
        this.itemsType = itemsType;
    }

    public List<ItemTypeModel> getSelectedItemTypes() {
        return selectedItemTypes;
    }

    public void setSelectedItemTypes(List<ItemTypeModel> selectedItemTypes) {
        this.selectedItemTypes = selectedItemTypes;
    }
    
    
}
