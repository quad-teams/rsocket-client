package team.quad.rsocket.server;

import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/businesses")
public class BusinessController {

  private final RSocketRequester rSocketRequester;

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Publisher<Business> feed() {
    return rSocketRequester
      .route("Business.Feed")
      .data("")
      .retrieveFlux(Business.class);
  }

  @GetMapping("/collect")
  public Publisher<Void> collect() {
    return rSocketRequester
      .route("Business.Collect")
      .data(new Business())
      .send();
  }
}
