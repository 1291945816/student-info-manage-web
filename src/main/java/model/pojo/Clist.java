package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/05/03 19:54
 * @description: 授课类
 */
public class Clist {
    private String cnum;
    private String cno;
    private Integer clstart_time;
    private Integer number;

    public Clist(String cnum, String cno, Integer clstart_time, Integer number){
        this.cnum = cnum;
        this.cno = cno;
        this.clstart_time = clstart_time;
        this.number = number;
    }

    public Clist(){

    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public Integer getClstart_time() {
        return clstart_time;
    }

    public void setClstart_time(Integer clstart_time) {
        this.clstart_time = clstart_time;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString(){
        return "Clist{" +
                "cnum='" + cnum + '\'' +
                ", cno='" + cno + '\'' +
                ", clstart_time=" + clstart_time +
                ", number=" + number +
                '}';
    }
}
