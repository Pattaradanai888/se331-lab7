package se331.lab.rest.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Event;
import se331.lab.rest.repository.EventRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class EventDaoDbImpl implements EventDao {
    final EventRepository eventReposity;

    @Override
    public Integer getEventSize() {
        return Math.toIntExact(eventReposity.count());
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        return eventReposity.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Event getEvent(Long id) {
        return eventReposity.findById(id).orElse(null);

    }
}

