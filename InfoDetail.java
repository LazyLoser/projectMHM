/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectMHM;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dell
 */
public class InfoDetail {
    private final StringProperty Name;
    private final StringProperty ID;
    private final StringProperty IDType;
    private final StringProperty FatherID;
    private final StringProperty MotherID;
    private final StringProperty FGFatherID;
    private final StringProperty FGMotherID;
    private final StringProperty MGFatherID;
    private final StringProperty MGMotherID;
    
    
    public InfoDetail(String name,String id,String idtype,String fatherid,String motherid,String fgfatherid,String fgmotherid,String mgfatherid,String mgmotherid)
    {
        this.Name=new SimpleStringProperty(name);
        this.ID=new SimpleStringProperty(id);
        this.IDType=new SimpleStringProperty(idtype);
        this.FatherID=new SimpleStringProperty(fatherid);
        this.MotherID=new SimpleStringProperty(motherid);
        this.FGFatherID=new SimpleStringProperty(fgfatherid);
        this.FGMotherID=new SimpleStringProperty(fgmotherid);
        this.MGFatherID=new SimpleStringProperty(mgfatherid);
        this.MGMotherID=new SimpleStringProperty(mgmotherid);
    }

    public String getName() {
        return Name.get();
    }
    public void setName(String Name)
    {
        this.Name.set(Name);
    }
    
    public String getID() {
        return ID.get();
    }
    public void setID(String ID)
    {
        this.ID.set(ID);
    }
    
    public String getIDType() {
        return IDType.get();
    }
    public void setIDType(String IDType)
    {
       this.IDType.set(IDType);
    }
    
    public String getFatherID() {
        return FatherID.get();
    }
    public void setFatherID(String FatherID)
    {
        this.FatherID.set(FatherID);
    }
    
    public String getMotherID() {
        return MotherID.get();
    }
    public void setMotherID(String MotherID)
    {
        this.MotherID.set(MotherID);
    }
    
    public String getFGFatherID() {
        return FGFatherID.get();
    }
    public void setFGFatherID(String FGFatherID)
    {
        this.FGFatherID.set(FGFatherID);
    }
    
    public String getFGMotherID() {
        return FGMotherID.get();
    }
    public void setFGMotherID(String FGMotherID)
    {
        this.FGMotherID.set(FGMotherID);
    }
    
    public String getMGFatherID() {
        return MGFatherID.get();
    }
    public void setMGFatherID(String MGFatherID)
    {
        this.MGFatherID.set(MGFatherID);
    }
    
    public String getMGMotherID() {
        return MGMotherID.get();
    }
    public void setMGMotherID(String MGMotherID)
    {
        this.MGMotherID.set(MGMotherID);
    }
               
}
