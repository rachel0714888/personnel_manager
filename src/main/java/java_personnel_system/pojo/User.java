package java_personnel_system.pojo;

import java_personnel_system.util.Print;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther Rachel
 * @date 2023/6/11 11:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String userPassword;
    private Integer userStaffId;
    private boolean isOnwork;
    private Integer userAuthority;

    public boolean getIsOnwork() {
        return isOnwork;
    }

    public User(String userName, String userPassword, Integer userStaffId, boolean isOnwork, Integer userAuthority) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userStaffId = userStaffId;
        this.isOnwork = isOnwork;
        this.userAuthority = userAuthority;
    }

    public User(String userName, String userPassword, Integer userAuthority) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAuthority = userAuthority;
    }

    public User(Integer userId, String userName, String userPassword, Integer userStaffId, Integer userAuthority) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userStaffId = userStaffId;
        this.userAuthority = userAuthority;
    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
}
