/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.expenditure.dto;

import java.io.Serializable;

/**
 *
 * @author fabioestrela
 */
public class ExpenditureDto implements Serializable{
   
    private Double value;
    private String name;
    
    public ExpenditureDto(Double value, String name){
        this.value = value;
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
