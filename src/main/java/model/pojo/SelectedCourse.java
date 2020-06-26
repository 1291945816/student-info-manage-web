package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/06/26 22:23
 * @description: 已选课程信息
 */
public class SelectedCourse {
    private String cno;
    private String cname;
    private String startdate;
    private Double credit;
    private String tname;

    public SelectedCourse() {
    }

    public SelectedCourse(String cno, String cname, String startdate, Double credit, String tname) {
        this.cno = cno;
        this.cname = cname;
        this.startdate = startdate;
        this.credit = credit;
        this.tname = tname;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "SelectedCourse{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", startdate='" + startdate + '\'' +
                ", credit=" + credit +
                ", tname='" + tname + '\'' +
                '}';
    }
}
