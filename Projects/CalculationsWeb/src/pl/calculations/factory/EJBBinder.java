package pl.calculations.factory;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pl.calculations.system.facade.ISystemCalculationsRemote;
import pl.calculations.system.facade.impl.SystemCalculationsBean;

public class EJBBinder {

	public static ISystemCalculationsRemote lookupRemoteSystemCalculationsBean() throws NamingException {
    	final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        final String appName = "CalculationsEar";
        final String moduleName = "SystemCalculations";
        final String distinctName = "";
        final String beanName = SystemCalculationsBean.class.getSimpleName();
        final String viewClassName = ISystemCalculationsRemote.class.getName();
        return (ISystemCalculationsRemote) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}
}
