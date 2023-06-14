package java_personnel_system.view;

import java_personnel_system.DAO.UserDao;
import java_personnel_system.DAO.UserDaoimpl;
import java_personnel_system.controller.OrdinaryUserController;
import java_personnel_system.controller.PersonnelController;
import java_personnel_system.controller.StaffController;
import java_personnel_system.service.StaffService;
import java_personnel_system.service.UserService;
import java_personnel_system.util.Print;

import java.util.Scanner;

/**
 * @auther Rachel
 * @date 2023/6/12 14:53
 */
public class OrdinaryUserView {
    private static Scanner sc = new Scanner(System.in);

    public static void ordinaryUserView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.用户模式");
        Print.print("2.员工模式");
        Print.print("3.公告信息查询");
        String inputKey = sc.next();
        OrdinaryUserController.ordinaryUserController(inputKey);
    }

    public static void userView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.查看用户信息");
        Print.print("2.修改用户信息");
        String inputKey = sc.next();
        OrdinaryUserController.userController(inputKey);
    }

    public static void userChangeView() throws Exception {
        Print.print("请输入修改后的用户名：");
        String userName = sc.next();
        Print.print("请输入修改后的用户密码：");
        String password = sc.next();
        UserService.ordinaryUserMsgUpdate(userName, password);
        Print.print("用户信息修改中...");
        Thread.sleep(3000);
        Print.print("用户信息修改完毕，将为您返回用户界面");
    }

    public static void staffView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.查看员工信息");
        Print.print("2.修改员工信息");
        String inputKey = sc.next();
        OrdinaryUserController.staffController(inputKey);
    }

    public static void ordinaryUserUpdateStaffMsgView() throws Exception {
        Print.print("请输入您修改后的员工姓名：");
        String staffName = sc.next();
        Print.print("请输入您修改后的员工的身份证：");
        String staffIdentityId = sc.next();
        Print.print("请输入您修改后的员工的电话号码：");
        String staffPhonenum = sc.next();
        StaffService.ordinaryUserUpdateStaffMsg(staffName, staffIdentityId, staffPhonenum);
        Print.print("正在为您修改员工信息...");
        Thread.sleep(3000);
        Print.print("员工信息修改成功，将为您返回员工界面");
    }

    public static void selectStaffIdNoInputView() throws Exception {
        Print.print("正在查询员工信息...");
        Thread.sleep(3000);
        StaffService.selectStaffIdNoInput(MainView.currentUser.getUserStaffId());
        Print.print("员工信息查询完毕");
    }

    public static void updateStaffView() throws Exception {
        Print.print("请选择您要修改的属性：");
        Print.print("1.姓名");
        Print.print("2.性别");
        Print.print("3.民族");
        Print.print("4.身份证");
        Print.print("5.电话号");
        OrdinaryUserController.staffUpdateController(sc.next());
    }

}
