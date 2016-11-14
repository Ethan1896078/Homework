import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/14.
 */
public class App {
    public static void main(String[] args) {
        System.out.println(StringUtils.trim("hello tropical disease research!"));
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        System.out.println(JSON.toJSONString(list));
    }
}
