package jehc.xtmodules.xtcore.hession;

import java.util.List;


public interface SpaceService {
	List showSpaceNames(); //返回名字集合  
    
    Space findSpace(String id);//找到空间名字  
      
    boolean updateSpaceName(String name); //更新空间名字
}
