package net.wanho.pojo;

public class Student {
    private Integer id;
    private String username;
    private Integer age;
    private String gender;
    private String address1;

    public Student() {
    }

    public Student(Integer id, String username, Integer age, String gender) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.gender = gender;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
