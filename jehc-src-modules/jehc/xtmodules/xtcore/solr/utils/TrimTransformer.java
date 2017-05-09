package jehc.xtmodules.xtcore.solr.utils;

import java.util.List;
import java.util.Map;

import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.Transformer;

/**
 * Solr TrimTransformer模板
 * 
 * @author 邓纯杰
 * 
 */
public class TrimTransformer extends Transformer {
	/**
	使用例子
	<entity name="test" query="..." transformer="xtCore.solr.utils.TrimTransformer">
		<field column="testName" trim="true" />
	</entity>
    **/
	@Override
	public Object transformRow(Map<String, Object> row, Context context) {
		List<Map<String, String>> fields = context.getAllEntityFields();
		for (int i = 0; i < fields.size(); i++) {
			//Check if this field has trim="true" specified in the data-config.xml
			String trim = fields.get(i).get("trim");
			if ("true".equals(trim)) {
				//Apply trim on this fied
				String columnName = fields.get(i).get("column");
				//Get this field's value from the current row
				String value = (String) row.get(columnName);//Trim and put the updated value back in the current row
				if (value != null) {
					row.put(columnName, value.trim());
				}
			}
		}
		return row;
	}

}
