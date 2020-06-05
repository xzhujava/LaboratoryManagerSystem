import com.laboratory.common.utils.DateTimeUtil;
import com.laboratory.dao.GenericDao;
import com.laboratory.dao.impl.GenericDaoImpl;
import com.laboratory.entity.SysRoleUser;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/3 21:52
 */
public class Test {
    private GenericDao<SysRoleUser> sysRoleUserGenericDao = new GenericDaoImpl<SysRoleUser>();

    @org.junit.Test
    public void testGetAll(){
        try {
            List<SysRoleUser> all = sysRoleUserGenericDao.findAllByConditions(null, SysRoleUser.class);
            for (SysRoleUser sysRoleUser : all) {
                System.out.println(sysRoleUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @org.junit.Test
    public void testDateFormat(){
        String str = "2018-07-05 12:24:12";
        LocalDateTime localDateTime = DateTimeUtil.parseStringToLocalDateTime(str);
        System.out.println(localDateTime);
    }

    @org.junit.Test
    public void testString(){
        String sql = "select * from user where id in (1,2,3,";
        sql = sql.substring(0,sql.length()-1);
        System.out.println(sql);
    }
}
