package addressbook;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

/**
 *
 * @author Andrew King (@AndrewCKing)
 */
public class AddressBook extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainWindow();
    }

    public void mainWindow() {
        //requries try-catch
        try {
            //get the fxml doc generated in javafx scene-builder
            FXMLLoader loader = new FXMLLoader(AddressBook.class.getResource("MainWindowView.fxml"));
            AnchorPane pane = loader.load();
            //Create new scene
            Scene scene = new Scene(pane);
            //get the CSS doc
            scene.getStylesheets().add(getClass().getResource("addressbook.css").toExternalForm());
            //Add connection to main window controller
            MainWindowController controller = loader.getController();
            controller.setMain(this);
            //Set constraits to the primary stage
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
        }
    }

    public boolean newContactWindow(Contact contact) {
        try {
            //get the fxml document
            FXMLLoader loader = new FXMLLoader(AddressBook.class.getResource("NewContactView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            Stage stage = new Stage();
            //get the CSS doc for the new contact window
            scene.getStylesheets().add(getClass().getResource("newcontactview.css").toExternalForm());
            NewContactController controller = loader.getController();
            //contact has to be passed in via new contact controller to main window controller
            controller.setMain(this, stage, contact);
            //New Contact Window Constraints
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();

            return controller.okClicked();
        } catch (Exception e) {
            return false;
        }
    }
    //observable list from new javafx collections

    private ObservableList<Contact> contactData = FXCollections.observableArrayList();

    public ObservableList<Contact> getContactData() {
        return contactData;
    }

    public AddressBook() {
        Object[] reloadedContacts = (Object[]) Contacts.load();

        for (Object reloaded : reloadedContacts) {
            Contact con = (Contact) reloaded;
            System.out.println(con); // result from tostring
            if (con != null) {
                contactData.add(con);
            }
        }
        System.out.println("Loaded");
    }

    public void exit() throws IOException {
        //K... this got complicated Observable list is not serializable
        //so I loop through the data and save it into an object and then serialize that
        Object[] data = new Object[contactData.size() + 1];
        int counter = 1;
        for (Contact contact : contactData) {
            data[counter] = contact;
            counter++;
        }
        Contacts.save(data);
        //exit the program
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
