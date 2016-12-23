package team.t9001.saad.model;

/**
 * desc:团队
 * Created by huangzhe on 2016/12/18.
 */
public class Team {
    private int teamId;
    private String teamName;
    private String leader;
    /** 成员 */
    private String members;
    /** 参与团队的团队编号，用英文逗号分隔 */
    private String partTeam;
    private int status;

    public Team() {
        status = 1;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", leader='" + leader + '\'' +
                ", members='" + members + '\'' +
                ", partTeam='" + partTeam + '\'' +
                ", status=" + status +
                '}';
    }

    public int getTeamId() {

        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getPartTeam() {
        return partTeam;
    }

    public void setPartTeam(String partTeam) {
        this.partTeam = partTeam;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
