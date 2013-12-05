package webAppClient.client;

/**
 * Created with IntelliJ IDEA.
 * User: montero
 * Date: 5/12/13
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface ServerResponseAutoBeanFactory extends AutoBeanFactory {
    public AutoBean<IServerResponse> serverResponse();
    public AutoBean<IServerResponse> serverResponse(IServerResponse toWrap);
}
