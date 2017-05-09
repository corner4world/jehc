package jehc.xtmodules.xtcore.base.moredatasources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
/**
 * 重新Mapper
 * 用于测试 即使用Mybatis多数据源操作 
 * @Qualifier("sqlSessionFactory.overrideMapper")与datasources.xml中<bean id="sqlSessionFactory.overrideMapper" class="org.mybatis.spring.SqlSessionFactoryBean">必须一致
 * 采用继承BaseMDS
 * @author 邓纯杰
 *
 */
@Repository("mBaseDataSourcesTest")
public class MBaseDataSourcesTest extends BaseMDS{
	@Autowired
	public void setSqlSessionFactory(@Qualifier("sqlSessionFactory.overrideMapper") SqlSessionFactory sqlMapClient) {
		super.setSqlSessionFactory(sqlMapClient);
	}
}
