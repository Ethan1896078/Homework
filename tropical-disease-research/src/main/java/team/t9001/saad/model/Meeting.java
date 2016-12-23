package team.t9001.saad.model;

/**
 * desc:
 * Created by huangzhe on 2016/12/18.
 */
public class Meeting {
    private int meetingId;
    private String title;
    private String conveneTime;
    private String content;
    private int status;

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", title=" + title +
                ", conveneTime='" + conveneTime + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }

    public Meeting() {
        status = 1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }


    public String getConveneTime() {
        return conveneTime;
    }

    public void setConveneTime(String conveneTime) {
        this.conveneTime = conveneTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
