//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.thymeleaf.standard.expression;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ognl.ArrayPropertyAccessor;
import ognl.EnumerationPropertyAccessor;
import ognl.IteratorPropertyAccessor;
import ognl.ListPropertyAccessor;
import ognl.MapPropertyAccessor;
import ognl.ObjectPropertyAccessor;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.PropertyAccessor;
import ognl.SetPropertyAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.cache.ExpressionCacheKey;
import org.thymeleaf.cache.ICache;
import org.thymeleaf.cache.ICacheManager;
import org.thymeleaf.context.IContext;

final class OGNLShortcutExpression {
    private static final Logger LOGGER = LoggerFactory.getLogger(OGNLShortcutExpression.class);
    private static final String EXPRESSION_CACHE_TYPE_OGNL_SHORTCUT = "ognlsc";
    private static final Object[] NO_PARAMS = new Object[0];
    private final String[] expressionLevels;

    OGNLShortcutExpression(String[] expressionLevels) {
        this.expressionLevels = expressionLevels;
    }

    Object evaluate(IEngineConfiguration configuration, Map<String, Object> context, Object root) throws Exception {
        ICacheManager cacheManager = configuration.getCacheManager();
        ICache<ExpressionCacheKey, Object> expressionCache = cacheManager == null ? null : cacheManager.getExpressionCache();
        Object target = root;
        String[] var7 = this.expressionLevels;
        int var8 = var7.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            String propertyName = var7[var9];
            if (target == null) {
                //return propertyName;
                throw new OgnlException("source is null for getProperty(null, \"" + propertyName + "\")");
            }

            Class<?> targetClass = OgnlRuntime.getTargetClass(target);
            PropertyAccessor ognlPropertyAccessor = OgnlRuntime.getPropertyAccessor(targetClass);
            if (target instanceof Class) {
                target = getObjectProperty(expressionCache, propertyName, target);
            } else if (OGNLContextPropertyAccessor.class.equals(ognlPropertyAccessor.getClass())) {
                target = getContextProperty(propertyName, context, target);
            } else if (ObjectPropertyAccessor.class.equals(ognlPropertyAccessor.getClass())) {
                target = getObjectProperty(expressionCache, propertyName, target);
            } else if (MapPropertyAccessor.class.equals(ognlPropertyAccessor.getClass())) {
                target = getMapProperty(propertyName, (Map)target);
            } else if (ListPropertyAccessor.class.equals(ognlPropertyAccessor.getClass())) {
                target = getListProperty(expressionCache, propertyName, (List)target);
            } else if (SetPropertyAccessor.class.equals(ognlPropertyAccessor.getClass())) {
                target = getSetProperty(expressionCache, propertyName, (Set)target);
            } else if (IteratorPropertyAccessor.class.equals(ognlPropertyAccessor.getClass())) {
                target = getIteratorProperty(expressionCache, propertyName, (Iterator)target);
            } else if (EnumerationPropertyAccessor.class.equals(ognlPropertyAccessor.getClass())) {
                target = getEnumerationProperty(expressionCache, propertyName, (Enumeration)target);
            } else {
                if (!ArrayPropertyAccessor.class.equals(ognlPropertyAccessor.getClass())) {
                    throw new OGNLShortcutExpression.OGNLShortcutExpressionNotApplicableException();
                }

                target = getArrayProperty(expressionCache, propertyName, (Object[])target);
            }
        }

        return target;
    }

    private static Object getContextProperty(String propertyName, Map<String, Object> context, Object target) throws OgnlException {
        if ("param".equals(propertyName) && context != null && context.containsKey("%RESTRICT_REQUEST_PARAMETERS%")) {
            throw new OgnlException("Access to variable \"" + propertyName + "\" is forbidden in this context. Note some restrictions apply to variable access. For example, accessing request parameters is forbidden in preprocessing and unescaped expressions, and also in fragment inclusion specifications.");
        } else {
            if ("execInfo".equals(propertyName)) {
                Object execInfoResult = checkExecInfo(propertyName, context);
                if (execInfoResult != null) {
                    return execInfoResult;
                }
            }

            return ((IContext)target).getVariable(propertyName);
        }
    }

    /** @deprecated */
    @Deprecated
    private static Object checkExecInfo(String propertyName, Map<String, Object> context) {
        if ("execInfo".equals(propertyName)) {
            LOGGER.warn("[THYMELEAF][{}] Found Thymeleaf Standard Expression containing a call to the context variable \"execInfo\" (e.g. \"${execInfo.templateName}\"), which has been deprecated. The Execution Info should be now accessed as an expression object instead (e.g. \"${#execInfo.templateName}\"). Deprecated use is still allowed, but will be removed in future versions of Thymeleaf.", TemplateEngine.threadIndex());
            return context.get("execInfo");
        } else {
            return null;
        }
    }

    private static Object getObjectProperty(ICache<ExpressionCacheKey, Object> expressionCache, String propertyName, Object target) {
        Class<?> currClass = OgnlRuntime.getTargetClass(target);
        ExpressionCacheKey cacheKey = computeMethodCacheKey(currClass, propertyName);
        Method readMethod = null;
        if (expressionCache != null) {
            readMethod = (Method)expressionCache.get(cacheKey);
        }

        if (readMethod == null) {
            BeanInfo beanInfo;
            try {
                beanInfo = Introspector.getBeanInfo(currClass);
            } catch (IntrospectionException var14) {
                throw new OGNLShortcutExpression.OGNLShortcutExpressionNotApplicableException();
            }

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null) {
                PropertyDescriptor[] var8 = propertyDescriptors;
                int var9 = propertyDescriptors.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    PropertyDescriptor propertyDescriptor = var8[var10];
                    if (propertyDescriptor.getName().equals(propertyName)) {
                        readMethod = propertyDescriptor.getReadMethod();
                        if (readMethod != null && expressionCache != null) {
                            expressionCache.put(cacheKey, readMethod);
                        }
                        break;
                    }
                }
            }
        }

        if (readMethod == null) {
            throw new OGNLShortcutExpression.OGNLShortcutExpressionNotApplicableException();
        } else {
            try {
                return readMethod.invoke(target, NO_PARAMS);
            } catch (IllegalAccessException var12) {
                throw new OGNLShortcutExpression.OGNLShortcutExpressionNotApplicableException();
            } catch (InvocationTargetException var13) {
                throw new OGNLShortcutExpression.OGNLShortcutExpressionNotApplicableException();
            }
        }
    }

    private static Object getMapProperty(String propertyName, Map<?, ?> map) {
        if (propertyName.equals("size")) {
            return map.size();
        } else if (!propertyName.equals("keys") && !propertyName.equals("keySet")) {
            if (propertyName.equals("values")) {
                return map.values();
            } else if (propertyName.equals("isEmpty")) {
                return map.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
            } else {
                return map.get(propertyName);
            }
        } else {
            return map.keySet();
        }
    }

    public static Object getListProperty(ICache<ExpressionCacheKey, Object> expressionCache, String propertyName, List<?> list) {
        if (propertyName.equals("size")) {
            return list.size();
        } else if (propertyName.equals("iterator")) {
            return list.iterator();
        } else if (!propertyName.equals("isEmpty") && !propertyName.equals("empty")) {
            return getObjectProperty(expressionCache, propertyName, list);
        } else {
            return list.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public static Object getArrayProperty(ICache<ExpressionCacheKey, Object> expressionCache, String propertyName, Object[] array) {
        return propertyName.equals("length") ? Array.getLength(array) : getObjectProperty(expressionCache, propertyName, array);
    }

    public static Object getEnumerationProperty(ICache<ExpressionCacheKey, Object> expressionCache, String propertyName, Enumeration enumeration) {
        if (!propertyName.equals("next") && !propertyName.equals("nextElement")) {
            if (!propertyName.equals("hasNext") && !propertyName.equals("hasMoreElements")) {
                return getObjectProperty(expressionCache, propertyName, enumeration);
            } else {
                return enumeration.hasMoreElements() ? Boolean.TRUE : Boolean.FALSE;
            }
        } else {
            return enumeration.nextElement();
        }
    }

    public static Object getIteratorProperty(ICache<ExpressionCacheKey, Object> expressionCache, String propertyName, Iterator<?> iterator) {
        if (propertyName.equals("next")) {
            return iterator.next();
        } else if (propertyName.equals("hasNext")) {
            return iterator.hasNext() ? Boolean.TRUE : Boolean.FALSE;
        } else {
            return getObjectProperty(expressionCache, propertyName, iterator);
        }
    }

    public static Object getSetProperty(ICache<ExpressionCacheKey, Object> expressionCache, String propertyName, Set<?> set) {
        if (propertyName.equals("size")) {
            return set.size();
        } else if (propertyName.equals("iterator")) {
            return set.iterator();
        } else if (propertyName.equals("isEmpty")) {
            return set.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
        } else {
            return getObjectProperty(expressionCache, propertyName, set);
        }
    }

    static String[] parse(String expression) {
        return doParseExpr(expression, 0, 0, expression.length());
    }

    private static String[] doParseExpr(String expression, int level, int offset, int len) {
        int i = offset;

        for(boolean firstChar = true; i < len; ++i) {
            int codepoint = Character.codePointAt(expression, i);
            if (codepoint == 46) {
                break;
            }

            if (firstChar) {
                if (!Character.isJavaIdentifierStart(codepoint)) {
                    return null;
                }

                firstChar = false;
            } else if (!Character.isJavaIdentifierPart(codepoint)) {
                return null;
            }
        }

        String[] result;
        if (i < len) {
            result = doParseExpr(expression, level + 1, i + 1, len);
            if (result == null) {
                return null;
            }
        } else {
            result = new String[level + 1];
        }

        result[level] = expression.substring(offset, i);
        return !"true".equalsIgnoreCase(result[level]) && !"false".equalsIgnoreCase(result[level]) && !"null".equalsIgnoreCase(result[level]) ? result : null;
    }

    private static ExpressionCacheKey computeMethodCacheKey(Class<?> targetClass, String propertyName) {
        return new ExpressionCacheKey("ognlsc", targetClass.getName(), propertyName);
    }

    static class OGNLShortcutExpressionNotApplicableException extends RuntimeException {
        OGNLShortcutExpressionNotApplicableException() {
        }
    }
}
