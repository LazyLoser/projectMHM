package projectMHM;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.DataFormat.URL;
import javafx.stage.FileChooser;
import login.sqlConnection;

public class Controller {//implements Initializable{
    final ObservableList religion=FXCollections.observableArrayList();
    final ObservableList township=FXCollections.observableArrayList();
    final ObservableList bloodtype=FXCollections.observableArrayList();
    final ObservableList NRCtype=FXCollections.observableArrayList();
    final ObservableList Age=FXCollections.observableArrayList();
    @FXML
    private TextField txtID,txtAge,txtHeight,txtNationality,txtOccupation,txtName,txtFatherNRC,txtMotherNRC,txtFatherGFNRC,txtFatherGMNRC,txtMotherGFNRC,txtMotherGMNRC,txtUserName,txtPassword;
    @FXML
    private DatePicker txtDOB;
    @FXML
    private TextField txtUpdateID,txtUpdateAge,txtUpdateHeight,txtUpdateOccupation,txtUpdateTownship,txtUpdateBloodType,txtUpdateNRCType,txtgetTownship,txtdeleteID;
    @FXML
    private TextArea txtUpdateAddress,txtAddress;
    @FXML
    RadioButton admin,radioMale;
    @FXML
    RadioButton employ,radioFemale;
    @FXML
    Label lbldeleteName,lbldeleteNRCType,lbldeleteTownship;
    @FXML
    private AnchorPane logPane;
    @FXML
    private AnchorPane insertPane;
    @FXML
    private AnchorPane RootPane;
    @FXML
    private AnchorPane AdmFun;
    @FXML
    private AnchorPane PasPane;
    @FXML
    private ImageView insertiv,updateiv;
    @FXML
    private TextField txtsearchNRC,txtsearchName;
    @FXML
    private ComboBox comboReligion,comboTownship,comboBloodType,comboNRCType,comboAge;
    String gender="",Texttownship="";
    String searchNRC="",searchName="";
    private FileChooser filechooser;
    private FileInputStream fis;
    File file;
    
    public void next(ActionEvent actionEvent) throws Exception {
        if(admin.isSelected())
        {
            Parent root=FXMLLoader.load(getClass().getResource("/projectMHM/Login.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setTitle("Login Mhat Htar Mal");
            stage.setScene(scene);
            stage.show();
        }
        else if(employ.isSelected())
        {
            Parent rootemp = FXMLLoader.load(getClass().getResource("/projectMHM/login (1).fxml"));
            Stage stageemp = new Stage();
            Scene sceneemp = new Scene(rootemp);
            stageemp.setTitle("Login Mhat Htar Mal");
            stageemp.setScene(sceneemp);
            stageemp.show();
        }
    }
    public void logout(ActionEvent actionEvent) {
        Alert alertdel1 = new Alert(Alert.AlertType.CONFIRMATION);
        alertdel1.setTitle("Confirmation!");
        alertdel1.setHeaderText("Are you sure to want to log out?");
        alertdel1.setContentText("Click Ok to proceed");

        Optional<ButtonType> result = alertdel1.showAndWait();
        if (result.get() == ButtonType.OK) {

            try {

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                Parent parent = FXMLLoader.load(getClass().getResource("/projectMHM/Login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Login Frame");
                stage.show();
                /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Complete");
                alert.setHeaderText("Your password has been changed!");
                alert.setContentText("Please Login again!!");
                alert.show();*/

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {

        }
    }
    
    public void insert(ActionEvent actionEvent) {
        try {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/Insertnew.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Admin Frame");
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update(ActionEvent actionEvent) {
        try {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/update.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Admin Frame");
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updatesearchbutton(ActionEvent event) throws SQLException
    {
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT * FROM national_people_information WHERE ID=?";
        stmt=connection.prepareStatement(query);
        stmt.setString(1, txtUpdateID.getText());
        rs=stmt.executeQuery();
        while(rs.next()){
            Image image=new Image(rs.getBinaryStream("Image"));//file.toURI().toString()
            updateiv.setImage(image);
            txtUpdateAge.setText(rs.getString("Age"));
            txtUpdateHeight.setText(rs.getString("Height"));
            txtUpdateOccupation.setText(rs.getString("Occupation"));
            txtUpdateAddress.setText(rs.getString("Address"));
            txtUpdateTownship.setText(rs.getString("Township"));
            txtUpdateBloodType.setText(rs.getString("BloodType"));
            txtUpdateNRCType.setText(rs.getString("NRC_Type"));
        }
        stmt.close();
        rs.close();
    }   
    public void delete(ActionEvent actionEvent) throws SQLException{
        try {           
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/delete.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Admin Frame");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void deletesearchbutton(ActionEvent event) throws SQLException
    {
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT Name,NRC_Type,Township FROM national_people_information WHERE ID=?";
        stmt=connection.prepareStatement(query);
        stmt.setString(1,txtdeleteID.getText());
        rs=stmt.executeQuery();
        while(rs.next()){
            lbldeleteName.setText(rs.getString("Name"));
            lbldeleteNRCType.setText(rs.getString("NRC_Type"));
            lbldeleteTownship.setText(rs.getString("Township"));
        }
        stmt.close();
        rs.close();
    }   
    public void Viewbutton(ActionEvent actionEvent) {
        try{          
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/township.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Admin Frame");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void employeeViewbutton(ActionEvent actionEvent) {
        try{          
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/employeetownship.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Employee Frame");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void search(ActionEvent actionEvent) {
        try{
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/adminsearch.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Admin Frame");
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void Searchbutton(ActionEvent actionEvent) throws IOException{
                  
            searchNRC=txtsearchNRC.getText();
            searchName=txtsearchName.getText();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/projectMHM/indivitual.fxml"));
            Parent root=(Parent) loader.load();
            individualController iController=loader.getController();
            iController.myFuction1(searchNRC, searchName);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Individual Frame");
            stage.show();    
    }    
    public void Nextbutton(ActionEvent actionEvent) throws IOException {
        
            Texttownship=txtgetTownship.getText();           
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/projectMHM/data2.fxml"));
            Parent root=(Parent) loader.load();
            townshipController tController=loader.getController();
            tController.myFuction(Texttownship);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin Frame");
            stage.show();      
    }
    public void employeeNextbutton(ActionEvent actionEvent) throws IOException {
        
            Texttownship=txtgetTownship.getText();           
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/projectMHM/data3.fxml"));
            Parent root=(Parent) loader.load();
            townshipController tController=loader.getController();
            tController.myFuction(Texttownship);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Employee Frame");
            stage.show();      
    }
    public void backfun(ActionEvent actionEvent) {
        try {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/threebutton.fxml"));
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
    public void employeesearch(ActionEvent actionEvent) {
        try {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/employeesearch.fxml"));
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
    public void employeeSearchbutton(ActionEvent actionEvent) {
        try {
            searchNRC=txtsearchNRC.getText();
            searchName=txtsearchName.getText();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/projectMHM/employeeindividual.fxml"));
            Parent root=(Parent) loader.load();
            individualController iController=loader.getController();
            iController.myFuction1(searchNRC, searchName);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Employee Frame");
            stage.show();    
            stage.show();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void backfun1(ActionEvent actionEvent) {
        try {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/twobutton.fxml"));
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
    public void setting(ActionEvent actionEvent) {
        try{
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/setting.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Setting Frame");
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void chgPasforadmin(ActionEvent actionEvent) {
        try{
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/forget.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Admin Password Change Frame");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ask(ActionEvent actionEvent) {
        try{
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/question.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Forget Password Frame");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void chgPasforemployee(ActionEvent actionEvent) {
        try{
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/edit.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Employee Password Change Frame");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------
    public void Loginbutton(ActionEvent event) throws SQLException, IOException
    {
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="select * from login where UserName=? and Password=?";
        stmt=connection.prepareStatement(query);
        stmt.setString(1, txtUserName.getText());
        stmt.setString(2, txtPassword.getText());
        rs=stmt.executeQuery();
        if(rs.next())
        {
            
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/threebutton.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Admin Frame");
            stage.show();
        }
        else{
            String queryEmployee="select * from employee where EmployeeName=? and EmployeePassword=?";
            stmt=connection.prepareStatement(queryEmployee);
            stmt.setString(1, txtUserName.getText());
            stmt.setString(2, txtPassword.getText());
            rs=stmt.executeQuery();
            if(rs.next())
            {
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Parent parent=FXMLLoader.load(getClass().getResource("/projectMHM/twobutton.fxml"));
                Stage stage=new Stage();
                Scene scene=new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Employee Frame");
                stage.show();
            }
            else
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!!");
                alert.setHeaderText("Incorrect Password...");
                alert.setContentText("Please Try Again!!");
                alert.showAndWait();
                txtUserName.clear();
                txtPassword.clear();
            }
            
        }
        stmt.close();
        rs.close();
    }
    
    public void radioselect(ActionEvent event)
    {       
         if(radioMale.isSelected()){
             gender=radioMale.getText();
         }
         if(radioFemale.isSelected()){
             gender=radioFemale.getText();
         }
    }
    public void insertImagebrowsebutton(ActionEvent event) throws SQLException{
        fillReligionComboBox();
        fillTownshipComboBox();
        fillBloodTypeComboBox();
        fillNRCTypeComboBox();
        fillAgeComboBox();
        filechooser=new FileChooser();
        file=filechooser.showOpenDialog(null);
        if(file!=null){
            Image image=new Image(file.toURI().toString(),200,200,true,true);//file.toURI().toString()
            insertiv.setImage(image);
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Image not found.");
            alert.setContentText("Please Try Again!");
            alert.showAndWait();
        }
    }
    public void Savebutton(ActionEvent event) throws SQLException, FileNotFoundException
    {

    if(txtID.getText().compareTo("")==0||txtHeight.getText().compareTo("")==0||txtNationality.getText().compareTo("")==0 || txtOccupation.getText().compareTo("")==0||txtAddress.getText().compareTo("")==0||txtName.getText().compareTo("")==0||txtFatherNRC.getText().compareTo("")==0
            ||txtFatherGFNRC.getText().compareTo("")==0||txtFatherGMNRC.getText().compareTo("")==0||txtMotherNRC.getText().compareTo("")==0||txtMotherGFNRC.getText().compareTo("")==0||txtFatherGMNRC.getText().compareTo("")==0)
    {
         Alert alert=new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Oops!");
         alert.setHeaderText("Something went wrong!");
         alert.setContentText("There may be one or more blanks in TextFields.\nPlease Try Again.");
         alert.showAndWait();
    }
        else if(!txtID.getText().matches("[0-1][0-2]/[A-Z]{3}\\(N\\)[0-9]{6}")||!txtFatherNRC.getText().matches("[0-1][0-2]/[A-Z]{3}\\(N\\)[0-9]{6}")||!txtFatherGFNRC.getText().matches("[0-1][0-2]/[A-Z]{3}\\(N\\)[0-9]{6}")||
                !txtFatherGMNRC.getText().matches("[0-1][0-2]/[A-Z]{3}\\(N\\)[0-9]{6}")||!txtMotherNRC.getText().matches("[0-1][0-2]/[A-Z]{3}\\(N\\)[0-9]{6}")||!txtMotherGFNRC.getText().matches("[0-1][0-2]/[A-Z]{3}\\(N\\)[0-9]{6}")||!txtMotherGMNRC.getText().matches("[0-1][0-2]/[A-Z]{3}\\(N\\)[0-9]{6}"))
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oops!");
            alert.setHeaderText("Something went wrong!");
            alert.setContentText("The NRC ID format is invalid. \nPlease Try Again.");
            alert.showAndWait();
            clearFields();
        }
    else {
        sqlConnection sqlConnection = new sqlConnection();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query = "INSERT INTO national_people_information (ID,Gender,Age,Height,DOB,Nationality,Religion,Occupation,Address,Township,BloodType,Name,NRC_Type,Father_NRC_ID,Mother_NRC_ID,F_Grandfather_NRC_ID,F_Grandmother_NRC_ID,M_Grandfather_NRC_ID,M_Grandmother_NRC_ID,Image) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        stmt = connection.prepareStatement(query);
        stmt.setString(1, txtID.getText());
        stmt.setString(2, gender);
        stmt.setString(3, (String) comboAge.getSelectionModel().getSelectedItem());
        stmt.setString(4, txtHeight.getText());
        stmt.setString(5, ((TextField) txtDOB.getEditor()).getText());
        stmt.setString(6, txtNationality.getText());
        stmt.setString(7, (String) comboReligion.getSelectionModel().getSelectedItem());
        stmt.setString(8, txtOccupation.getText());
        stmt.setString(9, txtAddress.getText());
        stmt.setString(10, (String) comboTownship.getSelectionModel().getSelectedItem());
        stmt.setString(11, (String) comboBloodType.getSelectionModel().getSelectedItem());
        stmt.setString(12, txtName.getText());
        stmt.setString(13, (String) comboNRCType.getSelectionModel().getSelectedItem());
        stmt.setString(14, txtFatherNRC.getText());
        stmt.setString(15, txtMotherNRC.getText());
        stmt.setString(16, txtFatherGFNRC.getText());
        stmt.setString(17, txtFatherGMNRC.getText());
        stmt.setString(18, txtMotherGFNRC.getText());
        stmt.setString(19, txtMotherGMNRC.getText());
        fis = new FileInputStream(file);
        stmt.setBinaryStream(20, (InputStream) fis, (int) file.length());
        stmt.execute();
        stmt.close();
        clearFields();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("National Registration Data Insertion");
        alert.setHeaderText(null);
        alert.setContentText("Data has been inserted");
        alert.showAndWait();
    }
    }
    
    public void clearFields()
    {
        txtID.clear();
        comboAge.setValue(null);
        txtHeight.clear();
        txtDOB.setValue(null);
        txtNationality.clear();
        comboReligion.setValue(null);
        txtOccupation.clear();
        txtAddress.clear();
        comboTownship.setValue(null);
        comboBloodType.setValue(null);
        txtName.clear();
        comboNRCType.setValue(null);
        txtFatherNRC.clear();
        txtMotherNRC.clear();
        txtFatherGFNRC.clear();
        txtFatherGMNRC.clear();
        txtMotherGFNRC.clear();
        txtMotherGMNRC.clear();
        insertiv.setImage(null);       
    }
    public void clearUpdateFields()
    {
        txtUpdateID.clear();
        txtUpdateAge.clear();
        txtUpdateHeight.clear();
        txtUpdateOccupation.clear();
        txtUpdateAddress.clear();
        txtUpdateTownship.clear();
        txtUpdateBloodType.clear();
        txtUpdateNRCType.clear();
        updateiv.setImage(null);
    }
    public void clearDeleteFields()
    {
        lbldeleteNRCType.setText("");
        lbldeleteName.setText("");
        lbldeleteTownship.setText("");
        txtdeleteID.clear();
    }
    public void updateImagebrowsebutton(ActionEvent event){
        
        filechooser=new FileChooser();
        file=filechooser.showOpenDialog(null);
        if(file!=null){
            Image image=new Image(file.toURI().toString(),200,200,true,true);//file.toURI().toString()
            updateiv.setImage(image);
        }
    }
    public void Updatebutton(ActionEvent event) throws SQLException, FileNotFoundException{
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="UPDATE national_people_information SET Age=?,Height=?,Occupation=?,Address=?,Township=?,BloodType=?,NRC_Type=?,Image=? where ID='"+txtUpdateID.getText()+"'";
        stmt=connection.prepareStatement(query);
        stmt.setInt(1, Integer.parseInt(txtUpdateAge.getText()));  
        stmt.setString(2, txtUpdateHeight.getText());
        stmt.setString(3, txtUpdateOccupation.getText());
        stmt.setString(4, txtUpdateAddress.getText());
        stmt.setString(5, txtUpdateTownship.getText());
        stmt.setString(6, txtUpdateBloodType.getText());   
        stmt.setString(7, txtUpdateNRCType.getText());
        fis=new FileInputStream(file);
        stmt.setBinaryStream(8, (InputStream)fis,(int)file.length());
        stmt.execute();
        stmt.close();
        clearUpdateFields();
        
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("National Registration Data Updatation");
        alert.setHeaderText(null);
        alert.setContentText("Data has been Updated");
        alert.showAndWait(); 
    }
    public void Deletebutton(ActionEvent event) throws SQLException
    {
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        String query="DELETE FROM national_people_information WHERE ID=?";
        stmt=connection.prepareStatement(query);
        stmt.setString(1, txtdeleteID.getText());
        stmt.executeUpdate();
        stmt.close();
        clearDeleteFields();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("National Registration Data Deletation");
        alert.setHeaderText(null);
        alert.setContentText("Data has been Deleted");
        alert.showAndWait(); 
    }
    public void fillReligionComboBox() throws SQLException{
        religion.removeAll(religion);
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT Name from religion";
        stmt=connection.prepareStatement(query);
        rs=stmt.executeQuery();
        while(rs.next()){
            religion.add(rs.getString("Name"));
        }
        comboReligion.setItems(religion);
        stmt.close();
        rs.close();
        
    }
    public void fillTownshipComboBox() throws SQLException{
        township.removeAll(township);
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT Name from township";
        stmt=connection.prepareStatement(query);
        rs=stmt.executeQuery();
        while(rs.next()){
            township.add(rs.getString("Name"));
        }
        comboTownship.setItems(township);
        stmt.close();
        rs.close();
        
    }
    public void fillBloodTypeComboBox() throws SQLException{
        bloodtype.removeAll(bloodtype);
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT Name from bloodtype";
        stmt=connection.prepareStatement(query);
        rs=stmt.executeQuery();
        while(rs.next()){
            bloodtype.add(rs.getString("Name"));
        }
        comboBloodType.setItems(bloodtype);
        stmt.close();
        rs.close();
        
    }
    public void fillNRCTypeComboBox() throws SQLException{
        NRCtype.removeAll(NRCtype);
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT Type from nrctype";
        stmt=connection.prepareStatement(query);
        rs=stmt.executeQuery();
        while(rs.next()){
            NRCtype.add(rs.getString("Type"));
        }
        comboNRCType.setItems(NRCtype);
        stmt.close();
        rs.close();
        
    }
    public void fillAgeComboBox() throws SQLException{
        Age.removeAll(Age);
        sqlConnection sqlConnection=new sqlConnection();
        Connection connection=sqlConnection.getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        String query="SELECT Number from age";
        stmt=connection.prepareStatement(query);
        rs=stmt.executeQuery();
        while(rs.next()){
            Age.add(rs.getString("Number"));
        }
        comboAge.setItems(Age);
        stmt.close();
        rs.close();
        
    }

    public void credit(ActionEvent actionEvent) {
        Stage stage=new Stage();
        StackPane root=new StackPane();
        MediaPlayer player=new MediaPlayer(new Media(getClass().getResource("creditvideo.mp4").toExternalForm()));
        MediaView mediaView=new MediaView(player);
        root.getChildren().add(mediaView);
        Scene scene=new Scene(root,1440,1080);
        stage.setScene(scene);
        stage.show();
        player.play();
    }
    /*public void initialize(URL location, ResourceBundle resources) {
            new SplashScreen().start();
    }*/

    class SplashScreen extends Thread{
@Override
        public void run()
        {
            try {
                Thread.sleep(2000);
                Platform.runLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        Parent rootwel= null;
                        try {
                            rootwel = FXMLLoader.load(getClass().getResource("/projectMHM/Login.fxml"));
                        } catch (IOException e) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,e);
                        }
                        RootPane.getScene().getWindow().hide();
                        Stage stagewel=new Stage();
                        Scene scenewel=new Scene(rootwel);
                        stagewel.setTitle("Choose Mhat Htar Mal");
                        stagewel.setScene(scenewel);
                        stagewel.show();              
                    }
                });
            } catch (InterruptedException e) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
}
