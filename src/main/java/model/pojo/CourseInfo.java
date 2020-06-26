package model.pojo;

/**
 * @author: Hps
 * @date: 2020/6/26 13:34
 * @description:
 */
public class CourseInfo {
    private String cno;
    private String ccode;
    private String cname;
    private String tname;
    private Double cnum;

    public CourseInfo() {
    }

    public CourseInfo(String cno, String ccode, String cname, String tname, Double cnum) {
        this.cno = cno;
        this.ccode = ccode;
        this.cname = cname;
        this.tname = tname;
        this.cnum = cnum;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Double getCnum() {
        return cnum;
    }

    public void setCnum(Double cnum) {
        this.cnum = cnum;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "cno='" + cno + '\'' +
                ", ccode='" + ccode + '\'' +
                ", cname='" + cname + '\'' +
                ", tname='" + tname + '\'' +
                ", cnum=" + cnum +
                '}';
    }
}
