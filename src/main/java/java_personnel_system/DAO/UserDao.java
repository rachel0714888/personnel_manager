package java_personnel_system.DAO;

import java_personnel_system.pojo.User;

/**
 * @auther Rachel
 * @date 2023/6/11 22:08
 */
public interface UserDao {

    /**
     * 将输入进来的用户和数据库中的用户进行匹配，如果用户名存在且密码相同为匹配成功，将该数据的用户名、密码、状态、权限保存到当前用户
     *
     * @param inputUser 保存要匹配的用户名和密码
     * @return 返回true表示匹配成功，反之匹配失败
     * @throws Exception 如果出现SQLException，抛出异常
     */
    boolean match(User inputUser) throws Exception;

    /**
     * 插入一条用户数据
     *
     * @param inputUser 存放插入的用户数据，包括用户名、密码、用户员工id、用户状态、用户权限
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void insert(User inputUser) throws Exception;

    /**
     * 根据用户id删除用户
     *
     * @param userId 存放要删除的用户id
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void idRemove(int userId) throws Exception;

    /**
     * 判断输入的用户名是否存在
     *
     * @param inputUserName 存放要判断的用户名
     * @return 存在返回true，反之返回false
     * @throws Exception 如果出现SQLException，抛出异常
     */
    boolean userNameExist(String inputUserName) throws Exception;

    /**
     * 判断输入的用户的员工id是否存在
     *
     * @param inputStaffId 存放要判断的员工id
     * @return 存在返回true 反之返回false
     * @throws Exception 如果出现SQLException，抛出异常
     */
    boolean userStaffIdExist(int inputStaffId) throws Exception;

    /**
     * 防止手动删除数据后出现主键自增不连续
     *
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void userTableAlter() throws Exception;

    /**
     * 根据用户名删除用户
     *
     * @param inputUserName 存放要删除用户的用户名
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void nameRemove(String inputUserName) throws Exception;

    /**
     * 根据用户的员工id删除用户
     *
     * @param staffId 存放要删除用户的员工id
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void staffIdRemove(int staffId) throws Exception;

    /**
     * 根据用户的状态删除用户
     *
     * @param isWork 存放要删除的用户的状态代码，1为在职 0为离职
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void isWorkRemove(int isWork) throws Exception;

    /**
     * 根据用户id修改用户信息
     *
     * @param user 存放要修改的用户id和修改后的用户信息
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void userChange(User user) throws Exception;


    /**普通用户只能根据自己的id对自己的用户名/密码进行修改
     * @param userName 修改后的用户名
     * @param password 修改后的用户密码
     * @throws Exception
     */
    void ordinaryUserMsgUpdate(String userName,String password) throws Exception;

    /**
     * 查看所有用户信息
     *
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void userAllSelect() throws Exception;

    /**
     * 根据输入的用户名对用户信息进行模糊匹配，查询想要的用户信息
     *
     * @param userName 存入要查询的用户名
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void userLikeNameSelect(String userName) throws Exception;

    /**
     * 根据用户状态（在职离职）对用户信息进行查询
     *
     * @param isWork 存放要查询的员工状态
     * @throws Exception 如果出现SQLException，抛出异常
     */
    void userLikeIsworkSelect(int isWork) throws Exception;

    /**
     * 根据用户名查询该用户的员工id
     *
     * @param userName 存入该用户的用户id
     * @return 该用户的员工id 返回0说明该用户id不存在
     * @throws Exception
     */
    int selectUserStaffId(String userName) throws Exception;


}
