package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    TimeEntry timeEntry;
    List<TimeEntry> timeEntryList =  new ArrayList<>();


    public TimeEntry create(TimeEntry timeEntry) {
        this.timeEntry=timeEntry;
        this.timeEntry.setId(timeEntryList.size()+1);
        timeEntryList.add(timeEntry);
        //TimeEntry createdTimeEntry = (TimeEntry) (new TimeEntry(timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(),timeEntry.getHours()));
        return this.timeEntry;//createdTimeEntry;
    }

    public TimeEntry find(long timeEntryId) {
        TimeEntry respTimeEntry = null;
        for (Iterator<TimeEntry> iterator = this.timeEntryList.iterator(); iterator.hasNext(); ) {
            TimeEntry timeEntry = iterator.next();
            if (timeEntry.getId()==timeEntryId) {
                respTimeEntry=timeEntry;
            }
        }
        return respTimeEntry;
    }

    public List<TimeEntry> list() {
        return this.timeEntryList;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry respTimeEntry = null;
        int index = 0;
        for (Iterator<TimeEntry> iterator = this.timeEntryList.iterator(); iterator.hasNext(); ) {
            TimeEntry timeEntryIt = iterator.next();

            if (timeEntryIt.getId()==id) {
                timeEntry.setId(id);
                respTimeEntry=timeEntry;
                this.timeEntryList.set(index, timeEntry);
                break;
            }
            index ++;
        }
        return respTimeEntry;
    }

    public void delete(long id) {
        for (Iterator<TimeEntry> iterator = this.timeEntryList.iterator(); iterator.hasNext(); ) {
            TimeEntry timeEntry = iterator.next();
            if (timeEntry.getId()==id) {
                iterator.remove();
            }
        }
    }
}
