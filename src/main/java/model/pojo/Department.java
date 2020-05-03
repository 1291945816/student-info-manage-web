package model.pojo;

/**
 * @author: Hps
 * @date: 2020/4/20 21:52
 * @description:
 */
public class Department {
    private String name;
    private String admin;

    public Department(String name, String admin) {
        this.name = name;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }
}
