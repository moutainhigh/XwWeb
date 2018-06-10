/**
 *
 * All Rights Reserved
 *
 * Revision History:
 *                       Modification        Tracking
 *
 */

package app.creditapp.wkf.dao;

import java.util.HashMap;
import java.util.Map;
import app.creditapp.wkf.entity.AppWkfcfg;

public class WorkflowConfigCache {
	
	private static final Map<String, String> mappers = new HashMap<String, String>();
	
	public static String get(AppWkfcfg appWkfcfg) {
		String key = appWkfcfg.getBrNo() + "_" + appWkfcfg.getAppType() + "_" + appWkfcfg.getPrdtNo();
		return mappers.get(key);
	}
	
	public static String put(AppWkfcfg appWkfcfg, String processKey) {
		String key = appWkfcfg.getBrNo() + "_" + appWkfcfg.getAppType() + "_" + appWkfcfg.getPrdtNo();
		return mappers.put(key, processKey);
	}
	
}
