package jehc.xtmodules.xtcore.base.moredatasources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
/**
 * 重新Mapper
 * 用于测试 即使用Mybatis多数据源操作 
 * @Qualifier("SQLSESSIONFACTORY_TEST")与datasources.xml中<bean id="SQLSESSIONFACTORY_TEST" class="org.mybatis.spring.SqlSessionFactoryBean">必须一致
 * 采用继承BaseMDS
 * @author 邓纯杰
 *
 */
public class MBaseDaoImplTest extends MBaseDao{
	@Autowired
	public void setSqlSessionFactory(@Qualifier(SQLSESSIONFACTORY_TEST) SqlSessionFactory sqlMapClient) {
		super.setSqlSessionFactory(sqlMapClient);
	}
}
