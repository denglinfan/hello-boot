package com.example.helloboot.binlogListener;

import com.example.helloboot.binlogListener.binlog.BinlogEventV4;
import com.example.helloboot.binlogListener.binlog.BinlogEventV4Header;
import com.example.helloboot.binlogListener.binlog.impl.event.AbstractRowEvent;
import com.example.helloboot.binlogListener.binlog.impl.event.QueryEvent;
import com.example.helloboot.binlogListener.binlog.impl.event.TableMapEvent;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

public class LogEvent implements Serializable {

    /**
     * 只针对delete、insert、update事件
     */
    private static final long serialVersionUID = 5503152746318421290L;

    private String eventId = null;//事件唯一标识
    private String databaseName = null;
    private String tableName = null;
    private Integer eventType = null;//时间类型
    private Long timeStamp = null;//事件发生的时间戳[MYSQL服务器时间]
    private Long timeStampRecepite = null;//服务接收到的时间戳[CDC执行的时间戳]
    private String binlogName = null;//binlog文件名称
    private Long position = null;
    private Long nextPosition = null;
    private Long serverId = null;
    //after,before标识变化前后所在行的所有数据（columnName：columnValue)
    private Map<String, String> before = null;
    private Map<String, String> after = null;

    public LogEvent() {
    }

    public LogEvent(final QueryEvent qe, String databaseName, String tableName) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.init(qe);
    }

    public LogEvent(final AbstractRowEvent re){
        this.init(re);
        TableMapEvent tableMapEvent = new TableMapEvent();//TODO 存在问题
        this.databaseName = tableMapEvent.getDatabaseName().toString();
        this.tableName = tableMapEvent.getTableName().toString();
    }

    private void init(final BinlogEventV4 be){
        this.eventId = UUID.randomUUID().toString();
        BinlogEventV4Header header = be.getHeader();
        this.binlogName = header.getBinlogFileName();
        this.position = header.getPosition();
        this.nextPosition = header.getNextPosition();
        this.timeStamp = header.getTimeStamp();
        this.timeStampRecepite = header.getTimeStampOfReceipt();
        this.serverId = header.getServerId();
        this.eventType = header.getEventType();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{eventIf:").append(eventId);
        builder.append(",databaseName:").append(databaseName);
        builder.append(",tableName:").append(tableName);
        builder.append(",eventType:").append(eventType);
        builder.append(",timeStamp:").append(timeStamp);
        builder.append(",timeStampRecepite:").append(timeStampRecepite);
        builder.append(",binlogName:").append(binlogName);
        builder.append(",position:").append(position);
        builder.append(",nextPosition:").append(nextPosition);
        builder.append(",serverId:").append(serverId);
        builder.append(",before:").append(before);
        builder.append(",after:").append(after).append(" }");
        return builder.toString();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getTimeStampRecepite() {
        return timeStampRecepite;
    }

    public void setTimeStampRecepite(Long timeStampRecepite) {
        this.timeStampRecepite = timeStampRecepite;
    }

    public String getBinlogName() {
        return binlogName;
    }

    public void setBinlogName(String binlogName) {
        this.binlogName = binlogName;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Long nextPosition) {
        this.nextPosition = nextPosition;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Map<String, String> getBefore() {
        return before;
    }

    public void setBefore(Map<String, String> before) {
        this.before = before;
    }

    public Map<String, String> getAfter() {
        return after;
    }

    public void setAfter(Map<String, String> after) {
        this.after = after;
    }
}
