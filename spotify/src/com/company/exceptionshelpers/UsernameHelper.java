package com.company.exceptionshelpers;

import com.company.exceptions.IncorrectUsernameException;
import java.util.Iterator;
import java.util.List;

public class UsernameHelper {
    public UsernameHelper() {
    }

    public void validarUsername(String user, String pattern, List<String> users, Integer pos) throws IncorrectUsernameException {
        Iterator<String> iterate = users.iterator();
        users.add(pos, user); //users[pos] = user;
        if (users.size() > 1) {
            for (int i = 0; i < pos; i++) {
                if (user.equals(users.get(i))) {
                    throw new IncorrectUsernameException("that username already exists.\n");
                }
            }
        }

            if (!user.matches(pattern)) {
                throw new IncorrectUsernameException("the username has been incorrectly typed.\n");
            }

            if (user.length() < 8) {
                throw new IncorrectUsernameException("the nickname has less than 8 characters.\n");
            }

            if (user.length() > 30) {
                throw new IncorrectUsernameException("the nickname has exceeded the 30 characters limit.\n");
            }

        }
    }


