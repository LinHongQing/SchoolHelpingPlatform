package util;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

public class AliasToBeanResultTransformerUtil implements ResultTransformer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2276593438227145995L;
    private final Class<?> resultClass;
    private Setter[] setters;
    private PropertyAccessor propertyAccessor;
    public AliasToBeanResultTransformerUtil(Class<?> resultClass) {
        if (resultClass == null) throw new IllegalArgumentException("resultClass cannot be null");
        this.resultClass = resultClass;
        propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[] {
        		PropertyAccessorFactory.getPropertyAccessor(resultClass, null), PropertyAccessorFactory.getPropertyAccessor("field")
        		});
    }
	@Override
    @SuppressWarnings({ "rawtypes" })
	public List<?> transformList(List collection) {
        return collection;
	}

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		// TODO Auto-generated method stub
        Object result;
        try {
            if (setters == null) {
                setters = new Setter[aliases.length];
                for (int i = 0; i < aliases.length; i++) {
                    String alias = convertColumnToProperty(aliases[i]);
                    if (alias != null) {
                        try {
                            setters[i] = propertyAccessor.getSetter(resultClass, alias);
                        } catch (PropertyNotFoundException e) {
                            continue;
                        }
                    }
                }
            }
            result = resultClass.newInstance();
            for (int i = 0; i < aliases.length; i++) {
                if (setters[i] != null) {
                    setters[i].set(result, tuple[i], null);
                }
            }
        } catch (InstantiationException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        } catch (IllegalAccessException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        }
        return result;
	}
    public String convertColumnToProperty(String columnName) {
        columnName = columnName.toLowerCase();
        StringBuffer buff = new StringBuffer(columnName.length());
        StringTokenizer st = new StringTokenizer(columnName, "_");
        while (st.hasMoreTokens()) {
            buff.append(StringUtils.capitalize(st.nextToken()));
        }
        buff.setCharAt(0, Character.toLowerCase(buff.charAt(0)));
        return buff.toString();
    }

}
