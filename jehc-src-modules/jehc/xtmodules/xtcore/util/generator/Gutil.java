package jehc.xtmodules.xtcore.util.generator;

import java.util.ArrayList;
import java.util.List;

import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtmodel.Xt_Generator;
import jehc.xtmodules.xtmodel.Xt_Generator_TableMany_To_One;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_ColumnMany_To_One;

public class Gutil {
	/**
     * 总生成代码
     * @return
     */
    public int createCode(List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnList,Xt_Generator xt_Generator){
    	GeneratorModel generatorModel = new GeneratorModel();
    	GeneratorDao generatorDao = new GeneratorDao();
    	GeneratorService generatorService = new GeneratorService();
    	GeneratorWeb generatorWeb = new GeneratorWeb();
    	GeneratorPage generatorPage = new GeneratorPage();
    	int i = 0;
    	try {
    		///////////////////////////////单表及主表生成开始/////////////////////////
    		//设置为当前为主表
    		xt_Generator.setIs_main_table(true);
    		//1实体层
    		generatorModel.createModel(xt_Generator_Table_ColumnList, xt_Generator);
    		//2数据层
    		generatorDao.createDaoAll(xt_Generator_Table_ColumnList, xt_Generator);
    		//3业务层
    		generatorService.createServiceAll(xt_Generator_Table_ColumnList, xt_Generator);
    		//4控制层
    		generatorWeb.createWeb(xt_Generator_Table_ColumnList, xt_Generator);
    		//5页面层
    		generatorPage.createPageAll(xt_Generator_Table_ColumnList, xt_Generator);
    		///////////////////////////////单表及主表生成结束/////////////////////////
    		//6一对多子表生成代码
    		commonManyToOne(xt_Generator);
			i = 1;
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
    	return i;
    }
    
    /**
     * 一对多子表生成
     * @param xt_Generator
     */
    private void commonManyToOne(Xt_Generator xt_Generator){
    	//设置为非主表
    	xt_Generator.setIs_main_table(false);
    	GeneratorModel generatorModel = new GeneratorModel();
    	GeneratorDao generatorDao = new GeneratorDao();
    	GeneratorService generatorService = new GeneratorService();
    	GeneratorWeb generatorWeb = new GeneratorWeb();
    	//如果是一对多则对子表生成代码直到控制层（Model，Dao，Service，Web层需要生成）
		if(xt_Generator.getIs_one_to_many().equals("1")){
			List<Xt_Generator_TableMany_To_One> xt_Generator_TableMany_To_OneList = xt_Generator.getXt_Generator_TableMany_To_OneList();
			for(int j = 0; j < xt_Generator_TableMany_To_OneList.size(); j++){
				Xt_Generator_TableMany_To_One xt_Generator_TableMany_To_One = xt_Generator_TableMany_To_OneList.get(j);
				List<Xt_Generator_Table_ColumnMany_To_One> xt_Generator_Table_ColumnMany_To_OneList = xt_Generator_TableMany_To_One.getXt_Generator_Table_ColumnMany_To_OneList();
				List<Xt_Generator_Table_Column> xt_Generator_Table_ColumnManyToOneList = new ArrayList<Xt_Generator_Table_Column>();
				for(int h = 0; h < xt_Generator_Table_ColumnMany_To_OneList.size(); h++){
					Xt_Generator_Table_ColumnMany_To_One xt_Generator_Table_ColumnMany_To_One = xt_Generator_Table_ColumnMany_To_OneList.get(h);
					Xt_Generator_Table_Column xt_Generator_Table_Column = new Xt_Generator_Table_Column();
					xt_Generator_Table_Column.setCHARACTER_MAXIMUM_LENGTH(xt_Generator_Table_ColumnMany_To_One.getCHARACTER_MAXIMUM_LENGTH());
					xt_Generator_Table_Column.setCOLUMN_COMMENT(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_COMMENT());
					xt_Generator_Table_Column.setCOLUMN_KEY(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_KEY());
					xt_Generator_Table_Column.setColumn_label_anchor(xt_Generator_Table_ColumnMany_To_One.getColumn_label_anchor());
					xt_Generator_Table_Column.setColumn_label_position(xt_Generator_Table_ColumnMany_To_One.getColumn_label_position());
					xt_Generator_Table_Column.setCOLUMN_NAME(xt_Generator_Table_ColumnMany_To_One.getCOLUMN_NAME());
					xt_Generator_Table_Column.setColumn_type(xt_Generator_Table_ColumnMany_To_One.getColumn_type());
					xt_Generator_Table_Column.setDATA_TYPE(xt_Generator_Table_ColumnMany_To_One.getDATA_TYPE());
					xt_Generator_Table_Column.setIS_NULLABLE(xt_Generator_Table_ColumnMany_To_One.getIS_NULLABLE());
					xt_Generator_Table_Column.setIsHidden(xt_Generator_Table_ColumnMany_To_One.getIsHidden());
					xt_Generator_Table_Column.setXt_script_id(xt_Generator_Table_ColumnMany_To_One.getXt_script_id());
					xt_Generator_Table_ColumnManyToOneList.add(xt_Generator_Table_Column);
				}
				xt_Generator.setIs_main_table(false);
				xt_Generator.setIs_one_to_many("1");
				xt_Generator.setFk(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_fkey());
				xt_Generator.setXt_generator_web_package(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_web_package());
				xt_Generator.setXt_generator_model_package(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_model_package());
				xt_Generator.setXt_generator_service_package(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_service_package());
				xt_Generator.setXt_generator_dao_package(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_dao_package());
				xt_Generator.setXt_generator_tbname(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name());
				xt_Generator.setXt_generator_tbcomment(xt_Generator_TableMany_To_One.getXt_generator_one_to_many_table_name_zh());
				//1实体层
        		generatorModel.createModel(xt_Generator_Table_ColumnManyToOneList, xt_Generator);
        		//2数据层
        		generatorDao.createDaoAll(xt_Generator_Table_ColumnManyToOneList, xt_Generator);
        		//3业务层
        		generatorService.createServiceAll(xt_Generator_Table_ColumnManyToOneList, xt_Generator);
        		//4控制层
        		generatorWeb.createWeb(xt_Generator_Table_ColumnManyToOneList, xt_Generator);
			}
		}
    }
}
