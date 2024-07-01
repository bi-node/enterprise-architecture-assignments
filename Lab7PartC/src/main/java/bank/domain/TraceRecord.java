package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class TraceRecord {
    @Id
    @GeneratedValue
    private long id;
    private String traceMsg;
    private LocalDateTime timeStamp;

    public TraceRecord() {
    }

    public TraceRecord(String traceMsg, LocalDateTime timeStamp) {
        this.traceMsg = traceMsg;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTraceMsg() {
        return traceMsg;
    }

    public void setTraceMsg(String traceMsg) {
        this.traceMsg = traceMsg;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
