package br.dev.multicode.api.interceptor;

import io.quarkus.arc.Priority;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.jboss.logging.Logger;

@LogEvent
@Priority(-1)
@Interceptor
public class LogEventInterceptor {

  private static final Logger log = Logger.getLogger(LogEventInterceptor.class);

  static List<Event> events = new ArrayList<>();

  @AroundInvoke
  public Object logEvent(InvocationContext ctx) throws Exception
  {
    final Event event = new Event(ctx.getMethod().getName(), Arrays.deepToString(ctx.getParameters()));

    events.add(event);
    log.info("LogEventInterceptor::Event::".concat(event.getMethodName())
        .concat(" - "
        .concat(event.getParams())));

    return ctx.proceed();
  }

}
