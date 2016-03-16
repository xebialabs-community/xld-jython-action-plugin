# Custom Jython Action Plugin #

# Overview #

This plugin allows for custom actions definitions with Jython scripts. Actions can be associated with triggers.

It can be used in two ways : 

- create an xlc.BaseCustomAction CI under Configuration and define your Jython script path with the scriptPath property
- create a new type in a synthetic.xml file by deriving xlc.BaseCustomAction then create a CI from this definition


# Requirements #

* **XLDeploy requirements**
	* **XLDeploy**: version 5.1.0+

# Installation #

Place the plugin JAR file into your `SERVER_HOME/plugins` directory.
	
	
## Variables injected ##

The following variables are injected in the Jython scripts:

- context : Map<String, Object>
- logger  : an org.slf4j.Logger 

The context variable allows access to  :

- task   : com.xebialabs.deployit.engine.tasker.Task
- deltas : com.xebialabs.deployit.plugin.api.deployment.specification.Deltas
- action : com.xebialabs.deployit.plugin.trigger.Action
- deployedApplication : com.xebialabs.deployit.plugin.api.udm.DeployedApplication

# Sample python script # 

Here is a sample script to illustrate the access to the context variable:

```
deployedApp = context.get("deployedApplication")
logger.info("Application id : %s"%deployedApp.id)
logger.info("Environment id : %s"%deployedApp.environment.id)
task = context.get("task")
logger.info("Task : %s, status : %s"%(task,task.getState().name()))
``` 
		
