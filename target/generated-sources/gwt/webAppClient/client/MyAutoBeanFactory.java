package webAppClient.client;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 5/12/13
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 */
public interface MyAutoBeanFactory extends AutoBeanFactory {
    public AutoBean<Item> makeMyBean();
}