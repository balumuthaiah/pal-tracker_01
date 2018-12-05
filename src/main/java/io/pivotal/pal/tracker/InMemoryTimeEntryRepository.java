package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository {
//    public void create(TimeEntry te) {
//        System.out.println("Test");
//    }

    public TimeEntry create(TimeEntry te) {
        return te;
    }

    public TimeEntry find (long id) {
        TimeEntry te = new TimeEntry(1, 1, LocalDate.parse("2017-01-08"), 1);
        return te;
    }

    public TimeEntry update (long id, TimeEntry te) {
        return te;
    }

    public List<TimeEntry> list () {
        List<TimeEntry> lte = new ArrayList<>();
        return lte;
    }


    public void delete(long id) {

    }
}
