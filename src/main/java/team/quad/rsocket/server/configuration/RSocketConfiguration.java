package team.quad.rsocket.server.configuration;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.MetadataExtractor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.net.InetSocketAddress;

@Configuration
public class RSocketConfiguration {

  @Bean
  RSocket rSocket() {
    return RSocketFactory.connect()
      .mimeType(MetadataExtractor.ROUTING.toString(), MimeTypeUtils.APPLICATION_JSON_VALUE)
      .frameDecoder(PayloadDecoder.ZERO_COPY)
      .transport(TcpClientTransport.create(new InetSocketAddress(7000)))
      .start()
      .block();
  }

  @Bean
  RSocketRequester rSocketRequester(RSocketStrategies rSocketStrategies) {
    return RSocketRequester.wrap(rSocket(), MimeTypeUtils.APPLICATION_JSON, MetadataExtractor.ROUTING, rSocketStrategies);
  }
}
