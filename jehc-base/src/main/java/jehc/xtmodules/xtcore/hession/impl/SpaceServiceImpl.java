package jehc.xtmodules.xtcore.hession.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jehc.xtmodules.xtcore.hession.Space;
import jehc.xtmodules.xtcore.hession.SpaceService;

public class SpaceServiceImpl implements SpaceService {
	private static final Log log = LogFactory.getLog(SpaceServiceImpl.class);
	private Space createSpace() {
		Space space = new Space();
		space.setPublicName("roosher-space");
		space.setPrivateName("my-private-room");
		space.setActive(true);
		space.setLastUpdatedTime(new Date());
		return space;
	}

	public Space findSpace(String id) {
		log.debug("find space id is :" + id);
		return createSpace();
	}

	public List showSpaceNames() {
		List names = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			names.add("Hello space " + i);
		}
		return names;
	}

	public boolean updateSpaceName(String name) {
		log.debug("updated space name : " + name);
		return true;
	}
}
