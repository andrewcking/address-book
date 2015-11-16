package addressbook;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author Andrew King (@AndrewCKing)
 */
public class NewContactController {

    //java fx textfield type
    @FXML
    private TextField firstNameField, middleNameField, lastNameField, homePhoneField, workPhoneField, homeAddressField, workAddressField, personalEmailField, workEmailField;
    private Stage stage;
    private Contact contact;
    private AddressBook main;
    private Boolean okClicked = false;

    public void setMain(AddressBook main, Stage stage, Contact contact) {
        //pass in the stage so we can do things like close it etc
        this.main = main;
        this.stage = stage;
        this.contact = contact;
        if (contact != null) {
            addContactDetails();
        }
    }

    //This method will add the contact details to the new contact window for editing
    public void addContactDetails() {
        firstNameField.setText(contact.getFirstName());
        middleNameField.setText(contact.getMiddleName());
        lastNameField.setText(contact.getLastName());

        homePhoneField.setText(contact.getHomePhone());
        workPhoneField.setText(contact.getWorkPhone());

        homeAddressField.setText(contact.getHomeAddress());
        workAddressField.setText(contact.getWorkAddress());

        personalEmailField.setText(contact.getPersonalEmail());
        workEmailField.setText(contact.getWorkEmail());

    }

    //method for the ok button in the new contact window
    @FXML
    public void actionOk() {
        //we need to be able to handle the edit case so if it isn't null just update
        if (contact != null) {
            contact.setFirstName(firstNameField.getText());
            contact.setMiddleName(middleNameField.getText());
            contact.setLastName(lastNameField.getText());

            contact.setHomePhone(homePhoneField.getText());
            contact.setWorkPhone(workPhoneField.getText());
            contact.setHomeAddress(homeAddressField.getText());
            contact.setWorkAddress(workAddressField.getText());
            contact.setPersonalEmail(personalEmailField.getText());
            contact.setWorkEmail(workEmailField.getText());
            okClicked = true;
        } else {
            //otherwise just create a new contact (not editing)
            Contact theContact = new Contact(firstNameField.getText(), middleNameField.getText(), lastNameField.getText(), homePhoneField.getText(), workPhoneField.getText(), homeAddressField.getText(), workAddressField.getText(), personalEmailField.getText(), workEmailField.getText());
            main.getContactData().add(theContact);
        }
        //close the new contact window
        stage.close();
    }

    //for the cancel button in the new contact window
    public void actionCancel() {
        stage.close();
    }

    //Toggle ok click
    public boolean okClicked() {
        return okClicked;
    }

}
