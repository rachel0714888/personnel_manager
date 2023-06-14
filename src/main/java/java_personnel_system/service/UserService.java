package java_personnel_system.service;

import java_personnel_system.DAO.UserDao;
import java_personnel_system.DAO.UserDaoimpl;
import java_personnel_system.pojo.User;
import java_personnel_system.util.Print;
import java_personnel_system.util.RandomStr;
import java_personnel_system.view.MainView;
import java_personnel_system.view.ManagerView;
import java_personnel_system.view.OrdinaryUserView;
import java_personnel_system.view.PersonnelView;

import java.util.Scanner;

/**
 * @auther Rachel
 * @date 2023/6/11 19:27
 */
public class UserService {
    private static UserDao userDao = new UserDaoimpl();

    public static void login(User user, String confirmCode, String inputConfirmCode) throws Exception {
        //进行验证码确认
        if (confirmCode.equalsIgnoreCase(inputConfirmCode)) {
            Print.print("验证码通过,正在验证用户名和密码,请稍后...");
            Thread.sleep(3000);
            int isLogin = userDao.match(user);
            //根据返回值判断该用户的状态
            if (isLogin == 1) {
                Print.print("登陆成功");
                //登陆成功，判断权限，根据不同的权限进入不同的操作页面
                int authority = MainView.currentUser.getUserAuthority();
                if (authority == 0) {
                    ManagerView.managerView();
                } else if (authority == 1) {
                    PersonnelView.personnelView();
                } else if (authority == 2) {
                    OrdinaryUserView.ordinaryUserView();
                }
            } else if (isLogin == 2) {
                Print.print("用户已离职");
            } else if (isLogin == 3) {
                Print.print("密码错误");
            } else if (isLogin == 4) {
                Print.print("用户不存在");
            } else if (isLogin == 5) {
                Print.print("用户已登录");
            }
        } else {
            Print.print("验证码输入错误，请重新登录");
        }
        MainView.mainView();
    }

    public static void userAdd(String inputUserName, String inputPassword, int inputUserStaffId, int inputIswork, int inputUserAuthority) throws Exception {
        //判断输入的用户名是否存在 如果存在返回用户添加视图
        boolean isExistUserName = userDao.userNameExist(inputUserName);
        if (isExistUserName) {
            Print.print("用户名已存在，请重新输入");
            MainView.userAddView();
        }
        //判断输入的用户的员工id是否存在 存在则返回
        boolean isExistStaffId = userDao.userStaffIdExist(inputUserStaffId);
        if (isExistStaffId) {
            Print.print("员工已有账号，请重新输入");
            MainView.userAddView();
        }
        //由于数据库的布尔用tinyint存储 在这里进行对应转换
        ;boolean iswork = false;
        if (inputIswork == 1) {
            iswork = true;
        }
        if (inputIswork == 2) {
            iswork = false;
        } else {
            Print.print("输入有误，重新输入");
            MainView.userAddView();
        }
        int userAuthority = 2;
        if (inputUserAuthority == 1) {
            userAuthority = 1;
        } else if (inputUserAuthority == 3) {
            userAuthority = 5;
        } else if (inputUserAuthority == 2) {
        } else {
            Print.print("输入有误，重新输入");
            MainView.userAddView();
        }
        //调用Dao的方法做数据库数据插入
        userDao.insert(new User(inputUserName, inputPassword, inputUserStaffId, iswork, userAuthority));
    }

    public synchronized static void userIdRemove(int inputUserId) throws Exception {
        //判断用户id是否存在
        if (userDao.userIdExist(inputUserId)) {
            userDao.idRemove(inputUserId);
            userDao.userTableAlter();
        } else {
            Print.print("用户不存在，将为您返回用户删除界面");
            MainView.userIdRemoveView();
        }
    }

    public synchronized static void userNameRemove(String inputUserName) throws Exception {
        //判断用户名是否存在
        if (userDao.userNameExist(inputUserName)) {
            userDao.nameRemove(inputUserName);
            userDao.userTableAlter();
        } else {
            Print.print("用户不存在，将为您返回用户删除界面");
            MainView.userNameRemoveView();
        }
    }

    public synchronized static void userStaffIdRemove(int inputUserStaffId) throws Exception {
        //判断用户表是否有该员工id
        if (userDao.userStaffIdExist(inputUserStaffId)) {
            userDao.staffIdRemove(inputUserStaffId);
            userDao.userTableAlter();
        } else {
            Print.print("用户不存在，将为您返回用户删除界面");
            MainView.userStaffIdRemoveView();
        }
    }

    public synchronized static void userIsWorkRemove(int iswork) throws Exception {
        int isOnwork = 0;
        if (iswork == 1) {
            isOnwork = 1;
        } else if (iswork == 2) {
            isOnwork = 0;
        } else {
            Print.print("输入错误，请重新输入");
            MainView.userIsworkRemoveView();
        }
        userDao.isWorkRemove(isOnwork);
        userDao.userTableAlter();
    }

    public static void userNameChange(int userId, String userName) throws Exception {
        //判断要修改的用户是否存在
        if (!userDao.userIdExist(userId)) {
            Print.print("用户不存在，将为您返回用户修改界面");
            MainView.userChangeView();
        }
        userDao.userNameChange(userId, userName);
    }

    public static void userPasswordChange(int userId, String userPassword) throws Exception {
        //判断要修改的用户是否存在
        if (!userDao.userIdExist(userId)) {
            Print.print("用户不存在，将为您返回用户修改界面");
            MainView.userChangeView();
        }
        userDao.userPasswordChange(userId, userPassword);
    }

    public static void userStaffidChange(int userId, int staffId) throws Exception {
        //判断要修改的用户是否存在
        if (!userDao.userIdExist(userId)) {
            Print.print("用户不存在，将为您返回用户修改界面");
            MainView.userChangeView();
        }
        userDao.userStaffidChange(userId, staffId);
    }

    public static void userIsOnworkChange(int userId, int isWork) throws Exception {
        //判断要修改的用户是否存在
        if (!userDao.userIdExist(userId)) {
            Print.print("用户不存在，将为您返回用户修改界面");
            MainView.userChangeView();
        }
        if (isWork != 0 & isWork != 1) {
            Print.print("输入有误，请重新输入");
            MainView.changeUserIsOnworkView();
        }
        userDao.userIsOnworkChange(userId, isWork);
    }

    public static void userAuthorityChange(int userId, int inputAuthority) throws Exception {
        //判断要修改的用户是否存在
        if (!userDao.userIdExist(userId)) {
            Print.print("用户不存在，将为您返回用户修改界面");
            MainView.userChangeView();
        }
        int authority = 0;
        //人事部长权限
        if (inputAuthority == 1) {
            authority = 1;
            //普通员工权限
        } else if (inputAuthority == 2) {
            authority = 2;
            //离职员工权限
        } else if (inputAuthority == 3) {
            authority = 5;
        }
        userDao.changeUserAuthority(userId,authority);
    }

    public static void ordinaryUserMsgUpdate(String userName,String password) throws Exception {
        userDao.ordinaryUserMsgUpdate(userName, password);
    }

    public static void userAllSelect() throws Exception {
        userDao.userAllSelect();
    }

    public static void userLikeNameSelect(String userName) throws Exception {
        if (!userDao.userNameExist(userName)) {
            Print.print("用户不存在，将为您返回用户指定查询界面");
            MainView.userLikeNameSelectView();
        }
        userDao.userLikeNameSelect(userName);
    }

    public static void userLikeIsOnworkSelect(int iswork) throws Exception {
        if (iswork != 0 & iswork != 1) {
            Print.print("输入有误，重新输入");
            MainView.userLikeIsOnworkSelectView();
        }else if (userDao.userIsworkExist(iswork)){
            Print.print("员工不存在，将为您返回用户查询界面");
            MainView.userSelectView();
        }
        userDao.userLikeIsworkSelect(iswork);
    }

    public static int getUserStaffId(String userName) throws Exception {
        return userDao.selectUserStaffId(userName);
    }


}
