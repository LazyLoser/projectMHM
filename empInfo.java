package projectMHM;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class empInfo {
    private final StringProperty EmployeeID;
    private final StringProperty EmployeeName;
    private final StringProperty EmployeePassword;

    public empInfo(String eid,String ename,String epassword){
        this.EmployeeID=new SimpleStringProperty(eid);
        this.EmployeeName=new SimpleStringProperty(ename);
        this.EmployeePassword=new SimpleStringProperty(epassword);

    }

    public String getEmployeeID() {
        return EmployeeID.get();
    }
    public void setEmployeeID(String employeeID)
    {
        this.EmployeeID.set(employeeID);
    }

    public String getEmployeeName() {
        return EmployeeName.get();
    }
    public void setEmployeeName(String employeeName)
    {
        this.EmployeeName.set(employeeName);
    }

    public String getEmployeePassword() {
        return EmployeePassword.get();
    }
    public void setEmployeePassword(String employeePassword)
    {
        this.EmployeePassword.set(employeePassword);
    }
}
