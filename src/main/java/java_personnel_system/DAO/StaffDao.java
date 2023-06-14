package java_personnel_system.DAO;

import java_personnel_system.pojo.Staff;

/**
 * @auther Rachel
 * @date 2023/6/12 10:15
 */
public interface StaffDao {

    /**判断员工id是否存在
     * @param staffId 要判断的员工id
     * @return 存在为true
     * @throws Exception 抛出异常
     */
    boolean staffIdExist(int staffId) throws Exception;

    /**判断员工名是否存在
     * @param staffName 要判断的员工名
     * @return 存在为true
     * @throws Exception 抛出异常
     */
    boolean staffNameExist(String staffName) throws Exception;

    /**判断员工性别是否存在
     * @param staffSex 要判断的员工性别
     * @return 存在为true
     * @throws Exception 抛出异常
     */
    boolean staffSexExist(String staffSex) throws Exception;

    /**判断部门id是否存在
     * @param departmentId 要判断的部门id
     * @return 存在为true
     * @throws Exception 抛出异常
     */
    boolean staffDepartmentIdExist(int departmentId) throws Exception;

    /**判断职位id是否存在
     * @param positionId 要判断的职位id
     * @return 存在为true
     * @throws Exception 抛出异常
     */
    boolean staffPositionIdExist(int positionId) throws Exception;

    /**判断民族是否存在
     * @param nationName 要判断的民族
     * @return 存在为true
     * @throws Exception 抛出异常
     */
    boolean staffNationNameExist(String nationName) throws Exception;

    /**判断教育背景是否存在
     * @param educationName 要判断的教育背景
     * @return 存在为true
     * @throws Exception 抛出异常
     */
    boolean staffEducationNameExist(String educationName) throws Exception;

    /**判断身份证是否存在
     * @param identityId 要判断的身份证
     * @return 存在为true
     * @throws Exception 抛出异常
     */
    boolean staffIdentityIdNameExist(String identityId) throws Exception;

    /**判断电话号是否存在
     * @param phoneNum 要判断的电话号
     * @return 存在为true
     * @throws Exception 抛出异常
     */
    boolean staffPhoneNumNameExist(String phoneNum) throws Exception;

    /**
     * 增加员工
     *
     * @param staff 存放要增加的员工数据
     * @throws Exception 抛出异常 向上抛出异常
     */
    void staffAdd(Staff staff) throws Exception;

    /**
     * 根据提供的员工id删除对应的员工数据
     *
     * @param staffId 存入要删除的员工的员工id
     * @throws Exception 抛出异常 向上抛出异常
     */
    void removeStaffId(int staffId) throws Exception;

    /**
     * 根据提供的员工姓名删除对应的员工数据
     *
     * @param staffName 存入要删除的员工的员工姓名
     * @throws Exception 抛出异常 向上抛出异常
     */
    void removeStaffName(String staffName) throws Exception;

    /**
     * 防止手动删除数据导致主键增长不连续
     *
     * @throws Exception 抛出异常 向上抛出异常
     */
    void tableAlter() throws Exception;

    /**
     * 在删除职位的时候调用 根据该职位的所有员工的员工id 自动将这些员工的职位id改为12（未定义）
     *
     * @param staffId 存放要修改的员工id
     * @throws Exception 抛出异常 向上抛出异常
     */
    void autoUpdateStaffPositionId(int staffId) throws Exception;

    /**
     * 在删除部门的时候调用 根据该部门的所有员工的员工id 自动将这些员工的部门id改为6（未定义）
     *
     * @param staffId 存放要修改的员工id
     * @throws Exception 抛出异常 向上抛出异常
     */
    void autoUpdateStaffDepartmentId(int staffId) throws Exception;

    /**根据员工id更改员工姓名
     * @param staffId 要更改的员工的员工id
     * @param staffName 更改后的员工姓名
     * @throws Exception 抛出异常
     */
    void updateStaffName(int staffId,String staffName)throws Exception;

    /**根据员工id更改员工性别
     * @param staffId 要更改的员工的员工id
     * @param staffSex 更改后的员工性别
     * @throws Exception 抛出异常
     */
    void updateStaffSex(int staffId,String staffSex)throws Exception;

    /**根据员工id更改员工部门id
     * @param staffId 要更改的员工的员工id
     * @param sdepartmentId 更改后的员工部门id
     * @throws Exception 抛出异常
     */
    void updateStaffDepartmentId(int staffId,int sdepartmentId)throws Exception;

    /**根据员工id更改员工职位id
     * @param staffId 要更改的员工的员工id
     * @param spositionId 更改后的员工职位id
     * @throws Exception 抛出异常
     */
    void updateStaffPositionId(int staffId,int spositionId)throws Exception;

    /**根据员工id更改员工民族
     * @param staffId 要更改的员工的员工id
     * @param staffNation 更改后的员工民族
     * @throws Exception 抛出异常
     */
    void updateStaffNation(int staffId,String staffNation)throws Exception;

    /**根据员工id更改员工学历
     * @param staffId 要更改的员工的员工id
     * @param staffEducation 更改后的员工学历
     * @throws Exception 抛出异常
     */
    void updateStaffEducation(int staffId,String staffEducation)throws Exception;

    /**根据员工id更改员工身份证
     * @param staffId 要更改的员工的员工id
     * @param staffIdentityId 更改后的员工身份证
     * @throws Exception 抛出异常
     */
    void updateStaffIdentityid(int staffId,String staffIdentityId)throws Exception;

    /**根据员工id更改员工电话号
     * @param staffId  要更改的员工的员工id
     * @param staffPhonenum 更改后的员工电话号
     * @throws Exception 抛出异常
     */
    void updateStaffPhonenum(int staffId,String staffPhonenum)throws Exception;

    /**
     * 根据输入的员工id 更新员工信息
     *
     * @param staff 存放要更改的员工的id和准备更新的员工信息
     * @throws Exception 抛出异常 向上抛出异常
     */
    void updateStaffMsg(Staff staff) throws Exception;


    /**普通员工权限只能修改自己的名字、身份证、电话号
     * @param staffName 要修改的名字
     * @param staffIdentityId 要修改的身份证
     * @param staffPhonenum 要修改的电话号
     * @throws Exception 抛出异常
     */
    void ordinaryUserUpdateStaffMsg(String staffName,String staffIdentityId,String staffPhonenum) throws Exception;

    /**
     * 查询所有员工的信息
     *
     * @throws Exception 抛出异常 向上抛出异常
     */
    void selectAllStaffMsg() throws Exception;

    /**
     * 根据员工id查询员工信息
     *
     * @param staffId 存放要查询的员工的员工id
     * @throws Exception 抛出异常
     */
    void selectStaffId(int staffId) throws Exception;

    /**
     * 根据员工姓名查询员工信息
     *
     * @param staffName 存放要查询的员工的员工姓名
     * @throws Exception 抛出异常
     */
    void selectStaffName(String staffName) throws Exception;

    /**根据员工性别查询员工信息
     * @param staffSex 存放要查询的员工的员工性别
     * @throws Exception 抛出异常
     */
    void selectStaffSex(String staffSex) throws Exception;

    /**根据员工部门id查询员工信息
     * @param sdepartmentId 存放要查询的员工的员工部门id
     * @throws Exception 抛出异常
     */
    void selectStaffDepartmentId(int sdepartmentId) throws Exception;

    /**根据员工职位id查询员工信息
     * @param spositionId 存放要查询的员工的员工职位id
     * @throws Exception 抛出异常
     */
    void selectStaffPrositionId(int spositionId) throws Exception;

    /**根据员工民族查询员工信息
     * @param staffNation 存放要查询的员工的员工民族
     * @throws Exception 抛出异常
     */
    void selectStaffNation(String staffNation) throws Exception;

    /**根据员工教育背景查询员工信息
     * @param staffEducation 存放要查询的员工的员工教育背景
     * @throws Exception 抛出异常
     */
    void selectStaffEducation(String staffEducation) throws Exception;

    /**根据员工身份证查询员工信息
     * @param staffIdentityId 存放要查询的员工的身份证
     * @throws Exception 抛出异常
     */
    void selectStaffIdentityId(String staffIdentityId) throws Exception;

    /**根据员工手机号查询员工信息
     * @param staffPhonenum 存放要查询的员工的手机号
     * @throws Exception 抛出异常
     */
    void selectStaffPhonenum(String staffPhonenum) throws Exception;

}
