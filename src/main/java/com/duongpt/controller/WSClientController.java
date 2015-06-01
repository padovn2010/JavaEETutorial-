package com.duongpt.controller;

import com.duongpt.javaeewebservice.Calculation;
import com.duongpt.javaeewebservice.CalculationService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Pado
 */

@Named (value = "wsclientcontroller")
@SessionScoped
public class WSClientController implements Serializable{
    private String para01;
    private String para02;
    private String sum;
    private String subtract;
    private static final String DEFAULT = " ";
    
    @WebServiceRef(wsdlLocation = "http://localhost:8080/JavaEEWebService/CalculationService?wsdl")
    private CalculationService calculationService;
    
    public WSClientController(){
        para01 = DEFAULT;
        para02 = DEFAULT;
        sum = DEFAULT;
        subtract = DEFAULT;
    }
        
    public String wsSum(){
        
        para01 = getPara01();
        para02 = getPara02();
        
        // Get instance of web-service
        Calculation cal= calculationService.getCalculationPort();
        int i = cal.sum(Integer.parseInt(para01), Integer.parseInt(para02));
        sum = Integer.toString(i);        
        return sum;
    }
    
    public String wsSub(){
        para01 = getPara01();
        para02 = getPara02();
        
        Calculation cal= calculationService.getCalculationPort();
        int i = cal.subtract(Integer.parseInt(para01), Integer.parseInt(para02));
        subtract = Integer.toString(i);
        return subtract;
    }
 
    public String getPara01() {
        return para01;
    }
    
    public void setPara01(String para01) {
        this.para01 = para01;
    }
    
    public String getPara02() {
        return para02;
    }
    
    public void setPara02(String para02) {
        this.para02 = para02;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getSubtract() {
        return subtract;
    }

    public void setSubtract(String subtract) {
        this.subtract = subtract;
    }
    
}
