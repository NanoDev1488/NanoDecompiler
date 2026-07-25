// исходный (обфусцированный) внутренний класс: ru.nexusguard.UserProfile
package ru.nexusguard;

import ru.nexusguard.IGuard;
import ru.nexusguard.UserProfile_IRCProfile;

public class UserProfile implements IGuard {

    // ---- поля ----
  public static UserProfile instance;
  public UserProfile_IRCProfile irc;
  public static String username;
  public static String hwid;
  public static int uid;
  public static String role;

    static {
        instance = new UserProfile();
        username = "NexusUser";
        hwid = "123";
        uid = 1337;
        role = "DEV";
    }

  public UserProfile() { // было: <init>
        super();
        irc = new UserProfile_IRCProfile(((String) null));
    }

  public String username() {
        return username;
    }

  public String hwid() {
        return hwid;
    }

  public String role() {
        return role;
    }

  public int uid() {
        return uid;
    }

  public String roleName() {
        String var1 = role;
        int var2 = -1;
        switch (var1.hashCode()) {
            case 67573:
                if (!var1.equals("DEV")) {
                    break;
                }
                var2 = 1;
                break;
            case 2035184:
                if (!var1.equals("BETA")) {
                    break;
                }
                var2 = 4;
                break;
            case 2094806:
                if (!var1.equals("DEV+")) {
                    break;
                }
                var2 = 0;
                break;
            case 62130991:
                if (!var1.equals("ADMIN")) {
                    break;
                }
                var2 = 2;
                break;
            case 73234372:
                if (!var1.equals("MEDIA")) {
                    break;
                }
                var2 = 3;
            default:
        }
        switch (var2) {
            case 0:
            case 1:
                return "Разработчик";
            case 2:
                return "Администратор";
            case 3:
                return "Медиа";
            case 4:
                return "Бета";
            default:
                return "Пользователь";
        }
    }

}