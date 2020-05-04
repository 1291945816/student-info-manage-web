package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/05/04 14:23
 * @description: 提供课程计划信息
 */
public class Courseplan {
    private String cno;
    private String ccode;
    private String startdate;

    public Courseplan(String cno, String ccode, String startdate){
        this.cno = cno;
        this.ccode = ccode;
        this.startdate = startdate;
    }

    public Courseplan(){

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

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    @Override
    public String toString() {
        return "Courseplan{" +
                "cno='" + cno + '\'' +
                ", ccode='" + ccode + '\'' +
                ", startdate='" + startdate + '\'' +
                '}';
    }
}
