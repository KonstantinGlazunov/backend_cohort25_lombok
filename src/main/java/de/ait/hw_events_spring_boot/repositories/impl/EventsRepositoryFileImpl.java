package de.ait.hw_events_spring_boot.repositories.impl;

import de.ait.hw_events_spring_boot.models.Event;
import de.ait.hw_events_spring_boot.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventsRepositoryFileImpl implements EventsRepository {
    private final String fileName;

    public EventsRepositoryFileImpl(@Value("${events.file-name}") String fileName) {
        this.fileName = fileName;
    }


    @Override
    public Event findByID(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            return reader.lines()
                    .map(line -> line.split("#"))
                    .map(parsed -> new Event (parsed[0], parsed[1]))
                    .collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + e);
        } catch (IOException e) {
            throw new RuntimeException("Troubles with file " + e);
        }
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(event.getEventName() + "#" + event.getEventDescription());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Troubles with file " + e);
        }

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Event model) {

    }

    @Override
    public Event findByEventName(String request) {
        return null;
    }
}
