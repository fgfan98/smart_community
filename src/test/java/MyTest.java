import com.gigsider.dao.MytestDao;
import com.gigsider.po.Mytest;
import com.gigsider.service.MytestService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {

    //test spring
    @Test
    public void run1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MytestService mytestService = ac.getBean(MytestService.class);
        mytestService.findAll();
    }

    //test mybatis
    @Test
    public void run2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MytestDao mytestDao = sqlSession.getMapper(MytestDao.class);
        List<Mytest> list = mytestDao.findAll();
        for (Mytest mytest : list)
            System.out.println(mytest);
        sqlSession.close();
        inputStream.close();
    }

}
