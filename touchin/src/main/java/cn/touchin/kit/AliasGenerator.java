/*
 * cn.touchin.kit.AliasGenerator.java
 * Feb 12, 2012 
 */
package cn.touchin.kit;

import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Strings;

/**
 * Feb 12, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class AliasGenerator {

    public static final String ALIAS_PATH_SEPERATOR = "#";
    public static final String ALIAS_PREFIX = "_";

    private Map<String, String> aliasMap = new HashMap<String, String>();

    public AliasGenerator() {
        super();
    }

    public String generate(String aliasPath) {
        String alias = aliasPath;
        if (!Strings.isBlank(aliasPath)) {
            String[] aliasArray = aliasPath.split(AliasGenerator.ALIAS_PATH_SEPERATOR);
            if (aliasArray != null && aliasArray.length > 0) {
                String last = aliasArray[aliasArray.length - 1];
                int sameAliasCount = getSameAliasCount(last);
                if (sameAliasCount <= 0) {
                    alias = ALIAS_PREFIX + last;
                } else {
                    alias = ALIAS_PREFIX + last + ALIAS_PREFIX + sameAliasCount;
                }
                aliasMap.put(aliasPath, alias);
            }
        }
        return alias;
    }

    private int getSameAliasCount(String alias) {
        int count = 0;
        if (aliasMap.size() > 0) {
            for (String name : aliasMap.keySet()) {
                if (name.indexOf(alias) != -1) {
                    count++;
                }
            }
        }
        return count;
    }


}
