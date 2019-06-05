package net.wanho.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sf;
    private static ThreadLocal<SqlSession> threadLocal;

    static {
        threadLocal = new ThreadLocal<SqlSession>();
        InputStream is = MybatisUtils.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        sf = new SqlSessionFactoryBuilder().build(is);
    }

    public static SqlSession getSession(){
        SqlSession session = threadLocal.get();
        if(session == null){
            session = sf.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public static void closeSession(){
        SqlSession session = threadLocal.get();
        if (session != null) {
            session.close();
            threadLocal.remove();
        }
    }



}
