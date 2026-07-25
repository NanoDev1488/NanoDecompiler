// исходный (обфусцированный) внутренний класс: ru.nexusguard.UserProfile.IRCProfile
package ru.nexusguard;

import lombok.Generated;
import ru.nexusguard.UserProfile;

public class UserProfile_IRCProfile {

    // ---- поля ----
  private String prefix;

  public String getPrefix() {
        return prefix != null ? prefix : getRoleName();
    }

  public void setPrefix(String arg0) {
        prefix = arg0;
    }

  public String getRoleName() {
        String var1 = UserProfile.role;
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
                return "§cРазработчик";
            case 2:
                return "§4Администратор";
            case 3:
                return "§cМедиа";
            case 4:
                return "§9Бета";
            default:
                return "§7Пользователь";
        }
    }

    @Generated
  public UserProfile_IRCProfile(String arg0) { // было: <init>
        super();
        prefix = arg0;
    }

}