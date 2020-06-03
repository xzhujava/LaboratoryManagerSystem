import com.laboratory.dao.GenericDao;
import com.laboratory.dao.impl.GenericDaoImpl;
import com.laboratory.entity.SysRoleUser;

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
}
