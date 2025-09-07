package com.hartcircle.admin.entity;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdminID") // Match column name
    private Integer adminId;

    @Column(name = "F_Name")
    private String firstName;

    @Column(name = "L_Name")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "NIC",unique = true, nullable = false)
    private String nic;

    @Column(name = "TPNumber")
    private String tpNumber;

    @Column(name = "Password")
    private String password;

    @Lob
    @Column(name = "Image", columnDefinition = "LONGBLOB")
    private byte[] image;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    public String getTpNumber() {
        return tpNumber;
    }

    public void setTpNumber(String tpNumber) {
        this.tpNumber = tpNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
