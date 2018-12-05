package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ResponseBody
public class TimeEntryController {
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository=timeEntryRepository;

    }

    @PutMapping(path ="/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable("timeEntryId") long timeEntryId,@RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, expected);
        if (null !=timeEntry )
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("timeEntryId") long timeEntryId) {
        this.timeEntryRepository.delete(timeEntryId);
        return  new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(path = "/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(this.timeEntryRepository.list(), HttpStatus.OK);
    }

    @PostMapping(path = "/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        return new ResponseEntity<TimeEntry>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping(path = "/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable("timeEntryId")long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if (null !=timeEntry )
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
    }
}
