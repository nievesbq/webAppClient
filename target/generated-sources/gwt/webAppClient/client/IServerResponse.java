package webAppClient.client;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 5/12/13
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
import java.util.List;

public interface IServerResponse {
    public int getNextSeq();
    public void setNextSeq(int nextSeq);
    public List<IChatMessage> getMessages();
    public void setMessages(List<IChatMessage> messages);
}
