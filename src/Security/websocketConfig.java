package Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 * Created by chett_000 on 2017/06/12.
 */
//@Configuration
public class websocketConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

@Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
    messages.simpTypeMatchers(SimpMessageType.CONNECT).permitAll();
    }

        @Override
    protected boolean sameOriginDisabled(){
        return false;
    }
}
