package cn.com.dplus.legend.proxy;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:19 18-5-17
 * @Modified By:
 */
public class UserDaoImpl implements UserDao {
    @Override
    public boolean login(String username, String password) {
        System.out.println(this.getClass().getName() + "-> processing login:" + "(" + username + "," + password + ")");
        return true;
    }
}
