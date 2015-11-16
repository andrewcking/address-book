package addressbook;

import java.io.Serializable;

/**
 *
 * @author Andrew King (@AndrewCKing)
 */
public class Contact implements Serializable{

    private String firstName;
    private String middleName;
    private String lastName;
    private String homePhone;
    private String workPhone;
    private String homeAddress;
    private String workAddress;
    private String personalEmail;
    private String workEmail;

    //contact format

    public Contact(String firstName, String middleName, String lastName,
            String homePhone, String workPhone,
            String homeAddress, String workAddress,
            String personalEmail, String workEmail) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

        this.homePhone = homePhone;
        this.workPhone = workPhone;

        this.homeAddress = homeAddress;
        this.workAddress = workAddress;

        this.personalEmail = personalEmail;
        this.workEmail = workEmail;
    }

    //the getters

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    //the setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }
    public static final long serialVersionUID = 0L;
    
    //Provide tostring for console in addressbook method of addressbook.java
    @Override
    public String toString() {
    String result = firstName +" "+lastName;   
    return result;        
    }

}
