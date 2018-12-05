package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry() {

    }
    public void setId(long id) {
        this.id=id;
    }

    public long getId() {
        return id;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof TimeEntry)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        TimeEntry timeEntry = (TimeEntry) o;

        // Compare the data members and return accordingly
        return this.id == timeEntry.getId()
                && this.projectId == timeEntry.getProjectId()
                && this.userId == timeEntry.getUserId()
                && this.hours == timeEntry.getHours()
                && this.date.equals(timeEntry.getDate());
    }
}
