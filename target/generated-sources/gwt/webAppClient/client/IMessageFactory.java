package webAppClient.client;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 9/12/13
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */
public interface IMessageFactory extends AutoBeanFactory {
    AutoBean<IChatMessage> message(ChatMessage message);
}
