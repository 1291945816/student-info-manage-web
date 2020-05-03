package model.pojo;

/**
 * @author: 蒙素靓
 * @date: 2020/05/03 20:58
 * @description: 选课类
 */
public class Cmenu {
    private String sno;
    private String cno;
    private String cnum;
    private Double cgrade;
    private Double egrade;
    private Double tgrade;

    public Cmenu(String sno, String cno, String cnum, Double cgrade, Double egrade, Double tgrade){
        this.sno = sno;
        this.cno = cno;
        this.cnum = cnum;
        this.cgrade = cgrade;
        this.egrade = egrade;
        this.tgrade = tgrade;
    }

    public Cmenu(){

    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public Double getCgrade() {
        return cgrade;
    }

    public void setCgrade(Double cgrade) {
        this.cgrade = cgrade;
    }

    public Double getEgrade() {
        return egrade;
    }

    public void setEgrade(Double egrade) {
        this.egrade = egrade;
    }

    public Double getTgrade() {
        return tgrade;
    }

    public void setTgrade(Double tgrade) {
        this.tgrade = tgrade;
    }

    @Override
    public String toString(){
        return "Cmenu{" +
                "sno='" + sno + '\'' +
                ", cno='" + cno + '\'' +
                ", cnum='" + cnum + '\'' +
                ", cgrade=" + cgrade +
                ", egrade=" + egrade +
                ", tgrade=" + tgrade +
                '}';
    }
}
