/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.expenditure.web;

import br.com.fabiose.expenditure.dao.ExpenditureDao;
import br.com.fabiose.expenditure.dto.ExpenditureDto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author fabioestrela
 */
@ManagedBean
@SessionScoped
public class DashboardBean implements Serializable {

    private PieChartModel expenditureDaily;
    private PieChartModel expenditureMonthly;
    private PieChartModel expenditureYearly;
         
    
    public void execute() {     
        createExpenditureDaily();
        createExpenditureMonthly();
        createExpenditureYearly();    
    } 
    
    private void createExpenditureDaily() {
        expenditureDaily = new PieChartModel();
        
        List<ExpenditureDto> listExpenditureByDay = null;

        try {
            listExpenditureByDay = new ExpenditureDao().getExpenditureByDay(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        for (ExpenditureDto eByDay : listExpenditureByDay) {
            expenditureDaily.set(eByDay.getName(), eByDay.getValue());
        }
        
        expenditureDaily.setTitle("Despesa Diária");
        expenditureDaily.setLegendPosition("w");
        
        setExpenditureDaily(expenditureDaily);
    }
    
     private void createExpenditureMonthly() {
        expenditureMonthly = new PieChartModel();

        List<ExpenditureDto> listExpenditureByMonth = null;

        try {
            listExpenditureByMonth = new ExpenditureDao().getExpenditureByMonth(new Date());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (ExpenditureDto eByMonth : listExpenditureByMonth) {
            expenditureMonthly.set(eByMonth.getName(), eByMonth.getValue());
        }

        expenditureMonthly.setTitle("Despesa Mensal");
        expenditureMonthly.setLegendPosition("w");
    }

    private void createExpenditureYearly() {
        expenditureYearly = new PieChartModel();

        List<ExpenditureDto> ListExpenditureByYear = null;

        try {
            ListExpenditureByYear = new ExpenditureDao().getExpenditureByYear(new Date());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (ExpenditureDto eByYear : ListExpenditureByYear) {
            expenditureYearly.set(eByYear.getName(), eByYear.getValue());
        }

        expenditureYearly.setTitle("Despesa Anual");
        expenditureYearly.setLegendPosition("w");
    }

    public PieChartModel getExpenditureDaily() {
        return expenditureDaily;
    }

    public PieChartModel getExpenditureMonthly() {
        return expenditureMonthly;
    }

    public PieChartModel getExpenditureYearly() {
        return expenditureYearly;
    }

    public void setExpenditureDaily(PieChartModel expenditureDaily) {
        this.expenditureDaily = expenditureDaily;
    }

    public void setExpenditureMonthly(PieChartModel expenditureMonthly) {
        this.expenditureMonthly = expenditureMonthly;
    }

    public void setExpenditureYearly(PieChartModel expenditureYearly) {
        this.expenditureYearly = expenditureYearly;
    }
    
}
