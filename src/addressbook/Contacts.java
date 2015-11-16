package addressbook;

import java.io.*;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author Andrew King (@AndrewCKing)
 */
public class Contacts {

    public static void save(Object[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("contacts_backup.ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(data);
        } catch (IOException ioe) {
            System.out.println("Problem saving contacts");
        } finally {
             //after finished writing the data into backup replace the existing atomically... booyah
            java.nio.file.Files.move(Paths.get("contacts_backup.ser"), Paths.get("contacts.ser"), REPLACE_EXISTING, ATOMIC_MOVE);
        }
    }

    public static Object[] load() {
        Object[] contacts = new Object[0];
        try (
                FileInputStream fis = new FileInputStream("contacts.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            contacts = (Object[]) ois.readObject();
        } catch (IOException ioe) {
            System.out.println("Error reading file");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading contacts");
        }
        return contacts;
    }
}
