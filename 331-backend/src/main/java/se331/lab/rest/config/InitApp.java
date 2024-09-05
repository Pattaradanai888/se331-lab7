package se331.lab.rest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventReposity;
    @Override
    public void onApplicationEvent (ApplicationReadyEvent applicationReadyEvent) {
        eventReposity.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petAllowed(false)
                .organizer("CAMT").build());
        eventReposity.save(Event.builder()
                .category("Academic")
                .title("Commencement Exam")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00 am.- 4.00 pm.")
                .petAllowed(false)
                .organizer("CMU").build());
        eventReposity.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping river")
                .date("21th Nov")
                .time("8.00 - 10.00 pm.")
                .petAllowed(false)
                .organizer("Chiang Mai").build());
        eventReposity.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00 am.- 6.00 pm.")
                .petAllowed(false)
                .organizer("Chiang Mai Municipality").build());
    }
}
