package share.sss.com.emoji.bean;

/**
 * 表情添加路径
 * Created by Administrator on 2019/2/13.
 */

public class EmojiFaceResBean {
    /**
     * 表情名
     */
    public String name;
    /**
     * 表情路径
     */
    public int res;

    /**
     * 构造
     */
    public EmojiFaceResBean(String name, int res) {
        this.name = name;
        this.res = res;
    }
}
