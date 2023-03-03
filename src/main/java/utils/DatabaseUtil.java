package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DatabaseUtil {
    public static SqlSession getSqlSession() throws IOException{
        SqlSession sqlSession;
        //获取配置的资源文件

            Reader reader= Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory  factory=new SqlSessionFactoryBuilder().build(reader);
            //sqlsession就能够执行配置文件的sql语句
           sqlSession= factory.openSession();



        return sqlSession;
    }
}
