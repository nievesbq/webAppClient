package webAppClient.client;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 5/12/13
 * Time: 9:25
 * To change this template use File | Settings | File Templates.
 */
public class Item {

    private String user;
    private String msg;


    public Item(String user, String msg) {
        this.user = user;
        this.msg=msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



}
