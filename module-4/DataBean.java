package mendez.model;

import java.io.Serializable;

/**
 * DataBean holds record data for JSP display.
 * This class follows JavaBean standards and implements Serializable.
 */
public class DataBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // ===== Fields  =====
    private int recordId;
    private String firstName;
    private String lastName;
    private String email;
    private String department;

    // ===== No-Argument Constructor =====
    public DataBean() {
    }

    // ===== Getters and Setters =====
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
