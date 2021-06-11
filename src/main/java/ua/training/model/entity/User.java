package ua.training.model.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 1460590099577790342L;

    private Long id;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private Role role;

    private UserStatus userStatus;

    List<Account> accountList = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", userStatus=" + userStatus +
                ", accountList=" + accountList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }


    public static class Builder{

        private User user;

        public Builder()
        {
            user =  new User();
        }

        public Builder withId(long id){
            user.setId(id);
            return this;
        }

        public Builder withLogin(String login){
            user.setLogin(login);
            return this;
        }

        public Builder withPassword(String password){
            user.setPassword(password);
            return this;
        }

        public Builder withFirstName(String firstName){
            user.setFirstName(firstName);
            return this;
        }

        public Builder withLastName(String lastName){
            user.setLastName(lastName);
            return this;
        }

        public Builder withRole(Role role) {
            user.setRole(role);
            return this;
        }

        public Builder withUserStatus(UserStatus userStatus){
            user.setUserStatus(userStatus);
            return this;
        }

        public Builder withAccountList(List<Account> accountList){
            user.setAccountList(accountList);
            return this;
        }

        public User build(){
            return user;
        }
    }

}
