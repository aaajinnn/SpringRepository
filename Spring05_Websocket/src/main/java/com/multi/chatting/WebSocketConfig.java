package com.multi.chatting;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration // xml�� �ƴϰ� �ڹٷ� �ϰڴ�
@EnableWebSocketMessageBroker // websocket���� ������ �۵��ȴ�
// implements WebSocketMessageBrokerConfigurer �ؾ���
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		// ������ : ws://localhost:9090/���ؽ�Ʈ/chat ==> Sockjs���� ������ �� ����ϴ� �ּ�
		// ���� �� ä��
		registry.addEndpoint("/chat")
		.withSockJS(); // ���� ���� ������������ ���
		
		// ê�� ������ ��������Ʈ ����
		registry.addEndpoint("/chatbot") 
		.withSockJS();
		//queue���� ���� Ŭ���̾�Ʈ�� �ĺ��� HandshakeHandler ����
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		// �ش� �ּҸ� �����ϴ� ������ Ŭ���̾�Ʈ���� �޽����� �����Ѵ�.
		registry.enableSimpleBroker("/topic","/queue");
		
		// Ŭ���̾�Ʈ�� ������ ���� �޽����� �޴� prefix(���ξ�)
		registry.setApplicationDestinationPrefixes("/app");
		// /app/chat => ���ξ� endpiont
	}

}
