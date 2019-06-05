package net.wanho.mapper;

import net.wanho.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {
    void addStu(Student student);
    //安装free-mybatis插件的提示
    void deleteStu(Integer id);
    void updateStu(Student student);

    List<Student> getAllStus();

    Student getStuById(Integer id);
    //测试模糊查询
    List<Student> getStusByName(String username);
    List<Student> getStusByName2(Student student);
    //查询主键
    void addStuReturnKey(Student student);
    void addStuReturnKey1(Student student);

    //多个参数传值
    void updateStu1(String username,Integer id);
    void updateStu2(@Param("username") String username,@Param("id") Integer id);
    void updateStu3(@Param("stu") Student student,@Param("id") Integer id);

    @Update("UPDATE student SET username = #{stu.username} WHERE id = #{id}")
    void updateStu4(@Param("stu") Student student,@Param("id") Integer id);


}
