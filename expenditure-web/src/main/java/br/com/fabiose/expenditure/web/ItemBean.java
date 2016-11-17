/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.expenditure.web;

/**
 *
 * @author fabioestrela
 */

import br.com.fabiose.expenditure.dao.ItemDao;
import br.com.fabiose.expenditure.dao.ItemTypeDao;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.ArrayList;
import br.com.fabiose.expenditure.model.ItemModel;
import br.com.fabiose.expenditure.model.ItemTypeModel;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class ItemBean {
    
    private ItemModel itemModel;
    private ItemModel itemModelEdit;
    private List<SelectItem> typeItems;
    private List<ItemModel> items;
    private List<ItemModel> selectedItems;
    
   
    @PostConstruct
    public void init() {
        itemModel = new ItemModel();
        itemModel.setItemType(new ItemTypeModel());  
    }

    public void save() {
        ItemDao itemDao = new ItemDao();
        try {
            itemDao.save(itemModel);
            items.add(itemModel);
            RequestContext.getCurrentInstance().update("formListItem:panelListItem");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info - ", "Item cadastrado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - ", e.getMessage()));
        }
    }
    
    public void update() {
        ItemDao itemDao = new ItemDao();
        try {
            itemDao.update(itemModelEdit);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info - ", "Item atualizado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - ", e.getMessage()));
        }
    }
        
    public void delete(){
        ItemDao itemDao = new ItemDao();
        for(ItemModel item: selectedItems){
            try {
                itemDao.delete(item);
                items.remove(item);
                RequestContext.getCurrentInstance().update("formListItem:panelListItem");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info - ", "Item deletado"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - ", e.getMessage()));
            }
        }
       
    }

    public void loadTypeItems() {    
        typeItems = new ArrayList<SelectItem>();     
        List<ItemTypeModel> listItemTypeModels = null;
        try{
            listItemTypeModels = new ItemTypeDao().getAll();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        for (ItemTypeModel itemTypeModel : listItemTypeModels) {
            typeItems.add(new SelectItem(itemTypeModel.getId(), itemTypeModel.getName()));
        }       
    }
    
    public void loadItems() {    
        items = new ArrayList<ItemModel>();
        try{
            items = new ItemDao().getAll();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public String goPageEdit(){
        return "editItem";
    }
  
    public List<SelectItem> getTypeItems() {
        return typeItems;
    }

    public void setTypeItems(List<SelectItem> typeItems) {
        this.typeItems = typeItems;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

    public List<ItemModel> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<ItemModel> selectedItems) {
        this.selectedItems = selectedItems;
    }  
    
     public ItemModel getItemModel() {
        return itemModel;
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }

    public ItemModel getItemModelEdit() {
        return itemModelEdit;
    }

    public void setItemModelEdit(ItemModel itemModelEdit) {
        this.itemModelEdit = itemModelEdit;
    }
}
