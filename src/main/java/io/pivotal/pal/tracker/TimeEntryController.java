package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class TimeEntryController {

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

    }

    public ResponseEntity create(TimeEntry timeEntryToCreate) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<TimeEntry> read(long timeEntryId) {
        TimeEntry te = new TimeEntry();
        return new ResponseEntity<>(te, HttpStatus.OK);
    }

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> lte = new ArrayList<TimeEntry>();
        return new ResponseEntity<>(lte, HttpStatus.OK);
    }

    public ResponseEntity update(long timeEntryId, TimeEntry expected) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
