package java_personnel_system.view;

import java_personnel_system.controller.ManagerController;
import java_personnel_system.controller.NoticeController;
import java_personnel_system.controller.StaffController;
import java_personnel_system.controller.UserController;
import java_personnel_system.pojo.Notice;
import java_personnel_system.pojo.Staff;
import java_personnel_system.pojo.User;
import java_personnel_system.service.NoticeService;
import java_personnel_system.service.StaffService;
import java_personnel_system.service.UserService;
import java_personnel_system.util.ConnectionPool;
import java_personnel_system.util.Print;
import java_personnel_system.util.RandomStr;
import lombok.SneakyThrows;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java_personnel_system.service.UserService.userAdd;

/**
 * @auther Rachel
 * @date 2023/6/11 11:20
 */
public class MainView {
    public static ConnectionPool cp = new ConnectionPool(15);

    private static Scanner sc = new Scanner(System.in);
    public static User currentUser = null;

    public static void mainView() throws Exception {
        //必须要登录，且验证是在职员工才能进入系统
        Print.print("欢迎来到员工管理系统");
        Print.print("请输入你登录的用户名:");
        String inputUserName = sc.next();
        Print.print("请输入您的密码:");
        String inputPassword = sc.next();
        String confirmCode = RandomStr.getRandomString();
        Print.print("验证码为:" + confirmCode + ",请输入验证码进行验证,验证码不区分大小写");
        String inputConfirmCode = sc.next();
        UserService.login(new User(inputUserName, inputPassword), confirmCode, inputConfirmCode);
    }

    //用户模块

    public static void adminUserView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加用户");
        Print.print("2.删除用户");
        Print.print("3.修改用户");
        Print.print("4.查询用户");
        Print.print("5.返回上一级");
        String inputKey = sc.next();
        UserController.userController(inputKey);
    }

    public static void userAddView() throws Exception {
        Print.print("请输入您想要添加的用户名：");
        String inputUserName = sc.next();
        Print.print("请输入您想要添加的密码：");
        String inputPassword = sc.next();
        Print.print("请输入您想要添加的用户的员工id：");
        int inputUserStaffId = sc.nextInt();
        Print.print("请输入您想要添加的用户是否是在职状态：");
        Print.print("1.是");
        Print.print("2.否");
        int inputIswork = sc.nextInt();
        Print.print("请输入您想要添加的用户的权限：");
        Print.print("1.人事部长权限");
        Print.print("2.普通员工权限");
        Print.print("3.离职员工权限");
        int inputUserAuthority = sc.nextInt();
        UserService.userAdd(inputUserName, inputPassword, inputUserStaffId, inputIswork, inputUserAuthority);
        Print.print("用户添加中...");
        Thread.sleep(3000);
        Print.print("用户添加成功！将为您返回用户界面");
    }

    public static void userIdRemoveView() throws Exception {
        Print.print("请输入您想要删除的用户id：");
        int inputUserId = sc.nextInt();
        UserService.userIdRemove(inputUserId);
        Print.print("用户删除中...");
        Thread.sleep(3000);
        Print.print("用户删除成功！将为您返回用户界面");
    }

    public static void userNameRemoveView() throws Exception {
        Print.print("请输入您想要删除的用户名：");
        String inputUserName = sc.next();
        UserService.userNameRemove(inputUserName);
        Print.print("用户删除中...");
        Thread.sleep(3000);
        Print.print("用户删除成功！将为您返回用户界面");
    }

    public static void userStaffIdRemoveView() throws Exception {
        Print.print("请输入您想要删除的用户员工id：");
        int inputUserStaffId = sc.nextInt();
        UserService.userStaffIdRemove(inputUserStaffId);
        Print.print("用户删除中...");
        Thread.sleep(3000);
        Print.print("用户删除成功！将为您返回用户界面");
    }

    public static void userIsworkRemoveView() throws Exception {
        Print.print("请输入您想要删除什么状态的员工：");
        Print.print("1.在职");
        Print.print("2.离职");
        int iswork = sc.nextInt();
        UserService.userIsWorkRemove(iswork);
        Print.print("用户删除中...");
        Thread.sleep(3000);
        Print.print("用户删除成功！将为您返回用户界面");
    }

    public static void userChangeView() throws Exception {
        Print.print("请输入您要修改的属性：");
        Print.print("1.用户名");
        Print.print("2.密码");
        Print.print("3.员工id");
        Print.print("4.员工状态");
        Print.print("5.员工权限");
        UserController.userChangeController(sc.next());
    }

    public static void changeUserAuthorityView() throws Exception {
        Print.print("请输入要修改的用户id：");
        int userId = sc.nextInt();
        Print.print("请输入修改后的员工权限");
        Print.print("1.人事部长");
        Print.print("2.普通职员");
        Print.print("3.离职");
        int authority = sc.nextInt();
        UserService.userAuthorityChange(userId, authority);
        Print.print("用户修改中...");
        Thread.sleep(3000);
        Print.print("用户修改成功！将为您返回用户界面");
    }

    public static void changeUserIsOnworkView() throws Exception {
        Print.print("请输入要修改的用户id：");
        int userId = sc.nextInt();
        Print.print("请输入修改后的员工状态");
        Print.print("0.离职");
        Print.print("1.在职");
        int isWork = sc.nextInt();
        UserService.userIsOnworkChange(userId, isWork);
        Print.print("用户修改中...");
        Thread.sleep(3000);
        Print.print("用户修改成功！将为您返回用户界面");
    }

    public static void changeUserStaffidView() throws Exception {
        Print.print("请输入要修改的用户id：");
        int userId = sc.nextInt();
        Print.print("请输入修改后的员工id：");
        int staffId = sc.nextInt();
        UserService.userStaffidChange(userId, staffId);
        Print.print("用户修改中...");
        Thread.sleep(3000);
        Print.print("用户修改成功！将为您返回用户界面");
    }

    public static void changeUserPasswordView() throws Exception {
        Print.print("请输入要修改的用户id：");
        int userId = sc.nextInt();
        Print.print("请输入修改后的密码：");
        String userPassword = sc.next();
        UserService.userPasswordChange(userId, userPassword);
        Print.print("用户修改中...");
        Thread.sleep(3000);
        Print.print("用户修改成功！将为您返回用户界面");
    }

    public static void changeUserNameView() throws Exception {
        Print.print("请输入要修改的用户id：");
        int userId = sc.nextInt();
        Print.print("请输入修改后的用户名：");
        String userName = sc.next();
        UserService.userNameChange(userId, userName);
        Print.print("用户修改中...");
        Thread.sleep(3000);
        Print.print("用户修改成功！将为您返回用户界面");
    }

    public static void userSelectView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.查询全部用户");
        Print.print("2.查询指定用户");
        Print.print("3.返回上一级");
        String inputKey = sc.next();
        UserController.userSelectController(inputKey);
    }

    public static void userAllSelectView() throws Exception {
        Print.print("正在为您查询用户信息...");
        Thread.sleep(3000);
        UserService.userAllSelect();
        Print.print("用户信息查询成功，将为您返回用户界面");
    }

    public static void userLikeSelectView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.根据用户名查询用户信息");
        Print.print("2.根据用户状态查询用户信息");
        String inputKey = sc.next();
        UserController.userLikeSelectController(inputKey);
    }

    public static void userLikeIsOnworkSelectView() throws Exception {
        Print.print("请输入要查询的用户状态：");
        Print.print("0.离职");
        Print.print("1.在职");
        int iswork = sc.nextInt();
        Print.print("正在为您查询用户信息...");
        Thread.sleep(3000);
        UserService.userLikeIsOnworkSelect(iswork);
        Print.print("用户信息查询成功，将为您返回用户界面");
    }

    public static void userLikeNameSelectView() throws Exception {
        Print.print("请输入要查询的用户名：");
        String userName = sc.next();
        Print.print("正在为您查询用户信息...");
        Thread.sleep(3000);
        UserService.userLikeNameSelect(userName);
        Print.print("用户信息查询成功，将为您返回用户界面");
    }

    public static void userRemoveView() throws Exception {
        Print.print("请输入你想根据什么删除用户：");
        Print.print("1.用户id");
        Print.print("2.用户名");
        Print.print("3.用户的员工id");
        Print.print("4.用户状态");
        Print.print("5.返回上一级");
        String inputKey = sc.next();
        UserController.userRemoveController(inputKey);
    }

    //员工模块

    public static void staffView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加员工");
        Print.print("2.删除员工");
        Print.print("3.修改员工信息");
        Print.print("4.查询员工信息");
        Print.print("5.返回上一级");
        String inputKey = sc.next();
        StaffController.staffController(inputKey);
    }

    public static void staffAddView() throws Exception {
        Print.print("请输入您想要添加的员工姓名：");
        String staffName = sc.next();
        Print.print("请输入您想要添加的员工性别：");
        String staffSex = sc.next();
        Print.print("请输入您想要添加的员工的部门id：");
        int sdepartmentId = sc.nextInt();
        Print.print("请输入您想要添加的员工的职位id：");
        int spositionId = sc.nextInt();
        Print.print("请输入您想要添加的员工的民族：");
        String staffNation = sc.next();
        Print.print("请输入您想要添加的员工的学历：");
        String staffEducation = sc.next();
        Print.print("请输入您想要添加的员工的身份证：");
        String staffIdentityId = sc.next();
        Print.print("请输入您想要添加的员工的电话号码：");
        String staffPhonenum = sc.next();
        StaffService.addStaff(new Staff(staffName, staffSex, sdepartmentId, spositionId, staffNation, staffEducation, staffIdentityId, staffPhonenum));
        Print.print("正在为您添加员工...");
        Thread.sleep(3000);
        Print.print("员工添加完毕，将为您返回员工界面");
    }

    public static void staffRemoveView() throws Exception {
        Print.print("请输入你想根据什么删除员工：");
        Print.print("1.员工id");
        Print.print("2.员工名");
        Print.print("3.返回上一级");
        String inputKey = sc.next();
        StaffController.staffRemoveController(inputKey);
    }

    public static void removeStaffIdView() throws Exception {
        Print.print("请输入您想要删除的员工id：");
        int staffId = sc.nextInt();
        StaffService.removeStaffId(staffId);
        Print.print("正在为您删除员工...");
        Thread.sleep(3000);
        Print.print("员工删除完毕，将为您返回员工界面");
    }

    public static void removeStaffNameView() throws Exception {
        Print.print("请输入您想要删除的员工姓名：");
        String staffName = sc.next();
        StaffService.removeStaffName(staffName);
        Print.print("正在为您删除员工...");
        Thread.sleep(3000);
        Print.print("员工删除完毕，将为您返回员工界面");
    }

    public static void updateStaffView()throws Exception{
        Print.print("请选择您要修改的属性：");
        Print.print("1.姓名");
        Print.print("2.性别");
        Print.print("3.部门id");
        Print.print("4.职位id");
        Print.print("5.民族");
        Print.print("6.学历");
        Print.print("7.身份证");
        Print.print("8.电话号");
        Print.print("9.全部更改");
        StaffController.staffUpdateController(sc.next());
    }

    public static void updateStaffPhonenumView()throws Exception{
        Print.print("请输入您想要修改的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("请输入您修改后的员工的电话号码：");
        String staffPhonenum = sc.next();
        StaffService.updateStaffPhonenum(staffId,staffPhonenum);
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void updateStaffIdentityidView()throws Exception{
        Print.print("请输入您想要修改的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("请输入您修改后的员工的身份证：");
        String staffIdentityId = sc.next();
        StaffService.updateStaffIdentityid(staffId,staffIdentityId);
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void updateStaffEducationView()throws Exception{
        Print.print("请输入您想要修改的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("请输入您修改后的员工的学历：");
        String staffEducation = sc.next();
        StaffService.updateStaffEducation(staffId,staffEducation);
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void updateStaffNationView()throws Exception{
        Print.print("请输入您想要修改的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("请输入您修改后的员工的民族：");
        String staffNation = sc.next();
        StaffService.updateStaffNation(staffId,staffNation);
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void updateStaffPositionIdView()throws Exception{
        Print.print("请输入您想要修改的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("请输入您修改后的员工的职位id：");
        int spositionId = sc.nextInt();
        StaffService.updateStaffPositionId(staffId,spositionId);
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void updateStaffDepartmentIdView()throws Exception{
        Print.print("请输入您想要修改的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("请输入您修改后的员工的部门id：");
        int sdepartmentId = sc.nextInt();
        StaffService.updateStaffDepartmentId(staffId,sdepartmentId);
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void updateStaffSexView()throws Exception{
        Print.print("请输入您想要修改的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("请输入您修改后的员工性别：");
        String staffSex = sc.next();
        StaffService.updateStaffSex(staffId,staffSex);
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void updateStaffNameView()throws Exception{
        Print.print("请输入您想要修改的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("请输入您修改后的员工姓名：");
        String staffName = sc.next();
        StaffService.updateStaffName(staffId,staffName);
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void updateStaffMsgView() throws Exception {
        Print.print("请输入您想要修改的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("请输入您修改后的员工姓名：");
        String staffName = sc.next();
        Print.print("请输入您修改后的员工性别：");
        String staffSex = sc.next();
        Print.print("请输入您修改后的员工的部门id：");
        int sdepartmentId = sc.nextInt();
        Print.print("请输入您修改后的员工的职位id：");
        int spositionId = sc.nextInt();
        Print.print("请输入您修改后的员工的民族：");
        String staffNation = sc.next();
        Print.print("请输入您修改后的员工的学历：");
        String staffEducation = sc.next();
        Print.print("请输入您修改后的员工的身份证：");
        String staffIdentityId = sc.next();
        Print.print("请输入您修改后的员工的电话号码：");
        String staffPhonenum = sc.next();
        StaffService.updateStaffMsg(new Staff(staffId, staffName, staffSex, sdepartmentId, spositionId, staffNation, staffEducation, staffIdentityId, staffPhonenum));
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void staffSelectView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.查询全部员工");
        Print.print("2.查询指定员工");
        Print.print("3.返回上一级");
        String inputKey = sc.next();
        StaffController.staffSelectController(inputKey);
    }

    public static void selectAllStaffMsgView() throws Exception {
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectAllStaffMsg();
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    public static void staffLikeSelectView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.根据员工id查询员工信息");
        Print.print("2.根据员工姓名查询员工信息");
        Print.print("3.根据员工性别查询员工信息");
        Print.print("4.根据员工部门id查询员工信息");
        Print.print("5.根据员工职位id查询员工信息");
        Print.print("6.根据员工民族查询员工信息");
        Print.print("7.根据员工学历查询员工信息");
        Print.print("8.根据员工身份证查询员工信息");
        Print.print("9.根据员工手机号查询员工信息");
        String inputKey = sc.next();
        StaffController.staffLikeSelectController(inputKey);
    }

    public static void selectStaffIdView() throws Exception {
        Print.print("请输入您想要查询的员工的员工id");
        int staffId = sc.nextInt();
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffId(staffId);
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    public static void selectStaffNameView() throws Exception {
        Print.print("请输入您要查询的员工姓名：");
        String staffName = sc.next();
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffName(staffName);
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    public static void selectStaffSexView() throws Exception {
        Print.print("请输入您要查询的员工性别：");
        String staffSex = sc.next();
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffSex(staffSex);
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    public static void selectStaffDepartmentIdView() throws Exception {
        Print.print("请输入您要查询的员工的部门id：");
        int sdepartmentId = sc.nextInt();
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffDepartmentId(sdepartmentId);
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    public static void selectStaffPrositionIdView() throws Exception {
        Print.print("请输入您要查询的员工的职位id：");
        int spositionId = sc.nextInt();
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffPrositionId(spositionId);
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    public static void selectStaffNationView() throws Exception {
        Print.print("请输入您要查询的员工的民族：");
        String staffNation = sc.next();
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffNation(staffNation);
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    public static void selectStaffEducationView() throws Exception {
        Print.print("请输入您要查询的员工的学历：");
        String staffEducation = sc.next();
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffEducation(staffEducation);
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    public static void selectStaffIdentityIdView() throws Exception {
        Print.print("请输入您要查询的员工的身份证：");
        String staffIdentityId = sc.next();
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffIdentityId(staffIdentityId);
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    public static void selectStaffPhonenumView() throws Exception {
        Print.print("请输入您要查询的员工的电话号码：");
        String staffPhonenum = sc.next();
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffPhonenum(staffPhonenum);
        Print.print("员工信息查询完毕，将为您返回员工界面");
    }

    //公告模块

    public static void noticeSelectView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.所有公告查询");
        Print.print("2.指定公告查询");
        String inputKey = sc.next();
        NoticeController.noticeSelectViewController(inputKey);
    }

    public static void selectAllNoticeView()throws Exception{
        Print.print("正在查询公告...");
        Thread.sleep(3000);
        NoticeService.selectAllNotice();
        Print.print("公告查询完毕，将为您返回公告页面");
    }

    public static void noticeLikeSelectView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.根据公告名查询");
        Print.print("2.根据公告内容查询");
        String inputKey = sc.next();
        NoticeController.noticeLikeSelectController(inputKey);
    }

    public static void selectNoticeNameView()throws Exception{
        Print.print("请输入要查询的公告名");
        String noticeName = sc.next();
        Print.print("正在查询公告...");
        Thread.sleep(3000);
        NoticeService.selectNoticeName(noticeName);
        Print.print("公告查询完毕，将为您返回公告页面");
    }

    public static void selectNoticeContentView()throws Exception{
        Print.print("请输入要查询的公告内容");
        String noticeContent = sc.next();
        Print.print("正在查询公告...");
        Thread.sleep(3000);
        NoticeService.selectNoticeContent(noticeContent);
        Print.print("公告查询完毕，将为您返回公告页面");
    }
}
