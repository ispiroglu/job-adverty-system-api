package com.lcwaikiki.advertservice.util.user;

import com.lcwaikiki.advertservice.model.User;
import java.lang.reflect.Field;

public class UserValidationUtil {

  public static boolean isValidForApplication(User user) {
    for (Field declaredField : user.getClass().getDeclaredFields())
      if (declaredField == null)
        return false;
    return true;
  }
}
