package projectMHM;

import com.mysql.jdbc.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.sqlConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class editCon {
    @FXML
    private TableView<empInfo>emptable=new TableView<>();
    final ObservableList<empInfo> data=FXCollections.observableArrayList();

    @FXML
    private TableColumn<empInfo,String> EName;
    @FXML
    private TableColumn<empInfo,String> EPassword;
    @FXML
    private TextField InsertEname,InsertEPass;
    private FileInputStream fis;
    File file;

    public void loadE(ActionEvent actionEvent) throws SQLException{
        data.removeAll(data);
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT EmployeeID,EmployeeName,EmployeePassword from employee";
        stmt=connection.prepareStatement(query);
        rs=stmt.executeQuery();
        while(rs.next())
        {
            data.add(new empInfo(rs.getString("EmployeeID"),rs.getString("EmployeeName"),rs.getString("EmployeePassword")));
            emptable.setItems(data);
        }
        stmt.close();
        rs.close();

        EName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        EPassword.setCellValueFactory(new PropertyValueFactory<>("EmployeePassword"));
        emptable.setEditable(true);
        emptable.setTableMenuButtonVisible(true);

    }


    public void setting(ActionEvent actionEvent) {
        try{
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent= FXMLLoader.load(getClass().getResource("/projectMHM/setting.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Setting Frame");
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void addEAcc(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="INSERT INTO employee(EmployeeName,EmployeePassword) VALUES(?,?) ";
        stmt=connection.prepareStatement(query);
        stmt.setString(1,InsertEname.getText());
        stmt.setString(2,InsertEPass.getText());


        stmt.execute();
        stmt.close();
        InsertEPass.clear();
        InsertEname.clear();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Employee Account Selection");
        alert.setHeaderText(null);
        alert.setContentText("Data has been inserted");
        alert.showAndWait();


    }

    public void delEAcc(ActionEvent actionEvent) {
        Alert alertdel1 = new Alert(Alert.AlertType.CONFIRMATION);
        alertdel1.setTitle("Confirm");
        alertdel1.setHeaderText("Are you sure to want to delete?");
        alertdel1.setContentText("Click Ok to proceed");

        Optional<ButtonType> result = alertdel1.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK


            try {

                sqlConnection sqlConnection=new sqlConnection();
                Connection connection=sqlConnection.getConnection();
                PreparedStatement stmt;
                ResultSet rs;
                String query="DELETE FROM employee WHERE EmployeeName=?";
                stmt=connection.prepareStatement(query);
                stmt.setString(1,InsertEname.getText());
                stmt.execute();
                stmt.close();
                Alert alertdel = new Alert(Alert.AlertType.INFORMATION);
                alertdel.setTitle("Delete Complete");
                alertdel.setHeaderText(null);
                alertdel.setContentText("The Account is successfully deleted!");
                alertdel.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            InsertEname.clear();
            InsertEPass.clear();


        } else {
            InsertEname.clear();
            InsertEPass.clear();
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void select(MouseEvent event) {
        empInfo ee=emptable.getSelectionModel().getSelectedItem();
        if(ee==null)
        {
            InsertEname.setText("");
            InsertEPass.setText("");
        }
        else
        {
            String name=ee.getEmployeeName();
            String pass=ee.getEmployeePassword();
            InsertEname.setText(name);
            InsertEPass.setText(pass);
        }
    }
}
