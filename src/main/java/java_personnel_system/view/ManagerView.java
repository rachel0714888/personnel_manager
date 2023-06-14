package java_personnel_system.view;

import java_personnel_system.controller.ManagerController;
import java_personnel_system.controller.UserController;
import java_personnel_system.pojo.Department;
import java_personnel_system.pojo.Notice;
import java_personnel_system.service.DepartmentService;
import java_personnel_system.service.NoticeService;
import java_personnel_system.service.PositionService;
import java_personnel_system.util.Print;

import java.util.Scanner;

/**
 * @auther Rachel
 * @date 2023/6/12 1:03
 */
public class ManagerView {
    private static Scanner sc = new Scanner(System.in);

    public static void managerView() throws Exception {
        Print.print("请输入你想访问的模块");
        Print.print("1.用户模块");
        Print.print("2.部门模块");
        Print.print("3.职位模块");
        Print.print("4.员工模块");
        Print.print("5.公告模块");
        String inputKey = sc.next();
        ManagerController.managerController(inputKey);
    }

    public static void managerDepartmentView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加部门");
        Print.print("2.删除部门");
        Print.print("3.修改部门");
        Print.print("4.查询部门");
        Print.print("5.返回上一级");
        String inputKey = sc.next();
        ManagerController.departmentController(inputKey);
    }

    public static void addDepartmentView() throws Exception {
        Print.print("请输入要增加的部门名");
        String departmentName = sc.next();
        DepartmentService.addDepartment(departmentName);
        Print.print("正在为您添加部门...");
        Thread.sleep(3000);
        Print.print("部门添加完毕，将为您返回部门页面");
    }

    public static void removeDepartmentIdView() throws Exception {
        Print.print("请输入要删除的部门id");
        int departmentId = sc.nextInt();
        Print.print("该部门还有员工，请确认是否删除");
        Print.print("1.是");
        Print.print("2.否");
        int confirm = sc.nextInt();
        DepartmentService.removeDepartmentId(departmentId, confirm);
        Print.print("正在为您删除部门...");
        Thread.sleep(3000);
        Print.print("部门删除完毕，将为您返回部门页面");
    }

    public static void removeDepartmentNameView() throws Exception {
        Print.print("请输入要删除的部门名");
        String departmentName = sc.next();
        Print.print("该部门还有员工，请确认是否删除");
        Print.print("1.是");
        Print.print("2.否");
        int confirm = sc.nextInt();
        DepartmentService.removeDepartmentName(departmentName, confirm);
        Print.print("正在为您删除部门...");
        Thread.sleep(3000);
        Print.print("部门删除完毕，将为您返回部门页面");
    }

    public static void departmentRemoveView() throws Exception {
        Print.print("请输入你想根据什么删除部门");
        Print.print("1.部门id");
        Print.print("2.部门名");
        String inputKey = sc.next();
        ManagerController.departmentRemoveController(inputKey);
    }

    public static void changeDepartmentMsgView() throws Exception {
        Print.print("请输入要修改的部门id");
        int departmentId = sc.nextInt();
        Print.print("请输入修改后的部门名");
        String departmentName = sc.next();
        DepartmentService.changeDepartmentMsg(departmentId, departmentName);
        Print.print("正在为您修改部门...");
        Thread.sleep(3000);
        Print.print("部门修改完毕，将为您返回部门页面");
    }

    public static void departmentSelectView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.查询所有部门");
        Print.print("2.查询指定部门");
        String inputKey = sc.next();
        ManagerController.departmentSelectController(inputKey);
    }

    public static void selectAllDepartmentMsgView() throws Exception {
        Print.print("正在查询部门信息...");
        Thread.sleep(3000);
        DepartmentService.selectAllDepartmentMsg();
        Print.print("部门信息查询成功");
    }

    public static void selectLikeDepartmentNameView() throws Exception {
        Print.print("请输入要查询的部门名");
        String departmentName = sc.next();
        Print.print("正在查询部门信息...");
        Thread.sleep(3000);
        DepartmentService.selectLikeDepartmentName(departmentName);
        Print.print("部门信息查询成功");
    }

    public static void positionView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加职位");
        Print.print("2.删除职位");
        Print.print("3.修改职位");
        Print.print("4.查询职位");
        Print.print("5.返回上一级");
        String inputKey = sc.next();
        ManagerController.positionController(inputKey);
    }

    public static void addPositionView() throws Exception {
        Print.print("请输入要增加的职位名");
        String positionName = sc.next();
        PositionService.addPosition(positionName);
        Print.print("正在为您增加职位...");
        Thread.sleep(3000);
        Print.print("职位增加成功，将为您返回职位界面");
    }

    public static void removePositionIdView() throws Exception {
        Print.print("请输入要删除的职位id");
        int positionId = sc.nextInt();
        Print.print("是否确认删除该职位，对应员工将会调整为未定义员工");
        Print.print("1.是");
        Print.print("2.否");
        int confirm = sc.nextInt();
        PositionService.removePositionId(positionId, confirm);
        Print.print("正在为您删除职位...");
        Thread.sleep(3000);
        Print.print("职位删除成功，将为您返回职位界面");
    }

    public static void removePositionNameView() throws Exception {
        Print.print("请输入要删除的职位名");
        String positionName = sc.next();
        Print.print("是否确认删除该职位，对应员工将会调整为未定义员工");
        Print.print("1.是");
        Print.print("2.否");
        int confirm = sc.nextInt();
        PositionService.removePositionName(positionName,confirm);
        Print.print("正在为您删除职位...");
        Thread.sleep(3000);
        Print.print("职位删除成功，将为您返回职位界面");
    }


    public static void positionRemoveView() throws Exception {
        Print.print("请输入你想根据什么删除职位");
        Print.print("1.职位id");
        Print.print("2.职位名");
        String inputKey = sc.next();
        ManagerController.positionRemoveController(inputKey);
    }

    public static void changePositionMsgView() throws Exception {
        Print.print("请输入要修改的职位id");
        int positionId = sc.nextInt();
        Print.print("请输入修改后的职位名");
        String positionName = sc.next();
        PositionService.changePositionMsg(positionId, positionName);
        Print.print("正在为您修改职位...");
        Thread.sleep(3000);
        Print.print("职位修改成功，将为您返回职位界面");
    }

    public static void positionSelectView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.查询所有职位");
        Print.print("2.查询指定职位");
        String inputKey = sc.next();
        ManagerController.positionSelectController(inputKey);
    }

    public static void selectAllPositionMsgView() throws Exception {
        Print.print("正在查询职位信息...");
        Thread.sleep(3000);
        PositionService.selectAllPositionMsg();
        Print.print("职位信息查询成功，将为您返回职位界面");
    }

    public static void selectLikePositionNameView() throws Exception {
        Print.print("请输入要查询的职位名");
        String positionName = sc.next();
        Print.print("正在查询职位信息...");
        Thread.sleep(3000);
        PositionService.selectLikePositionName(positionName);
        Print.print("职位信息查询成功，将为您返回职位界面");
    }

    public static void noticeView() throws Exception {
        Print.print("请输入你想访问的功能");
        Print.print("1.发布公告");
        Print.print("2.删除公告");
        Print.print("3.更改公告");
        Print.print("4.查询公告");
        String inputKey = sc.next();
        ManagerController.noticeController(inputKey);
    }

    public static void addNoticeView() throws Exception {
        Print.print("请输入要增加的公告内容");
        String noticeContent = sc.next();
        Print.print("请输入要增加的公告名");
        String noticeName = sc.next();
        NoticeService.addNotice(noticeContent, noticeName);
        Print.print("正在为您增加公告...");
        Thread.sleep(3000);
        Print.print("公告发布完毕，将为您返回公告页面");
    }

    public static void removeNoticeView() throws Exception {
        Print.print("请输入要删除的公告的id");
        int noticeId = sc.nextInt();
        NoticeService.removeNotice(noticeId);
        Print.print("正在为您删除公告...");
        Thread.sleep(3000);
        Print.print("公告删除完毕，将为您返回公告页面");
    }

    public static void updateNoticeView() throws Exception {
        Print.print("请输入要更改的公告的id");
        int noticeId = sc.nextInt();
        Print.print("请输入更改后的公告内容");
        String noticeContent = sc.next();
        Print.print("请输入更改后的公告名");
        String noticeName = sc.next();
        NoticeService.updateNotice(new Notice(noticeId, noticeContent, noticeName));
        Print.print("正在为您更改公告...");
        Thread.sleep(3000);
        Print.print("公告更改完毕，将为您返回公告页面");
    }


}
