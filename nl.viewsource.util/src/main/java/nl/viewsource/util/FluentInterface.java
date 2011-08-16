package nl.viewsource.util;


import java.beans.Statement;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* http://codemonkeyism.com/fluent-interface-and-reflection-for-object-building-in-java/ */

public class FluentInterface<T> implements InvocationHandler {
	Object obj;

	public FluentInterface(Object obj) {
		this.obj = obj;
	}

	@SuppressWarnings("unchecked")
	public static <T> T create(Object object, Class fluentInterfaceClass) {
		
		FluentInterface handler = new FluentInterface(object);
		
		T fluentInterface = (T) Proxy.newProxyInstance(fluentInterfaceClass.getClassLoader(), new Class[] { fluentInterfaceClass }, handler);
		return fluentInterface;
	}

	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		try {
			String name = m.getName();
			if ("create".equals(name)) {
				return obj;
			}
			String setter = "set" + StringUtils.firstToUpper(name);
			Statement stmt = new Statement(this.obj, setter, args);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proxy;
	}
}