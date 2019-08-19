package team.quad.rsocket.server;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/businesses")
public class BusinessController {

  @Autowired
  private RSocketRequester rSocketRequester;

  @GetMapping(value = "/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Publisher<Business> post() {
    return rSocketRequester
      .route("feedBusiness")
      .data(new Business())
      .retrieveFlux(Business.class);
  }

  @GetMapping("/collect")
  public Mono<Void> get() {
    return rSocketRequester
      .route("collectBusiness")
      .data(new Business())
      .send();
  }
}
