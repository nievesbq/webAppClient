package webAppClient.client;

import com.google.web.bindery.autobean.shared.AutoBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 9/12/13
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */
public interface IResponse {
    @AutoBean.PropertyName(value="messages")
    List<IChatMessage> getMessages();
    @AutoBean.PropertyName(value="nextSeq")
    int getNextSeq();

    @AutoBean.PropertyName(value="messages")
    void setMessages(List<IChatMessage> messages);
    @AutoBean.PropertyName(value="nextSeq")
    void setNextSeq(int nextSeq);
}
