package jehc.xtmodules.xtservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.db.DBMSMetaUtil;
import jehc.xtmodules.xtcore.util.db.DbInfo;
import jehc.xtmodules.xtcore.util.db.MapUtil;
import jehc.xtmodules.xtcore.util.generator.Gutil;
import jehc.xtmodules.xtdao.XtGeneratorDao;
import jehc.xtmodules.xtmodel.XtGenerator;
import jehc.xtmodules.xtmodel.XtGeneratorTableColumn;
import jehc.xtmodules.xtservice.XtGeneratorService;
/**
 * 代码生成器
 * @author邓纯杰
 *
 */
@Service("xtGeneratorService")
public class XtGeneratorServiceImpl extends BaseService implements XtGeneratorService {
	@Autowired
	private XtGeneratorDao xtGeneratorDao;
	/**
	 * 查询所有生成信息并分页
	 * @param condition
	 * @return
	 */
	public List<XtGenerator> getXtGeneratorListByCondition(Map<String, Object> condition){
		try {
			return xtGeneratorDao.getXtGeneratorListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	 * 生成代码
	 * @param xt_Generator
	 */
	public int addXtGenerator(XtGenerator xt_Generator){
		Gutil gutil = new Gutil();
		int result = 0;
		try {
			String table_name = xt_Generator.getXt_generator_tbname();
			String comment = xt_Generator.getXt_generator_tbcomment();
			List<XtGeneratorTableColumn> xt_Generator_Table_ColumnList = new ArrayList<XtGeneratorTableColumn>();
			DbInfo dbInfo = DBMSMetaUtil.excuteDB(table_name);
			List<Map<String, Object>> columns = dbInfo.getColumns();
			List<Map<String, Object>> columnsPrimary = MapUtil.convertKeyList2LowerCase(dbInfo.getColumnsPrimary());
			String pk=null;
			for(int i = 0; i < columnsPrimary.size(); i++){
				Map<String, Object> map = columnsPrimary.get(i);
				pk = map.get("column_name").toString();
				break;
			}
			if(!columns.isEmpty() && columns.size()>0){
				for(int i = 0; i < columns.size(); i++){
					Map<String, Object> map = columns.get(i);
					XtGeneratorTableColumn xt_Generator_Table_Column = new XtGeneratorTableColumn();
					xt_Generator_Table_Column.setCOLUMN_NAME(map.get("COLUMN_NAME").toString());
					xt_Generator_Table_Column.setCOLUMN_COMMENT(map.get("REMARKS").toString());
					/**
					xt_Generator_Table_Column.setDATA_TYPE(map.get("DATA_TYPE").toString());
					**/
					xt_Generator_Table_Column.setDATA_TYPE(map.get("TYPE_NAME").toString());
					xt_Generator_Table_Column.setIS_NULLABLE(map.get("IS_NULLABLE").toString());
					xt_Generator_Table_Column.setCHARACTER_MAXIMUM_LENGTH(map.get("COLUMN_SIZE").toString());
					if(null != pk && !"".equals(pk) &&pk.toLowerCase().equals(map.get("COLUMN_NAME").toString().toLowerCase())){
						xt_Generator_Table_Column.setCOLUMN_KEY("PRI");
					}
					xt_Generator_Table_ColumnList.add(xt_Generator_Table_Column);
				}
			}
			int i = gutil.createCode(xt_Generator_Table_ColumnList, xt_Generator);
			xt_Generator.setXt_userinfo_id(CommonUtils.getXtUid());
			xt_Generator.setXt_generator_time(CommonUtils.getSimpleDateFormat());
			if(i > 0){
				xt_Generator.setXt_generator_state("1");
				result = 1;
			}else{
				xt_Generator.setXt_generator_state("2");
				result = 0;
			}
			xt_Generator.setXt_generator_tbname(table_name);
			xt_Generator.setXt_generator_tbcomment(comment);
			result = xtGeneratorDao.addXtGenerator(xt_Generator);
		} catch (Exception e) {
			result = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param condition
	 */
	public int delXtGenerator(Map<String,Object> condition){
		int result = 0;
		try {
			result = xtGeneratorDao.delXtGenerator(condition);
		} catch (Exception e) {
			result = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return result;
	}
}
