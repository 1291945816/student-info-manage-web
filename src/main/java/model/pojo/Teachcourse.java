package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/05/04 16:08
 * @description: 提供教师授课的信息
 */
public class Teachcourse {
    private String cno;
    private String tno;
    private int cnum;

    public Teachcourse(String cno, String tno, int cnum){
        this.cno = cno;
        this.tno = tno;
        this.cnum = cnum;
    }

    public Teachcourse(){

    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public int getCnum() {
        return cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }

    @Override
    public String toString() {
        return "Teachcourse{" +
                "cno='" + cno + '\'' +
                ", tno='" + tno + '\'' +
                ", cnum=" + cnum +
                '}';
    }
}
