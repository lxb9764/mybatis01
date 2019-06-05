import net.wanho.mapper.StudentMapper;
import net.wanho.pojo.Student;
import net.wanho.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * 测试Mybatis的使用
 */
public class TestMybatis {
    private SqlSession sqlSession;
    private StudentMapper studentMapper;
    @Before
    public void before(){
        sqlSession = MybatisUtils.getSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @Test
    public void testAddStu(){
        Student student = new Student(null,"lisi",12,"nv");
        SqlSession session = MybatisUtils.getSession();
        session.update("net.wanho.mapper.StudentMapper.addStu", student);
        session.commit();
        MybatisUtils.closeSession();
    }

    @Test
    public void testDeleteStu(){
        sqlSession.update("net.wanho.mapper.StudentMapper.deleteStu",5);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateStu(){
        Student student = new Student();
        student.setId(1);
        student.setUsername("哈哈哈哈哈");
        sqlSession.update("net.wanho.mapper.StudentMapper.updateStu",student);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testGetAllStus(){
        List<Student> allStus = studentMapper.getAllStus();
        System.out.println(allStus);
        MybatisUtils.closeSession();
    }

    @Test
    public void testGetStuById(){
        Student student = studentMapper.getStuById(2);
        System.out.println(student);
        MybatisUtils.closeSession();
    }
    /**
     * 模糊查询
     * 常用写法 concat
     *  SELECT * FROM student WHERE username LIKE concat("%",#{username},"%")
     * 简单数据类型：
     *  后台的sql:SELECT * FROM student WHERE username LIKE #{username}
     *  #：会将"%li%"全部带过来，但是这种写法不常用，其中#的大括号中代表占位符
     *  $: 字符串拼接
     *  前台：List<Student> students = studentMapper.getStusByName("%li%");
     *
     *  后台的sql:SELECT * FROM student WHERE username LIKE "%${value}%":前台可以直接传名字
     *  前台：List<Student> students = studentMapper.getStusByName("li");
     *  复杂数据类型
     * */
    @Test
    public void testGetStusByName(){
//        List<Student> students = studentMapper.getStusByName("%li%");
//        List<Student> students = studentMapper.getStusByName("li");
        List<Student> students = studentMapper.getStusByName("li");

        System.out.println(students);
        MybatisUtils.closeSession();
    }

    @Test
    public void testGetStusByName2(){
        Student student = new Student();
        student.setUsername("li");
        List<Student> students = studentMapper.getStusByName2(student);
        System.out.println(students);
        MybatisUtils.closeSession();
    }

    /**
     * 返回主键:
     *      方式1
     *          <insert id="addStuReturnKey" useGeneratedKeys="true" keyProperty="id">
     *            INSERT INTO student VALUES (NULL ,#{username},#{age},#{gender})
     *          </insert>
     *      方式2
     *          <insert id="addStuReturnKey1">
     *            <selectKey keyProperty="id" order="AFTER" resultType="int">
     *            SELECT last_insert_id()
     *            </selectKey>
     *            INSERT INTO student VALUES (NULL ,#{username},#{age},#{gender})
     *          </insert>
     */
    @Test
    public void testaddStuReturnKey(){
        Student student = new Student(null,"lisi",12,"nv");
        studentMapper = sqlSession.getMapper(StudentMapper.class);
        studentMapper.addStuReturnKey(student);
        sqlSession.commit();
        System.out.println(student.getId());
        MybatisUtils.closeSession();
    }

    @Test
    public void testaddStuReturnKey1(){
        Student student = new Student(null,"lisi",12,"nv");
        studentMapper = sqlSession.getMapper(StudentMapper.class);
        studentMapper.addStuReturnKey1(student);
        sqlSession.commit();
        System.out.println(student.getId());
        MybatisUtils.closeSession();
    }

    /**
     * 多个参数传值
     * 简单类型传值
     *      1、param1+param2
     *      2、在原方法的参数上加@Param注解，并取名
     * 复杂类型+简单类型
     *      1、在原方法的参数上加@Param注解，并取名；
     *          复杂类型用别名.属性名取值
     *          简单类型用别名直接取值
     */
    @Test
    public void testUpdateStu1(){
        studentMapper = sqlSession.getMapper(StudentMapper.class);
        studentMapper.updateStu1("aaa",12);
        sqlSession.commit();
        MybatisUtils.closeSession();
    }

    @Test
    public void testUpdateStu2(){
        studentMapper = sqlSession.getMapper(StudentMapper.class);
        studentMapper.updateStu2("bbb",12);
        sqlSession.commit();
        MybatisUtils.closeSession();
    }

    @Test
    public void testUpdateStu3(){
        studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setUsername("ccc");
        studentMapper.updateStu3(student,12);
        sqlSession.commit();
        MybatisUtils.closeSession();
    }

    /**
     * 基于注解的sql
     */
    @Test
    public void testUpdateStu4(){
        studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setUsername("ddd");
        studentMapper.updateStu4(student,12);
        sqlSession.commit();
        MybatisUtils.closeSession();
    }
}
