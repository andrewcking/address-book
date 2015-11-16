package addressbook;

import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Andrew King (@AndrewCKing)
 */
public class MainWindowController {

    private AddressBook main;
    //reference fxml components
    @FXML
    TableView<Contact> tableView;
    @FXML
    TableColumn<Contact, String> firstNameColumn;
    @FXML
    TableColumn<Contact, String> lastNameColumn;
    @FXML
    Label firstNameLabel, middleNameLabel, lastNameLabel, homePhoneLabel, workPhoneLabel, homeAddressLabel, workAddressLabel, personalEmailLabel, workEmailLabel;

    //declare and retrieve the stage
    public void setMain(AddressBook main) {
        this.main = main;
        tableView.setItems(main.getContactData());
        tableView.setPlaceholder(new Label("Add A Contact!"));
    }

    //For contact details labels
    public void showDetails(Contact contact) {
        firstNameLabel.setText(contact.getFirstName());
        lastNameLabel.setText(contact.getLastName());
        middleNameLabel.setText(contact.getMiddleName());
        homePhoneLabel.setText(contact.getHomePhone());
        workPhoneLabel.setText(contact.getWorkPhone());
        homeAddressLabel.setText(contact.getHomeAddress());
        workAddressLabel.setText(contact.getWorkAddress());
        personalEmailLabel.setText(contact.getPersonalEmail());
        workEmailLabel.setText(contact.getWorkEmail());
    }

    @FXML
    public void actionNew() {
        //pass in null since it takes an arguement to handle edit state
        main.newContactWindow(null);
    }

    @FXML
    public void actionEdit() {
        //save selected contact into contact
        Contact contact = tableView.getSelectionModel().getSelectedItem();
        //when ok is clicked update the contact based on their index
        Boolean okClicked = main.newContactWindow(contact);

        if (okClicked) {
            //we have to refresh the table view in case of detail changes
            refreshView();
            //this is to refresh the contact info details
            showDetails(contact);
        }
    }

    @FXML
    public void actionDelete() {
        //javafx here we are getting the number of the contact in the observablelist
        int index = tableView.getSelectionModel().getSelectedIndex();
        //and removing it based on its number
        main.getContactData().remove(index);
    }

    @FXML
    public void actionExit() throws IOException {
        main.exit();
    }
    //we have to refresh the view on click because otherwise nothing would appear to change
    private void refreshView() {
        //update table
        tableView.setItems(null);
        tableView.layout();
        tableView.setItems(main.getContactData());
    }

    public void initialize() {

        //these are kinda weird - this is for retrieving column views for the contact list
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        //so get the item and use listner to post into list (I took some netbeans suggestions on this... not sure what they did).
        tableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Contact> observable, Contact oldvalue, Contact newValue) -> {
            showDetails(newValue);
        });
        
    }
}
