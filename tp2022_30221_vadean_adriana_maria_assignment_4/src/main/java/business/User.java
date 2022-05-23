package business;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected String username;
    protected String password;
    public int id;
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

}
