package cn.com.servletBMS.modle;

public class User {
    String user;
    String paw;
    String confirmpaw;

    public String getConfirmpaw() {
        return confirmpaw;
    }

    public void setConfirmpaw(String confirmpaw) {
        this.confirmpaw = confirmpaw;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPaw() {
        return paw;
    }

    public void setPaw(String paw) {
        this.paw = paw;
    }
}
