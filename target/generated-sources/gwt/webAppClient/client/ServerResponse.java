package webAppClient.client;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 9/12/13
 * Time: 10:03
 * To change this template use File | Settings | File Templates.
 */
public class ServerResponse implements IServerResponse{

    private int nextSeq;
    private List<Item> itemList;


    public int getNextSeq() {
        return nextSeq;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setNextSeq(int nextSeq) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Item> getMessages() {
        return itemList;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setMessages(List<Item> messages) {
        itemList=messages;
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
