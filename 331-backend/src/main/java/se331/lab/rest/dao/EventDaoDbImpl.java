package se331.lab.rest.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
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
    public List<Event> getEvents(Integer pageSize, Integer page) {
        List<Event> events = eventReposity.findAll();
        pageSize = pageSize == null ? events.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        List<Event> output = events.subList(firstIndex, firstIndex + pageSize);
        return output;
    }

    @Override
    public Event getEvent(Long id) {
        return eventReposity.findById(id).orElse(null);

    }
}

