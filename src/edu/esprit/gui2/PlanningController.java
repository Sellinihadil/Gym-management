/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import edu.esprit.entities.availableCour;
import edu.esprit.entities.cour;
import edu.esprit.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hadil
 */
public class PlanningController implements Initializable {

    @FXML
    private Button availableCour_addBtn;

    @FXML
    private Button availableCour_btn;

    @FXML
    private Button availableCour_clearBtn;

    @FXML
    private TableColumn<availableCour, String> availableCour_col_cour;

    @FXML
    private TableColumn<availableCour, String> availableCour_col_description;

    @FXML
    private TableColumn<availableCour, String> availableCour_col_status;

    @FXML
    private TextField availableCour_cour;

    @FXML
    private TextField availableCour_description;

    @FXML
    private AnchorPane availableCour_form;

    @FXML
    private Button availableCour_form_deleteBtn;


    @FXML
    private TableView<availableCour> availableCour_tableView;

    @FXML
    private Button availableCour_updateBtn;

    @FXML
    private Button dashboard_btn;

    private AnchorPane dashboard_form;

    @FXML
    private Button listcour_addBtn;

    @FXML
    private ComboBox<cour> listcour_coach;
    @FXML
    private ComboBox<cour> listcour_status;

    @FXML
    private TableColumn<availableCour, String> listcour_col_coach;

    @FXML
    private TableColumn<availableCour, Date> listcour_col_date;

    @FXML
    private TableColumn<availableCour, Date> listcour_col_description;

    @FXML
    private TableColumn<availableCour, Integer> listcour_col_idcour;

    @FXML
    private TableColumn<availableCour, String> listcour_col_nomcour;

    @FXML
    private TableColumn<availableCour, String> listcour_col_salle;

    @FXML
    private TableColumn<availableCour, String> listcour_col_status;

    @FXML
    private DatePicker listcour_date;

    @FXML
    private TextField listcour_description;

    @FXML
    private TextField listcour_idcour;


    @FXML
    private Button listcour_resetBtn;

    @FXML
    private ComboBox<availableCour> listcour_salle;


    @FXML
    private TextField listcour_search;

    @FXML
    private TableView<availableCour> listcour_tableView;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    private AnchorPane modifierPlan_form;

    @FXML
    private TableView<cour> plancour_tableview;

    @FXML
    private Button plannifier_btn;

    @FXML
    private Button plannifiercour_addBtn;

    @FXML
    private Button plannifiercour_clearBtn;

    @FXML
    private TableColumn<cour, String> plannifiercour_col_coach;

    @FXML
    private TableColumn<cour, Date> plannifiercour_col_date;

    @FXML
    private TableColumn<cour, String> plannifiercour_col_duree;

    @FXML
    private TableColumn<cour, Integer> plannifiercour_col_idcour;

    @FXML
    private TableColumn<cour, String> plannifiercour_col_nomcour;

    @FXML
    private TableColumn<cour, String> plannifiercour_col_salle;

    @FXML
    private TableColumn<cour, String> plannifiercour_col_status;

    @FXML
    private DatePicker plannifiercour_date;

    @FXML
    private Button plannifiercour_deleteBtn;

    @FXML
    private TextField plannifiercour_description;

    @FXML
    private AnchorPane plannifiercour_form;

    @FXML
    private TextField plannifiercour_idcour;

    @FXML
    private ComboBox<cour> plannifiercour_nomcour;

    @FXML
    private ComboBox<cour> plannifiercour_salle;

    @FXML
    private TextField plannifiercour_search;

    @FXML
    private ComboBox<cour> plannifiercour_status;

    @FXML
    private Button plannifiercour_updateBtn;
    @FXML
    private ComboBox<cour> listcour_cour;
    @FXML
    private TextField plannifiercour_coach;
    @FXML
    private Label dashboard_totalcour;

    @FXML
    private BarChart<cour, String> dashboard_barchart;
    @FXML
    private AnchorPane reservationcour_form;
    @FXML
    private Button listcour_resetBtn1;
    @FXML
    private Button listcour_resetBtn2;
    
    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    /**
     *
     * @return
     */
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    
    
    
    
     void totalcour() {

        String sql = "SELECT COUNT(idcour) FROM cour";

        connect = MyConnection.connectDb();

        int count= 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                count = result.getInt("COUNT(idcour)");
            }

            dashboard_totalcour.setText(String.valueOf(count));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     void totalcourplanifier() {

        dashboard_barchart.getData().clear();

        String sql = "SELECT date, COUNT(idcour) FROM cour WHERE status = 'active' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        connect = MyConnection.connectDb();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            dashboard_barchart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
     
    public ObservableList<availableCour> availableCourList() {

        ObservableList<availableCour> list = FXCollections.observableArrayList();

        String sql = "SELECT * FROM availablecour";

        Connection connect = MyConnection.connectDb();

        try {
            availableCour courA;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                courA = new availableCour(
                        result.getString("nomCour"),
                        result.getString("description"));

                list.add(courA);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private ObservableList<availableCour> availableCourseList;

    /**
     *
     */
    void availableCourShowList(ActionEvent event) {

        availableCourseList = availableCourList();

        availableCour_col_cour.setCellValueFactory(new PropertyValueFactory<>("nomCour"));
        availableCour_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));

        availableCour_tableView.setItems(availableCourseList);
    }

    @FXML
    public void availableCourSelect(ActionEvent event) {
        availableCour courB = availableCour_tableView.getSelectionModel().getSelectedItem();
        int num = availableCour_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableCour_cour.setText(courB.getNomCour());
        availableCour_description.setText(courB.getDescription());

    }

    @FXML
    void availableCourAdd(ActionEvent event) {

        String insertData = "INSERT INTO course (course,description,degree) VALUES(?,?,?)";

        connect = MyConnection.connectDb();

        try {
            Alert alert;

            if (availableCour_cour.getText().isEmpty()
                    || availableCour_description.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
//            CHECK IF THE COURSE YOU WANT TO INSERT IS ALREADY EXIST!
                String check = "SELECT cour FROM availablecour WHERE availablecour = '"
                        + availableCour_cour.getText() + "'";

                Statement statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cour: " + availableCour_cour.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, availableCour_cour.getText());
                    prepare.setString(2, availableCour_description.getText());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                   

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void availableCourClear(ActionEvent event) {
        availableCour_cour.setText("");
        availableCour_description.setText("");
    }

    @FXML
    void availableCourUpdate(ActionEvent event) {

        String update = "UPDATE availablecour SET description = '"
                + availableCour_description.getText() + "' WHERE nomCour = '"
                + availableCour_cour.getText() + "'";

        connect = MyConnection.connectDb();

        try {
            Alert alert;

            if (availableCour_cour.getText().isEmpty()
                    || availableCour_description.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE nomCour: " + availableCour_cour.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(update);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                   

                } else {
                    return;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void availableCourDelete(ActionEvent event) {

        String delete = "DELETE FROM availablecour WHERE nomCour = '"
                + availableCour_cour.getText() + "'";

        connect = MyConnection.connectDb();

        try {
            Alert alert;

            if (availableCour_cour.getText().isEmpty()
                    || availableCour_description.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
//                ALL GOOD GUYS! NOW LETS PROCEED TO ADD STUDENTS FORM
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Cour : " + availableCour_cour.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(delete);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    
    
    
    
    
    private final String[] nomcourList = {"Yoga", "Gymnastique", "Cardio", "Danse","HIIT ", "CrossFit", "TRX",};

    @FXML
    void addCoursnomList(ActionEvent event) {

        List<String> nomcourL = new ArrayList<>();

        nomcourL.addAll(Arrays.asList(nomcourList));

        ObservableList listnomcour = FXCollections.observableArrayList(nomcourL);
        plannifiercour_nomcour.setItems(listnomcour);

    }

    private final String[] SalleList = {"Musculation", "Boxe", "Stretching", "cardio-trainning"};

    @FXML
    void addCoursSalleList(ActionEvent event) {
        List<String> salleL = new ArrayList<>();

        salleL.addAll(Arrays.asList(SalleList));

        ObservableList listsalle = FXCollections.observableArrayList(salleL);
        plannifiercour_salle.setItems(listsalle);
    }

    private final String[] StatusList = {"active", "Inactive"};

    @FXML
   void addCoursStatusList(ActionEvent event) {
        List<String> StatusL = new ArrayList<>();

        StatusL.addAll(Arrays.asList(StatusList));

        ObservableList liststatus = FXCollections.observableArrayList(StatusL);
        plannifiercour_status.setItems(liststatus);
    }
    
    
    
    
    
    
   

    public ObservableList<cour> planifiecoursList() {

        ObservableList<cour> listcours = FXCollections.observableArrayList();

        String sql = "SELECT * FROM cours";
        connect = MyConnection.connectDb();

        try {
            cour c;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                c = new cour(
                        result.getInt("idcour"),
                        result.getString("nomcour"),
                        result.getString("dureecour"),
                        result.getString("status"),
                        result.getString("salle"),
                        result.getDate("datee"));

                listcours.add(c);
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return listcours;
    }
    private ObservableList<cour> planifiecoursListD;

    void plannificourShowList(ActionEvent event) {
        planifiecoursListD = planifiecoursList();

        plannifiercour_col_idcour.setCellValueFactory(new PropertyValueFactory<>("idcour"));
        plannifiercour_col_nomcour.setCellValueFactory(new PropertyValueFactory<>("nomcour"));
        plannifiercour_col_coach.setCellValueFactory(new PropertyValueFactory<>("nomcoach"));
        plannifiercour_col_salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
        plannifiercour_col_date.setCellValueFactory(new PropertyValueFactory<>("datee"));
        plannifiercour_col_duree.setCellValueFactory(new PropertyValueFactory<>("dureecour"));
        plannifiercour_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        plancour_tableview.setItems(planifiecoursListD);

    }

    @FXML
    void addCoursPlannifie(ActionEvent event) {

        cour c = plancour_tableview.getSelectionModel().getSelectedItem();
        int num = plancour_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        plannifiercour_col_idcour.setText(String.valueOf(c.getIdcour()));
        plannifiercour_coach.setText(c.getNomcoach());
        plannifiercour_description.setText(c.getDescriptioncour());
        plannifiercour_date.setValue(LocalDate.parse(String.valueOf(c.getDatee())));

    }

     @FXML
    void updatecourplanifie(ActionEvent event) {
        

        String update = "UPDATE student SET "
                + "nomcour = '" + plannifiercour_nomcour.getSelectionModel().getSelectedItem()
                + "', salle = '" + plannifiercour_salle.getSelectionModel().getSelectedItem()
                + "', description = '" + plannifiercour_description.getText()
                + "', coach = '" + plannifiercour_coach.getText()
                + "', datee = '" + plannifiercour_date.getValue()
                + "', status = '" + plannifiercour_status.getSelectionModel().getSelectedItem()
                + plannifiercour_idcour.getText() + "'";

        connect = MyConnection.connectDb();

        try {
            Alert alert;
            if (plannifiercour_idcour.getText().isEmpty()
                    || plannifiercour_nomcour.getSelectionModel().getSelectedItem() == null
                    || plannifiercour_salle.getSelectionModel().getSelectedItem() == null
                    || plannifiercour_description.getText().isEmpty()
                    || plannifiercour_coach.getText().isEmpty()
                    || plannifiercour_date.getValue() == null
                    || plannifiercour_status.getSelectionModel().getSelectedItem() == null
) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE cour" + plannifiercour_idcour.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(update);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();


                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    void deletecourplanifie(ActionEvent event) {
        String delete = "DELETE FROM cour WHERE idcour = '"
                + plannifiercour_idcour.getText() + "'";

        connect = MyConnection.connectDb();

        try {
            Alert alert;
            if (plannifiercour_idcour.getText().isEmpty()
                    || plannifiercour_nomcour.getSelectionModel().getSelectedItem() == null
                    || plannifiercour_salle.getSelectionModel().getSelectedItem() == null
                    || plannifiercour_description.getText().isEmpty()
                    || plannifiercour_coach.getText().isEmpty()
                    || plannifiercour_date.getValue() == null
                    || plannifiercour_status.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE cour " + plannifiercour_idcour.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    Statement statement = connect.createStatement();
                    statement.executeUpdate(delete);

                    String check = "SELECT nomcour FROM cour "
                            + "WHERE idcour = '" + plannifiercour_idcour.getText() + "'";

                    prepare = connect.prepareStatement(check);
                    result = prepare.executeQuery();

                    // IF THE STUDENT NUMBER IS EXIST THEN PROCEED TO DELETE
                    if (result.next()) {
                        String deletecour = "DELETE FROM cour WHERE "
                                + "idcour = '" + plannifiercour_idcour.getText() + "'";

                        statement = connect.createStatement();
                        statement.executeUpdate(deletecour);

                    }

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();


                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clearcourplaifie(ActionEvent event) {
         plannifiercour_idcour.setText("");
        plannifiercour_nomcour.getSelectionModel().clearSelection();
        plannifiercour_salle.getSelectionModel().clearSelection();
        plannifiercour_description.setText("");
        plannifiercour_coach.setText("");
        plannifiercour_date.setValue(null);
        plannifiercour_status.getSelectionModel().clearSelection();

    }

    
    
    
    
    
    
    
    
    
    
    @FXML
     void switchForm(ActionEvent event) {

        //interface1
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            modifierPlan_form.setVisible(false);
            availableCour_form.setVisible(false);
            plannifiercour_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            availableCour_btn.setStyle("-fx-background-color:transparent");
            plannifier_btn.setStyle("-fx-background-color:transparent");
        } //interface2
        else if (event.getSource() == availableCour_btn) {
            dashboard_form.setVisible(false);
            modifierPlan_form.setVisible(false);
            availableCour_form.setVisible(true);
            plannifiercour_form.setVisible(false);

            availableCour_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            plannifier_btn.setStyle("-fx-background-color:transparent");

//          
        } else if (event.getSource() == plannifier_btn) {
            dashboard_form.setVisible(false);
            modifierPlan_form.setVisible(false);
            availableCour_form.setVisible(false);
            plannifiercour_form.setVisible(true);

            plannifiercour_form.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            availableCour_form.setStyle("-fx-background-color:transparent");

            
        }
    }
    
    
    
    
    
    
    
    

    public void emptyFields() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        

    }

    @FXML
    public void resNomcourList() {

        String listCourse = "SELECT * FROM availablecour";

        connect = MyConnection.connectDb();

        try {

            ObservableList listC = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(listCourse);
            result = prepare.executeQuery();

            while (result.next()) {
                listC.add(result.getString("nomCour"));
            }
            listcour_cour.setItems(listC);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void resStatusList() {

        List<String> StatusL = new ArrayList<>();

        for (String data : StatusList) {
            StatusL.add(data);
        }

        ObservableList liststatus = FXCollections.observableArrayList(StatusL);
        listcour_status.setItems(liststatus);

    }

   

  
  

}
