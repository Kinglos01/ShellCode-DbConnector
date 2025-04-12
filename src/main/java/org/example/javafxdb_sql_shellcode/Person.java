package org.example.javafxdb_sql_shellcode;

public class Person {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;



    public Person(String name, String email, String phone, String address, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;

    }

    public void removePerson(String name, String email, String phone, String address, String password){
        if (this.name.equals(name) && this.email.equals(email) && this.phone.equals(phone) && this.address.equals(address) && this.password.equals(password) ) {
            this.name = null;
            this.email = null;
            this.phone = null;
            this.address = null;
            this.password = null;
        }
        else{System.out.println("Error: The information you entered does not match any known person");}
    }

    public void editPerson(String input, String editer){
        switch(input){
            case "Name":
                this.name = editer;
                break;
            case "Email":
                this.email = editer;
                break;
            case "Phone":
                this.phone = editer;
                break;
            case "Address":
                this.address = editer;
                break;
            case "Password":
                this.password = editer;
                break;
        }
        System.out.println(input + "changed to " + editer + " in person class");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
