package projectMHM;

import com.mysql.jdbc.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.sqlConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class questionCon {
    @FXML
    private TextField question1;
    @FXML
    private TextField question2;
    @FXML
    private TextField question3;


    public void backToLogin(ActionEvent actionEvent) throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Parent root= FXMLLoader.load(getClass().getResource("/ProjectMHM/Login.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(root);
        stage.setTitle("Mhat Htar Mal Login");
        stage.setScene(scene);
        stage.show();
    }

    public void resetPas(ActionEvent actionEvent)throws SQLException,IOException{
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="select * from login where SecurityQuestion1=? and SecurityQuestion2=? and SecurityQuestion3=?";
        stmt=connection.prepareStatement(query);
        stmt.setString(1, question1.getText());
        stmt.setString(2, question2.getText());
        stmt.setString(3,question3.getText());
        rs=stmt.executeQuery();
        if(rs.next())
        {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Parent root= FXMLLoader.load(getClass().getResource("/ProjectMHM/forget.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setTitle("Mhat Htar Mal Reset Password");
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Answer");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Answer! Please Try again.");
            alert.show();
            question1.clear();
            question2.clear();
            question3.clear();
        }
    }
}
