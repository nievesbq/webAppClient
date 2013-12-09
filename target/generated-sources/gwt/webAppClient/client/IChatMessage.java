package webAppClient.client;

import com.google.web.bindery.autobean.shared.AutoBean;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 5/12/13
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public interface IChatMessage {
    @AutoBean.PropertyName(value="message")
    String getMessage();
    @AutoBean.PropertyName(value="message")
    void setMessage(String message);

    @AutoBean.PropertyName(value="nick")
    String getNick();
    @AutoBean.PropertyName(value="nick")
    void setNick(String nick);

}
