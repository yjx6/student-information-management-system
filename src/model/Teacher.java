package model;

public class Teacher {
    private String id;
    private String name;
    private String sex;
    private String academic;
    private String phone;
    private String facial;
    private String manage_class;

    public Teacher() {
    }

    public Teacher(String id, String name, String sex, String academic, String phone, String facial, String manage_class) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.academic = academic;
        this.phone = phone;
        this.facial = facial;
        this.manage_class = manage_class;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacial() {
        return facial;
    }

    public void setFacial(String facial) {
        this.facial = facial;
    }

    public String getManage_class() {
        return manage_class;
    }

    public void setManage_class(String manage_class) {
        this.manage_class = manage_class;
    }
}
