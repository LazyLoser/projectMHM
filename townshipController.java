/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectMHM;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import login.sqlConnection;

/**
 *
 * @author User
 */
public class townshipController implements Initializable{
    @FXML
    private Label lblsetTownship,lblcount;
    
    @FXML
    private TableView<InfoDetail> mytable=new TableView<>();
    final ObservableList<InfoDetail> data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<InfoDetail,String> CName;
    @FXML
    private TableColumn<InfoDetail,String> CNRC;
    @FXML
    private TableColumn<InfoDetail,String> CType;
    @FXML
    private TableColumn<InfoDetail,String> CFather;
    @FXML 
    private TableColumn<InfoDetail,String> CMother;
    @FXML
    private TableColumn<InfoDetail,String> CFGrandFather;
    @FXML
    private TableColumn<InfoDetail,String> CFGrandMother;
    @FXML
    private TableColumn<InfoDetail,String> CMGrandFather;
    @FXML
    private TableColumn<InfoDetail,String> CMGrandMother;
    int count=0;
    
    public void Loadbutton(ActionEvent actionEvent) throws SQLException
    {
        data.removeAll(data);
        //mytable.refresh();
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT Name,ID,NRC_Type,Father_NRC_ID,Mother_NRC_ID,F_Grandfather_NRC_ID,F_Grandmother_NRC_ID,M_Grandfather_NRC_ID,M_Grandmother_NRC_ID from national_people_information where Township='"+lblsetTownship.getText()+"'";//
        stmt=connection.prepareStatement(query);
        rs=stmt.executeQuery();
        while(rs.next()){
            count++;
            data.add(new InfoDetail(rs.getString("Name"), rs.getString("ID"), rs.getString("NRC_Type"), rs.getString("Father_NRC_ID"), rs.getString("Mother_NRC_ID"), rs.getString("F_Grandfather_NRC_ID"), rs.getString("F_Grandmother_NRC_ID"), rs.getString("M_Grandfather_NRC_ID"), rs.getString("M_Grandmother_NRC_ID"))); 
            mytable.setItems(data);           
        }
        lblcount.setText(String.valueOf(count));
        count=0;
        stmt.close();
        rs.close();
        CName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        CNRC.setCellValueFactory(new PropertyValueFactory<>("ID"));
        CType.setCellValueFactory(new PropertyValueFactory<>("IDType"));
        CFather.setCellValueFactory(new PropertyValueFactory<>("FatherID"));
        CMother.setCellValueFactory(new PropertyValueFactory<>("MotherID"));
        CFGrandFather.setCellValueFactory(new PropertyValueFactory<>("FGFatherID"));
        CFGrandMother.setCellValueFactory(new PropertyValueFactory<>("FGMotherID"));
        CMGrandFather.setCellValueFactory(new PropertyValueFactory<>("MGFatherID"));
        CMGrandMother.setCellValueFactory(new PropertyValueFactory<>("MGMotherID"));
        mytable.setEditable(true);
        mytable.setTableMenuButtonVisible(true);
        
        }
    
    public void Nextbutton(ActionEvent actionEvent) throws IOException {        
                     
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/projectMHM/data2.fxml"));
            Parent root=(Parent) loader.load();         
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin Frame");
            stage.show();      
    }    
    public void backfun(ActionEvent actionEvent) {
        try {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/township.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Admin Frame");
            stage.show();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }    
    public void backfun1(ActionEvent actionEvent) {
        try {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/employeetownship.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Employee Frame");
            stage.show();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public void myFuction(String text)
    {
        lblsetTownship.setText(text);
    }
    
}
