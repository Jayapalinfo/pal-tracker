package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long, TimeEntry> timeEntries ;
    private static long lastId;

    public InMemoryTimeEntryRepository() {
        this.timeEntries = new HashMap<>();
        this.lastId = 0;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry){
        lastId = lastId+1;
        timeEntry.setId(lastId);
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id){

        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list(){
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry){
        if(timeEntries.containsKey(id)) {
            timeEntries.replace(id, timeEntry);
            timeEntry.setId(id);
            return timeEntry;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        timeEntries.remove(id);
    }

}
