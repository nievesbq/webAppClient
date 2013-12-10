package webAppClient.client.interfaces;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import webAppClient.client.ChatMessage;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 9/12/13
 * Time: 12:02
 * To change this template use File | Settings | File Templates.
 */
public interface IResponseFactory extends AutoBeanFactory {
    AutoBean<IResponse> response();

    AutoBean<IChatMessage> message(ChatMessage message);

}
