// User.java
public class User {
    private String username;
    private String plan; // "Free" or "Premium"

    public User() {}

    public User(String username, String plan) {
        this.username = username;
        this.plan = plan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", plan='" + plan + '\'' + '}';
    }
}
