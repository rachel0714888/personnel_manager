package java_personnel_system.controller;

import java_personnel_system.pojo.Notice;
import java_personnel_system.pojo.Position;
import java_personnel_system.service.DepartmentService;
import java_personnel_system.service.NoticeService;
import java_personnel_system.service.PositionService;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;
import java_personnel_system.view.ManagerView;

/**
 * @auther Rachel
 * @date 2023/6/12 1:17
 */
public class ManagerController {
    public static void managerController(String key) throws Exception {
        switch (key) {
            case "1":
                Print.print("即将跳转到用户模块...");
                Thread.sleep(3000);
                MainView.adminUserView();
                break;
            case "2":
                Print.print("即将跳转到部门模块...");
                Thread.sleep(3000);
                ManagerView.managerDepartmentView();
                break;
            case "3":
                Print.print("即将跳转到职位模块...");
                Thread.sleep(3000);
                ManagerView.positionView();
                break;
            case "4":
                Print.print("即将跳转到员工模块...");
                Thread.sleep(3000);
                MainView.staffView();
                break;
            case "5":
                Print.print("即将跳转到公告模块...");
                Thread.sleep(3000);
                ManagerView.noticeView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                ManagerView.managerView();
        }
    }

    public static void departmentController(String key) throws Exception {
        switch (key) {
            case "1":
                ManagerView.addDepartmentView();
                ManagerView.managerDepartmentView();
                break;
            case "2":
                ManagerView.departmentRemoveView();
                ManagerView.managerDepartmentView();
                break;
            case "3":
                ManagerView.changeDepartmentMsgView();
                ManagerView.managerDepartmentView();
                break;
            case "4":
                ManagerView.departmentSelectView();
                ManagerView.managerDepartmentView();
                break;
            case "5":
                ManagerView.managerView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                ManagerView.managerDepartmentView();
        }
    }

    public static void departmentRemoveController(String key) throws Exception {
        switch (key) {
            case "1":
                ManagerView.removeDepartmentIdView();
                ManagerView.managerDepartmentView();
                break;
            case "2":
                ManagerView.removeDepartmentNameView();
                ManagerView.managerDepartmentView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                ManagerView.departmentRemoveView();
        }
    }

    public static void departmentSelectController(String key) throws Exception {
        switch (key) {
            case "1":
                ManagerView.selectAllDepartmentMsgView();
                ManagerView.managerDepartmentView();
                break;
            case "2":
                ManagerView.selectLikeDepartmentNameView();
                ManagerView.managerDepartmentView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                ManagerView.departmentSelectView();
        }
    }

    public static void positionController(String key) throws Exception {
        switch (key) {
            case "1":
                //在职位页面选择1——添加职位 进入职位服务
                ManagerView.addPositionView();
                ManagerView.positionView();
                break;
            case "2":
                //在职位页面选择2——删除职位 进入职位删除页面
                ManagerView.positionRemoveView();
                ManagerView.positionView();
                break;
            case "3":
                //在职位页面选择3——修改职位 进入职位服务
                ManagerView.changePositionMsgView();
                ManagerView.positionView();
                break;
            case "4":
                ManagerView.positionSelectView();
                ManagerView.positionView();
                break;
            case "5":
                ManagerView.managerView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                ManagerView.positionView();
        }
    }

    public static void positionRemoveController(String key) throws Exception {
        switch (key) {
            case "1":
                ManagerView.removePositionIdView();
                ManagerView.positionView();
                break;
            case "2":
                ManagerView.removePositionNameView();
                ManagerView.positionView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                ManagerView.positionRemoveView();
        }
    }

    public static void positionSelectController(String key) throws Exception {
        switch (key) {
            case "1":
                ManagerView.selectAllPositionMsgView();
                ManagerView.positionView();
                break;
            case "2":
                ManagerView.selectLikePositionNameView();
                ManagerView.positionView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                ManagerView.positionSelectView();
        }
    }

    public static void noticeController(String key) throws Exception {
        switch (key) {
            case "1":
                ManagerView.addNoticeView();
                ManagerView.noticeView();
                break;
            case "2":
                ManagerView.removeNoticeView();
                ManagerView.noticeView();
                break;
            case "3":
                ManagerView.updateNoticeView();
                ManagerView.noticeView();
                break;
            case "4":
                MainView.noticeSelectView();
                ManagerView.noticeView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                ManagerView.noticeView();
        }
    }

}
