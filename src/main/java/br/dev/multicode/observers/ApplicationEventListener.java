package br.dev.multicode.observers;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ApplicationEventListener {

  private static final Logger log = Logger.getLogger(ApplicationEventListener.class);

  void onStart(@Observes StartupEvent event)
  {
    log.info("Application starting...");
  }

  void onSTop(@Observes ShutdownEvent event)
  {
    log.info("Application shutting down...");
  }

}
