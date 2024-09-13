package se331.lab.rest.dao;

import jakarta.annotation.PostConstruct;
import org.aspectj.weaver.ast.Or;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.repository.EventRepository;
import se331.lab.rest.repository.OrganizerRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual-or")
public class OrganizerDaoImpl implements OrganizerDao {
    private final OrganizerRepository organizerRepository;
    List<Organizer> organizerList;


    public OrganizerDaoImpl(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @PostConstruct
    public void init() {
        organizerList = new ArrayList<>();
        organizerList.add(Organizer.builder()
                .id(123L)
                .name("Fern Pollin")
                .address("Flora City")
                .build());
        organizerList.add(Organizer.builder()
                .id(456L)
                .name("Carey Wales")
                .address("Playa Del Carmen")
                .build());
        organizerList.add(Organizer.builder()
                .id(789L)
                .name("Dawg Dahd")
                .address("Woof Town")
                .build());
        organizerList.add(Organizer.builder()
                .id(1001L)
                .name("Kahn Opiner")
                .address("Tin City")
                .build());
        organizerList.add(Organizer.builder()
                .id(1002L)
                .name("Brody Kill")
                .address("Highway 50")
                .build());
    }

    @Override
    public Integer getOrganizerSize() {
        return organizerList.size();
    }

    @Override
    public Page<Organizer> getOrganizers(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizerList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page -1) * pageSize;
        return new
                PageImpl<Organizer>(organizerList.subList(firstIndex, firstIndex + pageSize), PageRequest.of(page,
                pageSize),organizerList.size());
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerList.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Organizer save(Organizer organizer){
        organizer.setId(organizerList.get(organizerList.size() - 1).getId());
        organizerList.add(organizer);
        return organizer;
    }
}
