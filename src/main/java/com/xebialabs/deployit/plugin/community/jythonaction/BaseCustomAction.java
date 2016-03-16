/**
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS
 * FOR A PARTICULAR PURPOSE. THIS CODE AND INFORMATION ARE NOT SUPPORTED BY XEBIALABS.
 */

package com.xebialabs.deployit.plugin.community.jythonaction;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xebialabs.deployit.plugin.api.udm.Metadata;
import com.xebialabs.deployit.plugin.api.udm.Property;
import com.xebialabs.deployit.plugin.api.udm.base.BaseConfigurationItem;
import com.xebialabs.deployit.plugin.trigger.Action;

@SuppressWarnings("serial")
@Metadata(root = Metadata.ConfigurationItemRoot.CONFIGURATION, description = "Custom base action")
public class BaseCustomAction extends BaseConfigurationItem implements Action {

	
	@Property(label="Python scripts to add to classpath", description="List of jython scripts scriptPath1, scriptPath2,..", defaultValue="", required=false)
	private String  scriptClasspath;
	
	@Property(label="Python script to execute for this action", description="Path to the python script", required=true)
	private String  scriptPath;
	
	
	@Override
    public void execute(Map<String, Object> ctx) {
		//for debug purpose
		debugContext(ctx);
		executeJythonScript(ctx, this.id);	
	}
	
	private void debugContext(Map<String, Object> ctx) {
		for (Entry<String, Object> e:ctx.entrySet()){
			logger.debug("Action context key={} value={} type={}",e.getKey(), e.getValue(), e.getValue()!=null?e.getValue().getClass().toString():"-");
		}
	}
	
	
	protected  void executeJythonScript(Map<String, Object> ctx, String id){
    	ScriptRunner.executeScript(ctx, scriptPath, scriptClasspath, id);
    }
	
	private static final Logger logger = LoggerFactory.getLogger(BaseCustomAction.class);
}
