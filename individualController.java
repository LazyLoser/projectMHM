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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import login.sqlConnection;

/**
 *
 * @author User
 */
public class individualController implements Initializable{

    @FXML
    private Label lblindividualName,lblindividualNRC,lblindividualGender,lblindividualAge,lblindividualHeight,lblindividualDOB,lblindividualNationality;
    @FXML
    private Label lblindividualReligion,lblindivitualOccupation,lblindividualAddress,lblindividualTownship,lblindividualBlood;
    @FXML
    private Label lblindividualFNRC,lblindividualMNRC,lblindividualFGFNRC,lblindividualFGMNRC,lblindividualMGFNRC,lblindividualMGMNRC;
    @FXML
    private Label lblindividualNRCType;
    @FXML
    private ImageView searchiv;
    public void Nextbutton(ActionEvent actionEvent) throws IOException {        
                     
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/projectMHM/adminsearch.fxml"));
            Parent root=(Parent) loader.load();         
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Data View Frame2");
            stage.show();      
    }
    public void employeesearch(ActionEvent actionEvent) {
        try{
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/employeesearch.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Search Frame");
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }
    public void myFuction1(String text1,String text2)
    {
        lblindividualNRC.setText(text1);
        lblindividualName.setText(text2);
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT * FROM national_people_information WHERE ID=? and Name=?";
        try {
            stmt=connection.prepareStatement(query);
            stmt.setString(1, lblindividualNRC.getText());
            stmt.setString(2, lblindividualName.getText());
            rs=stmt.executeQuery();
            while(rs.next()){
                Image image=new Image(rs.getBinaryStream("Image"));//file.toURI().toString()
                searchiv.setImage(image);
                lblindividualGender.setText(rs.getString("Gender"));
                lblindividualAddress.setText(rs.getString("Address"));
                lblindividualAge.setText(rs.getString("Age"));
                lblindividualBlood.setText(rs.getString("BloodType"));
                lblindividualHeight.setText(rs.getString("Height"));
                lblindividualDOB.setText(rs.getString("DOB"));
                lblindividualNationality.setText(rs.getString("Nationality"));
                lblindividualReligion.setText(rs.getString("Religion"));
                lblindivitualOccupation.setText(rs.getString("Occupation"));
                lblindividualTownship.setText(rs.getString("Township"));
                lblindividualFNRC.setText(rs.getString("Father_NRC_ID"));
                lblindividualMNRC.setText(rs.getString("Mother_NRC_ID"));
                lblindividualFGFNRC.setText(rs.getString("F_Grandfather_NRC_ID"));
                lblindividualFGMNRC.setText(rs.getString("F_Grandmother_NRC_ID"));
                lblindividualMGFNRC.setText(rs.getString("M_Grandfather_NRC_ID"));
                lblindividualMGMNRC.setText(rs.getString("M_Grandmother_NRC_ID"));
                lblindividualNRCType.setText(rs.getString("NRC_Type"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(individualController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
